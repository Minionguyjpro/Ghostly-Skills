package com.startapp.android.publish.ads.splash;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class a extends com.startapp.android.publish.html.a {
    public a(Context context, b bVar, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, bVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_SPLASH, true);
    }

    /* access modifiers changed from: protected */
    public void a(Boolean bool) {
        super.a(bool);
        a(bool.booleanValue());
    }
}
