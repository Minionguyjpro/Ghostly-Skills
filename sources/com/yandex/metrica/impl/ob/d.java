package com.yandex.metrica.impl.ob;

import java.io.IOException;

public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    protected volatile int f842a = -1;

    public void a(b bVar) throws IOException {
    }

    /* access modifiers changed from: protected */
    public int c() {
        return 0;
    }

    public int a() {
        if (this.f842a < 0) {
            b();
        }
        return this.f842a;
    }

    public int b() {
        int c = c();
        this.f842a = c;
        return c;
    }

    public static final byte[] a(d dVar) {
        int b = dVar.b();
        byte[] bArr = new byte[b];
        try {
            b a2 = b.a(bArr, 0, b);
            dVar.a(a2);
            a2.b();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public String toString() {
        return e.a(this);
    }
}
