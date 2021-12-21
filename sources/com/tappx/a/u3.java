package com.tappx.a;

import android.content.Context;
import android.view.View;
import com.tappx.a.t3;
import com.tappx.a.x3;

public class u3 implements t3 {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public t3.b f596a;
    private final Context b;
    /* access modifiers changed from: private */
    public x3 c;
    private x3.d d = new a();

    class a implements x3.d {
        a() {
        }

        public void a() {
            j0.d("EO6JnLxOUsi6kIdAfPMA//Kib626NzkhHKkXIfYGxxc", new Object[0]);
            if (u3.this.f596a != null) {
                u3.this.f596a.d();
            }
        }

        public void b() {
            if (u3.this.f596a != null) {
                u3.this.f596a.b();
            }
        }

        public void c() {
            j0.d("xhf99ytwwl8bVeOsPAy3pg", new Object[0]);
            if (u3.this.f596a != null) {
                u3.this.f596a.a((View) u3.this.c);
            }
        }
    }

    u3(Context context) {
        this.b = context;
    }

    public void a() {
    }

    public void a(boolean z) {
    }

    public void destroy() {
        x3 x3Var = this.c;
        if (x3Var != null) {
            x3Var.setListener((x3.d) null);
            this.c.destroy();
            this.c = null;
        }
    }

    public void a(t3.b bVar) {
        this.f596a = bVar;
    }

    public View a(b4 b4Var, String str, t3.a aVar) {
        j0.d("h0fTNqXwKZ+DG4kdf/GC5w", new Object[0]);
        if (this.c == null) {
            x3 x3Var = new x3(this.b, a(aVar));
            this.c = x3Var;
            x3Var.setListener(this.d);
            if (b4Var == b4.INLINE || o.f535a) {
                this.c.a();
            }
            this.c.a(str);
        }
        return this.c;
    }

    private boolean a(t3.a aVar) {
        if (aVar == null) {
            return false;
        }
        return aVar.a();
    }
}
