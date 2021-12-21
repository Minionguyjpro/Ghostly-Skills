package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import java.io.Closeable;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class bk {
    public static String a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "0.0";
        }
    }

    public static int b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    public static String a(String str, Throwable th) {
        String a2 = a(th);
        if (TextUtils.isEmpty(str)) {
            return a2;
        }
        return str + ":\n" + a2;
    }

    static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        String obj = stringWriter.toString();
        printWriter.close();
        return obj;
    }

    public static boolean a(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    public static boolean b(int i) {
        return Build.VERSION.SDK_INT > i;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception unused) {
            }
        }
    }

    public static void a(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public static void a(Object obj, String str) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s. %s should not be null.", new Object[]{str, str}));
        }
    }

    public static void a(String str, String str2) throws IllegalArgumentException {
        if (bi.a(str)) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s. %s should not be null/empty.", new Object[]{str2, str2}));
        }
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static void a(String str) {
        a(str, "API Key");
        try {
            UUID.fromString(str);
        } catch (Exception unused) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s = %s. Please, read official documentation how to obtain one: %s", new Object[]{"API Key", str, "https://tech.yandex.com/metrica-mobile-sdk/doc/mobile-sdk-dg/concepts/android-initialize-docpage/"}));
        }
    }

    public static List<ResolveInfo> a(Context context, String str, String str2) {
        try {
            Intent intent = new Intent(str, (Uri) null);
            intent.addCategory(str2);
            return context.getPackageManager().queryIntentActivities(intent, 0);
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static PackageInfo c(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(PackageManager packageManager, String str, String str2, String str3) {
        try {
            Bundle bundle = packageManager.getApplicationInfo(str, 128).metaData;
            Object obj = bundle != null ? bundle.get(str2) : null;
            if (obj != null) {
                return obj.toString();
            }
        } catch (Exception unused) {
        }
        return str3;
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.endTransaction();
            } catch (Exception unused) {
            }
        }
    }

    public static boolean a(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean a(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static String a(Context context, CounterConfiguration counterConfiguration, String str) {
        return counterConfiguration.D() ? counterConfiguration.j() : a(context.getPackageManager(), str, "metrica:api:key", (String) null);
    }

    public static boolean b(String str) {
        return !bi.a(str) && !"-1".equals(str);
    }

    public static String[] a(long[] jArr) {
        String[] strArr = new String[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            strArr[i] = String.valueOf(jArr[i]);
        }
        return strArr;
    }
}
