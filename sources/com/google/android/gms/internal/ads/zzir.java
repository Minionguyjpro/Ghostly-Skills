package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzir extends zzbfc<zzir> {
    private static volatile zzir[] zzaop;
    private String zzanq = null;
    private Integer zzanr = null;
    private zzis zzant = null;

    public zzir() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public static zzir[] zzhs() {
        if (zzaop == null) {
            synchronized (zzbfg.zzebs) {
                if (zzaop == null) {
                    zzaop = new zzir[0];
                }
            }
        }
        return zzaop;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzr */
    public final zzir zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                this.zzanq = zzbez.readString();
            } else if (zzabk == 16) {
                int position = zzbez.getPosition();
                try {
                    this.zzanr = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(position);
                    zza(zzbez, zzabk);
                }
            } else if (zzabk == 26) {
                if (this.zzant == null) {
                    this.zzant = new zzis();
                }
                zzbez.zza(this.zzant);
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.zzanq;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        Integer num = this.zzanr;
        if (num != null) {
            zzbfa.zzm(2, num.intValue());
        }
        zzis zzis = this.zzant;
        if (zzis != null) {
            zzbfa.zza(3, (zzbfi) zzis);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        String str = this.zzanq;
        if (str != null) {
            zzr += zzbfa.zzg(1, str);
        }
        Integer num = this.zzanr;
        if (num != null) {
            zzr += zzbfa.zzq(2, num.intValue());
        }
        zzis zzis = this.zzant;
        return zzis != null ? zzr + zzbfa.zzb(3, (zzbfi) zzis) : zzr;
    }
}
