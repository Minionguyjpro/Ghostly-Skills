package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
abstract class zah<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zaj> {
    public zah(GoogleApiClient googleApiClient) {
        super((Api<?>) Common.API, googleApiClient);
    }
}
