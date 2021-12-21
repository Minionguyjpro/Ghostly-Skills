package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzie extends zzbfc<zzie> {
    private String zzamj = null;
    private zzic[] zzamk = zzic.zzhr();
    private Integer zzaml = null;
    private Integer zzamm = null;
    private Integer zzamn = null;

    public zzie() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzh */
    public final zzie zza(zzbez zzbez) throws IOException {
        int i;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                this.zzamj = zzbez.readString();
            } else if (zzabk == 18) {
                int zzb = zzbfl.zzb(zzbez, 18);
                zzic[] zzicArr = this.zzamk;
                int length = zzicArr == null ? 0 : zzicArr.length;
                int i2 = zzb + length;
                zzic[] zzicArr2 = new zzic[i2];
                if (length != 0) {
                    System.arraycopy(this.zzamk, 0, zzicArr2, 0, length);
                }
                while (length < i2 - 1) {
                    zzicArr2[length] = new zzic();
                    zzbez.zza(zzicArr2[length]);
                    zzbez.zzabk();
                    length++;
                }
                zzicArr2[length] = new zzic();
                zzbez.zza(zzicArr2[length]);
                this.zzamk = zzicArr2;
            } else if (zzabk == 24) {
                i = zzbez.getPosition();
                this.zzaml = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
            } else if (zzabk == 32) {
                i = zzbez.getPosition();
                this.zzamm = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
            } else if (zzabk == 40) {
                i = zzbez.getPosition();
                try {
                    this.zzamn = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(i);
                    zza(zzbez, zzabk);
                }
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.zzamj;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        zzic[] zzicArr = this.zzamk;
        if (zzicArr != null && zzicArr.length > 0) {
            int i = 0;
            while (true) {
                zzic[] zzicArr2 = this.zzamk;
                if (i >= zzicArr2.length) {
                    break;
                }
                zzic zzic = zzicArr2[i];
                if (zzic != null) {
                    zzbfa.zza(2, (zzbfi) zzic);
                }
                i++;
            }
        }
        Integer num = this.zzaml;
        if (num != null) {
            zzbfa.zzm(3, num.intValue());
        }
        Integer num2 = this.zzamm;
        if (num2 != null) {
            zzbfa.zzm(4, num2.intValue());
        }
        Integer num3 = this.zzamn;
        if (num3 != null) {
            zzbfa.zzm(5, num3.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        String str = this.zzamj;
        if (str != null) {
            zzr += zzbfa.zzg(1, str);
        }
        zzic[] zzicArr = this.zzamk;
        if (zzicArr != null && zzicArr.length > 0) {
            int i = 0;
            while (true) {
                zzic[] zzicArr2 = this.zzamk;
                if (i >= zzicArr2.length) {
                    break;
                }
                zzic zzic = zzicArr2[i];
                if (zzic != null) {
                    zzr += zzbfa.zzb(2, (zzbfi) zzic);
                }
                i++;
            }
        }
        Integer num = this.zzaml;
        if (num != null) {
            zzr += zzbfa.zzq(3, num.intValue());
        }
        Integer num2 = this.zzamm;
        if (num2 != null) {
            zzr += zzbfa.zzq(4, num2.intValue());
        }
        Integer num3 = this.zzamn;
        return num3 != null ? zzr + zzbfa.zzq(5, num3.intValue()) : zzr;
    }
}
