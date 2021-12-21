package com.tappx.a;

import com.mopub.volley.DefaultRetryPolicy;

public class k5 implements w5 {

    /* renamed from: a  reason: collision with root package name */
    private int f498a;
    private int b;
    private final int c;
    private final float d;

    public k5() {
        this(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 1, 1.0f);
    }

    public int a() {
        return this.f498a;
    }

    public int b() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return this.b <= this.c;
    }

    public k5(int i, int i2, float f) {
        this.f498a = i;
        this.c = i2;
        this.d = f;
    }

    public void a(z5 z5Var) {
        this.b++;
        int i = this.f498a;
        this.f498a = i + ((int) (((float) i) * this.d));
        if (!c()) {
            throw z5Var;
        }
    }
}
