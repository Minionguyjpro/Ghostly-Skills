package com.yandex.metrica.impl.ob;

import java.util.Random;

public class dm {

    /* renamed from: a  reason: collision with root package name */
    private int f847a;
    private int b;
    private Random c;
    private int d;

    public dm(int i) {
        if (i <= 0 || i > 31) {
            this.f847a = 31;
        } else {
            this.f847a = i;
        }
        this.c = new Random();
    }

    public int a() {
        int i = this.b;
        if (i < this.f847a) {
            int i2 = i + 1;
            this.b = i2;
            this.d = 1 << i2;
        }
        return this.c.nextInt(this.d);
    }
}
