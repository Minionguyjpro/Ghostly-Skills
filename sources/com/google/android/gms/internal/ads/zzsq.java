package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class zzsq implements Runnable {
    private final zzaoj zzbnu;
    private final Future zzbnv;

    zzsq(zzaoj zzaoj, Future future) {
        this.zzbnu = zzaoj;
        this.zzbnv = future;
    }

    public final void run() {
        zzaoj zzaoj = this.zzbnu;
        Future future = this.zzbnv;
        if (zzaoj.isCancelled()) {
            future.cancel(true);
        }
    }
}
