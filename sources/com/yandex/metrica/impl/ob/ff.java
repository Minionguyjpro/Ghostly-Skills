package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import java.util.concurrent.atomic.AtomicInteger;

public class ff {

    /* renamed from: a  reason: collision with root package name */
    private static final AtomicInteger f901a = new AtomicInteger(0);
    private X509Certificate[] b;
    private boolean c = false;

    ff(X509Certificate[] x509CertificateArr) {
        f901a.incrementAndGet();
        this.b = x509CertificateArr;
    }

    public X509Certificate[] a() {
        return (X509Certificate[]) this.b.clone();
    }

    public boolean b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.c = true;
    }
}
