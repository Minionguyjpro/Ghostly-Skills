package com.yandex.metrica.impl;

import android.content.Context;
import android.os.Handler;
import com.yandex.metrica.b;
import com.yandex.metrica.e;
import com.yandex.metrica.impl.ob.dw;
import java.util.HashMap;
import java.util.Map;

class ax {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f734a;
    /* access modifiers changed from: private */
    public ay b;
    /* access modifiers changed from: private */
    public j c;
    /* access modifiers changed from: private */
    public Handler d;
    /* access modifiers changed from: private */
    public dw e;
    private Map<String, b> f;

    /* synthetic */ ax(byte b2) {
        this();
    }

    private ax() {
        this.f = new HashMap();
    }

    /* access modifiers changed from: package-private */
    public z a(e eVar, boolean z) {
        if (!this.f.containsKey(eVar.getApiKey())) {
            z zVar = new z(this.f734a, eVar, this.b);
            a((b) zVar);
            zVar.a(eVar, z);
            zVar.a();
            this.b.a(zVar);
            this.f.put(eVar.getApiKey(), zVar);
            return zVar;
        }
        throw new IllegalArgumentException(String.format("Failed to activate AppMetrica with provided API Key. API Key %s has already been used by another reporter.", new Object[]{eVar.getApiKey()}));
    }

    /* access modifiers changed from: package-private */
    public synchronized b a(String str) {
        aa aaVar;
        b bVar = this.f.get(str);
        aaVar = bVar;
        if (bVar == null) {
            aa aaVar2 = new aa(this.f734a, aw.f733a.get(str), str, this.b);
            a((b) aaVar2);
            aaVar2.a();
            this.f.put(str, aaVar2);
            aaVar = aaVar2;
        }
        return aaVar;
    }

    private void a(b bVar) {
        bVar.a(new w(this.d, bVar));
        bVar.a(this.c);
        bVar.a(this.e);
    }

    static class a {

        /* renamed from: a  reason: collision with root package name */
        ax f735a = new ax((byte) 0);

        a() {
        }

        /* access modifiers changed from: package-private */
        public a a(Context context) {
            Context unused = this.f735a.f734a = context;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(ay ayVar) {
            ay unused = this.f735a.b = ayVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(j jVar) {
            j unused = this.f735a.c = jVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(Handler handler) {
            Handler unused = this.f735a.d = handler;
            return this;
        }

        /* access modifiers changed from: package-private */
        public a a(dw dwVar) {
            dw unused = this.f735a.e = dwVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ax a() {
            return this.f735a;
        }
    }
}
