package com.startapp.a.a.e;

import com.startapp.a.a.a.c;
import java.io.DataInput;
import java.io.IOException;

/* compiled from: StartAppSDK */
public class e extends d {
    /* access modifiers changed from: protected */
    public DataInput a(byte[] bArr) {
        DataInput a2 = super.a(bArr);
        b(a2);
        return a2;
    }

    /* access modifiers changed from: protected */
    public c a(DataInput dataInput) {
        long readInt = (long) dataInput.readInt();
        c cVar = new c(readInt << 6);
        a(dataInput, cVar, readInt);
        return cVar;
    }

    private void b(DataInput dataInput) {
        try {
            dataInput.readInt();
        } catch (IOException e) {
            throw new RuntimeException("problem incrementInputStreamForBackwordCompatability", e);
        }
    }
}
