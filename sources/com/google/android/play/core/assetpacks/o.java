package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.bh;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;

public final class o implements ce<t> {

    /* renamed from: a  reason: collision with root package name */
    private final ce<Context> f1102a;
    private final ce<an> b;
    private final ce<cj> c;

    public o(ce<Context> ceVar, ce<an> ceVar2, ce<cj> ceVar3) {
        this.f1102a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        t tVar = (t) (l.b(((p) this.f1102a).a()) == null ? cc.c(this.b).a() : cc.c(this.c).a());
        bh.k(tVar);
        return tVar;
    }
}
