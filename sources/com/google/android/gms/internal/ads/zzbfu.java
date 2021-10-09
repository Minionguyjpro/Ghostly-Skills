package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfu extends zzbfc<zzbfu> {
    private static volatile zzbfu[] zzedm;
    public String url = null;
    public Integer zzedn = null;
    public zzbfp zzedo = null;
    private zzbfr zzedp = null;
    private Integer zzedq = null;
    private int[] zzedr = zzbfl.zzeby;
    private String zzeds = null;
    public Integer zzedt = null;
    public String[] zzedu = zzbfl.zzecd;

    public zzbfu() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzac */
    public final zzbfu zza(zzbez zzbez) throws IOException {
        zzbfi zzbfi;
        int zzabn;
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    return this;
                case 8:
                    this.zzedn = Integer.valueOf(zzbez.zzabn());
                    continue;
                case 18:
                    this.url = zzbez.readString();
                    continue;
                case 26:
                    if (this.zzedo == null) {
                        this.zzedo = new zzbfp();
                    }
                    zzbfi = this.zzedo;
                    break;
                case 34:
                    if (this.zzedp == null) {
                        this.zzedp = new zzbfr();
                    }
                    zzbfi = this.zzedp;
                    break;
                case 40:
                    this.zzedq = Integer.valueOf(zzbez.zzabn());
                    continue;
                case 48:
                    int zzb = zzbfl.zzb(zzbez, 48);
                    int[] iArr = this.zzedr;
                    int length = iArr == null ? 0 : iArr.length;
                    int i = zzb + length;
                    int[] iArr2 = new int[i];
                    if (length != 0) {
                        System.arraycopy(this.zzedr, 0, iArr2, 0, length);
                    }
                    while (length < i - 1) {
                        iArr2[length] = zzbez.zzabn();
                        zzbez.zzabk();
                        length++;
                    }
                    iArr2[length] = zzbez.zzabn();
                    this.zzedr = iArr2;
                    continue;
                case 50:
                    int zzbr = zzbez.zzbr(zzbez.zzacc());
                    int position = zzbez.getPosition();
                    int i2 = 0;
                    while (zzbez.zzagn() > 0) {
                        zzbez.zzabn();
                        i2++;
                    }
                    zzbez.zzdc(position);
                    int[] iArr3 = this.zzedr;
                    int length2 = iArr3 == null ? 0 : iArr3.length;
                    int i3 = i2 + length2;
                    int[] iArr4 = new int[i3];
                    if (length2 != 0) {
                        System.arraycopy(this.zzedr, 0, iArr4, 0, length2);
                    }
                    while (length2 < i3) {
                        iArr4[length2] = zzbez.zzabn();
                        length2++;
                    }
                    this.zzedr = iArr4;
                    zzbez.zzbs(zzbr);
                    continue;
                case 58:
                    this.zzeds = zzbez.readString();
                    continue;
                case 64:
                    try {
                        zzabn = zzbez.zzabn();
                        if (zzabn < 0 || zzabn > 3) {
                            StringBuilder sb = new StringBuilder(46);
                            sb.append(zzabn);
                            sb.append(" is not a valid enum AdResourceType");
                            break;
                        } else {
                            this.zzedt = Integer.valueOf(zzabn);
                            continue;
                        }
                    } catch (IllegalArgumentException unused) {
                        zzbez.zzdc(zzbez.getPosition());
                        zza(zzbez, zzabk);
                        break;
                    }
                case 74:
                    int zzb2 = zzbfl.zzb(zzbez, 74);
                    String[] strArr = this.zzedu;
                    int length3 = strArr == null ? 0 : strArr.length;
                    int i4 = zzb2 + length3;
                    String[] strArr2 = new String[i4];
                    if (length3 != 0) {
                        System.arraycopy(this.zzedu, 0, strArr2, 0, length3);
                    }
                    while (length3 < i4 - 1) {
                        strArr2[length3] = zzbez.readString();
                        zzbez.zzabk();
                        length3++;
                    }
                    strArr2[length3] = zzbez.readString();
                    this.zzedu = strArr2;
                    continue;
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        return this;
                    }
                    continue;
            }
            zzbez.zza(zzbfi);
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append(zzabn);
        sb2.append(" is not a valid enum AdResourceType");
        throw new IllegalArgumentException(sb2.toString());
    }

    public static zzbfu[] zzagu() {
        if (zzedm == null) {
            synchronized (zzbfg.zzebs) {
                if (zzedm == null) {
                    zzedm = new zzbfu[0];
                }
            }
        }
        return zzedm;
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        zzbfa.zzm(1, this.zzedn.intValue());
        String str = this.url;
        if (str != null) {
            zzbfa.zzf(2, str);
        }
        zzbfp zzbfp = this.zzedo;
        if (zzbfp != null) {
            zzbfa.zza(3, (zzbfi) zzbfp);
        }
        zzbfr zzbfr = this.zzedp;
        if (zzbfr != null) {
            zzbfa.zza(4, (zzbfi) zzbfr);
        }
        Integer num = this.zzedq;
        if (num != null) {
            zzbfa.zzm(5, num.intValue());
        }
        int[] iArr = this.zzedr;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.zzedr;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzbfa.zzm(6, iArr2[i2]);
                i2++;
            }
        }
        String str2 = this.zzeds;
        if (str2 != null) {
            zzbfa.zzf(7, str2);
        }
        Integer num2 = this.zzedt;
        if (num2 != null) {
            zzbfa.zzm(8, num2.intValue());
        }
        String[] strArr = this.zzedu;
        if (strArr != null && strArr.length > 0) {
            while (true) {
                String[] strArr2 = this.zzedu;
                if (i >= strArr2.length) {
                    break;
                }
                String str3 = strArr2[i];
                if (str3 != null) {
                    zzbfa.zzf(9, str3);
                }
                i++;
            }
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int[] iArr;
        int zzr = super.zzr() + zzbfa.zzq(1, this.zzedn.intValue());
        String str = this.url;
        if (str != null) {
            zzr += zzbfa.zzg(2, str);
        }
        zzbfp zzbfp = this.zzedo;
        if (zzbfp != null) {
            zzr += zzbfa.zzb(3, (zzbfi) zzbfp);
        }
        zzbfr zzbfr = this.zzedp;
        if (zzbfr != null) {
            zzr += zzbfa.zzb(4, (zzbfi) zzbfr);
        }
        Integer num = this.zzedq;
        if (num != null) {
            zzr += zzbfa.zzq(5, num.intValue());
        }
        int[] iArr2 = this.zzedr;
        int i = 0;
        if (iArr2 != null && iArr2.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                iArr = this.zzedr;
                if (i2 >= iArr.length) {
                    break;
                }
                i3 += zzbfa.zzce(iArr[i2]);
                i2++;
            }
            zzr = zzr + i3 + (iArr.length * 1);
        }
        String str2 = this.zzeds;
        if (str2 != null) {
            zzr += zzbfa.zzg(7, str2);
        }
        Integer num2 = this.zzedt;
        if (num2 != null) {
            zzr += zzbfa.zzq(8, num2.intValue());
        }
        String[] strArr = this.zzedu;
        if (strArr == null || strArr.length <= 0) {
            return zzr;
        }
        int i4 = 0;
        int i5 = 0;
        while (true) {
            String[] strArr2 = this.zzedu;
            if (i >= strArr2.length) {
                return zzr + i4 + (i5 * 1);
            }
            String str3 = strArr2[i];
            if (str3 != null) {
                i5++;
                i4 += zzbfa.zzeo(str3);
            }
            i++;
        }
    }
}
