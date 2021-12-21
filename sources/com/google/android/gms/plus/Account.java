package com.google.android.gms.plus;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

@Deprecated
public interface Account {
    @Deprecated
    void clearDefaultAccount(GoogleApiClient googleApiClient);

    @Deprecated
    String getAccountName(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<Status> revokeAccessAndDisconnect(GoogleApiClient googleApiClient);
}
