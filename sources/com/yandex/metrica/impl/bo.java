package com.yandex.metrica.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.b;
import com.yandex.metrica.e;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter;
import com.yandex.metrica.impl.ax;
import com.yandex.metrica.impl.i;
import com.yandex.metrica.impl.j;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.bz;
import com.yandex.metrica.impl.ob.dv;
import com.yandex.metrica.impl.ob.dw;
import com.yandex.metrica.impl.utils.h;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class bo implements j.a {

    /* renamed from: a  reason: collision with root package name */
    private static bo f757a;
    private static n b = new n();
    private final Context c;
    private final ax d;
    /* access modifiers changed from: private */
    public z e;
    private ah f;
    private final ExecutorService g = Executors.newSingleThreadExecutor();
    private final dv h;
    private final ar i;
    private g j;
    private i k;

    private bo(Context context, String str) {
        Log.i(com.yandex.metrica.impl.utils.j.f().d(), "Initializing of Metrica" + ", Release type" + ", Version 2.73" + ", API Level 58" + ", Dated 15.06.2017.");
        com.yandex.metrica.impl.utils.j.a(context);
        this.c = context.getApplicationContext();
        GoogleAdvertisingIdGetter.b.f698a.a(this.c);
        Handler handler = new Handler(Looper.getMainLooper());
        ay ayVar = new ay(this.g, this.c, handler);
        bz bzVar = new bz(bp.a(this.c).e());
        new f(bzVar).a(this.c);
        dv dvVar = new dv(ayVar, str, bzVar);
        this.h = dvVar;
        ayVar.a((dw) dvVar);
        this.i = new ar(ayVar, bzVar);
        j jVar = new j(handler);
        jVar.a(this);
        ayVar.a(jVar);
        this.d = new ax.a().a(this.c).a((dw) this.h).a(ayVar).a(handler).a(jVar).a();
        if (bc.b()) {
            this.j = new g(bzVar, new c(this.c), this.g);
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        ah ahVar = new ah(Thread.getDefaultUncaughtExceptionHandler());
        ahVar.a(new av(this.d.a("20799a27-fa80-4b36-b2db-0f8141f24180"), new i.a() {
            public boolean a(Throwable th) {
                String a2 = bk.a(th);
                if (TextUtils.isEmpty(a2)) {
                    return false;
                }
                StringBuilder sb = new StringBuilder("at ");
                sb.append("com.yandex.metrica");
                sb.append(".");
                return a2.contains(sb.toString());
            }
        }));
        this.f = ahVar;
        Thread.setDefaultUncaughtExceptionHandler(ahVar);
    }

    public static synchronized void a(Context context, e eVar) {
        synchronized (bo.class) {
            boolean i2 = b.i();
            e a2 = b.a(eVar);
            b(context, a2);
            if (f757a.e == null) {
                if (Boolean.TRUE.equals(a2.isLogEnabled())) {
                    com.yandex.metrica.impl.utils.j.f().a();
                }
                bo boVar = f757a;
                z a3 = boVar.d.a(a2, i2);
                boVar.e = a3;
                a(a3.d().b().k());
            } else {
                f757a.e.a(a2, i2);
            }
            ((b) YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180")).a(1);
        }
    }

    public static synchronized void a(Context context) {
        synchronized (bo.class) {
            b(context, (e) null);
        }
    }

    public static synchronized void b(Context context, e eVar) {
        synchronized (bo.class) {
            bk.a((Object) context, "App Context");
            if (f757a == null) {
                bo boVar = new bo(context.getApplicationContext(), eVar != null ? eVar.a() : null);
                f757a = boVar;
                v.a(boVar.c);
                if (eVar != null) {
                    boVar.h.a(eVar.c());
                    boVar.h.a(eVar.f());
                    boVar.h.a(eVar.g());
                }
                boVar.h.d();
                boVar.g.execute(new h.a(boVar.c));
                f757a.a();
            }
        }
    }

    public static synchronized bo b() {
        bo boVar;
        synchronized (bo.class) {
            if (f757a != null) {
                boVar = f757a;
            } else {
                throw bl.f754a;
            }
        }
        return boVar;
    }

    public static bo b(Context context) {
        a(context);
        return b();
    }

    public static synchronized z c() {
        z zVar;
        synchronized (bo.class) {
            bo b2 = b();
            if (b2.e != null) {
                zVar = b2.e;
            } else {
                throw bl.f754a;
            }
        }
        return zVar;
    }

    static synchronized boolean d() {
        boolean z;
        synchronized (bo.class) {
            z = (f757a == null || f757a.e == null) ? false : true;
        }
        return z;
    }

    public b a(String str) {
        return this.d.a(str);
    }

    public void b(String str) {
        this.i.a(str);
    }

    public static void a(boolean z) {
        if (d()) {
            bo b2 = b();
            if (z) {
                if (b2.k == null) {
                    b2.k = new av(b2.e, new i.a() {
                        public boolean a(Throwable th) {
                            return bo.this.e.f();
                        }
                    });
                }
                b2.f.a(b2.k);
            } else {
                b2.f.b(b2.k);
            }
            b2.e.c(z);
            return;
        }
        b.c(z);
    }

    public void a(int i2, Bundle bundle) {
        if (i2 == 1) {
            this.h.a(bundle);
            g gVar = this.j;
            if (gVar != null) {
                gVar.a();
            }
        } else if (i2 == 2) {
            this.h.b(bundle);
        }
    }
}
