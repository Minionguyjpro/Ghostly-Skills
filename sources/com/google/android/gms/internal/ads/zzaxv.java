package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzaxv extends zzbbo<zzaxv, zza> implements zzbcw {
    private static volatile zzbdf<zzaxv> zzakh;
    /* access modifiers changed from: private */
    public static final zzaxv zzdmc;
    private int zzdih;
    private zzaxx zzdmb;

    public static final class zza extends zzbbo.zza<zzaxv, zza> implements zzbcw {
        private zza() {
            super(zzaxv.zzdmc);
        }

        /* synthetic */ zza(zzaxw zzaxw) {
            this();
        }

        public final zza zzb(zzaxx zzaxx) {
            zzadh();
            ((zzaxv) this.zzdtx).zza(zzaxx);
            return this;
        }

        public final zza zzbe(int i) {
            zzadh();
            ((zzaxv) this.zzdtx).setVersion(0);
            return this;
        }
    }

    static {
        zzaxv zzaxv = new zzaxv();
        zzdmc = zzaxv;
        zzbbo.zza(zzaxv.class, zzaxv);
    }

    private zzaxv() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzdih = i;
    }

    /* access modifiers changed from: private */
    public final void zza(zzaxx zzaxx) {
        if (zzaxx != null) {
            this.zzdmb = zzaxx;
            return;
        }
        throw null;
    }

    public static zzaxv zzaj(zzbah zzbah) throws zzbbu {
        return (zzaxv) zzbbo.zza(zzdmc, zzbah);
    }

    public static zza zzzz() {
        return (zza) ((zzbbo.zza) zzdmc.zza(zzbbo.zze.zzdue, (Object) null, (Object) null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzaxv>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzaxv> zzbdf;
        switch (zzaxw.zzakf[i - 1]) {
            case 1:
                return new zzaxv();
            case 2:
                return new zza((zzaxw) null);
            case 3:
                return zza((zzbcu) zzdmc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzdih", "zzdmb"});
            case 4:
                return zzdmc;
            case 5:
                zzbdf<zzaxv> zzbdf2 = zzakh;
                zzbdf<zzaxv> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzaxv.class) {
                        zzbdf<zzaxv> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdmc);
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

    public final zzaxx zzzy() {
        zzaxx zzaxx = this.zzdmb;
        return zzaxx == null ? zzaxx.zzaac() : zzaxx;
    }
}
