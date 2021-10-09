package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbd extends zzbfc<zzbd> {
    private Long zzgl = null;
    private Integer zzgm = null;
    private Boolean zzgn = null;
    private int[] zzgo = zzbfl.zzeby;
    private Long zzgp = null;

    public zzbd() {
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                this.zzgl = Long.valueOf(zzbez.zzacd());
            } else if (zzabk == 16) {
                this.zzgm = Integer.valueOf(zzbez.zzacc());
            } else if (zzabk == 24) {
                this.zzgn = Boolean.valueOf(zzbez.zzabq());
            } else if (zzabk == 32) {
                int zzb = zzbfl.zzb(zzbez, 32);
                int[] iArr = this.zzgo;
                int length = iArr == null ? 0 : iArr.length;
                int i = zzb + length;
                int[] iArr2 = new int[i];
                if (length != 0) {
                    System.arraycopy(this.zzgo, 0, iArr2, 0, length);
                }
                while (length < i - 1) {
                    iArr2[length] = zzbez.zzacc();
                    zzbez.zzabk();
                    length++;
                }
                iArr2[length] = zzbez.zzacc();
                this.zzgo = iArr2;
            } else if (zzabk == 34) {
                int zzbr = zzbez.zzbr(zzbez.zzacc());
                int position = zzbez.getPosition();
                int i2 = 0;
                while (zzbez.zzagn() > 0) {
                    zzbez.zzacc();
                    i2++;
                }
                zzbez.zzdc(position);
                int[] iArr3 = this.zzgo;
                int length2 = iArr3 == null ? 0 : iArr3.length;
                int i3 = i2 + length2;
                int[] iArr4 = new int[i3];
                if (length2 != 0) {
                    System.arraycopy(this.zzgo, 0, iArr4, 0, length2);
                }
                while (length2 < i3) {
                    iArr4[length2] = zzbez.zzacc();
                    length2++;
                }
                this.zzgo = iArr4;
                zzbez.zzbs(zzbr);
            } else if (zzabk == 40) {
                this.zzgp = Long.valueOf(zzbez.zzacd());
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Long l = this.zzgl;
        if (l != null) {
            zzbfa.zzi(1, l.longValue());
        }
        Integer num = this.zzgm;
        if (num != null) {
            zzbfa.zzm(2, num.intValue());
        }
        Boolean bool = this.zzgn;
        if (bool != null) {
            zzbfa.zzf(3, bool.booleanValue());
        }
        int[] iArr = this.zzgo;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzgo;
                if (i >= iArr2.length) {
                    break;
                }
                zzbfa.zzm(4, iArr2[i]);
                i++;
            }
        }
        Long l2 = this.zzgp;
        if (l2 != null) {
            zzbfa.zza(5, l2.longValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int[] iArr;
        int zzr = super.zzr();
        Long l = this.zzgl;
        if (l != null) {
            zzr += zzbfa.zzd(1, l.longValue());
        }
        Integer num = this.zzgm;
        if (num != null) {
            zzr += zzbfa.zzq(2, num.intValue());
        }
        Boolean bool = this.zzgn;
        if (bool != null) {
            bool.booleanValue();
            zzr += zzbfa.zzcd(3) + 1;
        }
        int[] iArr2 = this.zzgo;
        if (iArr2 != null && iArr2.length > 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                iArr = this.zzgo;
                if (i >= iArr.length) {
                    break;
                }
                i2 += zzbfa.zzce(iArr[i]);
                i++;
            }
            zzr = zzr + i2 + (iArr.length * 1);
        }
        Long l2 = this.zzgp;
        return l2 != null ? zzr + zzbfa.zze(5, l2.longValue()) : zzr;
    }
}
