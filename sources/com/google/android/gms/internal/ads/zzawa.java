package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzawa extends zzbbo<zzawa, zza> implements zzbcw {
    private static volatile zzbdf<zzawa> zzakh;
    /* access modifiers changed from: private */
    public static final zzawa zzdix;
    private int zzdir;
    private zzawc zzdiv;

    public static final class zza extends zzbbo.zza<zzawa, zza> implements zzbcw {
        private zza() {
            super(zzawa.zzdix);
        }

        /* synthetic */ zza(zzawb zzawb) {
            this();
        }
    }

    static {
        zzawa zzawa = new zzawa();
        zzdix = zzawa;
        zzbbo.zza(zzawa.class, zzawa);
    }

    private zzawa() {
    }

    public static zzawa zzq(zzbah zzbah) throws zzbbu {
        return (zzawa) zzbbo.zza(zzdix, zzbah);
    }

    public final int getKeySize() {
        return this.zzdir;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzawa>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzawa> zzbdf;
        switch (zzawb.zzakf[i - 1]) {
            case 1:
                return new zzawa();
            case 2:
                return new zza((zzawb) null);
            case 3:
                return zza((zzbcu) zzdix, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzdiv", "zzdir"});
            case 4:
                return zzdix;
            case 5:
                zzbdf<zzawa> zzbdf2 = zzakh;
                zzbdf<zzawa> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzawa.class) {
                        zzbdf<zzawa> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdix);
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

    public final zzawc zzxe() {
        zzawc zzawc = this.zzdiv;
        return zzawc == null ? zzawc.zzxi() : zzawc;
    }
}
