package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztc extends zzlb {
    private final /* synthetic */ zzst zzbnw;

    zztc(zzst zzst) {
        this.zzbnw = zzst;
    }

    public final void onAppEvent(String str, String str2) throws RemoteException {
        this.zzbnw.zzxo.add(new zztd(this, str, str2));
    }
}
