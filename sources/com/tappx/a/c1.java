package com.tappx.a;

import android.content.Context;
import com.tappx.a.c5;
import com.tappx.a.d1;

public class c1 {

    /* renamed from: a  reason: collision with root package name */
    private final Context f389a;
    /* access modifiers changed from: private */
    public d1.b b;
    private d5 c;
    /* access modifiers changed from: private */
    public d1 d;
    private c5.a e = new a();

    class a implements c5.a {
        a() {
        }

        public void a() {
            c1.this.b.a(a2.INTERNAL_ERROR);
        }

        public void b() {
            c1.this.b.b();
        }

        public void c() {
            c1.this.b.c();
        }

        public void d() {
            c1.this.b.d();
        }

        public void e() {
            c1.this.b.a(c1.this.d);
        }
    }

    public c1(Context context) {
        this.f389a = context;
    }

    public void a(y1 y1Var, d1.b bVar, d1 d1Var) {
        this.b = bVar;
        this.d = d1Var;
        d5 d5Var = new d5();
        this.c = d5Var;
        d5Var.a(this.e);
        this.c.a(this.f389a, y1Var.h(), new f5().a(y1Var.i()).c(y1Var.o()).c(y1Var.l()).b(y1Var.j()).b(y1Var.n()).a(y1Var.m()).a(a(y1Var.d())).a(b3.a(y1Var.a())));
    }

    public void b() {
        d5 d5Var = this.c;
        if (d5Var != null) {
            d5Var.b();
        }
    }

    private j3 a(int i) {
        if (i == 1) {
            return j3.PORTRAIT;
        }
        if (i != 2) {
            return j3.ANY;
        }
        return j3.LANDSCAPE;
    }

    public void a() {
        d5 d5Var = this.c;
        if (d5Var != null) {
            d5Var.a();
        }
    }
}
