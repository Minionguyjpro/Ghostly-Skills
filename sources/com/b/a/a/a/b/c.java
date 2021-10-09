package com.b.a.a.a.b;

import com.b.a.a.a.e.b;
import com.b.a.a.a.e.e;
import org.json.JSONObject;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private final f f981a;
    private final f b;
    private final boolean c;

    private c(f fVar, f fVar2, boolean z) {
        this.f981a = fVar;
        if (fVar2 == null) {
            this.b = f.NONE;
        } else {
            this.b = fVar2;
        }
        this.c = z;
    }

    public static c a(f fVar, f fVar2, boolean z) {
        e.a((Object) fVar, "Impression owner is null");
        e.a(fVar);
        return new c(fVar, fVar2, z);
    }

    public boolean a() {
        return f.NATIVE == this.f981a;
    }

    public boolean b() {
        return f.NATIVE == this.b;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        b.a(jSONObject, "impressionOwner", this.f981a);
        b.a(jSONObject, "videoEventsOwner", this.b);
        b.a(jSONObject, "isolateVerificationScripts", Boolean.valueOf(this.c));
        return jSONObject;
    }
}
