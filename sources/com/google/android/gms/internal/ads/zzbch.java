package com.google.android.gms.internal.ads;

import java.util.List;

final class zzbch extends zzbce {
    private zzbch() {
        super();
    }

    private static <E> zzbbt<E> zzd(Object obj, long j) {
        return (zzbbt) zzbek.zzp(obj, j);
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        zzbbt zzd = zzd(obj, j);
        if (zzd.zzaay()) {
            return zzd;
        }
        int size = zzd.size();
        zzbbt zzbm = zzd.zzbm(size == 0 ? 10 : size << 1);
        zzbek.zza(obj, j, (Object) zzbm);
        return zzbm;
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzbbt zzd = zzd(obj, j);
        zzbbt zzd2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = zzd2.size();
        if (size > 0 && size2 > 0) {
            if (!zzd.zzaay()) {
                zzd = zzd.zzbm(size2 + size);
            }
            zzd.addAll(zzd2);
        }
        if (size > 0) {
            zzd2 = zzd;
        }
        zzbek.zza(obj, j, (Object) zzd2);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        zzd(obj, j).zzaaz();
    }
}
