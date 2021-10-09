package com.google.android.gms.internal.ads;

final class zzabj implements Runnable {
    private final /* synthetic */ zzajh zzaam;
    private final /* synthetic */ zzabh zzbzk;

    zzabj(zzabh zzabh, zzajh zzajh) {
        this.zzbzk = zzabh;
        this.zzaam = zzajh;
    }

    public final void run() {
        synchronized (this.zzbzk.mLock) {
            zzabh zzabh = this.zzbzk;
            zzabh.zzbzd.zzb(this.zzaam);
        }
    }
}
