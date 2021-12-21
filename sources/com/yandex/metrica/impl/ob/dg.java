package com.yandex.metrica.impl.ob;

import android.content.Context;

public class dg extends dd {
    private static final dk c = new dk("SERVICE_API_LEVEL");
    private static final dk d = new dk("CLIENT_API_LEVEL");
    private dk e;
    private dk f;

    /* access modifiers changed from: protected */
    public String f() {
        return "_migrationpreferences";
    }

    public dg(Context context) {
        super(context, (String) null);
    }

    /* access modifiers changed from: protected */
    public void h() {
        super.h();
        this.e = new dk(c.a());
        this.f = new dk(d.a());
    }

    public int a() {
        return this.b.getInt(this.e.b(), -1);
    }

    public dg b() {
        h(this.e.b());
        return this;
    }

    public dg c() {
        h(this.f.b());
        return this;
    }
}
