package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;
import java.util.List;

public final class zzayf extends zzbbo<zzayf, zza> implements zzbcw {
    private static volatile zzbdf<zzayf> zzakh;
    /* access modifiers changed from: private */
    public static final zzayf zzdmt;
    private int zzdlq;
    private String zzdmr = "";
    private zzbbt<zzaxp> zzdms = zzadd();

    public static final class zza extends zzbbo.zza<zzayf, zza> implements zzbcw {
        private zza() {
            super(zzayf.zzdmt);
        }

        /* synthetic */ zza(zzayg zzayg) {
            this();
        }

        public final zza zzb(zzaxp zzaxp) {
            zzadh();
            ((zzayf) this.zzdtx).zza(zzaxp);
            return this;
        }

        public final zza zzej(String str) {
            zzadh();
            ((zzayf) this.zzdtx).zzei(str);
            return this;
        }
    }

    static {
        zzayf zzayf = new zzayf();
        zzdmt = zzayf;
        zzbbo.zza(zzayf.class, zzayf);
    }

    private zzayf() {
    }

    /* access modifiers changed from: private */
    public final void zza(zzaxp zzaxp) {
        if (zzaxp != null) {
            if (!this.zzdms.zzaay()) {
                zzbbt<zzaxp> zzbbt = this.zzdms;
                int size = zzbbt.size();
                this.zzdms = zzbbt.zzbm(size == 0 ? 10 : size << 1);
            }
            this.zzdms.add(zzaxp);
            return;
        }
        throw null;
    }

    public static zza zzaam() {
        return (zza) ((zzbbo.zza) zzdmt.zza(zzbbo.zze.zzdue, (Object) null, (Object) null));
    }

    /* access modifiers changed from: private */
    public final void zzei(String str) {
        if (str != null) {
            this.zzdmr = str;
            return;
        }
        throw null;
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.ads.zzbdf<com.google.android.gms.internal.ads.zzayf>, com.google.android.gms.internal.ads.zzbbo$zzb] */
    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        zzbdf<zzayf> zzbdf;
        switch (zzayg.zzakf[i - 1]) {
            case 1:
                return new zzayf();
            case 2:
                return new zza((zzayg) null);
            case 3:
                return zza((zzbcu) zzdmt, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zzdlq", "zzdmr", "zzdms", zzaxp.class});
            case 4:
                return zzdmt;
            case 5:
                zzbdf<zzayf> zzbdf2 = zzakh;
                zzbdf<zzayf> zzbdf3 = zzbdf2;
                if (zzbdf2 == null) {
                    synchronized (zzayf.class) {
                        zzbdf<zzayf> zzbdf4 = zzakh;
                        zzbdf = zzbdf4;
                        if (zzbdf4 == null) {
                            ? zzb = new zzbbo.zzb(zzdmt);
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

    public final List<zzaxp> zzaal() {
        return this.zzdms;
    }
}
