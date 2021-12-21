package com.startapp.android.publish.ads.splash;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.a.f;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.d;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.Constants;
import com.startapp.common.a.g;
import com.startapp.common.a.i;
import com.startapp.common.b;

/* compiled from: StartAppSDK */
public class c {

    /* renamed from: a  reason: collision with root package name */
    Activity f85a;
    boolean b;
    a c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private d i;
    private BroadcastReceiver j;

    /* compiled from: StartAppSDK */
    enum a {
        LOADING,
        RECEIVED,
        DISPLAYED,
        HIDDEN,
        DO_NOT_DISPLAY
    }

    public c(Activity activity) {
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = false;
        this.b = false;
        this.c = a.LOADING;
        this.i = null;
        this.j = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                c.this.i();
            }
        };
        this.f85a = activity;
    }

    public c(Activity activity, d dVar) {
        this(activity);
        this.i = dVar;
    }

    public void a(final Runnable runnable, final com.startapp.android.publish.cache.c cVar) {
        g.a("Splash", 4, "Minimum splash screen time has passed");
        this.d = true;
        AnonymousClass1 r0 = new d() {
            private Runnable d = new Runnable() {
                public void run() {
                    c.this.b = true;
                    if (c.this.c != a.DO_NOT_DISPLAY) {
                        c.this.c(runnable, cVar);
                    }
                }
            };

            public void a() {
                g.a("Splash", 4, "MetaData received");
                c.this.f85a.runOnUiThread(this.d);
            }

            public void b() {
                g.a("Splash", 4, "MetaData failed to receive - proceeding with old MetaData");
                c.this.f85a.runOnUiThread(this.d);
            }
        };
        if (this.c != a.DO_NOT_DISPLAY) {
            a((d) r0);
        } else {
            k();
        }
    }

    public void a() {
        this.d = true;
    }

    private void a(d dVar) {
        synchronized (MetaData.getLock()) {
            if (MetaData.getInstance().isReady()) {
                dVar.a();
            } else {
                MetaData.getInstance().addMetaDataListener(dVar);
            }
        }
    }

    public void a(Runnable runnable) {
        g.a("Splash", 4, "Splash ad received");
        if (this.c == a.LOADING) {
            this.c = a.RECEIVED;
        }
        b(runnable);
    }

    public void b() {
        g.a("Splash", 4, "Error receiving Ad");
        this.c = a.DO_NOT_DISPLAY;
        b((Runnable) null);
    }

    public boolean b(Runnable runnable, com.startapp.android.publish.cache.c cVar) {
        if (!this.h) {
            if (this.c == a.LOADING) {
                g.a("Splash", 4, "Splash Loading Timer Expired");
                this.e = false;
                this.c = a.DO_NOT_DISPLAY;
                k();
                return true;
            } else if (this.c == a.RECEIVED) {
                g.a("Splash", 4, "MetaData Loading Timer Expired - proceeding with old MetaData");
                this.b = true;
                c(runnable, cVar);
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void c(Runnable runnable, com.startapp.android.publish.cache.c cVar) {
        f a2 = com.startapp.android.publish.adsCommon.a.g.a().b().a(AdPreferences.Placement.INAPP_SPLASH, (String) null);
        g.a("Splash", 4, "checkAdRulesAndShowAd: shouldDisplayAd " + a2.a());
        if (a2.a()) {
            g.a("Splash", 4, "checkAdRulesAndShowAd: showAd");
            b(runnable);
            return;
        }
        g.a("Splash", 4, "Should not display splash ad");
        this.c = a.DO_NOT_DISPLAY;
        if (cVar != null) {
            com.startapp.android.publish.adsCommon.c.a((Context) this.f85a, com.startapp.android.publish.adsCommon.c.a(com.startapp.android.publish.cache.a.a().b(cVar)), (String) null, a2.c());
        }
        if (Constants.a().booleanValue()) {
            i.a().a(this.f85a, a2.b());
        }
        k();
    }

    public void c() {
        g.a("Splash", 4, "Splash Screen has been hidden");
        this.c = a.HIDDEN;
        g();
        if (!this.f85a.isFinishing()) {
            this.f85a.finish();
        }
    }

    public void d() {
        this.c = a.DISPLAYED;
    }

    private void b(Runnable runnable) {
        if (!this.d) {
            return;
        }
        if (!this.b && runnable != null) {
            return;
        }
        if (this.c == a.RECEIVED && runnable != null) {
            this.e = false;
            runnable.run();
        } else if (this.c != a.LOADING) {
            k();
        }
    }

    public void a(StartAppAd startAppAd) {
        if (this.c == a.DISPLAYED) {
            g.a("Splash", 4, "Splash Ad Display Timeout");
            if (!this.g) {
                g.a("Splash", 4, "Closing Splash Ad");
                startAppAd.close();
                c();
            }
        }
    }

    public void e() {
        if (this.c != a.DISPLAYED && this.c != a.DO_NOT_DISPLAY) {
            this.c = a.DO_NOT_DISPLAY;
            if (this.e) {
                f();
            }
        }
    }

    public void f() {
        g.a("Splash", 4, "User Canceled Splash Screen");
        g();
    }

    private void k() {
        a(this.i, (e) new e() {
            public void a() {
                g.a("Splash", 4, "Loading Main Activity");
                c.this.g();
                if (!c.this.f85a.isFinishing()) {
                    c.this.f85a.finish();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void g() {
        if (!this.f) {
            this.f = true;
            b.a((Context) this.f85a).a(new Intent("com.startapp.android.splashHidden"));
        }
        if (this.j != null) {
            try {
                Log.v("startapp", "unregistering receiver");
                b.a((Context) this.f85a).a(this.j);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    public void h() {
        this.h = true;
    }

    public void i() {
        this.g = true;
    }

    public void j() {
        b.a((Context) this.f85a).a(this.j, new IntentFilter("com.startapp.android.adInfoWasClickedBroadcastListener"));
    }

    /* access modifiers changed from: protected */
    public void a(d dVar, e eVar) {
        if (dVar == null) {
            eVar.a();
            return;
        }
        dVar.a(eVar);
        dVar.b();
    }
}
