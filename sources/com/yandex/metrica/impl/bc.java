package com.yandex.metrica.impl;

import android.os.Build;

public class bc {
    public static String a() {
        return "2.73";
    }

    public static boolean b() {
        return c("com.yandex.metrica.YandexMetricaInternal");
    }

    public static String c() {
        return a.f746a;
    }

    public static String a(String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("/");
        sb.append(a());
        sb.append(".7854 (");
        if (Build.MODEL.startsWith(Build.MANUFACTURER)) {
            str2 = bi.b(Build.MODEL);
        } else {
            str2 = bi.b(Build.MANUFACTURER) + " " + Build.MODEL;
        }
        sb.append(str2);
        sb.append("; Android ");
        sb.append(Build.VERSION.RELEASE);
        sb.append(")");
        return sb.toString();
    }

    static class a {

        /* renamed from: a  reason: collision with root package name */
        static final String f746a = new a().a();

        a() {
        }

        /* access modifiers changed from: package-private */
        public String a() {
            if (a("com.unity3d.player.UnityPlayer")) {
                return "unity";
            }
            if (a("mono.MonoPackageManager")) {
                return "xamarin";
            }
            if (a("org.apache.cordova.CordovaPlugin")) {
                return "cordova";
            }
            return a("com.facebook.react.ReactRootView") ? "react" : "native";
        }

        /* access modifiers changed from: package-private */
        public boolean a(String str) {
            return bc.c(str);
        }
    }

    /* access modifiers changed from: private */
    public static boolean c(String str) {
        try {
            return Class.forName(str) != null;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
