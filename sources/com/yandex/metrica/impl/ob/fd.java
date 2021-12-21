package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.List;

public class fd {

    /* renamed from: a  reason: collision with root package name */
    private fj f899a;
    private boolean b;
    private boolean c;
    private long d;
    private String e;
    private List<X509Certificate> f;

    fd() {
        this.b = true;
        this.c = false;
        this.d = 86400000;
        this.e = "https://certificate.mobile.yandex.net/api/v1/pins";
    }

    public fd(fj fjVar) {
        this.b = true;
        this.c = false;
        this.d = 86400000;
        this.e = "https://certificate.mobile.yandex.net/api/v1/pins";
        this.f899a = fjVar;
    }

    public fd(fj fjVar, boolean z, boolean z2) {
        this(fjVar);
        this.b = z;
        this.c = z2;
    }

    public void a(String str, List<X509Certificate> list) {
        this.e = str;
        this.f = list;
    }

    /* access modifiers changed from: package-private */
    public long a() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public List<X509Certificate> c() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public fj d() {
        return this.f899a;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.b;
    }
}
