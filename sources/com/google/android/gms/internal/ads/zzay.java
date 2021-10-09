package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzay extends zzbfc<zzay> {
    public String zzcx;
    private String zzcy;
    private String zzcz;
    private String zzda;
    private String zzdb;

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                this.zzcx = zzbez.readString();
            } else if (zzabk == 18) {
                this.zzcy = zzbez.readString();
            } else if (zzabk == 26) {
                this.zzcz = zzbez.readString();
            } else if (zzabk == 34) {
                this.zzda = zzbez.readString();
            } else if (zzabk == 42) {
                this.zzdb = zzbez.readString();
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        String str = this.zzcx;
        if (str != null) {
            zzbfa.zzf(1, str);
        }
        String str2 = this.zzcy;
        if (str2 != null) {
            zzbfa.zzf(2, str2);
        }
        String str3 = this.zzcz;
        if (str3 != null) {
            zzbfa.zzf(3, str3);
        }
        String str4 = this.zzda;
        if (str4 != null) {
            zzbfa.zzf(4, str4);
        }
        String str5 = this.zzdb;
        if (str5 != null) {
            zzbfa.zzf(5, str5);
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        String str = this.zzcx;
        if (str != null) {
            zzr += zzbfa.zzg(1, str);
        }
        String str2 = this.zzcy;
        if (str2 != null) {
            zzr += zzbfa.zzg(2, str2);
        }
        String str3 = this.zzcz;
        if (str3 != null) {
            zzr += zzbfa.zzg(3, str3);
        }
        String str4 = this.zzda;
        if (str4 != null) {
            zzr += zzbfa.zzg(4, str4);
        }
        String str5 = this.zzdb;
        return str5 != null ? zzr + zzbfa.zzg(5, str5) : zzr;
    }
}
