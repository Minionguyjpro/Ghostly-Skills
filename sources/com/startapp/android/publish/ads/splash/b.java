package com.startapp.android.publish.ads.splash;

import android.content.Context;
import com.startapp.android.publish.ads.b.c;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class b extends c {
    private static final long serialVersionUID = 1;

    public b(Context context) {
        super(context, AdPreferences.Placement.INAPP_OVERLAY);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences, AdEventListener adEventListener) {
        return super.load(adPreferences, adEventListener, false);
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new a(this.context, this, adPreferences, adEventListener).c();
    }
}
