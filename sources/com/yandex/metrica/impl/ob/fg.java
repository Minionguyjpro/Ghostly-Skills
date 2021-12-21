package com.yandex.metrica.impl.ob;

import android.os.Build;
import android.util.Base64;
import com.mopub.common.Constants;
import com.yandex.metrica.impl.ob.ex;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;

class fg {
    static String a(X509Certificate x509Certificate) {
        try {
            return Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest(x509Certificate.getPublicKey().getEncoded()), 2);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    static fs a(List<X509Certificate> list) throws GeneralSecurityException, IOException {
        TrustManager[] trustManagerArr;
        fm fmVar;
        if (list == null || list.isEmpty()) {
            trustManagerArr = null;
        } else {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load((InputStream) null, (char[]) null);
            for (int i = 0; i < list.size(); i++) {
                instance.setCertificateEntry("ca" + i, list.get(i));
            }
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init(instance);
            trustManagerArr = instance2.getTrustManagers();
        }
        SSLContext instance3 = SSLContext.getInstance("TLS");
        instance3.init((KeyManager[]) null, trustManagerArr, (SecureRandom) null);
        if (Build.VERSION.SDK_INT >= 9) {
            fmVar = new fn(instance3.getSocketFactory());
        } else {
            ex.a aVar = new ex.a(instance3);
            PlainSocketFactory socketFactory = PlainSocketFactory.getSocketFactory();
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme(Constants.HTTP, socketFactory, 80));
            schemeRegistry.register(new Scheme(Constants.HTTPS, aVar, 443));
            fmVar = new fl(new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams));
        }
        return new fs(new fq(fmVar));
    }

    static boolean a(fd fdVar) {
        return !"https://certificate.mobile.yandex.net/api/v1/pins".equals(fdVar.b());
    }
}
