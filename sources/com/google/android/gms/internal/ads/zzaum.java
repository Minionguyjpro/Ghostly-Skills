package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class zzaum<P> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private ConcurrentMap<String, List<zzaun<P>>> zzdhk = new ConcurrentHashMap();
    private zzaun<P> zzdhl;

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0070  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzaun<P> zza(P r6, com.google.android.gms.internal.ads.zzaxr.zzb r7) throws java.security.GeneralSecurityException {
        /*
            r5 = this;
            com.google.android.gms.internal.ads.zzaun r0 = new com.google.android.gms.internal.ads.zzaun
            int[] r1 = com.google.android.gms.internal.ads.zzaud.zzdhh
            com.google.android.gms.internal.ads.zzayd r2 = r7.zzzs()
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 5
            r3 = 1
            if (r1 == r3) goto L_0x002f
            r4 = 2
            if (r1 == r4) goto L_0x002f
            r4 = 3
            if (r1 == r4) goto L_0x0026
            r2 = 4
            if (r1 != r2) goto L_0x001e
            byte[] r1 = com.google.android.gms.internal.ads.zzauc.zzdhg
            goto L_0x0044
        L_0x001e:
            java.security.GeneralSecurityException r6 = new java.security.GeneralSecurityException
            java.lang.String r7 = "unknown output prefix type"
            r6.<init>(r7)
            throw r6
        L_0x0026:
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r2)
            java.nio.ByteBuffer r1 = r1.put(r3)
            goto L_0x0038
        L_0x002f:
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r2)
            r2 = 0
            java.nio.ByteBuffer r1 = r1.put(r2)
        L_0x0038:
            int r2 = r7.zzzr()
            java.nio.ByteBuffer r1 = r1.putInt(r2)
            byte[] r1 = r1.array()
        L_0x0044:
            com.google.android.gms.internal.ads.zzaxl r2 = r7.zzzq()
            com.google.android.gms.internal.ads.zzayd r7 = r7.zzzs()
            r0.<init>(r6, r1, r2, r7)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r6.add(r0)
            java.lang.String r7 = new java.lang.String
            byte[] r1 = r0.zzwj()
            java.nio.charset.Charset r2 = UTF_8
            r7.<init>(r1, r2)
            java.util.concurrent.ConcurrentMap<java.lang.String, java.util.List<com.google.android.gms.internal.ads.zzaun<P>>> r1 = r5.zzdhk
            java.util.List r6 = java.util.Collections.unmodifiableList(r6)
            java.lang.Object r6 = r1.put(r7, r6)
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x0084
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r1.addAll(r6)
            r1.add(r0)
            java.util.concurrent.ConcurrentMap<java.lang.String, java.util.List<com.google.android.gms.internal.ads.zzaun<P>>> r6 = r5.zzdhk
            java.util.List r1 = java.util.Collections.unmodifiableList(r1)
            r6.put(r7, r1)
        L_0x0084:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaum.zza(java.lang.Object, com.google.android.gms.internal.ads.zzaxr$zzb):com.google.android.gms.internal.ads.zzaun");
    }

    /* access modifiers changed from: protected */
    public final void zza(zzaun<P> zzaun) {
        this.zzdhl = zzaun;
    }

    public final zzaun<P> zzwh() {
        return this.zzdhl;
    }
}
