package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbf extends zzbfc<zzbf> {
    public Long zzgl = null;
    private String zzgt = null;
    private byte[] zzgu = null;

    public zzbf() {
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                this.zzgl = Long.valueOf(zzbez.zzacd());
            } else if (zzabk == 26) {
                this.zzgt = zzbez.readString();
            } else if (zzabk == 34) {
                this.zzgu = zzbez.readBytes();
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Long l = this.zzgl;
        if (l != null) {
            zzbfa.zzi(1, l.longValue());
        }
        String str = this.zzgt;
        if (str != null) {
            zzbfa.zzf(3, str);
        }
        byte[] bArr = this.zzgu;
        if (bArr != null) {
            zzbfa.zza(4, bArr);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Long l = this.zzgl;
        if (l != null) {
            zzr += zzbfa.zzd(1, l.longValue());
        }
        String str = this.zzgt;
        if (str != null) {
            zzr += zzbfa.zzg(3, str);
        }
        byte[] bArr = this.zzgu;
        return bArr != null ? zzr + zzbfa.zzb(4, bArr) : zzr;
    }
}
