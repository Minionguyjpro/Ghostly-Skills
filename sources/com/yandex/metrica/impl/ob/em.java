package com.yandex.metrica.impl.ob;

import java.io.IOException;

class em implements ey {

    /* renamed from: a  reason: collision with root package name */
    private final fb f882a;
    private final fb b;

    public em(fe feVar, String str) throws IOException {
        en enVar = new en(feVar.b(), Integer.toString(str.hashCode()));
        this.f882a = new fb(enVar, "LIB-BLACK");
        this.b = new fb(enVar, "LIB-TRUST");
    }

    public fb a() {
        return this.f882a;
    }

    public fb b() {
        throw new UnsupportedOperationException("white list isn't supported in custom container");
    }

    public fb c() {
        return this.b;
    }
}
