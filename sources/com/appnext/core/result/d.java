package com.appnext.core.result;

public final class d {
    private static d ik;

    /* renamed from: if  reason: not valid java name */
    private c f2if;

    private d() {
    }

    public static synchronized d br() {
        d dVar;
        synchronized (d.class) {
            if (ik == null) {
                ik = new d();
            }
            dVar = ik;
        }
        return dVar;
    }

    public final c bs() {
        return this.f2if;
    }

    public final void a(c cVar) {
        this.f2if = cVar;
    }
}
