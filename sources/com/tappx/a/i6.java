package com.tappx.a;

import java.io.ByteArrayOutputStream;

public class i6 extends ByteArrayOutputStream {

    /* renamed from: a  reason: collision with root package name */
    private final d6 f475a;

    public i6(d6 d6Var, int i) {
        this.f475a = d6Var;
        this.buf = d6Var.a(Math.max(i, 256));
    }

    private void a(int i) {
        int i2 = this.count + i;
        if (i2 > this.buf.length) {
            byte[] a2 = this.f475a.a(i2 * 2);
            System.arraycopy(this.buf, 0, a2, 0, this.count);
            this.f475a.a(this.buf);
            this.buf = a2;
        }
    }

    public void close() {
        this.f475a.a(this.buf);
        this.buf = null;
        super.close();
    }

    public void finalize() {
        this.f475a.a(this.buf);
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        a(i2);
        super.write(bArr, i, i2);
    }

    public synchronized void write(int i) {
        a(1);
        super.write(i);
    }
}
