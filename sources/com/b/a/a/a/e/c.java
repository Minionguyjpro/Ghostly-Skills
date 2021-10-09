package com.b.a.a.a.e;

import android.util.Log;

public final class c {
    public static void a(String str) {
    }

    public static void a(String str, Exception exc) {
        if (exc != null) {
            Log.e("OMIDLIB", str, exc);
        }
    }
}
