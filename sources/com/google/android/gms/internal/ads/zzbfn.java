package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfn extends zzbfc<zzbfn> {
    public String zzcnd = null;

    public zzbfn() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                this.zzcnd = zzbez.readString();
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.zzcnd;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        String str = this.zzcnd;
        return str != null ? zzr + zzbfa.zzg(1, str) : zzr;
    }
}
