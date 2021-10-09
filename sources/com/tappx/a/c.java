package com.tappx.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.tappx.a.g1;
import com.tappx.a.i1;
import com.tappx.a.j1;
import com.tappx.a.l;
import com.tappx.a.l0;
import com.tappx.a.m1;
import com.tappx.a.p0;
import com.tappx.a.q;
import com.tappx.a.s;
import com.tappx.a.u;
import com.tappx.a.y;

public class c {
    private static volatile c f;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final Context f383a;
    private final l<b0> b = new l<>(new a());
    private final l<t2> c = new l<>(new b());
    private final l<v> d = new l<>(new C0014c());
    private final l<d2> e = new l<>(new d());

    class a implements l.a<b0> {
        a() {
        }

        public b0 a() {
            return new h0(c.this.f383a);
        }
    }

    class b implements l.a<t2> {
        b() {
        }

        public t2 a() {
            return new u2(c.this.q());
        }
    }

    /* renamed from: com.tappx.a.c$c  reason: collision with other inner class name */
    class C0014c implements l.a<v> {
        C0014c() {
        }

        public v a() {
            return new w(c.this.q(), c.this.n(), c.this.v(), c.this.u(), c.this.i(), c.this.p());
        }
    }

    class d implements l.a<d2> {
        d() {
        }

        public d2 a() {
            return new d2(c.this.m(), c.this.s());
        }
    }

    public c(Context context) {
        this.f383a = context;
        x();
    }

    /* access modifiers changed from: private */
    public String p() {
        return n.a();
    }

    /* access modifiers changed from: private */
    public b0 q() {
        return this.b.a();
    }

    private d2 r() {
        return this.e.a();
    }

    /* access modifiers changed from: private */
    public n2 s() {
        return o2.a(b()).c();
    }

    private j1.a t() {
        return j1.a.a(this.f383a);
    }

    /* access modifiers changed from: private */
    public q.a u() {
        return new q.a(w());
    }

    /* access modifiers changed from: private */
    public s.a v() {
        return new s.a(w());
    }

    private x w() {
        return new x();
    }

    private void x() {
        new p(this.f383a).a();
    }

    public h1 g() {
        return new h1(this.f383a);
    }

    public i1.a h() {
        return i1.a.a(this.f383a);
    }

    public u.b i() {
        return new u.b(k(), new t(j()), t(), c());
    }

    public SharedPreferences j() {
        return this.f383a.getSharedPreferences("tappx", 0);
    }

    public m1.a k() {
        return m1.a.a(this.f383a);
    }

    public v l() {
        return this.d.a();
    }

    public y2 m() {
        return z2.a(this.f383a);
    }

    public y.a n() {
        return new y.a(w(), d(), k(), c(), h(), f(), t());
    }

    public q1 o() {
        return new r1(l(), a(), r());
    }

    public static c a(Context context) {
        c cVar = f;
        if (cVar == null) {
            synchronized (c.class) {
                cVar = f;
                if (cVar == null) {
                    f = new c(context.getApplicationContext());
                    c cVar2 = f;
                    return cVar2;
                }
            }
        }
        return cVar;
    }

    public Context b() {
        return this.f383a;
    }

    public l0.a c() {
        return l0.a.a(this.f383a);
    }

    public p0.b d() {
        return p0.b.a(this.f383a);
    }

    public t2 e() {
        return this.c.a();
    }

    public g1.b f() {
        return g1.b.a(this.f383a);
    }

    public k0 a() {
        return new k0();
    }
}
