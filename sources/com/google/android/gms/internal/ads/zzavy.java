package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzavy extends zzbbo<zzavy, zza> implements zzbcw {
    private static volatile zzbdf<zzavy> zzakh;
    /* access modifiers changed from: private */
    public static final zzavy zzdiw;
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;
    private zzawc zzdiv;

    public static final class zza extends zzbbo.zza<zzavy, zza> implements zzbcw {
        private zza() {
            super(zzavy.zzdiw);
        }

        /* synthetic */ zza(zzavz zzavz) {
            this();
        }

        public final zza zzan(int i) {
            zzadh();
            ((zzavy) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzb(zzawc zzawc) {
            zzadh();
            ((zzavy) this.zzdtx).zza(zzawc);
            return this;
        }

        public final zza zzp(zzbah zzbah) {
            zzadh();
            ((zzavy) this.zzdtx).zzk(zzbah);
            return this;
        }
    }

    static {
        zzavy zzavy = new zzavy();
        zzdiw = zzavy;
        zzbbo.zza(zzavy.class, zzavy);
    }

    private zzavy() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzdih = i;
    }

    /* access modifiers changed from: private */
    public final void zza(zzawc zzawc) {
        if (zzawc != null) {
            this.zzdiv = zzawc;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public final void zzk(zzbah zzbah) {
        if (zzbah != null) {
            this.zzdip = zzbah;
            return;
        }
        throw null;
    }

    public static zzavy zzo(zzbah zzbah) throws zzbbu {
        return (zzavy) zzbbo.zza(zzdiw, zzbah);
    }

    public static zza zzxf() {
        return (zza) ((zzbbo.zza) zzdiw.zza(zzbbo.zze.zzdue, (Object) null, (Object) null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzavy>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzavy> zzbdf;
        switch (zzavz.zzakf[i - 1]) {
            case 1:
                return new zzavy();
            case 2:
                return new zza((zzavz) null);
            case 3:
                return zza((zzbcu) zzdiw, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzdih", "zzdiv", "zzdip"});
            case 4:
                return zzdiw;
            case 5:
                zzbdf<zzavy> zzbdf2 = zzakh;
                zzbdf<zzavy> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzavy.class) {
                        zzbdf<zzavy> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdiw);
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

    public final zzawc zzxe() {
        zzawc zzawc = this.zzdiv;
        return zzawc == null ? zzawc.zzxi() : zzawc;
    }
}
