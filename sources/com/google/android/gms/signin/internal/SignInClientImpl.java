package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zas;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public class SignInClientImpl extends GmsClient<zae> implements zad {
    private final boolean zaa;
    private final ClientSettings zab;
    private final Bundle zac;
    private final Integer zad;

    public SignInClientImpl(Context context, Looper looper, boolean z, ClientSettings clientSettings, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zaa = z;
        this.zab = clientSettings;
        this.zac = bundle;
        this.zad = clientSettings.zad();
    }

    public int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* access modifiers changed from: protected */
    public String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    /* access modifiers changed from: protected */
    public String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    public SignInClientImpl(Context context, Looper looper, boolean z, ClientSettings clientSettings, SignInOptions signInOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, true, clientSettings, createBundleFromClientSettings(clientSettings), connectionCallbacks, onConnectionFailedListener);
    }

    public boolean requiresSignIn() {
        return this.zaa;
    }

    public final void zaa(IAccountAccessor iAccountAccessor, boolean z) {
        try {
            ((zae) getService()).zaa(iAccountAccessor, ((Integer) Preconditions.checkNotNull(this.zad)).intValue(), z);
        } catch (RemoteException unused) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public final void zaa() {
        try {
            ((zae) getService()).zaa(((Integer) Preconditions.checkNotNull(this.zad)).intValue());
        } catch (RemoteException unused) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    public final void zaa(zac zac2) {
        Preconditions.checkNotNull(zac2, "Expecting a valid ISignInCallbacks");
        try {
            Account accountOrDefault = this.zab.getAccountOrDefault();
            GoogleSignInAccount googleSignInAccount = null;
            if ("<<default account>>".equals(accountOrDefault.name)) {
                googleSignInAccount = Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount();
            }
            ((zae) getService()).zaa(new zak(new zas(accountOrDefault, ((Integer) Preconditions.checkNotNull(this.zad)).intValue(), googleSignInAccount)), zac2);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zac2.zaa(new zam(8));
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Bundle getGetServiceRequestExtraArgs() {
        if (!getContext().getPackageName().equals(this.zab.getRealClientPackageName())) {
            this.zac.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zab.getRealClientPackageName());
        }
        return this.zac;
    }

    public final void zab() {
        connect(new BaseGmsClient.LegacyClientCallbackAdapter());
    }

    public static Bundle createBundleFromClientSettings(ClientSettings clientSettings) {
        SignInOptions zac2 = clientSettings.zac();
        Integer zad2 = clientSettings.zad();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", clientSettings.getAccount());
        if (zad2 != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", zad2.intValue());
        }
        if (zac2 != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zac2.zaa());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zac2.zab());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zac2.zac());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zac2.zad());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zac2.zae());
            bundle.putString("com.google.android.gms.signin.internal.logSessionId", zac2.zaf());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zac2.zag());
            Long zah = zac2.zah();
            if (zah != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", zah.longValue());
            }
            Long zai = zac2.zai();
            if (zai != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", zai.longValue());
            }
        }
        return bundle;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if (queryLocalInterface instanceof zae) {
            return (zae) queryLocalInterface;
        }
        return new zah(iBinder);
    }
}
