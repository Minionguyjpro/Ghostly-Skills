package com.yandex.metrica.impl.utils;

import android.content.Context;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.cc;

public class k {

    /* renamed from: a  reason: collision with root package name */
    private volatile long f957a;
    private cc b;

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        static k f958a = new k((byte) 0);
    }

    /* synthetic */ k(byte b2) {
        this();
    }

    public static k a() {
        return a.f958a;
    }

    private k() {
    }

    public synchronized long b() {
        return this.f957a;
    }

    public synchronized void a(Context context) {
        if (context != null) {
            cc ccVar = new cc(bp.a(context).b());
            this.b = ccVar;
            this.f957a = ccVar.c(0);
        }
    }

    public synchronized void a(long j) {
        this.f957a = (j - System.currentTimeMillis()) / 1000;
        if (this.b != null) {
            this.b.a(this.f957a);
            this.b.h();
        }
    }
}
