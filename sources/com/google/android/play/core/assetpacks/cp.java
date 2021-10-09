package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;

public final class cp implements ce<co> {

    /* renamed from: a  reason: collision with root package name */
    private final ce f1076a;
    private final /* synthetic */ int b = 0;

    public cp(ce<au> ceVar) {
        this.f1076a = ceVar;
    }

    public cp(ce<t> ceVar, byte[] bArr) {
        this.f1076a = ceVar;
    }

    public cp(ce<Context> ceVar, char[] cArr) {
        this.f1076a = ceVar;
    }

    public cp(ce<au> ceVar, short[] sArr) {
        this.f1076a = ceVar;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        int i = this.b;
        return i != 0 ? i != 1 ? i != 2 ? new dd((au) this.f1076a.a()) : new cv(((p) this.f1076a).a()) : new bc(cc.c(this.f1076a)) : new co((au) this.f1076a.a());
    }
}
