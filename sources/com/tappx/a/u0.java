package com.tappx.a;

import android.content.Context;
import com.tappx.a.d1;
import com.tappx.a.v0;

public class u0 extends d1<x1> {
    /* access modifiers changed from: private */
    public x1 f;
    private v0 g;

    class a implements Runnable {
        a() {
        }

        public void run() {
            u0.this.d().a(u0.this.f.f());
        }
    }

    public static class b implements d1.a<x1> {

        /* renamed from: a  reason: collision with root package name */
        private final t2 f592a;

        public b(t2 t2Var) {
            this.f592a = t2Var;
        }

        public d1<x1> a() {
            return new u0(this.f592a);
        }

        public boolean a(u1 u1Var) {
            return u1Var instanceof x1;
        }
    }

    u0(t2 t2Var) {
        super(t2Var);
    }

    /* access modifiers changed from: protected */
    public void e() {
        v0 v0Var = this.g;
        if (v0Var != null) {
            try {
                v0Var.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void g() {
        v0 v0Var = this.g;
        if (v0Var != null) {
            try {
                v0Var.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(Context context, d1.b bVar, x1 x1Var) {
        String h = x1Var.h();
        this.f = x1Var;
        try {
            j0.d("EecDzDUbtS5qsctGaW8eDzBBqoEJJw2EaiO9g7mmMkc", new Object[0]);
            v0 a2 = v0.a.a(context);
            this.g = a2;
            a2.a(h);
            this.g.a(bVar, this, new a());
            this.g.loadAd();
        } catch (Exception | NoClassDefFoundError e) {
            e.printStackTrace();
            bVar.a(a2.NO_FILL);
        }
    }

    /* access modifiers changed from: protected */
    public long a(x1 x1Var) {
        long j = (long) x1Var.j();
        if (j > 0) {
            return j;
        }
        return super.a(x1Var);
    }
}
