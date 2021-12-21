package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.internal.plus.zzr;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public final class zzh extends GmsClient<zzf> {
    private Person zzr;
    private final zzn zzs;

    public zzh(Context context, Looper looper, ClientSettings clientSettings, zzn zzn, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 2, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzs = zzn;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.plus.internal.IPlusService");
        return queryLocalInterface instanceof zzf ? (zzf) queryLocalInterface : new zzg(iBinder);
    }

    public final String getAccountName() {
        checkConnected();
        try {
            return ((zzf) getService()).getAccountName();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: protected */
    public final Bundle getGetServiceRequestExtraArgs() {
        Bundle zze = this.zzs.zze();
        zze.putStringArray("request_visible_actions", this.zzs.zzc());
        zze.putString(ServiceSpecificExtraArgs.PlusExtraArgs.PLUS_AUTH_PACKAGE, this.zzs.zzd());
        return zze;
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.plus.service.START";
    }

    /* access modifiers changed from: protected */
    public final void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        if (i == 0 && bundle != null && bundle.containsKey("loaded_person")) {
            this.zzr = zzr.zza(bundle.getByteArray("loaded_person"));
        }
        super.onPostInitHandler(i, iBinder, bundle, i2);
    }

    public final boolean requiresSignIn() {
        Set<Scope> applicableScopes = getClientSettings().getApplicableScopes(Plus.API);
        if (applicableScopes == null || applicableScopes.isEmpty()) {
            return false;
        }
        return applicableScopes.size() != 1 || !applicableScopes.contains(new Scope("plus_one_placeholder_scope"));
    }

    public final ICancelToken zza(BaseImplementation.ResultHolder<People.LoadPeopleResult> resultHolder, int i, String str) {
        checkConnected();
        zzj zzj = new zzj(resultHolder);
        try {
            return ((zzf) getService()).zza(zzj, 1, i, -1, str);
        } catch (RemoteException unused) {
            zzj.zza(DataHolder.empty(8), (String) null);
            return null;
        }
    }

    public final void zza() {
        checkConnected();
        try {
            this.zzr = null;
            ((zzf) getService()).zza();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<People.LoadPeopleResult> resultHolder) {
        checkConnected();
        zzj zzj = new zzj(resultHolder);
        try {
            ((zzf) getService()).zza(zzj, 2, 1, -1, (String) null);
        } catch (RemoteException unused) {
            zzj.zza(DataHolder.empty(8), (String) null);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<People.LoadPeopleResult> resultHolder, Collection<String> collection) {
        checkConnected();
        zzj zzj = new zzj(resultHolder);
        try {
            ((zzf) getService()).zza(zzj, new ArrayList(collection));
        } catch (RemoteException unused) {
            zzj.zza(DataHolder.empty(8), (String) null);
        }
    }

    public final void zza(BaseImplementation.ResultHolder<People.LoadPeopleResult> resultHolder, String[] strArr) {
        zza(resultHolder, (Collection<String>) Arrays.asList(strArr));
    }

    public final Person zzb() {
        checkConnected();
        return this.zzr;
    }

    public final void zzb(BaseImplementation.ResultHolder<Status> resultHolder) {
        checkConnected();
        zza();
        zzk zzk = new zzk(resultHolder);
        try {
            ((zzf) getService()).zza(zzk);
        } catch (RemoteException unused) {
            zzk.zza(8, (Bundle) null);
        }
    }
}
