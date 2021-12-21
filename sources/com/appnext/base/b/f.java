package com.appnext.base.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Process;
import android.text.TextUtils;

public class f {
    public static final String TAG = f.class.getSimpleName();

    public static NetworkInfo e(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || context.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
            return false;
        }
        return true;
    }

    public static String getKey() {
        h.aO();
        return h.J(com.appnext.core.f.b(e.getContext(), false));
    }
}
