package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziu extends zzbfc<zziu> {
    private static volatile zziu[] zzaow;
    private zziy zzaox = null;
    private zzja zzaoy = null;
    private zzjb zzaoz = null;
    private zzjc zzapa = null;
    private zziv zzapb = null;
    private zziz zzapc = null;
    private zzix zzapd = null;
    private Integer zzape = null;
    private Integer zzapf = null;
    private zzis zzapg = null;
    private Integer zzaph = null;
    private Integer zzapi = null;
    private Integer zzapj = null;
    private Integer zzapk = null;
    private Integer zzapl = null;
    private Long zzapm = null;

    public zziu() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public static zziu[] zzhu() {
        if (zzaow == null) {
            synchronized (zzbfg.zzebs) {
                if (zzaow == null) {
                    zzaow = new zziu[0];
                }
            }
        }
        return zzaow;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        zzbfi zzbfi;
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    return this;
                case 42:
                    if (this.zzaox == null) {
                        this.zzaox = new zziy();
                    }
                    zzbfi = this.zzaox;
                    break;
                case 50:
                    if (this.zzaoy == null) {
                        this.zzaoy = new zzja();
                    }
                    zzbfi = this.zzaoy;
                    break;
                case 58:
                    if (this.zzaoz == null) {
                        this.zzaoz = new zzjb();
                    }
                    zzbfi = this.zzaoz;
                    break;
                case 66:
                    if (this.zzapa == null) {
                        this.zzapa = new zzjc();
                    }
                    zzbfi = this.zzapa;
                    break;
                case 74:
                    if (this.zzapb == null) {
                        this.zzapb = new zziv();
                    }
                    zzbfi = this.zzapb;
                    break;
                case 82:
                    if (this.zzapc == null) {
                        this.zzapc = new zziz();
                    }
                    zzbfi = this.zzapc;
                    break;
                case 90:
                    if (this.zzapd == null) {
                        this.zzapd = new zzix();
                    }
                    zzbfi = this.zzapd;
                    break;
                case 96:
                    this.zzape = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 104:
                    this.zzapf = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 114:
                    if (this.zzapg == null) {
                        this.zzapg = new zzis();
                    }
                    zzbfi = this.zzapg;
                    break;
                case 120:
                    this.zzaph = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 128:
                    this.zzapi = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 136:
                    this.zzapj = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 144:
                    this.zzapk = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 152:
                    this.zzapl = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 160:
                    this.zzapm = Long.valueOf(zzbez.zzacd());
                    continue;
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        return this;
                    }
                    continue;
            }
            zzbez.zza(zzbfi);
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        zziy zziy = this.zzaox;
        if (zziy != null) {
            zzbfa.zza(5, (zzbfi) zziy);
        }
        zzja zzja = this.zzaoy;
        if (zzja != null) {
            zzbfa.zza(6, (zzbfi) zzja);
        }
        zzjb zzjb = this.zzaoz;
        if (zzjb != null) {
            zzbfa.zza(7, (zzbfi) zzjb);
        }
        zzjc zzjc = this.zzapa;
        if (zzjc != null) {
            zzbfa.zza(8, (zzbfi) zzjc);
        }
        zziv zziv = this.zzapb;
        if (zziv != null) {
            zzbfa.zza(9, (zzbfi) zziv);
        }
        zziz zziz = this.zzapc;
        if (zziz != null) {
            zzbfa.zza(10, (zzbfi) zziz);
        }
        zzix zzix = this.zzapd;
        if (zzix != null) {
            zzbfa.zza(11, (zzbfi) zzix);
        }
        Integer num = this.zzape;
        if (num != null) {
            zzbfa.zzm(12, num.intValue());
        }
        Integer num2 = this.zzapf;
        if (num2 != null) {
            zzbfa.zzm(13, num2.intValue());
        }
        zzis zzis = this.zzapg;
        if (zzis != null) {
            zzbfa.zza(14, (zzbfi) zzis);
        }
        Integer num3 = this.zzaph;
        if (num3 != null) {
            zzbfa.zzm(15, num3.intValue());
        }
        Integer num4 = this.zzapi;
        if (num4 != null) {
            zzbfa.zzm(16, num4.intValue());
        }
        Integer num5 = this.zzapj;
        if (num5 != null) {
            zzbfa.zzm(17, num5.intValue());
        }
        Integer num6 = this.zzapk;
        if (num6 != null) {
            zzbfa.zzm(18, num6.intValue());
        }
        Integer num7 = this.zzapl;
        if (num7 != null) {
            zzbfa.zzm(19, num7.intValue());
        }
        Long l = this.zzapm;
        if (l != null) {
            zzbfa.zza(20, l.longValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        zziy zziy = this.zzaox;
        if (zziy != null) {
            zzr += zzbfa.zzb(5, (zzbfi) zziy);
        }
        zzja zzja = this.zzaoy;
        if (zzja != null) {
            zzr += zzbfa.zzb(6, (zzbfi) zzja);
        }
        zzjb zzjb = this.zzaoz;
        if (zzjb != null) {
            zzr += zzbfa.zzb(7, (zzbfi) zzjb);
        }
        zzjc zzjc = this.zzapa;
        if (zzjc != null) {
            zzr += zzbfa.zzb(8, (zzbfi) zzjc);
        }
        zziv zziv = this.zzapb;
        if (zziv != null) {
            zzr += zzbfa.zzb(9, (zzbfi) zziv);
        }
        zziz zziz = this.zzapc;
        if (zziz != null) {
            zzr += zzbfa.zzb(10, (zzbfi) zziz);
        }
        zzix zzix = this.zzapd;
        if (zzix != null) {
            zzr += zzbfa.zzb(11, (zzbfi) zzix);
        }
        Integer num = this.zzape;
        if (num != null) {
            zzr += zzbfa.zzq(12, num.intValue());
        }
        Integer num2 = this.zzapf;
        if (num2 != null) {
            zzr += zzbfa.zzq(13, num2.intValue());
        }
        zzis zzis = this.zzapg;
        if (zzis != null) {
            zzr += zzbfa.zzb(14, (zzbfi) zzis);
        }
        Integer num3 = this.zzaph;
        if (num3 != null) {
            zzr += zzbfa.zzq(15, num3.intValue());
        }
        Integer num4 = this.zzapi;
        if (num4 != null) {
            zzr += zzbfa.zzq(16, num4.intValue());
        }
        Integer num5 = this.zzapj;
        if (num5 != null) {
            zzr += zzbfa.zzq(17, num5.intValue());
        }
        Integer num6 = this.zzapk;
        if (num6 != null) {
            zzr += zzbfa.zzq(18, num6.intValue());
        }
        Integer num7 = this.zzapl;
        if (num7 != null) {
            zzr += zzbfa.zzq(19, num7.intValue());
        }
        Long l = this.zzapm;
        return l != null ? zzr + zzbfa.zze(20, l.longValue()) : zzr;
    }
}
