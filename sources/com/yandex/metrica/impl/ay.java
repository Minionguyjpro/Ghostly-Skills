package com.yandex.metrica.impl;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.impl.az;
import com.yandex.metrica.impl.ob.dw;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.g;
import com.yandex.metrica.impl.utils.j;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ay implements s {

    /* renamed from: a  reason: collision with root package name */
    private final Context f736a;
    private ad b;
    private final NativeCrashesHelper c;
    private final ExecutorService d;
    private z e;
    private u f;
    private dw g;
    private final az h = new az(this);

    ay(ExecutorService executorService, Context context, Handler handler) {
        this.b = new ad(context, handler);
        this.d = executorService;
        this.f736a = context;
        this.c = new NativeCrashesHelper(context);
        this.f = new u(context);
    }

    /* access modifiers changed from: package-private */
    public void a(z zVar) {
        this.e = zVar;
    }

    /* access modifiers changed from: package-private */
    public void a(dw dwVar) {
        this.g = dwVar;
        this.f.b(dwVar);
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        this.f.a(jVar);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z, aw awVar) {
        awVar.b().b(z);
        this.c.a(z);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, aw awVar) {
        j.f().a("Error received: native");
        a(p.a(p.a.EVENT_TYPE_NATIVE_CRASH, str), awVar);
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        a(str, this.e.d());
    }

    /* access modifiers changed from: private */
    public static h c(h hVar, aw awVar) {
        if (hVar.c() == p.a.EVENT_TYPE_EXCEPTION_USER.a()) {
            hVar.e(awVar.f());
        }
        return hVar;
    }

    /* access modifiers changed from: package-private */
    public void a(h hVar, aw awVar) {
        a(c(hVar, awVar), awVar, (Map<String, Object>) null);
    }

    public Future<Void> a(h hVar, final aw awVar, final Map<String, Object> map) {
        this.b.c();
        az.d dVar = new az.d(hVar, awVar);
        if (!bk.a((Map) map)) {
            dVar.a((az.c) new az.c() {
                public h a(h hVar) {
                    return ay.c(hVar.c(g.a(map)), awVar);
                }
            });
        }
        return a(dVar);
    }

    public void c() {
        a(p.d(p.a.EVENT_TYPE_STARTUP), (aw) this.f);
    }

    public void b(String str) {
        a(p.d(str), (aw) this.f);
    }

    public void a(aw awVar) {
        a(p.a(awVar.g()), awVar);
    }

    public void a(List<String> list) {
        this.f.b().a(list);
    }

    public void a(Map<String, String> map) {
        this.f.b().a(map);
    }

    public void c(String str) {
        this.f.b().h(str);
    }

    /* access modifiers changed from: package-private */
    public void a(Throwable th, aw awVar) {
        String str;
        if (awVar.b().C()) {
            j.f().a("Error received: uncaught");
        }
        this.b.c();
        String a2 = bk.a((String) null, th);
        if (th == null) {
            str = "";
        } else {
            str = th.getClass().getName();
        }
        h c2 = p.c(str, a2);
        c2.e(awVar.f());
        try {
            a(new az.d(c2, awVar).a(true)).get();
        } catch (InterruptedException | ExecutionException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.b.c();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.b.b();
    }

    public void a(IMetricaService iMetricaService, h hVar, aw awVar) throws RemoteException {
        c(awVar);
        if (awVar.b().l()) {
            this.c.a(this, this.d);
        }
        iMetricaService.reportData(hVar.a(awVar.c()));
        z zVar = this.e;
        if (zVar == null || zVar.e()) {
            this.b.b();
        }
    }

    public void a(String str, String str2, aw awVar) {
        a(new az.d(new h().a(p.a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED.a()).a(str, str2), awVar));
    }

    public void b(aw awVar) {
        a(new az.d(new h().a(p.a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED.a()), awVar));
    }

    /* access modifiers changed from: package-private */
    public void c(aw awVar) {
        if (awVar.b().C()) {
            awVar.b().e(j.f().b());
        }
    }

    private Future<Void> a(az.d dVar) {
        dVar.a().a(this.g);
        return this.h.a(dVar);
    }

    public ad a() {
        return this.b;
    }

    public Context b() {
        return this.f736a;
    }
}
