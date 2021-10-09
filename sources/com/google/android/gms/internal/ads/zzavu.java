package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzavu extends zzbbo<zzavu, zza> implements zzbcw {
    private static volatile zzbdf<zzavu> zzakh;
    /* access modifiers changed from: private */
    public static final zzavu zzdis;
    private zzavw zzdio;
    private int zzdir;

    public static final class zza extends zzbbo.zza<zzavu, zza> implements zzbcw {
        private zza() {
            super(zzavu.zzdis);
        }

        /* synthetic */ zza(zzavv zzavv) {
            this();
        }
    }

    static {
        zzavu zzavu = new zzavu();
        zzdis = zzavu;
        zzbbo.zza(zzavu.class, zzavu);
    }

    private zzavu() {
    }

    public static zzavu zzn(zzbah zzbah) throws zzbbu {
        return (zzavu) zzbbo.zza(zzdis, zzbah);
    }

    public static zzavu zzwz() {
        return zzdis;
    }

    public final int getKeySize() {
        return this.zzdir;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzavu>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzavu> zzbdf;
        switch (zzavv.zzakf[i - 1]) {
            case 1:
                return new zzavu();
            case 2:
                return new zza((zzavv) null);
            case 3:
                return zza((zzbcu) zzdis, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzdio", "zzdir"});
            case 4:
                return zzdis;
            case 5:
                zzbdf<zzavu> zzbdf2 = zzakh;
                zzbdf<zzavu> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzavu.class) {
                        zzbdf<zzavu> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdis);
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

    public final zzavw zzwu() {
        zzavw zzavw = this.zzdio;
        return zzavw == null ? zzavw.zzxc() : zzavw;
    }
}
