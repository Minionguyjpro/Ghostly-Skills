package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzawg extends zzbbo<zzawg, zza> implements zzbcw {
    private static volatile zzbdf<zzawg> zzakh;
    /* access modifiers changed from: private */
    public static final zzawg zzdja;
    private int zzdir;

    public static final class zza extends zzbbo.zza<zzawg, zza> implements zzbcw {
        private zza() {
            super(zzawg.zzdja);
        }

        /* synthetic */ zza(zzawh zzawh) {
            this();
        }
    }

    static {
        zzawg zzawg = new zzawg();
        zzdja = zzawg;
        zzbbo.zza(zzawg.class, zzawg);
    }

    private zzawg() {
    }

    public static zzawg zzt(zzbah zzbah) throws zzbbu {
        return (zzawg) zzbbo.zza(zzdja, zzbah);
    }

    public final int getKeySize() {
        return this.zzdir;
    }

    /* JADX WARNING: type inference failed for: r1v13, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzawg>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzawg> zzbdf;
        switch (zzawh.zzakf[i - 1]) {
            case 1:
                return new zzawg();
            case 2:
                return new zza((zzawh) null);
            case 3:
                return zza((zzbcu) zzdja, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0003\u0000\u0000\u0000\u0002\u000b", new Object[]{"zzdir"});
            case 4:
                return zzdja;
            case 5:
                zzbdf<zzawg> zzbdf2 = zzakh;
                zzbdf<zzawg> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzawg.class) {
                        zzbdf<zzawg> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdja);
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
}
