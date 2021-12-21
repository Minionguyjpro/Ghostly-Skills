package com.mopub.mobileads;

import android.app.Activity;
import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubRewardedAd;
import com.mopub.mraid.RewardedMraidInterstitial;
import java.util.Map;

public class MoPubRewardedPlayable extends MoPubRewardedAd {
    static final String MOPUB_REWARDED_PLAYABLE_ID = "mopub_rewarded_playable_id";
    private RewardedMraidInterstitial mRewardedMraidInterstitial = new RewardedMraidInterstitial();

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(Activity activity, Map<String, Object> map, Map<String, String> map2) throws Exception {
        super.loadWithSdkInitialized(activity, map, map2);
        RewardedMraidInterstitial rewardedMraidInterstitial = this.mRewardedMraidInterstitial;
        if (rewardedMraidInterstitial == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "mRewardedMraidInterstitial is null. Has this class been invalidated?");
            return;
        }
        rewardedMraidInterstitial.loadInterstitial(activity, new MoPubRewardedPlayableListener(), map, map2);
    }

    /* access modifiers changed from: protected */
    public String getAdNetworkId() {
        return this.mAdUnitId != null ? this.mAdUnitId : MOPUB_REWARDED_PLAYABLE_ID;
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        RewardedMraidInterstitial rewardedMraidInterstitial = this.mRewardedMraidInterstitial;
        if (rewardedMraidInterstitial != null) {
            rewardedMraidInterstitial.onInvalidate();
        }
        this.mRewardedMraidInterstitial = null;
        super.onInvalidate();
    }

    /* access modifiers changed from: protected */
    public void show() {
        if (!isReady() || this.mRewardedMraidInterstitial == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "MoPub rewarded playable not loaded. Unable to show playable.");
            return;
        }
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Showing MoPub rewarded playable.");
        this.mRewardedMraidInterstitial.showInterstitial();
    }

    private class MoPubRewardedPlayableListener extends MoPubRewardedAd.MoPubRewardedAdListener implements RewardedMraidInterstitial.RewardedMraidInterstitialListener {
        public MoPubRewardedPlayableListener() {
            super(MoPubRewardedPlayable.class);
        }

        public void onMraidComplete() {
            if (MoPubRewardedPlayable.this.getRewardedAdCurrencyName() == null) {
                MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "No rewarded video was loaded, so no reward is possible");
                return;
            }
            MoPubRewardedVideoManager.onRewardedVideoCompleted(this.mCustomEventClass, MoPubRewardedPlayable.this.getAdNetworkId(), MoPubReward.success(MoPubRewardedPlayable.this.getRewardedAdCurrencyName(), MoPubRewardedPlayable.this.getRewardedAdCurrencyAmount()));
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setRewardedMraidInterstitial(RewardedMraidInterstitial rewardedMraidInterstitial) {
        this.mRewardedMraidInterstitial = rewardedMraidInterstitial;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public RewardedMraidInterstitial getRewardedMraidInterstitial() {
        return this.mRewardedMraidInterstitial;
    }
}
