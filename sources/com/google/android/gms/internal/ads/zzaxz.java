package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzaxz extends zzbbo<zzaxz, zza> implements zzbcw {
    private static volatile zzbdf<zzaxz> zzakh;
    /* access modifiers changed from: private */
    public static final zzaxz zzdmg;
    private int zzdih;
    private zzayb zzdmf;

    public static final class zza extends zzbbo.zza<zzaxz, zza> implements zzbcw {
        private zza() {
            super(zzaxz.zzdmg);
        }

        /* synthetic */ zza(zzaya zzaya) {
            this();
        }

        public final zza zzb(zzayb zzayb) {
            zzadh();
            ((zzaxz) this.zzdtx).zza(zzayb);
            return this;
        }

        public final zza zzbf(int i) {
            zzadh();
            ((zzaxz) this.zzdtx).setVersion(0);
            return this;
        }
    }

    static {
        zzaxz zzaxz = new zzaxz();
        zzdmg = zzaxz;
        zzbbo.zza(zzaxz.class, zzaxz);
    }

    private zzaxz() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzdih = i;
    }

    /* access modifiers changed from: private */
    public final void zza(zzayb zzayb) {
        if (zzayb != null) {
            this.zzdmf = zzayb;
            return;
        }
        throw null;
    }

    public static zza zzaaf() {
        return (zza) ((zzbbo.zza) zzdmg.zza(zzbbo.zze.zzdue, (Object) null, (Object) null));
    }

    public static zzaxz zzal(zzbah zzbah) throws zzbbu {
        return (zzaxz) zzbbo.zza(zzdmg, zzbah);
    }

    public final int getVersion() {
        return this.zzdih;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbbo$zzb, com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzaxz>] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzaxz> zzbdf;
        switch (zzaya.zzakf[i - 1]) {
            case 1:
                return new zzaxz();
            case 2:
                return new zza((zzaya) null);
            case 3:
                return zza((zzbcu) zzdmg, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzdih", "zzdmf"});
            case 4:
                return zzdmg;
            case 5:
                zzbdf<zzaxz> zzbdf2 = zzakh;
                zzbdf<zzaxz> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzaxz.class) {
                        zzbdf<zzaxz> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdmg);
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

    public final zzayb zzaae() {
        zzayb zzayb = this.zzdmf;
        return zzayb == null ? zzayb.zzaaj() : zzayb;
    }
}
