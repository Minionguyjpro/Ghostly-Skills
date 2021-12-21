package com.google.android.gms.internal.ads;

import android.content.Context;

@zzadh
public final class zzaix implements zzft {
    private final Object mLock;
    private final Context zzatx;
    private boolean zzcno;
    private String zzye;

    public zzaix(Context context, String str) {
        this.zzatx = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzye = str;
        this.zzcno = false;
        this.mLock = new Object();
    }

    public final void setAdUnitId(String str) {
        this.zzye = str;
    }

    public final void zza(zzfs zzfs) {
        zzx(zzfs.zztg);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzx(boolean r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzaiy r0 = com.google.android.gms.ads.internal.zzbv.zzfh()
            android.content.Context r1 = r3.zzatx
            boolean r0 = r0.zzs(r1)
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.zzcno     // Catch:{ all -> 0x003f }
            if (r1 != r4) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x0016:
            r3.zzcno = r4     // Catch:{ all -> 0x003f }
            java.lang.String r4 = r3.zzye     // Catch:{ all -> 0x003f }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0022
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x0022:
            boolean r4 = r3.zzcno     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0032
            com.google.android.gms.internal.ads.zzaiy r4 = com.google.android.gms.ads.internal.zzbv.zzfh()     // Catch:{ all -> 0x003f }
            android.content.Context r1 = r3.zzatx     // Catch:{ all -> 0x003f }
            java.lang.String r2 = r3.zzye     // Catch:{ all -> 0x003f }
            r4.zzb(r1, r2)     // Catch:{ all -> 0x003f }
            goto L_0x003d
        L_0x0032:
            com.google.android.gms.internal.ads.zzaiy r4 = com.google.android.gms.ads.internal.zzbv.zzfh()     // Catch:{ all -> 0x003f }
            android.content.Context r1 = r3.zzatx     // Catch:{ all -> 0x003f }
            java.lang.String r2 = r3.zzye     // Catch:{ all -> 0x003f }
            r4.zzc(r1, r2)     // Catch:{ all -> 0x003f }
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaix.zzx(boolean):void");
    }
}
