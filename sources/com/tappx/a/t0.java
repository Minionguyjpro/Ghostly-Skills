package com.tappx.a;

import android.content.Context;
import com.tappx.a.m0;
import com.tappx.a.q0;

public class t0 extends m0<x1> {
    /* access modifiers changed from: private */
    public x1 f;
    private q0 g;

    class a implements Runnable {
        a() {
        }

        public void run() {
            t0.this.d().a(t0.this.f.f());
        }
    }

    public static final class b implements m0.b<x1> {

        /* renamed from: a  reason: collision with root package name */
        private final t2 f584a;

        public b(t2 t2Var) {
            this.f584a = t2Var;
        }

        public m0<x1> a() {
            return new t0(this.f584a);
        }

        public boolean a(u1 u1Var) {
            return u1Var instanceof x1;
        }
    }

    t0(t2 t2Var) {
        super(t2Var);
    }

    /* access modifiers changed from: protected */
    public void e() {
        q0 q0Var = this.g;
        if (q0Var != null) {
            w4.b(q0Var.a());
            try {
                this.g.a((m0.c) null, (Runnable) null);
                this.g.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(Context context, m0.c cVar, x1 x1Var) {
        j0.d("7qjY7245E0dfSy30XptPQ/SJdTfZfiiWf+eZ42wqMQY", new Object[0]);
        this.f = x1Var;
        String h = x1Var.h();
        int k = x1Var.k();
        int i = x1Var.i();
        try {
            q0 a2 = q0.a.a(context);
            this.g = a2;
            a2.a(h, k, i);
            this.g.a(cVar, new a());
            this.g.loadAd();
        } catch (Exception | NoClassDefFoundError e) {
            e.printStackTrace();
            cVar.a(a2.NO_FILL);
        }
    }
}
