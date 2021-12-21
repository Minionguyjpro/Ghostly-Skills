package com.google.android.gms.internal.plus;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.internal.zzh;

final class zzl extends zzp {
    private final /* synthetic */ String zzak;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzl(zzj zzj, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient, (zzk) null);
        this.zzak = str;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        setCancelToken(((zzh) anyClient).zza(this, 0, this.zzak));
    }
}
