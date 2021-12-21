package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.mopub.common.Constants;
import com.mopub.common.IntentActions;
import com.mopub.mobileads.RewardedVastVideoInterstitialTwo;
import kotlin.jvm.internal.Intrinsics;

@Mockable
/* compiled from: RewardedVideoBroadcastReceiverTwo.kt */
public class RewardedVideoBroadcastReceiverTwo extends BaseBroadcastReceiver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final IntentFilter sIntentFilter = new IntentFilter(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
    private final RewardedVastVideoInterstitialTwo.RewardedVideoInterstitialListenerTwo rewardedVideoListener;

    public RewardedVideoBroadcastReceiverTwo(RewardedVastVideoInterstitialTwo.RewardedVideoInterstitialListenerTwo rewardedVideoInterstitialListenerTwo, long j) {
        super(j);
        this.rewardedVideoListener = rewardedVideoInterstitialListenerTwo;
    }

    public IntentFilter getIntentFilter() {
        return sIntentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, Constants.INTENT_SCHEME);
        if (this.rewardedVideoListener != null && shouldConsumeBroadcast(intent) && Intrinsics.areEqual(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE, intent.getAction())) {
            this.rewardedVideoListener.onVideoComplete();
            unregister(this);
        }
    }

    /* compiled from: RewardedVideoBroadcastReceiverTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
