package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;
import java.io.IOException;
import java.util.List;
import java.util.Map;

final class zzbax implements zzbey {
    private final zzbav zzdpv;

    private zzbax(zzbav zzbav) {
        zzbav zzbav2 = (zzbav) zzbbq.zza(zzbav, "output");
        this.zzdpv = zzbav2;
        zzbav2.zzdqn = this;
    }

    public static zzbax zza(zzbav zzbav) {
        return zzbav.zzdqn != null ? zzbav.zzdqn : new zzbax(zzbav);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzdpv.zza(i, d);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzdpv.zza(i, f);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzdpv.zza(i, j);
    }

    public final void zza(int i, zzbah zzbah) throws IOException {
        this.zzdpv.zza(i, zzbah);
    }

    public final <K, V> void zza(int i, zzbcn<K, V> zzbcn, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            this.zzdpv.zzl(i, 2);
            this.zzdpv.zzca(zzbcm.zza(zzbcn, next.getKey(), next.getValue()));
            zzbcm.zza(this.zzdpv, zzbcn, next.getKey(), next.getValue());
        }
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzbah) {
            this.zzdpv.zzb(i, (zzbah) obj);
        } else {
            this.zzdpv.zza(i, (zzbcu) obj);
        }
    }

    public final void zza(int i, Object obj, zzbdm zzbdm) throws IOException {
        this.zzdpv.zza(i, (zzbcu) obj, zzbdm);
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzbcd) {
            zzbcd zzbcd = (zzbcd) list;
            while (i2 < list.size()) {
                Object zzcp = zzbcd.zzcp(i2);
                if (zzcp instanceof String) {
                    this.zzdpv.zzf(i, (String) zzcp);
                } else {
                    this.zzdpv.zza(i, (zzbah) zzcp);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzf(i, list.get(i2));
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzbdm zzbdm) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, (Object) list.get(i2), zzbdm);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzce(list.get(i4).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzbz(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzm(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final int zzacn() {
        return zzbbo.zze.zzdul;
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzdpv.zzb(i, j);
    }

    public final void zzb(int i, Object obj, zzbdm zzbdm) throws IOException {
        zzbav zzbav = this.zzdpv;
        zzbav.zzl(i, 3);
        zzbdm.zza((zzbcu) obj, zzbav.zzdqn);
        zzbav.zzl(i, 4);
    }

    public final void zzb(int i, List<zzbah> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzdpv.zza(i, list.get(i2));
        }
    }

    public final void zzb(int i, List<?> list, zzbdm zzbdm) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzbdm);
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzch(list.get(i4).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzcc(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzp(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzdpv.zzc(i, j);
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzp(list.get(i4).longValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzm(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzcm(int i) throws IOException {
        this.zzdpv.zzl(i, 3);
    }

    public final void zzcn(int i) throws IOException {
        this.zzdpv.zzl(i, 4);
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzq(list.get(i4).longValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzm(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzs(list.get(i4).longValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzo(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzf(int i, String str) throws IOException {
        this.zzdpv.zzf(i, str);
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzc(list.get(i4).floatValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzb(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    public final void zzf(int i, boolean z) throws IOException {
        this.zzdpv.zzf(i, z);
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzc(list.get(i4).doubleValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzb(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzcj(list.get(i4).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzbz(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzm(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzdpv.zza(i, j);
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzaq(list.get(i4).booleanValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzap(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzf(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzdpv.zzc(i, j);
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzcf(list.get(i4).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzca(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzn(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzci(list.get(i4).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzcc(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzp(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzt(list.get(i4).longValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzo(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzm(int i, int i2) throws IOException {
        this.zzdpv.zzm(i, i2);
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzcg(list.get(i4).intValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzcb(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzo(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzn(int i, int i2) throws IOException {
        this.zzdpv.zzn(i, i2);
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzdpv.zzl(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzbav.zzr(list.get(i4).longValue());
            }
            this.zzdpv.zzca(i3);
            while (i2 < list.size()) {
                this.zzdpv.zzn(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzdpv.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzo(int i, int i2) throws IOException {
        this.zzdpv.zzo(i, i2);
    }

    public final void zzp(int i, int i2) throws IOException {
        this.zzdpv.zzp(i, i2);
    }

    public final void zzw(int i, int i2) throws IOException {
        this.zzdpv.zzp(i, i2);
    }

    public final void zzx(int i, int i2) throws IOException {
        this.zzdpv.zzm(i, i2);
    }
}
