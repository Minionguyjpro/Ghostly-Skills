package com.google.android.gms.internal.ads;

final class zzbak extends zzbao {
    private final int zzdpt;
    private final int zzdpu;

    zzbak(byte[] bArr, int i, int i2) {
        super(bArr);
        zzd(i, i + i2, bArr.length);
        this.zzdpt = i;
        this.zzdpu = i2;
    }

    public final int size() {
        return this.zzdpu;
    }

    /* access modifiers changed from: protected */
    public final void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzdpw, zzabh(), bArr, 0, i3);
    }

    /* access modifiers changed from: protected */
    public final int zzabh() {
        return this.zzdpt;
    }

    public final byte zzbn(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzdpw[this.zzdpt + i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(size);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }
}
