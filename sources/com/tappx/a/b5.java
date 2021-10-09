package com.tappx.a;

import android.content.Context;
import android.view.View;
import com.tappx.a.c5;
import com.tappx.a.e5;
import com.tappx.a.t3;

public class b5 {

    /* renamed from: a  reason: collision with root package name */
    private e5 f381a;
    private int b;

    class a implements t3.b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ c5.a f382a;

        a(b5 b5Var, c5.a aVar) {
            this.f382a = aVar;
        }

        public void a() {
        }

        public void a(View view) {
            c5.a aVar = this.f382a;
            if (aVar != null) {
                aVar.e();
            }
        }

        public void a(boolean z) {
        }

        public void b() {
        }

        public void c() {
        }

        public void d() {
            c5.a aVar = this.f382a;
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    public b5() {
        this(e5.a());
    }

    public void a(int i, c5 c5Var, Context context, String str, c5.a aVar, f5 f5Var) {
        if (g.f443a) {
            aVar.e();
            return;
        }
        this.b = i;
        t3 a2 = w3.a(context, str);
        a2.a((t3.b) new a(this, aVar));
        a2.a(b4.INTERSTITIAL, str, new t3.a().a(f5Var.g()));
        this.f381a.a(i, c5Var, a2);
    }

    public b5(e5 e5Var) {
        this.f381a = e5Var;
    }

    public void a() {
        e5.a a2;
        int i = this.b;
        if (i != 0 && (a2 = this.f381a.a(i)) != null) {
            a2.a().destroy();
        }
    }
}
