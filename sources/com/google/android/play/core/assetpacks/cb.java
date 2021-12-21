package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.a;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;
import java.util.concurrent.Executor;

public final class cb implements ce<ca> {

    /* renamed from: a  reason: collision with root package name */
    private final ce f1065a;
    private final ce b;
    private final ce c;
    private final ce d;
    private final /* synthetic */ int e = 0;

    public cb(ce<au> ceVar, ce<t> ceVar2, ce<bo> ceVar3, ce<Executor> ceVar4) {
        this.f1065a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
    }

    public cb(ce<au> ceVar, ce<t> ceVar2, ce<ar> ceVar3, ce<bo> ceVar4, byte[] bArr) {
        this.f1065a = ceVar;
        this.b = ceVar2;
        this.c = ceVar3;
        this.d = ceVar4;
    }

    public cb(ce<ca> ceVar, ce<au> ceVar2, ce<bc> ceVar3, ce<a> ceVar4, char[] cArr) {
        this.c = ceVar;
        this.b = ceVar2;
        this.f1065a = ceVar3;
        this.d = ceVar4;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.e;
        if (i == 0) {
            Object a2 = this.f1065a.a();
            return new ca((au) a2, cc.c(this.b), (bo) this.c.a(), cc.c(this.d));
        } else if (i != 1) {
            Object a3 = this.c.a();
            Object a4 = this.b.a();
            return new cd((ca) a3, (au) a4, (bc) this.f1065a.a(), (a) this.d.a());
        } else {
            return new bj((au) this.f1065a.a(), cc.c(this.b), cc.c(this.c), (bo) this.d.a());
        }
    }
}
