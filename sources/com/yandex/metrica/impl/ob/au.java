package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import com.yandex.metrica.d;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.utils.n;

public class au extends af {
    public au(t tVar) {
        super(tVar);
    }

    public boolean a(h hVar) {
        b(hVar);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(h hVar) {
        String k = hVar.k();
        d a2 = n.a(k);
        String g = a().g();
        d a3 = n.a(g);
        if (!a2.equals(a3)) {
            boolean z = true;
            if (TextUtils.isEmpty(a2.a()) && !TextUtils.isEmpty(a3.a())) {
                hVar.a(g);
                a(hVar, n.a.LOGOUT);
            } else {
                if (!TextUtils.isEmpty(a2.a()) && TextUtils.isEmpty(a3.a())) {
                    a(hVar, n.a.LOGIN);
                } else {
                    if (TextUtils.isEmpty(a2.a()) || a2.a().equals(a3.a())) {
                        z = false;
                    }
                    if (z) {
                        a(hVar, n.a.SWITCH);
                    } else {
                        a(hVar, n.a.UPDATE);
                    }
                }
            }
            a().a(k);
        }
        if (!a().j().C()) {
            a().c();
        }
    }

    private void a(h hVar, n.a aVar) {
        hVar.c(n.a(aVar));
        a().d(hVar);
    }
}
