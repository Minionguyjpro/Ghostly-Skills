package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.text.TextUtils;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.a;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter;
import com.yandex.metrica.impl.bb;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.interact.DeviceInfo;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.m;
import com.yandex.metrica.impl.ob.n;
import com.yandex.metrica.impl.ob.p;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.utils.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ba {
    private List<String> A;
    private String B;
    private String C;
    private String D;
    private String E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private String J;
    private String K;
    private String L;

    /* renamed from: a  reason: collision with root package name */
    private String f743a = Build.MANUFACTURER;
    private String b = Build.MODEL;
    private String c = Build.VERSION.RELEASE;
    private int d = Build.VERSION.SDK_INT;
    private String e = "273";
    private String f = bc.a();
    private String g = "7854";
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private int q;
    private int r;
    private int s;
    private float t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private List<String> z;

    public ba() {
        this.h = TextUtils.isEmpty("") ? "public" : "public_";
        this.i = "android";
        this.j = InternalAvidAdSessionContext.AVID_API_LEVEL;
        this.u = a.PHONE.name().toLowerCase(Locale.US);
        this.E = "0";
    }

    public String a() {
        return this.o;
    }

    public synchronized String b() {
        return a(this.k, "");
    }

    public synchronized String c() {
        return a(this.l, "");
    }

    public String d() {
        return a(this.p, "");
    }

    public void c(String str) {
        this.p = str;
    }

    public synchronized void d(String str) {
        this.m = str;
    }

    public synchronized String e() {
        return this.m;
    }

    public String f() {
        return this.j;
    }

    public String g() {
        return this.f;
    }

    public void e(String str) {
        this.e = str;
    }

    public String h() {
        return this.e;
    }

    public void f(String str) {
        this.w = str;
    }

    public String i() {
        return this.w;
    }

    public int j() {
        return i.a(this.w, 0);
    }

    public String k() {
        return this.g;
    }

    public void g(String str) {
        this.g = str;
    }

    public String l() {
        return this.h;
    }

    public void h(String str) {
        this.h = str;
    }

    public String m() {
        return this.i;
    }

    public String n() {
        return a(this.n, "");
    }

    public String o() {
        return a(this.f743a, "");
    }

    public String p() {
        return a(this.b, "");
    }

    public String q() {
        return a(this.c, "");
    }

    public int r() {
        return this.d;
    }

    public void i(String str) {
        this.c = str;
    }

    public void a(int i2) {
        this.d = i2;
    }

    public int s() {
        return this.q;
    }

    public int t() {
        return this.r;
    }

    public int u() {
        return this.s;
    }

    public float v() {
        return this.t;
    }

    public String w() {
        return a(this.v, "");
    }

    public void j(String str) {
        this.v = str;
    }

    public String x() {
        return a(this.x, "");
    }

    public void l(String str) {
        this.J = str;
    }

    public String y() {
        return a(this.J, "");
    }

    public String z() {
        return a(this.y, "");
    }

    public void a(List<String> list) {
        this.z = list;
    }

    public void n(String str) {
        this.B = str;
    }

    public void o(String str) {
        this.C = str;
    }

    public String A() {
        return a(this.C, "");
    }

    public String B() {
        return a(this.D, "");
    }

    public void p(String str) {
        this.D = str;
    }

    public String C() {
        return a(this.B, "");
    }

    public List<String> D() {
        ArrayList arrayList = new ArrayList();
        if (!bk.a((Collection) this.z)) {
            arrayList.addAll(this.z);
        }
        if (!bk.a((Collection) this.A)) {
            arrayList.addAll(this.A);
        }
        arrayList.add("https://startup.mobile.yandex.net/");
        return arrayList;
    }

    public List<String> E() {
        return this.z;
    }

    public void q(String str) {
        this.E = str;
    }

    public String F() {
        return a(this.E, "0");
    }

    public String G() {
        return a(this.u, a.PHONE.name().toLowerCase(Locale.US));
    }

    public boolean H() {
        return this.F;
    }

    public void a(boolean z2) {
        this.F = z2;
    }

    public boolean I() {
        return this.G;
    }

    public boolean J() {
        return this.H;
    }

    public boolean K() {
        return this.I;
    }

    public void b(boolean z2) {
        this.G = z2;
    }

    public void c(boolean z2) {
        this.H = z2;
    }

    public void d(boolean z2) {
        this.I = z2;
    }

    public String L() {
        return a(this.K, "https://certificate.mobile.yandex.net/api/v1/pins");
    }

    public void r(String str) {
        this.K = str;
    }

    public synchronized boolean M() {
        return !bi.a(b(), c(), C());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(long r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.M()     // Catch:{ all -> 0x0025 }
            r1 = 0
            if (r0 != 0) goto L_0x000a
            monitor-exit(r6)
            return r1
        L_0x000a:
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0025 }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r4
            long r2 = r2 - r7
            r7 = 86400(0x15180, double:4.26873E-319)
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x0023
            r7 = 0
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0020
            goto L_0x0023
        L_0x0020:
            r7 = 1
            monitor-exit(r6)
            return r7
        L_0x0023:
            monitor-exit(r6)
            return r1
        L_0x0025:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ba.a(long):boolean");
    }

    public void a(t tVar) {
        Context m2 = tVar.m();
        String b2 = tVar.l().b();
        CounterConfiguration j2 = tVar.j();
        DeviceInfo instance = DeviceInfo.getInstance(m2);
        cd E2 = tVar.E();
        this.o = bk.a(m2, j2, b2);
        this.u = a(m2, j2);
        List<ResolveInfo> a2 = be.a(m2, be.a(m2).setPackage(b2));
        bb.a a3 = !a2.isEmpty() ? bb.a(be.a((PackageItemInfo) a2.get(0).serviceInfo)) : null;
        if (a3 == null) {
            a3 = bb.f744a;
            HashMap hashMap = new HashMap();
            hashMap.put("package", b2);
            YandexMetrica.getReporter(m2, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("invalid_sdk_version", hashMap);
        }
        this.w = a3.f745a;
        a(instance);
        a(tVar, E2);
        b(tVar, E2);
        b(E2);
        String o2 = j2.o();
        if (bi.a(o2)) {
            o2 = x();
            if (bi.a(o2)) {
                o2 = bk.a(m2, b2);
            }
        }
        k(o2);
        String p2 = j2.p();
        if (bi.a(p2)) {
            p2 = z();
            if (bi.a(p2)) {
                p2 = String.valueOf(bk.b(m2, b2));
            }
        }
        m(p2);
        String packageName = tVar.m().getPackageName();
        try {
            this.L = a(d(tVar));
        } catch (PackageManager.NameNotFoundException unused) {
            if (TextUtils.equals(packageName, tVar.l().b())) {
                this.L = a(tVar.m().getApplicationInfo());
            } else {
                this.L = "0";
            }
        }
        e(tVar);
    }

    /* access modifiers changed from: package-private */
    public void b(t tVar) {
        cd E2 = tVar.E();
        b(tVar, E2);
        a(tVar, E2);
        e(tVar);
    }

    private void b(cd cdVar) {
        this.J = cdVar.d((String) null);
    }

    public void c(t tVar) {
        a(DeviceInfo.getInstance(tVar.m()));
        b(tVar.E());
    }

    /* access modifiers changed from: package-private */
    public String a(Context context, CounterConfiguration counterConfiguration) {
        a e2 = counterConfiguration.e();
        return e2 == null ? b(context) : e2.a();
    }

    /* access modifiers changed from: package-private */
    public String b(Context context) {
        return DeviceInfo.getInstance(context).deviceType;
    }

    private void a(DeviceInfo deviceInfo) {
        this.n = deviceInfo.platformDeviceId;
        g.a().b((com.yandex.metrica.impl.ob.i) new m(this.n));
        this.s = deviceInfo.screenDpi;
        this.t = deviceInfo.scaleFactor;
        int i2 = deviceInfo.screenWidth;
        int i3 = deviceInfo.screenHeight;
        this.q = Math.max(i2, i3);
        this.r = Math.min(i2, i3);
        this.v = deviceInfo.getLocale();
        this.E = deviceInfo.deviceRootStatus;
    }

    /* access modifiers changed from: package-private */
    public ApplicationInfo d(t tVar) throws PackageManager.NameNotFoundException {
        return tVar.m().getPackageManager().getApplicationInfo(tVar.l().b(), 0);
    }

    private static String a(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 2) != 0 ? "1" : "0";
    }

    public synchronized void a(bg.a aVar) {
        a(aVar.i());
        b(aVar.h());
        o(aVar.d());
        a(aVar.c());
        n(aVar.e());
        p(aVar.f());
        r(aVar.g());
        l(aVar.j());
        a(aVar.a());
        b(aVar.b());
        c(aVar.p());
        d(aVar.q());
    }

    public synchronized void e(t tVar) {
        CounterConfiguration j2 = tVar.j();
        a(j2.g());
        d(j2.i());
        k(j2.o());
        m(j2.p());
        c(j2.E());
        f(tVar);
    }

    private void f(t tVar) {
        CounterConfiguration j2 = tVar.j();
        if (tVar.l().d() || tVar.l().c()) {
            cd E2 = tVar.E();
            List<String> n2 = j2.n();
            if (bk.a((Collection) n2) && !bk.a((Collection) this.A)) {
                E2.b((List<String>) null).h();
                this.A = null;
            }
            if (!bk.a((Collection) n2) && !bk.a((Object) n2, (Object) this.A)) {
                this.A = n2;
                E2.b(n2).h();
            }
        }
    }

    public String N() {
        return this.L;
    }

    public void s(String str) {
        this.L = str;
    }

    public synchronized void a(String str) {
        if (!bi.a(str)) {
            this.k = str;
        }
    }

    public synchronized void b(String str) {
        if (!bi.a(str)) {
            this.l = str;
        }
    }

    public String a(Context context) {
        return a(GoogleAdvertisingIdGetter.b.f698a.b(context), "");
    }

    public void k(String str) {
        if (!bi.a(str)) {
            this.x = str;
        }
    }

    public void m(String str) {
        if (!bi.a(str)) {
            this.y = str;
        }
    }

    private static String a(String str, String str2) {
        return !bi.a(str) ? str : str2;
    }

    /* access modifiers changed from: package-private */
    public void a(t tVar, cd cdVar) {
        this.C = cdVar.f("");
        this.D = cdVar.g("");
        cdVar.c("https://startup.mobile.yandex.net/");
        this.z = cdVar.a();
        this.A = cdVar.b();
        this.B = cdVar.e("");
        f(tVar);
        a(cdVar);
    }

    /* access modifiers changed from: package-private */
    public void a(cd cdVar) {
        a(cdVar.e());
        b(cdVar.i());
        c(cdVar.j());
        d(cdVar.k());
    }

    private synchronized void b(t tVar, cd cdVar) {
        String c2 = c();
        if (bi.a(c2)) {
            c2 = tVar.j().h();
            if (bi.a(c2)) {
                c2 = ci.a().a(tVar.m());
            }
        }
        g.a().b((com.yandex.metrica.impl.ob.i) new n(c2));
        b(c2);
        String g2 = tVar.j().g();
        if (bi.a(g2)) {
            g2 = b();
            if (bi.a(g2)) {
                g2 = cdVar.b("");
            }
        }
        g.a().b((com.yandex.metrica.impl.ob.i) new p(g2, tVar.l().b()));
        a(g2);
    }
}
