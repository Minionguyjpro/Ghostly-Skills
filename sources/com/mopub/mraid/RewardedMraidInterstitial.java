package com.mopub.mraid;

import android.content.Context;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.RewardedMraidActivity;
import java.util.Map;

public class RewardedMraidInterstitial extends MraidInterstitial {
    public static final String ADAPTER_NAME = RewardedMraidInterstitial.class.getSimpleName();
    private int mRewardedDuration;
    private RewardedPlayableBroadcastReceiver mRewardedPlayableBroadcastReceiver;
    private boolean mShouldRewardOnClick;

    public interface RewardedMraidInterstitialListener extends CustomEventInterstitial.CustomEventInterstitialListener {
        void onMraidComplete();
    }

    public void loadInterstitial(Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        super.loadInterstitial(context, customEventInterstitialListener, map, map2);
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
        if (customEventInterstitialListener instanceof RewardedMraidInterstitialListener) {
            RewardedPlayableBroadcastReceiver rewardedPlayableBroadcastReceiver = new RewardedPlayableBroadcastReceiver((RewardedMraidInterstitialListener) customEventInterstitialListener, this.mBroadcastIdentifier);
            this.mRewardedPlayableBroadcastReceiver = rewardedPlayableBroadcastReceiver;
            rewardedPlayableBroadcastReceiver.register(rewardedPlayableBroadcastReceiver, context);
        }
    }

    /* access modifiers changed from: protected */
    public void preRenderHtml(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener) {
        Map map = this.mLocalExtras;
        if (map != null) {
            Object obj = map.get(DataKeys.REWARDED_AD_DURATION_KEY);
            this.mRewardedDuration = obj instanceof Integer ? ((Integer) obj).intValue() : 30;
            Object obj2 = map.get(DataKeys.SHOULD_REWARD_ON_CLICK_KEY);
            this.mShouldRewardOnClick = obj2 instanceof Boolean ? ((Boolean) obj2).booleanValue() : false;
        }
        RewardedMraidActivity.preRenderHtml(this, this.mContext, customEventInterstitialListener, Long.valueOf(this.mBroadcastIdentifier), this.mAdReport, this.mRewardedDuration);
    }

    public void showInterstitial() {
        MoPubLog.log(MoPubLog.AdapterLogEvent.SHOW_ATTEMPTED, ADAPTER_NAME);
        RewardedMraidActivity.start(this.mContext, this.mAdReport, this.mBroadcastIdentifier, this.mRewardedDuration, this.mShouldRewardOnClick);
    }

    public void onInvalidate() {
        super.onInvalidate();
        RewardedPlayableBroadcastReceiver rewardedPlayableBroadcastReceiver = this.mRewardedPlayableBroadcastReceiver;
        if (rewardedPlayableBroadcastReceiver != null) {
            rewardedPlayableBroadcastReceiver.unregister(rewardedPlayableBroadcastReceiver);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public int getRewardedDuration() {
        return this.mRewardedDuration;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean isShouldRewardOnClick() {
        return this.mShouldRewardOnClick;
    }
}
