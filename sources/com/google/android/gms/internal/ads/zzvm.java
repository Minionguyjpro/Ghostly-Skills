package com.google.android.gms.internal.ads;

final class zzvm implements Runnable {
    private final /* synthetic */ zzvw zzbqi;
    private final /* synthetic */ zzuu zzbqj;
    private final /* synthetic */ zzvf zzbqk;

    zzvm(zzvf zzvf, zzvw zzvw, zzuu zzuu) {
        this.zzbqk = zzvf;
        this.zzbqi = zzvw;
        this.zzbqj = zzuu;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzvf r0 = r3.zzbqk
            java.lang.Object r0 = r0.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzvw r1 = r3.zzbqi     // Catch:{ all -> 0x0036 }
            int r1 = r1.getStatus()     // Catch:{ all -> 0x0036 }
            r2 = -1
            if (r1 == r2) goto L_0x0034
            com.google.android.gms.internal.ads.zzvw r1 = r3.zzbqi     // Catch:{ all -> 0x0036 }
            int r1 = r1.getStatus()     // Catch:{ all -> 0x0036 }
            r2 = 1
            if (r1 != r2) goto L_0x001a
            goto L_0x0034
        L_0x001a:
            com.google.android.gms.internal.ads.zzvw r1 = r3.zzbqi     // Catch:{ all -> 0x0036 }
            r1.reject()     // Catch:{ all -> 0x0036 }
            java.util.concurrent.Executor r1 = com.google.android.gms.internal.ads.zzaoe.zzcvy     // Catch:{ all -> 0x0036 }
            com.google.android.gms.internal.ads.zzuu r2 = r3.zzbqj     // Catch:{ all -> 0x0036 }
            r2.getClass()     // Catch:{ all -> 0x0036 }
            java.lang.Runnable r2 = com.google.android.gms.internal.ads.zzvn.zza(r2)     // Catch:{ all -> 0x0036 }
            r1.execute(r2)     // Catch:{ all -> 0x0036 }
            java.lang.String r1 = "Could not receive loaded message in a timely manner. Rejecting."
            com.google.android.gms.internal.ads.zzakb.v(r1)     // Catch:{ all -> 0x0036 }
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return
        L_0x0034:
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return
        L_0x0036:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzvm.run():void");
    }
}
