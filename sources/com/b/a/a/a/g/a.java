package com.b.a.a.a.g;

import android.webkit.WebView;
import com.b.a.a.a.b.c;
import com.b.a.a.a.b.h;
import com.b.a.a.a.b.i;
import com.b.a.a.a.c.d;
import com.b.a.a.a.f.b;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private b f1002a = new b((WebView) null);
    private com.b.a.a.a.b.a b;
    private com.b.a.a.a.b.a.a c;
    private C0040a d;
    private double e;

    /* renamed from: com.b.a.a.a.g.a$a  reason: collision with other inner class name */
    enum C0040a {
        AD_STATE_IDLE,
        AD_STATE_VISIBLE,
        AD_STATE_HIDDEN
    }

    public a() {
        i();
    }

    public void a() {
    }

    public void a(float f) {
        d.a().a(c(), f);
    }

    /* access modifiers changed from: package-private */
    public void a(WebView webView) {
        this.f1002a = new b(webView);
    }

    public void a(com.b.a.a.a.b.a.a aVar) {
        this.c = aVar;
    }

    public void a(com.b.a.a.a.b.a aVar) {
        this.b = aVar;
    }

    public void a(c cVar) {
        d.a().a(c(), cVar.c());
    }

    public void a(i iVar, com.b.a.a.a.b.d dVar) {
        String g = iVar.g();
        JSONObject jSONObject = new JSONObject();
        com.b.a.a.a.e.b.a(jSONObject, "environment", "app");
        com.b.a.a.a.e.b.a(jSONObject, "adSessionType", dVar.f());
        com.b.a.a.a.e.b.a(jSONObject, "deviceInfo", com.b.a.a.a.e.a.d());
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("clid");
        jSONArray.put("vlid");
        com.b.a.a.a.e.b.a(jSONObject, "supports", jSONArray);
        JSONObject jSONObject2 = new JSONObject();
        com.b.a.a.a.e.b.a(jSONObject2, "partnerName", dVar.a().a());
        com.b.a.a.a.e.b.a(jSONObject2, "partnerVersion", dVar.a().b());
        com.b.a.a.a.e.b.a(jSONObject, "omidNativeInfo", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        com.b.a.a.a.e.b.a(jSONObject3, "libraryVersion", "1.2.0-Startapp");
        com.b.a.a.a.e.b.a(jSONObject3, "appId", com.b.a.a.a.c.c.a().b().getApplicationContext().getPackageName());
        com.b.a.a.a.e.b.a(jSONObject, "app", jSONObject3);
        if (dVar.d() != null) {
            com.b.a.a.a.e.b.a(jSONObject, "customReferenceData", dVar.d());
        }
        JSONObject jSONObject4 = new JSONObject();
        for (h next : dVar.b()) {
            com.b.a.a.a.e.b.a(jSONObject4, next.a(), next.c());
        }
        d.a().a(c(), g, jSONObject, jSONObject4);
    }

    public void a(String str) {
        d.a().a(c(), str, (JSONObject) null);
    }

    public void a(String str, double d2) {
        if (d2 > this.e) {
            this.d = C0040a.AD_STATE_VISIBLE;
            d.a().c(c(), str);
        }
    }

    public void a(String str, JSONObject jSONObject) {
        d.a().a(c(), str, jSONObject);
    }

    public void a(boolean z) {
        if (f()) {
            d.a().d(c(), z ? "foregrounded" : "backgrounded");
        }
    }

    public void b() {
        this.f1002a.clear();
    }

    public void b(String str, double d2) {
        if (d2 > this.e && this.d != C0040a.AD_STATE_HIDDEN) {
            this.d = C0040a.AD_STATE_HIDDEN;
            d.a().c(c(), str);
        }
    }

    public WebView c() {
        return (WebView) this.f1002a.get();
    }

    public com.b.a.a.a.b.a d() {
        return this.b;
    }

    public com.b.a.a.a.b.a.a e() {
        return this.c;
    }

    public boolean f() {
        return this.f1002a.get() != null;
    }

    public void g() {
        d.a().a(c());
    }

    public void h() {
        d.a().b(c());
    }

    public void i() {
        this.e = com.b.a.a.a.e.d.a();
        this.d = C0040a.AD_STATE_IDLE;
    }
}
