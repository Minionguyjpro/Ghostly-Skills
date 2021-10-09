package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzhd {
    private Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final Runnable zzajq = new zzhe(this);
    /* access modifiers changed from: private */
    public zzhk zzajr;
    /* access modifiers changed from: private */
    public zzho zzajs;

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void connect() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            android.content.Context r1 = r6.mContext     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x002c
            com.google.android.gms.internal.ads.zzhk r1 = r6.zzajr     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x000c
            goto L_0x002c
        L_0x000c:
            com.google.android.gms.internal.ads.zzhg r1 = new com.google.android.gms.internal.ads.zzhg     // Catch:{ all -> 0x002e }
            r1.<init>(r6)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.ads.zzhh r2 = new com.google.android.gms.internal.ads.zzhh     // Catch:{ all -> 0x002e }
            r2.<init>(r6)     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.ads.zzhk r3 = new com.google.android.gms.internal.ads.zzhk     // Catch:{ all -> 0x002e }
            android.content.Context r4 = r6.mContext     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.ads.zzamg r5 = com.google.android.gms.ads.internal.zzbv.zzez()     // Catch:{ all -> 0x002e }
            android.os.Looper r5 = r5.zzsa()     // Catch:{ all -> 0x002e }
            r3.<init>(r4, r5, r1, r2)     // Catch:{ all -> 0x002e }
            r6.zzajr = r3     // Catch:{ all -> 0x002e }
            r3.checkAvailabilityAndConnect()     // Catch:{ all -> 0x002e }
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x002e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhd.connect():void");
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzajr != null) {
                if (this.zzajr.isConnected() || this.zzajr.isConnecting()) {
                    this.zzajr.disconnect();
                }
                this.zzajr = null;
                this.zzajs = null;
                Binder.flushPendingCommands();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            android.content.Context r1 = r2.mContext     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x000c:
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ all -> 0x0048 }
            r2.mContext = r3     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zznk.zzbdo     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0048 }
            java.lang.Object r3 = r1.zzd(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0028
            r2.connect()     // Catch:{ all -> 0x0048 }
            goto L_0x0046
        L_0x0028:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zznk.zzbdn     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzni r1 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0048 }
            java.lang.Object r3 = r1.zzd(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0046
            com.google.android.gms.internal.ads.zzhf r3 = new com.google.android.gms.internal.ads.zzhf     // Catch:{ all -> 0x0048 }
            r3.<init>(r2)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzgg r1 = com.google.android.gms.ads.internal.zzbv.zzen()     // Catch:{ all -> 0x0048 }
            r1.zza(r3)     // Catch:{ all -> 0x0048 }
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x0048:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhd.initialize(android.content.Context):void");
    }

    public final zzhi zza(zzhl zzhl) {
        synchronized (this.mLock) {
            if (this.zzajs == null) {
                zzhi zzhi = new zzhi();
                return zzhi;
            }
            try {
                zzhi zza = this.zzajs.zza(zzhl);
                return zza;
            } catch (RemoteException e) {
                zzakb.zzb("Unable to call into cache service.", e);
                return new zzhi();
            }
        }
    }

    public final void zzhh() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbdp)).booleanValue()) {
            synchronized (this.mLock) {
                connect();
                zzbv.zzek();
                zzakk.zzcrm.removeCallbacks(this.zzajq);
                zzbv.zzek();
                zzakk.zzcrm.postDelayed(this.zzajq, ((Long) zzkb.zzik().zzd(zznk.zzbdq)).longValue());
            }
        }
    }
}
