package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzip extends zzbfc<zzip> {
    private Integer zzaol = null;
    private Integer zzaom = null;

    public zzip() {
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
                this.zzaol = Integer.valueOf(zzbez.zzacc());
            } else if (zzabk == 16) {
                this.zzaom = Integer.valueOf(zzbez.zzacc());
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzaol;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        Integer num2 = this.zzaom;
        if (num2 != null) {
            zzbfa.zzm(2, num2.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzaol;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        Integer num2 = this.zzaom;
        return num2 != null ? zzr + zzbfa.zzq(2, num2.intValue()) : zzr;
    }
}
