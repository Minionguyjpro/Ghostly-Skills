package com.google.android.gms.internal.plus;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import java.util.Collection;

public final class zzj implements People {
    public final Person getCurrentPerson(GoogleApiClient googleApiClient) {
        return Plus.zza(googleApiClient, true).zzb();
    }

    public final PendingResult<People.LoadPeopleResult> load(GoogleApiClient googleApiClient, Collection<String> collection) {
        return googleApiClient.enqueue(new zzn(this, googleApiClient, collection));
    }

    public final PendingResult<People.LoadPeopleResult> load(GoogleApiClient googleApiClient, String... strArr) {
        return googleApiClient.enqueue(new zzo(this, googleApiClient, strArr));
    }

    public final PendingResult<People.LoadPeopleResult> loadConnected(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzm(this, googleApiClient));
    }

    public final PendingResult<People.LoadPeopleResult> loadVisible(GoogleApiClient googleApiClient, int i, String str) {
        return googleApiClient.enqueue(new zzk(this, googleApiClient, i, str));
    }

    public final PendingResult<People.LoadPeopleResult> loadVisible(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzl(this, googleApiClient, str));
    }
}
