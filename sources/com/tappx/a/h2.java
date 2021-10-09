package com.tappx.a;

import android.content.Context;
import com.tappx.a.e1;
import com.tappx.a.f3;
import com.tappx.sdk.android.AdRequest;
import com.tappx.sdk.android.TappxAdError;
import com.tappx.sdk.android.TappxInterstitial;
import com.tappx.sdk.android.TappxInterstitialListener;

public class h2 extends f2 {
    /* access modifiers changed from: private */
    public final TappxInterstitial l;
    private final f3 m;
    private final e1 n;
    /* access modifiers changed from: private */
    public TappxInterstitialListener o;
    /* access modifiers changed from: private */
    public u1 p;
    /* access modifiers changed from: private */
    public d1 q;
    /* access modifiers changed from: private */
    public boolean r;
    /* access modifiers changed from: private */
    public boolean s;
    private final f3.b t = new a();
    private e1.a u = new b();

    class a implements f3.b {
        a() {
        }

        public void a() {
            h2.this.j();
        }
    }

    class c implements Runnable {
        c() {
        }

        public void run() {
            h2.this.k();
        }
    }

    public h2(TappxInterstitial tappxInterstitial, Context context) {
        super(context, v1.INTERSTITIAL);
        this.l = tappxInterstitial;
        i a2 = i.a(context);
        e1 b2 = a2.b();
        this.n = b2;
        b2.a(this.u);
        f3 a3 = a2.a();
        this.m = a3;
        a3.a(this.t);
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.r) {
            this.r = false;
            return;
        }
        TappxInterstitialListener tappxInterstitialListener = this.o;
        if (tappxInterstitialListener != null) {
            tappxInterstitialListener.onInterstitialLoaded(this.l);
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        this.m.e();
        h();
    }

    /* access modifiers changed from: private */
    public void k() {
        if (e()) {
            this.m.e();
            if (this.q != null) {
                TappxInterstitialListener tappxInterstitialListener = this.o;
                if (tappxInterstitialListener != null) {
                    tappxInterstitialListener.onInterstitialShown(this.l);
                }
                this.q.g();
                this.q = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        d1 d1Var = this.q;
        if (d1Var != null) {
            d1Var.b();
            this.q = null;
        }
    }

    private void h() {
        this.r = true;
        c();
    }

    /* access modifiers changed from: protected */
    public void d() {
        super.d();
        this.n.a();
    }

    public boolean e() {
        return this.q != null;
    }

    public void f() {
        if (this.g.e()) {
            this.g.a(this.l.getContext(), (Runnable) null);
            this.g.a((Runnable) new c());
            return;
        }
        k();
    }

    /* access modifiers changed from: protected */
    public void b(w1 w1Var) {
        this.n.a(b(), w1Var);
    }

    /* access modifiers changed from: private */
    public void b(TappxAdError tappxAdError) {
        if (this.r) {
            this.r = false;
            return;
        }
        TappxInterstitialListener tappxInterstitialListener = this.o;
        if (tappxInterstitialListener != null) {
            tappxInterstitialListener.onInterstitialLoadFailed(this.l, tappxAdError);
        }
    }

    public void a(TappxInterstitialListener tappxInterstitialListener) {
        this.o = tappxInterstitialListener;
    }

    /* access modifiers changed from: protected */
    public void a(TappxAdError tappxAdError) {
        if (this.r) {
            this.r = false;
            return;
        }
        TappxInterstitialListener tappxInterstitialListener = this.o;
        if (tappxInterstitialListener != null) {
            tappxInterstitialListener.onInterstitialLoadFailed(this.l, tappxAdError);
        }
    }

    class b implements e1.a {
        b() {
        }

        public void a(u1 u1Var, d1 d1Var) {
            h2 h2Var = h2.this;
            if (!h2Var.k) {
                u1 unused = h2Var.p = u1Var;
                h2.this.g();
                d1 unused2 = h2.this.q = d1Var;
                h2.this.a(u1Var);
                boolean z = h2.this.s && !h2.this.r;
                h2.this.i();
                if (z) {
                    h2.this.f();
                }
            }
        }

        public void b(u1 u1Var) {
            if (h2.this.o != null) {
                h2.this.o.onInterstitialDismissed(h2.this.l);
            }
        }

        public void a(a2 a2Var) {
            h2 h2Var = h2.this;
            if (!h2Var.k) {
                h2.this.b(h2Var.b(a2Var));
            }
        }

        public void a(u1 u1Var) {
            if (h2.this.o != null) {
                h2.this.o.onInterstitialClicked(h2.this.l);
            }
        }
    }

    public void a(AdRequest adRequest) {
        g();
        this.r = false;
        super.a(adRequest);
    }

    public void a() {
        super.a();
        a((TappxInterstitialListener) null);
        g();
        this.n.destroy();
        this.m.e();
    }

    /* access modifiers changed from: private */
    public void a(u1 u1Var) {
        long c2 = u1Var.c() - System.currentTimeMillis();
        if (c2 > 0) {
            this.m.a(c2);
            this.m.d();
            return;
        }
        this.m.e();
    }

    public void a(boolean z) {
        this.s = z;
    }
}
