package com.mopub.mobileads;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial;
import java.util.Map;

public class GooglePlayServicesInterstitial extends CustomEventInterstitial {
    public static final String AD_UNIT_ID_KEY = "adUnitID";
    public static final String LOCATION_KEY = "location";
    private InterstitialAd mGoogleInterstitialAd;
    /* access modifiers changed from: private */
    public CustomEventInterstitial.CustomEventInterstitialListener mInterstitialListener;

    /* access modifiers changed from: protected */
    public void loadInterstitial(Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        this.mInterstitialListener = customEventInterstitialListener;
        if (extrasAreValid(map2)) {
            InterstitialAd interstitialAd = new InterstitialAd(context);
            this.mGoogleInterstitialAd = interstitialAd;
            interstitialAd.setAdListener(new InterstitialAdListener());
            this.mGoogleInterstitialAd.setAdUnitId(map2.get("adUnitID"));
            try {
                this.mGoogleInterstitialAd.loadAd(new AdRequest.Builder().setRequestAgent(MoPubLog.LOGTAG).build());
            } catch (NoClassDefFoundError unused) {
                this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
            }
        } else {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public void showInterstitial() {
        if (this.mGoogleInterstitialAd.isLoaded()) {
            this.mGoogleInterstitialAd.show();
        } else {
            Log.d(MoPubLog.LOGTAG, "Tried to show a Google Play Services interstitial ad before it finished loading. Please try again.");
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        InterstitialAd interstitialAd = this.mGoogleInterstitialAd;
        if (interstitialAd != null) {
            interstitialAd.setAdListener((AdListener) null);
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        return map.containsKey("adUnitID");
    }

    private class InterstitialAdListener extends AdListener {
        private InterstitialAdListener() {
        }

        public void onAdClosed() {
            Log.d(MoPubLog.LOGTAG, "Google Play Services interstitial ad dismissed.");
            if (GooglePlayServicesInterstitial.this.mInterstitialListener != null) {
                GooglePlayServicesInterstitial.this.mInterstitialListener.onInterstitialDismissed();
            }
        }

        public void onAdFailedToLoad(int i) {
            Log.d(MoPubLog.LOGTAG, "Google Play Services interstitial ad failed to load.");
            if (GooglePlayServicesInterstitial.this.mInterstitialListener != null) {
                GooglePlayServicesInterstitial.this.mInterstitialListener.onInterstitialFailed(getMoPubErrorCode(i));
            }
        }

        public void onAdLeftApplication() {
            Log.d(MoPubLog.LOGTAG, "Google Play Services interstitial ad clicked.");
            if (GooglePlayServicesInterstitial.this.mInterstitialListener != null) {
                GooglePlayServicesInterstitial.this.mInterstitialListener.onInterstitialClicked();
            }
        }

        public void onAdLoaded() {
            Log.d(MoPubLog.LOGTAG, "Google Play Services interstitial ad loaded successfully.");
            if (GooglePlayServicesInterstitial.this.mInterstitialListener != null) {
                GooglePlayServicesInterstitial.this.mInterstitialListener.onInterstitialLoaded();
            }
        }

        public void onAdOpened() {
            Log.d(MoPubLog.LOGTAG, "Showing Google Play Services interstitial ad.");
            if (GooglePlayServicesInterstitial.this.mInterstitialListener != null) {
                GooglePlayServicesInterstitial.this.mInterstitialListener.onInterstitialShown();
            }
        }

        private MoPubErrorCode getMoPubErrorCode(int i) {
            if (i == 0) {
                return MoPubErrorCode.INTERNAL_ERROR;
            }
            if (i == 1) {
                return MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR;
            }
            if (i == 2) {
                return MoPubErrorCode.NO_CONNECTION;
            }
            if (i != 3) {
                return MoPubErrorCode.UNSPECIFIED;
            }
            return MoPubErrorCode.NO_FILL;
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public InterstitialAd getGoogleInterstitialAd() {
        return this.mGoogleInterstitialAd;
    }
}
