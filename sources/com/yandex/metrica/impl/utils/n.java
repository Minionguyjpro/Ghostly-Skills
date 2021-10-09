package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.d;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class n {

    public enum a {
        LOGIN("login"),
        LOGOUT("logout"),
        SWITCH("switch"),
        UPDATE("update");
        
        private String e;

        private a(String str) {
            this.e = str;
        }

        public String toString() {
            return this.e;
        }
    }

    public static d a(String str) {
        d dVar = new d();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                dVar.a(jSONObject.optString("UserInfo.UserId", (String) null));
                dVar.b(jSONObject.optString("UserInfo.Type", (String) null));
                dVar.a((Map<String, String>) g.a(jSONObject.optJSONObject("UserInfo.Options")));
            } catch (JSONException unused) {
            }
        }
        return dVar;
    }

    public static String a(a aVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("action", aVar.toString());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }
}
