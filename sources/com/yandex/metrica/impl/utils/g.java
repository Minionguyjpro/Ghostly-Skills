package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.impl.bk;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g {

    public static class a extends JSONObject {
        public a(String str) throws JSONException {
            super(str);
        }

        public String a(String str) {
            try {
                return super.getString(str);
            } catch (Exception unused) {
                return "";
            }
        }

        public Object a(String str, Object obj) {
            try {
                return super.get(str);
            } catch (Exception unused) {
                return obj;
            }
        }

        public boolean b(String str) {
            try {
                return NULL != super.get(str);
            } catch (Exception unused) {
            }
        }
    }

    public static Object a(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            if (obj.getClass().isArray()) {
                int length = Array.getLength(obj);
                ArrayList arrayList = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    arrayList.add(a(Array.get(obj, i)));
                }
                return new JSONArray(arrayList);
            } else if (obj instanceof Collection) {
                Collection<Object> collection = (Collection) obj;
                ArrayList arrayList2 = new ArrayList(collection.size());
                for (Object a2 : collection) {
                    arrayList2.add(a(a2));
                }
                return new JSONArray(arrayList2);
            } else if (!(obj instanceof Map)) {
                return obj;
            } else {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    String obj2 = entry.getKey().toString();
                    if (obj2 != null) {
                        linkedHashMap.put(obj2, a(entry.getValue()));
                    }
                }
                return new JSONObject(linkedHashMap);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(Map map) {
        if (bk.a(map)) {
            return null;
        }
        if (bk.a(19)) {
            return new JSONObject(map).toString();
        }
        return a((Object) map).toString();
    }

    public static String a(List<String> list) {
        if (bk.a((Collection) list)) {
            return null;
        }
        if (bk.a(19)) {
            return new JSONArray(list).toString();
        }
        return a((Object) list).toString();
    }

    public static HashMap<String, String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static List<String> b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            ArrayList arrayList = new ArrayList(jSONArray.length());
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    arrayList.add(jSONArray.getString(i));
                    i++;
                } catch (JSONException unused) {
                }
            }
            return arrayList;
        } catch (JSONException unused2) {
            return null;
        }
    }

    public static HashMap<String, String> a(JSONObject jSONObject) {
        if (JSONObject.NULL.equals(jSONObject)) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String optString = jSONObject.optString(next);
            if (optString != null) {
                hashMap.put(next, optString);
            }
        }
        return hashMap;
    }
}
