package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbft extends zzbfc<zzbft> {
    public String mimeType = null;
    public Integer zzamf = null;
    public byte[] zzedl = null;

    public zzbft() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzab */
    public final zzbft zza(zzbez zzbez) throws IOException {
        int zzabn;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                try {
                    zzabn = zzbez.zzabn();
                    if (zzabn < 0 || zzabn > 1) {
                        StringBuilder sb = new StringBuilder(36);
                        sb.append(zzabn);
                        sb.append(" is not a valid enum Type");
                    } else {
                        this.zzamf = Integer.valueOf(zzabn);
                    }
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(zzbez.getPosition());
                    zza(zzbez, zzabk);
                }
            } else if (zzabk == 18) {
                this.mimeType = zzbez.readString();
            } else if (zzabk == 26) {
                this.zzedl = zzbez.readBytes();
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(36);
        sb2.append(zzabn);
        sb2.append(" is not a valid enum Type");
        throw new IllegalArgumentException(sb2.toString());
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzamf;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        String str = this.mimeType;
        if (str != null) {
            zzbfa.zzf(2, str);
        }
        byte[] bArr = this.zzedl;
        if (bArr != null) {
            zzbfa.zza(3, bArr);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzamf;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        String str = this.mimeType;
        if (str != null) {
            zzr += zzbfa.zzg(2, str);
        }
        byte[] bArr = this.zzedl;
        return bArr != null ? zzr + zzbfa.zzb(3, bArr) : zzr;
    }
}
