package com.google.android.gms.dynamite;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public interface zzk extends IInterface {
    int zza(IObjectWrapper iObjectWrapper, String str, boolean z) throws RemoteException;

    IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i) throws RemoteException;

    int zzb() throws RemoteException;

    int zzb(IObjectWrapper iObjectWrapper, String str, boolean z) throws RemoteException;

    IObjectWrapper zzb(IObjectWrapper iObjectWrapper, String str, int i) throws RemoteException;
}
