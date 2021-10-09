package com.google.android.gms.plus.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ICancelToken;
import java.util.List;

public interface zzf extends IInterface {
    String getAccountName() throws RemoteException;

    ICancelToken zza(zzb zzb, int i, int i2, int i3, String str) throws RemoteException;

    void zza() throws RemoteException;

    void zza(zzb zzb) throws RemoteException;

    void zza(zzb zzb, List<String> list) throws RemoteException;
}
