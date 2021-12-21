package com.appnext.base.b;

import android.content.Context;

public final class e {
    private static Context fr;

    private e() {
    }

    public static void init(Context context) {
        if (context != null) {
            fr = context.getApplicationContext();
            return;
        }
        throw new IllegalArgumentException("context shouldn't be null");
    }

    public static Context getContext() {
        return fr;
    }

    public static String getPackageName() {
        return fr.getPackageName();
    }
}
