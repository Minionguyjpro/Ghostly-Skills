package com.onesignal;

import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JSONUtils {
    static JSONObject generateJsonDiff(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, Set<String> set) {
        JSONObject jSONObject4;
        if (jSONObject == null) {
            return null;
        }
        if (jSONObject2 == null) {
            return jSONObject3;
        }
        Iterator<String> keys = jSONObject2.keys();
        if (jSONObject3 != null) {
            jSONObject4 = jSONObject3;
        } else {
            jSONObject4 = new JSONObject();
        }
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                Object obj = jSONObject2.get(next);
                if (jSONObject.has(next)) {
                    if (obj instanceof JSONObject) {
                        String jSONObject5 = generateJsonDiff(jSONObject.getJSONObject(next), (JSONObject) obj, (jSONObject3 == null || !jSONObject3.has(next)) ? null : jSONObject3.getJSONObject(next), set).toString();
                        if (!jSONObject5.equals("{}")) {
                            jSONObject4.put(next, new JSONObject(jSONObject5));
                        }
                    } else if (obj instanceof JSONArray) {
                        handleJsonArray(next, (JSONArray) obj, jSONObject.getJSONArray(next), jSONObject4);
                    } else if (set == null || !set.contains(next)) {
                        Object obj2 = jSONObject.get(next);
                        if (!obj.equals(obj2)) {
                            if (!(obj2 instanceof Integer) || "".equals(obj)) {
                                jSONObject4.put(next, obj);
                            } else if (((Number) obj2).doubleValue() != ((Number) obj).doubleValue()) {
                                jSONObject4.put(next, obj);
                            }
                        }
                    } else {
                        jSONObject4.put(next, obj);
                    }
                } else if (obj instanceof JSONObject) {
                    jSONObject4.put(next, new JSONObject(obj.toString()));
                } else if (obj instanceof JSONArray) {
                    handleJsonArray(next, (JSONArray) obj, (JSONArray) null, jSONObject4);
                } else {
                    jSONObject4.put(next, obj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject4;
    }

    private static void handleJsonArray(String str, JSONArray jSONArray, JSONArray jSONArray2, JSONObject jSONObject) throws JSONException {
        String str2;
        if (str.endsWith("_a") || str.endsWith("_d")) {
            jSONObject.put(str, jSONArray);
            return;
        }
        String stringNE = toStringNE(jSONArray);
        JSONArray jSONArray3 = new JSONArray();
        JSONArray jSONArray4 = new JSONArray();
        if (jSONArray2 == null) {
            str2 = null;
        } else {
            str2 = toStringNE(jSONArray2);
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            String str3 = (String) jSONArray.get(i);
            if (jSONArray2 == null || !str2.contains(str3)) {
                jSONArray3.put(str3);
            }
        }
        if (jSONArray2 != null) {
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                String string = jSONArray2.getString(i2);
                if (!stringNE.contains(string)) {
                    jSONArray4.put(string);
                }
            }
        }
        if (!jSONArray3.toString().equals("[]")) {
            jSONObject.put(str + "_a", jSONArray3);
        }
        if (!jSONArray4.toString().equals("[]")) {
            jSONObject.put(str + "_d", jSONArray4);
        }
    }

    static String toStringNE(JSONArray jSONArray) {
        int i = 0;
        String str = "[";
        while (i < jSONArray.length()) {
            try {
                str = str + "\"" + jSONArray.getString(i) + "\"";
                i++;
            } catch (JSONException unused) {
            }
        }
        return str + "]";
    }

    static JSONObject getJSONObjectWithoutBlankValues(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        Iterator<String> keys = optJSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object obj = optJSONObject.get(next);
                if (!"".equals(obj)) {
                    jSONObject2.put(next, obj);
                }
            } catch (JSONException unused) {
            }
        }
        return jSONObject2;
    }

    static boolean compareJSONArrays(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        if (jSONArray == null || jSONArray2 == null || jSONArray.length() != jSONArray2.length()) {
            return false;
        }
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                int i2 = 0;
                while (i2 < jSONArray2.length()) {
                    if (normalizeType(jSONArray.get(i)).equals(normalizeType(jSONArray2.get(i2)))) {
                        i++;
                    } else {
                        i2++;
                    }
                }
                return false;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static Object normalizeType(Object obj) {
        Class<?> cls = obj.getClass();
        if (cls.equals(Integer.class)) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        return cls.equals(Float.class) ? Double.valueOf((double) ((Float) obj).floatValue()) : obj;
    }
}
