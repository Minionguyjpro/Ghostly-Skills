package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzis extends zzbfc<zzis> {
    private static volatile zzis[] zzaoq;
    private Integer zzaor = null;
    private Integer zzaos = null;

    public zzis() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public static zzis[] zzht() {
        if (zzaoq == null) {
            synchronized (zzbfg.zzebs) {
                if (zzaoq == null) {
                    zzaoq = new zzis[0];
                }
            }
        }
        return zzaoq;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                this.zzaor = Integer.valueOf(zzbez.zzacc());
            } else if (zzabk == 16) {
                this.zzaos = Integer.valueOf(zzbez.zzacc());
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzaor;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        Integer num2 = this.zzaos;
        if (num2 != null) {
            zzbfa.zzm(2, num2.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzaor;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        Integer num2 = this.zzaos;
        return num2 != null ? zzr + zzbfa.zzq(2, num2.intValue()) : zzr;
    }
}
