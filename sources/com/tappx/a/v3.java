package com.tappx.a;

import android.content.Context;
import android.view.View;
import com.tappx.a.d4;
import com.tappx.a.t3;

public class v3 implements t3 {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public t3.b f601a;
    private final Context b;
    private d4 c;
    private d4.h d = new a();
    private d4.k e = new b();

    class b implements d4.k {
        b() {
        }

        public void a(boolean z) {
            if (v3.this.f601a != null) {
                v3.this.f601a.a(z);
            }
        }
    }

    v3(Context context) {
        this.b = context;
    }

    public void destroy() {
        d4 d4Var = this.c;
        if (d4Var != null) {
            d4Var.a((d4.h) null);
            this.c.b();
            this.c = null;
        }
    }

    class a implements d4.h {
        a() {
        }

        public void a(View view) {
            j0.d("v5lNHAiXCIZ1hAylTNDUIT+qLa9pGoGxoSFqUJi0Wwg", new Object[0]);
            if (v3.this.f601a != null) {
                v3.this.f601a.a(view);
            }
        }

        public void b() {
            if (v3.this.f601a != null) {
                v3.this.f601a.b();
            }
        }

        public void c() {
            if (v3.this.f601a != null) {
                v3.this.f601a.c();
            }
        }

        public void d() {
            j0.d("ZJVkXnYZGc0zgB3S4AsbuD81KHR8Nkg8UponZZuzRBk", new Object[0]);
            if (v3.this.f601a != null) {
                v3.this.f601a.d();
            }
        }

        public void a() {
            if (v3.this.f601a != null) {
                v3.this.f601a.a();
            }
        }
    }

    public void a(t3.b bVar) {
        this.f601a = bVar;
    }

    public View a(b4 b4Var, String str, t3.a aVar) {
        j0.d("3ZJsjFJl8424bBJ0FHBsPsvg6JPdFtnXjH4FLENWtoY", new Object[0]);
        if (this.c == null) {
            d4 d4Var = new d4(this.b, b4Var);
            this.c = d4Var;
            d4Var.a(this.d);
            this.c.a(str);
            this.c.a(this.e);
        }
        return this.c.c();
    }

    public void a(boolean z) {
        d4 d4Var = this.c;
        if (d4Var != null) {
            d4Var.b(z);
        }
    }

    public void a() {
        d4 d4Var = this.c;
        if (d4Var != null) {
            d4Var.g();
        }
    }
}
