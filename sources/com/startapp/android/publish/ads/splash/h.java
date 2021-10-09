package com.startapp.android.publish.ads.splash;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.a.f;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.cache.c;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public class h {

    /* renamed from: a  reason: collision with root package name */
    Activity f94a;
    c b;
    c c;
    d d = null;
    boolean e = false;
    a f;
    Runnable g = new Runnable() {
        public void run() {
            h.this.b.a(h.this.d, (e) new e() {
                public void a() {
                    if (!h.this.e && h.this.f != null) {
                        g.a("Splash", 4, "Displaying Splash ad");
                        h.this.f.showAd((AdDisplayListener) new AdDisplayListener() {
                            public void adNotDisplayed(Ad ad) {
                            }

                            public void adHidden(Ad ad) {
                                h.this.b.c();
                            }

                            public void adDisplayed(Ad ad) {
                                h.this.b.d();
                            }

                            public void adClicked(Ad ad) {
                                h.this.b.i();
                            }
                        });
                        h.this.f();
                        h.this.f94a.finish();
                    }
                }
            });
        }
    };
    private SplashConfig h;
    private Handler i = new Handler();
    private AdPreferences j;
    private Runnable k = new Runnable() {
        public void run() {
            if (h.this.c()) {
                h.this.d();
                h.this.e();
                return;
            }
            h.this.f94a.finish();
        }
    };
    private AdEventListener l = new AdEventListener() {
        public void onReceiveAd(Ad ad) {
            g.a("Splash", 4, "Splash ad received");
            h.this.b.a(h.this.g);
        }

        public void onFailedToReceiveAd(Ad ad) {
            if (h.this.f != null) {
                h.this.b.b();
            }
        }
    };

    public void a() {
    }

    /* compiled from: StartAppSDK */
    private static class a extends StartAppAd {
        private static final long serialVersionUID = 1;

        public a(Context context) {
            super(context);
            this.placement = AdPreferences.Placement.INAPP_SPLASH;
        }

        /* access modifiers changed from: protected */
        public f shouldDisplayAd(String str, AdPreferences.Placement placement) {
            return new f(true);
        }
    }

    public h(Activity activity, SplashConfig splashConfig, AdPreferences adPreferences) {
        this.f94a = activity;
        this.h = splashConfig;
        this.j = adPreferences;
        try {
            h();
            this.b = new c(activity, this.d);
        } catch (Exception e2) {
            c cVar = new c(activity);
            this.b = cVar;
            cVar.a();
            this.b.b();
            com.startapp.android.publish.adsCommon.f.f.a(activity, d.EXCEPTION, "SplashScreen.constructor - WebView failed", e2.getMessage(), "");
        }
    }

    private void h() {
        this.h.initSplashLogo(this.f94a);
        if (!k()) {
            this.d = this.h.initSplashHtml(this.f94a);
        }
    }

    public void a(Bundle bundle) {
        g.a("Splash", 4, "========= Splash Screen Feature =========");
        this.b.j();
        if (!i()) {
            this.i.post(this.k);
            return;
        }
        this.i.postDelayed(this.k, 100);
        g.a("Splash", 4, "Splash screen orientation is being modified");
    }

    public void b() {
        this.i.removeCallbacks(this.k);
        this.b.e();
    }

    private boolean i() {
        int i2 = this.f94a.getResources().getConfiguration().orientation;
        if (this.h.getOrientation() == SplashConfig.Orientation.AUTO) {
            if (i2 == 2) {
                this.h.setOrientation(SplashConfig.Orientation.LANDSCAPE);
            } else {
                this.h.setOrientation(SplashConfig.Orientation.PORTRAIT);
            }
        }
        int i3 = AnonymousClass7.f103a[this.h.getOrientation().ordinal()];
        boolean z = true;
        boolean z2 = false;
        if (i3 != 1) {
            if (i3 == 2) {
                if (i2 != 1) {
                    z = false;
                }
                com.startapp.common.a.c.b(this.f94a);
            }
            g.a("Splash", 4, "Set Orientation: [" + this.h.getOrientation().toString() + "]");
            return z2;
        }
        if (i2 != 2) {
            z = false;
        }
        com.startapp.common.a.c.a(this.f94a);
        z2 = z;
        g.a("Splash", 4, "Set Orientation: [" + this.h.getOrientation().toString() + "]");
        return z2;
    }

    /* renamed from: com.startapp.android.publish.ads.splash.h$7  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass7 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f103a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.startapp.android.publish.ads.splash.SplashConfig$Orientation[] r0 = com.startapp.android.publish.ads.splash.SplashConfig.Orientation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f103a = r0
                com.startapp.android.publish.ads.splash.SplashConfig$Orientation r1 = com.startapp.android.publish.ads.splash.SplashConfig.Orientation.PORTRAIT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f103a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.ads.splash.SplashConfig$Orientation r1 = com.startapp.android.publish.ads.splash.SplashConfig.Orientation.LANDSCAPE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.splash.h.AnonymousClass7.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        g.a("Splash", 4, "Displaying Splash screen");
        if (this.h.validate(this.f94a)) {
            View j2 = j();
            if (j2 == null) {
                return false;
            }
            this.f94a.setContentView(j2, new ViewGroup.LayoutParams(-1, -1));
            return true;
        }
        throw new IllegalArgumentException(this.h.getErrorMessage());
    }

    private View j() {
        if (k()) {
            return this.h.getLayout(this.f94a);
        }
        d dVar = this.d;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        g.a("Splash", 4, "Loading Splash Ad");
        a aVar = new a(this.f94a.getApplicationContext());
        this.f = aVar;
        this.c = aVar.loadSplash(this.j, this.l);
    }

    /* access modifiers changed from: package-private */
    public void e() {
        g.a("Splash", 4, "Started Splash Loading Timer");
        this.i.postDelayed(new Runnable() {
            public void run() {
                if (h.this.b.b(h.this.g, h.this.c)) {
                    h.this.f = null;
                    h.this.c = null;
                }
            }
        }, this.h.getMaxLoadAdTimeout().longValue());
        this.i.postDelayed(new Runnable() {
            public void run() {
                h.this.b.a(h.this.g, h.this.c);
            }
        }, this.h.getMinSplashTime().getIndex());
    }

    /* access modifiers changed from: package-private */
    public void f() {
        g.a("Splash", 4, "Started Splash Display Timer");
        if (this.h.getMaxAdDisplayTime() != SplashConfig.MaxAdDisplayTime.FOR_EVER) {
            this.i.postDelayed(new Runnable() {
                public void run() {
                    h.this.b.a((StartAppAd) h.this.f);
                }
            }, this.h.getMaxAdDisplayTime().getIndex());
        }
    }

    public void g() {
        this.e = true;
        this.b.h();
    }

    private boolean k() {
        return !this.h.isHtmlSplash() || this.h.isUserDefinedSplash();
    }
}
