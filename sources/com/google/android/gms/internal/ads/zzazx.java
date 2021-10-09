package com.google.android.gms.internal.ads;

public final class zzazx {
    private final byte[] zzdpc = new byte[256];
    private int zzdpd;
    private int zzdpe;

    public zzazx(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.zzdpc[i] = (byte) i;
        }
        byte b = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            byte[] bArr2 = this.zzdpc;
            b = (b + bArr2[i2] + bArr[i2 % bArr.length]) & 255;
            byte b2 = bArr2[i2];
            bArr2[i2] = bArr2[b];
            bArr2[b] = b2;
        }
        this.zzdpd = 0;
        this.zzdpe = 0;
    }

    public final void zzn(byte[] bArr) {
        int i = this.zzdpd;
        int i2 = this.zzdpe;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            byte[] bArr2 = this.zzdpc;
            i2 = (i2 + bArr2[i]) & 255;
            byte b = bArr2[i];
            bArr2[i] = bArr2[i2];
            bArr2[i2] = b;
            bArr[i3] = (byte) (bArr2[(bArr2[i] + bArr2[i2]) & 255] ^ bArr[i3]);
        }
        this.zzdpd = i;
        this.zzdpe = i2;
    }
}
