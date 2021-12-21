package com.google.android.gms.internal.plus;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.internal.zzh;
import java.util.Collection;

final class zzn extends zzp {
    private final /* synthetic */ Collection zzal;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzn(zzj zzj, GoogleApiClient googleApiClient, Collection collection) {
        super(googleApiClient, (zzk) null);
        this.zzal = collection;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzh) anyClient).zza((BaseImplementation.ResultHolder<People.LoadPeopleResult>) this, (Collection<String>) this.zzal);
    }
}
