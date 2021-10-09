package com.yandex.metrica.impl;

import android.content.Context;

public final class al {
    public static boolean a(Context context, String str) {
        if (str != null) {
            try {
                if (context.checkCallingOrSelfPermission(str) != 0) {
                    return false;
                }
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Context context) {
        return a(context, "android.permission.ACCESS_COARSE_LOCATION") || a(context, "android.permission.ACCESS_FINE_LOCATION");
    }
}
