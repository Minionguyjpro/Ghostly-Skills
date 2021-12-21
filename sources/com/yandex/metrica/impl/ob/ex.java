package com.yandex.metrica.impl.ob;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class ex {
    public static X509Certificate a(InputStream inputStream) {
        BufferedInputStream bufferedInputStream;
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            bufferedInputStream = new BufferedInputStream(inputStream);
            X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(bufferedInputStream);
            bufferedInputStream.close();
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
            return x509Certificate;
        } catch (Exception e) {
            try {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            bufferedInputStream.close();
            throw th2;
        }
    }

    public static X509Certificate a(String str) {
        try {
            return a((InputStream) new ByteArrayInputStream(str.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class a extends SSLSocketFactory {

        /* renamed from: a  reason: collision with root package name */
        private final SSLContext f892a;

        public a(SSLContext sSLContext) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
            super((KeyStore) null);
            this.f892a = sSLContext;
            setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return this.f892a.getSocketFactory().createSocket(socket, str, i, z);
        }

        public Socket createSocket() throws IOException {
            return this.f892a.getSocketFactory().createSocket();
        }
    }
}
