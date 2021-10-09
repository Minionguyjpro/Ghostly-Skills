package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.security.GeneralSecurityException;

class eq implements ey {

    /* renamed from: a  reason: collision with root package name */
    private final fd f886a;
    private ey b;
    private fh c;

    eq(fe feVar, fd fdVar) {
        fs fsVar;
        this.f886a = fdVar;
        if (fdVar.e()) {
            boolean a2 = fg.a(fdVar);
            boolean f = fdVar.f();
            this.b = new et();
            if (f) {
                if (a2) {
                    fsVar = a(feVar, fdVar);
                } else {
                    fsVar = fa.c(feVar);
                }
                this.c = new fh(feVar, this.b, fsVar, fdVar);
            }
        } else {
            boolean a3 = fg.a(fdVar);
            boolean z = true;
            if (!(86400000 != fdVar.a()) && !a3) {
                z = false;
            }
            boolean f2 = fdVar.f();
            if (a3) {
                try {
                    this.b = new em(feVar, fdVar.b());
                } catch (IOException unused) {
                    this.b = new et();
                }
            } else {
                this.b = fa.b(feVar);
            }
            if (f2) {
                if (z) {
                    this.c = new fh(feVar, this.b, a(feVar, fdVar), fdVar);
                } else {
                    this.c = fa.a(feVar);
                }
            }
        }
        fh fhVar = this.c;
        if (fhVar != null) {
            fhVar.a(fdVar.d());
        }
    }

    /* access modifiers changed from: package-private */
    public fh d() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (this.f886a.f()) {
            this.c.c();
        }
    }

    public fb a() {
        return this.b.a();
    }

    public fb b() {
        return this.b.b();
    }

    public fb c() {
        return this.b.c();
    }

    private static fs a(fe feVar, fd fdVar) {
        if (!fg.a(fdVar)) {
            return feVar.d();
        }
        try {
            return feVar.a(fdVar.c());
        } catch (IOException | GeneralSecurityException unused) {
            return null;
        }
    }
}
