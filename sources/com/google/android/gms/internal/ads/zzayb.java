package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzayb extends zzbbo<zzayb, zza> implements zzbcw {
    private static volatile zzbdf<zzayb> zzakh;
    /* access modifiers changed from: private */
    public static final zzayb zzdmj;
    private String zzdmh = "";
    private zzaxn zzdmi;

    public static final class zza extends zzbbo.zza<zzayb, zza> implements zzbcw {
        private zza() {
            super(zzayb.zzdmj);
        }

        /* synthetic */ zza(zzayc zzayc) {
            this();
        }
    }

    static {
        zzayb zzayb = new zzayb();
        zzdmj = zzayb;
        zzbbo.zza(zzayb.class, zzayb);
    }

    private zzayb() {
    }

    public static zzayb zzaaj() {
        return zzdmj;
    }

    public static zzayb zzam(zzbah zzbah) throws zzbbu {
        return (zzayb) zzbbo.zza(zzdmj, zzbah);
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzayb>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzayb> zzbdf;
        switch (zzayc.zzakf[i - 1]) {
            case 1:
                return new zzayb();
            case 2:
                return new zza((zzayc) null);
            case 3:
                return zza((zzbcu) zzdmj, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"zzdmh", "zzdmi"});
            case 4:
                return zzdmj;
            case 5:
                zzbdf<zzayb> zzbdf2 = zzakh;
                zzbdf<zzayb> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzayb.class) {
                        zzbdf<zzayb> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdmj);
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

    public final String zzaah() {
        return this.zzdmh;
    }

    public final zzaxn zzaai() {
        zzaxn zzaxn = this.zzdmi;
        return zzaxn == null ? zzaxn.zzzc() : zzaxn;
    }
}
