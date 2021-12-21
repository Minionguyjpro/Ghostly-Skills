package com.google.android.gms.internal.common;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public final class zze {
    private static final zzf zza;
    private static volatile zzf zzb;

    public static zzf zza() {
        return zzb;
    }

    static {
        zzg zzg = new zzg();
        zza = zzg;
        zzb = zzg;
    }
}
