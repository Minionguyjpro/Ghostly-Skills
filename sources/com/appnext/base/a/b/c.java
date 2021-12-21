package com.appnext.base.a.b;

import android.text.TextUtils;
import org.json.JSONObject;

public class c extends d {
    private String dK;
    private String dL;
    private String dM;
    private String dN;
    private String dO;
    private String dP;
    private String dQ;
    private JSONObject dR;

    public c(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.dK = str;
        this.dL = str2;
        this.dM = str3;
        this.dN = str4;
        this.dO = str5;
        this.dP = str6;
        this.dQ = str7;
        if (TextUtils.isEmpty(str8)) {
            this.dR = null;
            return;
        }
        try {
            this.dR = new JSONObject(str8);
        } catch (Throwable unused) {
            this.dR = null;
        }
    }

    public final String ak() {
        return this.dK;
    }

    public final String al() {
        return this.dL;
    }

    public final String am() {
        return this.dM;
    }

    public final String an() {
        return this.dN;
    }

    public final String ao() {
        return this.dO;
    }

    public final String getKey() {
        return this.dP;
    }

    public final String ap() {
        return this.dQ;
    }

    public final JSONObject aq() {
        return this.dR;
    }

    private boolean p(String str) {
        JSONObject jSONObject = this.dR;
        return jSONObject != null && jSONObject.has(str) && !this.dR.isNull(str);
    }

    public final String d(String str, String str2) {
        if (!p(str)) {
            return str2;
        }
        try {
            return this.dR.getString(str);
        } catch (Throwable unused) {
            return str2;
        }
    }

    public final long a(String str, long j) {
        if (!p(str)) {
            return 1;
        }
        try {
            return this.dR.getLong(str);
        } catch (Throwable unused) {
            return 1;
        }
    }

    public final int a(String str, int i) {
        if (!p(str)) {
            return i;
        }
        try {
            return this.dR.getInt(str);
        } catch (Throwable unused) {
            return i;
        }
    }

    public final boolean a(String str, boolean z) {
        if (!p(str)) {
            return z;
        }
        try {
            return this.dR.getBoolean(str);
        } catch (Throwable unused) {
            return z;
        }
    }
}
