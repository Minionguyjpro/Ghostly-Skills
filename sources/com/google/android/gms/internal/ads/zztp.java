package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztp implements zzts {
    private final /* synthetic */ int zzbnx;

    zztp(zzti zzti, int i) {
        this.zzbnx = i;
    }

    public final void zzb(zztt zztt) throws RemoteException {
        if (zztt.zzboh != null) {
            zztt.zzboh.onRewardedVideoAdFailedToLoad(this.zzbnx);
        }
    }
}
