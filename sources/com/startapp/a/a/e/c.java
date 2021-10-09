package com.startapp.a.a.e;

/* compiled from: StartAppSDK */
class c {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f15a = "0123456789abcdef".toCharArray();

    c() {
    }

    /* access modifiers changed from: package-private */
    public byte[] a(String str) {
        if (b(str)) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        int length = str.length();
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    private boolean b(String str) {
        return str.length() % 2 != 0;
    }

    /* access modifiers changed from: package-private */
    public String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            char[] cArr2 = f15a;
            cArr[i2] = cArr2[(bArr[i] & 240) >>> 4];
            cArr[i2 + 1] = cArr2[bArr[i] & 15];
        }
        return new String(cArr);
    }
}
