package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

@zzadh
public final class zzjg extends RemoteCreator<zzkq> {
    public zzjg() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        return queryLocalInterface instanceof zzkq ? (zzkq) queryLocalInterface : new zzkr(iBinder);
    }

    public final zzkn zza(Context context, String str, zzxn zzxn) {
        try {
            IBinder zza = ((zzkq) getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap(context), str, zzxn, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            if (zza == null) {
                return null;
            }
            IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            return queryLocalInterface instanceof zzkn ? (zzkn) queryLocalInterface : new zzkp(zza);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e) {
            zzane.zzc("Could not create remote builder for AdLoader.", e);
            return null;
        }
    }
}
