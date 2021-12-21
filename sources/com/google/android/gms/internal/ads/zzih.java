package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzih extends zzbfc<zzih> {
    private Integer zzanc = null;
    private zzit zzand = null;
    private String zzane = null;
    private String zzanf = null;

    public zzih() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzj */
    public final zzih zza(zzbez zzbez) throws IOException {
        int zzacc;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 40) {
                try {
                    zzacc = zzbez.zzacc();
                    if (zzacc < 0 || zzacc > 2) {
                        StringBuilder sb = new StringBuilder(40);
                        sb.append(zzacc);
                        sb.append(" is not a valid enum Platform");
                    } else {
                        this.zzanc = Integer.valueOf(zzacc);
                    }
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(zzbez.getPosition());
                    zza(zzbez, zzabk);
                }
            } else if (zzabk == 50) {
                if (this.zzand == null) {
                    this.zzand = new zzit();
                }
                zzbez.zza(this.zzand);
            } else if (zzabk == 58) {
                this.zzane = zzbez.readString();
            } else if (zzabk == 66) {
                this.zzanf = zzbez.readString();
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append(zzacc);
        sb2.append(" is not a valid enum Platform");
        throw new IllegalArgumentException(sb2.toString());
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzanc;
        if (num != null) {
            zzbfa.zzm(5, num.intValue());
        }
        zzit zzit = this.zzand;
        if (zzit != null) {
            zzbfa.zza(6, (zzbfi) zzit);
        }
        String str = this.zzane;
        if (str != null) {
            zzbfa.zzf(7, str);
        }
        String str2 = this.zzanf;
        if (str2 != null) {
            zzbfa.zzf(8, str2);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzanc;
        if (num != null) {
            zzr += zzbfa.zzq(5, num.intValue());
        }
        zzit zzit = this.zzand;
        if (zzit != null) {
            zzr += zzbfa.zzb(6, (zzbfi) zzit);
        }
        String str = this.zzane;
        if (str != null) {
            zzr += zzbfa.zzg(7, str);
        }
        String str2 = this.zzanf;
        return str2 != null ? zzr + zzbfa.zzg(8, str2) : zzr;
    }
}
