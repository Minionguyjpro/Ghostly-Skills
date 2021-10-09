package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import com.yandex.metrica.impl.am;
import org.json.JSONException;
import org.json.JSONObject;

public class cj {

    /* renamed from: a  reason: collision with root package name */
    private final String f826a;
    private final String b;
    private final String c;
    private final Point d;

    public cj(Context context) {
        this.f826a = Build.MANUFACTURER;
        this.b = Build.MODEL;
        this.c = Build.VERSION.SDK_INT > 8 ? Build.SERIAL : "";
        int i = am.a(context).y;
        int i2 = am.a(context).x;
        this.d = new Point(Math.min(i, i2), Math.max(i, i2));
    }

    public cj(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.f826a = jSONObject.getString("manufacturer");
        this.b = jSONObject.getString("model");
        this.c = jSONObject.getString("serial");
        this.d = new Point(jSONObject.getInt("width"), jSONObject.getInt("height"));
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("manufacturer", this.f826a);
        jSONObject.put("model", this.b);
        jSONObject.put("serial", this.c);
        jSONObject.put("width", this.d.x);
        jSONObject.put("height", this.d.y);
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            cj cjVar = (cj) obj;
            String str = this.f826a;
            if (str == null ? cjVar.f826a != null : !str.equals(cjVar.f826a)) {
                return false;
            }
            String str2 = this.b;
            if (str2 == null ? cjVar.b != null : !str2.equals(cjVar.b)) {
                return false;
            }
            String str3 = this.c;
            if (str3 == null ? cjVar.c != null : !str3.equals(cjVar.c)) {
                return false;
            }
            Point point = this.d;
            Point point2 = cjVar.d;
            if (point != null) {
                return point.equals(point2);
            }
            if (point2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.f826a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.c;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Point point = this.d;
        if (point != null) {
            i = point.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "DeviceShapshot{mManufacturer='" + this.f826a + '\'' + ", mModel='" + this.b + '\'' + ", mSerial='" + this.c + '\'' + ", mScreenSize=" + this.d + '}';
    }
}
