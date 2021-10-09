package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class Common {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.ClientKey<zaj> CLIENT_KEY = new Api.ClientKey<>();
    public static final zad zaa = new zac();
    private static final Api.AbstractClientBuilder<zaj, Api.ApiOptions.NoOptions> zab;

    static {
        zab zab2 = new zab();
        zab = zab2;
        API = new Api<>("Common.API", zab2, CLIENT_KEY);
    }
}
