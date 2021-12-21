package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class zzanw implements Runnable {
    private final Future zzbnv;
    private final zzanz zzcvs;

    zzanw(zzanz zzanz, Future future) {
        this.zzcvs = zzanz;
        this.zzbnv = future;
    }

    public final void run() {
        zzanz zzanz = this.zzcvs;
        Future future = this.zzbnv;
        if (zzanz.isCancelled()) {
            future.cancel(true);
        }
    }
}
