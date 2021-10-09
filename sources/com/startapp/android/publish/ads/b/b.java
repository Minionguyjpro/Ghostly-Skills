package com.startapp.android.publish.ads.b;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.e;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.html.a;

/* compiled from: StartAppSDK */
public class b extends a {
    public b(Context context, e eVar, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, eVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_RETURN, true);
    }

    /* access modifiers changed from: protected */
    public void a(Boolean bool) {
        super.a(bool);
        a(bool.booleanValue());
    }
}
