package com.tappx.a;

import android.content.Context;
import com.tappx.a.n;
import com.tappx.a.n1;
import com.tappx.a.u1;

public abstract class d1<T extends u1> implements n1.b {

    /* renamed from: a  reason: collision with root package name */
    private final t2 f406a;
    private final n1 b;
    private T c;
    private boolean d;
    private d1<T>.c e;

    public interface a<T extends u1> {
        d1<T> a();

        boolean a(u1 u1Var);
    }

    public interface b {
        void a();

        void a(a2 a2Var);

        void a(d1 d1Var);

        void b();

        void c();

        void d();
    }

    protected d1(t2 t2Var) {
        this(t2Var, new n1());
    }

    /* access modifiers changed from: protected */
    public long a(T t) {
        return 15000;
    }

    public void a(Context context, b bVar, u1 u1Var) {
        try {
            this.c = u1Var;
            a(false);
            this.e = new c(bVar);
            f();
            j0.d("w73w5GD8aw/6JbEwVsPDUQ", getClass().getSimpleName());
            b(context, this.e, this.c);
        } catch (ClassCastException unused) {
            bVar.a(a2.INTERNAL_ERROR);
        }
    }

    public void b() {
        this.b.a();
        e();
    }

    /* access modifiers changed from: protected */
    public abstract void b(Context context, b bVar, T t);

    /* access modifiers changed from: package-private */
    public T c() {
        return this.c;
    }

    public t2 d() {
        return this.f406a;
    }

    /* access modifiers changed from: protected */
    public abstract void e();

    /* access modifiers changed from: package-private */
    public void f() {
        a(a(this.c));
    }

    public abstract void g();

    private class c implements b {

        /* renamed from: a  reason: collision with root package name */
        private final b f407a;

        public c(b bVar) {
            this.f407a = bVar;
        }

        public void a(d1 d1Var) {
            d1.this.a(true);
            this.f407a.a(d1Var);
        }

        public void b() {
            d1.this.d().a(d1.this.c().g());
            this.f407a.b();
        }

        public void c() {
            d1.this.d().a(d1.this.c().e());
            this.f407a.c();
        }

        public void d() {
            this.f407a.d();
        }

        public void a(a2 a2Var) {
            d1.this.a(true);
            this.f407a.a(a2Var);
        }

        public void a() {
            this.f407a.a();
        }
    }

    protected d1(t2 t2Var, n1 n1Var) {
        this.f406a = t2Var;
        this.b = n1Var;
        n1Var.a((n1.b) this);
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        if (j < n.a.c) {
            j0.d("tJ/RDdwmde5sNRNl2OtVTfCw4OuAfiMIPu/zkSLGIT2zwNm3C7thqGQX04tsdosL", Long.valueOf(j));
            j = n.a.c;
        } else if (j > n.a.d) {
            j0.d("w73w5GD8aw/6JbEwVsPDUQ", Long.valueOf(j));
            j = n.a.d;
        }
        this.b.a(j);
    }

    public void a(boolean z) {
        this.d = z;
        if (z) {
            this.b.a();
        }
    }

    public void a() {
        if (!this.d) {
            j0.d("wUWo9wuOBqc42QHm8/JVjGXXMTT2DoYHEa3wguYezUW0KEhBaolGwT3KPMo6Sz+d", new Object[0]);
            d1<T>.c cVar = this.e;
            if (cVar != null) {
                cVar.a(a2.TIMEOUT);
            }
        }
    }
}
