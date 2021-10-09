package com.yandex.metrica.impl.ob;

import android.content.Context;

public class di extends dd {
    static final dk c = new dk("PREF_KEY_DEVICE_ID_");
    static final dk d = new dk("PREF_KEY_UID_");
    static final dk e = new dk("STARTUP_CLIDS_MATCH_WITH_APP_CLIDS_");
    static final dk f = new dk("PREF_KEY_PINNING_UPDATE_URL");
    private static final dk g = new dk("PREF_KEY_HOST_URL_");
    private static final dk h = new dk("PREF_KEY_REPORT_URL_");
    private static final dk i = new dk("PREF_KEY_GET_AD_URL");
    private static final dk j = new dk("PREF_KEY_REPORT_AD_URL");
    private static final dk k = new dk("PREF_KEY_STARTUP_OBTAIN_TIME_");
    private static final dk l = new dk("PREF_KEY_STARTUP_ENCODED_CLIDS_");
    private static final dk m = new dk("PREF_KEY_DISTRIBUTION_REFERRER_");
    private static final dk n = new dk("PREF_KEY_EASY_COLLECTING_ENABLED_");
    private dk o;
    private dk p;
    private dk q;
    private dk r;
    private dk s;
    private dk t;
    private dk u;
    private dk v;
    private dk w;
    private dk x;

    /* access modifiers changed from: protected */
    public String f() {
        return "_startupserviceinfopreferences";
    }

    public di(Context context) {
        this(context, (String) null);
    }

    public di(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public void h() {
        super.h();
        this.o = new dk(c.a());
        this.p = new dk(d.a(), j());
        this.q = new dk(g.a(), j());
        this.r = new dk(h.a(), j());
        this.s = new dk(i.a(), j());
        this.t = new dk(j.a(), j());
        this.u = new dk(k.a(), j());
        this.v = new dk(l.a(), j());
        this.w = new dk(m.a(), j());
        this.x = new dk(n.a(), j());
    }

    public long a(long j2) {
        return this.b.getLong(this.u.b(), j2);
    }

    public String a(String str) {
        return this.b.getString(this.o.b(), str);
    }

    public String b(String str) {
        return this.b.getString(this.p.b(), str);
    }

    public String c(String str) {
        return this.b.getString(this.q.b(), str);
    }

    public String d(String str) {
        return this.b.getString(this.v.b(), str);
    }

    public String e(String str) {
        return this.b.getString(this.r.b(), str);
    }

    public String f(String str) {
        return this.b.getString(this.s.b(), str);
    }

    public String g(String str) {
        return this.b.getString(this.t.b(), str);
    }

    public String a() {
        return this.b.getString(this.w.a(), (String) null);
    }

    public di i(String str) {
        return (di) a(this.p.b(), str);
    }

    public di j(String str) {
        return (di) a(this.o.b(), str);
    }

    public static void b(Context context) {
        dl.a(context, "_startupserviceinfopreferences").edit().remove(c.a()).apply();
    }

    public void b() {
        h(this.o.b()).h(this.p.b()).h(this.q.b()).h(this.r.b()).h(this.s.b()).h(this.t.b()).h(this.u.b()).h(this.x.b()).h(this.v.b()).h(this.w.a()).h(e.a()).h(f.a()).k();
    }
}
