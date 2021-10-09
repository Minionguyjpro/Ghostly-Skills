package com.google.android.gms.internal.ads;

import android.content.Context;

final class zzakm implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzakk zzcru;

    zzakm(zzakk zzakk, Context context) {
        this.zzcru = zzakk;
        this.val$context = context;
    }

    public final void run() {
        synchronized (this.zzcru.mLock) {
            String unused = this.zzcru.zzcpq = zzakk.zzam(this.val$context);
            this.zzcru.mLock.notifyAll();
        }
    }
}
