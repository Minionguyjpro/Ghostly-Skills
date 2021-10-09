package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgg {
    private final Object zzaho = new Object();
    private zzgh zzahp = null;
    private boolean zzahq = false;

    public final Activity getActivity() {
        synchronized (this.zzaho) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            }
            if (this.zzahp == null) {
                return null;
            }
            Activity activity = this.zzahp.getActivity();
            return activity;
        }
    }

    public final Context getContext() {
        synchronized (this.zzaho) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            }
            if (this.zzahp == null) {
                return null;
            }
            Context context = this.zzahp.getContext();
            return context;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzaho
            monitor-enter(r0)
            boolean r1 = r4.zzahq     // Catch:{ all -> 0x0050 }
            if (r1 != 0) goto L_0x004e
            boolean r1 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich()     // Catch:{ all -> 0x0050 }
            if (r1 != 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x000f:
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zznk.zzayg     // Catch:{ all -> 0x0050 }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x0050 }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x0050 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r1 != 0) goto L_0x0023
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x0023:
            r1 = 0
            android.content.Context r2 = r5.getApplicationContext()     // Catch:{ all -> 0x0050 }
            if (r2 != 0) goto L_0x002b
            r2 = r5
        L_0x002b:
            boolean r3 = r2 instanceof android.app.Application     // Catch:{ all -> 0x0050 }
            if (r3 == 0) goto L_0x0032
            r1 = r2
            android.app.Application r1 = (android.app.Application) r1     // Catch:{ all -> 0x0050 }
        L_0x0032:
            if (r1 != 0) goto L_0x003b
            java.lang.String r5 = "Can not cast Context to Application"
            com.google.android.gms.internal.ads.zzakb.zzdk(r5)     // Catch:{ all -> 0x0050 }
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x003b:
            com.google.android.gms.internal.ads.zzgh r2 = r4.zzahp     // Catch:{ all -> 0x0050 }
            if (r2 != 0) goto L_0x0046
            com.google.android.gms.internal.ads.zzgh r2 = new com.google.android.gms.internal.ads.zzgh     // Catch:{ all -> 0x0050 }
            r2.<init>()     // Catch:{ all -> 0x0050 }
            r4.zzahp = r2     // Catch:{ all -> 0x0050 }
        L_0x0046:
            com.google.android.gms.internal.ads.zzgh r2 = r4.zzahp     // Catch:{ all -> 0x0050 }
            r2.zza((android.app.Application) r1, (android.content.Context) r5)     // Catch:{ all -> 0x0050 }
            r5 = 1
            r4.zzahq = r5     // Catch:{ all -> 0x0050 }
        L_0x004e:
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x0050:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgg.initialize(android.content.Context):void");
    }

    public final void zza(zzgj zzgj) {
        synchronized (this.zzaho) {
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzayg)).booleanValue()) {
                    if (this.zzahp == null) {
                        this.zzahp = new zzgh();
                    }
                    this.zzahp.zza(zzgj);
                }
            }
        }
    }
}
