package com.google.android.gms.ads.internal;

final /* synthetic */ class zzba implements Runnable {
    private final Runnable zzxi;
    private final zzay zzzx;

    zzba(zzay zzay, Runnable runnable) {
        this.zzzx = zzay;
        this.zzxi = runnable;
    }

    public final void run() {
        this.zzzx.zza(this.zzxi);
    }
}
