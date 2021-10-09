package com.startapp.android.publish.ads.banner.banner3d;

import android.content.Context;
import com.startapp.android.publish.a.a;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;

/* compiled from: StartAppSDK */
public class c extends a {
    private int g = 0;

    /* access modifiers changed from: protected */
    public void a(Ad ad) {
    }

    public c(Context context, a aVar, int i, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, aVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_BANNER);
        this.g = i;
    }

    /* access modifiers changed from: protected */
    public GetAdRequest a() {
        com.startapp.android.publish.ads.banner.a aVar = new com.startapp.android.publish.ads.banner.a();
        b((GetAdRequest) aVar);
        aVar.setAdsNumber(com.startapp.android.publish.ads.banner.c.a().b().f());
        aVar.setOffset(this.g);
        aVar.a(((a) this.b).b());
        return aVar;
    }
}
