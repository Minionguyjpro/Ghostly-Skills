package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzic extends zzbfc<zzic> {
    private static volatile zzic[] zzame;
    private Integer zzamf = null;
    private zziq zzamg = null;

    public zzic() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzf */
    public final zzic zza(zzbez zzbez) throws IOException {
        int zzacc;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                try {
                    zzacc = zzbez.zzacc();
                    if (zzacc < 0 || zzacc > 10) {
                        StringBuilder sb = new StringBuilder(44);
                        sb.append(zzacc);
                        sb.append(" is not a valid enum AdFormatType");
                    } else {
                        this.zzamf = Integer.valueOf(zzacc);
                    }
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(zzbez.getPosition());
                    zza(zzbez, zzabk);
                }
            } else if (zzabk == 18) {
                if (this.zzamg == null) {
                    this.zzamg = new zziq();
                }
                zzbez.zza(this.zzamg);
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(44);
        sb2.append(zzacc);
        sb2.append(" is not a valid enum AdFormatType");
        throw new IllegalArgumentException(sb2.toString());
    }

    public static zzic[] zzhr() {
        if (zzame == null) {
            synchronized (zzbfg.zzebs) {
                if (zzame == null) {
                    zzame = new zzic[0];
                }
            }
        }
        return zzame;
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzamf;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        zziq zziq = this.zzamg;
        if (zziq != null) {
            zzbfa.zza(2, (zzbfi) zziq);
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
        zziq zziq = this.zzamg;
        return zziq != null ? zzr + zzbfa.zzb(2, (zzbfi) zziq) : zzr;
    }
}
