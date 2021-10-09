package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzin extends zzbfc<zzin> {
    private Integer zzany = null;
    private zzis zzanz = null;

    public zzin() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzp */
    public final zzin zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                int position = zzbez.getPosition();
                try {
                    this.zzany = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(position);
                    zza(zzbez, zzabk);
                }
            } else if (zzabk == 18) {
                if (this.zzanz == null) {
                    this.zzanz = new zzis();
                }
                zzbez.zza(this.zzanz);
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzany;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        zzis zzis = this.zzanz;
        if (zzis != null) {
            zzbfa.zza(2, (zzbfi) zzis);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzany;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        zzis zzis = this.zzanz;
        return zzis != null ? zzr + zzbfa.zzb(2, (zzbfi) zzis) : zzr;
    }
}
