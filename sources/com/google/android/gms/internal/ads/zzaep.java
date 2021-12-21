package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzaep extends zzej implements zzaen {
    zzaep(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    public final void zza(zzaef zzaef, zzaeq zzaeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable) zzaef);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) zzaeq);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzaey zzaey, zzaet zzaet) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable) zzaey);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) zzaet);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    public final zzaej zzb(zzaef zzaef) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable) zzaef);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzaej zzaej = (zzaej) zzel.zza(transactAndReadException, zzaej.CREATOR);
        transactAndReadException.recycle();
        return zzaej;
    }

    public final void zzb(zzaey zzaey, zzaet zzaet) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable) zzaey);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) zzaet);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
}
