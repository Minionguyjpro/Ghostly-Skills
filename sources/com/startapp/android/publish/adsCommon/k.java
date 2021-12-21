package com.startapp.android.publish.adsCommon;

import android.content.Context;
import android.content.SharedPreferences;
import com.startapp.android.publish.adsCommon.Utils.i;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static SharedPreferences f267a;

    public static SharedPreferences a(Context context) {
        if (f267a == null) {
            f267a = context.getApplicationContext().getSharedPreferences("com.startapp.android.publish", 0);
        }
        return f267a;
    }

    public static Boolean a(Context context, String str, Boolean bool) {
        return Boolean.valueOf(a(context).getBoolean(str, bool.booleanValue()));
    }

    public static void b(Context context, String str, Boolean bool) {
        i.a(a(context).edit().putBoolean(str, bool.booleanValue()));
    }

    public static String a(Context context, String str, String str2) {
        return a(context).getString(str, str2);
    }

    public static void b(Context context, String str, String str2) {
        i.a(a(context).edit().putString(str, str2));
    }

    public static Integer a(Context context, String str, Integer num) {
        return Integer.valueOf(a(context).getInt(str, num.intValue()));
    }

    public static void b(Context context, String str, Integer num) {
        i.a(a(context).edit().putInt(str, num.intValue()));
    }

    public static Float a(Context context, String str, Float f) {
        return Float.valueOf(a(context).getFloat(str, f.floatValue()));
    }

    public static void b(Context context, String str, Float f) {
        i.a(a(context).edit().putFloat(str, f.floatValue()));
    }

    public static Long a(Context context, String str, Long l) {
        return Long.valueOf(a(context).getLong(str, l.longValue()));
    }

    public static void b(Context context, String str, Long l) {
        i.a(a(context).edit().putLong(str, l.longValue()));
    }

    public static void a(Context context, String str, Map<String, String> map) {
        b(context, str, new JSONObject(map).toString());
    }

    public static Map<String, String> b(Context context, String str, Map<String, String> map) {
        try {
            JSONObject jSONObject = new JSONObject(a(context).getString(str, (String) null));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                map.put(next, (String) jSONObject.get(next));
            }
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable unused) {
        }
        return map;
    }
}
