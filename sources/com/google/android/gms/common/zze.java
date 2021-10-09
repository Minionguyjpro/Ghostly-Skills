package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
final /* synthetic */ class zze implements Callable {
    private final boolean zza;
    private final String zzb;
    private final zzd zzc;

    zze(boolean z, String str, zzd zzd) {
        this.zza = z;
        this.zzb = str;
        this.zzc = zzd;
    }

    public final Object call() {
        return zzc.zza(this.zza, this.zzb, this.zzc);
    }
}
