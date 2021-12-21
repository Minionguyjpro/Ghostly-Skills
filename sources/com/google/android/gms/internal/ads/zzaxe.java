package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzaxe extends zzbbo<zzaxe, zza> implements zzbcw {
    private static volatile zzbdf<zzaxe> zzakh;
    /* access modifiers changed from: private */
    public static final zzaxe zzdko;
    private int zzdir;
    private zzaxg zzdkm;

    public static final class zza extends zzbbo.zza<zzaxe, zza> implements zzbcw {
        private zza() {
            super(zzaxe.zzdko);
        }

        /* synthetic */ zza(zzaxf zzaxf) {
            this();
        }
    }

    static {
        zzaxe zzaxe = new zzaxe();
        zzdko = zzaxe;
        zzbbo.zza(zzaxe.class, zzaxe);
    }

    private zzaxe() {
    }

    public static zzaxe zzag(zzbah zzbah) throws zzbbu {
        return (zzaxe) zzbbo.zza(zzdko, zzbah);
    }

    public static zzaxe zzyq() {
        return zzdko;
    }

    public final int getKeySize() {
        return this.zzdir;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzaxe>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzaxe> zzbdf;
        switch (zzaxf.zzakf[i - 1]) {
            case 1:
                return new zzaxe();
            case 2:
                return new zza((zzaxf) null);
            case 3:
                return zza((zzbcu) zzdko, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzdkm", "zzdir"});
            case 4:
                return zzdko;
            case 5:
                zzbdf<zzaxe> zzbdf2 = zzakh;
                zzbdf<zzaxe> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzaxe.class) {
                        zzbdf<zzaxe> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdko);
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

    public final zzaxg zzym() {
        zzaxg zzaxg = this.zzdkm;
        return zzaxg == null ? zzaxg.zzyu() : zzaxg;
    }
}
