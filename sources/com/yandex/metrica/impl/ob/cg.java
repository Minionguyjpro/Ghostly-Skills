package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.ob.ce;

public class cg extends ce {
    public /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public /* bridge */ /* synthetic */ ch b() {
        return super.b();
    }

    public /* bridge */ /* synthetic */ be.a c() {
        return super.c();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    cg(be.a aVar, ch chVar, ch chVar2) {
        super(aVar, chVar == null ? chVar2 : chVar);
    }

    public ce.a a(cj cjVar) {
        ch b = b();
        if (cjVar.equals(b.d())) {
            return ce.a.THIS;
        }
        if (b.d() != null) {
            return ce.a.OTHER;
        }
        if (b.b()) {
            return ce.a.OTHER;
        }
        return ce.a.UNKNOWN;
    }
}
