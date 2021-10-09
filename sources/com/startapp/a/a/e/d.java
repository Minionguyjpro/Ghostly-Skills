package com.startapp.a.a.e;

import com.startapp.a.a.a.c;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.PrintStream;

/* compiled from: StartAppSDK */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    private final c f16a = new c();

    /* access modifiers changed from: protected */
    public abstract c a(DataInput dataInput);

    public c a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] a2 = this.f16a.a(str);
            if (a2 == null) {
                return null;
            }
            return a(a(a2));
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("HighPageCountException")) {
                PrintStream printStream = System.err;
                printStream.println("HighPageCountException (PLM-2573) " + e.getMessage() + ", bad bloom token: " + str);
            }
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void a(DataInput dataInput, c cVar, long j) {
        int c = cVar.c();
        for (int i = 0; i < c; i++) {
            long[] a2 = cVar.a(i);
            int i2 = 0;
            while (true) {
                if (i2 >= 4096) {
                    break;
                }
                long j2 = j - 1;
                if (j <= 0) {
                    j = j2;
                    break;
                }
                a2[i2] = dataInput.readLong();
                i2++;
                j = j2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public DataInput a(byte[] bArr) {
        return new DataInputStream(new ByteArrayInputStream(bArr));
    }
}
