package com.yandex.metrica.impl.ob;

import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.impl.bi;
import java.util.List;
import java.util.Map;

public class dx {

    /* renamed from: a  reason: collision with root package name */
    private String f867a = this.j.a((String) null);
    private String b;
    private String c = this.j.c((String) null);
    private String d = this.j.d((String) null);
    private String e = this.j.n((String) null);
    private List<String> f = this.j.b();
    private long g = this.j.a(0);
    private String h = this.j.e((String) null);
    private String i = this.j.l((String) null);
    private final bz j;

    public enum a {
        IDENTIFIERS,
        URLS,
        ALL
    }

    public dx(bz bzVar, String str) {
        this.j = bzVar;
        this.b = bzVar.b((String) null);
        b(str);
        e();
    }

    private void b(String str) {
        if (bi.a(this.f867a) && !bi.a(str)) {
            this.f867a = str;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(Map<String, String> map) {
        b(map);
        c(map);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean a(a aVar) {
        if (a.ALL == aVar) {
            return h();
        } else if (a.IDENTIFIERS == aVar) {
            return f();
        } else if (a.URLS != aVar) {
            return false;
        } else {
            return g();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void b(Map<String, String> map) {
        if (!bi.a(this.f867a)) {
            map.put("yandex_mobile_metrica_uuid", this.f867a);
        }
        if (!bi.a(this.b)) {
            map.put("yandex_mobile_metrica_device_id", this.b);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void c(Map<String, String> map) {
        if (!bi.a(this.c)) {
            map.put("yandex_mobile_metrica_get_ad_url", this.c);
        }
        if (!bi.a(this.d)) {
            map.put("yandex_mobile_metrica_report_ad_url", this.d);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(Bundle bundle) {
        b(bundle);
        c(bundle);
        b(bundle.getLong("ServerTimeOffset"));
        String string = bundle.getString("Clids");
        if (!bi.a(string)) {
            this.h = string;
        }
        String string2 = bundle.getString("CookieBrowsers");
        if (!TextUtils.isEmpty(string2)) {
            this.i = string2;
        }
        e();
    }

    private void e() {
        this.j.f(this.f867a).g(this.b).h(this.c).i(this.d).d(this.g).j(this.h).m(this.i).o(this.e).h();
    }

    /* access modifiers changed from: package-private */
    public void a(long j2) {
        this.j.e(j2).h();
    }

    /* access modifiers changed from: package-private */
    public List<String> b() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void a(List<String> list) {
        this.f = list;
        this.j.a(list);
    }

    private synchronized boolean f() {
        return !bi.a(this.f867a, this.b);
    }

    private synchronized boolean g() {
        return !bi.a(this.c);
    }

    private synchronized boolean h() {
        return f() && g();
    }

    private synchronized void b(Bundle bundle) {
        b(bundle.getString("UuId"));
        String string = bundle.getString("DeviceId");
        if (!bi.a(string)) {
            a(string);
        }
    }

    private synchronized void c(Bundle bundle) {
        String string = bundle.getString("AdUrlGet");
        if (!TextUtils.isEmpty(string)) {
            c(string);
        }
        String string2 = bundle.getString("AdUrlReport");
        if (!TextUtils.isEmpty(string2)) {
            d(string2);
        }
        String string3 = bundle.getString("BindIdUrl");
        if (!TextUtils.isEmpty(string3)) {
            e(string3);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(String str) {
        this.b = str;
    }

    private synchronized void c(String str) {
        this.c = str;
    }

    private synchronized void d(String str) {
        this.d = str;
    }

    private synchronized void e(String str) {
        this.e = str;
    }

    private synchronized void b(long j2) {
        this.g = j2;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return this.f867a;
    }

    /* access modifiers changed from: package-private */
    public String d() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - this.j.b(0);
        return currentTimeMillis > 86400 || currentTimeMillis < 0;
    }
}
