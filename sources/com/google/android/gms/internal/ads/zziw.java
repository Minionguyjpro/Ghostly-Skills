package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziw extends zzbfc<zziw> {
    private Integer zzapp = null;

    public zziw() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzt */
    public final zziw zza(zzbez zzbez) throws IOException {
        int zzacc;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                try {
                    zzacc = zzbez.zzacc();
                    if (zzacc < 0 || zzacc > 3) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append(zzacc);
                        sb.append(" is not a valid enum VideoErrorCode");
                    } else {
                        this.zzapp = Integer.valueOf(zzacc);
                    }
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(zzbez.getPosition());
                    zza(zzbez, zzabk);
                }
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append(zzacc);
        sb2.append(" is not a valid enum VideoErrorCode");
        throw new IllegalArgumentException(sb2.toString());
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzapp;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzapp;
        return num != null ? zzr + zzbfa.zzq(1, num.intValue()) : zzr;
    }
}
