package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.Set;

public class eo extends ek {

    /* renamed from: a  reason: collision with root package name */
    private fb f884a;

    public eo(fb fbVar) {
        super(fbVar);
        this.f884a = fbVar;
    }

    public boolean a(X509Certificate[] x509CertificateArr) {
        Set<String> b = this.f884a.b();
        if (b.isEmpty()) {
            return false;
        }
        return b.contains(fg.a(x509CertificateArr[0]));
    }
}
