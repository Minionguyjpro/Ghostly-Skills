package com.tappx.a;

import android.content.Context;
import com.tappx.a.d1;

public class b1 extends d1<y1> {
    private c1 f;

    public static class a implements d1.a<y1> {

        /* renamed from: a  reason: collision with root package name */
        private final t2 f377a;

        public a(t2 t2Var) {
            this.f377a = t2Var;
        }

        public d1<y1> a() {
            return new b1(this.f377a);
        }

        public boolean a(u1 u1Var) {
            return u1Var instanceof y1;
        }
    }

    b1(t2 t2Var) {
        super(t2Var);
    }

    /* access modifiers changed from: protected */
    public void e() {
        c1 c1Var = this.f;
        if (c1Var != null) {
            c1Var.a();
        }
    }

    public void g() {
        c1 c1Var = this.f;
        if (c1Var != null) {
            c1Var.b();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(Context context, d1.b bVar, y1 y1Var) {
        d().a(y1Var.f());
        c1 c1Var = new c1(context);
        this.f = c1Var;
        c1Var.a(y1Var, bVar, this);
    }

    /* access modifiers changed from: protected */
    public long a(y1 y1Var) {
        int k = y1Var.k();
        if (k > 0) {
            return (long) k;
        }
        return (long) n.d;
    }
}
