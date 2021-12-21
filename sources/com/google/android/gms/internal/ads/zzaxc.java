package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzaxc extends zzbbo<zzaxc, zza> implements zzbcw {
    private static volatile zzbdf<zzaxc> zzakh;
    /* access modifiers changed from: private */
    public static final zzaxc zzdkn;
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;
    private zzaxg zzdkm;

    public static final class zza extends zzbbo.zza<zzaxc, zza> implements zzbcw {
        private zza() {
            super(zzaxc.zzdkn);
        }

        /* synthetic */ zza(zzaxd zzaxd) {
            this();
        }

        public final zza zzaf(zzbah zzbah) {
            zzadh();
            ((zzaxc) this.zzdtx).zzk(zzbah);
            return this;
        }

        public final zza zzav(int i) {
            zzadh();
            ((zzaxc) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzc(zzaxg zzaxg) {
            zzadh();
            ((zzaxc) this.zzdtx).zzb(zzaxg);
            return this;
        }
    }

    static {
        zzaxc zzaxc = new zzaxc();
        zzdkn = zzaxc;
        zzbbo.zza(zzaxc.class, zzaxc);
    }

    private zzaxc() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzdih = i;
    }

    public static zzaxc zzae(zzbah zzbah) throws zzbbu {
        return (zzaxc) zzbbo.zza(zzdkn, zzbah);
    }

    /* access modifiers changed from: private */
    public final void zzb(zzaxg zzaxg) {
        if (zzaxg != null) {
            this.zzdkm = zzaxg;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public final void zzk(zzbah zzbah) {
        if (zzbah != null) {
            this.zzdip = zzbah;
            return;
        }
        throw null;
    }

    public static zza zzyn() {
        return (zza) ((zzbbo.zza) zzdkn.zza(zzbbo.zze.zzdue, (Object) null, (Object) null));
    }

    public static zzaxc zzyo() {
        return zzdkn;
    }

    public final int getVersion() {
        return this.zzdih;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzaxc>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzaxc> zzbdf;
        switch (zzaxd.zzakf[i - 1]) {
            case 1:
                return new zzaxc();
            case 2:
                return new zza((zzaxd) null);
            case 3:
                return zza((zzbcu) zzdkn, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzdih", "zzdkm", "zzdip"});
            case 4:
                return zzdkn;
            case 5:
                zzbdf<zzaxc> zzbdf2 = zzakh;
                zzbdf<zzaxc> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzaxc.class) {
                        zzbdf<zzaxc> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdkn);
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

    public final zzbah zzwv() {
        return this.zzdip;
    }

    public final zzaxg zzym() {
        zzaxg zzaxg = this.zzdkm;
        return zzaxg == null ? zzaxg.zzyu() : zzaxg;
    }
}
