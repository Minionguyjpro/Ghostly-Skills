package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

public final class zzawe extends zzbbo<zzawe, zza> implements zzbcw {
    private static volatile zzbdf<zzawe> zzakh;
    /* access modifiers changed from: private */
    public static final zzawe zzdiz;
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;

    public static final class zza extends zzbbo.zza<zzawe, zza> implements zzbcw {
        private zza() {
            super(zzawe.zzdiz);
        }

        /* synthetic */ zza(zzawf zzawf) {
            this();
        }

        public final zza zzao(int i) {
            zzadh();
            ((zzawe) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzs(zzbah zzbah) {
            zzadh();
            ((zzawe) this.zzdtx).zzk(zzbah);
            return this;
        }
    }

    static {
        zzawe zzawe = new zzawe();
        zzdiz = zzawe;
        zzbbo.zza(zzawe.class, zzawe);
    }

    private zzawe() {
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzdih = i;
    }

    /* access modifiers changed from: private */
    public final void zzk(zzbah zzbah) {
        if (zzbah != null) {
            this.zzdip = zzbah;
            return;
        }
        throw null;
    }

    public static zzawe zzr(zzbah zzbah) throws zzbbu {
        return (zzawe) zzbbo.zza(zzdiz, zzbah);
    }

    public static zza zzxk() {
        return (zza) ((zzbbo.zza) zzdiz.zza(zzbbo.zze.zzdue, (Object) null, (Object) null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzawe>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzawe> zzbdf;
        switch (zzawf.zzakf[i - 1]) {
            case 1:
                return new zzawe();
            case 2:
                return new zza((zzawf) null);
            case 3:
                return zza((zzbcu) zzdiz, "\u0000\u0002\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzdih", "zzdip"});
            case 4:
                return zzdiz;
            case 5:
                zzbdf<zzawe> zzbdf2 = zzakh;
                zzbdf<zzawe> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzawe.class) {
                        zzbdf<zzawe> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdiz);
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
}
