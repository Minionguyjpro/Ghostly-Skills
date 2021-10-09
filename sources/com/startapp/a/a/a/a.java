package com.startapp.a.a.a;

import java.nio.ByteBuffer;
import java.util.List;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final int f3a;
    private final int b;

    public a(int i, int i2) {
        this.f3a = i;
        this.b = i2;
    }

    public c a(List<String> list) {
        c cVar = new c((long) (this.f3a * this.b));
        a(list, cVar);
        return cVar;
    }

    /* access modifiers changed from: package-private */
    public void a(List<String> list, c cVar) {
        for (String bytes : list) {
            a(ByteBuffer.wrap(bytes.getBytes()), cVar);
        }
    }

    private void a(ByteBuffer byteBuffer, c cVar) {
        for (long a2 : a(byteBuffer, cVar.a())) {
            cVar.a(a2);
        }
    }

    private long[] a(ByteBuffer byteBuffer, long j) {
        int i = this.f3a;
        long[] jArr = new long[i];
        long j2 = j / ((long) i);
        long a2 = b.a(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), 0);
        long a3 = b.a(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), a2);
        for (int i2 = 0; i2 < this.f3a; i2++) {
            long j3 = (long) i2;
            jArr[i2] = (j3 * j2) + Math.abs(((j3 * a3) + a2) % j2);
        }
        return jArr;
    }
}
