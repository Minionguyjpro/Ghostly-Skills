package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzmp implements Runnable {
    private final /* synthetic */ zzmo zzatm;

    zzmp(zzmo zzmo) {
        this.zzatm = zzmo;
    }

    public final void run() {
        if (this.zzatm.zzatl != null) {
            try {
                this.zzatm.zzatl.onRewardedVideoAdFailedToLoad(1);
            } catch (RemoteException e) {
                zzane.zzc("Could not notify onRewardedVideoAdFailedToLoad event.", e);
            }
        }
    }
}
