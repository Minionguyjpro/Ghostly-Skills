package com.google.android.gms.plus;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.plus.zze;
import com.google.android.gms.internal.plus.zzi;
import com.google.android.gms.internal.plus.zzj;
import com.google.android.gms.plus.internal.zzh;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public final class Plus {
    @Deprecated
    public static final Api<PlusOptions> API;
    @Deprecated
    public static final Account AccountApi = new zze();
    private static final Api.AbstractClientBuilder<zzh, PlusOptions> CLIENT_BUILDER;
    public static final Api.ClientKey<zzh> CLIENT_KEY = new Api.ClientKey<>();
    @Deprecated
    public static final People PeopleApi = new zzj();
    @Deprecated
    public static final Scope SCOPE_PLUS_LOGIN = new Scope(Scopes.PLUS_LOGIN);
    @Deprecated
    public static final Scope SCOPE_PLUS_PROFILE = new Scope(Scopes.PLUS_ME);
    @Deprecated
    private static final zzb zze = new zzi();
    private static final zza zzf = new com.google.android.gms.internal.plus.zzh();

    @Deprecated
    public static final class PlusOptions implements Api.ApiOptions.Optional {
        private final String zzg;
        final Set<String> zzh;

        @Deprecated
        public static final class Builder {
            String zzg;
            final Set<String> zzh = new HashSet();

            @Deprecated
            public final Builder addActivityTypes(String... strArr) {
                Preconditions.checkNotNull(strArr, "activityTypes may not be null.");
                for (String add : strArr) {
                    this.zzh.add(add);
                }
                return this;
            }

            @Deprecated
            public final PlusOptions build() {
                return new PlusOptions(this, (zzc) null);
            }

            @Deprecated
            public final Builder setServerClientId(String str) {
                this.zzg = str;
                return this;
            }
        }

        private PlusOptions() {
            this.zzg = null;
            this.zzh = new HashSet();
        }

        private PlusOptions(Builder builder) {
            this.zzg = builder.zzg;
            this.zzh = builder.zzh;
        }

        /* synthetic */ PlusOptions(Builder builder, zzc zzc) {
            this(builder);
        }

        /* synthetic */ PlusOptions(zzc zzc) {
            this();
        }

        @Deprecated
        public static Builder builder() {
            return new Builder();
        }
    }

    public static abstract class zza<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzh> {
        public zza(GoogleApiClient googleApiClient) {
            super(Plus.CLIENT_KEY, googleApiClient);
        }
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [com.google.android.gms.internal.plus.zzi, com.google.android.gms.plus.zzb] */
    /* JADX WARNING: type inference failed for: r0v7, types: [com.google.android.gms.internal.plus.zzh, com.google.android.gms.plus.zza] */
    static {
        zzc zzc = new zzc();
        CLIENT_BUILDER = zzc;
        API = new Api<>("Plus.API", zzc, CLIENT_KEY);
    }

    private Plus() {
    }

    public static zzh zza(GoogleApiClient googleApiClient, boolean z) {
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        Preconditions.checkState(googleApiClient.isConnected(), "GoogleApiClient must be connected.");
        Preconditions.checkState(googleApiClient.hasApi(API), "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        boolean hasConnectedApi = googleApiClient.hasConnectedApi(API);
        if (z && !hasConnectedApi) {
            throw new IllegalStateException("GoogleApiClient has an optional Plus.API and is not connected to Plus. Use GoogleApiClient.hasConnectedApi(Plus.API) to guard this call.");
        } else if (hasConnectedApi) {
            return (zzh) googleApiClient.getClient(CLIENT_KEY);
        } else {
            return null;
        }
    }
}
