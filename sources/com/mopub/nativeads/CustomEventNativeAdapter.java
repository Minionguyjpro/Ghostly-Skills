package com.mopub.nativeads;

import android.content.Context;
import android.os.Handler;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.nativeads.CustomEventNative;
import com.mopub.nativeads.factories.CustomEventNativeFactory;
import com.mopub.network.AdResponse;
import java.util.Map;

final class CustomEventNativeAdapter {
    private CustomEventNative customEventNative;
    /* access modifiers changed from: private */
    public volatile boolean mCompleted = false;
    /* access modifiers changed from: private */
    public CustomEventNative.CustomEventNativeListener mExternalListener;
    private final Handler mHandler = new Handler();
    private final Runnable mTimeout = new Runnable() {
        public void run() {
            if (!CustomEventNativeAdapter.this.mCompleted) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "CustomEventNativeAdapter() failed with code " + MoPubErrorCode.NETWORK_TIMEOUT.getIntCode() + " and message " + MoPubErrorCode.NETWORK_TIMEOUT);
                CustomEventNativeAdapter.this.stopLoading();
                CustomEventNativeAdapter.this.mExternalListener.onNativeAdFailed(NativeErrorCode.NETWORK_TIMEOUT);
            }
        }
    };

    CustomEventNativeAdapter(CustomEventNative.CustomEventNativeListener customEventNativeListener) {
        Preconditions.checkNotNull(customEventNativeListener);
        this.mExternalListener = customEventNativeListener;
    }

    public void loadNativeAd(Context context, Map<String, Object> map, AdResponse adResponse) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(map);
        Preconditions.checkNotNull(adResponse);
        String customEventClassName = adResponse.getCustomEventClassName();
        MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, adResponse.getDspCreativeId());
        try {
            this.customEventNative = CustomEventNativeFactory.create(customEventClassName);
            if (adResponse.hasJson()) {
                map.put(DataKeys.JSON_BODY_KEY, adResponse.getJsonBody());
            }
            map.put(DataKeys.CLICK_TRACKING_URL_KEY, adResponse.getClickTrackingUrl());
            try {
                this.customEventNative.loadNativeAd(context, createListener(), map, adResponse.getServerExtras());
                this.mHandler.postDelayed(this.mTimeout, (long) adResponse.getAdTimeoutMillis(30000).intValue());
            } catch (Exception unused) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "loadNativeAd() failed with code " + MoPubErrorCode.ADAPTER_NOT_FOUND.getIntCode() + " and message " + MoPubErrorCode.ADAPTER_NOT_FOUND);
                this.mExternalListener.onNativeAdFailed(NativeErrorCode.NATIVE_ADAPTER_NOT_FOUND);
            }
        } catch (Exception unused2) {
            MoPubLog.SdkLogEvent sdkLogEvent2 = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent2, "loadNativeAd() failed with code " + MoPubErrorCode.ADAPTER_NOT_FOUND.getIntCode() + " and message " + MoPubErrorCode.ADAPTER_NOT_FOUND);
            this.mExternalListener.onNativeAdFailed(NativeErrorCode.NATIVE_ADAPTER_NOT_FOUND);
        }
    }

    private CustomEventNative.CustomEventNativeListener createListener() {
        return new CustomEventNative.CustomEventNativeListener() {
            public void onNativeAdLoaded(BaseNativeAd baseNativeAd) {
                if (!CustomEventNativeAdapter.this.mCompleted) {
                    MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "onNativeAdLoaded");
                    CustomEventNativeAdapter.this.invalidate();
                    CustomEventNativeAdapter.this.mExternalListener.onNativeAdLoaded(baseNativeAd);
                }
            }

            public void onNativeAdFailed(NativeErrorCode nativeErrorCode) {
                if (!CustomEventNativeAdapter.this.mCompleted) {
                    MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                    MoPubLog.log(sdkLogEvent, "onNativeAdFailed with code " + nativeErrorCode.getIntCode() + " and message " + nativeErrorCode);
                    CustomEventNativeAdapter.this.invalidate();
                    CustomEventNativeAdapter.this.mExternalListener.onNativeAdFailed(nativeErrorCode);
                }
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void stopLoading() {
        try {
            if (this.customEventNative != null) {
                this.customEventNative.onInvalidate();
            }
        } catch (Exception e) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, e.toString());
        }
        invalidate();
    }

    /* access modifiers changed from: private */
    public synchronized void invalidate() {
        if (!this.mCompleted) {
            this.mCompleted = true;
            this.mHandler.removeCallbacks(this.mTimeout);
            this.customEventNative = null;
        }
    }
}
