package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzix extends zzbfc<zzix> {
    private Integer zzanu = null;
    private zziw zzapn = null;
    private zzis zzapo = null;
    private zzit zzapq = null;

    public zzix() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzu */
    public final zzix zza(zzbez zzbez) throws IOException {
        zzbfi zzbfi;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                if (this.zzapq == null) {
                    this.zzapq = new zzit();
                }
                zzbfi = this.zzapq;
            } else if (zzabk == 16) {
                int position = zzbez.getPosition();
                try {
                    this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(position);
                    zza(zzbez, zzabk);
                }
            } else if (zzabk == 26) {
                if (this.zzapn == null) {
                    this.zzapn = new zziw();
                }
                zzbfi = this.zzapn;
            } else if (zzabk == 34) {
                if (this.zzapo == null) {
                    this.zzapo = new zzis();
                }
                zzbfi = this.zzapo;
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
            zzbez.zza(zzbfi);
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        zzit zzit = this.zzapq;
        if (zzit != null) {
            zzbfa.zza(1, (zzbfi) zzit);
        }
        Integer num = this.zzanu;
        if (num != null) {
            zzbfa.zzm(2, num.intValue());
        }
        zziw zziw = this.zzapn;
        if (zziw != null) {
            zzbfa.zza(3, (zzbfi) zziw);
        }
        zzis zzis = this.zzapo;
        if (zzis != null) {
            zzbfa.zza(4, (zzbfi) zzis);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        zzit zzit = this.zzapq;
        if (zzit != null) {
            zzr += zzbfa.zzb(1, (zzbfi) zzit);
        }
        Integer num = this.zzanu;
        if (num != null) {
            zzr += zzbfa.zzq(2, num.intValue());
        }
        zziw zziw = this.zzapn;
        if (zziw != null) {
            zzr += zzbfa.zzb(3, (zzbfi) zziw);
        }
        zzis zzis = this.zzapo;
        return zzis != null ? zzr + zzbfa.zzb(4, (zzbfi) zzis) : zzr;
    }
}
