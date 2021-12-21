package com.startapp.a.a.g;

import com.startapp.a.a.a.a;
import com.startapp.a.a.d.e;
import java.util.HashMap;
import java.util.Map;

/* compiled from: StartAppSDK */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final Map<a, b> f21a;

    public c() {
        HashMap hashMap = new HashMap();
        this.f21a = hashMap;
        hashMap.put(a.ZERO, new g());
        this.f21a.put(a.THREE, new f());
        this.f21a.put(a.FOUR, new e());
        this.f21a.put(a.FIVE, new d());
    }

    public a a(a aVar) {
        return this.f21a.get(aVar).b();
    }

    public e b(a aVar) {
        return this.f21a.get(aVar).a();
    }
}
