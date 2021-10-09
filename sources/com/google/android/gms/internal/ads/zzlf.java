package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzlf extends zzej implements zzld {
    zzlf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IClientApi");
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzkn createAdLoaderBuilder(com.google.android.gms.dynamic.IObjectWrapper r2, java.lang.String r3, com.google.android.gms.internal.ads.zzxn r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.obtainAndWriteInterfaceToken()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.IInterface) r2)
            r0.writeString(r3)
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.IInterface) r4)
            r0.writeInt(r5)
            r2 = 3
            android.os.Parcel r2 = r1.transactAndReadException(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x001d
            r3 = 0
            goto L_0x0031
        L_0x001d:
            java.lang.String r4 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.ads.zzkn
            if (r5 == 0) goto L_0x002b
            r3 = r4
            com.google.android.gms.internal.ads.zzkn r3 = (com.google.android.gms.internal.ads.zzkn) r3
            goto L_0x0031
        L_0x002b:
            com.google.android.gms.internal.ads.zzkp r4 = new com.google.android.gms.internal.ads.zzkp
            r4.<init>(r3)
            r3 = r4
        L_0x0031:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlf.createAdLoaderBuilder(com.google.android.gms.dynamic.IObjectWrapper, java.lang.String, com.google.android.gms.internal.ads.zzxn, int):com.google.android.gms.internal.ads.zzkn");
    }

    public final zzaap createAdOverlay(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken);
        zzaap zzu = zzaaq.zzu(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzu;
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzks createBannerAdManager(com.google.android.gms.dynamic.IObjectWrapper r2, com.google.android.gms.internal.ads.zzjn r3, java.lang.String r4, com.google.android.gms.internal.ads.zzxn r5, int r6) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.obtainAndWriteInterfaceToken()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.IInterface) r2)
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.Parcelable) r3)
            r0.writeString(r4)
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.IInterface) r5)
            r0.writeInt(r6)
            r2 = 1
            android.os.Parcel r2 = r1.transactAndReadException(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x0020
            r3 = 0
            goto L_0x0034
        L_0x0020:
            java.lang.String r4 = "com.google.android.gms.ads.internal.client.IAdManager"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.ads.zzks
            if (r5 == 0) goto L_0x002e
            r3 = r4
            com.google.android.gms.internal.ads.zzks r3 = (com.google.android.gms.internal.ads.zzks) r3
            goto L_0x0034
        L_0x002e:
            com.google.android.gms.internal.ads.zzku r4 = new com.google.android.gms.internal.ads.zzku
            r4.<init>(r3)
            r3 = r4
        L_0x0034:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlf.createBannerAdManager(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.ads.zzjn, java.lang.String, com.google.android.gms.internal.ads.zzxn, int):com.google.android.gms.internal.ads.zzks");
    }

    public final zzaaz createInAppPurchaseManager(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        zzaaz zzw = zzaba.zzw(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzw;
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzks createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper r2, com.google.android.gms.internal.ads.zzjn r3, java.lang.String r4, com.google.android.gms.internal.ads.zzxn r5, int r6) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.obtainAndWriteInterfaceToken()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.IInterface) r2)
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.Parcelable) r3)
            r0.writeString(r4)
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.IInterface) r5)
            r0.writeInt(r6)
            r2 = 2
            android.os.Parcel r2 = r1.transactAndReadException(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x0020
            r3 = 0
            goto L_0x0034
        L_0x0020:
            java.lang.String r4 = "com.google.android.gms.ads.internal.client.IAdManager"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.ads.zzks
            if (r5 == 0) goto L_0x002e
            r3 = r4
            com.google.android.gms.internal.ads.zzks r3 = (com.google.android.gms.internal.ads.zzks) r3
            goto L_0x0034
        L_0x002e:
            com.google.android.gms.internal.ads.zzku r4 = new com.google.android.gms.internal.ads.zzku
            r4.<init>(r3)
            r3 = r4
        L_0x0034:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlf.createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.ads.zzjn, java.lang.String, com.google.android.gms.internal.ads.zzxn, int):com.google.android.gms.internal.ads.zzks");
    }

    public final zzqa createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper2);
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken);
        zzqa zzi = zzqb.zzi(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzi;
    }

    public final zzqf createNativeAdViewHolderDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper2);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper3);
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken);
        zzqf zzj = zzqg.zzj(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzj;
    }

    public final zzagz createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzxn zzxn, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface) zzxn);
        obtainAndWriteInterfaceToken.writeInt(i);
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken);
        zzagz zzy = zzaha.zzy(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzy;
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzks createSearchAdManager(com.google.android.gms.dynamic.IObjectWrapper r2, com.google.android.gms.internal.ads.zzjn r3, java.lang.String r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            android.os.Parcel r0 = r1.obtainAndWriteInterfaceToken()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.IInterface) r2)
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.Parcelable) r3)
            r0.writeString(r4)
            r0.writeInt(r5)
            r2 = 10
            android.os.Parcel r2 = r1.transactAndReadException(r2, r0)
            android.os.IBinder r3 = r2.readStrongBinder()
            if (r3 != 0) goto L_0x001e
            r3 = 0
            goto L_0x0032
        L_0x001e:
            java.lang.String r4 = "com.google.android.gms.ads.internal.client.IAdManager"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.ads.zzks
            if (r5 == 0) goto L_0x002c
            r3 = r4
            com.google.android.gms.internal.ads.zzks r3 = (com.google.android.gms.internal.ads.zzks) r3
            goto L_0x0032
        L_0x002c:
            com.google.android.gms.internal.ads.zzku r4 = new com.google.android.gms.internal.ads.zzku
            r4.<init>(r3)
            r3 = r4
        L_0x0032:
            r2.recycle()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlf.createSearchAdManager(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.ads.zzjn, java.lang.String, int):com.google.android.gms.internal.ads.zzks");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzlj getMobileAdsSettingsManager(com.google.android.gms.dynamic.IObjectWrapper r4) throws android.os.RemoteException {
        /*
            r3 = this;
            android.os.Parcel r0 = r3.obtainAndWriteInterfaceToken()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.IInterface) r4)
            r4 = 4
            android.os.Parcel r4 = r3.transactAndReadException(r4, r0)
            android.os.IBinder r0 = r4.readStrongBinder()
            if (r0 != 0) goto L_0x0014
            r0 = 0
            goto L_0x0028
        L_0x0014:
            java.lang.String r1 = "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.ads.zzlj
            if (r2 == 0) goto L_0x0022
            r0 = r1
            com.google.android.gms.internal.ads.zzlj r0 = (com.google.android.gms.internal.ads.zzlj) r0
            goto L_0x0028
        L_0x0022:
            com.google.android.gms.internal.ads.zzll r1 = new com.google.android.gms.internal.ads.zzll
            r1.<init>(r0)
            r0 = r1
        L_0x0028:
            r4.recycle()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlf.getMobileAdsSettingsManager(com.google.android.gms.dynamic.IObjectWrapper):com.google.android.gms.internal.ads.zzlj");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzlj getMobileAdsSettingsManagerWithClientJarVersion(com.google.android.gms.dynamic.IObjectWrapper r3, int r4) throws android.os.RemoteException {
        /*
            r2 = this;
            android.os.Parcel r0 = r2.obtainAndWriteInterfaceToken()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r0, (android.os.IInterface) r3)
            r0.writeInt(r4)
            r3 = 9
            android.os.Parcel r3 = r2.transactAndReadException(r3, r0)
            android.os.IBinder r4 = r3.readStrongBinder()
            if (r4 != 0) goto L_0x0018
            r4 = 0
            goto L_0x002c
        L_0x0018:
            java.lang.String r0 = "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager"
            android.os.IInterface r0 = r4.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.ads.zzlj
            if (r1 == 0) goto L_0x0026
            r4 = r0
            com.google.android.gms.internal.ads.zzlj r4 = (com.google.android.gms.internal.ads.zzlj) r4
            goto L_0x002c
        L_0x0026:
            com.google.android.gms.internal.ads.zzll r0 = new com.google.android.gms.internal.ads.zzll
            r0.<init>(r4)
            r4 = r0
        L_0x002c:
            r3.recycle()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlf.getMobileAdsSettingsManagerWithClientJarVersion(com.google.android.gms.dynamic.IObjectWrapper, int):com.google.android.gms.internal.ads.zzlj");
    }
}
