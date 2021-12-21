package com.moat.analytics.mobile.mpub;

import android.app.Application;
import android.content.Context;
import com.moat.analytics.mobile.mpub.g;
import com.moat.analytics.mobile.mpub.w;
import java.lang.ref.WeakReference;

class k extends MoatAnalytics implements w.b {

    /* renamed from: a  reason: collision with root package name */
    boolean f1173a = false;
    boolean b = false;
    g c;
    WeakReference<Context> d;
    private boolean e = false;
    private String f;
    private MoatOptions g;

    k() {
    }

    private void a(MoatOptions moatOptions, Application application) {
        if (this.e) {
            p.a(3, "Analytics", (Object) this, "Moat SDK has already been started.");
            return;
        }
        this.g = moatOptions;
        w.a().b();
        if (application != null) {
            if (moatOptions.loggingEnabled && s.b(application.getApplicationContext())) {
                this.f1173a = true;
            }
            this.d = new WeakReference<>(application.getApplicationContext());
            this.e = true;
            this.b = moatOptions.autoTrackGMAInterstitials;
            a.a(application);
            w.a().a((w.b) this);
            if (!moatOptions.disableAdIdCollection) {
                s.a((Context) application);
            }
            p.a("[SUCCESS] ", "Moat Analytics SDK Version 2.6.6 started");
            return;
        }
        throw new n("Moat Analytics SDK didn't start, application was null");
    }

    private void d() {
        if (this.c == null) {
            g gVar = new g(a.a(), g.a.DISPLAY);
            this.c = gVar;
            gVar.a(this.f);
            p.a(3, "Analytics", (Object) this, "Preparing native display tracking with partner code " + this.f);
            p.a("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.f);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.e;
    }

    public void b() {
        n.a();
        if (this.f != null) {
            try {
                d();
            } catch (Exception e2) {
                n.a(e2);
            }
        }
    }

    public void c() {
    }

    public void prepareNativeDisplayTracking(String str) {
        this.f = str;
        if (w.a().f1186a != w.d.OFF) {
            try {
                d();
            } catch (Exception e2) {
                n.a(e2);
            }
        }
    }

    public void start(Application application) {
        start(new MoatOptions(), application);
    }

    public void start(MoatOptions moatOptions, Application application) {
        try {
            a(moatOptions, application);
        } catch (Exception e2) {
            n.a(e2);
        }
    }
}
