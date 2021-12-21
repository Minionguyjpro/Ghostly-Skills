package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzyw implements Runnable {
    private final /* synthetic */ zzyq zzbvd;

    zzyw(zzyq zzyq) {
        this.zzbvd = zzyq;
    }

    public final void run() {
        try {
            this.zzbvd.zzbuu.onAdLeftApplication();
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
