package com.yandex.metrica.impl;

abstract class i {

    /* renamed from: a  reason: collision with root package name */
    private final a f769a;

    public interface a {
        boolean a(Throwable th);
    }

    /* access modifiers changed from: package-private */
    public abstract void b(Throwable th);

    i(a aVar) {
        this.f769a = aVar;
    }

    /* access modifiers changed from: package-private */
    public void a(Throwable th) {
        if (this.f769a.a(th)) {
            b(th);
        }
    }
}
