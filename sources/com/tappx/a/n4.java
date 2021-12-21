package com.tappx.a;

import android.content.Context;
import android.graphics.Rect;

class n4 {

    /* renamed from: a  reason: collision with root package name */
    private final Context f534a;
    private final Rect b = new Rect();
    private final Rect c = new Rect();
    private final Rect d = new Rect();
    private final Rect e = new Rect();
    private final Rect f = new Rect();
    private final Rect g = new Rect();
    private final Rect h = new Rect();
    private final Rect i = new Rect();

    n4(Context context, float f2) {
        this.f534a = context.getApplicationContext();
    }

    private void a(Rect rect, Rect rect2) {
        rect2.set(q3.f((float) rect.left, this.f534a), q3.f((float) rect.top, this.f534a), q3.f((float) rect.right, this.f534a), q3.f((float) rect.bottom, this.f534a));
    }

    /* access modifiers changed from: package-private */
    public Rect b() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public void c(int i2, int i3, int i4, int i5) {
        this.d.set(i2, i3, i4 + i2, i5 + i3);
        a(this.d, this.e);
    }

    /* access modifiers changed from: package-private */
    public Rect d() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public Rect e() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public Rect f() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public Rect g() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void b(int i2, int i3, int i4, int i5) {
        this.h.set(i2, i3, i4 + i2, i5 + i3);
        a(this.h, this.i);
    }

    /* access modifiers changed from: package-private */
    public Rect c() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3) {
        this.b.set(0, 0, i2, i3);
        a(this.b, this.c);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, int i4, int i5) {
        this.f.set(i2, i3, i4 + i2, i5 + i3);
        a(this.f, this.g);
    }

    /* access modifiers changed from: package-private */
    public Rect a() {
        return this.f;
    }
}
