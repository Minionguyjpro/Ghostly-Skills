package com.mopub.mobileads;

import android.app.Activity;
import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubRewardedAd;
import com.mopub.mobileads.RewardedVastVideoInterstitial;
import java.util.Map;

public class MoPubRewardedVideo extends MoPubRewardedAd {
    static final String MOPUB_REWARDED_VIDEO_ID = "mopub_rewarded_video_id";
    private RewardedVastVideoInterstitial mRewardedVastVideoInterstitial = new RewardedVastVideoInterstitial();

    /* access modifiers changed from: protected */
    public String getAdNetworkId() {
        return this.mAdUnitId != null ? this.mAdUnitId : MOPUB_REWARDED_VIDEO_ID;
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        RewardedVastVideoInterstitial rewardedVastVideoInterstitial = this.mRewardedVastVideoInterstitial;
        if (rewardedVastVideoInterstitial != null) {
            rewardedVastVideoInterstitial.onInvalidate();
        }
        this.mRewardedVastVideoInterstitial = null;
        super.onInvalidate();
    }

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception {
        super.loadWithSdkInitialized(activity, map, map2);
        RewardedVastVideoInterstitial rewardedVastVideoInterstitial = this.mRewardedVastVideoInterstitial;
        if (rewardedVastVideoInterstitial == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "mRewardedVastVideoInterstitial is null. Has this class been invalidated?");
            return;
        }
        rewardedVastVideoInterstitial.loadInterstitial(activity, new MoPubRewardedVideoListener(), map, map2);
    }

    /* access modifiers changed from: protected */
    public void show() {
        if (!isReady() || this.mRewardedVastVideoInterstitial == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Unable to show MoPub rewarded video");
            return;
        }
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Showing MoPub rewarded video.");
        this.mRewardedVastVideoInterstitial.showInterstitial();
    }

    private class MoPubRewardedVideoListener extends MoPubRewardedAd.MoPubRewardedAdListener implements RewardedVastVideoInterstitial.RewardedVideoInterstitialListener {
        public MoPubRewardedVideoListener() {
            super(MoPubRewardedVideo.class);
        }

        public void onVideoComplete() {
            if (MoPubRewardedVideo.this.getRewardedAdCurrencyName() == null) {
                MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "No rewarded video was loaded, so no reward is possible");
                return;
            }
            MoPubRewardedVideoManager.onRewardedVideoCompleted(this.mCustomEventClass, MoPubRewardedVideo.this.getAdNetworkId(), MoPubReward.success(MoPubRewardedVideo.this.getRewardedAdCurrencyName(), MoPubRewardedVideo.this.getRewardedAdCurrencyAmount()));
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setRewardedVastVideoInterstitial(RewardedVastVideoInterstitial rewardedVastVideoInterstitial) {
        this.mRewardedVastVideoInterstitial = rewardedVastVideoInterstitial;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public RewardedVastVideoInterstitial getRewardedVastVideoInterstitial() {
        return this.mRewardedVastVideoInterstitial;
    }
}
