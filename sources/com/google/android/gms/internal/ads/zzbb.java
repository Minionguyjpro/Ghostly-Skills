package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbb extends zzbfc<zzbb> {
    private static volatile zzbb[] zzfo;
    public Long zzdo = null;
    public Long zzdp = null;
    public Long zzfp = null;
    public Long zzfq = null;
    public Long zzfr = null;
    public Long zzfs = null;
    public Integer zzft;
    public Long zzfu = null;
    public Long zzfv = null;
    public Long zzfw = null;
    public Integer zzfx;
    public Long zzfy = null;
    public Long zzfz = null;
    public Long zzga = null;
    public Long zzgb = null;
    public Long zzgc = null;
    public Long zzgd = null;
    public Long zzge = null;
    public Long zzgf = null;
    private Long zzgg = null;
    private Long zzgh = null;

    public zzbb() {
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: zzc */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzbb zza(com.google.android.gms.internal.ads.zzbez r4) throws java.io.IOException {
        /*
            r3 = this;
        L_0x0000:
            int r0 = r4.zzabk()
            switch(r0) {
                case 0: goto L_0x0118;
                case 8: goto L_0x010c;
                case 16: goto L_0x0100;
                case 24: goto L_0x00f4;
                case 32: goto L_0x00e8;
                case 40: goto L_0x00dc;
                case 48: goto L_0x00d0;
                case 56: goto L_0x00b4;
                case 64: goto L_0x00a8;
                case 72: goto L_0x009c;
                case 80: goto L_0x0090;
                case 88: goto L_0x007c;
                case 96: goto L_0x0071;
                case 104: goto L_0x0066;
                case 112: goto L_0x005b;
                case 120: goto L_0x0050;
                case 128: goto L_0x0045;
                case 136: goto L_0x003a;
                case 144: goto L_0x002f;
                case 152: goto L_0x0024;
                case 160: goto L_0x0019;
                case 168: goto L_0x000e;
                default: goto L_0x0007;
            }
        L_0x0007:
            boolean r0 = super.zza(r4, r0)
            if (r0 != 0) goto L_0x0000
            return r3
        L_0x000e:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzgh = r0
            goto L_0x0000
        L_0x0019:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzgg = r0
            goto L_0x0000
        L_0x0024:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzgf = r0
            goto L_0x0000
        L_0x002f:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzge = r0
            goto L_0x0000
        L_0x003a:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzgd = r0
            goto L_0x0000
        L_0x0045:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzgc = r0
            goto L_0x0000
        L_0x0050:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzgb = r0
            goto L_0x0000
        L_0x005b:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzga = r0
            goto L_0x0000
        L_0x0066:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfz = r0
            goto L_0x0000
        L_0x0071:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfy = r0
            goto L_0x0000
        L_0x007c:
            int r1 = r4.getPosition()
            int r2 = r4.zzacc()     // Catch:{ IllegalArgumentException -> 0x00c8 }
            int r2 = com.google.android.gms.internal.ads.zzaz.zzd(r2)     // Catch:{ IllegalArgumentException -> 0x00c8 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x00c8 }
            r3.zzfx = r2     // Catch:{ IllegalArgumentException -> 0x00c8 }
            goto L_0x0000
        L_0x0090:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfw = r0
            goto L_0x0000
        L_0x009c:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfv = r0
            goto L_0x0000
        L_0x00a8:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfu = r0
            goto L_0x0000
        L_0x00b4:
            int r1 = r4.getPosition()
            int r2 = r4.zzacc()     // Catch:{ IllegalArgumentException -> 0x00c8 }
            int r2 = com.google.android.gms.internal.ads.zzaz.zzd(r2)     // Catch:{ IllegalArgumentException -> 0x00c8 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x00c8 }
            r3.zzft = r2     // Catch:{ IllegalArgumentException -> 0x00c8 }
            goto L_0x0000
        L_0x00c8:
            r4.zzdc(r1)
            r3.zza(r4, r0)
            goto L_0x0000
        L_0x00d0:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfs = r0
            goto L_0x0000
        L_0x00dc:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfr = r0
            goto L_0x0000
        L_0x00e8:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfq = r0
            goto L_0x0000
        L_0x00f4:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzfp = r0
            goto L_0x0000
        L_0x0100:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzdp = r0
            goto L_0x0000
        L_0x010c:
            long r0 = r4.zzacd()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.zzdo = r0
            goto L_0x0000
        L_0x0118:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbb.zza(com.google.android.gms.internal.ads.zzbez):com.google.android.gms.internal.ads.zzbb");
    }

    public static zzbb[] zzs() {
        if (zzfo == null) {
            synchronized (zzbfg.zzebs) {
                if (zzfo == null) {
                    zzfo = new zzbb[0];
                }
            }
        }
        return zzfo;
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Long l = this.zzdo;
        if (l != null) {
            zzbfa.zzi(1, l.longValue());
        }
        Long l2 = this.zzdp;
        if (l2 != null) {
            zzbfa.zzi(2, l2.longValue());
        }
        Long l3 = this.zzfp;
        if (l3 != null) {
            zzbfa.zzi(3, l3.longValue());
        }
        Long l4 = this.zzfq;
        if (l4 != null) {
            zzbfa.zzi(4, l4.longValue());
        }
        Long l5 = this.zzfr;
        if (l5 != null) {
            zzbfa.zzi(5, l5.longValue());
        }
        Long l6 = this.zzfs;
        if (l6 != null) {
            zzbfa.zzi(6, l6.longValue());
        }
        Integer num = this.zzft;
        if (num != null) {
            zzbfa.zzm(7, num.intValue());
        }
        Long l7 = this.zzfu;
        if (l7 != null) {
            zzbfa.zzi(8, l7.longValue());
        }
        Long l8 = this.zzfv;
        if (l8 != null) {
            zzbfa.zzi(9, l8.longValue());
        }
        Long l9 = this.zzfw;
        if (l9 != null) {
            zzbfa.zzi(10, l9.longValue());
        }
        Integer num2 = this.zzfx;
        if (num2 != null) {
            zzbfa.zzm(11, num2.intValue());
        }
        Long l10 = this.zzfy;
        if (l10 != null) {
            zzbfa.zzi(12, l10.longValue());
        }
        Long l11 = this.zzfz;
        if (l11 != null) {
            zzbfa.zzi(13, l11.longValue());
        }
        Long l12 = this.zzga;
        if (l12 != null) {
            zzbfa.zzi(14, l12.longValue());
        }
        Long l13 = this.zzgb;
        if (l13 != null) {
            zzbfa.zzi(15, l13.longValue());
        }
        Long l14 = this.zzgc;
        if (l14 != null) {
            zzbfa.zzi(16, l14.longValue());
        }
        Long l15 = this.zzgd;
        if (l15 != null) {
            zzbfa.zzi(17, l15.longValue());
        }
        Long l16 = this.zzge;
        if (l16 != null) {
            zzbfa.zzi(18, l16.longValue());
        }
        Long l17 = this.zzgf;
        if (l17 != null) {
            zzbfa.zzi(19, l17.longValue());
        }
        Long l18 = this.zzgg;
        if (l18 != null) {
            zzbfa.zzi(20, l18.longValue());
        }
        Long l19 = this.zzgh;
        if (l19 != null) {
            zzbfa.zzi(21, l19.longValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Long l = this.zzdo;
        if (l != null) {
            zzr += zzbfa.zzd(1, l.longValue());
        }
        Long l2 = this.zzdp;
        if (l2 != null) {
            zzr += zzbfa.zzd(2, l2.longValue());
        }
        Long l3 = this.zzfp;
        if (l3 != null) {
            zzr += zzbfa.zzd(3, l3.longValue());
        }
        Long l4 = this.zzfq;
        if (l4 != null) {
            zzr += zzbfa.zzd(4, l4.longValue());
        }
        Long l5 = this.zzfr;
        if (l5 != null) {
            zzr += zzbfa.zzd(5, l5.longValue());
        }
        Long l6 = this.zzfs;
        if (l6 != null) {
            zzr += zzbfa.zzd(6, l6.longValue());
        }
        Integer num = this.zzft;
        if (num != null) {
            zzr += zzbfa.zzq(7, num.intValue());
        }
        Long l7 = this.zzfu;
        if (l7 != null) {
            zzr += zzbfa.zzd(8, l7.longValue());
        }
        Long l8 = this.zzfv;
        if (l8 != null) {
            zzr += zzbfa.zzd(9, l8.longValue());
        }
        Long l9 = this.zzfw;
        if (l9 != null) {
            zzr += zzbfa.zzd(10, l9.longValue());
        }
        Integer num2 = this.zzfx;
        if (num2 != null) {
            zzr += zzbfa.zzq(11, num2.intValue());
        }
        Long l10 = this.zzfy;
        if (l10 != null) {
            zzr += zzbfa.zzd(12, l10.longValue());
        }
        Long l11 = this.zzfz;
        if (l11 != null) {
            zzr += zzbfa.zzd(13, l11.longValue());
        }
        Long l12 = this.zzga;
        if (l12 != null) {
            zzr += zzbfa.zzd(14, l12.longValue());
        }
        Long l13 = this.zzgb;
        if (l13 != null) {
            zzr += zzbfa.zzd(15, l13.longValue());
        }
        Long l14 = this.zzgc;
        if (l14 != null) {
            zzr += zzbfa.zzd(16, l14.longValue());
        }
        Long l15 = this.zzgd;
        if (l15 != null) {
            zzr += zzbfa.zzd(17, l15.longValue());
        }
        Long l16 = this.zzge;
        if (l16 != null) {
            zzr += zzbfa.zzd(18, l16.longValue());
        }
        Long l17 = this.zzgf;
        if (l17 != null) {
            zzr += zzbfa.zzd(19, l17.longValue());
        }
        Long l18 = this.zzgg;
        if (l18 != null) {
            zzr += zzbfa.zzd(20, l18.longValue());
        }
        Long l19 = this.zzgh;
        return l19 != null ? zzr + zzbfa.zzd(21, l19.longValue()) : zzr;
    }
}
