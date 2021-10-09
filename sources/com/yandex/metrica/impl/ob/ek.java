package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.Set;

class ek {

    /* renamed from: a  reason: collision with root package name */
    private fb f881a;

    public ek(fb fbVar) {
        this.f881a = fbVar;
    }

    public boolean a(X509Certificate[] x509CertificateArr) {
        Set<String> b = this.f881a.b();
        if (b.isEmpty()) {
            return false;
        }
        for (X509Certificate a2 : x509CertificateArr) {
            if (b.contains(fg.a(a2))) {
                return true;
            }
        }
        return false;
    }
}
