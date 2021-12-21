package com.startapp.a.a.c;

import java.nio.charset.Charset;

/* compiled from: StartAppSDK */
public class f {
    private static String a(byte[] bArr, Charset charset) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr, charset);
    }

    public static String a(byte[] bArr) {
        return a(bArr, c.f);
    }
}
