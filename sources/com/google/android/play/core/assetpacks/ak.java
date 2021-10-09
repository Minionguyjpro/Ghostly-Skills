package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.i;

final class ak extends ag<Void> {
    final int c;
    final String d;
    final int e;
    final /* synthetic */ an f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ak(an anVar, i<Void> iVar, int i, String str, int i2) {
        super(anVar, iVar);
        this.f = anVar;
        this.c = i;
        this.d = str;
        this.e = i2;
    }

    public final void g(Bundle bundle) {
        this.f.e.b();
        int i = bundle.getInt("error_code");
        an.f1029a.b("onError(%d), retrying notifyModuleCompleted...", Integer.valueOf(i));
        int i2 = this.e;
        if (i2 > 0) {
            this.f.y(this.c, this.d, i2 - 1);
        }
    }
}
