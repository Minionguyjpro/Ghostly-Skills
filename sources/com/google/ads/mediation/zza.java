package com.google.ads.mediation;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

final class zza implements RewardedVideoAdListener {
    private final /* synthetic */ AbstractAdViewAdapter zzhd;

    zza(AbstractAdViewAdapter abstractAdViewAdapter) {
        this.zzhd = abstractAdViewAdapter;
    }

    public final void onRewarded(RewardItem rewardItem) {
        this.zzhd.zzhb.onRewarded(this.zzhd, rewardItem);
    }

    public final void onRewardedVideoAdClosed() {
        this.zzhd.zzhb.onAdClosed(this.zzhd);
        InterstitialAd unused = this.zzhd.zzha = null;
    }

    public final void onRewardedVideoAdFailedToLoad(int i) {
        this.zzhd.zzhb.onAdFailedToLoad(this.zzhd, i);
    }

    public final void onRewardedVideoAdLeftApplication() {
        this.zzhd.zzhb.onAdLeftApplication(this.zzhd);
    }

    public final void onRewardedVideoAdLoaded() {
        this.zzhd.zzhb.onAdLoaded(this.zzhd);
    }

    public final void onRewardedVideoAdOpened() {
        this.zzhd.zzhb.onAdOpened(this.zzhd);
    }

    public final void onRewardedVideoCompleted() {
        this.zzhd.zzhb.onVideoCompleted(this.zzhd);
    }

    public final void onRewardedVideoStarted() {
        this.zzhd.zzhb.onVideoStarted(this.zzhd);
    }
}
