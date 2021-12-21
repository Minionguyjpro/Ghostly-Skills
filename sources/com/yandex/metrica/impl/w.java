package com.yandex.metrica.impl;

import android.os.Handler;
import android.os.SystemClock;

class w {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f960a;
    private final b b;
    private final x c;

    w(Handler handler, b bVar) {
        this.f960a = handler;
        this.b = bVar;
        this.c = new x(handler, bVar);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        b(this.f960a, this.b, this.c);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        a(this.f960a, this.b, this.c);
    }

    static void a(Handler handler, b bVar, Runnable runnable) {
        b(handler, bVar, runnable);
        handler.postAtTime(runnable, a(bVar), SystemClock.uptimeMillis() + ((long) (bVar.d().b().d() * 500)));
    }

    private static void b(Handler handler, b bVar, Runnable runnable) {
        handler.removeCallbacks(runnable, a(bVar));
    }

    private static String a(b bVar) {
        return bVar.d().b().j();
    }
}
