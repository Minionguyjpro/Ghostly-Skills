package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.AdFormat;
import com.mopub.common.Preconditions;
import com.mopub.network.AdLoader;
import com.mopub.network.AdResponse;
import com.mopub.network.SingleImpression;
import com.mopub.network.TrackingRequest;
import java.util.Collections;
import java.util.List;

class AdLoaderRewardedVideo extends AdLoader {
    private boolean mClickTrackerFired = false;
    private boolean mImpressionTrackerFired = false;

    AdLoaderRewardedVideo(String str, AdFormat adFormat, String str2, Context context, AdLoader.Listener listener) {
        super(str, adFormat, str2, context, listener);
    }

    /* access modifiers changed from: package-private */
    public String getFailurl() {
        if (this.mMultiAdResponse != null) {
            return this.mMultiAdResponse.getFailURL();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public List<String> getImpressionUrls() {
        if (this.mLastDeliveredResponse != null) {
            return this.mLastDeliveredResponse.getImpressionTrackingUrls();
        }
        return Collections.emptyList();
    }

    /* access modifiers changed from: package-private */
    public String getClickUrl() {
        if (this.mLastDeliveredResponse != null) {
            return this.mLastDeliveredResponse.getClickTrackingUrl();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public AdResponse getLastDeliveredResponse() {
        return this.mLastDeliveredResponse;
    }

    /* access modifiers changed from: package-private */
    public void trackImpression(Context context) {
        Preconditions.checkNotNull(context);
        if (this.mLastDeliveredResponse != null && !this.mImpressionTrackerFired) {
            this.mImpressionTrackerFired = true;
            TrackingRequest.makeTrackingHttpRequest((Iterable<String>) getImpressionUrls(), context);
            new SingleImpression(this.mLastDeliveredResponse.getAdUnitId(), this.mLastDeliveredResponse.getImpressionData()).sendImpression();
        }
    }

    /* access modifiers changed from: package-private */
    public void trackClick(Context context) {
        Preconditions.checkNotNull(context);
        if (this.mLastDeliveredResponse != null && !this.mClickTrackerFired) {
            this.mClickTrackerFired = true;
            TrackingRequest.makeTrackingHttpRequest(getClickUrl(), context);
        }
    }
}
