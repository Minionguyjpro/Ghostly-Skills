package com.google.android.gms.internal.ads;

import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzxa extends zzxu {
    private final Object mLock = new Object();
    private zzxf zzbtf;
    private zzwz zzbtg;

    public final void onAdClicked() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzce();
            }
        }
    }

    public final void onAdClosed() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcf();
            }
        }
    }

    public final void onAdFailedToLoad(int i) {
        synchronized (this.mLock) {
            if (this.zzbtf != null) {
                this.zzbtf.zzx(i == 3 ? 1 : 2);
                this.zzbtf = null;
            }
        }
    }

    public final void onAdImpression() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcj();
            }
        }
    }

    public final void onAdLeftApplication() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcg();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onAdLoaded() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzxf r1 = r3.zzbtf     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x0012
            com.google.android.gms.internal.ads.zzxf r1 = r3.zzbtf     // Catch:{ all -> 0x001d }
            r2 = 0
            r1.zzx(r2)     // Catch:{ all -> 0x001d }
            r1 = 0
            r3.zzbtf = r1     // Catch:{ all -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x0012:
            com.google.android.gms.internal.ads.zzwz r1 = r3.zzbtg     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x001b
            com.google.android.gms.internal.ads.zzwz r1 = r3.zzbtg     // Catch:{ all -> 0x001d }
            r1.zzci()     // Catch:{ all -> 0x001d }
        L_0x001b:
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x001d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxa.onAdLoaded():void");
    }

    public final void onAdOpened() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzch();
            }
        }
    }

    public final void onAppEvent(String str, String str2) {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzb(str, str2);
            }
        }
    }

    public final void onVideoEnd() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcd();
            }
        }
    }

    public final void zza(zzwz zzwz) {
        synchronized (this.mLock) {
            this.zzbtg = zzwz;
        }
    }

    public final void zza(zzxf zzxf) {
        synchronized (this.mLock) {
            this.zzbtf = zzxf;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzxw r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzxf r1 = r3.zzbtf     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x0012
            com.google.android.gms.internal.ads.zzxf r1 = r3.zzbtf     // Catch:{ all -> 0x001d }
            r2 = 0
            r1.zza(r2, r4)     // Catch:{ all -> 0x001d }
            r4 = 0
            r3.zzbtf = r4     // Catch:{ all -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x0012:
            com.google.android.gms.internal.ads.zzwz r4 = r3.zzbtg     // Catch:{ all -> 0x001d }
            if (r4 == 0) goto L_0x001b
            com.google.android.gms.internal.ads.zzwz r4 = r3.zzbtg     // Catch:{ all -> 0x001d }
            r4.zzci()     // Catch:{ all -> 0x001d }
        L_0x001b:
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x001d:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxa.zza(com.google.android.gms.internal.ads.zzxw):void");
    }

    public final void zzb(zzqs zzqs, String str) {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zza(zzqs, str);
            }
        }
    }

    public final void zzbj(String str) {
    }
}
