package com.yandex.metrica.impl;

import android.content.ContentValues;
import com.yandex.metrica.impl.at;
import com.yandex.metrica.impl.ob.t;
import java.io.Closeable;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class bj implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private t f752a;
    private final ba b;
    private final Object c = new Object();
    private final aj d;
    private bh e;
    private boolean f = false;
    private Runnable g = new Runnable() {
        public void run() {
            bj.this.a();
        }
    };

    public bj(t tVar, Executor executor) {
        this.f752a = tVar;
        this.b = tVar.h();
        aj a2 = a(tVar, executor);
        this.d = a2;
        a2.start();
        this.e = a(this.f752a);
    }

    /* access modifiers changed from: package-private */
    public bh a(t tVar) {
        return new bh(tVar);
    }

    /* access modifiers changed from: package-private */
    public aj a(t tVar, Executor executor) {
        aj ajVar = new aj(executor, tVar.l());
        ajVar.setName("NetworkCore [" + tVar.l() + "]");
        return ajVar;
    }

    public void close() {
        synchronized (this.c) {
            if (!this.f) {
                d();
                if (this.d.isAlive()) {
                    this.d.a();
                }
                this.f = true;
            }
        }
    }

    public void a() {
        synchronized (this.c) {
            if (!this.f) {
                synchronized (this.c) {
                    if (!this.f) {
                        if (this.e.s()) {
                            bh bhVar = new bh(this.f752a);
                            this.e = bhVar;
                            this.d.a((ak) bhVar);
                        }
                        if (bk.b(this.b.a())) {
                            a(as.u(), (Long) -2L);
                            a(at.A(), (Long) null);
                        }
                    }
                }
                d();
            }
        }
    }

    private void a(at.a aVar, Long l) {
        List<ContentValues> a2 = this.f752a.i().a(l);
        if (a2.isEmpty()) {
            a2.add(l.f775a);
        }
        for (ContentValues next : a2) {
            try {
                this.d.a((ak) aVar.a(this.f752a).a(next));
            } catch (Exception unused) {
                return;
            }
        }
    }

    private void d() {
        this.f752a.n().removeCallbacks(this.g);
    }

    public void b() {
        synchronized (this.c) {
            if (!this.f) {
                d();
                if (this.f752a.j().b() > 0) {
                    this.f752a.n().postDelayed(this.g, TimeUnit.SECONDS.toMillis((long) this.f752a.j().b()));
                }
            }
        }
    }

    public void c() {
        synchronized (this.c) {
            if (!this.f && !this.d.b(this.e)) {
                this.e.a(true);
                this.e.a(0);
                bh bhVar = new bh(this.f752a);
                this.e = bhVar;
                this.d.a((ak) bhVar);
            }
        }
    }
}
