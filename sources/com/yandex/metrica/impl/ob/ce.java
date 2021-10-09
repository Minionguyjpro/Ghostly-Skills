package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.be;

class ce {

    /* renamed from: a  reason: collision with root package name */
    private final ch f818a;
    private final be.a b;

    enum a {
        THIS,
        OTHER,
        UNKNOWN
    }

    ce(be.a aVar, ch chVar) {
        this.f818a = chVar;
        this.b = aVar;
    }

    public String a() {
        return this.f818a.c();
    }

    public a a(cj cjVar) {
        return a.THIS;
    }

    public ch b() {
        return this.f818a;
    }

    public String toString() {
        return "Bid{mCredentials='" + this.f818a + '\'' + ", mDescriptor=" + this.b + '}';
    }

    public be.a c() {
        return this.b;
    }
}
