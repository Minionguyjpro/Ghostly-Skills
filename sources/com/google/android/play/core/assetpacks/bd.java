package com.google.android.play.core.assetpacks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

final class bd extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    private final cm f1042a = new cm();
    private byte[] b = new byte[4096];
    private long c;
    private boolean d = false;
    private boolean e = false;

    bd(InputStream inputStream) {
        super(inputStream);
    }

    private final boolean e(int i) throws IOException {
        int f = f(this.b, 0, i);
        if (f != i) {
            int i2 = i - f;
            if (f(this.b, f, i2) != i2) {
                this.f1042a.a(this.b, 0, f);
                return false;
            }
        }
        this.f1042a.a(this.b, 0, i);
        return true;
    }

    private final int f(byte[] bArr, int i, int i2) throws IOException {
        return Math.max(0, super.read(bArr, i, i2));
    }

    /* access modifiers changed from: package-private */
    public final de a() throws IOException {
        byte[] bArr;
        if (this.c > 0) {
            do {
                bArr = this.b;
            } while (read(bArr, 0, bArr.length) != -1);
        }
        if (this.d || this.e) {
            return new de((String) null, -1, -1, false, false, (byte[]) null);
        }
        if (!e(30)) {
            this.d = true;
            return this.f1042a.b();
        }
        de b2 = this.f1042a.b();
        if (b2.h()) {
            this.e = true;
            return b2;
        } else if (b2.e() != 4294967295L) {
            int c2 = this.f1042a.c() - 30;
            long j = (long) c2;
            int length = this.b.length;
            if (j > ((long) length)) {
                do {
                    length += length;
                } while (((long) length) < j);
                this.b = Arrays.copyOf(this.b, length);
            }
            if (!e(c2)) {
                this.d = true;
                return this.f1042a.b();
            }
            de b3 = this.f1042a.b();
            this.c = b3.e();
            return b3;
        } else {
            throw new bk("Files bigger than 4GiB are not supported.");
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean b() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public final boolean c() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public final long d() {
        return this.c;
    }

    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.c;
        if (j <= 0 || this.d) {
            return -1;
        }
        int f = f(bArr, i, (int) Math.min(j, (long) i2));
        this.c -= (long) f;
        if (f != 0) {
            return f;
        }
        this.d = true;
        return 0;
    }
}
