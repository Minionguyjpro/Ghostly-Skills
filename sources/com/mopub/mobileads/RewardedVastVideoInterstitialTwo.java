package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

@Mockable
/* compiled from: RewardedVastVideoInterstitialTwo.kt */
public class RewardedVastVideoInterstitialTwo extends VastVideoInterstitialTwo {
    /* access modifiers changed from: private */
    public static final String ADAPTER_NAME;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private RewardedVideoBroadcastReceiverTwo rewardedVideoBroadcastReceiver;

    /* compiled from: RewardedVastVideoInterstitialTwo.kt */
    public interface RewardedVideoInterstitialListenerTwo extends CustomEventInterstitial.CustomEventInterstitialListener {
        void onVideoComplete();
    }

    public RewardedVideoBroadcastReceiverTwo getRewardedVideoBroadcastReceiver() {
        return this.rewardedVideoBroadcastReceiver;
    }

    public void setRewardedVideoBroadcastReceiver(RewardedVideoBroadcastReceiverTwo rewardedVideoBroadcastReceiverTwo) {
        this.rewardedVideoBroadcastReceiver = rewardedVideoBroadcastReceiverTwo;
    }

    public void loadInterstitial(Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Map<String, ? extends Object> map, Map<String, String> map2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(customEventInterstitialListener, "customEventInterstitialListener");
        Intrinsics.checkParameterIsNotNull(map, "localExtras");
        Intrinsics.checkParameterIsNotNull(map2, "serverExtras");
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
        super.loadInterstitial(context, customEventInterstitialListener, map, map2);
        if (customEventInterstitialListener instanceof RewardedVideoInterstitialListenerTwo) {
            RewardedVideoBroadcastReceiverTwo rewardedVideoBroadcastReceiverTwo = new RewardedVideoBroadcastReceiverTwo((RewardedVideoInterstitialListenerTwo) customEventInterstitialListener, this.mBroadcastIdentifier);
            rewardedVideoBroadcastReceiverTwo.register(rewardedVideoBroadcastReceiverTwo, context);
            setRewardedVideoBroadcastReceiver(rewardedVideoBroadcastReceiverTwo);
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
        RewardedVideoBroadcastReceiverTwo rewardedVideoBroadcastReceiver2 = getRewardedVideoBroadcastReceiver();
        if (rewardedVideoBroadcastReceiver2 != null) {
            rewardedVideoBroadcastReceiver2.unregister(getRewardedVideoBroadcastReceiver());
        }
    }

    /* compiled from: RewardedVastVideoInterstitialTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getADAPTER_NAME() {
            return RewardedVastVideoInterstitialTwo.ADAPTER_NAME;
        }
    }

    static {
        String simpleName = RewardedVastVideoInterstitialTwo.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "RewardedVastVideoInterst…wo::class.java.simpleName");
        ADAPTER_NAME = simpleName;
    }
}
