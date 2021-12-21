package com.startapp.a.a.c;

import java.util.Arrays;

/* compiled from: StartAppSDK */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private final int f7a;
    protected final byte b = 61;
    protected final int c;
    private final int d;
    private final int e;

    /* access modifiers changed from: protected */
    public int a() {
        return 8192;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(byte[] bArr, int i, int i2, a aVar);

    /* access modifiers changed from: protected */
    public abstract boolean a(byte b2);

    /* compiled from: StartAppSDK */
    static class a {

        /* renamed from: a  reason: collision with root package name */
        int f8a;
        long b;
        byte[] c;
        int d;
        int e;
        boolean f;
        int g;
        int h;

        a() {
        }

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", new Object[]{getClass().getSimpleName(), Arrays.toString(this.c), Integer.valueOf(this.g), Boolean.valueOf(this.f), Integer.valueOf(this.f8a), Long.valueOf(this.b), Integer.valueOf(this.h), Integer.valueOf(this.d), Integer.valueOf(this.e)});
        }
    }

    protected b(int i, int i2, int i3, int i4) {
        this.f7a = i;
        this.d = i2;
        this.c = i3 > 0 && i4 > 0 ? (i3 / i2) * i2 : 0;
        this.e = i4;
    }

    /* access modifiers changed from: package-private */
    public int a(a aVar) {
        if (aVar.c != null) {
            return aVar.d - aVar.e;
        }
        return 0;
    }

    private byte[] b(a aVar) {
        if (aVar.c == null) {
            aVar.c = new byte[a()];
            aVar.d = 0;
            aVar.e = 0;
        } else {
            byte[] bArr = new byte[(aVar.c.length * 2)];
            System.arraycopy(aVar.c, 0, bArr, 0, aVar.c.length);
            aVar.c = bArr;
        }
        return aVar.c;
    }

    /* access modifiers changed from: protected */
    public byte[] a(int i, a aVar) {
        if (aVar.c == null || aVar.c.length < aVar.d + i) {
            return b(aVar);
        }
        return aVar.c;
    }

    /* access modifiers changed from: package-private */
    public int b(byte[] bArr, int i, int i2, a aVar) {
        if (aVar.c == null) {
            return aVar.f ? -1 : 0;
        }
        int min = Math.min(a(aVar), i2);
        System.arraycopy(aVar.c, aVar.e, bArr, i, min);
        aVar.e += min;
        if (aVar.e >= aVar.d) {
            aVar.c = null;
        }
        return min;
    }

    public byte[] b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        a(bArr, 0, bArr.length, aVar);
        a(bArr, 0, -1, aVar);
        int i = aVar.d - aVar.e;
        byte[] bArr2 = new byte[i];
        b(bArr2, 0, i, aVar);
        return bArr2;
    }

    /* access modifiers changed from: protected */
    public boolean c(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b2 : bArr) {
            if (61 == b2 || a(b2)) {
                return true;
            }
        }
        return false;
    }

    public long d(byte[] bArr) {
        int length = bArr.length;
        int i = this.f7a;
        long j = ((long) (((length + i) - 1) / i)) * ((long) this.d);
        int i2 = this.c;
        return i2 > 0 ? j + ((((((long) i2) + j) - 1) / ((long) i2)) * ((long) this.e)) : j;
    }
}
