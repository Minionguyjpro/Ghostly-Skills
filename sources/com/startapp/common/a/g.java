package com.startapp.common.a;

import android.util.Log;
import com.startapp.common.Constants;

/* compiled from: StartAppSDK */
public class g {
    public static void a(int i, String str) {
        if (Constants.a().booleanValue()) {
            a(i, str, (Throwable) null);
        }
    }

    public static void a(String str, int i, String str2) {
        if (Constants.a().booleanValue()) {
            a(str, i, str2, (Throwable) null);
        }
    }

    public static void a(int i, String str, Throwable th) {
        a((String) null, i, str, th);
    }

    public static void a(String str, int i, String str2, Throwable th) {
        String str3;
        if (str == null) {
            str3 = "";
        } else {
            str3 = "." + str;
        }
        if (Constants.a().booleanValue()) {
            StringBuffer stringBuffer = new StringBuffer(str2);
            if (stringBuffer.length() > 4000) {
                b("startapp" + str3, i, "sb.length = " + stringBuffer.length(), th);
                int length = stringBuffer.length() / 4000;
                int i2 = 0;
                while (i2 <= length) {
                    int i3 = i2 + 1;
                    int i4 = i3 * 4000;
                    if (i4 >= stringBuffer.length()) {
                        b("startapp" + str3, i, i2 + "/" + length + ":" + stringBuffer.substring(i2 * 4000), (Throwable) null);
                    } else {
                        b("startapp" + str3, i, i2 + "/" + length + ":" + stringBuffer.substring(i2 * 4000, i4), (Throwable) null);
                    }
                    i2 = i3;
                }
                return;
            }
            b("startapp" + str3, i, str2, th);
        }
    }

    private static void b(String str, int i, String str2, Throwable th) {
        if (i == 2) {
            Log.v(str, str2, th);
        } else if (i == 3) {
            Log.d(str, str2, th);
        } else if (i == 4) {
            Log.i(str, str2, th);
        } else if (i == 5) {
            Log.w(str, str2, th);
        } else if (i == 6) {
            Log.e(str, str2, th);
        }
    }
}
