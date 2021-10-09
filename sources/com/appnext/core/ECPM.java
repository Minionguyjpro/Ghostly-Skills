package com.appnext.core;

public class ECPM {
    private String banner;
    private float ecpm;
    private float ppr;

    public ECPM(float f, float f2, String str) {
        this.ecpm = f;
        this.ppr = f2;
        this.banner = str;
    }

    public float getEcpm() {
        return this.ecpm;
    }

    /* access modifiers changed from: protected */
    public final void a(float f) {
        this.ecpm = f;
    }

    public float getPpr() {
        return this.ppr;
    }

    /* access modifiers changed from: protected */
    public final void b(float f) {
        this.ppr = f;
    }

    public String getBanner() {
        return this.banner;
    }

    /* access modifiers changed from: protected */
    public final void ac(String str) {
        this.banner = str;
    }
}
