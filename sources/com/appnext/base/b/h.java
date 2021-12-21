package com.appnext.base.b;

import android.text.TextUtils;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class h {
    private static final h fw = new h();

    private static void init() {
    }

    private h() {
    }

    public static h aO() {
        return fw;
    }

    public static Long I(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            CRC32 crc32 = new CRC32();
            crc32.update(str.getBytes());
            return Long.valueOf(crc32.getValue());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String J(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            CRC32 crc32 = new CRC32();
            crc32.update(str.getBytes());
            return Long.toHexString(crc32.getValue());
        } catch (Throwable unused) {
            return null;
        }
    }

    public final byte[] f(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(str2)) {
                    byte[] bArr = new byte[16];
                    new SecureRandom().nextBytes(bArr);
                    Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    instance.init(1, new SecretKeySpec(Base64.decode(str2, 2), "AES"), new IvParameterSpec(bArr));
                    return a(bArr, instance.doFinal(str.getBytes("UTF-8")));
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public final String K(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] aP = aP();
            if (aP == null) {
                return null;
            }
            instance.init(1, new SecretKeySpec(aP, "AES"), new IvParameterSpec(bArr));
            return Base64.encodeToString(a(bArr, instance.doFinal(str.getBytes("utf-8"))), 2);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static byte[] aP() {
        try {
            return Arrays.copyOf((f.getKey()).getBytes("utf-8"), 16);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final String L(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            byte[] decode = Base64.decode(str, 2);
            byte[] copyOfRange = Arrays.copyOfRange(decode, 0, 16);
            byte[] copyOfRange2 = Arrays.copyOfRange(decode, 16, decode.length);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] aP = aP();
            if (aP == null) {
                return null;
            }
            instance.init(2, new SecretKeySpec(aP, "AES"), new IvParameterSpec(copyOfRange));
            return new String(instance.doFinal(copyOfRange2), "utf-8");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String aQ() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128);
            return Base64.encodeToString(instance.generateKey().getEncoded(), 0);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        int length = bArr2.length + 16;
        byte[] bArr3 = new byte[length];
        int i = 0;
        while (i < length) {
            bArr3[i] = i < 16 ? bArr[i] : bArr2[i - 16];
            i++;
        }
        return bArr3;
    }

    public final String M(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append('0');
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }
}
