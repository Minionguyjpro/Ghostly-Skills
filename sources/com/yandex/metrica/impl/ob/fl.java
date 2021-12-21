package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class fl implements fm {

    /* renamed from: a  reason: collision with root package name */
    protected final HttpClient f906a;

    public fl(HttpClient httpClient) {
        this.f906a = httpClient;
    }

    private static void a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String next : map.keySet()) {
            httpUriRequest.setHeader(next, map.get(next));
        }
    }

    private static void a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, fu<?> fuVar) throws fr {
        byte[] c = fuVar.c();
        if (c != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(c));
        }
    }

    public static final class a extends HttpEntityEnclosingRequestBase {
        public String getMethod() {
            return "PATCH";
        }

        public a() {
        }

        public a(String str) {
            setURI(URI.create(str));
        }
    }

    public HttpResponse a(fu<?> fuVar) throws IOException, fr {
        a aVar;
        switch (fuVar.d()) {
            case -1:
                byte[] j = fuVar.j();
                if (j == null) {
                    aVar = new HttpGet(fuVar.a());
                    break;
                } else {
                    a httpPost = new HttpPost(fuVar.a());
                    httpPost.addHeader("Content-Type", fuVar.i());
                    httpPost.setEntity(new ByteArrayEntity(j));
                    aVar = httpPost;
                    break;
                }
            case 0:
                aVar = new HttpGet(fuVar.a());
                break;
            case 1:
                aVar = new HttpPost(fuVar.a());
                aVar.addHeader("Content-Type", fuVar.m());
                a((HttpEntityEnclosingRequestBase) aVar, fuVar);
                break;
            case 2:
                aVar = new HttpPut(fuVar.a());
                aVar.addHeader("Content-Type", fuVar.m());
                a((HttpEntityEnclosingRequestBase) aVar, fuVar);
                break;
            case 3:
                aVar = new HttpDelete(fuVar.a());
                break;
            case 4:
                aVar = new HttpHead(fuVar.a());
                break;
            case 5:
                aVar = new HttpOptions(fuVar.a());
                break;
            case 6:
                aVar = new HttpTrace(fuVar.a());
                break;
            case 7:
                aVar = new a(fuVar.a());
                aVar.addHeader("Content-Type", fuVar.m());
                a((HttpEntityEnclosingRequestBase) aVar, fuVar);
                break;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
        a((HttpUriRequest) aVar, fuVar.b());
        HttpParams params = aVar.getParams();
        int n = fuVar.n();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, n);
        return this.f906a.execute(aVar);
    }
}
