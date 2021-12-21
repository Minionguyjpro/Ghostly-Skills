package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeoutException;

final /* synthetic */ class zzans implements Runnable {
    private final zzaoj zzbnu;

    zzans(zzaoj zzaoj) {
        this.zzbnu = zzaoj;
    }

    public final void run() {
        this.zzbnu.setException(new TimeoutException());
    }
}
