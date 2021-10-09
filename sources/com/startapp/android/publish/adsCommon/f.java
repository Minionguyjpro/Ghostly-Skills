package com.startapp.android.publish.adsCommon;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public class f {

    /* renamed from: a  reason: collision with root package name */
    protected StartAppAd f237a;
    private boolean b;
    private AutoInterstitialPreferences c;
    private long d;
    private int e;

    /* compiled from: StartAppSDK */
    private static class a {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final f f239a = new f();
    }

    private f() {
        this.b = false;
        this.c = null;
        this.d = -1;
        this.e = -1;
        this.f237a = null;
    }

    public static f a() {
        return a.f239a;
    }

    public void b() {
        this.b = true;
    }

    public void c() {
        this.b = false;
    }

    public void a(AutoInterstitialPreferences autoInterstitialPreferences) {
        this.c = autoInterstitialPreferences;
        this.d = -1;
        this.e = -1;
    }

    /* access modifiers changed from: protected */
    public void d() {
        this.d = System.currentTimeMillis();
        this.e = 0;
    }

    private boolean e() {
        return this.b && b.a().I();
    }

    private boolean f() {
        if (this.c == null) {
            this.c = new AutoInterstitialPreferences();
        }
        boolean z = this.d <= 0 || System.currentTimeMillis() >= this.d + ((long) (this.c.getSecondsBetweenAds() * 1000));
        int i = this.e;
        boolean z2 = i <= 0 || i >= this.c.getActivitiesBetweenAds();
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    private boolean a(Activity activity) {
        String name = activity.getClass().getName();
        if (!name.startsWith("com.startapp.android.publish." + "adsCommon.activities.OverlayActivity")) {
            if (!name.startsWith("com.startapp.android.publish." + "adsCommon.activities.FullScreenActivity")) {
                StringBuilder sb = new StringBuilder();
                sb.append("com.startapp.android.publish.");
                sb.append("ads.list3d.List3DActivity");
                return name.startsWith(sb.toString());
            }
        }
    }

    private boolean b(Activity activity) {
        return activity.getClass().getName().equals(m.a().g());
    }

    public void a(Context context) {
        if (e() && f()) {
            if (this.f237a == null) {
                this.f237a = new StartAppAd(context);
            }
            this.f237a.loadAd(StartAppAd.AdMode.AUTOMATIC, new AdPreferences().setAi(true), new AdEventListener() {
                public void onReceiveAd(Ad ad) {
                    if (f.this.f237a.showAd()) {
                        g.a("InterActivityAdManager", 3, "ShowInterActivityAd");
                        f.this.d();
                    }
                }

                public void onFailedToReceiveAd(Ad ad) {
                    g.a("InterActivityAdManager", 3, "FailedToShowInterActivityAd, error: [" + ad.errorMessage + "]");
                }
            });
        }
    }

    public void a(Activity activity, Bundle bundle) {
        if (bundle == null && !a(activity) && !b(activity)) {
            this.e++;
            a((Context) activity);
        }
    }
}
