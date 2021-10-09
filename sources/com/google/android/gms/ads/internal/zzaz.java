package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzaoe;

final /* synthetic */ class zzaz implements Runnable {
    private final Runnable zzxi;
    private final zzay zzzx;

    zzaz(zzay zzay, Runnable runnable) {
        this.zzzx = zzay;
        this.zzxi = runnable;
    }

    public final void run() {
        zzaoe.zzcvy.execute(new zzba(this.zzzx, this.zzxi));
    }
}
