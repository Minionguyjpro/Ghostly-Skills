package com.yandex.metrica.impl;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.cq;
import com.yandex.metrica.impl.ob.cu;
import com.yandex.metrica.impl.ob.du;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.i;
import com.yandex.metrica.impl.ob.o;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.utils.k;
import com.yandex.metrica.impl.utils.l;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

class bh extends ak {

    /* renamed from: a  reason: collision with root package name */
    private ba f751a;
    private Context b;
    private t c;
    private cd m;
    private boolean n = false;
    private du o;
    private List<String> p;

    public boolean n() {
        return true;
    }

    public long p() {
        return 0;
    }

    public bh(t tVar) {
        this.c = tVar;
        this.b = tVar.m();
        this.f751a = tVar.h();
        this.m = tVar.E();
        this.p = this.f751a.D();
    }

    public boolean b() {
        a(false);
        this.f751a.b(this.c);
        if (s()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public cq d() {
        return new cu(this.m.h((String) null)).a(h());
    }

    /* access modifiers changed from: package-private */
    public boolean s() {
        boolean z;
        boolean z2 = !this.f751a.a(this.m.a(0));
        String a2 = l.a(this.c.j().u());
        if (!z2 && !TextUtils.isEmpty(a2)) {
            if (a2.equals(this.m.m())) {
                if ((System.currentTimeMillis() - this.m.n()) / 1000 <= 86400) {
                    z2 = false;
                }
            }
            z2 = true;
        }
        if (z2) {
            return z2;
        }
        String d = ci.a().d();
        if (TextUtils.isEmpty(d)) {
            z = TextUtils.isEmpty(a(this.f751a));
        } else {
            z = TextUtils.equals(a(this.f751a), d);
        }
        return !z;
    }

    private static String a(ba baVar) {
        String c2 = baVar.c();
        String e = baVar.e();
        if (TextUtils.isEmpty(c2)) {
            return TextUtils.isEmpty(e) ? "" : e;
        }
        return c2;
    }

    /* access modifiers changed from: package-private */
    public void a(Uri.Builder builder) {
        builder.path("analytics/startup");
        builder.appendQueryParameter("deviceid", a(this.f751a));
        builder.appendQueryParameter("app_platform", this.f751a.m());
        builder.appendQueryParameter("protocol_version", this.f751a.f());
        builder.appendQueryParameter("analytics_sdk_version", this.f751a.h());
        builder.appendQueryParameter("analytics_sdk_version_name", this.f751a.g());
        builder.appendQueryParameter("model", this.f751a.p());
        builder.appendQueryParameter("manufacturer", this.f751a.o());
        builder.appendQueryParameter("os_version", this.f751a.q());
        builder.appendQueryParameter("screen_width", String.valueOf(this.f751a.s()));
        builder.appendQueryParameter("screen_height", String.valueOf(this.f751a.t()));
        builder.appendQueryParameter("screen_dpi", String.valueOf(this.f751a.u()));
        builder.appendQueryParameter("scalefactor", String.valueOf(this.f751a.v()));
        builder.appendQueryParameter("locale", this.f751a.w());
        builder.appendQueryParameter("device_type", this.f751a.G());
        builder.appendQueryParameter("query_hosts", InternalAvidAdSessionContext.AVID_API_LEVEL);
        builder.appendQueryParameter("features", bi.b("easy_collecting", "package_info", "socket", "permissions_collecting", "features_collecting"));
        builder.appendQueryParameter("browsers", "1");
        builder.appendQueryParameter("socket", "1");
        builder.appendQueryParameter("app_id", this.c.l().b());
        builder.appendQueryParameter("app_debuggable", this.f751a.N());
        Map<String, String> u = this.c.j().u();
        String v = this.c.j().v();
        if (TextUtils.isEmpty(v)) {
            v = this.m.c();
        }
        if (!bk.a((Map) u)) {
            builder.appendQueryParameter("distribution_customization", "1");
            a(builder, "clids_set", l.a(u));
            if (!TextUtils.isEmpty(v)) {
                builder.appendQueryParameter("install_referrer", v);
            }
        }
        a(builder, "uuid", this.f751a.b());
    }

    private static void a(Uri.Builder builder, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    public boolean c() {
        this.k = false;
        if (t()) {
            this.k = true;
        } else if (200 == this.h) {
            Map<String, String> u = this.c.j().u();
            bg.a a2 = bg.a(this.i);
            if (bg.a.C0031a.OK == a2.k()) {
                this.m.u(this.f751a.y());
                this.f751a.a(a2);
                Long a3 = bg.a(l());
                if (a3 != null) {
                    k.a().a(a3.longValue());
                }
                this.f751a.b(ci.a().c(this.b, this.f751a.c()));
                String str = "";
                if (a2.o() == null) {
                    g.a().a((Class<? extends i>) o.class);
                } else {
                    try {
                        str = a2.o().d();
                        g.a().b((i) new o(a2.o()));
                    } catch (JSONException unused) {
                    }
                }
                a(this.f751a, str);
                this.c.a(l.a(this.f751a.y()).equals(u));
                j.a(this.c.k(), this.f751a, a2);
                this.k = true;
            } else {
                this.o = du.PARSE;
            }
        }
        return this.k;
    }

    public void g() {
        this.o = du.NETWORK;
    }

    public void f() {
        if (!this.k) {
            if (this.o == null) {
                this.o = du.UNKNOWN;
            }
            j.a(this.c.k(), this.o);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00ce, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.yandex.metrica.impl.ba r5, java.lang.String r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.t()     // Catch:{ all -> 0x00cf }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r4)
            return
        L_0x0009:
            com.yandex.metrica.impl.ob.cd r0 = r4.m     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r5.b()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.j(r1)     // Catch:{ all -> 0x00cf }
            java.util.List r1 = r5.E()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.a((java.util.List<java.lang.String>) r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r5.C()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.l(r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r5.B()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.m(r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r5.A()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.n(r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r5.L()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.i(r1)     // Catch:{ all -> 0x00cf }
            boolean r1 = r5.H()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.a((boolean) r1)     // Catch:{ all -> 0x00cf }
            boolean r1 = r5.I()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.b((boolean) r1)     // Catch:{ all -> 0x00cf }
            boolean r1 = r5.J()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.c((boolean) r1)     // Catch:{ all -> 0x00cf }
            boolean r1 = r5.K()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.d((boolean) r1)     // Catch:{ all -> 0x00cf }
            r0.t(r6)     // Catch:{ all -> 0x00cf }
            java.lang.String r6 = r5.y()     // Catch:{ all -> 0x00cf }
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x00cf }
            if (r0 != 0) goto L_0x006d
            com.yandex.metrica.impl.ob.cd r0 = r4.m     // Catch:{ all -> 0x00cf }
            r0.p(r6)     // Catch:{ all -> 0x00cf }
        L_0x006d:
            com.yandex.metrica.impl.ob.cd r6 = r4.m     // Catch:{ all -> 0x00cf }
            r6.h()     // Catch:{ all -> 0x00cf }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00cf }
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            r4.a((long) r0)     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.co r6 = com.yandex.metrica.impl.ob.co.a()     // Catch:{ all -> 0x00cf }
            android.content.Context r0 = r4.b     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ba r1 = r4.f751a     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r1.b()     // Catch:{ all -> 0x00cf }
            java.lang.String r2 = r5.L()     // Catch:{ all -> 0x00cf }
            r6.a(r0, r1, r2)     // Catch:{ all -> 0x00cf }
            java.lang.String r6 = r5.c()     // Catch:{ all -> 0x00cf }
            boolean r6 = com.yandex.metrica.impl.bi.a((java.lang.String) r6)     // Catch:{ all -> 0x00cf }
            if (r6 != 0) goto L_0x00cd
            android.content.Intent r6 = new android.content.Intent     // Catch:{ all -> 0x00cf }
            java.lang.String r0 = "com.yandex.metrica.intent.action.SYNC"
            r6.<init>(r0)     // Catch:{ all -> 0x00cf }
            java.lang.String r0 = "CAUSE"
            java.lang.String r1 = "CAUSE_DEVICE_ID"
            r6.putExtra(r0, r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r0 = "SYNC_TO_PKG"
            com.yandex.metrica.impl.ob.t r1 = r4.c     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.r r1 = r1.l()     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r1.b()     // Catch:{ all -> 0x00cf }
            r6.putExtra(r0, r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r0 = "SYNC_DATA"
            java.lang.String r1 = r5.c()     // Catch:{ all -> 0x00cf }
            r6.putExtra(r0, r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r0 = "SYNC_DATA_2"
            java.lang.String r5 = r5.b()     // Catch:{ all -> 0x00cf }
            r6.putExtra(r0, r5)     // Catch:{ all -> 0x00cf }
            android.content.Context r5 = r4.b     // Catch:{ all -> 0x00cf }
            r5.sendBroadcast(r6)     // Catch:{ all -> 0x00cf }
        L_0x00cd:
            monitor-exit(r4)
            return
        L_0x00cf:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.bh.a(com.yandex.metrica.impl.ba, java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(long j) {
        this.m.b(j).h();
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(boolean z) {
        this.n = z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean t() {
        return this.n;
    }

    public boolean o() {
        return q() + 1 < this.p.size();
    }

    public void e() {
        super.e();
        Uri.Builder buildUpon = Uri.parse(this.p.get(q())).buildUpon();
        a(buildUpon);
        a(buildUpon.build().toString());
    }

    public String a() {
        return "Startup task for component: " + this.c.l().toString();
    }
}
