package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Views;
import java.util.Map;

public class FacebookBanner extends BaseAd implements AdListener {
    private static final String ADAPTER_NAME = FacebookBanner.class.getSimpleName();
    private static final String PLACEMENT_ID_KEY = "placement_id";
    private FacebookAdapterConfiguration mFacebookAdapterConfiguration = new FacebookAdapterConfiguration();
    private AdView mFacebookBanner;
    private String mPlacementId;

    /* access modifiers changed from: protected */
    public boolean checkAndInitializeSdk(Activity activity, AdData adData) {
        return false;
    }

    /* access modifiers changed from: protected */
    public LifecycleListener getLifecycleListener() {
        return null;
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
            AdView adView = new AdView(context, this.mPlacementId, calculateAdSize(adData.getAdHeight() == null ? 0 : adData.getAdHeight().intValue()));
            this.mFacebookBanner = adView;
            AdView.AdViewLoadConfigBuilder withAdListener = adView.buildLoadAdConfig().withAdListener(this);
            String str = (String) extras.get(DataKeys.ADM_KEY);
            if (!TextUtils.isEmpty(str)) {
                this.mFacebookBanner.loadAd(withAdListener.withBid(str).build());
                MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
                return;
            }
            this.mFacebookBanner.loadAd(withAdListener.build());
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
            return;
        }
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.NETWORK_NO_FILL.getIntCode()), MoPubErrorCode.NETWORK_NO_FILL);
        if (this.mLoadListener != null) {
            this.mLoadListener.onAdLoadFailed(MoPubErrorCode.NETWORK_NO_FILL);
        }
    }

    public View getAdView() {
        return this.mFacebookBanner;
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        AdView adView = this.mFacebookBanner;
        if (adView != null) {
            Views.removeFromParent(adView);
            this.mFacebookBanner.destroy();
            this.mFacebookBanner = null;
        }
    }

    public void onAdLoaded(Ad ad) {
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Facebook banner ad loaded successfully. Showing ad...");
        if (this.mLoadListener != null) {
            this.mLoadListener.onAdLoaded();
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_SUCCESS, ADAPTER_NAME);
        }
    }

    public void onError(Ad ad, AdError adError) {
        MoPubErrorCode moPubErrorCode;
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Facebook banner ad failed to load.");
        int errorCode = adError.getErrorCode();
        if (errorCode == 2100) {
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

    public void onAdClicked(Ad ad) {
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdClicked();
        }
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CLICKED, ADAPTER_NAME);
    }

    public void onLoggingImpression(Ad ad) {
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Facebook banner ad logged impression.");
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdImpression();
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        String str = map.get(PLACEMENT_ID_KEY);
        return str != null && str.length() > 0;
    }

    private AdSize calculateAdSize(int i) {
        if (i >= AdSize.RECTANGLE_HEIGHT_250.getHeight()) {
            return AdSize.RECTANGLE_HEIGHT_250;
        }
        if (i >= AdSize.BANNER_HEIGHT_90.getHeight()) {
            return AdSize.BANNER_HEIGHT_90;
        }
        return AdSize.BANNER_HEIGHT_50;
    }

    public String getAdNetworkId() {
        String str = this.mPlacementId;
        return str == null ? "" : str;
    }
}
