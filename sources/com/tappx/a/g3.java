package com.tappx.a;

import android.os.Handler;
import android.os.Looper;

public class g3 {

    /* renamed from: a  reason: collision with root package name */
    private static final Handler f451a = new Handler(Looper.getMainLooper());

    public static boolean a() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void a(Runnable runnable) {
        f451a.post(runnable);
    }
}
