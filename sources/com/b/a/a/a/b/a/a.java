package com.b.a.a.a.b.a;

import com.b.a.a.a.b.b;
import com.b.a.a.a.b.i;
import com.b.a.a.a.e.e;
import com.mopub.mobileads.VastIconXmlManager;
import org.json.JSONObject;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final i f980a;

    private a(i iVar) {
        this.f980a = iVar;
    }

    public static a a(b bVar) {
        i iVar = (i) bVar;
        e.a((Object) bVar, "AdSession is null");
        e.g(iVar);
        e.a(iVar);
        e.b(iVar);
        e.e(iVar);
        a aVar = new a(iVar);
        iVar.f().a(aVar);
        return aVar;
    }

    private void b(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Invalid Video duration");
        }
    }

    private void c(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Invalid Video volume");
        }
    }

    public void a() {
        e.c(this.f980a);
        this.f980a.f().a("firstQuartile");
    }

    public void a(float f) {
        c(f);
        e.c(this.f980a);
        JSONObject jSONObject = new JSONObject();
        com.b.a.a.a.e.b.a(jSONObject, "videoPlayerVolume", Float.valueOf(f));
        com.b.a.a.a.e.b.a(jSONObject, "deviceVolume", Float.valueOf(com.b.a.a.a.c.e.a().d()));
        this.f980a.f().a("volumeChange", jSONObject);
    }

    public void a(float f, float f2) {
        b(f);
        c(f2);
        e.c(this.f980a);
        JSONObject jSONObject = new JSONObject();
        com.b.a.a.a.e.b.a(jSONObject, VastIconXmlManager.DURATION, Float.valueOf(f));
        com.b.a.a.a.e.b.a(jSONObject, "videoPlayerVolume", Float.valueOf(f2));
        com.b.a.a.a.e.b.a(jSONObject, "deviceVolume", Float.valueOf(com.b.a.a.a.c.e.a().d()));
        this.f980a.f().a("start", jSONObject);
    }

    public void b() {
        e.c(this.f980a);
        this.f980a.f().a("midpoint");
    }

    public void c() {
        e.c(this.f980a);
        this.f980a.f().a("thirdQuartile");
    }

    public void d() {
        e.c(this.f980a);
        this.f980a.f().a("complete");
    }

    public void e() {
        e.c(this.f980a);
        this.f980a.f().a("pause");
    }

    public void f() {
        e.c(this.f980a);
        this.f980a.f().a("bufferStart");
    }

    public void g() {
        e.c(this.f980a);
        this.f980a.f().a("bufferFinish");
    }

    public void h() {
        e.c(this.f980a);
        this.f980a.f().a("skipped");
    }
}
