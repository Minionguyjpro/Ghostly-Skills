package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzaw extends zzbfc<zzaw> {
    private String stackTrace = null;
    public String zzco = null;
    public Long zzcp = null;
    private String zzcq = null;
    private String zzcr = null;
    private Long zzcs = null;
    private Long zzct = null;
    private String zzcu = null;
    private Long zzcv = null;
    private String zzcw = null;

    public zzaw() {
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    return this;
                case 10:
                    this.zzco = zzbez.readString();
                    break;
                case 16:
                    this.zzcp = Long.valueOf(zzbez.zzacd());
                    break;
                case 26:
                    this.stackTrace = zzbez.readString();
                    break;
                case 34:
                    this.zzcq = zzbez.readString();
                    break;
                case 42:
                    this.zzcr = zzbez.readString();
                    break;
                case 48:
                    this.zzcs = Long.valueOf(zzbez.zzacd());
                    break;
                case 56:
                    this.zzct = Long.valueOf(zzbez.zzacd());
                    break;
                case 66:
                    this.zzcu = zzbez.readString();
                    break;
                case 72:
                    this.zzcv = Long.valueOf(zzbez.zzacd());
                    break;
                case 82:
                    this.zzcw = zzbez.readString();
                    break;
                default:
                    if (super.zza(zzbez, zzabk)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.zzco;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        Long l = this.zzcp;
        if (l != null) {
            zzbfa.zzi(2, l.longValue());
        }
        String str2 = this.stackTrace;
        if (str2 != null) {
            zzbfa.zzf(3, str2);
        }
        String str3 = this.zzcq;
        if (str3 != null) {
            zzbfa.zzf(4, str3);
        }
        String str4 = this.zzcr;
        if (str4 != null) {
            zzbfa.zzf(5, str4);
        }
        Long l2 = this.zzcs;
        if (l2 != null) {
            zzbfa.zzi(6, l2.longValue());
        }
        Long l3 = this.zzct;
        if (l3 != null) {
            zzbfa.zzi(7, l3.longValue());
        }
        String str5 = this.zzcu;
        if (str5 != null) {
            zzbfa.zzf(8, str5);
        }
        Long l4 = this.zzcv;
        if (l4 != null) {
            zzbfa.zzi(9, l4.longValue());
        }
        String str6 = this.zzcw;
        if (str6 != null) {
            zzbfa.zzf(10, str6);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        String str = this.zzco;
        if (str != null) {
            zzr += zzbfa.zzg(1, str);
        }
        Long l = this.zzcp;
        if (l != null) {
            zzr += zzbfa.zzd(2, l.longValue());
        }
        String str2 = this.stackTrace;
        if (str2 != null) {
            zzr += zzbfa.zzg(3, str2);
        }
        String str3 = this.zzcq;
        if (str3 != null) {
            zzr += zzbfa.zzg(4, str3);
        }
        String str4 = this.zzcr;
        if (str4 != null) {
            zzr += zzbfa.zzg(5, str4);
        }
        Long l2 = this.zzcs;
        if (l2 != null) {
            zzr += zzbfa.zzd(6, l2.longValue());
        }
        Long l3 = this.zzct;
        if (l3 != null) {
            zzr += zzbfa.zzd(7, l3.longValue());
        }
        String str5 = this.zzcu;
        if (str5 != null) {
            zzr += zzbfa.zzg(8, str5);
        }
        Long l4 = this.zzcv;
        if (l4 != null) {
            zzr += zzbfa.zzd(9, l4.longValue());
        }
        String str6 = this.zzcw;
        return str6 != null ? zzr + zzbfa.zzg(10, str6) : zzr;
    }
}
