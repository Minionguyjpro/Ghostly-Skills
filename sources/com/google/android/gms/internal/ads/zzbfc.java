package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbfc;
import java.io.IOException;

public abstract class zzbfc<M extends zzbfc<M>> extends zzbfi {
    protected zzbfe zzebk;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzbfc zzbfc = (zzbfc) super.clone();
        zzbfg.zza(this, zzbfc);
        return zzbfc;
    }

    public void zza(zzbfa zzbfa) throws IOException {
        if (this.zzebk != null) {
            for (int i = 0; i < this.zzebk.size(); i++) {
                this.zzebk.zzdg(i).zza(zzbfa);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbez zzbez, int i) throws IOException {
        int position = zzbez.getPosition();
        if (!zzbez.zzbq(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzbfk zzbfk = new zzbfk(i, zzbez.zzab(position, zzbez.getPosition() - position));
        zzbff zzbff = null;
        zzbfe zzbfe = this.zzebk;
        if (zzbfe == null) {
            this.zzebk = new zzbfe();
        } else {
            zzbff = zzbfe.zzdf(i2);
        }
        if (zzbff == null) {
            zzbff = new zzbff();
            this.zzebk.zza(i2, zzbff);
        }
        zzbff.zza(zzbfk);
        return true;
    }

    public final /* synthetic */ zzbfi zzago() throws CloneNotSupportedException {
        return (zzbfc) clone();
    }

    /* access modifiers changed from: protected */
    public int zzr() {
        if (this.zzebk == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzebk.size(); i2++) {
            i += this.zzebk.zzdg(i2).zzr();
        }
        return i;
    }
}
