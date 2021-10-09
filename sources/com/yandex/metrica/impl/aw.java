package com.yandex.metrica.impl;

import android.os.Bundle;
import android.os.ResultReceiver;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.ob.dw;
import com.yandex.metrica.impl.utils.f;
import java.util.HashMap;

class aw {

    /* renamed from: a  reason: collision with root package name */
    static final HashMap<String, String> f733a = new HashMap<String, String>() {
        {
            put("20799a27-fa80-4b36-b2db-0f8141f24180", "13");
            put("01528cc0-dd34-494d-9218-24af1317e1ee", "17233");
            put("4e610cd2-753f-4bfc-9b05-772ce8905c5e", "21952");
            put("67bb016b-be40-4c08-a190-96a3f3b503d3", "22675");
            put("e4250327-8d3c-4d35-b9e8-3c1720a64b91", "22678");
            put("6c5f504e-8928-47b5-bfb5-73af8d8bf4b4", "30404");
            put("7d962ba4-a392-449a-a02d-6c5be5613928", "30407");
        }
    };
    protected final CounterConfiguration b = new CounterConfiguration();
    protected o c;
    protected an d;
    private q e = new q();

    protected aw() {
    }

    /* access modifiers changed from: package-private */
    public void a(f.a aVar) {
        this.c = new o(aVar);
    }

    /* access modifiers changed from: package-private */
    public CounterConfiguration b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public Bundle c() {
        return this.b.F();
    }

    /* access modifiers changed from: package-private */
    public void a(dw dwVar) {
        b(dwVar);
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.e.b();
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.e.a();
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.e.c();
    }

    /* access modifiers changed from: package-private */
    public void b(dw dwVar) {
        if (dwVar != null) {
            this.b.d(dwVar.a());
            this.b.e(dwVar.c());
            this.b.f(dwVar.b());
        }
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        this.b.a((ResultReceiver) jVar);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2) {
        this.c.a(str, str2);
    }

    /* access modifiers changed from: package-private */
    public String f() {
        return this.c.a();
    }

    /* access modifiers changed from: package-private */
    public an g() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public void a(an anVar) {
        this.d = anVar;
    }
}
