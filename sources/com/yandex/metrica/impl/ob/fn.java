package com.yandex.metrica.impl.ob;

import com.mopub.common.Constants;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class fn implements fm {

    /* renamed from: a  reason: collision with root package name */
    private final SSLSocketFactory f907a;

    public fn() {
        this((SSLSocketFactory) null);
    }

    public fn(SSLSocketFactory sSLSocketFactory) {
        this.f907a = sSLSocketFactory;
    }

    public HttpResponse a(fu<?> fuVar) throws IOException, fr {
        SSLSocketFactory sSLSocketFactory;
        String a2 = fuVar.a();
        HashMap hashMap = new HashMap();
        hashMap.putAll(fuVar.b());
        URL url = new URL(a2);
        HttpURLConnection a3 = a(url);
        int n = fuVar.n();
        a3.setConnectTimeout(n);
        a3.setReadTimeout(n);
        a3.setUseCaches(false);
        a3.setDoInput(true);
        if (Constants.HTTPS.equals(url.getProtocol()) && (sSLSocketFactory = this.f907a) != null) {
            ((HttpsURLConnection) a3).setSSLSocketFactory(sSLSocketFactory);
        }
        for (String str : hashMap.keySet()) {
            a3.addRequestProperty(str, (String) hashMap.get(str));
        }
        switch (fuVar.d()) {
            case -1:
                byte[] j = fuVar.j();
                if (j != null) {
                    a3.setDoOutput(true);
                    a3.setRequestMethod("POST");
                    a3.addRequestProperty("Content-Type", fuVar.i());
                    DataOutputStream dataOutputStream = new DataOutputStream(a3.getOutputStream());
                    dataOutputStream.write(j);
                    dataOutputStream.close();
                    break;
                }
                break;
            case 0:
                a3.setRequestMethod("GET");
                break;
            case 1:
                a3.setRequestMethod("POST");
                a(a3, fuVar);
                break;
            case 2:
                a3.setRequestMethod("PUT");
                a(a3, fuVar);
                break;
            case 3:
                a3.setRequestMethod("DELETE");
                break;
            case 4:
                a3.setRequestMethod("HEAD");
                break;
            case 5:
                a3.setRequestMethod("OPTIONS");
                break;
            case 6:
                a3.setRequestMethod("TRACE");
                break;
            case 7:
                a3.setRequestMethod("PATCH");
                a(a3, fuVar);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (a3.getResponseCode() != -1) {
            BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, a3.getResponseCode(), a3.getResponseMessage()));
            basicHttpResponse.setEntity(a(a3));
            for (Map.Entry entry : a3.getHeaderFields().entrySet()) {
                if (entry.getKey() != null) {
                    basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
                }
            }
            return basicHttpResponse;
        }
        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
    }

    private static HttpEntity a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException unused) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection a(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    private static void a(HttpURLConnection httpURLConnection, fu<?> fuVar) throws IOException, fr {
        byte[] c = fuVar.c();
        if (c != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", fuVar.m());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(c);
            dataOutputStream.close();
        }
    }
}
