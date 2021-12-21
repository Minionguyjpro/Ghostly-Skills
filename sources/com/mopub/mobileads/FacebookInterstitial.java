package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdExtendedListener;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;

public class FacebookInterstitial extends BaseAd implements InterstitialAdExtendedListener {
    /* access modifiers changed from: private */
    public static final String ADAPTER_NAME = FacebookInterstitial.class.getSimpleName();
    private static final int ONE_HOURS_MILLIS = 3600000;
    private static final String PLACEMENT_ID_KEY = "placement_id";
    private Runnable mAdExpiration = new Runnable() {
        public void run() {
            if (FacebookInterstitial.this.mLoadListener != null) {
                MoPubLog.log(FacebookInterstitial.this.getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, FacebookInterstitial.ADAPTER_NAME, "Expiring unused Facebook Interstitial ad due to Facebook's 60-minute expiration policy.");
                FacebookInterstitial.this.mLoadListener.onAdLoadFailed(MoPubErrorCode.FULLSCREEN_SHOW_ERROR);
                MoPubLog.log(FacebookInterstitial.this.getAdNetworkId(), MoPubLog.AdapterLogEvent.SHOW_FAILED, FacebookInterstitial.ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.EXPIRED.getIntCode()), MoPubErrorCode.EXPIRED);
                FacebookInterstitial.this.onInvalidate();
            }
        }
    };
    private FacebookAdapterConfiguration mFacebookAdapterConfiguration = new FacebookAdapterConfiguration();
    private InterstitialAd mFacebookInterstitial;
    private Handler mHandler = new Handler();
    private String mPlacementId;

    /* access modifiers changed from: protected */
    public boolean checkAndInitializeSdk(Activity activity, AdData adData) {
        return false;
    }

    /* access modifiers changed from: protected */
    public LifecycleListener getLifecycleListener() {
        return null;
    }

    public void onRewardedAdCompleted() {
    }

    public void onRewardedAdServerFailed() {
    }

    public void onRewardedAdServerSucceeded() {
    }

    /* access modifiers changed from: protected */
    public void load(Context context, AdData adData) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(adData);
        if (!AudienceNetworkAds.isInitialized(context)) {
            AudienceNetworkAds.initialize(context);
        }
        setAutomaticImpressionAndClickTracking(false);
        Map extras = adData.getExtras();
        if (extrasAreValid(extras)) {
            this.mPlacementId = (String) extras.get(PLACEMENT_ID_KEY);
            this.mFacebookAdapterConfiguration.setCachedInitializationParameters(context, extras);
            this.mFacebookInterstitial = new InterstitialAd(context, this.mPlacementId);
            String str = (String) extras.get(DataKeys.ADM_KEY);
            InterstitialAd.InterstitialAdLoadConfigBuilder withAdListener = this.mFacebookInterstitial.buildLoadAdConfig().withAdListener(this);
            if (!TextUtils.isEmpty(str)) {
                this.mFacebookInterstitial.loadAd(withAdListener.withBid(str).build());
                MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
                return;
            }
            this.mFacebookInterstitial.loadAd(withAdListener.build());
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
        } else if (this.mLoadListener != null) {
            this.mLoadListener.onAdLoadFailed(MoPubErrorCode.NETWORK_NO_FILL);
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.NETWORK_NO_FILL.getIntCode()), MoPubErrorCode.NETWORK_NO_FILL);
        }
    }

    /* access modifiers changed from: protected */
    public void show() {
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.SHOW_ATTEMPTED, ADAPTER_NAME);
        InterstitialAd interstitialAd = this.mFacebookInterstitial;
        if (interstitialAd == null || !interstitialAd.isAdLoaded() || this.mFacebookInterstitial.isAdInvalidated()) {
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.SHOW_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.NETWORK_NO_FILL.getIntCode()), MoPubErrorCode.NETWORK_NO_FILL);
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Tried to show a Facebook interstitial ad when it's not ready. Please try again.");
            if (this.mInteractionListener != null) {
                onError(this.mFacebookInterstitial, AdError.INTERNAL_ERROR);
                return;
            }
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Interstitial listener not instantiated. Please load interstitial again.");
            return;
        }
        this.mFacebookInterstitial.show();
        cancelExpirationTimer();
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        cancelExpirationTimer();
        InterstitialAd interstitialAd = this.mFacebookInterstitial;
        if (interstitialAd != null) {
            interstitialAd.destroy();
            this.mFacebookInterstitial = null;
        }
    }

    public void onAdLoaded(Ad ad) {
        cancelExpirationTimer();
        if (this.mLoadListener != null) {
            this.mLoadListener.onAdLoaded();
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_SUCCESS, ADAPTER_NAME);
        }
        this.mHandler.postDelayed(this.mAdExpiration, 3600000);
    }

    public void onError(Ad ad, AdError adError) {
        MoPubErrorCode moPubErrorCode;
        cancelExpirationTimer();
        int errorCode = adError.getErrorCode();
        if (errorCode == 2009) {
            moPubErrorCode = MoPubErrorCode.NETWORK_TIMEOUT;
        } else if (errorCode == 2100) {
            moPubErrorCode = MoPubErrorCode.VIDEO_PLAYBACK_ERROR;
        } else if (errorCode != 3001) {
            switch (errorCode) {
                case 1000:
                    moPubErrorCode = MoPubErrorCode.NO_CONNECTION;
                    break;
                case 1001:
                    moPubErrorCode = MoPubErrorCode.NETWORK_NO_FILL;
                    break;
                case 1002:
                    moPubErrorCode = MoPubErrorCode.CANCELLED;
                    break;
                default:
                    switch (errorCode) {
                        case AdError.SERVER_ERROR_CODE /*2000*/:
                            moPubErrorCode = MoPubErrorCode.SERVER_ERROR;
                            break;
                        case AdError.INTERNAL_ERROR_CODE /*2001*/:
                            moPubErrorCode = MoPubErrorCode.INTERNAL_ERROR;
                            break;
                        case AdError.CACHE_ERROR_CODE /*2002*/:
                            moPubErrorCode = MoPubErrorCode.VIDEO_CACHE_ERROR;
                            break;
                        default:
                            moPubErrorCode = MoPubErrorCode.UNSPECIFIED;
                            break;
                    }
            }
        } else {
            moPubErrorCode = MoPubErrorCode.NETWORK_INVALID_STATE;
        }
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(moPubErrorCode.getIntCode()), moPubErrorCode);
        if (this.mInteractionListener == null && this.mLoadListener != null) {
            this.mLoadListener.onAdLoadFailed(moPubErrorCode);
        } else if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdFailed(moPubErrorCode);
        }
    }

    public void onInterstitialDisplayed(Ad ad) {
        cancelExpirationTimer();
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.SHOW_SUCCESS, ADAPTER_NAME);
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdShown();
        }
    }

    public void onAdClicked(Ad ad) {
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CLICKED, ADAPTER_NAME);
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdClicked();
        }
    }

    public void onLoggingImpression(Ad ad) {
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Facebook interstitial ad logged impression.");
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdImpression();
        }
    }

    public void onInterstitialDismissed(Ad ad) {
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdDismissed();
        }
    }

    public void onInterstitialActivityDestroyed() {
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdDismissed();
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        String str = map.get(PLACEMENT_ID_KEY);
        return str != null && str.length() > 0;
    }

    private void cancelExpirationTimer() {
        this.mHandler.removeCallbacks(this.mAdExpiration);
    }

    public String getAdNetworkId() {
        String str = this.mPlacementId;
        return str == null ? "" : str;
    }
}
