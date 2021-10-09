package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.a;
import com.google.android.play.core.internal.ca;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;
import com.google.android.play.core.splitinstall.p;
import java.util.concurrent.Executor;

public final class j implements ce<i> {

    /* renamed from: a  reason: collision with root package name */
    private final ce<au> f1096a;
    private final ce<t> b;
    private final ce<ar> c;
    private final ce<p> d;
    private final ce<ca> e;
    private final ce<bo> f;
    private final ce<be> g;
    private final ce<Executor> h;
    private final ce<a> i;

    public j(ce<au> ceVar, ce<t> ceVar2, ce<ar> ceVar3, ce<p> ceVar4, ce<ca> ceVar5, ce<bo> ceVar6, ce<be> ceVar7, ce<Executor> ceVar8, ce<a> ceVar9) {
        this.f1096a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
        this.e = ceVar5;
        this.f = ceVar6;
        this.g = ceVar7;
        this.h = ceVar8;
        this.i = ceVar9;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        au a2 = this.f1096a.a();
        ca<t> c2 = cc.c(this.b);
        ar a3 = this.c.a();
        ca a4 = this.e.a();
        bo a5 = this.f.a();
        bo boVar = a5;
        return new i(a2, c2, a3, this.d.a(), a4, boVar, this.g.a(), cc.c(this.h), this.i.a());
    }
}
