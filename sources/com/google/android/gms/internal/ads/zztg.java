package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztg extends zzkf {
    private final /* synthetic */ zzst zzbnw;

    zztg(zzst zzst) {
        this.zzbnw = zzst;
    }

    public final void onAdClicked() throws RemoteException {
        this.zzbnw.zzxo.add(new zzth(this));
    }
}
