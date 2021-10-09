package com.yandex.metrica.impl;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.impl.ob.dw;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.f;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class b implements com.yandex.metrica.b {

    /* renamed from: a  reason: collision with root package name */
    public static final Collection<Integer> f742a;
    protected final aw b;
    protected final ay c;
    private w d;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(8);
        hashSet.add(11);
        hashSet.add(12);
        hashSet.add(13);
        hashSet.add(16);
        hashSet.add(17);
        hashSet.add(18);
        hashSet.add(19);
        f742a = Collections.unmodifiableCollection(hashSet);
    }

    b(Context context, String str, ay ayVar, aw awVar) {
        context.getApplicationContext();
        this.c = ayVar;
        this.b = awVar;
        awVar.b().a(str);
        this.b.b().c(context.getPackageName());
        this.b.a(f.a.d());
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.c.a(this.b);
    }

    /* access modifiers changed from: package-private */
    public void a(dw dwVar) {
        this.b.b(dwVar);
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        this.b.a(jVar);
    }

    /* access modifiers changed from: package-private */
    public void a(w wVar) {
        this.d = wVar;
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.b.a(str, str2);
        }
    }

    public void a(Map<String, String> map) {
        if (!bk.a((Map) map)) {
            for (Map.Entry next : map.entrySet()) {
                a((String) next.getKey(), (String) next.getValue());
            }
        }
    }

    public void b(Map<String, String> map) {
        if (!bk.a((Map) map)) {
            for (Map.Entry next : map.entrySet()) {
                b((String) next.getKey(), (String) next.getValue());
            }
        }
    }

    public void b(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.c.a(str, str2, this.b);
        }
    }

    public void b() {
        this.c.b(this.b);
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        this.c.d();
        this.d.b();
        this.c.a(p.b(str), this.b);
        if (this.b.e()) {
            this.c.a(p.d(p.a.EVENT_TYPE_PURGE_BUFFER), this.b);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(String str) {
        if (!this.b.a()) {
            this.c.e();
            this.d.a();
            this.c.a(p.c(str), this.b);
            this.b.d();
        }
    }

    public void reportEvent(String str) {
        reportEvent(str, (String) null);
    }

    public void reportEvent(String str, String str2) {
        bk.a((Object) str, "Event Name");
        a(p.a(str, str2));
    }

    public void reportEvent(String str, Map<String, Object> map) {
        bk.a((Object) str, "Event Name");
        this.c.a(p.a(str), d(), (Map<String, Object>) bk.a((Map) map) ? null : new HashMap(map));
    }

    public void reportError(String str, Throwable th) {
        bk.a((Object) str, "Message");
        a(p.b(str, bk.a((String) null, th)));
    }

    public void reportUnhandledException(Throwable th) {
        bk.a((Object) th, "Exception");
        this.c.a(th, this.b);
    }

    public void a(int i) {
        this.b.b().b(i);
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        boolean z = !e();
        if (z) {
            this.c.a(p.c(p.a.EVENT_TYPE_ALIVE.b()), this.b);
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public aw d() {
        return this.b;
    }

    private void a(h hVar) {
        this.c.a(hVar, this.b);
    }

    public boolean e() {
        return this.b.a();
    }
}
