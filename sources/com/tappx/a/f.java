package com.tappx.a;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class f {
    private static volatile f c;
    private static final byte[] d = {81, 80, 55, 68, 109, 116, 116, 52, 54, 67, 104, 99, 71, 108, 52, 81, 67, 102, 100, 86};
    private static final byte[] e = {-30, 31, 11, 37, 23, 88};

    /* renamed from: a  reason: collision with root package name */
    private Cipher f434a;
    private Cipher b;

    public f() {
        this(d, e);
    }

    public static f a() {
        f fVar = c;
        if (fVar == null) {
            synchronized (f.class) {
                fVar = c;
                if (fVar == null) {
                    fVar = new f();
                    c = fVar;
                }
            }
        }
        return fVar;
    }

    public static String b(String str) {
        return a().a(str);
    }

    public f(String str) {
        this(str.getBytes(), e);
    }

    public f(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(a(bArr, bArr2), "AES");
            Cipher instance = Cipher.getInstance("AES");
            this.f434a = instance;
            instance.init(1, secretKeySpec);
            Cipher instance2 = Cipher.getInstance("AES");
            this.b = instance2;
            instance2.init(2, secretKeySpec);
        } catch (Exception unused) {
        }
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 == null) {
            return bArr;
        }
        byte[] bArr3 = new byte[24];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 24 - bArr.length);
        return bArr3;
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new String(this.b.doFinal(Base64.decode(str, 0)), "UTF-8");
        } catch (Exception unused) {
            return "";
        }
    }
}
