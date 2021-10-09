package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class bn extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    private final cm f1052a = new cm();
    private final File b;
    private final cz c;
    private long d;
    private long e;
    private FileOutputStream f;
    private de g;

    bn(File file, cz czVar) {
        this.b = file;
        this.c = czVar;
    }

    public final void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    public final void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public final void write(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        while (i2 > 0) {
            if (this.d == 0 && this.e == 0) {
                int a2 = this.f1052a.a(bArr, i, i2);
                if (a2 != -1) {
                    i += a2;
                    i2 -= a2;
                    de b2 = this.f1052a.b();
                    this.g = b2;
                    if (b2.h()) {
                        this.d = 0;
                        this.c.m(this.g.i(), this.g.i().length);
                        this.e = (long) this.g.i().length;
                    } else if (!this.g.c() || this.g.b()) {
                        byte[] i4 = this.g.i();
                        this.c.m(i4, i4.length);
                        this.d = this.g.e();
                    } else {
                        this.c.g(this.g.i());
                        File file = new File(this.b, this.g.d());
                        file.getParentFile().mkdirs();
                        this.d = this.g.e();
                        this.f = new FileOutputStream(file);
                    }
                } else {
                    return;
                }
            }
            if (!this.g.b()) {
                if (this.g.h()) {
                    this.c.i(this.e, bArr, i, i2);
                    this.e += (long) i2;
                    i3 = i2;
                } else if (this.g.c()) {
                    i3 = (int) Math.min((long) i2, this.d);
                    this.f.write(bArr, i, i3);
                    long j = this.d - ((long) i3);
                    this.d = j;
                    if (j == 0) {
                        this.f.close();
                    }
                } else {
                    i3 = (int) Math.min((long) i2, this.d);
                    int length = this.g.i().length;
                    this.c.i((((long) length) + this.g.e()) - this.d, bArr, i, i3);
                    this.d -= (long) i3;
                }
                i += i3;
                i2 -= i3;
            }
        }
    }
}
