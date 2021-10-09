package com.tappx.a;

import android.content.Context;
import com.tappx.a.m0;
import com.tappx.a.v;

public class k1 extends m0<z1> {
    private final v f;
    private a1 g;
    /* access modifiers changed from: private */
    public m0.c h;
    private v.b i;

    class a implements m<y1> {
        a() {
        }

        public void a(y1 y1Var) {
            k1.this.b(y1Var);
            k1.this.a(y1Var);
            j0.d("hxsTS1PgJe7SvMvbIVXAleqYGWt1TgQOogRt9pTwP9Y", new Object[0]);
        }
    }

    class b implements h<v.a> {
        b() {
        }

        public void a(v.a aVar) {
            j0.d("wA68d1p5v8MSlvKrjle67r38zreZaMrbDBiCU39LXJU", aVar);
            k1.this.h.a(a2.NO_FILL);
        }
    }

    public static final class c implements m0.b<z1> {

        /* renamed from: a  reason: collision with root package name */
        private final t2 f490a;
        private final v b;

        public c(t2 t2Var, v vVar) {
            this.f490a = t2Var;
            this.b = vVar;
        }

        public m0<z1> a() {
            return new k1(this.f490a, this.b);
        }

        public boolean a(u1 u1Var) {
            return u1Var instanceof z1;
        }
    }

    k1(t2 t2Var, v vVar) {
        super(t2Var);
        this.f = vVar;
    }

    /* access modifiers changed from: protected */
    public void e() {
        v.b bVar = this.i;
        if (bVar != null) {
            this.f.a(bVar);
        }
        a1 a1Var = this.g;
        if (a1Var != null) {
            a1Var.a();
        }
        this.g = null;
    }

    /* access modifiers changed from: private */
    public void b(y1 y1Var) {
        int i2 = n.d;
        if (y1Var.k() > 0) {
            i2 = y1Var.k();
        }
        a((long) i2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(Context context, m0.c cVar, z1 z1Var) {
        j0.d("7qjY7245E0dfSy30XptPQ6Kjsb63PLX1qtOqZ64iM50", new Object[0]);
        this.h = cVar;
        this.g = new a1(context);
        this.i = this.f.a(z1Var, (m<y1>) new a(), (h<v.a>) new b());
    }

    /* access modifiers changed from: private */
    public void a(y1 y1Var) {
        d().a(y1Var.f());
        this.g.a(y1Var, this.h);
    }

    /* access modifiers changed from: protected */
    public long a(z1 z1Var) {
        if (z1Var.i() > 0) {
            return (long) z1Var.i();
        }
        return super.a(z1Var);
    }
}
