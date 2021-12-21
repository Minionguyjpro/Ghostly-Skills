package com.startapp.common.d;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static CookieManager f359a;

    public static void a(Context context) {
        f359a = new CookieManager(new b(context), CookiePolicy.ACCEPT_ALL);
    }

    public static void b(Context context) {
        if (Build.VERSION.SDK_INT >= 9) {
            a(context);
        }
    }

    public static void a(HttpURLConnection httpURLConnection, String str) {
        CookieManager a2;
        Map<String, List<String>> map;
        if (Build.VERSION.SDK_INT >= 9 && (a2 = a()) != null && (map = a2.get(URI.create(str), httpURLConnection.getRequestProperties())) != null && map.size() > 0 && map.get("Cookie").size() > 0) {
            httpURLConnection.addRequestProperty("Cookie", TextUtils.join("=", map.get("Cookie")));
        }
    }

    public static void b(HttpURLConnection httpURLConnection, String str) {
        CookieManager a2;
        if (Build.VERSION.SDK_INT >= 9 && (a2 = a()) != null) {
            a2.put(URI.create(str), httpURLConnection.getHeaderFields());
        }
    }

    public static CookieManager a() {
        return f359a;
    }
}
