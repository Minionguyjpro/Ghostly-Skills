package com.startapp.android.publish.adsCommon.adListeners;

import com.startapp.android.publish.adsCommon.Ad;

/* compiled from: StartAppSDK */
public interface AdDisplayListener {

    /* compiled from: StartAppSDK */
    public enum NotDisplayedReason {
        NETWORK_PROBLEM,
        AD_RULES,
        AD_NOT_READY,
        AD_EXPIRED,
        VIDEO_BACK,
        VIDEO_ERROR,
        INTERNAL_ERROR,
        AD_NOT_READY_VIDEO_FALLBACK,
        APP_IN_BACKGROUND,
        AD_CLOSED_TOO_QUICKLY,
        SERVING_ADS_DISABLED
    }

    void adClicked(Ad ad);

    void adDisplayed(Ad ad);

    void adHidden(Ad ad);

    void adNotDisplayed(Ad ad);
}
