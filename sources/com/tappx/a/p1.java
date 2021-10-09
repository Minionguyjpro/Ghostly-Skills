package com.tappx.a;

import android.content.Context;

abstract class p1 implements o1 {

    /* renamed from: a  reason: collision with root package name */
    private int f548a = 0;
    private w1 b;
    private Context c;

    p1() {
    }

    private void f() {
        c();
        if (this.b.b().size() <= this.f548a) {
            a(a2.NO_FILL);
            return;
        }
        u1 u1Var = this.b.b().get(this.f548a);
        this.f548a++;
        j0.a(f.b("mo5jy7IL/t1GLb3J/P8gjQ") + u1Var.b(), new Object[0]);
        j0.d("w73w5GD8aw/6JbEwVsPDUQ", u1Var.b());
        if (!a(this.c, u1Var)) {
            f();
        }
    }

    public void a(Context context, w1 w1Var) {
        a(w1Var);
        this.c = context;
        this.b = w1Var;
        this.f548a = 0;
        f();
    }

    /* access modifiers changed from: protected */
    public abstract void a(a2 a2Var);

    /* access modifiers changed from: protected */
    public abstract boolean a(Context context, u1 u1Var);

    /* access modifiers changed from: protected */
    public abstract void b();

    /* access modifiers changed from: protected */
    public abstract void c();

    /* access modifiers changed from: protected */
    public void d() {
        f();
    }

    public void destroy() {
        c();
        b();
    }

    /* access modifiers changed from: package-private */
    public void e() {
    }

    private void a(w1 w1Var) {
        String str = "{";
        for (u1 u1Var : w1Var.b()) {
            str = str + u1Var.getClass().getSimpleName() + ",";
        }
        j0.d("vowRFCKLTs9aEktGgLPt1r38zreZaMrbDBiCU39LXJU", str + "}");
    }

    public void a() {
        c();
    }
}
