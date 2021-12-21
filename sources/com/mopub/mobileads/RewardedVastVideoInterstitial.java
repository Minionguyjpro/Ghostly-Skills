package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial;
import java.util.Map;

class RewardedVastVideoInterstitial extends VastVideoInterstitial {
    public static final String ADAPTER_NAME = RewardedVastVideoInterstitial.class.getSimpleName();
    private RewardedVideoBroadcastReceiver mRewardedVideoBroadcastReceiver;

    interface RewardedVideoInterstitialListener extends CustomEventInterstitial.CustomEventInterstitialListener {
        void onVideoComplete();
    }

    RewardedVastVideoInterstitial() {
    }

    public void loadInterstitial(Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
        super.loadInterstitial(context, customEventInterstitialListener, map, map2);
        if (customEventInterstitialListener instanceof RewardedVideoInterstitialListener) {
            RewardedVideoBroadcastReceiver rewardedVideoBroadcastReceiver = new RewardedVideoBroadcastReceiver((RewardedVideoInterstitialListener) customEventInterstitialListener, this.mBroadcastIdentifier);
            this.mRewardedVideoBroadcastReceiver = rewardedVideoBroadcastReceiver;
            rewardedVideoBroadcastReceiver.register(rewardedVideoBroadcastReceiver, context);
        }
    }

    public void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig) {
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_SUCCESS, ADAPTER_NAME);
        if (vastVideoConfig != null) {
            vastVideoConfig.setIsRewardedVideo(true);
        }
        super.onVastVideoConfigurationPrepared(vastVideoConfig);
    }

    public void onInvalidate() {
        super.onInvalidate();
        RewardedVideoBroadcastReceiver rewardedVideoBroadcastReceiver = this.mRewardedVideoBroadcastReceiver;
        if (rewardedVideoBroadcastReceiver != null) {
            rewardedVideoBroadcastReceiver.unregister(rewardedVideoBroadcastReceiver);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public RewardedVideoBroadcastReceiver getRewardedVideoBroadcastReceiver() {
        return this.mRewardedVideoBroadcastReceiver;
    }
}
