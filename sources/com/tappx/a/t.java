package com.tappx.a;

import android.content.SharedPreferences;
import android.webkit.URLUtil;

public class t {

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f582a;

    public t(SharedPreferences sharedPreferences) {
        this.f582a = sharedPreferences;
    }

    public String a() {
        String string = this.f582a.getString("tappx_privacy_consent_endpoint", (String) null);
        if (string == null || !URLUtil.isValidUrl(string)) {
            return n.c();
        }
        return string;
    }

    public void a(String str) {
        this.f582a.edit().putString("tappx_privacy_consent_endpoint", str).apply();
    }
}
