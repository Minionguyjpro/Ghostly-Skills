package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class ApiKey<O extends Api.ApiOptions> {
    private final boolean zaa = false;
    private final int zab;
    private final Api<O> zac;
    private final O zad;

    private ApiKey(Api<O> api, O o) {
        this.zac = api;
        this.zad = o;
        this.zab = Objects.hashCode(api, o);
    }

    public final boolean isUnique() {
        return false;
    }

    public static <O extends Api.ApiOptions> ApiKey<O> getSharedApiKey(Api<O> api, O o) {
        return new ApiKey<>(api, o);
    }

    public final Api.AnyClientKey<?> getClientKey() {
        return this.zac.zac();
    }

    public final String getApiName() {
        return this.zac.zad();
    }

    public final int hashCode() {
        return this.zab;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApiKey)) {
            return false;
        }
        ApiKey apiKey = (ApiKey) obj;
        return Objects.equal(this.zac, apiKey.zac) && Objects.equal(this.zad, apiKey.zad);
    }
}
