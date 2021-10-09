package com.google.android.gms.internal.ads;

import java.io.IOException;

abstract class zzbee<T, B> {
    zzbee() {
    }

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, zzbah zzbah);

    /* access modifiers changed from: package-private */
    public abstract void zza(B b, int i, T t);

    /* access modifiers changed from: package-private */
    public abstract void zza(T t, zzbey zzbey) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract boolean zza(zzbdl zzbdl);

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002f A[LOOP:0: B:16:0x002f->B:19:0x003c, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(B r7, com.google.android.gms.internal.ads.zzbdl r8) throws java.io.IOException {
        /*
            r6 = this;
            int r0 = r8.getTag()
            int r1 = r0 >>> 3
            r0 = r0 & 7
            r2 = 1
            if (r0 == 0) goto L_0x0061
            if (r0 == r2) goto L_0x0059
            r3 = 2
            if (r0 == r3) goto L_0x0051
            r3 = 4
            r4 = 3
            if (r0 == r4) goto L_0x0028
            if (r0 == r3) goto L_0x0026
            r3 = 5
            if (r0 != r3) goto L_0x0021
            int r8 = r8.zzabp()
            r6.zzc(r7, r1, r8)
            return r2
        L_0x0021:
            com.google.android.gms.internal.ads.zzbbv r7 = com.google.android.gms.internal.ads.zzbbu.zzadq()
            throw r7
        L_0x0026:
            r7 = 0
            return r7
        L_0x0028:
            java.lang.Object r0 = r6.zzagb()
            int r4 = r1 << 3
            r3 = r3 | r4
        L_0x002f:
            int r4 = r8.zzaci()
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x003e
            boolean r4 = r6.zza(r0, (com.google.android.gms.internal.ads.zzbdl) r8)
            if (r4 != 0) goto L_0x002f
        L_0x003e:
            int r8 = r8.getTag()
            if (r3 != r8) goto L_0x004c
            java.lang.Object r8 = r6.zzv(r0)
            r6.zza(r7, (int) r1, r8)
            return r2
        L_0x004c:
            com.google.android.gms.internal.ads.zzbbu r7 = com.google.android.gms.internal.ads.zzbbu.zzadp()
            throw r7
        L_0x0051:
            com.google.android.gms.internal.ads.zzbah r8 = r8.zzabs()
            r6.zza(r7, (int) r1, (com.google.android.gms.internal.ads.zzbah) r8)
            return r2
        L_0x0059:
            long r3 = r8.zzabo()
            r6.zzb(r7, r1, r3)
            return r2
        L_0x0061:
            long r3 = r8.zzabm()
            r6.zza(r7, (int) r1, (long) r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbee.zza(java.lang.Object, com.google.android.gms.internal.ads.zzbdl):boolean");
    }

    /* access modifiers changed from: package-private */
    public abstract T zzac(Object obj);

    /* access modifiers changed from: package-private */
    public abstract B zzad(Object obj);

    /* access modifiers changed from: package-private */
    public abstract int zzae(T t);

    /* access modifiers changed from: package-private */
    public abstract B zzagb();

    /* access modifiers changed from: package-private */
    public abstract void zzb(B b, int i, long j);

    /* access modifiers changed from: package-private */
    public abstract void zzc(B b, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zzc(T t, zzbey zzbey) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zze(Object obj, T t);

    /* access modifiers changed from: package-private */
    public abstract void zzf(Object obj, B b);

    /* access modifiers changed from: package-private */
    public abstract T zzg(T t, T t2);

    /* access modifiers changed from: package-private */
    public abstract void zzo(Object obj);

    /* access modifiers changed from: package-private */
    public abstract T zzv(B b);

    /* access modifiers changed from: package-private */
    public abstract int zzy(T t);
}
