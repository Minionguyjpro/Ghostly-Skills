package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.WebViewCacheService;
import com.mopub.mobileads.factories.CustomEventInterstitialFactory;
import java.util.Map;
import java.util.TreeMap;

public class CustomEventInterstitialAdapter implements CustomEventInterstitial.CustomEventInterstitialListener {
    public static final int DEFAULT_INTERSTITIAL_TIMEOUT_DELAY = 30000;
    private long mBroadcastIdentifier;
    private Context mContext;
    private CustomEventInterstitial mCustomEventInterstitial;
    private CustomEventInterstitialAdapterListener mCustomEventInterstitialAdapterListener;
    private final Handler mHandler = new Handler();
    private boolean mInvalidated;
    private Map<String, Object> mLocalExtras;
    private final MoPubInterstitial mMoPubInterstitial;
    private Map<String, String> mServerExtras;
    private final Runnable mTimeout;

    interface CustomEventInterstitialAdapterListener {
        void onCustomEventInterstitialClicked();

        void onCustomEventInterstitialDismissed();

        void onCustomEventInterstitialFailed(MoPubErrorCode moPubErrorCode);

        void onCustomEventInterstitialImpression();

        void onCustomEventInterstitialLoaded();

        void onCustomEventInterstitialShown();
    }

    public CustomEventInterstitialAdapter(MoPubInterstitial moPubInterstitial, String str, Map<String, String> map, long j, AdReport adReport) {
        Preconditions.checkNotNull(map);
        this.mMoPubInterstitial = moPubInterstitial;
        this.mBroadcastIdentifier = j;
        this.mContext = moPubInterstitial.getActivity();
        this.mTimeout = new Runnable() {
            public void run() {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "CustomEventInterstitialAdapter() failed with code " + MoPubErrorCode.NETWORK_TIMEOUT.getIntCode() + " and message " + MoPubErrorCode.NETWORK_TIMEOUT);
                CustomEventInterstitialAdapter.this.onInterstitialFailed(MoPubErrorCode.NETWORK_TIMEOUT);
                CustomEventInterstitialAdapter.this.invalidate();
            }
        };
        MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
        MoPubLog.log(sdkLogEvent, "Attempting to invoke custom event: " + str);
        try {
            this.mCustomEventInterstitial = CustomEventInterstitialFactory.create(str);
            this.mServerExtras = new TreeMap(map);
            this.mLocalExtras = this.mMoPubInterstitial.getLocalExtras();
            if (this.mMoPubInterstitial.getLocation() != null) {
                this.mLocalExtras.put("location", this.mMoPubInterstitial.getLocation());
            }
            this.mLocalExtras.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(j));
            this.mLocalExtras.put(DataKeys.AD_REPORT_KEY, adReport);
        } catch (Exception e) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM_WITH_THROWABLE, "CustomEventInterstitialFactory.create() failed with exception", e);
            this.mMoPubInterstitial.onCustomEventInterstitialFailed(MoPubErrorCode.ADAPTER_NOT_FOUND);
        }
    }

    /* access modifiers changed from: package-private */
    public void loadInterstitial() {
        if (!isInvalidated() && this.mCustomEventInterstitial != null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "loadInterstitial()");
            this.mHandler.postDelayed(this.mTimeout, (long) getTimeoutDelayMilliseconds());
            try {
                this.mCustomEventInterstitial.loadInterstitial(this.mContext, this, this.mLocalExtras, this.mServerExtras);
            } catch (Exception unused) {
                onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void showInterstitial() {
        if (!isInvalidated() && this.mCustomEventInterstitial != null) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "showInterstitial()");
            try {
                this.mCustomEventInterstitial.showInterstitial();
            } catch (Exception unused) {
                MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
                MoPubLog.log(sdkLogEvent, "showInterstitial() failed with code " + MoPubErrorCode.INTERNAL_ERROR.getIntCode() + " and message " + MoPubErrorCode.INTERNAL_ERROR);
                onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void invalidate() {
        CustomEventInterstitial customEventInterstitial = this.mCustomEventInterstitial;
        if (customEventInterstitial != null) {
            try {
                customEventInterstitial.onInvalidate();
            } catch (Exception e) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Invalidating a custom event interstitial threw an exception.", e);
            }
        }
        this.mCustomEventInterstitial = null;
        this.mContext = null;
        this.mServerExtras = null;
        this.mLocalExtras = null;
        this.mCustomEventInterstitialAdapterListener = null;
        WebViewCacheService.Config popWebViewConfig = WebViewCacheService.popWebViewConfig(Long.valueOf(this.mBroadcastIdentifier));
        if (popWebViewConfig != null) {
            popWebViewConfig.getWebView().destroy();
        }
        this.mInvalidated = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isInvalidated() {
        return this.mInvalidated;
    }

    /* access modifiers changed from: package-private */
    public void setAdapterListener(CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener) {
        this.mCustomEventInterstitialAdapterListener = customEventInterstitialAdapterListener;
    }

    /* access modifiers changed from: package-private */
    public boolean isAutomaticImpressionAndClickTrackingEnabled() {
        CustomEventInterstitial customEventInterstitial = this.mCustomEventInterstitial;
        if (customEventInterstitial == null) {
            return true;
        }
        return customEventInterstitial.isAutomaticImpressionAndClickTrackingEnabled();
    }

    private void cancelTimeout() {
        this.mHandler.removeCallbacks(this.mTimeout);
    }

    private int getTimeoutDelayMilliseconds() {
        MoPubInterstitial moPubInterstitial = this.mMoPubInterstitial;
        if (moPubInterstitial == null) {
            return 30000;
        }
        return moPubInterstitial.getAdTimeoutDelay(30000).intValue();
    }

    public void onInterstitialLoaded() {
        if (!isInvalidated()) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "onInterstitialLoaded()");
            cancelTimeout();
            CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener = this.mCustomEventInterstitialAdapterListener;
            if (customEventInterstitialAdapterListener != null) {
                customEventInterstitialAdapterListener.onCustomEventInterstitialLoaded();
            }
        }
    }

    public void onInterstitialFailed(MoPubErrorCode moPubErrorCode) {
        if (!isInvalidated()) {
            if (moPubErrorCode == null) {
                moPubErrorCode = MoPubErrorCode.UNSPECIFIED;
            }
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "onInterstitialFailed() failed with code " + moPubErrorCode.getIntCode() + " and message " + moPubErrorCode);
            if (this.mCustomEventInterstitialAdapterListener != null) {
                cancelTimeout();
                this.mCustomEventInterstitialAdapterListener.onCustomEventInterstitialFailed(moPubErrorCode);
            }
        }
    }

    public void onInterstitialShown() {
        if (!isInvalidated()) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "onInterstitialShown()");
            CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener = this.mCustomEventInterstitialAdapterListener;
            if (customEventInterstitialAdapterListener != null) {
                customEventInterstitialAdapterListener.onCustomEventInterstitialShown();
            }
        }
    }

    public void onInterstitialClicked() {
        CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener;
        if (!isInvalidated() && (customEventInterstitialAdapterListener = this.mCustomEventInterstitialAdapterListener) != null) {
            customEventInterstitialAdapterListener.onCustomEventInterstitialClicked();
        }
    }

    public void onInterstitialImpression() {
        CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener;
        if (!isInvalidated() && (customEventInterstitialAdapterListener = this.mCustomEventInterstitialAdapterListener) != null) {
            customEventInterstitialAdapterListener.onCustomEventInterstitialImpression();
        }
    }

    public void onLeaveApplication() {
        onInterstitialClicked();
    }

    public void onInterstitialDismissed() {
        CustomEventInterstitialAdapterListener customEventInterstitialAdapterListener;
        if (!isInvalidated() && (customEventInterstitialAdapterListener = this.mCustomEventInterstitialAdapterListener) != null) {
            customEventInterstitialAdapterListener.onCustomEventInterstitialDismissed();
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setCustomEventInterstitial(CustomEventInterstitial customEventInterstitial) {
        this.mCustomEventInterstitial = customEventInterstitial;
    }
}
