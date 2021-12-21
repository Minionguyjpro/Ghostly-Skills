package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzaxg extends zzbbo<zzaxg, zza> implements zzbcw {
    private static volatile zzbdf<zzaxg> zzakh;
    /* access modifiers changed from: private */
    public static final zzaxg zzdkr;
    private int zzdkp;
    private int zzdkq;

    public static final class zza extends zzbbo.zza<zzaxg, zza> implements zzbcw {
        private zza() {
            super(zzaxg.zzdkr);
        }

        /* synthetic */ zza(zzaxh zzaxh) {
            this();
        }
    }

    static {
        zzaxg zzaxg = new zzaxg();
        zzdkr = zzaxg;
        zzbbo.zza(zzaxg.class, zzaxg);
    }

    private zzaxg() {
    }

    public static zzaxg zzyu() {
        return zzdkr;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzaxg>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzaxg> zzbdf;
        switch (zzaxh.zzakf[i - 1]) {
            case 1:
                return new zzaxg();
            case 2:
                return new zza((zzaxh) null);
            case 3:
                return zza((zzbcu) zzdkr, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zzdkp", "zzdkq"});
            case 4:
                return zzdkr;
            case 5:
                zzbdf<zzaxg> zzbdf2 = zzakh;
                zzbdf<zzaxg> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzaxg.class) {
                        zzbdf<zzaxg> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdkr);
                            zzakh = zzb;
                            zzbdf = zzb;
                        }
                    }
                    zzbdf3 = zzbdf;
                }
                return zzbdf3;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final zzaxa zzys() {
        zzaxa zzau = zzaxa.zzau(this.zzdkp);
        return zzau == null ? zzaxa.UNRECOGNIZED : zzau;
    }

    public final int zzyt() {
        return this.zzdkq;
    }
}
