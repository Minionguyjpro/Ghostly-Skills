package com.yandex.metrica.impl.ob;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;

public abstract class fu<T> {

    /* renamed from: a  reason: collision with root package name */
    private final int f916a;
    private final String b;
    private fw c;
    private volatile b<T> d;
    private volatile a e;

    public interface a {
        void a(fr frVar);
    }

    public interface b<T> {
        void a(T t);
    }

    /* access modifiers changed from: protected */
    public abstract T b(ft ftVar) throws fr;

    /* access modifiers changed from: protected */
    public Map<String, String> k() throws fr {
        return null;
    }

    /* access modifiers changed from: protected */
    public String l() {
        return "UTF-8";
    }

    public fu(int i, String str) {
        this.f916a = i;
        this.b = str;
        a(new fw());
    }

    public int d() {
        return this.f916a;
    }

    public fu<?> a(fw fwVar) {
        this.c = fwVar;
        return this;
    }

    public String a() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public b<T> e() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void a(b<T> bVar) {
        this.d = bVar;
    }

    /* access modifiers changed from: protected */
    public a f() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void a(a aVar) {
        this.e = aVar;
    }

    public Map<String, String> b() throws fr {
        return Collections.emptyMap();
    }

    /* access modifiers changed from: protected */
    public Map<String, String> g() throws fr {
        return k();
    }

    /* access modifiers changed from: protected */
    public String h() {
        return l();
    }

    public String i() {
        return m();
    }

    public byte[] j() throws fr {
        Map<String, String> g = g();
        if (g == null || g.size() <= 0) {
            return null;
        }
        return a(g, h());
    }

    public String m() {
        return "application/x-www-form-urlencoded; charset=" + l();
    }

    public byte[] c() throws fr {
        Map<String, String> k = k();
        if (k == null || k.size() <= 0) {
            return null;
        }
        return a(k, l());
    }

    private static byte[] a(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : map.entrySet()) {
                sb.append(URLEncoder.encode((String) next.getKey(), str));
                sb.append('=');
                sb.append(URLEncoder.encode((String) next.getValue(), str));
                sb.append('&');
            }
            return sb.toString().getBytes(str);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Encoding not supported: " + str, e2);
        }
    }

    public final int n() {
        return this.c.a();
    }

    public fw o() {
        return this.c;
    }
}
