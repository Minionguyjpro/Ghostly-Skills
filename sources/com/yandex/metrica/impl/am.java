package com.yandex.metrica.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Locale;

public final class am {

    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private static final String[] f722a = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};

        public static boolean a() {
            try {
                return new File("/system/app/Superuser.apk").exists();
            } catch (Throwable unused) {
                return false;
            }
        }

        public static boolean b() {
            String[] strArr = f722a;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str = strArr[i];
                try {
                    if (new File(str + "su").exists()) {
                        return true;
                    }
                    i++;
                } catch (Throwable unused) {
                }
            }
            return false;
        }

        public static int c() {
            return (a() || b()) ? 1 : 0;
        }
    }

    public static Point a(Context context) {
        int i;
        int i2;
        int i3;
        int i4;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                int intValue = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
                i = intValue;
            } catch (Exception unused) {
                i4 = defaultDisplay.getWidth();
                i3 = defaultDisplay.getHeight();
            }
        } else {
            i4 = defaultDisplay.getWidth();
            i3 = defaultDisplay.getHeight();
            int i5 = i4;
            i2 = i3;
            i = i5;
        }
        return new Point(i, i2);
    }

    public static String b(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        String country = locale.getCountry();
        StringBuilder sb = new StringBuilder(language);
        if (bk.a(21)) {
            String script = locale.getScript();
            if (!TextUtils.isEmpty(script)) {
                sb.append('-');
                sb.append(script);
            }
        }
        if (!TextUtils.isEmpty(country)) {
            sb.append('_');
            sb.append(country);
        }
        return sb.toString();
    }

    public static long a(boolean z) {
        try {
            StatFs b = b(z);
            return (((long) b.getBlockCount()) * ((long) b.getBlockSize())) / 1024;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static StatFs b(boolean z) {
        if (!z) {
            return new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        }
        return new StatFs(Environment.getRootDirectory().getAbsolutePath());
    }

    public static long c(boolean z) {
        try {
            StatFs b = b(z);
            return (((long) b.getAvailableBlocks()) * ((long) b.getBlockSize())) / 1024;
        } catch (Throwable unused) {
            return 0;
        }
    }
}
