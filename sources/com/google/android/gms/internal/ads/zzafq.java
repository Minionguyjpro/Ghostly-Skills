package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final class zzafq implements Runnable {
    private final /* synthetic */ Future zzchn;

    zzafq(zzafn zzafn, Future future) {
        this.zzchn = future;
    }

    public final void run() {
        if (!this.zzchn.isDone()) {
            this.zzchn.cancel(true);
        }
    }
}
