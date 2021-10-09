package com.tappx.a;

import com.tappx.a.f0;
import java.util.Map;
import java.util.TreeMap;

public abstract class d0<T> {

    /* renamed from: a  reason: collision with root package name */
    private f0.b<T> f403a;
    private f0.a b;
    private boolean c;
    private g0 d;
    private b e = b.NORMAL;

    public enum a {
        POST,
        GET,
        PUT,
        DELETE,
        HEAD,
        OPTIONS,
        TRACE,
        PATCH
    }

    public enum b {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    protected d0(f0.b<T> bVar, f0.a aVar) {
        this.f403a = bVar;
        this.b = aVar;
    }

    /* access modifiers changed from: protected */
    public abstract f0<T> a(c0 c0Var);

    public void a(f0.a aVar) {
        this.b = aVar;
    }

    public abstract byte[] a();

    public f0.a b() {
        return this.b;
    }

    public abstract Map<String, String> c();

    public abstract a d();

    public b e() {
        return this.e;
    }

    public g0 f() {
        return this.d;
    }

    public abstract String g();

    /* access modifiers changed from: protected */
    public Map<String, String> h() {
        return new TreeMap(String.CASE_INSENSITIVE_ORDER);
    }

    public boolean i() {
        return this.c;
    }

    public void a(g0 g0Var) {
        this.d = g0Var;
    }

    /* access modifiers changed from: protected */
    public void a(T t) {
        f0.b<T> bVar = this.f403a;
        if (bVar != null) {
            bVar.a(t);
        }
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a(b bVar) {
        this.e = bVar;
    }
}
