package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzjj;

final class zzaj implements Runnable {
    private final /* synthetic */ zzjj zzyh;
    private final /* synthetic */ zzah zzyi;
    private final /* synthetic */ int zzyj;

    zzaj(zzah zzah, zzjj zzjj, int i) {
        this.zzyi = zzah;
        this.zzyh = zzjj;
        this.zzyj = i;
    }

    public final void run() {
        synchronized (this.zzyi.mLock) {
            this.zzyi.zzb(this.zzyh, this.zzyj);
        }
    }
}
