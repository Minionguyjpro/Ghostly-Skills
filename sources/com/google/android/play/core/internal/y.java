package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class y extends j implements z {
    y(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
    }

    public final void c(Bundle bundle, Bundle bundle2) throws RemoteException {
        Parcel a2 = a();
        l.b(a2, bundle);
        l.b(a2, bundle2);
        b(2, a2);
    }

    public final void d(Bundle bundle) throws RemoteException {
        Parcel a2 = a();
        l.b(a2, bundle);
        b(3, a2);
    }

    public final void e(Bundle bundle) throws RemoteException {
        Parcel a2 = a();
        l.b(a2, bundle);
        b(4, a2);
    }
}
