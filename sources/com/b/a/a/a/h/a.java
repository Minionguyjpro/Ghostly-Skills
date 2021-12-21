package com.b.a.a.a.h;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.b.a.a.a.d.a;
import com.b.a.a.a.d.b;
import com.b.a.a.a.e.d;
import com.b.a.a.a.e.f;
import com.b.a.a.a.h.a.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class a implements a.C0039a {

    /* renamed from: a  reason: collision with root package name */
    private static a f1006a = new a();
    private static Handler b = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public static Handler c = null;
    /* access modifiers changed from: private */
    public static final Runnable j = new Runnable() {
        public void run() {
            a.a().i();
        }
    };
    /* access modifiers changed from: private */
    public static final Runnable k = new Runnable() {
        public void run() {
            if (a.c != null) {
                a.c.post(a.j);
                a.c.postDelayed(a.k, 200);
            }
        }
    };
    private List<C0041a> d = new ArrayList();
    private int e;
    private b f = new b();
    private b g = new b();
    /* access modifiers changed from: private */
    public c h = new c(new c());
    private double i;

    /* renamed from: com.b.a.a.a.h.a$a  reason: collision with other inner class name */
    public interface C0041a {
        void a(int i, long j);
    }

    a() {
    }

    public static a a() {
        return f1006a;
    }

    private void a(long j2) {
        if (this.d.size() > 0) {
            for (C0041a a2 : this.d) {
                a2.a(this.e, j2);
            }
        }
    }

    private void a(View view, com.b.a.a.a.d.a aVar, JSONObject jSONObject, d dVar) {
        aVar.a(view, jSONObject, this, dVar == d.PARENT_VIEW);
    }

    private boolean a(View view, JSONObject jSONObject) {
        String a2 = this.g.a(view);
        if (a2 == null) {
            return false;
        }
        com.b.a.a.a.e.b.a(jSONObject, a2);
        this.g.e();
        return true;
    }

    private void b(View view, JSONObject jSONObject) {
        ArrayList<String> b2 = this.g.b(view);
        if (b2 != null) {
            com.b.a.a.a.e.b.a(jSONObject, (List<String>) b2);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        j();
        e();
        k();
    }

    private void j() {
        this.e = 0;
        this.i = d.a();
    }

    private void k() {
        a((long) (d.a() - this.i));
    }

    private void l() {
        if (c == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            c = handler;
            handler.post(j);
            c.postDelayed(k, 200);
        }
    }

    private void m() {
        Handler handler = c;
        if (handler != null) {
            handler.removeCallbacks(k);
            c = null;
        }
    }

    public void a(View view, com.b.a.a.a.d.a aVar, JSONObject jSONObject) {
        d c2;
        if (f.d(view) && (c2 = this.g.c(view)) != d.UNDERLYING_VIEW) {
            JSONObject a2 = aVar.a(view);
            com.b.a.a.a.e.b.a(jSONObject, a2);
            if (!a(view, a2)) {
                b(view, a2);
                a(view, aVar, a2, c2);
            }
            this.e++;
        }
    }

    public void b() {
        l();
    }

    public void c() {
        d();
        this.d.clear();
        b.post(new Runnable() {
            public void run() {
                a.this.h.b();
            }
        });
    }

    public void d() {
        m();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.g.c();
        double a2 = d.a();
        com.b.a.a.a.d.a a3 = this.f.a();
        if (this.g.b().size() > 0) {
            this.h.b(a3.a((View) null), this.g.b(), a2);
        }
        if (this.g.a().size() > 0) {
            JSONObject a4 = a3.a((View) null);
            a((View) null, a3, a4, d.PARENT_VIEW);
            com.b.a.a.a.e.b.a(a4);
            this.h.a(a4, this.g.a(), a2);
        } else {
            this.h.b();
        }
        this.g.d();
    }
}
