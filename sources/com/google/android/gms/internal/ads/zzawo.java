package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzawo extends zzbbo<zzawo, zza> implements zzbcw {
    private static volatile zzbdf<zzawo> zzakh;
    /* access modifiers changed from: private */
    public static final zzawo zzdjk;
    private zzawq zzdjj;

    public static final class zza extends zzbbo.zza<zzawo, zza> implements zzbcw {
        private zza() {
            super(zzawo.zzdjk);
        }

        /* synthetic */ zza(zzawp zzawp) {
            this();
        }
    }

    static {
        zzawo zzawo = new zzawo();
        zzdjk = zzawo;
        zzbbo.zza(zzawo.class, zzawo);
    }

    private zzawo() {
    }

    public static zzawo zzw(zzbah zzbah) throws zzbbu {
        return (zzawo) zzbbo.zza(zzdjk, zzbah);
    }

    /* JADX WARNING: type inference failed for: r1v13, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzawo>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzawo> zzbdf;
        switch (zzawp.zzakf[i - 1]) {
            case 1:
                return new zzawo();
            case 2:
                return new zza((zzawp) null);
            case 3:
                return zza((zzbcu) zzdjk, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001\t", new Object[]{"zzdjj"});
            case 4:
                return zzdjk;
            case 5:
                zzbdf<zzawo> zzbdf2 = zzakh;
                zzbdf<zzawo> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzawo.class) {
                        zzbdf<zzawo> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdjk);
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

    public final zzawq zzxs() {
        zzawq zzawq = this.zzdjj;
        return zzawq == null ? zzawq.zzxx() : zzawq;
    }
}
