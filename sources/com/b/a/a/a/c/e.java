package com.b.a.a.a.c;

import android.content.Context;
import android.os.Handler;
import com.b.a.a.a.a.c;
import com.b.a.a.a.a.d;
import com.b.a.a.a.b.i;
import com.b.a.a.a.c.b;
import com.b.a.a.a.h.a;

public class e implements c, b.a {

    /* renamed from: a  reason: collision with root package name */
    private static e f995a;
    private float b = 0.0f;
    private final com.b.a.a.a.a.e c;
    private final com.b.a.a.a.a.b d;
    private d e;
    private a f;

    public e(com.b.a.a.a.a.e eVar, com.b.a.a.a.a.b bVar) {
        this.c = eVar;
        this.d = bVar;
    }

    public static e a() {
        if (f995a == null) {
            f995a = new e(new com.b.a.a.a.a.e(), new com.b.a.a.a.a.b());
        }
        return f995a;
    }

    private a e() {
        if (this.f == null) {
            this.f = a.a();
        }
        return this.f;
    }

    public void a(float f2) {
        this.b = f2;
        for (i f3 : e().c()) {
            f3.f().a(f2);
        }
    }

    public void a(Context context) {
        this.e = this.c.a(new Handler(), context, this.d.a(), this);
    }

    public void a(boolean z) {
        if (z) {
            a.a().b();
        } else {
            a.a().d();
        }
    }

    public void b() {
        b.a().a((b.a) this);
        b.a().b();
        if (b.a().d()) {
            a.a().b();
        }
        this.e.a();
    }

    public void c() {
        a.a().c();
        b.a().c();
        this.e.b();
    }

    public float d() {
        return this.b;
    }
}
