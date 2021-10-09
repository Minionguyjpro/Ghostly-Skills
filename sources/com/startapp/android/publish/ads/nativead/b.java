package com.startapp.android.publish.ads.nativead;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.h;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class b extends h {
    private static final long serialVersionUID = 1;
    private NativeAdPreferences config;

    public b(Context context, NativeAdPreferences nativeAdPreferences) {
        super(context, AdPreferences.Placement.INAPP_NATIVE);
        this.config = nativeAdPreferences;
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new a(this.context, this, adPreferences, adEventListener, this.config).c();
    }
}
