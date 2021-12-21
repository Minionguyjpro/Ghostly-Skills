package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzii extends zzbfc<zzii> {
    private Integer zzang = null;
    public String zzanh = null;
    private Integer zzani = null;
    private Integer zzanj = null;
    private zzit zzank = null;
    public long[] zzanl = zzbfl.zzebz;
    public zzig zzanm = null;
    private zzih zzann = null;
    private zzim zzano = null;
    public zzib zzanp = null;

    public zzii() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzk */
    public final zzii zza(zzbez zzbez) throws IOException {
        zzbfi zzbfi;
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    return this;
                case 72:
                    this.zzang = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 82:
                    this.zzanh = zzbez.readString();
                    continue;
                case 88:
                    this.zzani = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 96:
                    int position = zzbez.getPosition();
                    try {
                        this.zzanj = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException unused) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 106:
                    if (this.zzank == null) {
                        this.zzank = new zzit();
                    }
                    zzbfi = this.zzank;
                    break;
                case 112:
                    int zzb = zzbfl.zzb(zzbez, 112);
                    long[] jArr = this.zzanl;
                    int length = jArr == null ? 0 : jArr.length;
                    int i = zzb + length;
                    long[] jArr2 = new long[i];
                    if (length != 0) {
                        System.arraycopy(this.zzanl, 0, jArr2, 0, length);
                    }
                    while (length < i - 1) {
                        jArr2[length] = zzbez.zzacd();
                        zzbez.zzabk();
                        length++;
                    }
                    jArr2[length] = zzbez.zzacd();
                    this.zzanl = jArr2;
                    continue;
                case 114:
                    int zzbr = zzbez.zzbr(zzbez.zzacc());
                    int position2 = zzbez.getPosition();
                    int i2 = 0;
                    while (zzbez.zzagn() > 0) {
                        zzbez.zzacd();
                        i2++;
                    }
                    zzbez.zzdc(position2);
                    long[] jArr3 = this.zzanl;
                    int length2 = jArr3 == null ? 0 : jArr3.length;
                    int i3 = i2 + length2;
                    long[] jArr4 = new long[i3];
                    if (length2 != 0) {
                        System.arraycopy(this.zzanl, 0, jArr4, 0, length2);
                    }
                    while (length2 < i3) {
                        jArr4[length2] = zzbez.zzacd();
                        length2++;
                    }
                    this.zzanl = jArr4;
                    zzbez.zzbs(zzbr);
                    continue;
                case 122:
                    if (this.zzanm == null) {
                        this.zzanm = new zzig();
                    }
                    zzbfi = this.zzanm;
                    break;
                case 130:
                    if (this.zzann == null) {
                        this.zzann = new zzih();
                    }
                    zzbfi = this.zzann;
                    break;
                case 138:
                    if (this.zzano == null) {
                        this.zzano = new zzim();
                    }
                    zzbfi = this.zzano;
                    break;
                case 146:
                    if (this.zzanp == null) {
                        this.zzanp = new zzib();
                    }
                    zzbfi = this.zzanp;
                    break;
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        return this;
                    }
                    continue;
            }
            zzbez.zza(zzbfi);
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzang;
        if (num != null) {
            zzbfa.zzm(9, num.intValue());
        }
        String str = this.zzanh;
        if (str != null) {
            zzbfa.zzf(10, str);
        }
        Integer num2 = this.zzani;
        int i = 0;
        if (num2 != null) {
            int intValue = num2.intValue();
            zzbfa.zzl(11, 0);
            zzbfa.zzde(intValue);
        }
        Integer num3 = this.zzanj;
        if (num3 != null) {
            zzbfa.zzm(12, num3.intValue());
        }
        zzit zzit = this.zzank;
        if (zzit != null) {
            zzbfa.zza(13, (zzbfi) zzit);
        }
        long[] jArr = this.zzanl;
        if (jArr != null && jArr.length > 0) {
            while (true) {
                long[] jArr2 = this.zzanl;
                if (i >= jArr2.length) {
                    break;
                }
                zzbfa.zza(14, jArr2[i]);
                i++;
            }
        }
        zzig zzig = this.zzanm;
        if (zzig != null) {
            zzbfa.zza(15, (zzbfi) zzig);
        }
        zzih zzih = this.zzann;
        if (zzih != null) {
            zzbfa.zza(16, (zzbfi) zzih);
        }
        zzim zzim = this.zzano;
        if (zzim != null) {
            zzbfa.zza(17, (zzbfi) zzim);
        }
        zzib zzib = this.zzanp;
        if (zzib != null) {
            zzbfa.zza(18, (zzbfi) zzib);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        long[] jArr;
        int zzr = super.zzr();
        Integer num = this.zzang;
        if (num != null) {
            zzr += zzbfa.zzq(9, num.intValue());
        }
        String str = this.zzanh;
        if (str != null) {
            zzr += zzbfa.zzg(10, str);
        }
        Integer num2 = this.zzani;
        if (num2 != null) {
            zzr += zzbfa.zzcd(11) + zzbfa.zzcl(num2.intValue());
        }
        Integer num3 = this.zzanj;
        if (num3 != null) {
            zzr += zzbfa.zzq(12, num3.intValue());
        }
        zzit zzit = this.zzank;
        if (zzit != null) {
            zzr += zzbfa.zzb(13, (zzbfi) zzit);
        }
        long[] jArr2 = this.zzanl;
        if (jArr2 != null && jArr2.length > 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                jArr = this.zzanl;
                if (i >= jArr.length) {
                    break;
                }
                i2 += zzbfa.zzy(jArr[i]);
                i++;
            }
            zzr = zzr + i2 + (jArr.length * 1);
        }
        zzig zzig = this.zzanm;
        if (zzig != null) {
            zzr += zzbfa.zzb(15, (zzbfi) zzig);
        }
        zzih zzih = this.zzann;
        if (zzih != null) {
            zzr += zzbfa.zzb(16, (zzbfi) zzih);
        }
        zzim zzim = this.zzano;
        if (zzim != null) {
            zzr += zzbfa.zzb(17, (zzbfi) zzim);
        }
        zzib zzib = this.zzanp;
        return zzib != null ? zzr + zzbfa.zzb(18, (zzbfi) zzib) : zzr;
    }
}
