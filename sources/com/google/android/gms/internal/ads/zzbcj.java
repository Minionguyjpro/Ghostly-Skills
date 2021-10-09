package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo;

final class zzbcj implements zzbdn {
    private static final zzbct zzdvw = new zzbck();
    private final zzbct zzdvv;

    public zzbcj() {
        this(new zzbcl(zzbbn.zzadc(), zzaea()));
    }

    private zzbcj(zzbct zzbct) {
        this.zzdvv = (zzbct) zzbbq.zza(zzbct, "messageInfoFactory");
    }

    private static boolean zza(zzbcs zzbcs) {
        return zzbcs.zzaeh() == zzbbo.zze.zzdui;
    }

    private static zzbct zzaea() {
        try {
            return (zzbct) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzdvw;
        }
    }

    public final <T> zzbdm<T> zzd(Class<T> cls) {
        zzbdo.zzf(cls);
        zzbcs zzb = this.zzdvv.zzb(cls);
        if (zzb.zzaei()) {
            return zzbbo.class.isAssignableFrom(cls) ? zzbda.zza(zzbdo.zzafp(), zzbbf.zzact(), zzb.zzaej()) : zzbda.zza(zzbdo.zzafn(), zzbbf.zzacu(), zzb.zzaej());
        }
        if (zzbbo.class.isAssignableFrom(cls)) {
            boolean zza = zza(zzb);
            zzbdc zzaem = zzbde.zzaem();
            zzbce zzadz = zzbce.zzadz();
            zzbee<?, ?> zzafp = zzbdo.zzafp();
            if (zza) {
                return zzbcy.zza(cls, zzb, zzaem, zzadz, zzafp, zzbbf.zzact(), zzbcr.zzaef());
            }
            return zzbcy.zza(cls, zzb, zzaem, zzadz, zzafp, (zzbbd<?>) null, zzbcr.zzaef());
        }
        boolean zza2 = zza(zzb);
        zzbdc zzael = zzbde.zzael();
        zzbce zzady = zzbce.zzady();
        if (zza2) {
            return zzbcy.zza(cls, zzb, zzael, zzady, zzbdo.zzafn(), zzbbf.zzacu(), zzbcr.zzaee());
        }
        return zzbcy.zza(cls, zzb, zzael, zzady, zzbdo.zzafo(), (zzbbd<?>) null, zzbcr.zzaee());
    }
}
