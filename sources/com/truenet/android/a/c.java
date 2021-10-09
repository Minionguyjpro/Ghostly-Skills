package com.truenet.android.a;

import a.a.b.b.h;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

/* compiled from: StartAppSDK */
public final class c {
    public static final TelephonyManager a(Context context) {
        h.b(context, "$receiver");
        Object systemService = context.getSystemService("phone");
        if (systemService != null) {
            return (TelephonyManager) systemService;
        }
        throw new a.a.h("null cannot be cast to non-null type android.telephony.TelephonyManager");
    }

    public static final WindowManager b(Context context) {
        h.b(context, "$receiver");
        Object systemService = context.getSystemService("window");
        if (systemService != null) {
            return (WindowManager) systemService;
        }
        throw new a.a.h("null cannot be cast to non-null type android.view.WindowManager");
    }
}
