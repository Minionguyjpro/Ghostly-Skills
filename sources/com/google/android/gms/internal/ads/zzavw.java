package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzavw extends zzbbo<zzavw, zza> implements zzbcw {
    private static volatile zzbdf<zzavw> zzakh;
    /* access modifiers changed from: private */
    public static final zzavw zzdiu;
    private int zzdit;

    public static final class zza extends zzbbo.zza<zzavw, zza> implements zzbcw {
        private zza() {
            super(zzavw.zzdiu);
        }

        /* synthetic */ zza(zzavx zzavx) {
            this();
        }
    }

    static {
        zzavw zzavw = new zzavw();
        zzdiu = zzavw;
        zzbbo.zza(zzavw.class, zzavw);
    }

    private zzavw() {
    }

    public static zzavw zzxc() {
        return zzdiu;
    }

    /* JADX WARNING: type inference failed for: r1v13, types: [com.google.android.gms.internal.ads.zzbbo$zzb, com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzavw>] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzavw> zzbdf;
        switch (zzavx.zzakf[i - 1]) {
            case 1:
                return new zzavw();
            case 2:
                return new zza((zzavx) null);
            case 3:
                return zza((zzbcu) zzdiu, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzdit"});
            case 4:
                return zzdiu;
            case 5:
                zzbdf<zzavw> zzbdf2 = zzakh;
                zzbdf<zzavw> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzavw.class) {
                        zzbdf<zzavw> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdiu);
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

    public final int zzxb() {
        return this.zzdit;
    }
}
