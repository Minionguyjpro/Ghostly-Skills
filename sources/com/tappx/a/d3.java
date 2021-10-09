package com.tappx.a;

import android.content.Context;
import android.os.Process;

public class d3 {
    public static boolean a(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }
}
