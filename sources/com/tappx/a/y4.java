package com.tappx.a;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.appnext.base.b.d;
import com.mopub.mobileads.resource.DrawableConstants;
import com.tappx.a.c5;
import com.tappx.a.e5;
import com.tappx.a.p3;
import com.tappx.a.t3;

public final class y4 {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final Activity f630a;
    private final n2 b;
    private final e5 c;
    /* access modifiers changed from: private */
    public c5.a d;
    private f5 e;
    private t3 f;
    private p3 g;
    private int h;
    private t3.b i;

    class a implements p3.f {
        a() {
        }

        public void a() {
            y4.this.f630a.finish();
        }
    }

    class b implements t3.b {
        b() {
        }

        public void a() {
            y4.this.f630a.finish();
        }

        public void a(View view) {
        }

        public void b() {
            if (y4.this.d != null) {
                y4.this.d.c();
            }
        }

        public void c() {
        }

        public void d() {
            if (y4.this.d != null) {
                y4.this.d.a();
            }
            y4.this.f630a.finish();
        }

        public void a(boolean z) {
            if (z) {
                y4.this.j();
            } else {
                y4.this.l();
            }
        }
    }

    protected y4(Activity activity, n2 n2Var, e5 e5Var) {
        this.i = new b();
        this.f630a = activity;
        this.b = n2Var;
        this.c = e5Var;
    }

    private void e() {
        if (this.b.j()) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 83);
            this.g.addView(i(), layoutParams);
        }
    }

    private View f() {
        View h2 = h();
        p3 p3Var = new p3(this.f630a);
        this.g = p3Var;
        p3Var.setCloseListener(new a());
        FrameLayout.LayoutParams g2 = g();
        g2.gravity = 17;
        h2.setLayoutParams(g2);
        this.g.a(h2, g2);
        this.g.a(this.e.c(), this.e.h());
        e();
        a(this.g, this.e.b());
        return this.g;
    }

    private FrameLayout.LayoutParams g() {
        int i2;
        int d2;
        Display defaultDisplay = this.f630a.getWindowManager().getDefaultDisplay();
        int e2 = this.e.e();
        int width = defaultDisplay.getWidth();
        int i3 = -1;
        if (e2 <= 0 || (i2 = q3.d((float) e2, this.f630a)) > width) {
            i2 = -1;
        }
        int d3 = this.e.d();
        int height = defaultDisplay.getHeight();
        if (d3 > 0 && (d2 = q3.d((float) d3, this.f630a)) <= height) {
            i3 = d2;
        }
        return new FrameLayout.LayoutParams(i2, i3);
    }

    private View h() {
        String a2 = y3.a(this.f630a.getIntent());
        if (a2 == null) {
            this.f630a.finish();
            return new View(this.f630a);
        }
        t3 a3 = a(a2);
        this.f = a3;
        a3.a(this.i);
        return this.f.a(b4.INTERSTITIAL, a2, new t3.a().a(this.e.g()));
    }

    private View i() {
        return i3.c(this.f630a);
    }

    /* access modifiers changed from: private */
    public void j() {
        this.g.setCloseEnabled(false);
    }

    private void k() {
        this.f630a.getWindow().setBackgroundDrawable(new ColorDrawable(this.e.f() ? n.b : DrawableConstants.CtaButton.BACKGROUND_COLOR));
    }

    /* access modifiers changed from: private */
    public void l() {
        this.g.setCloseEnabled(true);
    }

    public void a(Bundle bundle) {
        int intExtra = this.f630a.getIntent().getIntExtra("aavc_otZMuRlffpTHI9DsaLyI", -1);
        this.h = intExtra;
        c5.a a2 = a5.a(intExtra);
        this.d = a2;
        if (a2 != null) {
            a2.b();
        }
        f5 f5Var = (f5) this.f630a.getIntent().getParcelableExtra("aavc_fagZVUC6pOQOxawVwpVy");
        this.e = f5Var;
        if (f5Var == null) {
            this.f630a.finish();
            return;
        }
        k();
        this.f630a.requestWindowFeature(1);
        this.f630a.getWindow().addFlags(d.fb);
        a(this.e.a());
        this.f630a.setContentView(f());
    }

    public void b() {
        t3 t3Var = this.f;
        if (t3Var != null) {
            t3Var.a(this.f630a.isFinishing());
        }
    }

    public void c() {
        t3 t3Var = this.f;
        if (t3Var != null) {
            t3Var.a();
        }
    }

    public boolean d() {
        return this.g.a();
    }

    private void a(j3 j3Var) {
        if (j3Var != null && j3Var != j3.ANY) {
            q3.a(this.f630a, j3Var);
        }
    }

    private void a(View view, k3 k3Var) {
        Animation a2 = m3.a(k3Var);
        if (a2 != null) {
            view.startAnimation(a2);
        }
    }

    private t3 a(String str) {
        e5.a a2 = this.c.a(this.h);
        if (a2 != null) {
            return a2.a();
        }
        t3 a3 = w3.a(this.f630a, str);
        a3.a(b4.INTERSTITIAL, str, new t3.a().a(this.e.g()));
        return a3;
    }

    public void a() {
        t3 t3Var = this.f;
        if (t3Var != null) {
            t3Var.destroy();
        }
        this.g.removeAllViews();
        c5.a aVar = this.d;
        if (aVar != null) {
            aVar.d();
        }
        this.d = null;
    }

    public y4(Activity activity) {
        this(activity, o2.a(activity).c(), e5.a());
    }
}
