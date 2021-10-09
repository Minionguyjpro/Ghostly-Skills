package com.startapp.android.publish.ads.banner.bannerstandard;

import android.content.Context;
import com.startapp.android.publish.ads.banner.c;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.e;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.android.publish.html.a;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public class b extends a {
    private int i = 0;

    public b(Context context, e eVar, int i2, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, eVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_BANNER, false);
        this.i = i2;
    }

    /* access modifiers changed from: protected */
    public GetAdRequest a() {
        a aVar = (a) this.b;
        com.startapp.android.publish.ads.banner.a aVar2 = new com.startapp.android.publish.ads.banner.a();
        b((GetAdRequest) aVar2);
        aVar2.setWidth(aVar.h());
        aVar2.setHeight(aVar.j());
        aVar2.setOffset(this.i);
        aVar2.setAdsNumber(c.a().b().g());
        aVar2.a(aVar.b());
        aVar2.a(aVar.c());
        return aVar2;
    }

    /* access modifiers changed from: protected */
    public void a(Boolean bool) {
        super.a(bool);
        a(bool.booleanValue());
        g.a(4, "Html onPostExecute, result=[" + bool + "]");
    }
}
