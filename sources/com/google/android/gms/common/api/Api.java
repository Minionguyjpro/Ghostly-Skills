package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class Api<O extends ApiOptions> {
    private final AbstractClientBuilder<?, O> zaa;
    private final zac<?, O> zab = null;
    private final ClientKey<?> zac;
    private final zab<?> zad;
    private final String zae;

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static abstract class AbstractClientBuilder<T extends Client, O> extends BaseClientBuilder<T, O> {
        @Deprecated
        public T buildClient(Context context, Looper looper, ClientSettings clientSettings, O o, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return buildClient(context, looper, clientSettings, o, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
        }

        public T buildClient(Context context, Looper looper, ClientSettings clientSettings, O o, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public interface AnyClient {
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static class AnyClientKey<C extends AnyClient> {
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public interface ApiOptions {
        public static final NoOptions NO_OPTIONS = new NoOptions();

        /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
        public interface HasAccountOptions extends HasOptions, NotRequiredOptions {
            Account getAccount();
        }

        /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
        public interface HasGoogleSignInAccountOptions extends HasOptions {
            GoogleSignInAccount getGoogleSignInAccount();
        }

        /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
        public interface HasOptions extends ApiOptions {
        }

        /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }
        }

        /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
        public interface NotRequiredOptions extends ApiOptions {
        }

        /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public interface Client extends AnyClient {
        void connect(BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks);

        void disconnect();

        void disconnect(String str);

        void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

        Feature[] getAvailableFeatures();

        String getEndpointPackageName();

        String getLastDisconnectMessage();

        int getMinApkVersion();

        void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set);

        Feature[] getRequiredFeatures();

        Set<Scope> getScopesForConnectionlessNonSignIn();

        IBinder getServiceBrokerBinder();

        Intent getSignInIntent();

        boolean isConnected();

        boolean isConnecting();

        void onUserSignOut(BaseGmsClient.SignOutCallbacks signOutCallbacks);

        boolean providesSignIn();

        boolean requiresAccount();

        boolean requiresGooglePlayServices();

        boolean requiresSignIn();
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static final class ClientKey<C extends Client> extends AnyClientKey<C> {
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public interface zaa<T extends IInterface> extends AnyClient {
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static final class zab<C extends zaa<? extends IInterface>> extends AnyClientKey<C> {
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static abstract class zac<T extends zaa<? extends IInterface>, O> extends BaseClientBuilder<T, O> {
    }

    public <C extends Client> Api(String str, AbstractClientBuilder<C, O> abstractClientBuilder, ClientKey<C> clientKey) {
        Preconditions.checkNotNull(abstractClientBuilder, "Cannot construct an Api with a null ClientBuilder");
        Preconditions.checkNotNull(clientKey, "Cannot construct an Api with a null ClientKey");
        this.zae = str;
        this.zaa = abstractClientBuilder;
        this.zac = clientKey;
        this.zad = null;
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
    public static abstract class BaseClientBuilder<T extends AnyClient, O> {
        public static final int API_PRIORITY_GAMES = 1;
        public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;
        public static final int API_PRIORITY_PLUS = 2;

        public int getPriority() {
            return Integer.MAX_VALUE;
        }

        public List<Scope> getImpliedScopes(O o) {
            return Collections.emptyList();
        }
    }

    public final BaseClientBuilder<?, O> zaa() {
        return (BaseClientBuilder) Preconditions.checkNotNull(this.zaa);
    }

    public final AbstractClientBuilder<?, O> zab() {
        Preconditions.checkState(this.zaa != null, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.zaa;
    }

    public final AnyClientKey<?> zac() {
        ClientKey<?> clientKey = this.zac;
        if (clientKey != null) {
            return clientKey;
        }
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
    }

    public final String zad() {
        return this.zae;
    }
}
