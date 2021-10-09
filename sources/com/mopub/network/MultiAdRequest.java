package com.mopub.network;

import android.content.Context;
import com.mopub.common.AdFormat;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.network.MoPubNetworkError;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Response;
import com.mopub.volley.toolbox.HttpHeaderParser;

public class MultiAdRequest extends MoPubRequest<MultiAdResponse> {
    private int hashCode = 0;
    final AdFormat mAdFormat;
    final String mAdUnitId;
    private final Context mContext;
    public final Listener mListener;

    public interface Listener extends Response.ErrorListener {
        void onSuccessResponse(MultiAdResponse multiAdResponse);
    }

    MultiAdRequest(String str, AdFormat adFormat, String str2, Context context, Listener listener) {
        super(context, clearUrlIfSdkNotInitialized(str), listener);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(adFormat);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(listener);
        this.mAdUnitId = str2;
        this.mListener = listener;
        this.mAdFormat = adFormat;
        this.mContext = context.getApplicationContext();
        setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 1, 1.0f));
        setShouldCache(false);
        PersonalInfoManager personalInformationManager = MoPub.getPersonalInformationManager();
        if (personalInformationManager != null) {
            personalInformationManager.requestSync(false);
        }
    }

    private static String clearUrlIfSdkNotInitialized(String str) {
        if (MoPub.getPersonalInformationManager() != null && MoPub.isSdkInitialized()) {
            return str;
        }
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Make sure to call MoPub#initializeSdk before loading an ad.");
        return "";
    }

    /* access modifiers changed from: protected */
    public Response<MultiAdResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return Response.success(new MultiAdResponse(this.mContext, networkResponse, this.mAdFormat, this.mAdUnitId), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (Exception e) {
            if (e instanceof MoPubNetworkError) {
                return Response.error((MoPubNetworkError) e);
            }
            return Response.error(new MoPubNetworkError((Throwable) e, MoPubNetworkError.Reason.UNSPECIFIED));
        }
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(MultiAdResponse multiAdResponse) {
        if (!isCanceled()) {
            this.mListener.onSuccessResponse(multiAdResponse);
        }
    }

    public void cancel() {
        super.cancel();
    }

    public boolean equals(Object obj) {
        int i;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultiAdRequest)) {
            return false;
        }
        MultiAdRequest multiAdRequest = (MultiAdRequest) obj;
        String str = this.mAdUnitId;
        if (str != null) {
            String str2 = multiAdRequest.mAdUnitId;
            i = str2 == null ? 1 : str.compareTo(str2);
        } else {
            i = multiAdRequest.mAdUnitId != null ? -1 : 0;
        }
        if (i == 0 && this.mAdFormat == multiAdRequest.mAdFormat && getUrl().compareTo(multiAdRequest.getUrl()) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.mAdUnitId;
            this.hashCode = ((((str == null ? 29 : str.hashCode()) * 31) + this.mAdFormat.hashCode()) * 31) + getOriginalUrl().hashCode();
        }
        return this.hashCode;
    }
}
