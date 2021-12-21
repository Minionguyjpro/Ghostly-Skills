package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfp extends zzbfc<zzbfp> {
    private zzbfq zzecz = null;
    public zzbfo[] zzeda = zzbfo.zzagt();
    private byte[] zzedb = null;
    private byte[] zzedc = null;
    private Integer zzedd = null;

    public zzbfp() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            if (zzabk == 0) {
                return this;
            }
            if (zzabk == 10) {
                if (this.zzecz == null) {
                    this.zzecz = new zzbfq();
                }
                zzbez.zza(this.zzecz);
            } else if (zzabk == 18) {
                int zzb = zzbfl.zzb(zzbez, 18);
                zzbfo[] zzbfoArr = this.zzeda;
                int length = zzbfoArr == null ? 0 : zzbfoArr.length;
                int i = zzb + length;
                zzbfo[] zzbfoArr2 = new zzbfo[i];
                if (length != 0) {
                    System.arraycopy(this.zzeda, 0, zzbfoArr2, 0, length);
                }
                while (length < i - 1) {
                    zzbfoArr2[length] = new zzbfo();
                    zzbez.zza(zzbfoArr2[length]);
                    zzbez.zzabk();
                    length++;
                }
                zzbfoArr2[length] = new zzbfo();
                zzbez.zza(zzbfoArr2[length]);
                this.zzeda = zzbfoArr2;
            } else if (zzabk == 26) {
                this.zzedb = zzbez.readBytes();
            } else if (zzabk == 34) {
                this.zzedc = zzbez.readBytes();
            } else if (zzabk == 40) {
                this.zzedd = Integer.valueOf(zzbez.zzabn());
            } else if (!super.zza(zzbez, zzabk)) {
                return this;
            }
        }
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        zzbfq zzbfq = this.zzecz;
        if (zzbfq != null) {
            zzbfa.zza(1, (zzbfi) zzbfq);
        }
        zzbfo[] zzbfoArr = this.zzeda;
        if (zzbfoArr != null && zzbfoArr.length > 0) {
            int i = 0;
            while (true) {
                zzbfo[] zzbfoArr2 = this.zzeda;
                if (i >= zzbfoArr2.length) {
                    break;
                }
                zzbfo zzbfo = zzbfoArr2[i];
                if (zzbfo != null) {
                    zzbfa.zza(2, (zzbfi) zzbfo);
                }
                i++;
            }
        }
        byte[] bArr = this.zzedb;
        if (bArr != null) {
            zzbfa.zza(3, bArr);
        }
        byte[] bArr2 = this.zzedc;
        if (bArr2 != null) {
            zzbfa.zza(4, bArr2);
        }
        Integer num = this.zzedd;
        if (num != null) {
            zzbfa.zzm(5, num.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        zzbfq zzbfq = this.zzecz;
        if (zzbfq != null) {
            zzr += zzbfa.zzb(1, (zzbfi) zzbfq);
        }
        zzbfo[] zzbfoArr = this.zzeda;
        if (zzbfoArr != null && zzbfoArr.length > 0) {
            int i = 0;
            while (true) {
                zzbfo[] zzbfoArr2 = this.zzeda;
                if (i >= zzbfoArr2.length) {
                    break;
                }
                zzbfo zzbfo = zzbfoArr2[i];
                if (zzbfo != null) {
                    zzr += zzbfa.zzb(2, (zzbfi) zzbfo);
                }
                i++;
            }
        }
        byte[] bArr = this.zzedb;
        if (bArr != null) {
            zzr += zzbfa.zzb(3, bArr);
        }
        byte[] bArr2 = this.zzedc;
        if (bArr2 != null) {
            zzr += zzbfa.zzb(4, bArr2);
        }
        Integer num = this.zzedd;
        return num != null ? zzr + zzbfa.zzq(5, num.intValue()) : zzr;
    }
}
