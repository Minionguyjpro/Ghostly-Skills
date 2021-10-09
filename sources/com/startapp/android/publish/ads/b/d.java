package com.startapp.android.publish.ads.b;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class d extends c {
    private static final long serialVersionUID = 1;

    public d(Context context) {
        super(context, AdPreferences.Placement.INAPP_OVERLAY);
    }

    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new a(this.context, this, adPreferences, adEventListener).c();
    }
}
