package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;
import java.util.List;

public class al extends af {

    /* renamed from: a  reason: collision with root package name */
    private final bp f782a;
    private final cx b;

    protected al(t tVar, bp bpVar, cx cxVar) {
        super(tVar);
        this.f782a = bpVar;
        this.b = cxVar;
    }

    public boolean a(h hVar) {
        t a2 = a();
        if (!a2.C().d() || !a2.A()) {
            return false;
        }
        br c = this.f782a.c();
        List<cw> a3 = this.b.a(a().m(), c.a());
        if (a3 == null) {
            a2.q();
            return false;
        }
        a2.f(h.a(hVar, a3));
        c.a(a3);
        return false;
    }
}
