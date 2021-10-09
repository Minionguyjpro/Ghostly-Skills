package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import com.yandex.metrica.impl.utils.g;
import java.util.List;

public class bz extends cb {

    /* renamed from: a  reason: collision with root package name */
    static final dk f812a = new dk("UUID");
    static final dk b = new dk("DEVICE_ID_POSSIBLE");
    static final dk c = new dk("DEVICE_ID");
    static final dk d = new dk("AD_URL_GET");
    static final dk e = new dk("AD_URL_REPORT");
    static final dk f = new dk("CUSTOM_HOSTS");
    static final dk g = new dk("SERVER_TIME_OFFSET");
    static final dk h = new dk("STARTUP_REQUEST_TIME");
    static final dk i = new dk("CLIDS");
    static final dk j = new dk("COOKIE_BROWSERS");
    static final dk k = new dk("BIND_ID_URL");
    static final dk l = new dk("REFERRER");
    static final dk m = new dk("DEFERRED_DEEP_LINK_WAS_CHECKED");
    static final dk n = new dk("API_LEVEL");

    public bz(bq bqVar) {
        super(bqVar);
    }

    public String a(String str) {
        return b(f812a.b(), str);
    }

    public String b(String str) {
        return b(c.b(), str);
    }

    public String a() {
        return b(b.b(), "");
    }

    public String c(String str) {
        return b(d.b(), str);
    }

    public String d(String str) {
        return b(e.b(), str);
    }

    public List<String> b() {
        String b2 = b(f.b(), (String) null);
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        return g.b(b2);
    }

    public long a(long j2) {
        return b(g.a(), j2);
    }

    public long b(long j2) {
        return b(h.b(), j2);
    }

    public String e(String str) {
        return b(i.b(), str);
    }

    public long c(long j2) {
        return b(n.b(), j2);
    }

    public String c() {
        return b(l.b(), (String) null);
    }

    public boolean d() {
        return b(m.b(), false);
    }

    public bz f(String str) {
        return (bz) a(f812a.b(), str);
    }

    public bz g(String str) {
        return (bz) a(c.b(), str);
    }

    public bz h(String str) {
        return (bz) a(d.b(), str);
    }

    public bz a(List<String> list) {
        return (bz) a(f.b(), g.a(list));
    }

    public bz i(String str) {
        return (bz) a(e.b(), str);
    }

    public bz d(long j2) {
        return (bz) a(g.b(), j2);
    }

    public bz e(long j2) {
        return (bz) a(h.b(), j2);
    }

    public bz j(String str) {
        return (bz) a(i.b(), str);
    }

    public bz f(long j2) {
        return (bz) a(n.b(), j2);
    }

    public bz k(String str) {
        return (bz) a(b.b(), str);
    }

    public String l(String str) {
        return b(j.b(), str);
    }

    public bz m(String str) {
        return (bz) a(j.b(), str);
    }

    public String n(String str) {
        return b(k.b(), str);
    }

    public bz o(String str) {
        return (bz) a(k.b(), str);
    }

    public bz p(String str) {
        return (bz) a(l.b(), str);
    }
}
