package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzjc extends zzbfc<zzjc> {
    private Integer zzanu = null;
    private zziw zzapn = null;

    public zzjc() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzz */
    public final zzjc zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                int position = zzbez.getPosition();
                try {
                    this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(position);
                    zza(zzbez, zzabk);
                }
            } else if (zzabk == 18) {
                if (this.zzapn == null) {
                    this.zzapn = new zziw();
                }
                zzbez.zza(this.zzapn);
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzanu;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        zziw zziw = this.zzapn;
        if (zziw != null) {
            zzbfa.zza(2, (zzbfi) zziw);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzanu;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        zziw zziw = this.zzapn;
        return zziw != null ? zzr + zzbfa.zzb(2, (zzbfi) zziw) : zzr;
    }
}
