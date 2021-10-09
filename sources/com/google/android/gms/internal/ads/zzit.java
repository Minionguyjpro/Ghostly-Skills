package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzit extends zzbfc<zzit> {
    public Integer zzaot = null;
    public Integer zzaou = null;
    public Integer zzaov = null;

    public zzit() {
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
                this.zzaot = Integer.valueOf(zzbez.zzacc());
            } else if (zzabk == 16) {
                this.zzaou = Integer.valueOf(zzbez.zzacc());
            } else if (zzabk == 24) {
                this.zzaov = Integer.valueOf(zzbez.zzacc());
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzaot;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        Integer num2 = this.zzaou;
        if (num2 != null) {
            zzbfa.zzm(2, num2.intValue());
        }
        Integer num3 = this.zzaov;
        if (num3 != null) {
            zzbfa.zzm(3, num3.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzaot;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        Integer num2 = this.zzaou;
        if (num2 != null) {
            zzr += zzbfa.zzq(2, num2.intValue());
        }
        Integer num3 = this.zzaov;
        return num3 != null ? zzr + zzbfa.zzq(3, num3.intValue()) : zzr;
    }
}
