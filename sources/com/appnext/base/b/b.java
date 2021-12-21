package com.appnext.base.b;

import android.text.TextUtils;
import com.appnext.base.a.a;
import com.appnext.base.a.b.c;
import com.appnext.base.b.d;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class b {
    public static boolean d(c cVar) {
        if (cVar == null) {
            return false;
        }
        try {
            int i = i.aR().getInt(cVar.getKey() + i.fA, 0);
            try {
                if (i >= Integer.parseInt(cVar.an()) || i == 0) {
                    return true;
                }
                return false;
            } catch (NumberFormatException unused) {
            }
        } catch (Throwable unused2) {
            return false;
        }
    }

    private static List<com.appnext.base.a.b.b> E(String str) {
        try {
            return a.X().aa().v(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean F(String str) {
        List<com.appnext.base.a.b.b> E = E(str);
        return E == null || E.size() == 0;
    }

    public static JSONObject a(String str, Date date, d.a aVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            String L = h.aO().L(str);
            if (TextUtils.isEmpty(L)) {
                return jSONObject;
            }
            jSONObject.put(c.DATA, j.b(L, aVar));
            jSONObject.put("date", j.a(date));
            return jSONObject;
        } catch (Throwable unused) {
        }
    }

    public static void G(String str) {
        if (str != null) {
            try {
                i.aR().putInt(str + i.fA, 0);
            } catch (Throwable unused) {
            }
        }
    }

    public static void H(String str) {
        try {
            i.aR().putLong(str + i.fy, System.currentTimeMillis());
            String str2 = str + i.fA;
            i.aR().putInt(str2, i.aR().getInt(str2, 0) + 1);
        } catch (Throwable unused) {
        }
    }

    public static JSONArray a(List<com.appnext.base.a.b.b> list, boolean z) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (com.appnext.base.a.b.b next : list) {
                String ai = next.ai();
                if (!ai.isEmpty()) {
                    String K = h.aO().K(ai);
                    if (!TextUtils.isEmpty(K)) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(com.appnext.base.a.c.d.ec, next.ah());
                        jSONObject.put(com.appnext.base.a.c.d.COLUMN_TYPE, next.getType());
                        jSONObject.put(com.appnext.base.a.c.d.ed, K);
                        jSONObject.put(com.appnext.base.a.c.d.ef, next.getDataType());
                        jSONArray.put(jSONObject);
                    }
                }
            }
            return jSONArray;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean a(String str, Map<String, String> map) {
        try {
            return j.b(str, map);
        } catch (Throwable unused) {
            return false;
        }
    }
}
