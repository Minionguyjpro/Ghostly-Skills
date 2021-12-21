package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzawq extends zzbbo<zzawq, zza> implements zzbcw {
    private static volatile zzbdf<zzawq> zzakh;
    /* access modifiers changed from: private */
    public static final zzawq zzdjo;
    private zzaww zzdjl;
    private zzawm zzdjm;
    private int zzdjn;

    public static final class zza extends zzbbo.zza<zzawq, zza> implements zzbcw {
        private zza() {
            super(zzawq.zzdjo);
        }

        /* synthetic */ zza(zzawr zzawr) {
            this();
        }
    }

    static {
        zzawq zzawq = new zzawq();
        zzdjo = zzawq;
        zzbbo.zza(zzawq.class, zzawq);
    }

    private zzawq() {
    }

    public static zzawq zzxx() {
        return zzdjo;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzawq>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzawq> zzbdf;
        switch (zzawr.zzakf[i - 1]) {
            case 1:
                return new zzawq();
            case 2:
                return new zza((zzawr) null);
            case 3:
                return zza((zzbcu) zzdjo, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"zzdjl", "zzdjm", "zzdjn"});
            case 4:
                return zzdjo;
            case 5:
                zzbdf<zzawq> zzbdf2 = zzakh;
                zzbdf<zzawq> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzawq.class) {
                        zzbdf<zzawq> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdjo);
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

    public final zzaww zzxu() {
        zzaww zzaww = this.zzdjl;
        return zzaww == null ? zzaww.zzyk() : zzaww;
    }

    public final zzawm zzxv() {
        zzawm zzawm = this.zzdjm;
        return zzawm == null ? zzawm.zzxq() : zzawm;
    }

    public final zzawk zzxw() {
        zzawk zzaq = zzawk.zzaq(this.zzdjn);
        return zzaq == null ? zzawk.UNRECOGNIZED : zzaq;
    }
}
