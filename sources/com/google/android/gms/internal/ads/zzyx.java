package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzyx implements Runnable {
    private final /* synthetic */ zzyq zzbvd;

    zzyx(zzyq zzyq) {
        this.zzbvd = zzyq;
    }

    public final void run() {
        try {
            this.zzbvd.zzbuu.onAdOpened();
        } catch (RemoteException e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
