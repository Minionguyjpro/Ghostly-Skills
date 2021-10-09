package com.mopub.mobileads;

import android.app.Activity;
import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubRewardedAd;
import com.mopub.mobileads.RewardedVastVideoInterstitialTwo;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MoPubRewardedVideoTwo.kt */
public final class MoPubRewardedVideoTwo extends MoPubRewardedAd {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String MOPUB_REWARDED_VIDEO_TWO_ID = "mopub_rewarded_video_two_id";
    private RewardedVastVideoInterstitialTwo rewardedVastVideoInterstitial = new RewardedVastVideoInterstitialTwo();

    public final RewardedVastVideoInterstitialTwo getRewardedVastVideoInterstitial() {
        return this.rewardedVastVideoInterstitial;
    }

    public final void setRewardedVastVideoInterstitial(RewardedVastVideoInterstitialTwo rewardedVastVideoInterstitialTwo) {
        this.rewardedVastVideoInterstitial = rewardedVastVideoInterstitialTwo;
    }

    /* access modifiers changed from: protected */
    public String getAdNetworkId() {
        String str = this.mAdUnitId;
        return str != null ? str : MOPUB_REWARDED_VIDEO_TWO_ID;
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        RewardedVastVideoInterstitialTwo rewardedVastVideoInterstitialTwo = this.rewardedVastVideoInterstitial;
        if (rewardedVastVideoInterstitialTwo != null) {
            rewardedVastVideoInterstitialTwo.onInvalidate();
        }
        this.rewardedVastVideoInterstitial = null;
        super.onInvalidate();
    }

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(Activity activity, Map<String, ? extends Object> map, Map<String, String> map2) throws Exception {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(map, "localExtras");
        Intrinsics.checkParameterIsNotNull(map2, "serverExtras");
        super.loadWithSdkInitialized(activity, map, map2);
        RewardedVastVideoInterstitialTwo rewardedVastVideoInterstitialTwo = this.rewardedVastVideoInterstitial;
        if (rewardedVastVideoInterstitialTwo != null) {
            rewardedVastVideoInterstitialTwo.loadInterstitial(activity, new MoPubRewardedVideoTwoListener(), map, map2);
            return;
        }
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "rewardedVastVideoInterstitialTwo is null. Has this class been invalidated?");
    }

    /* access modifiers changed from: protected */
    public void show() {
        if (!isReady() || this.rewardedVastVideoInterstitial == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Unable to show MoPub rewarded video");
            return;
        }
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Showing MoPub rewarded video.");
        RewardedVastVideoInterstitialTwo rewardedVastVideoInterstitialTwo = this.rewardedVastVideoInterstitial;
        if (rewardedVastVideoInterstitialTwo != null) {
            rewardedVastVideoInterstitialTwo.showInterstitial();
        }
    }

    /* compiled from: MoPubRewardedVideoTwo.kt */
    private final class MoPubRewardedVideoTwoListener extends MoPubRewardedAd.MoPubRewardedAdListener implements RewardedVastVideoInterstitialTwo.RewardedVideoInterstitialListenerTwo {
        public MoPubRewardedVideoTwoListener() {
            super(MoPubRewardedVideoTwo.class);
        }

        public void onVideoComplete() {
            String rewardedAdCurrencyName = MoPubRewardedVideoTwo.this.getRewardedAdCurrencyName();
            if (rewardedAdCurrencyName != null) {
                MoPubRewardedVideoManager.onRewardedVideoCompleted(this.mCustomEventClass, MoPubRewardedVideoTwo.this.getAdNetworkId(), MoPubReward.success(rewardedAdCurrencyName, MoPubRewardedVideoTwo.this.getRewardedAdCurrencyAmount()));
                return;
            }
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "No rewarded video was loaded, so no reward is possible");
        }
    }

    /* compiled from: MoPubRewardedVideoTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
