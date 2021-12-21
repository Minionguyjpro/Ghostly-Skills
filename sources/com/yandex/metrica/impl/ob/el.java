package com.yandex.metrica.impl.ob;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class el extends CertificateException {
    public el(ff ffVar) {
        super("There is not pinned certificates among chain " + a(ffVar.a()));
    }

    private static String a(X509Certificate[] x509CertificateArr) {
        StringBuilder sb = new StringBuilder();
        for (X509Certificate x509Certificate : x509CertificateArr) {
            sb.append("ISSUER=" + x509Certificate.getIssuerDN().toString() + "\n");
        }
        return sb.toString();
    }
}
