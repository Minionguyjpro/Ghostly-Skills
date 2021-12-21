package com.yandex.metrica.impl;

import android.content.ContentValues;
import com.yandex.metrica.impl.utils.g;
import java.util.LinkedHashMap;
import java.util.Map;

abstract class l extends ak {

    /* renamed from: a  reason: collision with root package name */
    static final ContentValues f775a = new ContentValues();
    final Map<String, String> b = new LinkedHashMap();
    final ba c = new ba();

    l() {
    }

    /* access modifiers changed from: package-private */
    public l a(ContentValues contentValues) {
        this.b.clear();
        for (Map.Entry next : contentValues.valueSet()) {
            this.b.put(next.getKey(), next.getValue().toString());
        }
        b(contentValues);
        return this;
    }

    /* access modifiers changed from: package-private */
    public void b(ContentValues contentValues) {
        String asString = contentValues.getAsString("report_request_parameters");
        if (!bi.a(asString)) {
            try {
                g.a aVar = new g.a(asString);
                this.c.b(aVar.a("dId"));
                this.c.a(aVar.a("uId"));
                this.c.e(aVar.a("kitVer"));
                this.c.f(aVar.a("clientKitVer"));
                this.c.g(aVar.a("kitBuildNumber"));
                this.c.h(aVar.a("kitBuildType"));
                this.c.k(aVar.a("appVer"));
                this.c.s(aVar.optString("app_debuggable", "0"));
                this.c.m(aVar.a("appBuild"));
                this.c.i(aVar.a("osVer"));
                this.c.a(aVar.optInt("osApiLev", -1));
                this.c.j(aVar.a("lang"));
                this.c.q(aVar.a("root"));
            } catch (Exception unused) {
            }
        }
    }

    public String a() {
        return super.a() + " [" + this.b.toString() + "]";
    }
}
