package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzsl extends zzej implements zzsk {
    zzsl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.httpcache.IHttpAssetsCacheService");
    }

    public final ParcelFileDescriptor zza(zzsg zzsg) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable) zzsg);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) zzel.zza(transactAndReadException, ParcelFileDescriptor.CREATOR);
        transactAndReadException.recycle();
        return parcelFileDescriptor;
    }
}
