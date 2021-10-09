package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.util.Map;

public class de extends dd {
    private static final dk c = new dk("UUID");
    private static final dk d = new dk("DEVICEID");
    private static final dk e = new dk("DEVICEID_2");
    private static final dk f = new dk("DEVICEID_3");
    private static final dk g = new dk("AD_URL_GET");
    private static final dk h = new dk("AD_URL_REPORT");
    private static final dk i = new dk("HOST_URL");
    private static final dk j = new dk("SERVER_TIME_OFFSET");
    private static final dk k = new dk("STARTUP_REQUEST_TIME");
    private static final dk l = new dk("CLIDS");
    private dk m;
    private dk n;
    private dk o;
    private dk p;
    private dk q;
    private dk r;
    private dk s;
    private dk t;
    private dk u;

    /* access modifiers changed from: protected */
    public String f() {
        return "_startupinfopreferences";
    }

    static {
        new dk("UUID_SOURCE");
    }

    public de(Context context) {
        super(context, (String) null);
    }

    /* access modifiers changed from: protected */
    public void h() {
        super.h();
        this.m = new dk(c.a());
        this.n = new dk(d.a());
        this.o = new dk(e.a());
        this.p = new dk(f.a());
        this.q = new dk(g.a());
        this.r = new dk(h.a());
        new dk(i.a());
        this.s = new dk(j.a());
        this.t = new dk(k.a());
        this.u = new dk(l.a());
    }

    public String a(String str) {
        return this.b.getString(this.m.b(), str);
    }

    public String b(String str) {
        return this.b.getString(this.p.b(), str);
    }

    public String a() {
        return this.b.getString(this.o.b(), this.b.getString(this.n.b(), ""));
    }

    public String c(String str) {
        return this.b.getString(this.q.b(), str);
    }

    public String d(String str) {
        return this.b.getString(this.r.b(), str);
    }

    public long a(long j2) {
        return this.b.getLong(this.s.a(), j2);
    }

    public long b(long j2) {
        return this.b.getLong(this.t.b(), j2);
    }

    public String e(String str) {
        return this.b.getString(this.u.b(), str);
    }

    public de b() {
        return (de) i();
    }

    public Map<String, ?> c() {
        return this.b.getAll();
    }
}
