package com.appnext.base.operations;

import android.os.Bundle;
import com.appnext.base.a;
import com.appnext.base.b.d;
import com.appnext.base.b.j;

public abstract class c extends a {
    public final void aD() {
    }

    public c(com.appnext.base.a.b.c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
    }

    public final void aC() {
        boolean z = false;
        try {
            Object a2 = j.a(d.fo, d.a.Boolean);
            if (a2 != null && (a2 instanceof Boolean)) {
                z = !((Boolean) a2).booleanValue();
            }
            if (aE()) {
                if (!z) {
                    av();
                    return;
                }
            }
            a(new a(a.C0036a.NoPermission$1d8b5b4a));
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public d.a aG() {
        return d.a.String;
    }
}
