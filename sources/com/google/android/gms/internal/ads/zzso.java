package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.common.internal.BaseGmsClient;

final class zzso implements BaseGmsClient.BaseConnectionCallbacks {
    final /* synthetic */ zzsm zzbnn;
    private final /* synthetic */ zzaoj zzbno;
    private final /* synthetic */ zzsg zzbnp;

    zzso(zzsm zzsm, zzaoj zzaoj, zzsg zzsg) {
        this.zzbnn = zzsm;
        this.zzbno = zzaoj;
        this.zzbnp = zzsg;
    }

    public final void onConnected(Bundle bundle) {
        synchronized (this.zzbnn.mLock) {
            if (!this.zzbnn.zzbnm) {
                boolean unused = this.zzbnn.zzbnm = true;
                zzsf zzd = this.zzbnn.zzbnl;
                if (zzd != null) {
                    this.zzbno.zza(new zzsq(this.zzbno, zzaki.zzb(new zzsp(this, zzd, this.zzbno, this.zzbnp))), zzaoe.zzcvz);
                }
            }
        }
    }

    public final void onConnectionSuspended(int i) {
    }
}
