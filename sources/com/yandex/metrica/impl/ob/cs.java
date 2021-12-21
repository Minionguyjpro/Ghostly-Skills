package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

public class cs extends cr {
    cs(String str) {
        super(str);
    }

    public HttpURLConnection a() throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.a();
        httpsURLConnection.setSSLSocketFactory(co.a().b());
        return httpsURLConnection;
    }

    public boolean b() {
        return co.a().c();
    }
}
