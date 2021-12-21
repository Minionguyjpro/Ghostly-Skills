package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfs extends zzbfc<zzbfs> {
    private byte[] zzedg = null;
    private Integer zzedj = null;
    private byte[] zzedk = null;

    public zzbfs() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                this.zzedj = Integer.valueOf(zzbez.zzabn());
            } else if (zzabk == 18) {
                this.zzedk = zzbez.readBytes();
            } else if (zzabk == 26) {
                this.zzedg = zzbez.readBytes();
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzedj;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        byte[] bArr = this.zzedk;
        if (bArr != null) {
            zzbfa.zza(2, bArr);
        }
        byte[] bArr2 = this.zzedg;
        if (bArr2 != null) {
            zzbfa.zza(3, bArr2);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzedj;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        byte[] bArr = this.zzedk;
        if (bArr != null) {
            zzr += zzbfa.zzb(2, bArr);
        }
        byte[] bArr2 = this.zzedg;
        return bArr2 != null ? zzr + zzbfa.zzb(3, bArr2) : zzr;
    }
}
