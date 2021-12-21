package com.startapp.a.a.a;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public class c implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f4a = (!c.class.desiredAssertionStatus());
    private final long[][] d;
    private int e;
    private final int f;

    private int d(long j) {
        return (int) (((j - 1) >>> 6) + 1);
    }

    public c(long j) {
        int d2 = d(j);
        this.e = d2;
        int i = d2 % 4096;
        int i2 = d2 / 4096;
        int i3 = (i == 0 ? 0 : 1) + i2;
        this.f = i3;
        if (i3 <= 100) {
            this.d = new long[i3][];
            for (int i4 = 0; i4 < i2; i4++) {
                this.d[i4] = new long[4096];
            }
            if (i != 0) {
                long[][] jArr = this.d;
                jArr[jArr.length - 1] = new long[i];
                return;
            }
            return;
        }
        throw new RuntimeException("HighPageCountException pageCount = " + this.f);
    }

    /* access modifiers changed from: package-private */
    public long a() {
        return ((long) this.e) << 6;
    }

    /* access modifiers changed from: package-private */
    public void a(long j) {
        int b = b(j);
        long[] jArr = this.d[b / 4096];
        int i = b % 4096;
        jArr[i] = (1 << (((int) j) & 63)) | jArr[i];
    }

    private int b(long j) {
        int i = (int) (j >> 6);
        if (i >= this.e) {
            c(j + 1);
            this.e = i + 1;
        }
        return i;
    }

    private void c(long j) {
        b(d(j));
    }

    private void b(int i) {
        if (!f4a && i > this.e) {
            throw new AssertionError("Growing of paged bitset is not supported");
        }
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public long[] a(int i) {
        return this.d[i];
    }
}
