package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzmi implements Runnable {
    private final /* synthetic */ zzmh zzatj;

    zzmi(zzmh zzmh) {
        this.zzatj = zzmh;
    }

    public final void run() {
        if (this.zzatj.zzati.zzxs != null) {
            try {
                this.zzatj.zzati.zzxs.onAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzane.zzc("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
