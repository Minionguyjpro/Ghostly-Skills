package com.tappx.a;

import android.content.Context;
import com.tappx.a.m0;

public final class z0 extends m0<y1> {
    private a1 f;

    public static final class a implements m0.b<y1> {

        /* renamed from: a  reason: collision with root package name */
        private final t2 f633a;

        public a(t2 t2Var) {
            this.f633a = t2Var;
        }

        public m0<y1> a() {
            return new z0(this.f633a);
        }

        public boolean a(u1 u1Var) {
            return u1Var instanceof y1;
        }
    }

    z0(t2 t2Var) {
        super(t2Var);
    }

    /* access modifiers changed from: protected */
    public void e() {
        a1 a1Var = this.f;
        if (a1Var != null) {
            a1Var.a();
        }
        this.f = null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(Context context, m0.c cVar, y1 y1Var) {
        d().a(y1Var.f());
        a1 a1Var = new a1(context);
        this.f = a1Var;
        a1Var.a(y1Var, cVar);
    }

    /* access modifiers changed from: protected */
    public long a(y1 y1Var) {
        int k = y1Var.k();
        if (k > 0) {
            return (long) k;
        }
        return super.a(y1Var);
    }
}
