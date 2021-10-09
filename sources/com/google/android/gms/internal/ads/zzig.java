package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzig extends zzbfc<zzig> {
    public String zzamu = null;
    private zzis zzamv = null;
    private Integer zzamw = null;
    public zzit zzamx = null;
    private Integer zzamy = null;
    private Integer zzamz = null;
    private Integer zzana = null;
    private Integer zzanb = null;

    public zzig() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzi */
    public final zzig zza(zzbez zzbez) throws IOException {
        zzbfi zzbfi;
        int i;
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk != 10) {
                if (zzabk == 18) {
                    if (this.zzamv == null) {
                        this.zzamv = new zzis();
                    }
                    zzbfi = this.zzamv;
                } else if (zzabk == 24) {
                    this.zzamw = Integer.valueOf(zzbez.zzacc());
                } else if (zzabk == 34) {
                    if (this.zzamx == null) {
                        this.zzamx = new zzit();
                    }
                    zzbfi = this.zzamx;
                } else if (zzabk == 40) {
                    this.zzamy = Integer.valueOf(zzbez.zzacc());
                } else if (zzabk == 48) {
                    i = zzbez.getPosition();
                    this.zzamz = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } else if (zzabk == 56) {
                    i = zzbez.getPosition();
                    this.zzana = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                } else if (zzabk == 64) {
                    i = zzbez.getPosition();
                    try {
                        this.zzanb = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                    } catch (IllegalArgumentException unused) {
                        zzbez.zzdc(i);
                        zza(zzbez, zzabk);
                    }
                } else if (!super.zza(zzbez, zzabk)) {
                    return this;
                }
                zzbez.zza(zzbfi);
            } else {
                this.zzamu = zzbez.readString();
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.zzamu;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        zzis zzis = this.zzamv;
        if (zzis != null) {
            zzbfa.zza(2, (zzbfi) zzis);
        }
        Integer num = this.zzamw;
        if (num != null) {
            zzbfa.zzm(3, num.intValue());
        }
        zzit zzit = this.zzamx;
        if (zzit != null) {
            zzbfa.zza(4, (zzbfi) zzit);
        }
        Integer num2 = this.zzamy;
        if (num2 != null) {
            zzbfa.zzm(5, num2.intValue());
        }
        Integer num3 = this.zzamz;
        if (num3 != null) {
            zzbfa.zzm(6, num3.intValue());
        }
        Integer num4 = this.zzana;
        if (num4 != null) {
            zzbfa.zzm(7, num4.intValue());
        }
        Integer num5 = this.zzanb;
        if (num5 != null) {
            zzbfa.zzm(8, num5.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        String str = this.zzamu;
        if (str != null) {
            zzr += zzbfa.zzg(1, str);
        }
        zzis zzis = this.zzamv;
        if (zzis != null) {
            zzr += zzbfa.zzb(2, (zzbfi) zzis);
        }
        Integer num = this.zzamw;
        if (num != null) {
            zzr += zzbfa.zzq(3, num.intValue());
        }
        zzit zzit = this.zzamx;
        if (zzit != null) {
            zzr += zzbfa.zzb(4, (zzbfi) zzit);
        }
        Integer num2 = this.zzamy;
        if (num2 != null) {
            zzr += zzbfa.zzq(5, num2.intValue());
        }
        Integer num3 = this.zzamz;
        if (num3 != null) {
            zzr += zzbfa.zzq(6, num3.intValue());
        }
        Integer num4 = this.zzana;
        if (num4 != null) {
            zzr += zzbfa.zzq(7, num4.intValue());
        }
        Integer num5 = this.zzanb;
        return num5 != null ? zzr + zzbfa.zzq(8, num5.intValue()) : zzr;
    }
}
