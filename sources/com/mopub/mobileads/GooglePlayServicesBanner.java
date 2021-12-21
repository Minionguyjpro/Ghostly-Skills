package com.mopub.mobileads;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Views;
import com.mopub.mobileads.CustomEventBanner;
import java.util.Map;

public class GooglePlayServicesBanner extends CustomEventBanner {
    public static final String AD_HEIGHT_KEY = "adHeight";
    public static final String AD_UNIT_ID_KEY = "adUnitID";
    public static final String AD_WIDTH_KEY = "adWidth";
    public static final String LOCATION_KEY = "location";
    /* access modifiers changed from: private */
    public CustomEventBanner.CustomEventBannerListener mBannerListener;
    /* access modifiers changed from: private */
    public AdView mGoogleAdView;

    /* access modifiers changed from: protected */
    public void loadBanner(Context context, CustomEventBanner.CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2) {
        this.mBannerListener = customEventBannerListener;
        if (extrasAreValid(map2)) {
            int parseInt = Integer.parseInt(map2.get("adWidth"));
            int parseInt2 = Integer.parseInt(map2.get("adHeight"));
            AdView adView = new AdView(context);
            this.mGoogleAdView = adView;
            adView.setAdListener(new AdViewListener());
            this.mGoogleAdView.setAdUnitId(map2.get("adUnitID"));
            AdSize calculateAdSize = calculateAdSize(parseInt, parseInt2);
            if (calculateAdSize == null) {
                this.mBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                return;
            }
            this.mGoogleAdView.setAdSize(calculateAdSize);
            try {
                this.mGoogleAdView.loadAd(new AdRequest.Builder().setRequestAgent(MoPubLog.LOGTAG).build());
            } catch (NoClassDefFoundError unused) {
                this.mBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_NO_FILL);
            }
        } else {
            this.mBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        Views.removeFromParent(this.mGoogleAdView);
        AdView adView = this.mGoogleAdView;
        if (adView != null) {
            adView.setAdListener((AdListener) null);
            this.mGoogleAdView.destroy();
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        try {
            Integer.parseInt(map.get("adWidth"));
            Integer.parseInt(map.get("adHeight"));
            return map.containsKey("adUnitID");
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private AdSize calculateAdSize(int i, int i2) {
        if (i <= AdSize.BANNER.getWidth() && i2 <= AdSize.BANNER.getHeight()) {
            return AdSize.BANNER;
        }
        if (i <= AdSize.MEDIUM_RECTANGLE.getWidth() && i2 <= AdSize.MEDIUM_RECTANGLE.getHeight()) {
            return AdSize.MEDIUM_RECTANGLE;
        }
        if (i <= AdSize.FULL_BANNER.getWidth() && i2 <= AdSize.FULL_BANNER.getHeight()) {
            return AdSize.FULL_BANNER;
        }
        if (i > AdSize.LEADERBOARD.getWidth() || i2 > AdSize.LEADERBOARD.getHeight()) {
            return null;
        }
        return AdSize.LEADERBOARD;
    }

    private class AdViewListener extends AdListener {
        public void onAdClosed() {
        }

        public void onAdLeftApplication() {
        }

        private AdViewListener() {
        }

        public void onAdFailedToLoad(int i) {
            Log.d(MoPubLog.LOGTAG, "Google Play Services banner ad failed to load.");
            if (GooglePlayServicesBanner.this.mBannerListener != null) {
                GooglePlayServicesBanner.this.mBannerListener.onBannerFailed(getMoPubErrorCode(i));
            }
        }

        public void onAdLoaded() {
            Log.d(MoPubLog.LOGTAG, "Google Play Services banner ad loaded successfully. Showing ad...");
            if (GooglePlayServicesBanner.this.mBannerListener != null) {
                GooglePlayServicesBanner.this.mBannerListener.onBannerLoaded(GooglePlayServicesBanner.this.mGoogleAdView);
            }
        }

        public void onAdOpened() {
            Log.d(MoPubLog.LOGTAG, "Google Play Services banner ad clicked.");
            if (GooglePlayServicesBanner.this.mBannerListener != null) {
                GooglePlayServicesBanner.this.mBannerListener.onBannerClicked();
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
    public AdView getGoogleAdView() {
        return this.mGoogleAdView;
    }
}
