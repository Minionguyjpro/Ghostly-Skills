package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzif extends zzbfc<zzif> {
    private Integer zzamo = null;
    private zzis zzamp = null;
    private zzis zzamq = null;
    private zzis zzamr = null;
    private zzis[] zzams = zzis.zzht();
    private Integer zzamt = null;

    public zzif() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        zzis zzis;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk != 8) {
                if (zzabk == 18) {
                    if (this.zzamp == null) {
                        this.zzamp = new zzis();
                    }
                    zzis = this.zzamp;
                } else if (zzabk == 26) {
                    if (this.zzamq == null) {
                        this.zzamq = new zzis();
                    }
                    zzis = this.zzamq;
                } else if (zzabk == 34) {
                    if (this.zzamr == null) {
                        this.zzamr = new zzis();
                    }
                    zzis = this.zzamr;
                } else if (zzabk == 42) {
                    int zzb = zzbfl.zzb(zzbez, 42);
                    zzis[] zzisArr = this.zzams;
                    int length = zzisArr == null ? 0 : zzisArr.length;
                    int i = zzb + length;
                    zzis[] zzisArr2 = new zzis[i];
                    if (length != 0) {
                        System.arraycopy(this.zzams, 0, zzisArr2, 0, length);
                    }
                    while (length < i - 1) {
                        zzisArr2[length] = new zzis();
                        zzbez.zza(zzisArr2[length]);
                        zzbez.zzabk();
                        length++;
                    }
                    zzisArr2[length] = new zzis();
                    zzbez.zza(zzisArr2[length]);
                    this.zzams = zzisArr2;
                } else if (zzabk == 48) {
                    this.zzamt = Integer.valueOf(zzbez.zzacc());
                } else if (!super.zza(zzbez, zzabk)) {
                    return this;
                }
                zzbez.zza(zzis);
            } else {
                this.zzamo = Integer.valueOf(zzbez.zzacc());
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzamo;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        zzis zzis = this.zzamp;
        if (zzis != null) {
            zzbfa.zza(2, (zzbfi) zzis);
        }
        zzis zzis2 = this.zzamq;
        if (zzis2 != null) {
            zzbfa.zza(3, (zzbfi) zzis2);
        }
        zzis zzis3 = this.zzamr;
        if (zzis3 != null) {
            zzbfa.zza(4, (zzbfi) zzis3);
        }
        zzis[] zzisArr = this.zzams;
        if (zzisArr != null && zzisArr.length > 0) {
            int i = 0;
            while (true) {
                zzis[] zzisArr2 = this.zzams;
                if (i >= zzisArr2.length) {
                    break;
                }
                zzis zzis4 = zzisArr2[i];
                if (zzis4 != null) {
                    zzbfa.zza(5, (zzbfi) zzis4);
                }
                i++;
            }
        }
        Integer num2 = this.zzamt;
        if (num2 != null) {
            zzbfa.zzm(6, num2.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzamo;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        zzis zzis = this.zzamp;
        if (zzis != null) {
            zzr += zzbfa.zzb(2, (zzbfi) zzis);
        }
        zzis zzis2 = this.zzamq;
        if (zzis2 != null) {
            zzr += zzbfa.zzb(3, (zzbfi) zzis2);
        }
        zzis zzis3 = this.zzamr;
        if (zzis3 != null) {
            zzr += zzbfa.zzb(4, (zzbfi) zzis3);
        }
        zzis[] zzisArr = this.zzams;
        if (zzisArr != null && zzisArr.length > 0) {
            int i = 0;
            while (true) {
                zzis[] zzisArr2 = this.zzams;
                if (i >= zzisArr2.length) {
                    break;
                }
                zzis zzis4 = zzisArr2[i];
                if (zzis4 != null) {
                    zzr += zzbfa.zzb(5, (zzbfi) zzis4);
                }
                i++;
            }
        }
        Integer num2 = this.zzamt;
        return num2 != null ? zzr + zzbfa.zzq(6, num2.intValue()) : zzr;
    }
}
