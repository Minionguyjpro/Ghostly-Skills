package com.tappx.a;

import android.content.Context;
import android.view.View;
import com.tappx.a.n;
import com.tappx.a.n1;
import com.tappx.a.u1;

public abstract class m0<T extends u1> implements n1.b {

    /* renamed from: a  reason: collision with root package name */
    private final t2 f514a;
    private final n1 b;
    private m0<T>.d c;
    private T d;
    private boolean e;

    public interface b<T extends u1> {
        m0<T> a();

        boolean a(u1 u1Var);
    }

    public interface c {
        void a();

        void a(View view);

        void a(a2 a2Var);

        void b();

        void c();

        void d();
    }

    private class d implements c {

        /* renamed from: a  reason: collision with root package name */
        private final c f515a;

        public void a(View view) {
            m0.this.a(true);
            this.f515a.a(view);
        }

        public void b() {
            this.f515a.b();
        }

        public void c() {
            this.f515a.c();
        }

        public void d() {
            this.f515a.d();
        }

        private d(c cVar) {
            this.f515a = cVar;
        }

        public void a(a2 a2Var) {
            m0.this.a(true);
            this.f515a.a(a2Var);
        }

        public void a() {
            this.f515a.a();
        }
    }

    protected m0(t2 t2Var, n1 n1Var) {
        this.f514a = t2Var;
        this.b = n1Var;
        n1Var.a((n1.b) this);
    }

    public void a(Context context, c cVar, u1 u1Var) {
        try {
            this.d = u1Var;
            a(false);
            this.c = new d(cVar);
            f();
            j0.d("w73w5GD8aw/6JbEwVsPDUQ", getClass().getSimpleName());
            b(context, this.c, this.d);
        } catch (ClassCastException unused) {
            cVar.a(a2.INTERNAL_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.b.a();
        e();
    }

    /* access modifiers changed from: protected */
    public abstract void b(Context context, c cVar, T t);

    /* access modifiers changed from: package-private */
    public T c() {
        return this.d;
    }

    public t2 d() {
        return this.f514a;
    }

    /* access modifiers changed from: protected */
    public abstract void e();

    /* access modifiers changed from: package-private */
    public void f() {
        a(a(this.d));
    }

    public m0(t2 t2Var) {
        this(t2Var, new n1());
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        if (j < n.a.f524a) {
            j0.d("/K518OsQwGWEySte999XTohBdjGQhBkPInQIUsTjG/cUwA8AiN+9hbf5gwErXHcf", Long.valueOf(j));
            j = n.a.f524a;
        } else if (j > n.a.b) {
            j0.d("wUWo9wuOBqc42QHm8/JVjGXXMTT2DoYHEa3wguYezUW0KEhBaolGwT3KPMo6Sz+d", Long.valueOf(j));
            j = n.a.b;
        }
        this.b.a(j);
    }

    /* access modifiers changed from: protected */
    public long a(T t) {
        return n.f523a;
    }

    public void a() {
        if (!this.e) {
            j0.d("tJ/RDdwmde5sNRNl2OtVTfCw4OuAfiMIPu/zkSLGIT2zwNm3C7thqGQX04tsdosL", new Object[0]);
            m0<T>.d dVar = this.c;
            if (dVar != null) {
                dVar.a(a2.TIMEOUT);
            }
        }
    }

    public void a(boolean z) {
        this.e = z;
        if (z) {
            this.b.a();
        }
    }
}
