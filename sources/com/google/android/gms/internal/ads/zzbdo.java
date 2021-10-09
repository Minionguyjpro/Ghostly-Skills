package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzbdo {
    private static final Class<?> zzdyf = zzafq();
    private static final zzbee<?, ?> zzdyg = zzas(false);
    private static final zzbee<?, ?> zzdyh = zzas(true);
    private static final zzbee<?, ?> zzdyi = new zzbeg();

    static <UT, UB> UB zza(int i, int i2, UB ub, zzbee<UT, UB> zzbee) {
        if (ub == null) {
            ub = zzbee.zzagb();
        }
        zzbee.zza(ub, i, (long) i2);
        return ub;
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzbbs<?> zzbbs, UB ub, zzbee<UT, UB> zzbee) {
        if (zzbbs == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzbbs.zzq(intValue) != null) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = zza(i, intValue, ub, zzbee);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (zzbbs.zzq(intValue2) == null) {
                    ub = zza(i, intValue2, ub, zzbee);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static void zza(int i, List<String> list, zzbey zzbey) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zza(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzbey zzbey, zzbdm zzbdm) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zza(i, list, zzbdm);
        }
    }

    public static void zza(int i, List<Double> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzg(i, list, z);
        }
    }

    static <T, FT extends zzbbi<FT>> void zza(zzbbd<FT> zzbbd, T t, T t2) {
        zzbbg<FT> zzm = zzbbd.zzm(t2);
        if (!zzm.isEmpty()) {
            zzbbd.zzn(t).zza(zzm);
        }
    }

    static <T> void zza(zzbcp zzbcp, T t, T t2, long j) {
        zzbek.zza((Object) t, j, zzbcp.zzb(zzbek.zzp(t, j), zzbek.zzp(t2, j)));
    }

    static <T, UT, UB> void zza(zzbee<UT, UB> zzbee, T t, T t2) {
        zzbee.zze(t, zzbee.zzg(zzbee.zzac(t), zzbee.zzac(t2)));
    }

    static int zzaf(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.zzp(zzbci.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.zzp(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzbee<?, ?> zzafn() {
        return zzdyg;
    }

    public static zzbee<?, ?> zzafo() {
        return zzdyh;
    }

    public static zzbee<?, ?> zzafp() {
        return zzdyi;
    }

    private static Class<?> zzafq() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzafr() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static int zzag(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.zzq(zzbci.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.zzq(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzah(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbci) {
            zzbci zzbci = (zzbci) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.zzr(zzbci.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.zzr(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzai(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.zzcj(zzbbp.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.zzcj(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzaj(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.zzce(zzbbp.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.zzce(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzak(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.zzcf(zzbbp.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.zzcf(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzal(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzbbp) {
            zzbbp zzbbp = (zzbbp) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.zzcg(zzbbp.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.zzcg(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzam(List<?> list) {
        return list.size() << 2;
    }

    static int zzan(List<?> list) {
        return list.size() << 3;
    }

    static int zzao(List<?> list) {
        return list.size();
    }

    private static zzbee<?, ?> zzas(boolean z) {
        try {
            Class<?> zzafr = zzafr();
            if (zzafr == null) {
                return null;
            }
            return (zzbee) zzafr.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzb(int i, List<zzbah> list, zzbey zzbey) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzb(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzbey zzbey, zzbdm zzbdm) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzb(i, list, zzbdm);
        }
    }

    public static void zzb(int i, List<Float> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzf(i, list, z);
        }
    }

    static int zzc(int i, Object obj, zzbdm zzbdm) {
        return obj instanceof zzbcb ? zzbav.zza(i, (zzbcb) obj) : zzbav.zzb(i, (zzbcu) obj, zzbdm);
    }

    static int zzc(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzcd = zzbav.zzcd(i) * size;
        if (list instanceof zzbcd) {
            zzbcd zzbcd = (zzbcd) list;
            while (i2 < size) {
                Object zzcp = zzbcd.zzcp(i2);
                zzcd += zzcp instanceof zzbah ? zzbav.zzao((zzbah) zzcp) : zzbav.zzeo((String) zzcp);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                zzcd += obj instanceof zzbah ? zzbav.zzao((zzbah) obj) : zzbav.zzeo((String) obj);
                i2++;
            }
        }
        return zzcd;
    }

    static int zzc(int i, List<?> list, zzbdm zzbdm) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzcd = zzbav.zzcd(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            zzcd += obj instanceof zzbcb ? zzbav.zza((zzbcb) obj) : zzbav.zza((zzbcu) obj, zzbdm);
        }
        return zzcd;
    }

    public static void zzc(int i, List<Long> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzc(i, list, z);
        }
    }

    static int zzd(int i, List<zzbah> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzcd = size * zzbav.zzcd(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzcd += zzbav.zzao(list.get(i2));
        }
        return zzcd;
    }

    static int zzd(int i, List<zzbcu> list, zzbdm zzbdm) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbav.zzc(i, list.get(i3), zzbdm);
        }
        return i2;
    }

    public static void zzd(int i, List<Long> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzd(i, list, z);
        }
    }

    static boolean zzd(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static void zze(int i, List<Long> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzn(i, list, z);
        }
    }

    public static boolean zze(int i, int i2, int i3) {
        if (i2 < 40) {
            return true;
        }
        long j = (long) i3;
        return ((((long) i2) - ((long) i)) + 1) + 9 <= ((2 * j) + 3) + ((j + 3) * 3);
    }

    public static void zzf(int i, List<Long> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zze(i, list, z);
        }
    }

    public static void zzf(Class<?> cls) {
        Class<?> cls2;
        if (!zzbbo.class.isAssignableFrom(cls) && (cls2 = zzdyf) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzg(int i, List<Long> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzbey zzbey, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzbey.zzi(i, list, z);
        }
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzaf(list) + (list.size() * zzbav.zzcd(i));
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzag(list) + (size * zzbav.zzcd(i));
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzah(list) + (size * zzbav.zzcd(i));
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzai(list) + (size * zzbav.zzcd(i));
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzaj(list) + (size * zzbav.zzcd(i));
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzak(list) + (size * zzbav.zzcd(i));
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzal(list) + (size * zzbav.zzcd(i));
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzt(i, 0);
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzg(i, 0);
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.zzg(i, true);
    }
}
