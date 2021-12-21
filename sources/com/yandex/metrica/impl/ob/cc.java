package com.yandex.metrica.impl.ob;

import com.yandex.metrica.CounterConfiguration;

public class cc extends cb {

    /* renamed from: a  reason: collision with root package name */
    static final dk f816a = new dk("LOCATION_TRACKING_ENABLED");
    static final dk b = new dk("COLLECT_INSTALLED_APPS");
    static final dk c = new dk("REFERRER");
    static final dk d = new dk("PREF_KEY_OFFSET");
    private static final dk e = new dk("LAST_MIGRATION_VERSION");

    public cc(bq bqVar) {
        super(bqVar);
    }

    public CounterConfiguration.a a() {
        return CounterConfiguration.a.a(Long.valueOf(b(b.b(), (long) CounterConfiguration.a.UNDEFINED.d)).intValue());
    }

    public String a(String str) {
        return b(c.b(), str);
    }

    public cc a(CounterConfiguration.a aVar) {
        return (cc) a(b.b(), (long) aVar.d);
    }

    public cc b(String str) {
        return (cc) a(c.b(), str);
    }

    public cc b() {
        return (cc) r(c.b());
    }

    public int a(int i) {
        return b(e.b(), i);
    }

    public cc b(int i) {
        return (cc) a(e.b(), i);
    }

    public void a(boolean z) {
        a(f816a.b(), z).h();
    }

    public boolean c() {
        return b(f816a.b(), false);
    }

    public long c(int i) {
        return b(d.b(), (long) i);
    }

    public cc a(long j) {
        return (cc) a(d.b(), j);
    }
}
