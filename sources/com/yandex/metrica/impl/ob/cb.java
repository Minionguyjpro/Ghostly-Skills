package com.yandex.metrica.impl.ob;

public abstract class cb {

    /* renamed from: a  reason: collision with root package name */
    private final bq f815a;
    private final String b;

    /* access modifiers changed from: protected */
    public void f() {
    }

    static {
        cb.class.getSimpleName();
    }

    public cb(bq bqVar) {
        this(bqVar, (String) null);
    }

    public cb(bq bqVar, String str) {
        this.f815a = bqVar;
        this.b = str;
        f();
    }

    public String g() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public dk q(String str) {
        return new dk(str, g());
    }

    /* access modifiers changed from: protected */
    public <T extends cb> T a(String str, String str2) {
        synchronized (this) {
            this.f815a.b(str, str2);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public <T extends cb> T a(String str, long j) {
        synchronized (this) {
            this.f815a.b(str, j);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public <T extends cb> T a(String str, int i) {
        synchronized (this) {
            this.f815a.b(str, i);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public <T extends cb> T a(String str, boolean z) {
        synchronized (this) {
            this.f815a.b(str, z);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public <T extends cb> T r(String str) {
        synchronized (this) {
            this.f815a.a(str);
        }
        return this;
    }

    public void h() {
        synchronized (this) {
            this.f815a.b();
        }
    }

    /* access modifiers changed from: package-private */
    public long b(String str, long j) {
        return this.f815a.a(str, j);
    }

    /* access modifiers changed from: package-private */
    public int b(String str, int i) {
        return this.f815a.a(str, i);
    }

    /* access modifiers changed from: package-private */
    public String b(String str, String str2) {
        return this.f815a.a(str, str2);
    }

    /* access modifiers changed from: package-private */
    public boolean b(String str, boolean z) {
        return this.f815a.a(str, z);
    }
}
