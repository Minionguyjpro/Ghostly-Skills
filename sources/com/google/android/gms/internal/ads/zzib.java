package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzib extends zzbfc<zzib> {
    public Integer zzalt = null;
    private Integer zzalu = null;
    private zzid zzalv = null;
    public zzie zzalw = null;
    private zzic[] zzalx = zzic.zzhr();
    private zzif zzaly = null;
    private zzio zzalz = null;
    private zzin zzama = null;
    private zzik zzamb = null;
    private zzil zzamc = null;
    private zziu[] zzamd = zziu.zzhu();

    public zzib() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: zze */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzib zza(com.google.android.gms.internal.ads.zzbez r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.zzabk()
            r1 = 0
            switch(r0) {
                case 0: goto L_0x013c;
                case 56: goto L_0x0105;
                case 64: goto L_0x00f1;
                case 74: goto L_0x00df;
                case 82: goto L_0x00d1;
                case 90: goto L_0x0094;
                case 98: goto L_0x0086;
                case 106: goto L_0x0078;
                case 114: goto L_0x0069;
                case 122: goto L_0x005a;
                case 130: goto L_0x004b;
                case 138: goto L_0x000f;
                default: goto L_0x0008;
            }
        L_0x0008:
            boolean r0 = super.zza(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x000f:
            r0 = 138(0x8a, float:1.93E-43)
            int r0 = com.google.android.gms.internal.ads.zzbfl.zzb(r7, r0)
            com.google.android.gms.internal.ads.zziu[] r2 = r6.zzamd
            if (r2 != 0) goto L_0x001b
            r2 = 0
            goto L_0x001c
        L_0x001b:
            int r2 = r2.length
        L_0x001c:
            int r0 = r0 + r2
            com.google.android.gms.internal.ads.zziu[] r3 = new com.google.android.gms.internal.ads.zziu[r0]
            if (r2 == 0) goto L_0x0026
            com.google.android.gms.internal.ads.zziu[] r4 = r6.zzamd
            java.lang.System.arraycopy(r4, r1, r3, r1, r2)
        L_0x0026:
            int r1 = r0 + -1
            if (r2 >= r1) goto L_0x003c
            com.google.android.gms.internal.ads.zziu r1 = new com.google.android.gms.internal.ads.zziu
            r1.<init>()
            r3[r2] = r1
            r1 = r3[r2]
            r7.zza(r1)
            r7.zzabk()
            int r2 = r2 + 1
            goto L_0x0026
        L_0x003c:
            com.google.android.gms.internal.ads.zziu r0 = new com.google.android.gms.internal.ads.zziu
            r0.<init>()
            r3[r2] = r0
            r0 = r3[r2]
            r7.zza(r0)
            r6.zzamd = r3
            goto L_0x0000
        L_0x004b:
            com.google.android.gms.internal.ads.zzil r0 = r6.zzamc
            if (r0 != 0) goto L_0x0056
            com.google.android.gms.internal.ads.zzil r0 = new com.google.android.gms.internal.ads.zzil
            r0.<init>()
            r6.zzamc = r0
        L_0x0056:
            com.google.android.gms.internal.ads.zzil r0 = r6.zzamc
            goto L_0x00ec
        L_0x005a:
            com.google.android.gms.internal.ads.zzik r0 = r6.zzamb
            if (r0 != 0) goto L_0x0065
            com.google.android.gms.internal.ads.zzik r0 = new com.google.android.gms.internal.ads.zzik
            r0.<init>()
            r6.zzamb = r0
        L_0x0065:
            com.google.android.gms.internal.ads.zzik r0 = r6.zzamb
            goto L_0x00ec
        L_0x0069:
            com.google.android.gms.internal.ads.zzin r0 = r6.zzama
            if (r0 != 0) goto L_0x0074
            com.google.android.gms.internal.ads.zzin r0 = new com.google.android.gms.internal.ads.zzin
            r0.<init>()
            r6.zzama = r0
        L_0x0074:
            com.google.android.gms.internal.ads.zzin r0 = r6.zzama
            goto L_0x00ec
        L_0x0078:
            com.google.android.gms.internal.ads.zzio r0 = r6.zzalz
            if (r0 != 0) goto L_0x0083
            com.google.android.gms.internal.ads.zzio r0 = new com.google.android.gms.internal.ads.zzio
            r0.<init>()
            r6.zzalz = r0
        L_0x0083:
            com.google.android.gms.internal.ads.zzio r0 = r6.zzalz
            goto L_0x00ec
        L_0x0086:
            com.google.android.gms.internal.ads.zzif r0 = r6.zzaly
            if (r0 != 0) goto L_0x0091
            com.google.android.gms.internal.ads.zzif r0 = new com.google.android.gms.internal.ads.zzif
            r0.<init>()
            r6.zzaly = r0
        L_0x0091:
            com.google.android.gms.internal.ads.zzif r0 = r6.zzaly
            goto L_0x00ec
        L_0x0094:
            r0 = 90
            int r0 = com.google.android.gms.internal.ads.zzbfl.zzb(r7, r0)
            com.google.android.gms.internal.ads.zzic[] r2 = r6.zzalx
            if (r2 != 0) goto L_0x00a0
            r2 = 0
            goto L_0x00a1
        L_0x00a0:
            int r2 = r2.length
        L_0x00a1:
            int r0 = r0 + r2
            com.google.android.gms.internal.ads.zzic[] r3 = new com.google.android.gms.internal.ads.zzic[r0]
            if (r2 == 0) goto L_0x00ab
            com.google.android.gms.internal.ads.zzic[] r4 = r6.zzalx
            java.lang.System.arraycopy(r4, r1, r3, r1, r2)
        L_0x00ab:
            int r1 = r0 + -1
            if (r2 >= r1) goto L_0x00c1
            com.google.android.gms.internal.ads.zzic r1 = new com.google.android.gms.internal.ads.zzic
            r1.<init>()
            r3[r2] = r1
            r1 = r3[r2]
            r7.zza(r1)
            r7.zzabk()
            int r2 = r2 + 1
            goto L_0x00ab
        L_0x00c1:
            com.google.android.gms.internal.ads.zzic r0 = new com.google.android.gms.internal.ads.zzic
            r0.<init>()
            r3[r2] = r0
            r0 = r3[r2]
            r7.zza(r0)
            r6.zzalx = r3
            goto L_0x0000
        L_0x00d1:
            com.google.android.gms.internal.ads.zzie r0 = r6.zzalw
            if (r0 != 0) goto L_0x00dc
            com.google.android.gms.internal.ads.zzie r0 = new com.google.android.gms.internal.ads.zzie
            r0.<init>()
            r6.zzalw = r0
        L_0x00dc:
            com.google.android.gms.internal.ads.zzie r0 = r6.zzalw
            goto L_0x00ec
        L_0x00df:
            com.google.android.gms.internal.ads.zzid r0 = r6.zzalv
            if (r0 != 0) goto L_0x00ea
            com.google.android.gms.internal.ads.zzid r0 = new com.google.android.gms.internal.ads.zzid
            r0.<init>()
            r6.zzalv = r0
        L_0x00ea:
            com.google.android.gms.internal.ads.zzid r0 = r6.zzalv
        L_0x00ec:
            r7.zza(r0)
            goto L_0x0000
        L_0x00f1:
            int r1 = r7.getPosition()
            int r2 = r7.zzacc()     // Catch:{ IllegalArgumentException -> 0x0134 }
            int r2 = com.google.android.gms.internal.ads.zzia.zzd(r2)     // Catch:{ IllegalArgumentException -> 0x0134 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x0134 }
            r6.zzalu = r2     // Catch:{ IllegalArgumentException -> 0x0134 }
            goto L_0x0000
        L_0x0105:
            int r1 = r7.getPosition()
            int r2 = r7.zzacc()     // Catch:{ IllegalArgumentException -> 0x0134 }
            if (r2 < 0) goto L_0x011b
            r3 = 9
            if (r2 > r3) goto L_0x011b
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x0134 }
            r6.zzalt = r2     // Catch:{ IllegalArgumentException -> 0x0134 }
            goto L_0x0000
        L_0x011b:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0134 }
            r4 = 43
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0134 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x0134 }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x0134 }
            java.lang.String r2 = " is not a valid enum AdInitiater"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x0134 }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x0134 }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x0134 }
            throw r3     // Catch:{ IllegalArgumentException -> 0x0134 }
        L_0x0134:
            r7.zzdc(r1)
            r6.zza(r7, r0)
            goto L_0x0000
        L_0x013c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzib.zza(com.google.android.gms.internal.ads.zzbez):com.google.android.gms.internal.ads.zzib");
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzalt;
        if (num != null) {
            zzbfa.zzm(7, num.intValue());
        }
        Integer num2 = this.zzalu;
        if (num2 != null) {
            zzbfa.zzm(8, num2.intValue());
        }
        zzid zzid = this.zzalv;
        if (zzid != null) {
            zzbfa.zza(9, (zzbfi) zzid);
        }
        zzie zzie = this.zzalw;
        if (zzie != null) {
            zzbfa.zza(10, (zzbfi) zzie);
        }
        zzic[] zzicArr = this.zzalx;
        int i = 0;
        if (zzicArr != null && zzicArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzic[] zzicArr2 = this.zzalx;
                if (i2 >= zzicArr2.length) {
                    break;
                }
                zzic zzic = zzicArr2[i2];
                if (zzic != null) {
                    zzbfa.zza(11, (zzbfi) zzic);
                }
                i2++;
            }
        }
        zzif zzif = this.zzaly;
        if (zzif != null) {
            zzbfa.zza(12, (zzbfi) zzif);
        }
        zzio zzio = this.zzalz;
        if (zzio != null) {
            zzbfa.zza(13, (zzbfi) zzio);
        }
        zzin zzin = this.zzama;
        if (zzin != null) {
            zzbfa.zza(14, (zzbfi) zzin);
        }
        zzik zzik = this.zzamb;
        if (zzik != null) {
            zzbfa.zza(15, (zzbfi) zzik);
        }
        zzil zzil = this.zzamc;
        if (zzil != null) {
            zzbfa.zza(16, (zzbfi) zzil);
        }
        zziu[] zziuArr = this.zzamd;
        if (zziuArr != null && zziuArr.length > 0) {
            while (true) {
                zziu[] zziuArr2 = this.zzamd;
                if (i >= zziuArr2.length) {
                    break;
                }
                zziu zziu = zziuArr2[i];
                if (zziu != null) {
                    zzbfa.zza(17, (zzbfi) zziu);
                }
                i++;
            }
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzalt;
        if (num != null) {
            zzr += zzbfa.zzq(7, num.intValue());
        }
        Integer num2 = this.zzalu;
        if (num2 != null) {
            zzr += zzbfa.zzq(8, num2.intValue());
        }
        zzid zzid = this.zzalv;
        if (zzid != null) {
            zzr += zzbfa.zzb(9, (zzbfi) zzid);
        }
        zzie zzie = this.zzalw;
        if (zzie != null) {
            zzr += zzbfa.zzb(10, (zzbfi) zzie);
        }
        zzic[] zzicArr = this.zzalx;
        int i = 0;
        if (zzicArr != null && zzicArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzic[] zzicArr2 = this.zzalx;
                if (i2 >= zzicArr2.length) {
                    break;
                }
                zzic zzic = zzicArr2[i2];
                if (zzic != null) {
                    zzr += zzbfa.zzb(11, (zzbfi) zzic);
                }
                i2++;
            }
        }
        zzif zzif = this.zzaly;
        if (zzif != null) {
            zzr += zzbfa.zzb(12, (zzbfi) zzif);
        }
        zzio zzio = this.zzalz;
        if (zzio != null) {
            zzr += zzbfa.zzb(13, (zzbfi) zzio);
        }
        zzin zzin = this.zzama;
        if (zzin != null) {
            zzr += zzbfa.zzb(14, (zzbfi) zzin);
        }
        zzik zzik = this.zzamb;
        if (zzik != null) {
            zzr += zzbfa.zzb(15, (zzbfi) zzik);
        }
        zzil zzil = this.zzamc;
        if (zzil != null) {
            zzr += zzbfa.zzb(16, (zzbfi) zzil);
        }
        zziu[] zziuArr = this.zzamd;
        if (zziuArr != null && zziuArr.length > 0) {
            while (true) {
                zziu[] zziuArr2 = this.zzamd;
                if (i >= zziuArr2.length) {
                    break;
                }
                zziu zziu = zziuArr2[i];
                if (zziu != null) {
                    zzr += zzbfa.zzb(17, (zzbfi) zziu);
                }
                i++;
            }
        }
        return zzr;
    }
}
