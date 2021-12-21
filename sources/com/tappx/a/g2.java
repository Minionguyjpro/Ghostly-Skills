package com.tappx.a;

import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.tappx.a.f3;
import com.tappx.a.n0;
import com.tappx.sdk.android.AdRequest;
import com.tappx.sdk.android.TappxAdError;
import com.tappx.sdk.android.TappxBanner;
import com.tappx.sdk.android.TappxBannerListener;

public class g2 extends f2 {
    /* access modifiers changed from: private */
    public final TappxBanner l;
    private final n0 m;
    /* access modifiers changed from: private */
    public final f3 n;
    private View o;
    /* access modifiers changed from: private */
    public TappxBannerListener p;
    private boolean q;
    /* access modifiers changed from: private */
    public boolean r;
    /* access modifiers changed from: private */
    public u1 s;
    private boolean t;
    private int u;
    private final n0.a v = new a();
    private final f3.b w = new b();

    class b implements f3.b {
        b() {
        }

        public void a() {
            g2.this.j();
        }
    }

    g2(TappxBanner tappxBanner) {
        super(tappxBanner.getContext(), v1.b);
        this.l = tappxBanner;
        b a2 = b.a(tappxBanner.getContext());
        n0 b2 = a2.b();
        this.m = b2;
        b2.a(this.v);
        f3 a3 = a2.a();
        this.n = a3;
        a3.a(this.w);
    }

    private View h() {
        if (this.o == null) {
            this.o = i3.b(this.l.getContext());
        }
        return this.o;
    }

    /* access modifiers changed from: private */
    public void i() {
        TappxBannerListener tappxBannerListener = this.p;
        if (tappxBannerListener != null) {
            tappxBannerListener.onBannerLoaded(this.l);
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        if (!this.k && this.t) {
            j0.a(f.b("WYP3IlFsQbao/nmzk+V5+EDTMrEq8ygXRWqwiT3aXVk"), new Object[0]);
            j0.d("fhrgFfJqgVZoVNjzyS7CzU1i9AA4GyPqlAJ20RCAJlg", new Object[0]);
            c();
        }
    }

    private void c(w1 w1Var) {
        if (!this.q) {
            c(w1Var.c());
        }
    }

    private void e() {
        if (this.g.j()) {
            this.l.addView(h(), new FrameLayout.LayoutParams(-2, -2, 83));
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.t) {
            this.n.d();
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        u1 u1Var;
        boolean z = this.u >= 50;
        if (!this.r && (u1Var = this.s) != null && this.t && z) {
            this.r = true;
            c(u1Var.g());
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        super.d();
        this.m.a();
    }

    private String b(TappxBanner.AdSize adSize) {
        if (adSize == TappxBanner.AdSize.SMART_BANNER) {
            return null;
        }
        return adSize.getWidth() + AvidJSONUtil.KEY_X + adSize.getHeight();
    }

    private void c(int i) {
        if (i == 0) {
            j0.d("Rfk0iXqG1NksAriLhvTIFrKC3X10rpfR3hyZYQqfkTdNYvQAOBsj6pQCdtEQgCZY", new Object[0]);
            this.n.e();
            this.n.a(false);
            return;
        }
        this.n.a(true);
        if (i > 0) {
            this.n.a((long) i);
        } else {
            this.n.b();
        }
    }

    public void a() {
        super.a();
        a((TappxBannerListener) null);
        this.l.removeAllViews();
        this.m.destroy();
        this.n.e();
        this.s = null;
    }

    /* access modifiers changed from: protected */
    public void b(w1 w1Var) {
        c(w1Var);
        this.m.a(this.l.getContext(), w1Var);
    }

    class a implements n0.a {
        a() {
        }

        public void a(u1 u1Var, View view) {
            g2 g2Var = g2.this;
            if (!g2Var.k) {
                boolean unused = g2Var.r = false;
                u1 unused2 = g2.this.s = u1Var;
                g2.this.a(view);
                g2.this.f();
                g2.this.i();
                g2.this.a(u1Var);
                g2.this.g();
            }
        }

        public void b(u1 u1Var) {
            g2.this.n.a();
            if (g2.this.p != null) {
                g2.this.p.onBannerExpanded(g2.this.l);
            }
        }

        public void c(u1 u1Var) {
            g2.this.n.c();
            if (g2.this.p != null) {
                g2.this.p.onBannerCollapsed(g2.this.l);
            }
        }

        public void a(a2 a2Var) {
            g2 g2Var = g2.this;
            if (!g2Var.k) {
                g2.this.b(g2Var.b(a2Var));
                g2.this.f();
            }
        }

        public void a(u1 u1Var) {
            if (g2.this.p != null) {
                g2.this.p.onBannerClicked(g2.this.l);
            }
            g2.this.c(u1Var.e());
        }
    }

    public static class c {

        /* renamed from: a  reason: collision with root package name */
        private static volatile c f450a;

        public static c a() {
            c cVar = f450a;
            if (cVar == null) {
                synchronized (c.class) {
                    cVar = f450a;
                    if (cVar == null) {
                        cVar = new c();
                    }
                }
            }
            return cVar;
        }

        public g2 a(TappxBanner tappxBanner) {
            return new g2(tappxBanner);
        }
    }

    /* access modifiers changed from: private */
    public void b(TappxAdError tappxAdError) {
        TappxBannerListener tappxBannerListener = this.p;
        if (tappxBannerListener != null) {
            tappxBannerListener.onBannerLoadFailed(this.l, tappxAdError);
        }
    }

    public void b(int i) {
        this.q = i > 0;
        c(i);
    }

    public void a(TappxBannerListener tappxBannerListener) {
        this.p = tappxBannerListener;
    }

    public void b(boolean z) {
        j0.d("r+UiUzt9REOhqndIQXQTv4xLHJ5RqFQyDLMKVsbc2y8", String.valueOf(z));
        this.t = z;
        if (z) {
            g();
            this.n.c();
            return;
        }
        this.n.a();
    }

    public void a(TappxBanner.AdSize adSize) {
        if (adSize == null) {
            adSize = TappxBanner.AdSize.SMART_BANNER;
        }
        a(b(adSize));
    }

    public void a(AdRequest adRequest) {
        if (this.g.e()) {
            this.g.a(this.l.getContext(), (Runnable) null);
        }
        super.a(adRequest);
    }

    /* access modifiers changed from: protected */
    public void a(TappxAdError tappxAdError) {
        TappxBannerListener tappxBannerListener = this.p;
        if (tappxBannerListener != null) {
            tappxBannerListener.onBannerLoadFailed(this.l, tappxAdError);
        }
        f();
    }

    /* access modifiers changed from: private */
    public void a(View view) {
        this.l.removeAllViews();
        this.l.addView(view, 0);
        e();
    }

    /* access modifiers changed from: private */
    public void a(u1 u1Var) {
        Animation a2 = m3.a(b3.a(u1Var.a()));
        if (a2 != null) {
            this.l.startAnimation(a2);
        }
    }

    public void a(boolean z) {
        this.n.a(z);
    }

    public void a(int i) {
        this.u = i;
        g();
    }
}
