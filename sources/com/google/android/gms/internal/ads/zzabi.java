package com.google.android.gms.internal.ads;

final class zzabi implements Runnable {
    private final /* synthetic */ zzabh zzbzk;

    zzabi(zzabh zzabh) {
        this.zzbzk = zzabh;
    }

    public final void run() {
        this.zzbzk.onStop();
    }
}
