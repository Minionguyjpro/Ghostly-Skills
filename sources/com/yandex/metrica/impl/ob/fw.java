package com.yandex.metrica.impl.ob;

import com.mopub.volley.DefaultRetryPolicy;

public class fw {

    /* renamed from: a  reason: collision with root package name */
    private int f918a;
    private int b;
    private final int c;
    private final float d;

    public fw() {
        this(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 1, 1.0f);
    }

    public fw(int i, int i2, float f) {
        this.f918a = i;
        this.c = i2;
        this.d = f;
    }

    public int a() {
        return this.f918a;
    }

    public void a(fr frVar) throws fr {
        this.b++;
        int i = this.f918a;
        this.f918a = (int) (((float) i) + (((float) i) * this.d));
        if (!b()) {
            throw frVar;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return this.b <= this.c;
    }
}
