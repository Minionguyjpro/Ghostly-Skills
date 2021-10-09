package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfv extends zzbfc<zzbfv> {
    public String zzedv = null;
    public Long zzedw = null;
    public Boolean zzedx = null;

    public zzbfv() {
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
                this.zzedv = zzbez.readString();
            } else if (zzabk == 16) {
                this.zzedw = Long.valueOf(zzbez.zzabm());
            } else if (zzabk == 24) {
                this.zzedx = Boolean.valueOf(zzbez.zzabq());
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.zzedv;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        Long l = this.zzedw;
        if (l != null) {
            zzbfa.zzi(2, l.longValue());
        }
        Boolean bool = this.zzedx;
        if (bool != null) {
            zzbfa.zzf(3, bool.booleanValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        String str = this.zzedv;
        if (str != null) {
            zzr += zzbfa.zzg(1, str);
        }
        Long l = this.zzedw;
        if (l != null) {
            zzr += zzbfa.zzd(2, l.longValue());
        }
        Boolean bool = this.zzedx;
        if (bool == null) {
            return zzr;
        }
        bool.booleanValue();
        return zzr + zzbfa.zzcd(3) + 1;
    }
}
