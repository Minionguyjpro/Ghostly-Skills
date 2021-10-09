package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.a;
import java.util.HashMap;

public class q {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<String, a> f931a = new HashMap<>();

    public synchronized a a(t tVar, ca caVar) {
        a aVar;
        aVar = this.f931a.get(tVar.l().toString());
        if (aVar == null) {
            a.C0030a a2 = caVar.a();
            aVar = new a(a2.f702a, a2.b);
            this.f931a.put(tVar.l().toString(), aVar);
        }
        return aVar;
    }

    public boolean a(a.C0030a aVar, ca caVar) {
        if (aVar.b <= caVar.a().b) {
            return false;
        }
        caVar.a(aVar).h();
        return true;
    }

    public synchronized void b(a.C0030a aVar, ca caVar) {
        caVar.a(aVar).h();
    }
}
