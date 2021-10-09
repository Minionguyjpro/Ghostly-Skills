package com.tappx.a;

import com.tappx.a.d0;
import com.tappx.a.f0;
import java.util.Map;
import java.util.TreeMap;

class v2 extends d0<Void> {
    private final String f;

    protected v2(String str, f0.b<Void> bVar, f0.a aVar) {
        super(bVar, aVar);
        this.f = str;
        a(new g0(10000, 0, 0.0f).a(false));
        a(d0.b.LOW);
    }

    public byte[] a() {
        return new byte[0];
    }

    public Map<String, String> c() {
        return new TreeMap(String.CASE_INSENSITIVE_ORDER);
    }

    public d0.a d() {
        return d0.a.GET;
    }

    public String g() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public f0<Void> a(c0 c0Var) {
        return f0.a(null);
    }
}
