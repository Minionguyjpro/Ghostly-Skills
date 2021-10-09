package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.j;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

abstract class b {

    /* renamed from: a  reason: collision with root package name */
    n f1154a = null;
    WeakReference<WebView> b;
    j c;
    TrackerListener d;
    final String e;
    final boolean f;
    private WeakReference<View> g;
    private final z h;
    private final boolean i;
    private boolean j;
    private boolean k;

    b(View view, boolean z, boolean z2) {
        String str;
        p.a(3, "BaseTracker", (Object) this, "Initializing.");
        if (z) {
            str = "m" + hashCode();
        } else {
            str = "";
        }
        this.e = str;
        this.g = new WeakReference<>(view);
        this.i = z;
        this.f = z2;
        this.j = false;
        this.k = false;
        this.h = new z();
    }

    private void i() {
        String str;
        p.a(3, "BaseTracker", (Object) this, "Attempting bridge installation.");
        if (this.b.get() != null) {
            this.c = new j((WebView) this.b.get(), j.a.WEBVIEW);
            str = "Bridge installed.";
        } else {
            this.c = null;
            str = "Bridge not installed, WebView is null.";
        }
        p.a(3, "BaseTracker", (Object) this, str);
    }

    private void j() {
        if (this.j) {
            throw new n("Tracker already started");
        }
    }

    private void k() {
        if (this.k) {
            throw new n("Tracker already stopped");
        }
    }

    private boolean l() {
        return this.i || this.f;
    }

    /* access modifiers changed from: package-private */
    public abstract String a();

    /* access modifiers changed from: package-private */
    public void a(WebView webView) {
        if (webView != null) {
            this.b = new WeakReference<>(webView);
            if (this.c == null && !l()) {
                i();
            }
            j jVar = this.c;
            if (jVar != null) {
                jVar.a(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        this.c = jVar;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Exception exc) {
        try {
            n.a(exc);
            String a2 = n.a(str, exc);
            if (this.d != null) {
                this.d.onTrackingFailedToStart(a2);
            }
            p.a(3, "BaseTracker", (Object) this, a2);
            p.a("[ERROR] ", a() + " " + a2);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void a(List<String> list) {
        if (f() == null && !this.f) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new n(TextUtils.join(" and ", list));
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        p.a(3, "BaseTracker", (Object) this, "Attempting to start impression.");
        c();
        d();
        a((List<String>) new ArrayList());
        j jVar = this.c;
        if (jVar != null) {
            jVar.b(this);
            this.j = true;
            p.a(3, "BaseTracker", (Object) this, "Impression started.");
            return;
        }
        p.a(3, "BaseTracker", (Object) this, "Bridge is null, won't start tracking");
        throw new n("Bridge is null");
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (this.f1154a != null) {
            throw new n("Tracker initialization failed: " + this.f1154a.getMessage());
        }
    }

    public void changeTargetView(View view) {
        p.a(3, "BaseTracker", (Object) this, "changing view to " + p.a(view));
        this.g = new WeakReference<>(view);
    }

    /* access modifiers changed from: package-private */
    public void d() {
        j();
        k();
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.j && !this.k;
    }

    /* access modifiers changed from: package-private */
    public View f() {
        return (View) this.g.get();
    }

    /* access modifiers changed from: package-private */
    public String g() {
        return p.a(f());
    }

    /* access modifiers changed from: package-private */
    public String h() {
        this.h.a(this.e, f());
        return this.h.f1197a;
    }

    public void removeListener() {
        this.d = null;
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    public void setListener(TrackerListener trackerListener) {
        this.d = trackerListener;
    }

    public void startTracking() {
        try {
            p.a(3, "BaseTracker", (Object) this, "In startTracking method.");
            b();
            if (this.d != null) {
                this.d.onTrackingStarted("Tracking started on " + g());
            }
            String str = "startTracking succeeded for " + g();
            p.a(3, "BaseTracker", (Object) this, str);
            p.a("[SUCCESS] ", a() + " " + str);
        } catch (Exception e2) {
            a("startTracking", e2);
        }
    }

    public void stopTracking() {
        boolean z = false;
        try {
            p.a(3, "BaseTracker", (Object) this, "In stopTracking method.");
            this.k = true;
            if (this.c != null) {
                this.c.c(this);
                z = true;
            }
        } catch (Exception e2) {
            n.a(e2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Attempt to stop tracking ad impression was ");
        sb.append(z ? "" : "un");
        sb.append("successful.");
        p.a(3, "BaseTracker", (Object) this, sb.toString());
        String str = z ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(a());
        sb2.append(" stopTracking ");
        sb2.append(z ? "succeeded" : "failed");
        sb2.append(" for ");
        sb2.append(g());
        p.a(str, sb2.toString());
        TrackerListener trackerListener = this.d;
        if (trackerListener != null) {
            trackerListener.onTrackingStopped("");
            this.d = null;
        }
    }
}
