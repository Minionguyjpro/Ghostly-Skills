package com.yandex.metrica.impl.ob;

import org.json.JSONException;
import org.json.JSONObject;

public final class cm {

    /* renamed from: a  reason: collision with root package name */
    private final String f832a;
    private final int b;
    private final boolean c;

    public cm(JSONObject jSONObject) throws JSONException {
        this.f832a = jSONObject.getString("name");
        this.c = jSONObject.getBoolean("required");
        this.b = jSONObject.optInt("version", -1);
    }

    public cm(String str, int i, boolean z) {
        this.f832a = str;
        this.b = i;
        this.c = z;
    }

    public cm(String str, boolean z) {
        this(str, -1, z);
    }

    public JSONObject a() throws JSONException {
        JSONObject put = new JSONObject().put("name", this.f832a).put("required", this.c);
        int i = this.b;
        if (i != -1) {
            put.put("version", i);
        }
        return put;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            cm cmVar = (cm) obj;
            if (this.b != cmVar.b || this.c != cmVar.c) {
                return false;
            }
            String str = this.f832a;
            String str2 = cmVar.f832a;
            if (str != null) {
                return str.equals(str2);
            }
            if (str2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.f832a;
        return ((((str != null ? str.hashCode() : 0) * 31) + this.b) * 31) + (this.c ? 1 : 0);
    }
}
