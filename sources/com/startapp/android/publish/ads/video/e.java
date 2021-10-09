package com.startapp.android.publish.ads.video;

import android.content.Context;
import com.startapp.android.publish.ads.b.c;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.ads.video.c.a.a.b;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class e extends c {
    private static final long serialVersionUID = 1;
    private VideoAdDetails videoAdDetails = null;

    public e(Context context) {
        super(context, AdPreferences.Placement.INAPP_OVERLAY);
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new b(this.context, this, adPreferences, adEventListener).c();
    }

    public void b(String str) {
        super.b(str);
        f(a(str, "@videoJson@"));
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        return this.videoAdDetails != null;
    }

    public VideoAdDetails d() {
        return this.videoAdDetails;
    }

    public void a(com.startapp.android.publish.ads.video.c.a.e eVar, boolean z) {
        if (eVar != null) {
            this.videoAdDetails = new VideoAdDetails(eVar, z);
            b g = eVar.g();
            if (g == null) {
                return;
            }
            if (g.d().intValue() > g.e().intValue()) {
                a(SplashConfig.Orientation.LANDSCAPE);
            } else {
                a(SplashConfig.Orientation.PORTRAIT);
            }
        }
    }

    public void e() {
        this.videoAdDetails = null;
    }

    private void f(String str) {
        if (str != null) {
            this.videoAdDetails = (VideoAdDetails) com.startapp.common.c.b.a(str, VideoAdDetails.class);
        }
    }
}
