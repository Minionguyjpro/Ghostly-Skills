package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzsu extends zzki {
    private final /* synthetic */ zzst zzbnw;

    zzsu(zzst zzst) {
        this.zzbnw = zzst;
    }

    public final void onAdClicked() throws RemoteException {
        this.zzbnw.zzxo.add(new zztb(this));
    }

    public final void onAdClosed() throws RemoteException {
        this.zzbnw.zzxo.add(new zzsv(this));
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        this.zzbnw.zzxo.add(new zzsw(this, i));
        zzakb.v("Pooled interstitial failed to load.");
    }

    public final void onAdImpression() throws RemoteException {
        this.zzbnw.zzxo.add(new zzta(this));
    }

    public final void onAdLeftApplication() throws RemoteException {
        this.zzbnw.zzxo.add(new zzsx(this));
    }

    public final void onAdLoaded() throws RemoteException {
        this.zzbnw.zzxo.add(new zzsy(this));
        zzakb.v("Pooled interstitial loaded.");
    }

    public final void onAdOpened() throws RemoteException {
        this.zzbnw.zzxo.add(new zzsz(this));
    }
}
