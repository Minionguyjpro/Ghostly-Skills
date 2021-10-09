package com.tappx.a;

import android.content.Context;
import com.tappx.a.d1;
import com.tappx.a.v;

public class l1 extends d1<z1> {
    /* access modifiers changed from: private */
    public d1.b f;
    private final v g;
    private c1 h;
    /* access modifiers changed from: private */
    public v.b i;

    class a implements m<y1> {
        a() {
        }

        public void a(y1 y1Var) {
            v.b unused = l1.this.i = null;
            l1.this.a(y1Var);
            j0.d("hxsTS1PgJe7SvMvbIVXAleqYGWt1TgQOogRt9pTwP9Y", new Object[0]);
        }
    }

    class b implements h<v.a> {
        b() {
        }

        public void a(v.a aVar) {
            j0.d("hxsTS1PgJe7SvMvbIVXAlWNuK93hkAa0eyf9OlSh3dE", new Object[0]);
            v.b unused = l1.this.i = null;
            if (l1.this.f != null) {
                l1.this.f.a(a2.NO_FILL);
            }
        }
    }

    public static class c implements d1.a<z1> {

        /* renamed from: a  reason: collision with root package name */
        private final t2 f504a;
        private final v b;

        public c(t2 t2Var, v vVar) {
            this.f504a = t2Var;
            this.b = vVar;
        }

        public d1<z1> a() {
            return new l1(this.f504a, this.b);
        }

        public boolean a(u1 u1Var) {
            return u1Var instanceof z1;
        }
    }

    l1(t2 t2Var, v vVar) {
        super(t2Var);
        this.g = vVar;
    }

    /* access modifiers changed from: protected */
    public void e() {
        c1 c1Var = this.h;
        if (c1Var != null) {
            c1Var.a();
        }
        v.b bVar = this.i;
        if (bVar != null) {
            this.g.a(bVar);
        }
        this.h = null;
    }

    public void g() {
        c1 c1Var = this.h;
        if (c1Var != null) {
            c1Var.b();
        }
    }

    private void b(y1 y1Var) {
        int i2 = n.d;
        if (y1Var.k() > 0) {
            i2 = y1Var.k();
        }
        a((long) i2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(Context context, d1.b bVar, z1 z1Var) {
        j0.d("EecDzDUbtS5qsctGaW8eD9qka7saamJrDJfaB/3470s", new Object[0]);
        this.f = bVar;
        this.h = new c1(context);
        this.i = this.g.a(z1Var, (m<y1>) new a(), (h<v.a>) new b());
    }

    /* access modifiers changed from: private */
    public void a(y1 y1Var) {
        b(y1Var);
        d().a(y1Var.f());
        this.h.a(y1Var, this.f, this);
    }

    /* access modifiers changed from: protected */
    public long a(z1 z1Var) {
        if (z1Var.i() > 0) {
            return (long) z1Var.i();
        }
        return super.a(z1Var);
    }
}
