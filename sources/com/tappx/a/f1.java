package com.tappx.a;

import android.content.Context;
import com.tappx.a.d1;
import com.tappx.a.e1;
import java.util.List;

public class f1 extends p1 implements e1 {
    /* access modifiers changed from: private */
    public e1.a d;
    private final List<d1.a> e;
    /* access modifiers changed from: private */
    public d1 f;
    /* access modifiers changed from: private */
    public d1 g;
    /* access modifiers changed from: private */
    public a h;
    /* access modifiers changed from: private */
    public a i;

    public f1(List<d1.a> list) {
        this.e = list;
    }

    /* access modifiers changed from: protected */
    public void c() {
        d1 d1Var = this.f;
        if (d1Var != null) {
            d1Var.b();
            this.f = null;
            this.h = null;
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        d1 d1Var = this.g;
        if (d1Var != null) {
            d1Var.b();
            this.g = null;
            this.i = null;
        }
    }

    public void a(e1.a aVar) {
        this.d = aVar;
    }

    /* access modifiers changed from: protected */
    public boolean a(Context context, u1 u1Var) {
        for (d1.a next : this.e) {
            if (next.a(u1Var)) {
                d1 a2 = next.a();
                this.f = a2;
                a a3 = a(a2);
                this.h = a3;
                this.f.a(context, a3, u1Var);
                return true;
            }
        }
        return false;
    }

    private final class a implements d1.b {

        /* renamed from: a  reason: collision with root package name */
        private final d1 f436a;

        public a(d1 d1Var) {
            this.f436a = d1Var;
        }

        private boolean g() {
            return this != f1.this.i;
        }

        private boolean h() {
            return this != f1.this.h;
        }

        public void a() {
        }

        public void a(d1 d1Var) {
            if (!h()) {
                f1.this.e();
                d1 unused = f1.this.f = null;
                a unused2 = f1.this.h = null;
                f1.this.b();
                a unused3 = f1.this.i = this;
                d1 unused4 = f1.this.g = f();
                f1.this.d.a(e(), f());
            }
        }

        public void b() {
        }

        public void c() {
            f1.this.d.a(e());
        }

        public void d() {
            if (!g()) {
                f1.this.d.b(e());
            }
        }

        public u1 e() {
            return this.f436a.c();
        }

        public d1 f() {
            return this.f436a;
        }

        public void a(a2 a2Var) {
            if (!h()) {
                f1.this.d();
            }
        }
    }

    private a a(d1 d1Var) {
        return new a(d1Var);
    }

    /* access modifiers changed from: protected */
    public void a(a2 a2Var) {
        e1.a aVar = this.d;
        if (aVar != null) {
            aVar.a(a2Var);
        }
    }
}
