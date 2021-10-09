package com.tappx.a;

import android.util.SparseArray;
import com.tappx.a.c5;

public class a5 {

    /* renamed from: a  reason: collision with root package name */
    private static final SparseArray<c5.a> f372a = new SparseArray<>();
    private static volatile int b = 0;

    public static int a(c5.a aVar) {
        int a2 = a();
        f372a.append(a2, aVar);
        return a2;
    }

    public static void b(int i) {
        f372a.remove(i);
    }

    public static c5.a a(int i) {
        return f372a.get(i);
    }

    private static synchronized int a() {
        int i;
        synchronized (a5.class) {
            i = b;
            b++;
        }
        return i;
    }
}
