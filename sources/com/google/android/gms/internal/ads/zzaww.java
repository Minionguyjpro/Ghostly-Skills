package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzaww extends zzbbo<zzaww, zza> implements zzbcw {
    private static volatile zzbdf<zzaww> zzakh;
    /* access modifiers changed from: private */
    public static final zzaww zzdjx;
    private int zzdju;
    private int zzdjv;
    private zzbah zzdjw = zzbah.zzdpq;

    public static final class zza extends zzbbo.zza<zzaww, zza> implements zzbcw {
        private zza() {
            super(zzaww.zzdjx);
        }

        /* synthetic */ zza(zzawx zzawx) {
            this();
        }
    }

    static {
        zzaww zzaww = new zzaww();
        zzdjx = zzaww;
        zzbbo.zza(zzaww.class, zzaww);
    }

    private zzaww() {
    }

    public static zzaww zzyk() {
        return zzdjx;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzaww>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzaww> zzbdf;
        switch (zzawx.zzakf[i - 1]) {
            case 1:
                return new zzaww();
            case 2:
                return new zza((zzawx) null);
            case 3:
                return zza((zzbcu) zzdjx, "\u0000\u0003\u0000\u0000\u0001\u000b\u000b\f\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"zzdju", "zzdjv", "zzdjw"});
            case 4:
                return zzdjx;
            case 5:
                zzbdf<zzaww> zzbdf2 = zzakh;
                zzbdf<zzaww> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzaww.class) {
                        zzbdf<zzaww> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdjx);
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

    public final zzawy zzyh() {
        zzawy zzat = zzawy.zzat(this.zzdju);
        return zzat == null ? zzawy.UNRECOGNIZED : zzat;
    }

    public final zzaxa zzyi() {
        zzaxa zzau = zzaxa.zzau(this.zzdjv);
        return zzau == null ? zzaxa.UNRECOGNIZED : zzau;
    }

    public final zzbah zzyj() {
        return this.zzdjw;
    }
}
