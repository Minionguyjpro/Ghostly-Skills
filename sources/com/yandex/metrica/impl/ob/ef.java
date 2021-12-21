package com.yandex.metrica.impl.ob;

import android.content.Context;

public class ef extends dy {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f877a = new Object();
    private static volatile ef b;
    private dy c;

    public static ef a(Context context) {
        if (b == null) {
            synchronized (f877a) {
                if (b == null) {
                    b = new ef(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    ef(Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
            this.c = new eb(context);
        } else {
            this.c = new ec();
        }
    }

    public synchronized void a() {
        this.c.a();
    }

    public synchronized void b() {
        this.c.b();
    }

    public synchronized void a(eh ehVar) {
        this.c.a(ehVar);
    }

    public synchronized void a(ea eaVar) {
        this.c.a(eaVar);
    }
}
