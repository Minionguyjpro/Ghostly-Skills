package com.startapp.android.publish.ads.banner.bannerstandard;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.e;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class a extends e {
    private static final long serialVersionUID = 1;
    private int bannerType;
    private boolean fixedSize;
    private int offset = 0;

    public a(Context context, int i) {
        super(context, AdPreferences.Placement.INAPP_BANNER);
        this.offset = i;
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new b(this.context, this, this.offset, adPreferences, adEventListener).c();
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

    public int c() {
        return this.bannerType;
    }

    public void a(int i) {
        this.bannerType = i;
    }
}
