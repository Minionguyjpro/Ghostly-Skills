package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p;

public class ap extends af {

    /* renamed from: a  reason: collision with root package name */
    private by f784a;

    public ap(t tVar) {
        super(tVar);
        this.f784a = tVar.C();
    }

    public boolean a(h hVar) {
        h hVar2;
        t a2 = a();
        if (this.f784a.c()) {
            return false;
        }
        if (a2.j().y()) {
            hVar2 = h.a(hVar, p.a.EVENT_TYPE_APP_UPDATE);
        } else {
            hVar2 = h.a(hVar, p.a.EVENT_TYPE_INIT);
        }
        a2.d(hVar2.c(this.f784a.d("")));
        a2.b(true);
        this.f784a.a();
        this.f784a.e();
        return false;
    }
}
