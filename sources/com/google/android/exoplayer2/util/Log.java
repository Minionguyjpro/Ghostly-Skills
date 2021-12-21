package com.google.android.exoplayer2.util;

import android.text.TextUtils;

public final class Log {
    private static int logLevel = 0;
    private static boolean logStackTraces = true;

    public static void d(String str, String str2) {
        if (logLevel == 0) {
            android.util.Log.d(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (logLevel <= 1) {
            android.util.Log.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (logLevel <= 2) {
            android.util.Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (!logStackTraces) {
            w(str, appendThrowableMessage(str2, th));
        } else if (logLevel <= 2) {
            android.util.Log.w(str, str2, th);
        }
    }

    public static void e(String str, String str2) {
        if (logLevel <= 3) {
            android.util.Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (!logStackTraces) {
            e(str, appendThrowableMessage(str2, th));
        } else if (logLevel <= 3) {
            android.util.Log.e(str, str2, th);
        }
    }

    private static String appendThrowableMessage(String str, Throwable th) {
        if (th == null) {
            return str;
        }
        String message = th.getMessage();
        if (TextUtils.isEmpty(message)) {
            return str;
        }
        return str + " - " + message;
    }
}
