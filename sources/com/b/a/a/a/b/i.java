package com.b.a.a.a.b;

import android.view.View;
import com.b.a.a.a.c.e;
import com.b.a.a.a.f.a;
import com.b.a.a.a.g.b;
import com.b.a.a.a.g.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class i extends b {

    /* renamed from: a  reason: collision with root package name */
    private final d f987a;
    private final c b;
    private final List<a> c = new ArrayList();
    private a d;
    private com.b.a.a.a.g.a e;
    private boolean f = false;
    private boolean g = false;
    private String h;
    private boolean i;

    i(c cVar, d dVar) {
        this.b = cVar;
        this.f987a = dVar;
        this.h = UUID.randomUUID().toString();
        e((View) null);
        this.e = dVar.f() == e.HTML ? new b(dVar.c()) : new c(dVar.b(), dVar.e());
        this.e.a();
        com.b.a.a.a.c.a.a().a(this);
        this.e.a(cVar);
    }

    private a c(View view) {
        for (a next : this.c) {
            if (next.get() == view) {
                return next;
            }
        }
        return null;
    }

    private void d(View view) {
        if (view == null) {
            throw new IllegalArgumentException("FriendlyObstruction is null");
        }
    }

    private void e(View view) {
        this.d = new a(view);
    }

    private void f(View view) {
        Collection<i> b2 = com.b.a.a.a.c.a.a().b();
        if (b2 != null && b2.size() > 0) {
            for (i next : b2) {
                if (next != this && next.h() == view) {
                    next.d.clear();
                }
            }
        }
    }

    private void n() {
        if (this.i) {
            throw new IllegalStateException("Impression event can only be sent once");
        }
    }

    public void a() {
        if (!this.f) {
            this.f = true;
            com.b.a.a.a.c.a.a().b(this);
            this.e.a(e.a().d());
            this.e.a(this, this.f987a);
        }
    }

    public void a(View view) {
        if (!this.g) {
            com.b.a.a.a.e.e.a((Object) view, "AdView is null");
            if (h() != view) {
                e(view);
                f().i();
                f(view);
            }
        }
    }

    public void b() {
        if (!this.g) {
            this.d.clear();
            c();
            this.g = true;
            f().g();
            com.b.a.a.a.c.a.a().c(this);
            f().b();
            this.e = null;
        }
    }

    public void b(View view) {
        if (!this.g) {
            d(view);
            if (c(view) == null) {
                this.c.add(new a(view));
            }
        }
    }

    public void c() {
        if (!this.g) {
            this.c.clear();
        }
    }

    public List<a> d() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        n();
        f().h();
        this.i = true;
    }

    public com.b.a.a.a.g.a f() {
        return this.e;
    }

    public String g() {
        return this.h;
    }

    public View h() {
        return (View) this.d.get();
    }

    public boolean i() {
        return this.f && !this.g;
    }

    public boolean j() {
        return this.f;
    }

    public boolean k() {
        return this.g;
    }

    public boolean l() {
        return this.b.a();
    }

    public boolean m() {
        return this.b.b();
    }
}
