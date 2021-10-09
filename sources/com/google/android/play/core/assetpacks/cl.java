package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

public final class cl extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final Enumeration<File> f1072a;
    private InputStream b;

    public cl(Enumeration<File> enumeration) throws IOException {
        this.f1072a = enumeration;
        a();
    }

    /* access modifiers changed from: package-private */
    public final void a() throws IOException {
        InputStream inputStream = this.b;
        if (inputStream != null) {
            inputStream.close();
        }
        this.b = this.f1072a.hasMoreElements() ? new FileInputStream(this.f1072a.nextElement()) : null;
    }

    public final void close() throws IOException {
        super.close();
        InputStream inputStream = this.b;
        if (inputStream != null) {
            inputStream.close();
            this.b = null;
        }
    }

    public final int read() throws IOException {
        while (true) {
            InputStream inputStream = this.b;
            if (inputStream == null) {
                return -1;
            }
            int read = inputStream.read();
            if (read != -1) {
                return read;
            }
            a();
        }
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.b == null) {
            return -1;
        }
        if (bArr == null) {
            throw null;
        } else if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            do {
                int read = this.b.read(bArr, i, i2);
                if (read > 0) {
                    return read;
                }
                a();
            } while (this.b != null);
            return -1;
        }
    }
}
