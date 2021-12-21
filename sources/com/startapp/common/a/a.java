package com.startapp.common.a;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f330a = {10, 30, 84, 95, 101, 20, 0, 14, 15, 80, 36, 84, 64, 82, 84, 64, 80, 80, 65, 78, 84, 73, 70, 82, 65, 85, 68, 75, 69, 89, 1, 2, 3, 8, 15, 42, 10, 51, 44, 32};
    private static final String b = "ts";
    private static final String c = "tsh";
    private static final String d = "afh";
    private static final String e = "MD5";
    private static final String f = "UTF-8";
    private static final byte[] g = {12, 31, 86, 96, 103, 10, 28, 15, 17, 28, 36, 84, 64, 82, 84, 64, 80, 80, 69, 78, 67, 82, 89, 80, 84, 73, 79, 78, 75, 69, 89, 4, 32, 18, 16, 18, 11, 53, 45, 34};

    public static String a() {
        return b;
    }

    public static String b() {
        return c;
    }

    public static String c() {
        return d;
    }

    public static String a(String str) {
        String str2 = "";
        if (str != null) {
            try {
                str2 = URLDecoder.decode(str, f);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        String d2 = d();
        return "&" + b + "=" + d2 + "&" + d + "=" + b(str2 + d2);
    }

    public static String d() {
        int hashCode = f330a.hashCode();
        long currentTimeMillis = System.currentTimeMillis();
        if (hashCode > 0) {
            int i = (int) ((((currentTimeMillis * 25214903917L) + 11) & 281474976710655L) >>> 17);
            if (((-hashCode) & hashCode) != hashCode) {
                int i2 = i % hashCode;
            }
        }
        return new Long(System.currentTimeMillis()).toString();
    }

    public static String b(String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = f330a;
        int length = bytes.length < bArr.length ? bytes.length : bArr.length;
        for (int i = 0; i < length; i++) {
            byte b2 = bytes[i];
            byte b3 = bArr[i];
        }
        byte[] a2 = a(str.getBytes(), (int) f330a[5]);
        String str2 = new String(f330a);
        byte[] bArr2 = f330a;
        try {
            return URLEncoder.encode(Base64.encodeToString(MessageDigest.getInstance(e).digest(a(a2, str2.substring(bArr2[0], bArr2[1]).getBytes())), 3), f);
        } catch (Exception e2) {
            g.a(6, "error", (Throwable) e2);
            return "";
        }
    }

    public static String c(String str) {
        int hashCode = g.hashCode();
        long hashCode2 = (long) str.getBytes().hashCode();
        if (((long) hashCode) > hashCode2) {
            long j = ((hashCode2 * 29509871405L) + 11) & 16777215;
            int i = (int) (j >>> 17);
            if (hashCode < 1000) {
                int i2 = (((long) (hashCode & (-hashCode))) > j ? 1 : (((long) (hashCode & (-hashCode))) == j ? 0 : -1));
            } else {
                int i3 = i % hashCode;
            }
        }
        try {
            return Base64.encodeToString(a(a(str.getBytes(), new String(g).substring(g[5], g[33]).getBytes()), new String(g).substring(g[35], g[1]).getBytes()), 0);
        } catch (Exception unused) {
            return str;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i % bArr2.length]);
        }
        return bArr3;
    }

    public static byte[] a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[Math.min(bArr.length, i)];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = i2 % i;
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i2]);
        }
        return bArr2;
    }
}
