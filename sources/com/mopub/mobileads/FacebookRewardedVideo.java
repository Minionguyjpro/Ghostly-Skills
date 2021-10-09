package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdExtendedListener;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;

public class FacebookRewardedVideo extends BaseAd implements RewardedVideoAdExtendedListener {
    /* access modifiers changed from: private */
    public static final String ADAPTER_NAME = FacebookRewardedVideo.class.getSimpleName();
    private static final int ONE_HOURS_MILLIS = 3600000;
    private boolean closeCallbackFired;
    private Runnable mAdExpiration = new Runnable() {
        public void run() {
            MoPubLog.log(FacebookRewardedVideo.this.getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, FacebookRewardedVideo.ADAPTER_NAME, "Expiring unused Facebook Rewarded Video ad due to Facebook's 60-minute expiration policy.");
            if (FacebookRewardedVideo.this.mLoadListener != null) {
                FacebookRewardedVideo.this.mLoadListener.onAdLoadFailed(MoPubErrorCode.VIDEO_PLAYBACK_ERROR);
            }
            MoPubLog.log(FacebookRewardedVideo.this.getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_FAILED, FacebookRewardedVideo.ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.EXPIRED.getIntCode()), MoPubErrorCode.EXPIRED);
            FacebookRewardedVideo.this.onInvalidate();
        }
    };
    private FacebookAdapterConfiguration mFacebookAdapterConfiguration = new FacebookAdapterConfiguration();
    private Handler mHandler = new Handler();
    private String mPlacementId = "";
    private RewardedVideoAd mRewardedVideoAd;

    /* access modifiers changed from: protected */
    public LifecycleListener getLifecycleListener() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean checkAndInitializeSdk(Activity activity, AdData adData) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(adData);
        if (AudienceNetworkAds.isInitialized(activity)) {
            return true;
        }
        AudienceNetworkAds.initialize(activity);
        return true;
    }

    /* access modifiers changed from: protected */
    public void load(Context context, AdData adData) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(adData);
        setAutomaticImpressionAndClickTracking(false);
        Map extras = adData.getExtras();
        this.mPlacementId = (String) extras.get("placement_id");
        this.mFacebookAdapterConfiguration.setCachedInitializationParameters(context.getApplicationContext(), extras);
        if (!TextUtils.isEmpty(this.mPlacementId)) {
            RewardedVideoAd rewardedVideoAd = this.mRewardedVideoAd;
            if (rewardedVideoAd != null) {
                rewardedVideoAd.destroy();
                this.mRewardedVideoAd = null;
            }
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Creating a Facebook Rewarded Video instance, and registering callbacks.");
            RewardedVideoAd rewardedVideoAd2 = new RewardedVideoAd(context, this.mPlacementId);
            this.mRewardedVideoAd = rewardedVideoAd2;
            if (rewardedVideoAd2.isAdLoaded()) {
                if (this.mLoadListener != null) {
                    this.mLoadListener.onAdLoaded();
                }
                MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_SUCCESS, ADAPTER_NAME);
                return;
            }
            String str = (String) extras.get(DataKeys.ADM_KEY);
            RewardedVideoAd.RewardedVideoAdLoadConfigBuilder withAdListener = this.mRewardedVideoAd.buildLoadAdConfig().withAdListener(this);
            if (!TextUtils.isEmpty(str)) {
                this.mRewardedVideoAd.loadAd(withAdListener.withBid(str).build());
                MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
                return;
            }
            this.mRewardedVideoAd.loadAd(withAdListener.build());
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
            return;
        }
        if (this.mLoadListener != null) {
            this.mLoadListener.onAdLoadFailed(MoPubErrorCode.NETWORK_NO_FILL);
        }
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.NETWORK_NO_FILL.getIntCode()), MoPubErrorCode.NETWORK_NO_FILL);
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Placement ID is null or empty.");
    }

    /* access modifiers changed from: protected */
    public String getAdNetworkId() {
        return this.mPlacementId;
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        cancelExpirationTimer();
        if (this.mRewardedVideoAd != null) {
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CUSTOM, ADAPTER_NAME, "Performing cleanup tasks...");
            this.mRewardedVideoAd.destroy();
            this.mRewardedVideoAd = null;
        }
    }

    private boolean hasVideoAvailable() {
        RewardedVideoAd rewardedVideoAd = this.mRewardedVideoAd;
        return rewardedVideoAd != null && rewardedVideoAd.isAdLoaded() && !this.mRewardedVideoAd.isAdInvalidated();
    }

    /* access modifiers changed from: protected */
    public void show() {
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.SHOW_ATTEMPTED, ADAPTER_NAME);
        if (this.mRewardedVideoAd == null || !hasVideoAvailable()) {
            if (this.mInteractionListener != null) {
                this.mInteractionListener.onAdFailed(MoPubErrorCode.VIDEO_PLAYBACK_ERROR);
            }
            MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.SHOW_FAILED, ADAPTER_NAME, Integer.valueOf(MoPubErrorCode.VIDEO_PLAYBACK_ERROR.getIntCode()), MoPubErrorCode.VIDEO_PLAYBACK_ERROR);
            return;
        }
        this.mRewardedVideoAd.show();
    }

    public void onRewardedVideoCompleted() {
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdComplete(MoPubReward.success("", 0));
        }
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.SHOULD_REWARD, ADAPTER_NAME, 0, "");
    }

    public void onLoggingImpression(Ad ad) {
        cancelExpirationTimer();
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdShown();
            this.mInteractionListener.onAdImpression();
        }
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.SHOW_SUCCESS, ADAPTER_NAME);
    }

    public void onRewardedVideoClosed() {
        this.closeCallbackFired = true;
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdDismissed();
        }
    }

    public void onAdLoaded(Ad ad) {
        cancelExpirationTimer();
        this.mHandler.postDelayed(this.mAdExpiration, 3600000);
        if (this.mLoadListener != null) {
            this.mLoadListener.onAdLoaded();
        }
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_SUCCESS, ADAPTER_NAME);
    }

    public void onAdClicked(Ad ad) {
        if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdClicked();
        }
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.CLICKED, ADAPTER_NAME);
    }

    public void onError(Ad ad, AdError adError) {
        cancelExpirationTimer();
        if (this.mInteractionListener == null && this.mLoadListener != null) {
            this.mLoadListener.onAdLoadFailed(mapErrorCode(adError.getErrorCode()));
        } else if (this.mInteractionListener != null) {
            this.mInteractionListener.onAdFailed(mapErrorCode(adError.getErrorCode()));
        }
        String adNetworkId = getAdNetworkId();
        MoPubLog.AdapterLogEvent adapterLogEvent = MoPubLog.AdapterLogEvent.CUSTOM;
        MoPubLog.log(adNetworkId, adapterLogEvent, ADAPTER_NAME, "Loading/Playing Facebook Rewarded Video creative encountered an error: " + mapErrorCode(adError.getErrorCode()).toString());
        MoPubLog.log(getAdNetworkId(), MoPubLog.AdapterLogEvent.LOAD_FAILED, ADAPTER_NAME, mapErrorCode(adError.getErrorCode()), mapErrorCode(adError.getErrorCode()).toString());
    }

    public void onRewardedVideoActivityDestroyed() {
        if (!this.closeCallbackFired && this.mInteractionListener != null) {
            this.mInteractionListener.onAdDismissed();
        }
    }

    private static MoPubErrorCode mapErrorCode(int i) {
        if (i == 2100) {
            return MoPubErrorCode.VIDEO_PLAYBACK_ERROR;
        }
        if (i == 3001) {
            return MoPubErrorCode.NETWORK_INVALID_STATE;
        }
        switch (i) {
            case 1000:
                return MoPubErrorCode.NO_CONNECTION;
            case 1001:
                return MoPubErrorCode.NETWORK_NO_FILL;
            case 1002:
                return MoPubErrorCode.CANCELLED;
            default:
                switch (i) {
                    case AdError.SERVER_ERROR_CODE /*2000*/:
                        return MoPubErrorCode.SERVER_ERROR;
                    case AdError.INTERNAL_ERROR_CODE /*2001*/:
                        return MoPubErrorCode.INTERNAL_ERROR;
                    case AdError.CACHE_ERROR_CODE /*2002*/:
                        return MoPubErrorCode.VIDEO_CACHE_ERROR;
                    default:
                        return MoPubErrorCode.UNSPECIFIED;
                }
        }
    }

    private void cancelExpirationTimer() {
        this.mHandler.removeCallbacks(this.mAdExpiration);
    }
}
