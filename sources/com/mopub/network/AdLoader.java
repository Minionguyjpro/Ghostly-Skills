package com.mopub.network;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubError;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MultiAdRequest;
import com.mopub.volley.Request;
import com.mopub.volley.Response;
import com.mopub.volley.VolleyError;
import java.lang.ref.WeakReference;

public class AdLoader {
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final MultiAdRequest.Listener mAdListener;
    private boolean mContentDownloaded;
    private final WeakReference<Context> mContext;
    private ContentDownloadAnalytics mDownloadTracker;
    /* access modifiers changed from: private */
    public volatile boolean mFailed;
    private Handler mHandler;
    protected AdResponse mLastDeliveredResponse = null;
    private MultiAdRequest mMultiAdRequest;
    protected MultiAdResponse mMultiAdResponse;
    private final Listener mOriginalListener;
    /* access modifiers changed from: private */
    public volatile boolean mRunning;

    public interface Listener extends Response.ErrorListener {
        void onSuccess(AdResponse adResponse);
    }

    public AdLoader(String str, AdFormat adFormat, String str2, Context context, Listener listener) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str));
        Preconditions.checkNotNull(adFormat);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(listener);
        this.mContext = new WeakReference<>(context);
        this.mOriginalListener = listener;
        this.mHandler = new Handler();
        this.mAdListener = new MultiAdRequest.Listener() {
            public void onErrorResponse(VolleyError volleyError) {
                MoPubLog.log(MoPubLog.AdLogEvent.RESPONSE_RECEIVED, volleyError.getMessage());
                boolean unused = AdLoader.this.mFailed = true;
                boolean unused2 = AdLoader.this.mRunning = false;
                AdLoader.this.deliverError(volleyError);
            }

            public void onSuccessResponse(MultiAdResponse multiAdResponse) {
                synchronized (AdLoader.this.lock) {
                    boolean unused = AdLoader.this.mRunning = false;
                    AdLoader.this.mMultiAdResponse = multiAdResponse;
                    if (AdLoader.this.mMultiAdResponse.hasNext()) {
                        AdLoader.this.deliverResponse(AdLoader.this.mMultiAdResponse.next());
                    }
                }
            }
        };
        this.mRunning = false;
        this.mFailed = false;
        this.mMultiAdRequest = new MultiAdRequest(str, adFormat, str2, context, this.mAdListener);
    }

    public boolean hasMoreAds() {
        if (this.mFailed || this.mContentDownloaded) {
            return false;
        }
        MultiAdResponse multiAdResponse = this.mMultiAdResponse;
        if (multiAdResponse == null || multiAdResponse.hasNext() || !multiAdResponse.isWaterfallFinished()) {
            return true;
        }
        return false;
    }

    public Request<?> loadNextAd(MoPubError moPubError) {
        if (this.mRunning) {
            return this.mMultiAdRequest;
        }
        if (this.mFailed) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    AdLoader.this.deliverError(new MoPubNetworkError(MoPubNetworkError.Reason.UNSPECIFIED));
                }
            });
            return null;
        }
        synchronized (this.lock) {
            if (this.mMultiAdResponse != null) {
                if (moPubError != null) {
                    creativeDownloadFailed(moPubError);
                }
                if (this.mMultiAdResponse.hasNext()) {
                    final AdResponse next = this.mMultiAdResponse.next();
                    this.mHandler.post(new Runnable() {
                        public void run() {
                            AdLoader.this.deliverResponse(next);
                        }
                    });
                    MultiAdRequest multiAdRequest = this.mMultiAdRequest;
                    return multiAdRequest;
                } else if (!this.mMultiAdResponse.isWaterfallFinished()) {
                    MultiAdRequest multiAdRequest2 = new MultiAdRequest(this.mMultiAdResponse.getFailURL(), this.mMultiAdRequest.mAdFormat, this.mMultiAdRequest.mAdUnitId, (Context) this.mContext.get(), this.mAdListener);
                    this.mMultiAdRequest = multiAdRequest2;
                    Request<?> fetchAd = fetchAd(multiAdRequest2, (Context) this.mContext.get());
                    return fetchAd;
                } else {
                    this.mHandler.post(new Runnable() {
                        public void run() {
                            AdLoader.this.deliverError(new MoPubNetworkError(MoPubNetworkError.Reason.NO_FILL));
                        }
                    });
                    return null;
                }
            } else if (RequestRateTracker.getInstance().isBlockedByRateLimit(this.mMultiAdRequest.mAdUnitId)) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, this.mMultiAdRequest.mAdUnitId + " is blocked by request rate limiting.");
                this.mFailed = true;
                this.mHandler.post(new Runnable() {
                    public void run() {
                        AdLoader.this.deliverError(new MoPubNetworkError(MoPubNetworkError.Reason.NO_FILL));
                    }
                });
                return null;
            } else {
                Request<?> fetchAd2 = fetchAd(this.mMultiAdRequest, (Context) this.mContext.get());
                return fetchAd2;
            }
        }
    }

    public void creativeDownloadSuccess() {
        this.mContentDownloaded = true;
        if (this.mDownloadTracker == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Response analytics should not be null here");
            return;
        }
        Context context = (Context) this.mContext.get();
        if (context == null || this.mLastDeliveredResponse == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Cannot send 'x-after-load-url' analytics.");
            return;
        }
        this.mDownloadTracker.reportAfterLoad(context, (MoPubError) null);
        this.mDownloadTracker.reportAfterLoadSuccess(context);
    }

    private void creativeDownloadFailed(MoPubError moPubError) {
        if (moPubError == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Must provide error code to report creative download error");
            return;
        }
        Context context = (Context) this.mContext.get();
        if (context == null || this.mLastDeliveredResponse == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Cannot send creative mFailed analytics.");
            return;
        }
        ContentDownloadAnalytics contentDownloadAnalytics = this.mDownloadTracker;
        if (contentDownloadAnalytics != null) {
            contentDownloadAnalytics.reportAfterLoad(context, moPubError);
            this.mDownloadTracker.reportAfterLoadFail(context, moPubError);
        }
    }

    private Request<?> fetchAd(MultiAdRequest multiAdRequest, Context context) {
        Preconditions.checkNotNull(multiAdRequest);
        if (context == null) {
            return null;
        }
        MoPubLog.log(MoPubLog.AdLogEvent.REQUESTED, multiAdRequest.getUrl(), multiAdRequest.getBody() != null ? new String(multiAdRequest.getBody()) : "<no body>");
        this.mRunning = true;
        MoPubRequestQueue requestQueue = Networking.getRequestQueue(context);
        this.mMultiAdRequest = multiAdRequest;
        requestQueue.add(multiAdRequest);
        return multiAdRequest;
    }

    /* access modifiers changed from: private */
    public void deliverError(VolleyError volleyError) {
        Preconditions.checkNotNull(volleyError);
        this.mLastDeliveredResponse = null;
        Listener listener = this.mOriginalListener;
        if (listener == null) {
            return;
        }
        if (volleyError instanceof MoPubNetworkError) {
            listener.onErrorResponse(volleyError);
        } else {
            listener.onErrorResponse(new MoPubNetworkError(volleyError.getMessage(), volleyError.getCause(), MoPubNetworkError.Reason.UNSPECIFIED));
        }
    }

    /* access modifiers changed from: private */
    public void deliverResponse(AdResponse adResponse) {
        Preconditions.checkNotNull(adResponse);
        ContentDownloadAnalytics contentDownloadAnalytics = new ContentDownloadAnalytics(adResponse);
        this.mDownloadTracker = contentDownloadAnalytics;
        contentDownloadAnalytics.reportBeforeLoad((Context) this.mContext.get());
        Listener listener = this.mOriginalListener;
        if (listener != null) {
            this.mLastDeliveredResponse = adResponse;
            listener.onSuccess(adResponse);
        }
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    public boolean isFailed() {
        return this.mFailed;
    }
}
