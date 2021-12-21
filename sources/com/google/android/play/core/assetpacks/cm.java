package com.google.android.play.core.assetpacks;

import java.util.Arrays;

final class cm {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f1073a = new byte[4096];
    private int b;
    private long c;
    private long d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    private String i;

    public cm() {
        d();
    }

    private final int e(int i2, byte[] bArr, int i3, int i4) {
        int i5 = this.b;
        if (i5 >= i2) {
            return 0;
        }
        int min = Math.min(i4, i2 - i5);
        System.arraycopy(bArr, i3, this.f1073a, this.b, min);
        int i6 = this.b + min;
        this.b = i6;
        if (i6 < i2) {
            return -1;
        }
        return min;
    }

    public final int a(byte[] bArr, int i2, int i3) {
        int e2 = e(30, bArr, i2, i3);
        if (e2 == -1) {
            return -1;
        }
        if (this.c == -1) {
            long d2 = ck.d(this.f1073a, 0);
            this.c = d2;
            if (d2 == 67324752) {
                this.h = false;
                this.d = ck.d(this.f1073a, 18);
                this.g = ck.e(this.f1073a, 8);
                this.e = ck.e(this.f1073a, 26);
                int e3 = this.e + 30 + ck.e(this.f1073a, 28);
                this.f = e3;
                int length = this.f1073a.length;
                if (length < e3) {
                    do {
                        length += length;
                    } while (length < e3);
                    this.f1073a = Arrays.copyOf(this.f1073a, length);
                }
            } else {
                this.h = true;
            }
        }
        int e4 = e(this.f, bArr, i2 + e2, i3 - e2);
        if (e4 == -1) {
            return -1;
        }
        int i4 = e2 + e4;
        if (!this.h && this.i == null) {
            this.i = new String(this.f1073a, 30, this.e);
        }
        return i4;
    }

    public final de b() {
        int i2 = this.b;
        int i3 = this.f;
        if (i2 < i3) {
            return de.a(this.i, this.d, this.g, true, Arrays.copyOf(this.f1073a, i2), this.h);
        }
        de a2 = de.a(this.i, this.d, this.g, false, Arrays.copyOf(this.f1073a, i3), this.h);
        d();
        return a2;
    }

    public final int c() {
        return this.f;
    }

    public final void d() {
        this.b = 0;
        this.e = -1;
        this.c = -1;
        this.h = false;
        this.f = 30;
        this.d = -1;
        this.g = -1;
        this.i = null;
    }
}
