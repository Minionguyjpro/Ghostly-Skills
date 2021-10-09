package com.startapp.android.publish.ads.c.b;

import android.content.Context;
import com.startapp.android.publish.ads.list3d.e;
import com.startapp.android.publish.ads.list3d.f;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.b;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import java.util.List;

/* compiled from: StartAppSDK */
public class a extends com.startapp.android.publish.a.a {
    public a(Context context, b bVar, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, bVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_OFFER_WALL);
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
    public void a(Ad ad) {
        b bVar = (b) ad;
        List<AdDetails> d = bVar.d();
        e a2 = f.a().a(bVar.a());
        a2.a();
        if (d != null) {
            for (AdDetails a3 : d) {
                a2.a(a3);
            }
        }
    }
}
