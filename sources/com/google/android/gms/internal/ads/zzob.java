package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzob extends zzek implements zzoa {
    public zzob() {
        super("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        String str;
        if (i == 1) {
            str = zzjn();
        } else if (i != 2) {
            if (i == 3) {
                zzg(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
            } else if (i == 4) {
                recordClick();
            } else if (i != 5) {
                return false;
            } else {
                recordImpression();
            }
            parcel2.writeNoException();
            return true;
        } else {
            str = getContent();
        }
        parcel2.writeNoException();
        parcel2.writeString(str);
        return true;
    }
}
