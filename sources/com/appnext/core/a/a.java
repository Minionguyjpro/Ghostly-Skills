package com.appnext.core.a;

import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private HashMap<String, String> hT;
    private HashMap<String, HashMap<String, String>> hU = new HashMap<>();

    public a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.hT = d(jSONObject.getJSONObject(b.hW));
            this.hU.put(b.hX, d(jSONObject.getJSONObject(b.hX)));
            this.hU.put(b.hY, d(jSONObject.getJSONObject(b.hY)));
        } catch (Throwable unused) {
        }
    }

    public final String t(String str, String str2) {
        if (this.hU.containsKey(str)) {
            return (String) this.hU.get(str).get(str2);
        }
        return null;
    }

    public final String d(String str) {
        return this.hT.get(str);
    }

    private static HashMap<String, String> d(JSONObject jSONObject) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.getString(next));
        }
        return hashMap;
    }
}
