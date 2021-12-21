package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfo extends zzbfc<zzbfo> {
    private static volatile zzbfo[] zzecw;
    public byte[] zzecx = null;
    public byte[] zzecy = null;

    public zzbfo() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public static zzbfo[] zzagt() {
        if (zzecw == null) {
            synchronized (zzbfg.zzebs) {
                if (zzecw == null) {
                    zzecw = new zzbfo[0];
                }
            }
        }
        return zzecw;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                this.zzecx = zzbez.readBytes();
            } else if (zzabk == 18) {
                this.zzecy = zzbez.readBytes();
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        zzbfa.zza(1, this.zzecx);
        byte[] bArr = this.zzecy;
        if (bArr != null) {
            zzbfa.zza(2, bArr);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr() + zzbfa.zzb(1, this.zzecx);
        byte[] bArr = this.zzecy;
        return bArr != null ? zzr + zzbfa.zzb(2, bArr) : zzr;
    }
}
