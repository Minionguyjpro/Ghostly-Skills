package com.startapp.common.c;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
public class b {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0014 A[SYNTHETIC, Splitter:B:13:0x0014] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T a(java.lang.String r2, java.lang.Class<T> r3) {
        /*
            r0 = 0
            com.startapp.common.c.a r1 = new com.startapp.common.c.a     // Catch:{ all -> 0x0011 }
            r1.<init>(r2)     // Catch:{ all -> 0x0011 }
            java.lang.Object r2 = r1.a(r3, (org.json.JSONObject) r0)     // Catch:{ all -> 0x000e }
            r1.close()     // Catch:{ IOException -> 0x000d }
        L_0x000d:
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()     // Catch:{ IOException -> 0x0017 }
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.common.c.b.a(java.lang.String, java.lang.Class):java.lang.Object");
    }

    public static String a(Object obj) {
        return c(obj).toString();
    }

    private static JSONObject c(Object obj) {
        Class cls = obj.getClass();
        ArrayList<Field> arrayList = new ArrayList<>();
        while (cls != null && !Object.class.equals(cls)) {
            arrayList.addAll(Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
        }
        JSONObject jSONObject = new JSONObject();
        for (Field field : arrayList) {
            int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                try {
                    field.setAccessible(true);
                    if (field.get(obj) != null) {
                        String a2 = c.a(field);
                        if (c.e(field)) {
                            jSONObject.put(a2, c(field.get(obj)));
                        } else if (c.c(field)) {
                            JSONArray jSONArray = new JSONArray();
                            for (Object b : (List) field.get(obj)) {
                                jSONArray.put(b(b));
                            }
                            jSONObject.put(a2, jSONArray);
                        } else if (c.d(field)) {
                            JSONArray jSONArray2 = new JSONArray();
                            for (Object b2 : (Set) field.get(obj)) {
                                jSONArray2.put(b(b2));
                            }
                            jSONObject.put(a2, jSONArray2);
                        } else if (c.b(field)) {
                            JSONObject jSONObject2 = new JSONObject();
                            for (Map.Entry entry : ((Map) field.get(obj)).entrySet()) {
                                jSONObject2.put(entry.getKey().toString(), b(entry.getValue()));
                            }
                            jSONObject.put(a2, jSONObject2);
                        } else {
                            jSONObject.put(a2, field.get(obj));
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | JSONException unused) {
                }
            }
        }
        return jSONObject;
    }

    public static Object b(Object obj) {
        if (c.a(obj)) {
            return obj;
        }
        return c(obj);
    }
}
