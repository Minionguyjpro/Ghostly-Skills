package com.startapp.android.publish.ads.banner;

import android.graphics.Point;

/* compiled from: StartAppSDK */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private Point f55a = new Point();

    public d() {
    }

    public d(int i, int i2) {
        a(i, i2);
    }

    public void a(int i) {
        this.f55a.x = i;
    }

    public void b(int i) {
        this.f55a.y = i;
    }

    public void a(int i, int i2) {
        a(i);
        b(i2);
    }

    public int a() {
        return this.f55a.x;
    }

    public int b() {
        return this.f55a.y;
    }
}
