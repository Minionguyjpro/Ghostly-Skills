package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;

public abstract class v<T> {

    /* renamed from: a  reason: collision with root package name */
    private final aa<T> f940a;

    /* access modifiers changed from: protected */
    public abstract boolean a(h hVar, x<T> xVar);

    protected v(aa<T> aaVar) {
        this.f940a = aaVar;
    }

    public boolean a(h hVar) {
        return a(hVar, b(hVar));
    }

    /* access modifiers changed from: package-private */
    public x<T> b(h hVar) {
        return this.f940a.a(hVar.c());
    }
}
