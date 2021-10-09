package com.mopub.nativeads;

import android.content.Context;
import android.os.Handler;
import com.mopub.common.Constants;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import com.mopub.nativeads.PositioningSource;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.Networking;
import com.mopub.volley.Response;
import com.mopub.volley.VolleyError;

class ServerPositioningSource implements PositioningSource {
    private static final double DEFAULT_RETRY_TIME_MILLISECONDS = 1000.0d;
    private static final double EXPONENTIAL_BACKOFF_FACTOR = 2.0d;
    private static final int MAXIMUM_RETRY_TIME_MILLISECONDS = 300000;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Response.ErrorListener mErrorListener;
    private PositioningSource.PositioningListener mListener;
    private int mMaximumRetryTimeMillis = MAXIMUM_RETRY_TIME_MILLISECONDS;
    private final Response.Listener<MoPubNativeAdPositioning.MoPubClientPositioning> mPositioningListener;
    private PositioningRequest mRequest;
    private int mRetryCount;
    private final Handler mRetryHandler;
    private final Runnable mRetryRunnable;
    private String mRetryUrl;

    ServerPositioningSource(Context context) {
        this.mContext = context.getApplicationContext();
        this.mRetryHandler = new Handler();
        this.mRetryRunnable = new Runnable() {
            public void run() {
                ServerPositioningSource.this.requestPositioningInternal();
            }
        };
        this.mPositioningListener = new Response.Listener<MoPubNativeAdPositioning.MoPubClientPositioning>() {
            public void onResponse(MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning) {
                ServerPositioningSource.this.handleSuccess(moPubClientPositioning);
            }
        };
        this.mErrorListener = new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                if (!(volleyError instanceof MoPubNetworkError) || ((MoPubNetworkError) volleyError).getReason().equals(MoPubNetworkError.Reason.WARMING_UP)) {
                    MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Failed to load positioning data", volleyError);
                    if (volleyError.networkResponse == null && !DeviceUtils.isNetworkAvailable(ServerPositioningSource.this.mContext)) {
                        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.valueOf(MoPubErrorCode.NO_CONNECTION));
                    }
                }
                ServerPositioningSource.this.handleFailure();
            }
        };
    }

    public void loadPositions(String str, PositioningSource.PositioningListener positioningListener) {
        PositioningRequest positioningRequest = this.mRequest;
        if (positioningRequest != null) {
            positioningRequest.cancel();
            this.mRequest = null;
        }
        if (this.mRetryCount > 0) {
            this.mRetryHandler.removeCallbacks(this.mRetryRunnable);
            this.mRetryCount = 0;
        }
        this.mListener = positioningListener;
        this.mRetryUrl = new PositioningUrlGenerator(this.mContext).withAdUnitId(str).generateUrlString(Constants.HOST);
        requestPositioningInternal();
    }

    /* access modifiers changed from: private */
    public void requestPositioningInternal() {
        MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
        MoPubLog.log(sdkLogEvent, "Loading positioning from: " + this.mRetryUrl);
        this.mRequest = new PositioningRequest(this.mContext, this.mRetryUrl, this.mPositioningListener, this.mErrorListener);
        Networking.getRequestQueue(this.mContext).add(this.mRequest);
    }

    /* access modifiers changed from: private */
    public void handleSuccess(MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning) {
        PositioningSource.PositioningListener positioningListener = this.mListener;
        if (positioningListener != null) {
            positioningListener.onLoad(moPubClientPositioning);
        }
        this.mListener = null;
        this.mRetryCount = 0;
    }

    /* access modifiers changed from: private */
    public void handleFailure() {
        int pow = (int) (Math.pow(EXPONENTIAL_BACKOFF_FACTOR, (double) (this.mRetryCount + 1)) * DEFAULT_RETRY_TIME_MILLISECONDS);
        if (pow >= this.mMaximumRetryTimeMillis) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Error downloading positioning information");
            PositioningSource.PositioningListener positioningListener = this.mListener;
            if (positioningListener != null) {
                positioningListener.onFailed();
            }
            this.mListener = null;
            return;
        }
        this.mRetryCount++;
        this.mRetryHandler.postDelayed(this.mRetryRunnable, (long) pow);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setMaximumRetryTimeMilliseconds(int i) {
        this.mMaximumRetryTimeMillis = i;
    }
}
