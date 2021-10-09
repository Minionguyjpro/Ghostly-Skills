package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzalv implements zzanj<Throwable, T> {
    private final /* synthetic */ zzalz zzcti;

    zzalv(zzalt zzalt, zzalz zzalz) {
        this.zzcti = zzalz;
    }

    public final /* synthetic */ zzanz zzc(Object obj) throws Exception {
        Throwable th = (Throwable) obj;
        zzakb.zzb("Error occurred while dispatching http response in getter.", th);
        zzbv.zzeo().zza(th, "HttpGetter.deliverResponse.1");
        return zzano.zzi(this.zzcti.zzny());
    }
}
