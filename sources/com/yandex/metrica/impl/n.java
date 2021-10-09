package com.yandex.metrica.impl;

import android.location.Location;
import com.yandex.metrica.e;
import java.util.HashMap;
import java.util.Map;

public class n {

    /* renamed from: a  reason: collision with root package name */
    private Integer f777a;
    private Boolean b;
    private Boolean c;
    private Location d;
    private Boolean e;
    private String f;
    private Boolean g;
    private Map<String, String> h = new HashMap();
    private Map<String, String> i = new HashMap();
    private boolean j;
    private boolean k;

    public Integer a() {
        return this.f777a;
    }

    public Boolean b() {
        return this.b;
    }

    public Boolean c() {
        return this.c;
    }

    public Location d() {
        return this.d;
    }

    public Boolean e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public Boolean g() {
        return this.g;
    }

    public void c(boolean z) {
        this.b = Boolean.valueOf(z);
    }

    public boolean i() {
        return this.j;
    }

    public e a(e eVar) {
        if (this.k) {
            return eVar;
        }
        e.a a2 = e.a(eVar.getApiKey());
        a2.a(eVar.f(), eVar.k());
        a2.a(eVar.e());
        a2.a(eVar.getPreloadInfo());
        a2.c(eVar.a());
        a2.a(eVar.getLocation());
        if (eVar.c() != null) {
            a2.a(eVar.c());
        }
        if (eVar.getAppVersion() != null) {
            a2.a(eVar.getAppVersion());
        }
        if (eVar.i() != null) {
            a2.d(eVar.i().intValue());
        }
        if (eVar.d() != null) {
            a2.b(eVar.d().intValue());
        }
        if (eVar.h() != null) {
            a2.c(eVar.h().intValue());
        }
        if ((eVar.isLogEnabled() != null) && eVar.isLogEnabled().booleanValue()) {
            a2.a();
        }
        if (eVar.getSessionTimeout() != null) {
            a2.a(eVar.getSessionTimeout().intValue());
        }
        if (eVar.isReportCrashEnabled() != null) {
            a2.a(eVar.isReportCrashEnabled().booleanValue());
        }
        if (eVar.isReportNativeCrashEnabled() != null) {
            a2.b(eVar.isReportNativeCrashEnabled().booleanValue());
        }
        if (eVar.isTrackLocationEnabled() != null) {
            a2.c(eVar.isTrackLocationEnabled().booleanValue());
        }
        if (eVar.isCollectInstalledApps() != null) {
            a2.d(eVar.isCollectInstalledApps().booleanValue());
        }
        if (eVar.g() != null) {
            a2.b(eVar.g());
        }
        if (eVar.isFirstActivationAsUpdate()) {
            a2.e(true);
        }
        a(eVar.j(), a2);
        b(eVar.getErrorEnvironment(), a2);
        Integer a3 = a();
        if (eVar.getSessionTimeout() == null) {
            if (a3 != null) {
                a2.a(a3.intValue());
            }
        }
        Boolean b2 = b();
        if (eVar.isReportCrashEnabled() == null) {
            if (b2 != null) {
                a2.a(b2.booleanValue());
            }
        }
        Boolean c2 = c();
        if (eVar.isReportNativeCrashEnabled() == null) {
            if (c2 != null) {
                a2.b(c2.booleanValue());
            }
        }
        Boolean e2 = e();
        if (eVar.isTrackLocationEnabled() == null) {
            if (e2 != null) {
                a2.c(e2.booleanValue());
            }
        }
        Location d2 = d();
        if (eVar.getLocation() == null) {
            if (d2 != null) {
                a2.a(d2);
            }
        }
        Boolean g2 = g();
        if (eVar.isCollectInstalledApps() == null) {
            if (g2 != null) {
                a2.d(g2.booleanValue());
            }
        }
        String f2 = f();
        if (eVar.getAppVersion() == null) {
            if (f2 != null) {
                a2.a(f2);
            }
        }
        a(this.h, a2);
        b(this.i, a2);
        this.k = true;
        this.f777a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h.clear();
        this.i.clear();
        this.j = false;
        return a2.b();
    }

    private static void a(Map<String, String> map, e.a aVar) {
        if (!bk.a((Map) map)) {
            for (Map.Entry next : map.entrySet()) {
                aVar.b((String) next.getKey(), (String) next.getValue());
            }
        }
    }

    private static void b(Map<String, String> map, e.a aVar) {
        if (!bk.a((Map) map)) {
            for (Map.Entry next : map.entrySet()) {
                aVar.a((String) next.getKey(), (String) next.getValue());
            }
        }
    }
}
