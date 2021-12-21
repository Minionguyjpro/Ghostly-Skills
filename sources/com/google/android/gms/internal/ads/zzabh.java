package com.google.android.gms.internal.ads;

import android.content.Context;

@zzadh
public abstract class zzabh extends zzajx {
    protected final Context mContext;
    protected final Object mLock = new Object();
    protected final zzabm zzbzd;
    protected final zzaji zzbze;
    protected zzaej zzbzf;
    protected final Object zzbzh = new Object();

    protected zzabh(Context context, zzaji zzaji, zzabm zzabm) {
        super(true);
        this.mContext = context;
        this.zzbze = zzaji;
        this.zzbzf = zzaji.zzcos;
        this.zzbzd = zzabm;
    }

    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public abstract zzajh zzaa(int i);

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033 A[Catch:{ zzabk -> 0x0014 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003b A[Catch:{ zzabk -> 0x0014 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdn() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            java.lang.String r1 = "AdRendererBackgroundTask started."
            com.google.android.gms.internal.ads.zzakb.zzck(r1)     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzaji r1 = r5.zzbze     // Catch:{ all -> 0x0060 }
            int r1 = r1.errorCode     // Catch:{ all -> 0x0060 }
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ zzabk -> 0x0014 }
            r5.zze(r2)     // Catch:{ zzabk -> 0x0014 }
            goto L_0x0050
        L_0x0014:
            r1 = move-exception
            int r2 = r1.getErrorCode()     // Catch:{ all -> 0x0060 }
            r3 = 3
            if (r2 == r3) goto L_0x0028
            r3 = -1
            if (r2 != r3) goto L_0x0020
            goto L_0x0028
        L_0x0020:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzakb.zzdk(r1)     // Catch:{ all -> 0x0060 }
            goto L_0x002f
        L_0x0028:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzakb.zzdj(r1)     // Catch:{ all -> 0x0060 }
        L_0x002f:
            com.google.android.gms.internal.ads.zzaej r1 = r5.zzbzf     // Catch:{ all -> 0x0060 }
            if (r1 != 0) goto L_0x003b
            com.google.android.gms.internal.ads.zzaej r1 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ all -> 0x0060 }
            r1.<init>(r2)     // Catch:{ all -> 0x0060 }
        L_0x0038:
            r5.zzbzf = r1     // Catch:{ all -> 0x0060 }
            goto L_0x0045
        L_0x003b:
            com.google.android.gms.internal.ads.zzaej r1 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzaej r3 = r5.zzbzf     // Catch:{ all -> 0x0060 }
            long r3 = r3.zzbsu     // Catch:{ all -> 0x0060 }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0060 }
            goto L_0x0038
        L_0x0045:
            android.os.Handler r1 = com.google.android.gms.internal.ads.zzakk.zzcrm     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzabi r3 = new com.google.android.gms.internal.ads.zzabi     // Catch:{ all -> 0x0060 }
            r3.<init>(r5)     // Catch:{ all -> 0x0060 }
            r1.post(r3)     // Catch:{ all -> 0x0060 }
            r1 = r2
        L_0x0050:
            com.google.android.gms.internal.ads.zzajh r1 = r5.zzaa(r1)     // Catch:{ all -> 0x0060 }
            android.os.Handler r2 = com.google.android.gms.internal.ads.zzakk.zzcrm     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzabj r3 = new com.google.android.gms.internal.ads.zzabj     // Catch:{ all -> 0x0060 }
            r3.<init>(r5, r1)     // Catch:{ all -> 0x0060 }
            r2.post(r3)     // Catch:{ all -> 0x0060 }
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return
        L_0x0060:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            goto L_0x0064
        L_0x0063:
            throw r1
        L_0x0064:
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabh.zzdn():void");
    }

    /* access modifiers changed from: protected */
    public abstract void zze(long j) throws zzabk;
}
