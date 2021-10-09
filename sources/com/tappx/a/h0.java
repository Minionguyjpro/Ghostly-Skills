package com.tappx.a;

import android.content.Context;
import com.mopub.common.Constants;
import com.tappx.a.a0;
import com.tappx.a.f0;

public final class h0 implements b0 {

    /* renamed from: a  reason: collision with root package name */
    private final t5 f454a;
    private final e0 b;

    public h0(Context context) {
        this(j6.a(context), new e0());
    }

    public void a(d0<?> d0Var) {
        if (!a(d0Var.g())) {
            f0.a b2 = d0Var.b();
            if (b2 != null) {
                b2.a(new a0(a0.a.PARSE_ERROR));
                return;
            }
            return;
        }
        i0 i0Var = new i0(d0Var);
        i0Var.b((Object) d0Var);
        this.f454a.a(i0Var);
    }

    public void b(d0 d0Var) {
        if (!this.b.a((d0<?>) d0Var)) {
            this.f454a.a((Object) d0Var);
        }
    }

    h0(t5 t5Var, e0 e0Var) {
        this.b = e0Var;
        this.f454a = t5Var;
        e0Var.a((b0) this);
    }

    private boolean a(String str) {
        return str != null && str.startsWith(Constants.HTTP);
    }

    public void a(d0<?> d0Var, int i) {
        this.b.a(d0Var, (long) i);
    }
}
