package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzoq;

final class zzu implements Runnable {
    private final /* synthetic */ zzq zzwt;
    private final /* synthetic */ zzoq zzww;

    zzu(zzq zzq, zzoq zzoq) {
        this.zzwt = zzq;
        this.zzww = zzoq;
    }

    public final void run() {
        try {
            if (this.zzwt.zzvw.zzadf != null) {
                this.zzwt.zzvw.zzadf.zza(this.zzww);
                this.zzwt.zzb(this.zzww.zzka());
            }
        } catch (RemoteException e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
