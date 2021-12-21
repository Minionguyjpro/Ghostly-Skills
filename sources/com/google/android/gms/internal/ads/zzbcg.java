package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

final class zzbcg extends zzbce {
    private static final Class<?> zzdvs = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzbcg() {
        super();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: com.google.android.gms.internal.ads.zzbcc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.google.android.gms.internal.ads.zzbcc} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.google.android.gms.internal.ads.zzbcc} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <L> java.util.List<L> zza(java.lang.Object r3, long r4, int r6) {
        /*
            java.util.List r0 = zzc(r3, r4)
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x001d
            boolean r0 = r0 instanceof com.google.android.gms.internal.ads.zzbcd
            if (r0 == 0) goto L_0x0014
            com.google.android.gms.internal.ads.zzbcc r0 = new com.google.android.gms.internal.ads.zzbcc
            r0.<init>((int) r6)
            goto L_0x0019
        L_0x0014:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r6)
        L_0x0019:
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r3, (long) r4, (java.lang.Object) r0)
            goto L_0x004f
        L_0x001d:
            java.lang.Class<?> r1 = zzdvs
            java.lang.Class r2 = r0.getClass()
            boolean r1 = r1.isAssignableFrom(r2)
            if (r1 == 0) goto L_0x003b
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.size()
            int r2 = r2 + r6
            r1.<init>(r2)
            r1.addAll(r0)
        L_0x0036:
            com.google.android.gms.internal.ads.zzbek.zza((java.lang.Object) r3, (long) r4, (java.lang.Object) r1)
            r0 = r1
            goto L_0x004f
        L_0x003b:
            boolean r1 = r0 instanceof com.google.android.gms.internal.ads.zzbeh
            if (r1 == 0) goto L_0x004f
            com.google.android.gms.internal.ads.zzbcc r1 = new com.google.android.gms.internal.ads.zzbcc
            int r2 = r0.size()
            int r2 = r2 + r6
            r1.<init>((int) r2)
            com.google.android.gms.internal.ads.zzbeh r0 = (com.google.android.gms.internal.ads.zzbeh) r0
            r1.addAll(r0)
            goto L_0x0036
        L_0x004f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcg.zza(java.lang.Object, long, int):java.util.List");
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzbek.zzp(obj, j);
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        List zzc = zzc(obj2, j);
        List zza = zza(obj, j, zzc.size());
        int size = zza.size();
        int size2 = zzc.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(zzc);
        }
        if (size > 0) {
            zzc = zza;
        }
        zzbek.zza(obj, j, (Object) zzc);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzbek.zzp(obj, j);
        if (list instanceof zzbcd) {
            obj2 = ((zzbcd) list).zzadx();
        } else if (!zzdvs.isAssignableFrom(list.getClass())) {
            obj2 = Collections.unmodifiableList(list);
        } else {
            return;
        }
        zzbek.zza(obj, j, obj2);
    }
}
