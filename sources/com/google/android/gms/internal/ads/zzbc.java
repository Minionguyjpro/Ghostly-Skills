package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbc extends zzbfc<zzbc> {
    private Long zzeq = null;
    private Long zzer = null;
    public Long zzgi = null;
    public Long zzgj = null;
    public Long zzgk = null;

    public zzbc() {
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                this.zzeq = Long.valueOf(zzbez.zzacd());
            } else if (zzabk == 16) {
                this.zzer = Long.valueOf(zzbez.zzacd());
            } else if (zzabk == 24) {
                this.zzgi = Long.valueOf(zzbez.zzacd());
            } else if (zzabk == 32) {
                this.zzgj = Long.valueOf(zzbez.zzacd());
            } else if (zzabk == 40) {
                this.zzgk = Long.valueOf(zzbez.zzacd());
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Long l = this.zzeq;
        if (l != null) {
            zzbfa.zzi(1, l.longValue());
        }
        Long l2 = this.zzer;
        if (l2 != null) {
            zzbfa.zzi(2, l2.longValue());
        }
        Long l3 = this.zzgi;
        if (l3 != null) {
            zzbfa.zzi(3, l3.longValue());
        }
        Long l4 = this.zzgj;
        if (l4 != null) {
            zzbfa.zzi(4, l4.longValue());
        }
        Long l5 = this.zzgk;
        if (l5 != null) {
            zzbfa.zzi(5, l5.longValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Long l = this.zzeq;
        if (l != null) {
            zzr += zzbfa.zzd(1, l.longValue());
        }
        Long l2 = this.zzer;
        if (l2 != null) {
            zzr += zzbfa.zzd(2, l2.longValue());
        }
        Long l3 = this.zzgi;
        if (l3 != null) {
            zzr += zzbfa.zzd(3, l3.longValue());
        }
        Long l4 = this.zzgj;
        if (l4 != null) {
            zzr += zzbfa.zzd(4, l4.longValue());
        }
        Long l5 = this.zzgk;
        return l5 != null ? zzr + zzbfa.zzd(5, l5.longValue()) : zzr;
    }
}
