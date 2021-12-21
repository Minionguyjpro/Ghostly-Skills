package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzawi extends zzbbo<zzawi, zza> implements zzbcw {
    private static volatile zzbdf<zzawi> zzakh;
    /* access modifiers changed from: private */
    public static final zzawi zzdjb;
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;

    public static final class zza extends zzbbo.zza<zzawi, zza> implements zzbcw {
        private zza() {
            super(zzawi.zzdjb);
        }

        /* synthetic */ zza(zzawj zzawj) {
            this();
        }

        public final zza zzap(int i) {
            zzadh();
            ((zzawi) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzv(zzbah zzbah) {
            zzadh();
            ((zzawi) this.zzdtx).zzk(zzbah);
            return this;
        }
    }

    static {
        zzawi zzawi = new zzawi();
        zzdjb = zzawi;
        zzbbo.zza(zzawi.class, zzawi);
    }

    private zzawi() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzdih = i;
    }

    /* access modifiers changed from: private */
    public final void zzk(zzbah zzbah) {
        if (zzbah != null) {
            this.zzdip = zzbah;
            return;
        }
        throw null;
    }

    public static zzawi zzu(zzbah zzbah) throws zzbbu {
        return (zzawi) zzbbo.zza(zzdjb, zzbah);
    }

    public static zza zzxn() {
        return (zza) ((zzbbo.zza) zzdjb.zza(zzbbo.zze.zzdue, (Object) null, (Object) null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbbo$zzb, com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzawi>] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzawi> zzbdf;
        switch (zzawj.zzakf[i - 1]) {
            case 1:
                return new zzawi();
            case 2:
                return new zza((zzawj) null);
            case 3:
                return zza((zzbcu) zzdjb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zzdih", "zzdip"});
            case 4:
                return zzdjb;
            case 5:
                zzbdf<zzawi> zzbdf2 = zzakh;
                zzbdf<zzawi> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzawi.class) {
                        zzbdf<zzawi> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdjb);
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
}
