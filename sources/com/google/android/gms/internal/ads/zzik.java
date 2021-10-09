package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzik extends zzbfc<zzik> {
    private int[] zzans = zzbfl.zzeby;
    private Integer zzanu = null;

    public zzik() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzm */
    public final zzik zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 8) {
                int position = zzbez.getPosition();
                try {
                    this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(position);
                    zza(zzbez, zzabk);
                }
            } else if (zzabk == 16) {
                int zzb = zzbfl.zzb(zzbez, 16);
                int[] iArr = this.zzans;
                int length = iArr == null ? 0 : iArr.length;
                int i = zzb + length;
                int[] iArr2 = new int[i];
                if (length != 0) {
                    System.arraycopy(this.zzans, 0, iArr2, 0, length);
                }
                while (length < i - 1) {
                    iArr2[length] = zzbez.zzacc();
                    zzbez.zzabk();
                    length++;
                }
                iArr2[length] = zzbez.zzacc();
                this.zzans = iArr2;
            } else if (zzabk == 18) {
                int zzbr = zzbez.zzbr(zzbez.zzacc());
                int position2 = zzbez.getPosition();
                int i2 = 0;
                while (zzbez.zzagn() > 0) {
                    zzbez.zzacc();
                    i2++;
                }
                zzbez.zzdc(position2);
                int[] iArr3 = this.zzans;
                int length2 = iArr3 == null ? 0 : iArr3.length;
                int i3 = i2 + length2;
                int[] iArr4 = new int[i3];
                if (length2 != 0) {
                    System.arraycopy(this.zzans, 0, iArr4, 0, length2);
                }
                while (length2 < i3) {
                    iArr4[length2] = zzbez.zzacc();
                    length2++;
                }
                this.zzans = iArr4;
                zzbez.zzbs(zzbr);
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzanu;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        int[] iArr = this.zzans;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzans;
                if (i >= iArr2.length) {
                    break;
                }
                zzbfa.zzm(2, iArr2[i]);
                i++;
            }
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzanu;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        int[] iArr = this.zzans;
        if (iArr == null || iArr.length <= 0) {
            return zzr;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr2 = this.zzans;
            if (i >= iArr2.length) {
                return zzr + i2 + (iArr2.length * 1);
            }
            i2 += zzbfa.zzce(iArr2[i]);
            i++;
        }
    }
}
