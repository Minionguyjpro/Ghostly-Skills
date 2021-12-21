package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzajp {
    private final Object mLock;
    private volatile int zzcpx;
    private volatile long zzcpy;

    private zzajp() {
        this.mLock = new Object();
        this.zzcpx = zzajq.zzcpz;
        this.zzcpy = 0;
    }

    /* synthetic */ zzajp(zzajo zzajo) {
        this();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzd(int r5, int r6) {
        /*
            r4 = this;
            r4.zzqk()
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzbv.zzer()
            long r0 = r0.currentTimeMillis()
            java.lang.Object r2 = r4.mLock
            monitor-enter(r2)
            int r3 = r4.zzcpx     // Catch:{ all -> 0x0020 }
            if (r3 == r5) goto L_0x0014
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            return
        L_0x0014:
            r4.zzcpx = r6     // Catch:{ all -> 0x0020 }
            int r5 = r4.zzcpx     // Catch:{ all -> 0x0020 }
            int r6 = com.google.android.gms.internal.ads.zzajq.zzcqb     // Catch:{ all -> 0x0020 }
            if (r5 != r6) goto L_0x001e
            r4.zzcpy = r0     // Catch:{ all -> 0x0020 }
        L_0x001e:
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            return
        L_0x0020:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajp.zzd(int, int):void");
    }

    private final void zzqk() {
        long currentTimeMillis = zzbv.zzer().currentTimeMillis();
        synchronized (this.mLock) {
            if (this.zzcpx == zzajq.zzcqb) {
                if (this.zzcpy + ((Long) zzkb.zzik().zzd(zznk.zzbfn)).longValue() <= currentTimeMillis) {
                    this.zzcpx = zzajq.zzcpz;
                }
            }
        }
    }

    public final void zzaa(boolean z) {
        int i;
        int i2;
        if (z) {
            i = zzajq.zzcpz;
            i2 = zzajq.zzcqa;
        } else {
            i = zzajq.zzcqa;
            i2 = zzajq.zzcpz;
        }
        zzd(i, i2);
    }

    public final boolean zzqa() {
        zzqk();
        return this.zzcpx == zzajq.zzcqa;
    }

    public final boolean zzqb() {
        zzqk();
        return this.zzcpx == zzajq.zzcqb;
    }

    public final void zzqc() {
        zzd(zzajq.zzcqa, zzajq.zzcqb);
    }
}
