package com.google.android.gms.internal.plus;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.internal.zzh;

final class zzm extends zzp {
    zzm(zzj zzj, GoogleApiClient googleApiClient) {
        super(googleApiClient, (zzk) null);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzh) anyClient).zza(this);
    }
}
