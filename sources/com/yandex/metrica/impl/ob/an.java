package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p;

public class an extends af {
    public an(t tVar) {
        super(tVar);
    }

    public boolean a(h hVar) {
        t a2 = a();
        if (!a2.j().C()) {
            return false;
        }
        String b = b();
        if (bi.a(b)) {
            return false;
        }
        c();
        a2.a(new h(hVar).c("").b(b).a(p.a.EVENT_TYPE_REFERRER_DEPRECATED.a()));
        return false;
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return a().t();
    }

    /* access modifiers changed from: package-private */
    public void c() {
        a().u();
    }
}
