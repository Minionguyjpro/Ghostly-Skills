package com.startapp.android.publish.ads.b;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.cache.d;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class e extends c {
    private static final long serialVersionUID = 1;

    public e(Context context) {
        super(context, AdPreferences.Placement.INAPP_RETURN);
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new b(this.context, this, adPreferences, adEventListener).c();
    }

    /* access modifiers changed from: protected */
    public long getFallbackAdCacheTtl() {
        return d.a().b().getReturnAdCacheTTL();
    }
}
