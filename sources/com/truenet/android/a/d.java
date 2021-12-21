package com.truenet.android.a;

import a.a.b.b.h;
import android.content.Context;
import android.net.ConnectivityManager;

/* compiled from: StartAppSDK */
public final class d {
    public static final ConnectivityManager a(Context context) {
        h.b(context, "$receiver");
        Object systemService = context.getSystemService("connectivity");
        if (systemService != null) {
            return (ConnectivityManager) systemService;
        }
        throw new a.a.h("null cannot be cast to non-null type android.net.ConnectivityManager");
    }

    public static final e b(Context context) {
        h.b(context, "$receiver");
        return new e(context);
    }
}
