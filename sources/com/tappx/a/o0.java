package com.tappx.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.tappx.a.m0;
import com.tappx.a.n0;
import java.lang.ref.WeakReference;
import java.util.List;

public class o0 extends p1 implements n0 {
    /* access modifiers changed from: private */
    public n0.a d;
    private final List<m0.b> e;
    /* access modifiers changed from: private */
    public m0 f;
    /* access modifiers changed from: private */
    public m0 g;
    /* access modifiers changed from: private */
    public b h;
    /* access modifiers changed from: private */
    public b i;

    private final class b implements m0.c {

        /* renamed from: a  reason: collision with root package name */
        private final m0 f536a;
        private WeakReference<View> b;

        private boolean h() {
            return this != o0.this.i;
        }

        private boolean i() {
            return this != o0.this.h;
        }

        public void a(View view) {
            if (!i()) {
                o0.this.e();
                m0 unused = o0.this.f = null;
                b unused2 = o0.this.h = null;
                o0.this.b();
                this.b = new WeakReference<>(view);
                b unused3 = o0.this.i = this;
                m0 unused4 = o0.this.g = f();
                o0.this.d.a(e(), view);
            }
        }

        public void b() {
            if (!h()) {
                o0.this.d.c(e());
            }
        }

        public void c() {
            if (!h()) {
                o0.this.d.b(e());
            }
        }

        public void d() {
            if (!h()) {
                o0.this.d.a(e());
            }
        }

        public u1 e() {
            return this.f536a.c();
        }

        public m0 f() {
            return this.f536a;
        }

        /* access modifiers changed from: protected */
        public View g() {
            WeakReference<View> weakReference = this.b;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        private b(m0 m0Var) {
            this.f536a = m0Var;
        }

        public void a(a2 a2Var) {
            if (!i()) {
                o0.this.d();
            }
        }

        public void a() {
            boolean h = h();
        }
    }

    public o0(List<m0.b> list) {
        this.e = list;
    }

    /* access modifiers changed from: protected */
    public void c() {
        m0 m0Var = this.f;
        if (m0Var != null) {
            m0Var.b();
            this.f = null;
            this.h = null;
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.g != null) {
            View g2 = this.i.g();
            if (g2 != null) {
                a(g2);
            }
            this.g.b();
            this.g = null;
            this.i = null;
        }
    }

    public void a(n0.a aVar) {
        this.d = aVar;
    }

    /* access modifiers changed from: protected */
    public boolean a(Context context, u1 u1Var) {
        for (m0.b next : this.e) {
            if (next.a(u1Var)) {
                m0 a2 = next.a();
                this.f = a2;
                b a3 = a(a2);
                this.h = a3;
                this.f.a(context, a3, u1Var);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void a(a2 a2Var) {
        n0.a aVar = this.d;
        if (aVar != null) {
            aVar.a(a2Var);
        }
    }

    private void a(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    private b a(m0 m0Var) {
        return new b(m0Var);
    }
}
