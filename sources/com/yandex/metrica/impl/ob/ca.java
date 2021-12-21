package com.yandex.metrica.impl.ob;

import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.a;

public class ca extends cb {

    /* renamed from: a  reason: collision with root package name */
    private static final dk f814a = new dk("SESSION_SLEEP_START");
    private static final dk b = new dk("SESSION_ID");
    private static final dk c = new dk("SESSION_COUNTER_ID");
    private static final dk d = new dk("SESSION_INIT_TIME");
    private static final dk e = new dk("SESSION_IS_ALIVE_REPORT_NEEDED");
    private static final dk f = new dk("BG_SESSION_ID");
    private static final dk g = new dk("BG_SESSION_SLEEP_START");
    private static final dk h = new dk("BG_SESSION_COUNTER_ID");
    private static final dk i = new dk("BG_SESSION_INIT_TIME");
    private static final dk j = new dk("BG_SESSION_IS_ALIVE_REPORT_NEEDED");
    private static final dk k = new dk("COLLECT_INSTALLED_APPS");
    private static final dk l = new dk("IDENTITY_SEND_TIME");
    private static final dk m = new dk("PERMISSIONS_CHECK_TIME");
    private static final dk n = new dk("USER_INFO");
    private static final dk o = new dk("APP_ENVIRONMENT");
    private static final dk p = new dk("APP_ENVIRONMENT_REVISION");
    private static final dk q = new dk("LAST_MIGRATION_VERSION");
    private static final dk r = new dk("LAST_APP_VERSION_WITH_FEATURES");
    private static final dk s = new dk("APPLICATION_FEATURES");

    static {
        new dk("SESSION_ALIVE_TIME");
    }

    public ca(bq bqVar) {
        super(bqVar);
    }

    public long a(long j2) {
        return b(d.b(), j2);
    }

    public long b(long j2) {
        return b(i.b(), j2);
    }

    public long c(long j2) {
        return b(l.b(), j2);
    }

    public long d(long j2) {
        return b(m.b(), j2);
    }

    public int a(int i2) {
        return b(r.b(), i2);
    }

    public long e(long j2) {
        return b(b.b(), j2);
    }

    public long f(long j2) {
        return b(f.b(), j2);
    }

    public long g(long j2) {
        return b(c.b(), j2);
    }

    public long h(long j2) {
        return b(h.b(), j2);
    }

    public a.C0030a a() {
        a.C0030a aVar;
        synchronized (this) {
            aVar = new a.C0030a(b(o.b(), "{}"), b(p.b(), 0));
        }
        return aVar;
    }

    public long i(long j2) {
        return b(f814a.b(), j2);
    }

    public long j(long j2) {
        return b(g.b(), j2);
    }

    public String b() {
        return b(s.b(), "");
    }

    public boolean a(boolean z) {
        return b(e.b(), z);
    }

    public CounterConfiguration.a c() {
        return CounterConfiguration.a.a(Long.valueOf(b(k.b(), (long) CounterConfiguration.a.UNDEFINED.d)).intValue());
    }

    public String a(String str) {
        return b(n.b(), str);
    }

    public ca k(long j2) {
        return (ca) a(d.b(), j2);
    }

    public ca l(long j2) {
        return (ca) a(i.b(), j2);
    }

    public ca a(a.C0030a aVar) {
        synchronized (this) {
            a(o.b(), aVar.f702a);
            a(p.b(), aVar.b);
        }
        return this;
    }

    public ca m(long j2) {
        return (ca) a(l.b(), j2);
    }

    public ca n(long j2) {
        return (ca) a(m.b(), j2);
    }

    public ca o(long j2) {
        return (ca) a(b.b(), j2);
    }

    public ca p(long j2) {
        return (ca) a(f.b(), j2);
    }

    public ca q(long j2) {
        return (ca) a(c.b(), j2);
    }

    public ca r(long j2) {
        return (ca) a(h.b(), j2);
    }

    public ca s(long j2) {
        return (ca) a(f814a.b(), j2);
    }

    public ca t(long j2) {
        return (ca) a(g.b(), j2);
    }

    public ca a(CounterConfiguration.a aVar) {
        return (ca) a(k.b(), (long) aVar.d);
    }

    public ca b(String str) {
        return (ca) a(n.b(), str);
    }

    public ca b(boolean z) {
        return (ca) a(e.b(), z);
    }

    public long d() {
        return b(q.b(), 0);
    }

    public ca u(long j2) {
        return (ca) a(q.b(), j2);
    }

    public boolean c(boolean z) {
        return b(j.b(), z);
    }

    public ca d(boolean z) {
        return (ca) a(j.b(), z);
    }

    public ca b(int i2) {
        return (ca) a(r.b(), i2);
    }

    public ca c(String str) {
        return (ca) a(s.b(), str);
    }
}
