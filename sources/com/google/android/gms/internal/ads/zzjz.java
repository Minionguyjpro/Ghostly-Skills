package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzjr;
import com.mopub.common.AdType;

final class zzjz extends zzjr.zza<zzagz> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzxn zzars;
    private final /* synthetic */ zzjr zzart;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjz(zzjr zzjr, Context context, zzxn zzxn) {
        super();
        this.zzart = zzjr;
        this.val$context = context;
        this.zzars = zzxn;
    }

    public final /* synthetic */ Object zza(zzld zzld) throws RemoteException {
        return zzld.createRewardedVideoAd(ObjectWrapper.wrap(this.val$context), this.zzars, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzagz zza = this.zzart.zzarn.zza(this.val$context, this.zzars);
        if (zza != null) {
            return zza;
        }
        zzjr.zza(this.val$context, AdType.REWARDED_VIDEO);
        return new zzmo();
    }
}
