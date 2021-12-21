package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzjr;

final class zzjv extends zzjr.zza<zzkn> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzarr;
    private final /* synthetic */ zzxn zzars;
    private final /* synthetic */ zzjr zzart;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjv(zzjr zzjr, Context context, String str, zzxn zzxn) {
        super();
        this.zzart = zzjr;
        this.val$context = context;
        this.zzarr = str;
        this.zzars = zzxn;
    }

    public final /* synthetic */ Object zza(zzld zzld) throws RemoteException {
        return zzld.createAdLoaderBuilder(ObjectWrapper.wrap(this.val$context), this.zzarr, this.zzars, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzkn zza = this.zzart.zzark.zza(this.val$context, this.zzarr, this.zzars);
        if (zza != null) {
            return zza;
        }
        zzjr.zza(this.val$context, "native_ad");
        return new zzmf();
    }
}
