package com.google.android.play.core.assetpacks;

import java.io.IOException;
import java.io.InputStream;

final class av extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f1035a;
    private long b;

    av(InputStream inputStream, long j) {
        this.f1035a = inputStream;
        this.b = j;
    }

    public final void close() throws IOException {
        super.close();
        this.f1035a.close();
        this.b = 0;
    }

    public final int read() throws IOException {
        long j = this.b;
        if (j <= 0) {
            return -1;
        }
        this.b = j - 1;
        return this.f1035a.read();
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.b;
        if (j <= 0) {
            return -1;
        }
        int read = this.f1035a.read(bArr, i, (int) Math.min((long) i2, j));
        if (read != -1) {
            this.b -= (long) read;
        }
        return read;
    }
}
