package com.google.android.gms.internal.ads;

final class zzbcl implements zzbct {
    private zzbct[] zzdvx;

    zzbcl(zzbct... zzbctArr) {
        this.zzdvx = zzbctArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzbct zza : this.zzdvx) {
            if (zza.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzbcs zzb(Class<?> cls) {
        for (zzbct zzbct : this.zzdvx) {
            if (zzbct.zza(cls)) {
                return zzbct.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
