package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzamj {
    private Object mLock = new Object();
    private long zzctx;
    private long zzcty = Long.MIN_VALUE;

    public zzamj(long j) {
        this.zzctx = j;
    }

    public final boolean tryAcquire() {
        synchronized (this.mLock) {
            long elapsedRealtime = zzbv.zzer().elapsedRealtime();
            if (this.zzcty + this.zzctx > elapsedRealtime) {
                return false;
            }
            this.zzcty = elapsedRealtime;
            return true;
        }
    }
}
