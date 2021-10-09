package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzxl implements Runnable {
    private final /* synthetic */ zzxe zzbun;

    zzxl(zzxk zzxk, zzxe zzxe) {
        this.zzbun = zzxe;
    }

    public final void run() {
        try {
            this.zzbun.zzbtx.destroy();
        } catch (RemoteException e) {
            zzakb.zzc("Could not destroy mediation adapter.", e);
        }
    }
}
