package com.startapp.android.publish.ads.c.a;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.b;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;

/* compiled from: StartAppSDK */
public class a extends com.startapp.android.publish.html.a {
    public a(Context context, b bVar, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, bVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_OFFER_WALL, true);
    }

    /* access modifiers changed from: protected */
    public GetAdRequest a() {
        GetAdRequest a2 = super.a();
        if (a2 == null) {
            return null;
        }
        a2.setAdsNumber(b.a().g());
        return a2;
    }

    /* access modifiers changed from: protected */
    public void a(Boolean bool) {
        super.a(bool);
        a(bool.booleanValue());
    }
}
