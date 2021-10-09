package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzaxn extends zzbbo<zzaxn, zza> implements zzbcw {
    private static volatile zzbdf<zzaxn> zzakh;
    /* access modifiers changed from: private */
    public static final zzaxn zzdlk;
    private String zzdks = "";
    private zzbah zzdkt = zzbah.zzdpq;
    private int zzdlj;

    public static final class zza extends zzbbo.zza<zzaxn, zza> implements zzbcw {
        private zza() {
            super(zzaxn.zzdlk);
        }

        /* synthetic */ zza(zzaxo zzaxo) {
            this();
        }
    }

    static {
        zzaxn zzaxn = new zzaxn();
        zzdlk = zzaxn;
        zzbbo.zza(zzaxn.class, zzaxn);
    }

    private zzaxn() {
    }

    public static zzaxn zzzc() {
        return zzdlk;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzaxn>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzaxn> zzbdf;
        switch (zzaxo.zzakf[i - 1]) {
            case 1:
                return new zzaxn();
            case 2:
                return new zza((zzaxo) null);
            case 3:
                return zza((zzbcu) zzdlk, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzdks", "zzdkt", "zzdlj"});
            case 4:
                return zzdlk;
            case 5:
                zzbdf<zzaxn> zzbdf2 = zzakh;
                zzbdf<zzaxn> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzaxn.class) {
                        zzbdf<zzaxn> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdlk);
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

    public final String zzyw() {
        return this.zzdks;
    }

    public final zzbah zzyx() {
        return this.zzdkt;
    }
}
