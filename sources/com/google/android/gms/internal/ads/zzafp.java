package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbv;

final class zzafp implements Runnable {
    private final /* synthetic */ zzaef zzchk;
    private final /* synthetic */ zzaeq zzchl;
    private final /* synthetic */ zzafn zzchm;

    zzafp(zzafn zzafn, zzaef zzaef, zzaeq zzaeq) {
        this.zzchm = zzafn;
        this.zzchk = zzaef;
        this.zzchl = zzaeq;
    }

    public final void run() {
        zzaej zzaej;
        try {
            zzaej = this.zzchm.zzb(this.zzchk);
        } catch (Exception e) {
            zzbv.zzeo().zza(e, "AdRequestServiceImpl.loadAdAsync");
            zzakb.zzc("Could not fetch ad response due to an Exception.", e);
            zzaej = null;
        }
        if (zzaej == null) {
            zzaej = new zzaej(0);
        }
        try {
            this.zzchl.zza(zzaej);
        } catch (RemoteException e2) {
            zzakb.zzc("Fail to forward ad response.", e2);
        }
    }
}
