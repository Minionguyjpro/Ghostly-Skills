package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzsw implements zzts {
    private final /* synthetic */ int zzbnx;

    zzsw(zzsu zzsu, int i) {
        this.zzbnx = i;
    }

    public final void zzb(zztt zztt) throws RemoteException {
        if (zztt.zzxs != null) {
            zztt.zzxs.onAdFailedToLoad(this.zzbnx);
        }
    }
}
