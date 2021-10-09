package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzid extends zzbfc<zzid> {
    private String zzacp = null;
    private zzic[] zzamh = zzic.zzhr();
    private Integer zzami = null;

    public zzid() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzg */
    public final zzid zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                this.zzacp = zzbez.readString();
            } else if (zzabk == 18) {
                int zzb = zzbfl.zzb(zzbez, 18);
                zzic[] zzicArr = this.zzamh;
                int length = zzicArr == null ? 0 : zzicArr.length;
                int i = zzb + length;
                zzic[] zzicArr2 = new zzic[i];
                if (length != 0) {
                    System.arraycopy(this.zzamh, 0, zzicArr2, 0, length);
                }
                while (length < i - 1) {
                    zzicArr2[length] = new zzic();
                    zzbez.zza(zzicArr2[length]);
                    zzbez.zzabk();
                    length++;
                }
                zzicArr2[length] = new zzic();
                zzbez.zza(zzicArr2[length]);
                this.zzamh = zzicArr2;
            } else if (zzabk == 24) {
                int position = zzbez.getPosition();
                try {
                    this.zzami = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } catch (IllegalArgumentException unused) {
                    zzbez.zzdc(position);
                    zza(zzbez, zzabk);
                }
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.zzacp;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        zzic[] zzicArr = this.zzamh;
        if (zzicArr != null && zzicArr.length > 0) {
            int i = 0;
            while (true) {
                zzic[] zzicArr2 = this.zzamh;
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
        Integer num = this.zzami;
        if (num != null) {
            zzbfa.zzm(3, num.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        String str = this.zzacp;
        if (str != null) {
            zzr += zzbfa.zzg(1, str);
        }
        zzic[] zzicArr = this.zzamh;
        if (zzicArr != null && zzicArr.length > 0) {
            int i = 0;
            while (true) {
                zzic[] zzicArr2 = this.zzamh;
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
        Integer num = this.zzami;
        return num != null ? zzr + zzbfa.zzq(3, num.intValue()) : zzr;
    }
}
