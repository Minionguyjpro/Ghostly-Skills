package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.mopub.common.IntentActions;
import com.mopub.mobileads.CustomEventInterstitial;

public class EventForwardingBroadcastReceiver extends BaseBroadcastReceiver {
    private static IntentFilter sIntentFilter;
    private final CustomEventInterstitial.CustomEventInterstitialListener mCustomEventInterstitialListener;

    public EventForwardingBroadcastReceiver(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, long j) {
        super(j);
        this.mCustomEventInterstitialListener = customEventInterstitialListener;
        getIntentFilter();
    }

    public IntentFilter getIntentFilter() {
        if (sIntentFilter == null) {
            IntentFilter intentFilter = new IntentFilter();
            sIntentFilter = intentFilter;
            intentFilter.addAction(IntentActions.ACTION_INTERSTITIAL_FAIL);
            sIntentFilter.addAction(IntentActions.ACTION_INTERSTITIAL_SHOW);
            sIntentFilter.addAction(IntentActions.ACTION_INTERSTITIAL_DISMISS);
            sIntentFilter.addAction(IntentActions.ACTION_INTERSTITIAL_CLICK);
        }
        return sIntentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.mCustomEventInterstitialListener != null && shouldConsumeBroadcast(intent)) {
            String action = intent.getAction();
            if (IntentActions.ACTION_INTERSTITIAL_FAIL.equals(action)) {
                this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
            } else if (IntentActions.ACTION_INTERSTITIAL_SHOW.equals(action)) {
                this.mCustomEventInterstitialListener.onInterstitialShown();
            } else if (IntentActions.ACTION_INTERSTITIAL_DISMISS.equals(action)) {
                this.mCustomEventInterstitialListener.onInterstitialDismissed();
                unregister(this);
            } else if (IntentActions.ACTION_INTERSTITIAL_CLICK.equals(action)) {
                this.mCustomEventInterstitialListener.onInterstitialClicked();
            }
        }
    }
}
