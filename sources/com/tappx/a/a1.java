package com.tappx.a;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.tappx.a.m0;
import com.tappx.a.t3;

public class a1 {

    /* renamed from: a  reason: collision with root package name */
    private final Context f367a;
    /* access modifiers changed from: private */
    public int b;
    /* access modifiers changed from: private */
    public int c;
    private t3 d;
    /* access modifiers changed from: private */
    public m0.c e;
    private t3.b f = new a();

    public a1(Context context) {
        this.f367a = context;
    }

    class a implements t3.b {
        a() {
        }

        public void a(View view) {
            view.setLayoutParams(new ViewGroup.LayoutParams(a1.this.b, a1.this.c));
            a1.this.e.a(view);
        }

        public void a(boolean z) {
        }

        public void b() {
            a1.this.e.d();
        }

        public void c() {
            a1.this.e.c();
        }

        public void d() {
            a1.this.e.a(a2.UNSPECIFIED);
        }

        public void a() {
            a1.this.e.b();
        }
    }

    public void a() {
        t3 t3Var = this.d;
        if (t3Var != null) {
            t3Var.destroy();
        }
    }

    public void a(y1 y1Var, m0.c cVar) {
        this.e = cVar;
        String h = y1Var.h();
        t3 a2 = w3.a(this.f367a, h);
        this.d = a2;
        a2.a(this.f);
        this.d.a(b4.INLINE, h, (t3.a) null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.f367a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int l = y1Var.l();
        int j = y1Var.j();
        this.b = (int) TypedValue.applyDimension(1, (float) l, displayMetrics);
        this.c = (int) TypedValue.applyDimension(1, (float) j, displayMetrics);
    }
}
