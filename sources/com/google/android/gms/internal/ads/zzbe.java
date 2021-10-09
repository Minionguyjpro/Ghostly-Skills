package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbe extends zzbfc<zzbe> {
    public byte[] data = null;
    public byte[] zzgq = null;
    public byte[] zzgr = null;
    public byte[] zzgs = null;

    public zzbe() {
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                this.data = zzbez.readBytes();
            } else if (zzabk == 18) {
                this.zzgq = zzbez.readBytes();
            } else if (zzabk == 26) {
                this.zzgr = zzbez.readBytes();
            } else if (zzabk == 34) {
                this.zzgs = zzbez.readBytes();
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        byte[] bArr = this.data;
        if (bArr != null) {
            zzbfa.zza(1, bArr);
        }
        byte[] bArr2 = this.zzgq;
        if (bArr2 != null) {
            zzbfa.zza(2, bArr2);
        }
        byte[] bArr3 = this.zzgr;
        if (bArr3 != null) {
            zzbfa.zza(3, bArr3);
        }
        byte[] bArr4 = this.zzgs;
        if (bArr4 != null) {
            zzbfa.zza(4, bArr4);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        byte[] bArr = this.data;
        if (bArr != null) {
            zzr += zzbfa.zzb(1, bArr);
        }
        byte[] bArr2 = this.zzgq;
        if (bArr2 != null) {
            zzr += zzbfa.zzb(2, bArr2);
        }
        byte[] bArr3 = this.zzgr;
        if (bArr3 != null) {
            zzr += zzbfa.zzb(3, bArr3);
        }
        byte[] bArr4 = this.zzgs;
        return bArr4 != null ? zzr + zzbfa.zzb(4, bArr4) : zzr;
    }
}
