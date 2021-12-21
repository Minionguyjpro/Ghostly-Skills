package com.startapp.android.publish.adsCommon.f;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.util.Pair;
import com.startapp.android.mediation.admob.StartAppNative;
import com.startapp.android.publish.adsCommon.BaseRequest;
import com.startapp.android.publish.adsCommon.Utils.c;
import com.startapp.android.publish.adsCommon.l;
import com.startapp.common.a.a;
import com.startapp.common.a.g;
import java.util.List;
import org.json.JSONArray;

/* compiled from: StartAppSDK */
public class e extends BaseRequest {

    /* renamed from: a  reason: collision with root package name */
    private d f245a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private Long h;
    private String i;
    private String j;
    private JSONArray k;

    public e(d dVar) {
        this(dVar, "", "");
    }

    public e(d dVar, String str, String str2) {
        this.f245a = dVar;
        this.b = str;
        this.c = str2;
    }

    public com.startapp.android.publish.adsCommon.Utils.e getNameValueJson() {
        com.startapp.android.publish.adsCommon.Utils.e nameValueJson = super.getNameValueJson();
        if (nameValueJson == null) {
            nameValueJson = new c();
        }
        a(nameValueJson);
        return nameValueJson;
    }

    private void a(com.startapp.android.publish.adsCommon.Utils.e eVar) {
        String d2 = a.d();
        eVar.a(a.a(), (Object) d2, true);
        eVar.a(a.b(), (Object) a.b(d2), true);
        eVar.a(StartAppNative.EXTRAS_CATEGORY, (Object) e().a(), true);
        eVar.a("value", (Object) f(), false);
        eVar.a("d", (Object) h(), false);
        eVar.a("orientation", (Object) i(), false);
        eVar.a("usedRam", (Object) j(), false);
        eVar.a("freeRam", (Object) k(), false);
        eVar.a("sessionTime", (Object) l(), false);
        eVar.a("appActivity", (Object) m(), false);
        eVar.a("details", (Object) g(), false);
        eVar.a("details_json", (Object) n(), false);
        eVar.a("cellScanRes", (Object) o(), false);
        Pair<String, String> c2 = l.c();
        Pair<String, String> d3 = l.d();
        eVar.a((String) c2.first, c2.second, false);
        eVar.a((String) d3.first, d3.second, false);
    }

    public d e() {
        return this.f245a;
    }

    public String f() {
        return this.b;
    }

    public void d(String str) {
        this.b = str;
    }

    public String g() {
        return this.c;
    }

    public String h() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public String i() {
        return this.e;
    }

    public void f(String str) {
        this.e = str;
    }

    public String j() {
        return this.f;
    }

    public void g(String str) {
        this.f = str;
    }

    public String k() {
        return this.g;
    }

    public void h(String str) {
        this.g = str;
    }

    public Long l() {
        return this.h;
    }

    public String m() {
        return this.i;
    }

    public JSONArray n() {
        return this.k;
    }

    public void a(JSONArray jSONArray) {
        this.k = jSONArray;
    }

    public String o() {
        return this.j;
    }

    public void i(String str) {
        this.j = str;
    }

    /* access modifiers changed from: package-private */
    public void a(Context context) {
        List<CellInfo> a2;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null && (a2 = com.startapp.common.a.c.a(context, telephonyManager)) != null && a2.size() > 0) {
                i(a.c(a2.toString()));
            }
        } catch (Exception e2) {
            g.a(6, "Cannot fillCellDetails " + e2.getMessage());
        }
    }

    public String toString() {
        return "InfoEventRequest [category=" + this.f245a.a() + ", value=" + this.b + ", details=" + this.c + ", d=" + this.d + ", orientation=" + this.e + ", usedRam=" + this.f + ", freeRam=" + this.g + ", sessionTime=" + this.h + ", appActivity=" + this.i + ", details_json=" + this.k + ", cellScanRes=" + this.j + "]";
    }
}
