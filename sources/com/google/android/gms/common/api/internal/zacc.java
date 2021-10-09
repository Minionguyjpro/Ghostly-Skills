package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zau;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.zad;
import com.google.android.gms.signin.internal.zam;
import com.google.android.gms.signin.zaa;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public final class zacc extends zad implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zaa = zaa.zaa;
    private final Context zab;
    private final Handler zac;
    private final Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zad;
    private Set<Scope> zae;
    private ClientSettings zaf;
    private com.google.android.gms.signin.zad zag;
    /* access modifiers changed from: private */
    public zacd zah;

    public zacc(Context context, Handler handler, ClientSettings clientSettings) {
        this(context, handler, clientSettings, zaa);
    }

    private zacc(Context context, Handler handler, ClientSettings clientSettings, Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder) {
        this.zab = context;
        this.zac = handler;
        this.zaf = (ClientSettings) Preconditions.checkNotNull(clientSettings, "ClientSettings must not be null");
        this.zae = clientSettings.getRequiredScopes();
        this.zad = abstractClientBuilder;
    }

    public final void zaa(zacd zacd) {
        com.google.android.gms.signin.zad zad2 = this.zag;
        if (zad2 != null) {
            zad2.disconnect();
        }
        this.zaf.zaa(Integer.valueOf(System.identityHashCode(this)));
        Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> abstractClientBuilder = this.zad;
        Context context = this.zab;
        Looper looper = this.zac.getLooper();
        ClientSettings clientSettings = this.zaf;
        this.zag = (com.google.android.gms.signin.zad) abstractClientBuilder.buildClient(context, looper, clientSettings, clientSettings.zac(), (GoogleApiClient.ConnectionCallbacks) this, (GoogleApiClient.OnConnectionFailedListener) this);
        this.zah = zacd;
        Set<Scope> set = this.zae;
        if (set == null || set.isEmpty()) {
            this.zac.post(new zacb(this));
        } else {
            this.zag.zab();
        }
    }

    public final void zaa() {
        com.google.android.gms.signin.zad zad2 = this.zag;
        if (zad2 != null) {
            zad2.disconnect();
        }
    }

    public final void onConnected(Bundle bundle) {
        this.zag.zaa(this);
    }

    public final void onConnectionSuspended(int i) {
        this.zag.disconnect();
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zah.zaa(connectionResult);
    }

    public final void zaa(zam zam) {
        this.zac.post(new zace(this, zam));
    }

    /* access modifiers changed from: private */
    public final void zab(zam zam) {
        ConnectionResult zaa2 = zam.zaa();
        if (zaa2.isSuccess()) {
            zau zau = (zau) Preconditions.checkNotNull(zam.zab());
            ConnectionResult zab2 = zau.zab();
            if (!zab2.isSuccess()) {
                String valueOf = String.valueOf(zab2);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 48);
                sb.append("Sign-in succeeded with resolve account failure: ");
                sb.append(valueOf);
                Log.wtf("SignInCoordinator", sb.toString(), new Exception());
                this.zah.zaa(zab2);
                this.zag.disconnect();
                return;
            }
            this.zah.zaa(zau.zaa(), this.zae);
        } else {
            this.zah.zaa(zaa2);
        }
        this.zag.disconnect();
    }
}
