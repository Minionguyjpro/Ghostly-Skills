package com.startapp.android.publish.ads.banner.banner3d;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.h;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class a extends h {
    private static final long serialVersionUID = 1;
    private boolean fixedSize;
    private int offset;

    public a(Context context, int i) {
        super(context, AdPreferences.Placement.INAPP_BANNER);
        this.offset = i;
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new c(this.context, this, this.offset, adPreferences, adEventListener).c();
        this.offset++;
    }

    public int a() {
        return this.offset;
    }

    public void a(boolean z) {
        this.fixedSize = z;
    }

    public boolean b() {
        return this.fixedSize;
    }
}
