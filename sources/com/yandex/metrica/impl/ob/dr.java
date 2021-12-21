package com.yandex.metrica.impl.ob;

import android.content.Context;

public class dr {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f862a = new Object();
    private static volatile dr b;
    private dp c;

    public static dr a(Context context) {
        if (b == null) {
            synchronized (f862a) {
                if (b == null) {
                    b = new dr(context);
                }
            }
        }
        return b;
    }

    private dr(Context context) {
        this.c = new dp(context);
    }

    public void a() {
        this.c.b();
    }

    public void b() {
        this.c.a();
    }
}
