package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzavs extends zzbbo<zzavs, zza> implements zzbcw {
    private static volatile zzbdf<zzavs> zzakh;
    /* access modifiers changed from: private */
    public static final zzavs zzdiq;
    private int zzdih;
    private zzavw zzdio;
    private zzbah zzdip = zzbah.zzdpq;

    public static final class zza extends zzbbo.zza<zzavs, zza> implements zzbcw {
        private zza() {
            super(zzavs.zzdiq);
        }

        /* synthetic */ zza(zzavt zzavt) {
            this();
        }

        public final zza zzam(int i) {
            zzadh();
            ((zzavs) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzc(zzavw zzavw) {
            zzadh();
            ((zzavs) this.zzdtx).zzb(zzavw);
            return this;
        }

        public final zza zzm(zzbah zzbah) {
            zzadh();
            ((zzavs) this.zzdtx).zzk(zzbah);
            return this;
        }
    }

    static {
        zzavs zzavs = new zzavs();
        zzdiq = zzavs;
        zzbbo.zza(zzavs.class, zzavs);
    }

    private zzavs() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzdih = i;
    }

    /* access modifiers changed from: private */
    public final void zzb(zzavw zzavw) {
        if (zzavw != null) {
            this.zzdio = zzavw;
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

    public static zzavs zzl(zzbah zzbah) throws zzbbu {
        return (zzavs) zzbbo.zza(zzdiq, zzbah);
    }

    public static zza zzww() {
        return (zza) ((zzbbo.zza) zzdiq.zza(zzbbo.zze.zzdue, (Object) null, (Object) null));
    }

    public static zzavs zzwx() {
        return zzdiq;
    }

    public final int getVersion() {
        return this.zzdih;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzavs>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzavs> zzbdf;
        switch (zzavt.zzakf[i - 1]) {
            case 1:
                return new zzavs();
            case 2:
                return new zza((zzavt) null);
            case 3:
                return zza((zzbcu) zzdiq, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzdih", "zzdio", "zzdip"});
            case 4:
                return zzdiq;
            case 5:
                zzbdf<zzavs> zzbdf2 = zzakh;
                zzbdf<zzavs> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzavs.class) {
                        zzbdf<zzavs> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdiq);
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

    public final zzbah zzwv() {
        return this.zzdip;
    }
}
