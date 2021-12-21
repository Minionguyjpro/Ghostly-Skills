package com.b.a.a.a.h;

import com.b.a.a.a.h.a.b;
import com.b.a.a.a.h.a.d;
import com.b.a.a.a.h.a.e;
import com.b.a.a.a.h.a.f;
import java.util.HashSet;
import org.json.JSONObject;

public class c implements b.C0042b {

    /* renamed from: a  reason: collision with root package name */
    private JSONObject f1012a;
    private final com.b.a.a.a.h.a.c b;

    public c(com.b.a.a.a.h.a.c cVar) {
        this.b = cVar;
    }

    public JSONObject a() {
        return this.f1012a;
    }

    public void a(JSONObject jSONObject) {
        this.f1012a = jSONObject;
    }

    public void a(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        this.b.b(new f(this, hashSet, jSONObject, d));
    }

    public void b() {
        this.b.b(new d(this));
    }

    public void b(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        this.b.b(new e(this, hashSet, jSONObject, d));
    }
}
