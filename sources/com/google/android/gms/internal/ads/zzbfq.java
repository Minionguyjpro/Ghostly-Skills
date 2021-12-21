package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfq extends zzbfc<zzbfq> {
    private byte[] zzede = null;
    private byte[] zzedf = null;
    private byte[] zzedg = null;

    public zzbfq() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                this.zzede = zzbez.readBytes();
            } else if (zzabk == 18) {
                this.zzedf = zzbez.readBytes();
            } else if (zzabk == 26) {
                this.zzedg = zzbez.readBytes();
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        byte[] bArr = this.zzede;
        if (bArr != null) {
            zzbfa.zza(1, bArr);
        }
        byte[] bArr2 = this.zzedf;
        if (bArr2 != null) {
            zzbfa.zza(2, bArr2);
        }
        byte[] bArr3 = this.zzedg;
        if (bArr3 != null) {
            zzbfa.zza(3, bArr3);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        byte[] bArr = this.zzede;
        if (bArr != null) {
            zzr += zzbfa.zzb(1, bArr);
        }
        byte[] bArr2 = this.zzedf;
        if (bArr2 != null) {
            zzr += zzbfa.zzb(2, bArr2);
        }
        byte[] bArr3 = this.zzedg;
        return bArr3 != null ? zzr + zzbfa.zzb(3, bArr3) : zzr;
    }
}
