package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfm extends zzbfc<zzbfm> {
    public String url = null;
    public Integer zzamf = null;
    private Integer zzecg = null;
    public String zzech = null;
    private String zzeci = null;
    public zzbfn zzecj = null;
    public zzbfu[] zzeck = zzbfu.zzagu();
    public String zzecl = null;
    public zzbft zzecm = null;
    private Boolean zzecn = null;
    private String[] zzeco = zzbfl.zzecd;
    private String zzecp = null;
    private Boolean zzecq = null;
    private Boolean zzecr = null;
    private byte[] zzecs = null;
    public zzbfv zzect = null;
    public String[] zzecu = zzbfl.zzecd;
    public String[] zzecv = zzbfl.zzecd;

    public zzbfm() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00da, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* renamed from: zzaa */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzbfm zza(com.google.android.gms.internal.ads.zzbez r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.zzabk()
            r1 = 0
            switch(r0) {
                case 0: goto L_0x01c4;
                case 10: goto L_0x01bc;
                case 18: goto L_0x01b4;
                case 26: goto L_0x01ac;
                case 34: goto L_0x016f;
                case 40: goto L_0x0163;
                case 50: goto L_0x0132;
                case 58: goto L_0x012a;
                case 64: goto L_0x011e;
                case 72: goto L_0x0112;
                case 80: goto L_0x00db;
                case 88: goto L_0x00ad;
                case 98: goto L_0x009b;
                case 106: goto L_0x0093;
                case 114: goto L_0x0085;
                case 122: goto L_0x007d;
                case 138: goto L_0x006f;
                case 162: goto L_0x003f;
                case 170: goto L_0x000f;
                default: goto L_0x0008;
            }
        L_0x0008:
            boolean r0 = super.zza(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x000f:
            r0 = 170(0xaa, float:2.38E-43)
            int r0 = com.google.android.gms.internal.ads.zzbfl.zzb(r7, r0)
            java.lang.String[] r2 = r6.zzecv
            if (r2 != 0) goto L_0x001b
            r2 = 0
            goto L_0x001c
        L_0x001b:
            int r2 = r2.length
        L_0x001c:
            int r0 = r0 + r2
            java.lang.String[] r3 = new java.lang.String[r0]
            if (r2 == 0) goto L_0x0026
            java.lang.String[] r4 = r6.zzecv
            java.lang.System.arraycopy(r4, r1, r3, r1, r2)
        L_0x0026:
            int r1 = r0 + -1
            if (r2 >= r1) goto L_0x0036
            java.lang.String r1 = r7.readString()
            r3[r2] = r1
            r7.zzabk()
            int r2 = r2 + 1
            goto L_0x0026
        L_0x0036:
            java.lang.String r0 = r7.readString()
            r3[r2] = r0
            r6.zzecv = r3
            goto L_0x0000
        L_0x003f:
            r0 = 162(0xa2, float:2.27E-43)
            int r0 = com.google.android.gms.internal.ads.zzbfl.zzb(r7, r0)
            java.lang.String[] r2 = r6.zzecu
            if (r2 != 0) goto L_0x004b
            r2 = 0
            goto L_0x004c
        L_0x004b:
            int r2 = r2.length
        L_0x004c:
            int r0 = r0 + r2
            java.lang.String[] r3 = new java.lang.String[r0]
            if (r2 == 0) goto L_0x0056
            java.lang.String[] r4 = r6.zzecu
            java.lang.System.arraycopy(r4, r1, r3, r1, r2)
        L_0x0056:
            int r1 = r0 + -1
            if (r2 >= r1) goto L_0x0066
            java.lang.String r1 = r7.readString()
            r3[r2] = r1
            r7.zzabk()
            int r2 = r2 + 1
            goto L_0x0056
        L_0x0066:
            java.lang.String r0 = r7.readString()
            r3[r2] = r0
            r6.zzecu = r3
            goto L_0x0000
        L_0x006f:
            com.google.android.gms.internal.ads.zzbfv r0 = r6.zzect
            if (r0 != 0) goto L_0x007a
            com.google.android.gms.internal.ads.zzbfv r0 = new com.google.android.gms.internal.ads.zzbfv
            r0.<init>()
            r6.zzect = r0
        L_0x007a:
            com.google.android.gms.internal.ads.zzbfv r0 = r6.zzect
            goto L_0x00a8
        L_0x007d:
            byte[] r0 = r7.readBytes()
            r6.zzecs = r0
            goto L_0x0000
        L_0x0085:
            com.google.android.gms.internal.ads.zzbft r0 = r6.zzecm
            if (r0 != 0) goto L_0x0090
            com.google.android.gms.internal.ads.zzbft r0 = new com.google.android.gms.internal.ads.zzbft
            r0.<init>()
            r6.zzecm = r0
        L_0x0090:
            com.google.android.gms.internal.ads.zzbft r0 = r6.zzecm
            goto L_0x00a8
        L_0x0093:
            java.lang.String r0 = r7.readString()
            r6.zzecl = r0
            goto L_0x0000
        L_0x009b:
            com.google.android.gms.internal.ads.zzbfn r0 = r6.zzecj
            if (r0 != 0) goto L_0x00a6
            com.google.android.gms.internal.ads.zzbfn r0 = new com.google.android.gms.internal.ads.zzbfn
            r0.<init>()
            r6.zzecj = r0
        L_0x00a6:
            com.google.android.gms.internal.ads.zzbfn r0 = r6.zzecj
        L_0x00a8:
            r7.zza(r0)
            goto L_0x0000
        L_0x00ad:
            int r1 = r7.getPosition()
            int r2 = r7.zzabn()     // Catch:{ IllegalArgumentException -> 0x010a }
            if (r2 < 0) goto L_0x00c2
            r3 = 4
            if (r2 > r3) goto L_0x00c2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x010a }
            r6.zzecg = r2     // Catch:{ IllegalArgumentException -> 0x010a }
            goto L_0x0000
        L_0x00c2:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x010a }
            r4 = 39
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x010a }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x010a }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x010a }
            java.lang.String r2 = " is not a valid enum Verdict"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x010a }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x010a }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x010a }
            throw r3     // Catch:{ IllegalArgumentException -> 0x010a }
        L_0x00db:
            int r1 = r7.getPosition()
            int r2 = r7.zzabn()     // Catch:{ IllegalArgumentException -> 0x010a }
            if (r2 < 0) goto L_0x00f1
            r3 = 9
            if (r2 > r3) goto L_0x00f1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x010a }
            r6.zzamf = r2     // Catch:{ IllegalArgumentException -> 0x010a }
            goto L_0x0000
        L_0x00f1:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x010a }
            r4 = 42
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x010a }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x010a }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x010a }
            java.lang.String r2 = " is not a valid enum ReportType"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x010a }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x010a }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x010a }
            throw r3     // Catch:{ IllegalArgumentException -> 0x010a }
        L_0x010a:
            r7.zzdc(r1)
            r6.zza(r7, r0)
            goto L_0x0000
        L_0x0112:
            boolean r0 = r7.zzabq()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.zzecr = r0
            goto L_0x0000
        L_0x011e:
            boolean r0 = r7.zzabq()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.zzecq = r0
            goto L_0x0000
        L_0x012a:
            java.lang.String r0 = r7.readString()
            r6.zzecp = r0
            goto L_0x0000
        L_0x0132:
            r0 = 50
            int r0 = com.google.android.gms.internal.ads.zzbfl.zzb(r7, r0)
            java.lang.String[] r2 = r6.zzeco
            if (r2 != 0) goto L_0x013e
            r2 = 0
            goto L_0x013f
        L_0x013e:
            int r2 = r2.length
        L_0x013f:
            int r0 = r0 + r2
            java.lang.String[] r3 = new java.lang.String[r0]
            if (r2 == 0) goto L_0x0149
            java.lang.String[] r4 = r6.zzeco
            java.lang.System.arraycopy(r4, r1, r3, r1, r2)
        L_0x0149:
            int r1 = r0 + -1
            if (r2 >= r1) goto L_0x0159
            java.lang.String r1 = r7.readString()
            r3[r2] = r1
            r7.zzabk()
            int r2 = r2 + 1
            goto L_0x0149
        L_0x0159:
            java.lang.String r0 = r7.readString()
            r3[r2] = r0
            r6.zzeco = r3
            goto L_0x0000
        L_0x0163:
            boolean r0 = r7.zzabq()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.zzecn = r0
            goto L_0x0000
        L_0x016f:
            r0 = 34
            int r0 = com.google.android.gms.internal.ads.zzbfl.zzb(r7, r0)
            com.google.android.gms.internal.ads.zzbfu[] r2 = r6.zzeck
            if (r2 != 0) goto L_0x017b
            r2 = 0
            goto L_0x017c
        L_0x017b:
            int r2 = r2.length
        L_0x017c:
            int r0 = r0 + r2
            com.google.android.gms.internal.ads.zzbfu[] r3 = new com.google.android.gms.internal.ads.zzbfu[r0]
            if (r2 == 0) goto L_0x0186
            com.google.android.gms.internal.ads.zzbfu[] r4 = r6.zzeck
            java.lang.System.arraycopy(r4, r1, r3, r1, r2)
        L_0x0186:
            int r1 = r0 + -1
            if (r2 >= r1) goto L_0x019c
            com.google.android.gms.internal.ads.zzbfu r1 = new com.google.android.gms.internal.ads.zzbfu
            r1.<init>()
            r3[r2] = r1
            r1 = r3[r2]
            r7.zza(r1)
            r7.zzabk()
            int r2 = r2 + 1
            goto L_0x0186
        L_0x019c:
            com.google.android.gms.internal.ads.zzbfu r0 = new com.google.android.gms.internal.ads.zzbfu
            r0.<init>()
            r3[r2] = r0
            r0 = r3[r2]
            r7.zza(r0)
            r6.zzeck = r3
            goto L_0x0000
        L_0x01ac:
            java.lang.String r0 = r7.readString()
            r6.zzeci = r0
            goto L_0x0000
        L_0x01b4:
            java.lang.String r0 = r7.readString()
            r6.zzech = r0
            goto L_0x0000
        L_0x01bc:
            java.lang.String r0 = r7.readString()
            r6.url = r0
            goto L_0x0000
        L_0x01c4:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbfm.zza(com.google.android.gms.internal.ads.zzbez):com.google.android.gms.internal.ads.zzbfm");
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.url;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        String str2 = this.zzech;
        if (str2 != null) {
            zzbfa.zzf(2, str2);
        }
        String str3 = this.zzeci;
        if (str3 != null) {
            zzbfa.zzf(3, str3);
        }
        zzbfu[] zzbfuArr = this.zzeck;
        int i = 0;
        if (zzbfuArr != null && zzbfuArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzbfu[] zzbfuArr2 = this.zzeck;
                if (i2 >= zzbfuArr2.length) {
                    break;
                }
                zzbfu zzbfu = zzbfuArr2[i2];
                if (zzbfu != null) {
                    zzbfa.zza(4, (zzbfi) zzbfu);
                }
                i2++;
            }
        }
        Boolean bool = this.zzecn;
        if (bool != null) {
            zzbfa.zzf(5, bool.booleanValue());
        }
        String[] strArr = this.zzeco;
        if (strArr != null && strArr.length > 0) {
            int i3 = 0;
            while (true) {
                String[] strArr2 = this.zzeco;
                if (i3 >= strArr2.length) {
                    break;
                }
                String str4 = strArr2[i3];
                if (str4 != null) {
                    zzbfa.zzf(6, str4);
                }
                i3++;
            }
        }
        String str5 = this.zzecp;
        if (str5 != null) {
            zzbfa.zzf(7, str5);
        }
        Boolean bool2 = this.zzecq;
        if (bool2 != null) {
            zzbfa.zzf(8, bool2.booleanValue());
        }
        Boolean bool3 = this.zzecr;
        if (bool3 != null) {
            zzbfa.zzf(9, bool3.booleanValue());
        }
        Integer num = this.zzamf;
        if (num != null) {
            zzbfa.zzm(10, num.intValue());
        }
        Integer num2 = this.zzecg;
        if (num2 != null) {
            zzbfa.zzm(11, num2.intValue());
        }
        zzbfn zzbfn = this.zzecj;
        if (zzbfn != null) {
            zzbfa.zza(12, (zzbfi) zzbfn);
        }
        String str6 = this.zzecl;
        if (str6 != null) {
            zzbfa.zzf(13, str6);
        }
        zzbft zzbft = this.zzecm;
        if (zzbft != null) {
            zzbfa.zza(14, (zzbfi) zzbft);
        }
        byte[] bArr = this.zzecs;
        if (bArr != null) {
            zzbfa.zza(15, bArr);
        }
        zzbfv zzbfv = this.zzect;
        if (zzbfv != null) {
            zzbfa.zza(17, (zzbfi) zzbfv);
        }
        String[] strArr3 = this.zzecu;
        if (strArr3 != null && strArr3.length > 0) {
            int i4 = 0;
            while (true) {
                String[] strArr4 = this.zzecu;
                if (i4 >= strArr4.length) {
                    break;
                }
                String str7 = strArr4[i4];
                if (str7 != null) {
                    zzbfa.zzf(20, str7);
                }
                i4++;
            }
        }
        String[] strArr5 = this.zzecv;
        if (strArr5 != null && strArr5.length > 0) {
            while (true) {
                String[] strArr6 = this.zzecv;
                if (i >= strArr6.length) {
                    break;
                }
                String str8 = strArr6[i];
                if (str8 != null) {
                    zzbfa.zzf(21, str8);
                }
                i++;
            }
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        String str = this.url;
        if (str != null) {
            zzr += zzbfa.zzg(1, str);
        }
        String str2 = this.zzech;
        if (str2 != null) {
            zzr += zzbfa.zzg(2, str2);
        }
        String str3 = this.zzeci;
        if (str3 != null) {
            zzr += zzbfa.zzg(3, str3);
        }
        zzbfu[] zzbfuArr = this.zzeck;
        int i = 0;
        if (zzbfuArr != null && zzbfuArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzbfu[] zzbfuArr2 = this.zzeck;
                if (i2 >= zzbfuArr2.length) {
                    break;
                }
                zzbfu zzbfu = zzbfuArr2[i2];
                if (zzbfu != null) {
                    zzr += zzbfa.zzb(4, (zzbfi) zzbfu);
                }
                i2++;
            }
        }
        Boolean bool = this.zzecn;
        if (bool != null) {
            bool.booleanValue();
            zzr += zzbfa.zzcd(5) + 1;
        }
        String[] strArr = this.zzeco;
        if (strArr != null && strArr.length > 0) {
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                String[] strArr2 = this.zzeco;
                if (i3 >= strArr2.length) {
                    break;
                }
                String str4 = strArr2[i3];
                if (str4 != null) {
                    i5++;
                    i4 += zzbfa.zzeo(str4);
                }
                i3++;
            }
            zzr = zzr + i4 + (i5 * 1);
        }
        String str5 = this.zzecp;
        if (str5 != null) {
            zzr += zzbfa.zzg(7, str5);
        }
        Boolean bool2 = this.zzecq;
        if (bool2 != null) {
            bool2.booleanValue();
            zzr += zzbfa.zzcd(8) + 1;
        }
        Boolean bool3 = this.zzecr;
        if (bool3 != null) {
            bool3.booleanValue();
            zzr += zzbfa.zzcd(9) + 1;
        }
        Integer num = this.zzamf;
        if (num != null) {
            zzr += zzbfa.zzq(10, num.intValue());
        }
        Integer num2 = this.zzecg;
        if (num2 != null) {
            zzr += zzbfa.zzq(11, num2.intValue());
        }
        zzbfn zzbfn = this.zzecj;
        if (zzbfn != null) {
            zzr += zzbfa.zzb(12, (zzbfi) zzbfn);
        }
        String str6 = this.zzecl;
        if (str6 != null) {
            zzr += zzbfa.zzg(13, str6);
        }
        zzbft zzbft = this.zzecm;
        if (zzbft != null) {
            zzr += zzbfa.zzb(14, (zzbfi) zzbft);
        }
        byte[] bArr = this.zzecs;
        if (bArr != null) {
            zzr += zzbfa.zzb(15, bArr);
        }
        zzbfv zzbfv = this.zzect;
        if (zzbfv != null) {
            zzr += zzbfa.zzb(17, (zzbfi) zzbfv);
        }
        String[] strArr3 = this.zzecu;
        if (strArr3 != null && strArr3.length > 0) {
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            while (true) {
                String[] strArr4 = this.zzecu;
                if (i6 >= strArr4.length) {
                    break;
                }
                String str7 = strArr4[i6];
                if (str7 != null) {
                    i8++;
                    i7 += zzbfa.zzeo(str7);
                }
                i6++;
            }
            zzr = zzr + i7 + (i8 * 2);
        }
        String[] strArr5 = this.zzecv;
        if (strArr5 == null || strArr5.length <= 0) {
            return zzr;
        }
        int i9 = 0;
        int i10 = 0;
        while (true) {
            String[] strArr6 = this.zzecv;
            if (i >= strArr6.length) {
                return zzr + i9 + (i10 * 2);
            }
            String str8 = strArr6[i];
            if (str8 != null) {
                i10++;
                i9 += zzbfa.zzeo(str8);
            }
            i++;
        }
    }
}
