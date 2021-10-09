package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzawu extends zzbbo<zzawu, zza> implements zzbcw {
    private static volatile zzbdf<zzawu> zzakh;
    /* access modifiers changed from: private */
    public static final zzawu zzdjt;
    private int zzdih;
    private zzawq zzdjj;
    private zzbah zzdjr = zzbah.zzdpq;
    private zzbah zzdjs = zzbah.zzdpq;

    public static final class zza extends zzbbo.zza<zzawu, zza> implements zzbcw {
        private zza() {
            super(zzawu.zzdjt);
        }

        /* synthetic */ zza(zzawv zzawv) {
            this();
        }

        public final zza zzac(zzbah zzbah) {
            zzadh();
            ((zzawu) this.zzdtx).zzz(zzbah);
            return this;
        }

        public final zza zzad(zzbah zzbah) {
            zzadh();
            ((zzawu) this.zzdtx).zzaa(zzbah);
            return this;
        }

        public final zza zzas(int i) {
            zzadh();
            ((zzawu) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzc(zzawq zzawq) {
            zzadh();
            ((zzawu) this.zzdtx).zzb(zzawq);
            return this;
        }
    }

    static {
        zzawu zzawu = new zzawu();
        zzdjt = zzawu;
        zzbbo.zza(zzawu.class, zzawu);
    }

    private zzawu() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzdih = i;
    }

    /* access modifiers changed from: private */
    public final void zzaa(zzbah zzbah) {
        if (zzbah != null) {
            this.zzdjs = zzbah;
            return;
        }
        throw null;
    }

    public static zzawu zzab(zzbah zzbah) throws zzbbu {
        return (zzawu) zzbbo.zza(zzdjt, zzbah);
    }

    /* access modifiers changed from: private */
    public final void zzb(zzawq zzawq) {
        if (zzawq != null) {
            this.zzdjj = zzawq;
            return;
        }
        throw null;
    }

    public static zza zzye() {
        return (zza) ((zzbbo.zza) zzdjt.zza(zzbbo.zze.zzdue, (Object) null, (Object) null));
    }

    public static zzawu zzyf() {
        return zzdjt;
    }

    /* access modifiers changed from: private */
    public final void zzz(zzbah zzbah) {
        if (zzbah != null) {
            this.zzdjr = zzbah;
            return;
        }
        throw null;
    }

    public final int getVersion() {
        return this.zzdih;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzawu>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzawu> zzbdf;
        switch (zzawv.zzakf[i - 1]) {
            case 1:
                return new zzawu();
            case 2:
                return new zza((zzawv) null);
            case 3:
                return zza((zzbcu) zzdjt, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", new Object[]{"zzdih", "zzdjj", "zzdjr", "zzdjs"});
            case 4:
                return zzdjt;
            case 5:
                zzbdf<zzawu> zzbdf2 = zzakh;
                zzbdf<zzawu> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzawu.class) {
                        zzbdf<zzawu> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdjt);
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

    public final zzbah zzyc() {
        return this.zzdjr;
    }

    public final zzbah zzyd() {
        return this.zzdjs;
    }
}
