package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final /* synthetic */ class zzsp implements Runnable {
    private final zzso zzbnq;
    private final zzsf zzbnr;
    private final zzaoj zzbns;
    private final zzsg zzbnt;

    zzsp(zzso zzso, zzsf zzsf, zzaoj zzaoj, zzsg zzsg) {
        this.zzbnq = zzso;
        this.zzbnr = zzsf;
        this.zzbns = zzaoj;
        this.zzbnt = zzsg;
    }

    public final void run() {
        zzso zzso = this.zzbnq;
        zzsf zzsf = this.zzbnr;
        zzaoj zzaoj = this.zzbns;
        try {
            zzaoj.set(zzsf.zzlb().zza(this.zzbnt));
        } catch (RemoteException e) {
            zzakb.zzb("Unable to obtain a cache service instance.", e);
            zzaoj.setException(e);
            zzso.zzbnn.disconnect();
        }
    }
}
