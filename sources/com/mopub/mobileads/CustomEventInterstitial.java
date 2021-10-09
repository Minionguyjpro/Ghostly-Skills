package com.mopub.mobileads;

import android.content.Context;
import java.util.Map;

public abstract class CustomEventInterstitial implements Interstitial {
    private boolean mAutomaticImpressionAndClickTracking = true;

    public interface CustomEventInterstitialListener {
        void onInterstitialClicked();

        void onInterstitialDismissed();

        void onInterstitialFailed(MoPubErrorCode moPubErrorCode);

        void onInterstitialImpression();

        void onInterstitialLoaded();

        void onInterstitialShown();

        void onLeaveApplication();
    }

    /* access modifiers changed from: protected */
    public abstract void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2);

    /* access modifiers changed from: protected */
    public abstract void onInvalidate();

    /* access modifiers changed from: protected */
    public abstract void showInterstitial();

    /* access modifiers changed from: protected */
    public void setAutomaticImpressionAndClickTracking(boolean z) {
        this.mAutomaticImpressionAndClickTracking = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isAutomaticImpressionAndClickTrackingEnabled() {
        return this.mAutomaticImpressionAndClickTracking;
    }
}
