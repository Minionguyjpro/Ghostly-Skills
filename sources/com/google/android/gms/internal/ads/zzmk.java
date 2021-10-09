package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzmk implements Runnable {
    private final /* synthetic */ zzmj zzatk;

    zzmk(zzmj zzmj) {
        this.zzatk = zzmj;
    }

    public final void run() {
        if (this.zzatk.zzxs != null) {
            try {
                this.zzatk.zzxs.onAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzane.zzc("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
