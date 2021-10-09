package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.ca;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;
import java.util.concurrent.Executor;

public final class bm implements ce<bl> {

    /* renamed from: a  reason: collision with root package name */
    private final ce f1051a;
    private final ce b;
    private final ce c;
    private final ce d;
    private final ce e;
    private final ce f;
    private final ce g;
    private final ce h;
    private final /* synthetic */ int i = 0;

    public bm(ce<ca> ceVar, ce<t> ceVar2, ce<bj> ceVar3, ce<dd> ceVar4, ce<co> ceVar5, ce<ct> ceVar6, ce<cx> ceVar7, ce<cd> ceVar8) {
        this.f1051a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
        this.e = ceVar5;
        this.f = ceVar6;
        this.g = ceVar7;
        this.h = ceVar8;
    }

    public bm(ce<Context> ceVar, ce<ca> ceVar2, ce<bl> ceVar3, ce<t> ceVar4, ce<bo> ceVar5, ce<be> ceVar6, ce<Executor> ceVar7, ce<Executor> ceVar8, byte[] bArr) {
        this.f1051a = ceVar;
        this.g = ceVar2;
        this.h = ceVar3;
        this.b = ceVar4;
        this.e = ceVar5;
        this.f = ceVar6;
        this.c = ceVar7;
        this.d = ceVar8;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        if (this.i != 0) {
            Context b2 = ((p) this.f1051a).a();
            Object a2 = this.g.a();
            Object a3 = this.h.a();
            ca c2 = cc.c(this.b);
            Object a4 = this.e.a();
            Object a5 = this.f.a();
            return new ar(b2, (ca) a2, (bl) a3, c2, (bo) a4, (be) a5, cc.c(this.c), cc.c(this.d));
        }
        Object a6 = this.f1051a.a();
        return new bl((ca) a6, cc.c(this.b), (bj) this.c.a(), (dd) this.d.a(), (co) this.e.a(), (ct) this.f.a(), (cx) this.g.a(), (cd) this.h.a());
    }
}
