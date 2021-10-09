package com.yandex.metrica.impl.ob;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.json.JSONException;
import org.json.JSONObject;

public class ch {

    /* renamed from: a  reason: collision with root package name */
    private final String f822a;
    private final cj b;
    private final long c;
    private final boolean d;
    private final long e;

    public ch(JSONObject jSONObject, long j) throws JSONException {
        this.f822a = jSONObject.getString("device_id");
        if (jSONObject.has("device_snapshot_key")) {
            this.b = new cj(jSONObject.getString("device_snapshot_key"));
        } else {
            this.b = null;
        }
        this.c = jSONObject.optLong("last_elections_time", -1);
        this.d = f();
        this.e = j;
    }

    public ch(String str, cj cjVar, long j) {
        this.f822a = str;
        this.b = cjVar;
        this.c = j;
        this.d = f();
        this.e = -1;
    }

    public String a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("device_id", this.f822a);
        cj cjVar = this.b;
        if (cjVar != null) {
            jSONObject.put("device_snapshot_key", cjVar.a());
        }
        jSONObject.put("last_elections_time", this.c);
        return jSONObject.toString();
    }

    public boolean b() {
        if (this.e > -1) {
            Calendar instance = GregorianCalendar.getInstance();
            instance.setTimeInMillis(this.e);
            if (instance.get(1) == 1970) {
                return true;
            }
        }
        return false;
    }

    public String c() {
        return this.f822a;
    }

    public cj d() {
        return this.b;
    }

    public boolean e() {
        return this.d;
    }

    private boolean f() {
        if (this.c <= -1 || System.currentTimeMillis() - this.c >= 604800000) {
            return false;
        }
        return true;
    }

    public boolean a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ch chVar = (ch) obj;
            if (this.d != chVar.d || !this.f822a.equals(chVar.f822a)) {
                return false;
            }
            cj cjVar = this.b;
            cj cjVar2 = chVar.b;
            if (cjVar != null) {
                return cjVar.equals(cjVar2);
            }
            if (cjVar2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.f822a.hashCode() * 31;
        cj cjVar = this.b;
        return ((hashCode + (cjVar != null ? cjVar.hashCode() : 0)) * 31) + (this.d ? 1 : 0);
    }

    public String toString() {
        return "Credentials{mFresh=" + this.d + ", mLastElectionsTime=" + this.c + ", mDeviceSnapshot=" + this.b + ", mDeviceID='" + this.f822a + '\'' + '}';
    }
}
