package com.mopub.network;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.MoPubError;
import java.util.ArrayList;
import java.util.List;

class ContentDownloadAnalytics {
    private static final String LOAD_DURATION_MS_MACRO = "%%LOAD_DURATION_MS%%";
    private static final String LOAD_RESULT_MACRO = "%%LOAD_RESULT%%";
    private AdResponse mAdResponse;
    Long mBeforeLoadTime = null;

    enum DownloadResult {
        AD_LOADED("ad_loaded"),
        MISSING_ADAPTER("missing_adapter"),
        TIMEOUT("timeout"),
        INVALID_DATA("invalid_data");
        
        /* access modifiers changed from: private */
        public final String value;

        private DownloadResult(String str) {
            this.value = str;
        }
    }

    ContentDownloadAnalytics(AdResponse adResponse) {
        Preconditions.checkNotNull(adResponse);
        this.mAdResponse = adResponse;
    }

    /* access modifiers changed from: package-private */
    public void reportBeforeLoad(Context context) {
        if (context != null) {
            String beforeLoadUrl = this.mAdResponse.getBeforeLoadUrl();
            if (!TextUtils.isEmpty(beforeLoadUrl)) {
                this.mBeforeLoadTime = Long.valueOf(SystemClock.uptimeMillis());
                TrackingRequest.makeTrackingHttpRequest(beforeLoadUrl, context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void reportAfterLoad(Context context, MoPubError moPubError) {
        if (context != null && this.mBeforeLoadTime != null) {
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) generateAfterLoadUrls(this.mAdResponse.getAfterLoadUrls(), errorCodeToDownloadResult(moPubError).value), context);
        }
    }

    /* access modifiers changed from: package-private */
    public void reportAfterLoadSuccess(Context context) {
        if (context != null && this.mBeforeLoadTime != null) {
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) generateAfterLoadUrls(this.mAdResponse.getAfterLoadSuccessUrls(), DownloadResult.AD_LOADED.value), context);
        }
    }

    /* access modifiers changed from: package-private */
    public void reportAfterLoadFail(Context context, MoPubError moPubError) {
        if (context != null && this.mBeforeLoadTime != null) {
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) generateAfterLoadUrls(this.mAdResponse.getAfterLoadFailUrls(), errorCodeToDownloadResult(moPubError).value), context);
        }
    }

    private List<String> generateAfterLoadUrls(List<String> list, String str) {
        if (list == null || list.isEmpty() || this.mBeforeLoadTime == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String replace : list) {
            arrayList.add(replace.replace(LOAD_DURATION_MS_MACRO, String.valueOf(SystemClock.uptimeMillis() - this.mBeforeLoadTime.longValue())).replace(LOAD_RESULT_MACRO, Uri.encode(str)));
        }
        return arrayList;
    }

    private DownloadResult errorCodeToDownloadResult(MoPubError moPubError) {
        if (moPubError == null) {
            return DownloadResult.AD_LOADED;
        }
        int intCode = moPubError.getIntCode();
        if (intCode == 0) {
            return DownloadResult.AD_LOADED;
        }
        if (intCode == 1) {
            return DownloadResult.MISSING_ADAPTER;
        }
        if (intCode != 2) {
            return DownloadResult.INVALID_DATA;
        }
        return DownloadResult.TIMEOUT;
    }
}
