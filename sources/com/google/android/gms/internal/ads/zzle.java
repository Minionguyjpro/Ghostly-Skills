package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzle extends zzek implements zzld {
    public zzle() {
        super("com.google.android.gms.ads.internal.client.IClientApi");
    }

    public static zzld asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
        return queryLocalInterface instanceof zzld ? (zzld) queryLocalInterface : new zzlf(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IInterface iInterface;
        switch (i) {
            case 1:
                iInterface = createBannerAdManager(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzjn) zzel.zza(parcel, zzjn.CREATOR), parcel.readString(), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 2:
                iInterface = createInterstitialAdManager(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzjn) zzel.zza(parcel, zzjn.CREATOR), parcel.readString(), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 3:
                iInterface = createAdLoaderBuilder(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 4:
                iInterface = getMobileAdsSettingsManager(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 5:
                iInterface = createNativeAdViewDelegate(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 6:
                iInterface = createRewardedVideoAd(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 7:
                iInterface = createInAppPurchaseManager(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 8:
                iInterface = createAdOverlay(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                break;
            case 9:
                iInterface = getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            case 10:
                iInterface = createSearchAdManager(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzjn) zzel.zza(parcel, zzjn.CREATOR), parcel.readString(), parcel.readInt());
                break;
            case 11:
                iInterface = createNativeAdViewHolderDelegate(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        zzel.zza(parcel2, iInterface);
        return true;
    }
}
