package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzyy implements Runnable {
    private final /* synthetic */ zzyq zzbvd;

    zzyy(zzyq zzyq) {
        this.zzbvd = zzyq;
    }

    public final void run() {
        try {
            this.zzbvd.zzbuu.onAdLoaded();
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
