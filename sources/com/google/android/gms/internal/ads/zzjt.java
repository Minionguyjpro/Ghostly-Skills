package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzjr;

final class zzjt extends zzjr.zza<zzks> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzjn zzarq;
    private final /* synthetic */ String zzarr;
    private final /* synthetic */ zzjr zzart;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjt(zzjr zzjr, Context context, zzjn zzjn, String str) {
        super();
        this.zzart = zzjr;
        this.val$context = context;
        this.zzarq = zzjn;
        this.zzarr = str;
    }

    public final /* synthetic */ Object zza(zzld zzld) throws RemoteException {
        return zzld.createSearchAdManager(ObjectWrapper.wrap(this.val$context), this.zzarq, this.zzarr, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzks zza = this.zzart.zzarj.zza(this.val$context, this.zzarq, this.zzarr, (zzxn) null, 3);
        if (zza != null) {
            return zza;
        }
        zzjr.zza(this.val$context, "search");
        return new zzmj();
    }
}
