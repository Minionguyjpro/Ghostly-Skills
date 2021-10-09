package com.moat.analytics.mobile.mpub;

import android.util.Log;
import android.view.View;
import com.mopub.mobileads.VastExtensionXmlManager;

class p {
    p() {
    }

    static String a(View view) {
        if (view == null) {
            return "null";
        }
        return view.getClass().getSimpleName() + "@" + view.hashCode();
    }

    private static String a(String str) {
        return VastExtensionXmlManager.MOAT + str;
    }

    static void a(int i, String str, Object obj, String str2) {
        String str3;
        if (w.a().b) {
            String a2 = a(str);
            if (obj == null) {
                str3 = String.format("message = %s", new Object[]{str2});
            } else {
                str3 = String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2});
            }
            Log.println(i, a2, str3);
        }
    }

    static void a(String str, int i, String str2, Object obj, String str3) {
        a(i, str2, obj, str3);
        a(str, str3);
    }

    static void a(String str, Object obj, String str2, Throwable th) {
        if (w.a().b) {
            Log.e(a(str), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}), th);
        }
    }

    static void a(String str, String str2) {
        if (!w.a().b && ((k) MoatAnalytics.getInstance()).f1173a) {
            int i = 2;
            if (str.equals("[ERROR] ")) {
                i = 6;
            }
            Log.println(i, "MoatAnalytics", str + str2);
        }
    }

    static void b(int i, String str, Object obj, String str2) {
        if (w.a().c) {
            String a2 = a(str);
            Object[] objArr = new Object[2];
            objArr[0] = obj == null ? "null" : Integer.valueOf(obj.hashCode());
            objArr[1] = str2;
            Log.println(i, a2, String.format("id = %s, message = %s", objArr));
        }
    }
}
