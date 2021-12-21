package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

public final class zzdo extends zzei {
    private static final Object zztn = new Object();
    private static volatile zzbj zzto;
    private zzax zztp = null;

    public zzdo(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2, zzax zzax) {
        super(zzcz, str, str2, zzba, i, 27);
        this.zztp = zzax;
    }

    private final String zzas() {
        try {
            if (this.zzps.zzak() != null) {
                this.zzps.zzak().get();
            }
            zzba zzaj = this.zzps.zzaj();
            if (zzaj == null || zzaj.zzcx == null) {
                return null;
            }
            return zzaj.zzcx;
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzar() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r11 = this;
            com.google.android.gms.internal.ads.zzbj r0 = zzto
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x002b
            com.google.android.gms.internal.ads.zzbj r0 = zzto
            java.lang.String r0 = r0.zzcx
            boolean r0 = com.google.android.gms.internal.ads.zzdg.zzo(r0)
            if (r0 != 0) goto L_0x002b
            com.google.android.gms.internal.ads.zzbj r0 = zzto
            java.lang.String r0 = r0.zzcx
            java.lang.String r3 = "E"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x002b
            com.google.android.gms.internal.ads.zzbj r0 = zzto
            java.lang.String r0 = r0.zzcx
            java.lang.String r3 = "0000000000000000000000000000000000000000000000000000000000000000"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r0 = 0
            goto L_0x002c
        L_0x002b:
            r0 = 1
        L_0x002c:
            if (r0 == 0) goto L_0x00df
            java.lang.Object r0 = zztn
            monitor-enter(r0)
            r3 = 0
            boolean r4 = com.google.android.gms.internal.ads.zzdg.zzo(r3)     // Catch:{ all -> 0x00dc }
            r5 = 4
            r6 = 2
            r7 = 3
            if (r4 != 0) goto L_0x003d
            r4 = 4
            goto L_0x007e
        L_0x003d:
            com.google.android.gms.internal.ads.zzdg.zzo(r3)     // Catch:{ all -> 0x00dc }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x00dc }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x00dc }
            if (r4 == 0) goto L_0x007d
            com.google.android.gms.internal.ads.zzcz r4 = r11.zzps     // Catch:{ all -> 0x00dc }
            boolean r4 = r4.zzah()     // Catch:{ all -> 0x00dc }
            if (r4 == 0) goto L_0x0078
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.zznk.zzbbb     // Catch:{ all -> 0x00dc }
            com.google.android.gms.internal.ads.zzni r8 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x00dc }
            java.lang.Object r4 = r8.zzd(r4)     // Catch:{ all -> 0x00dc }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x00dc }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x00dc }
            if (r4 == 0) goto L_0x0078
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.zznk.zzbbc     // Catch:{ all -> 0x00dc }
            com.google.android.gms.internal.ads.zzni r8 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x00dc }
            java.lang.Object r4 = r8.zzd(r4)     // Catch:{ all -> 0x00dc }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x00dc }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x00dc }
            if (r4 == 0) goto L_0x0078
            r4 = 1
            goto L_0x0079
        L_0x0078:
            r4 = 0
        L_0x0079:
            if (r4 == 0) goto L_0x007d
            r4 = 3
            goto L_0x007e
        L_0x007d:
            r4 = 2
        L_0x007e:
            java.lang.reflect.Method r8 = r11.zztz     // Catch:{ all -> 0x00dc }
            java.lang.Object[] r9 = new java.lang.Object[r7]     // Catch:{ all -> 0x00dc }
            com.google.android.gms.internal.ads.zzcz r10 = r11.zzps     // Catch:{ all -> 0x00dc }
            android.content.Context r10 = r10.getContext()     // Catch:{ all -> 0x00dc }
            r9[r2] = r10     // Catch:{ all -> 0x00dc }
            if (r4 != r6) goto L_0x008d
            r2 = 1
        L_0x008d:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x00dc }
            r9[r1] = r2     // Catch:{ all -> 0x00dc }
            com.google.android.gms.internal.ads.zzna<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zznk.zzbav     // Catch:{ all -> 0x00dc }
            com.google.android.gms.internal.ads.zzni r2 = com.google.android.gms.internal.ads.zzkb.zzik()     // Catch:{ all -> 0x00dc }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ all -> 0x00dc }
            r9[r6] = r1     // Catch:{ all -> 0x00dc }
            java.lang.Object r1 = r8.invoke(r3, r9)     // Catch:{ all -> 0x00dc }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x00dc }
            com.google.android.gms.internal.ads.zzbj r2 = new com.google.android.gms.internal.ads.zzbj     // Catch:{ all -> 0x00dc }
            r2.<init>(r1)     // Catch:{ all -> 0x00dc }
            zzto = r2     // Catch:{ all -> 0x00dc }
            java.lang.String r1 = r2.zzcx     // Catch:{ all -> 0x00dc }
            boolean r1 = com.google.android.gms.internal.ads.zzdg.zzo(r1)     // Catch:{ all -> 0x00dc }
            if (r1 != 0) goto L_0x00c0
            com.google.android.gms.internal.ads.zzbj r1 = zzto     // Catch:{ all -> 0x00dc }
            java.lang.String r1 = r1.zzcx     // Catch:{ all -> 0x00dc }
            java.lang.String r2 = "E"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x00dc }
            if (r1 == 0) goto L_0x00da
        L_0x00c0:
            if (r4 == r7) goto L_0x00cc
            if (r4 == r5) goto L_0x00c5
            goto L_0x00da
        L_0x00c5:
            com.google.android.gms.internal.ads.zzbj r1 = zzto     // Catch:{ all -> 0x00dc }
            java.lang.String r2 = r3.zzcx     // Catch:{ all -> 0x00dc }
            r1.zzcx = r2     // Catch:{ all -> 0x00dc }
            goto L_0x00da
        L_0x00cc:
            java.lang.String r1 = r11.zzas()     // Catch:{ all -> 0x00dc }
            boolean r2 = com.google.android.gms.internal.ads.zzdg.zzo(r1)     // Catch:{ all -> 0x00dc }
            if (r2 != 0) goto L_0x00da
            com.google.android.gms.internal.ads.zzbj r2 = zzto     // Catch:{ all -> 0x00dc }
            r2.zzcx = r1     // Catch:{ all -> 0x00dc }
        L_0x00da:
            monitor-exit(r0)     // Catch:{ all -> 0x00dc }
            goto L_0x00df
        L_0x00dc:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00dc }
            throw r1
        L_0x00df:
            com.google.android.gms.internal.ads.zzba r0 = r11.zztq
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzbj r1 = zzto     // Catch:{ all -> 0x0114 }
            if (r1 == 0) goto L_0x0112
            com.google.android.gms.internal.ads.zzba r1 = r11.zztq     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.ads.zzbj r2 = zzto     // Catch:{ all -> 0x0114 }
            java.lang.String r2 = r2.zzcx     // Catch:{ all -> 0x0114 }
            r1.zzcx = r2     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.ads.zzba r1 = r11.zztq     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.ads.zzbj r2 = zzto     // Catch:{ all -> 0x0114 }
            long r2 = r2.zzhx     // Catch:{ all -> 0x0114 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0114 }
            r1.zzeb = r2     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.ads.zzba r1 = r11.zztq     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.ads.zzbj r2 = zzto     // Catch:{ all -> 0x0114 }
            java.lang.String r2 = r2.zzcz     // Catch:{ all -> 0x0114 }
            r1.zzcz = r2     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.ads.zzba r1 = r11.zztq     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.ads.zzbj r2 = zzto     // Catch:{ all -> 0x0114 }
            java.lang.String r2 = r2.zzda     // Catch:{ all -> 0x0114 }
            r1.zzda = r2     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.ads.zzba r1 = r11.zztq     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.ads.zzbj r2 = zzto     // Catch:{ all -> 0x0114 }
            java.lang.String r2 = r2.zzdb     // Catch:{ all -> 0x0114 }
            r1.zzdb = r2     // Catch:{ all -> 0x0114 }
        L_0x0112:
            monitor-exit(r0)     // Catch:{ all -> 0x0114 }
            return
        L_0x0114:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0114 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdo.zzar():void");
    }
}
