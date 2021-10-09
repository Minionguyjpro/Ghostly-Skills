package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzjj;

final class zzai implements Runnable {
    private final /* synthetic */ zzjj zzyh;
    private final /* synthetic */ zzah zzyi;

    zzai(zzah zzah, zzjj zzjj) {
        this.zzyi = zzah;
        this.zzyh = zzjj;
    }

    public final void run() {
        synchronized (this.zzyi.mLock) {
            if (this.zzyi.zzde()) {
                this.zzyi.zze(this.zzyh);
            } else {
                this.zzyi.zzb(this.zzyh, 1);
            }
        }
    }
}
