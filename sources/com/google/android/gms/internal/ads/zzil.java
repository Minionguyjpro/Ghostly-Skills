package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzil extends zzbfc<zzil> {
    private zzis zzant = null;
    private Integer zzanu = null;
    private zzij zzanv = null;
    private zzir[] zzanw = zzir.zzhs();

    public zzil() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzn */
    public final zzil zza(zzbez zzbez) throws IOException {
        zzbfi zzbfi;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                if (this.zzanv == null) {
                    this.zzanv = new zzij();
                }
                zzbfi = this.zzanv;
            } else if (zzabk == 18) {
                int zzb = zzbfl.zzb(zzbez, 18);
                zzir[] zzirArr = this.zzanw;
                int length = zzirArr == null ? 0 : zzirArr.length;
                int i = zzb + length;
                zzir[] zzirArr2 = new zzir[i];
                if (length != 0) {
                    System.arraycopy(this.zzanw, 0, zzirArr2, 0, length);
                }
                while (length < i - 1) {
                    zzirArr2[length] = new zzir();
                    zzbez.zza(zzirArr2[length]);
                    zzbez.zzabk();
                    length++;
                }
                zzirArr2[length] = new zzir();
                zzbez.zza(zzirArr2[length]);
                this.zzanw = zzirArr2;
            } else if (zzabk == 24) {
                int position = zzbez.getPosition();
                try {
                    this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(position);
                    zza(zzbez, zzabk);
                }
            } else if (zzabk == 34) {
                if (this.zzant == null) {
                    this.zzant = new zzis();
                }
                zzbfi = this.zzant;
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
            zzbez.zza(zzbfi);
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        zzij zzij = this.zzanv;
        if (zzij != null) {
            zzbfa.zza(1, (zzbfi) zzij);
        }
        zzir[] zzirArr = this.zzanw;
        if (zzirArr != null && zzirArr.length > 0) {
            int i = 0;
            while (true) {
                zzir[] zzirArr2 = this.zzanw;
                if (i >= zzirArr2.length) {
                    break;
                }
                zzir zzir = zzirArr2[i];
                if (zzir != null) {
                    zzbfa.zza(2, (zzbfi) zzir);
                }
                i++;
            }
        }
        Integer num = this.zzanu;
        if (num != null) {
            zzbfa.zzm(3, num.intValue());
        }
        zzis zzis = this.zzant;
        if (zzis != null) {
            zzbfa.zza(4, (zzbfi) zzis);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        zzij zzij = this.zzanv;
        if (zzij != null) {
            zzr += zzbfa.zzb(1, (zzbfi) zzij);
        }
        zzir[] zzirArr = this.zzanw;
        if (zzirArr != null && zzirArr.length > 0) {
            int i = 0;
            while (true) {
                zzir[] zzirArr2 = this.zzanw;
                if (i >= zzirArr2.length) {
                    break;
                }
                zzir zzir = zzirArr2[i];
                if (zzir != null) {
                    zzr += zzbfa.zzb(2, (zzbfi) zzir);
                }
                i++;
            }
        }
        Integer num = this.zzanu;
        if (num != null) {
            zzr += zzbfa.zzq(3, num.intValue());
        }
        zzis zzis = this.zzant;
        return zzis != null ? zzr + zzbfa.zzb(4, (zzbfi) zzis) : zzr;
    }
}
