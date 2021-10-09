package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;

class ei implements er {

    /* renamed from: a  reason: collision with root package name */
    private final ek f879a;
    private final ek b;
    private final ek c;

    ei(ey eyVar) {
        this.f879a = new ek(eyVar.a());
        this.b = new eo(eyVar.b());
        this.c = new ek(eyVar.c());
    }

    public boolean a(X509Certificate[] x509CertificateArr) {
        return this.f879a.a(x509CertificateArr);
    }

    public boolean b(X509Certificate[] x509CertificateArr) {
        return this.b.a(x509CertificateArr);
    }

    public boolean c(X509Certificate[] x509CertificateArr) {
        return this.c.a(x509CertificateArr);
    }
}
