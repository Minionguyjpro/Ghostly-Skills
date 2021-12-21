package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class dq {

    /* renamed from: a  reason: collision with root package name */
    private final JSONObject f861a = new JSONObject();

    public void a(String str) {
        a("uuid", str);
    }

    public void b(String str) {
        a("device_id", str);
    }

    public void c(String str) {
        a("google_aid", str);
    }

    public void d(String str) {
        a("android_id", str);
    }

    private void a(String str, String str2) {
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            try {
                this.f861a.put(str, str2);
            } catch (JSONException unused) {
            }
        }
    }

    public String a() throws JSONException {
        return this.f861a.toString();
    }
}
