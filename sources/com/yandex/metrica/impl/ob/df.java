package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.a;

public class df extends dd {
    public static final dk c = new dk("APP_ENVIRONMENT");
    public static final dk d = new dk("APP_ENVIRONMENT_REVISION");
    private static final dk e = new dk("SESSION_SLEEP_START_");
    private static final dk f = new dk("SESSION_ID_");
    private static final dk g = new dk("SESSION_COUNTER_ID_");
    private static final dk h = new dk("SESSION_INIT_TIME_");
    private static final dk i = new dk("SESSION_ALIVE_TIME_");
    private static final dk j = new dk("SESSION_IS_ALIVE_REPORT_NEEDED_");
    private static final dk k = new dk("BG_SESSION_ID_");
    private static final dk l = new dk("BG_SESSION_SLEEP_START_");
    private static final dk m = new dk("BG_SESSION_COUNTER_ID_");
    private static final dk n = new dk("BG_SESSION_INIT_TIME_");
    private static final dk o = new dk("COLLECT_INSTALLED_APPS_");
    private static final dk p = new dk("IDENTITY_SEND_TIME_");
    private static final dk q = new dk("USER_INFO_");
    private static final dk r = new dk("REFERRER_");
    private static final dk s = new dk("APP_ENVIRONMENT_");
    private static final dk t = new dk("APP_ENVIRONMENT_REVISION_");
    private dk A;
    private dk B;
    private dk C;
    private dk D;
    private dk E;
    private dk F;
    private dk G;
    private dk H;
    private dk I;
    private dk J;
    private dk u;
    private dk v;
    private dk w;
    private dk x;
    private dk y;
    private dk z;

    /* access modifiers changed from: protected */
    public String f() {
        return "_boundentrypreferences";
    }

    public df(Context context, String str) {
        super(context, str);
        d();
        a(-1);
        b(0);
        c(0);
    }

    /* access modifiers changed from: protected */
    public void h() {
        super.h();
        this.u = new dk(e.a(), j());
        this.v = new dk(f.a(), j());
        this.w = new dk(g.a(), j());
        this.x = new dk(h.a(), j());
        this.y = new dk(i.a(), j());
        this.z = new dk(j.a(), j());
        this.A = new dk(k.a(), j());
        this.B = new dk(l.a(), j());
        this.C = new dk(m.a(), j());
        this.D = new dk(n.a(), j());
        this.E = new dk(p.a(), j());
        this.F = new dk(o.a(), j());
        this.G = new dk(q.a(), j());
        this.H = new dk(r.a(), j());
        this.I = new dk(s.a(), j());
        this.J = new dk(t.a(), j());
    }

    public long a(long j2) {
        return a(this.x.b(), j2);
    }

    public long b(long j2) {
        return a(this.D.b(), j2);
    }

    public long c(long j2) {
        return a(this.E.b(), j2);
    }

    public long d(long j2) {
        return a(this.v.b(), j2);
    }

    public long e(long j2) {
        return a(this.A.b(), j2);
    }

    public long f(long j2) {
        return a(this.w.b(), j2);
    }

    private long a(String str, long j2) {
        return this.b.getLong(str, j2);
    }

    public long g(long j2) {
        return a(this.C.b(), j2);
    }

    public a.C0030a a() {
        synchronized (this) {
            if (!this.b.contains(this.I.b()) || !this.b.contains(this.J.b())) {
                return null;
            }
            a.C0030a aVar = new a.C0030a(this.b.getString(this.I.b(), "{}"), this.b.getLong(this.J.b(), 0));
            return aVar;
        }
    }

    public long h(long j2) {
        return a(this.u.b(), j2);
    }

    public long i(long j2) {
        return a(this.B.b(), j2);
    }

    public Boolean a(boolean z2) {
        return Boolean.valueOf(this.b.getBoolean(this.z.b(), z2));
    }

    public CounterConfiguration.a b() {
        return CounterConfiguration.a.a(this.b.getInt(this.F.b(), CounterConfiguration.a.UNDEFINED.d));
    }

    public String a(String str) {
        return this.b.getString(this.G.b(), str);
    }

    public String b(String str) {
        return this.b.getString(this.H.b(), str);
    }

    public df a(a.C0030a aVar) {
        synchronized (this) {
            a(this.I.b(), aVar.f702a);
            a(this.J.b(), Long.valueOf(aVar.b));
        }
        return this;
    }

    public df c() {
        return (df) h(this.H.b());
    }

    public void a(int i2) {
        dl.a(this.b, this.y.b(), i2);
    }

    public void b(int i2) {
        dl.a(this.b, this.u.b(), i2);
    }

    public void c(int i2) {
        dl.a(this.b, this.w.b(), i2);
    }

    public void d() {
        SharedPreferences sharedPreferences = this.b;
        String b = this.F.b();
        if (sharedPreferences != null && sharedPreferences.contains(b)) {
            try {
                sharedPreferences.getBoolean(b, false);
                sharedPreferences.edit().remove(b).putInt(b, CounterConfiguration.a.UNDEFINED.d).apply();
            } catch (ClassCastException unused) {
            }
        }
    }

    public df e() {
        return (df) h(this.F.b());
    }

    public boolean g() {
        return this.b.contains(this.x.b()) || this.b.contains(this.y.b()) || this.b.contains(this.z.b()) || this.b.contains(this.u.b()) || this.b.contains(this.v.b()) || this.b.contains(this.w.b()) || this.b.contains(this.D.b()) || this.b.contains(this.B.b()) || this.b.contains(this.A.b()) || this.b.contains(this.C.b()) || this.b.contains(this.I.b()) || this.b.contains(this.F.b()) || this.b.contains(this.G.b()) || this.b.contains(this.H.b()) || this.b.contains(this.E.b());
    }

    public void l() {
        this.b.edit().remove(this.D.b()).remove(this.C.b()).remove(this.A.b()).remove(this.B.b()).remove(this.x.b()).remove(this.w.b()).remove(this.v.b()).remove(this.u.b()).remove(this.z.b()).remove(this.y.b()).remove(this.G.b()).remove(this.F.b()).remove(this.I.b()).remove(this.J.b()).remove(this.H.b()).remove(this.E.b()).apply();
    }
}
