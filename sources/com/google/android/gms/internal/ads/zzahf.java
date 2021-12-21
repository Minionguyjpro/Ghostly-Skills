package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzahf extends zzek implements zzahe {
    public zzahf() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }

    public static zzahe zzz(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        return queryLocalInterface instanceof zzahe ? (zzahe) queryLocalInterface : new zzahg(iBinder);
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) throws android.os.RemoteException {
        /*
            r0 = this;
            switch(r1) {
                case 1: goto L_0x0041;
                case 2: goto L_0x003d;
                case 3: goto L_0x0039;
                case 4: goto L_0x0035;
                case 5: goto L_0x0015;
                case 6: goto L_0x0011;
                case 7: goto L_0x0009;
                case 8: goto L_0x0005;
                default: goto L_0x0003;
            }
        L_0x0003:
            r1 = 0
            return r1
        L_0x0005:
            r0.onRewardedVideoCompleted()
            goto L_0x0044
        L_0x0009:
            int r1 = r2.readInt()
            r0.onRewardedVideoAdFailedToLoad(r1)
            goto L_0x0044
        L_0x0011:
            r0.onRewardedVideoAdLeftApplication()
            goto L_0x0044
        L_0x0015:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x001d
            r1 = 0
            goto L_0x0031
        L_0x001d:
            java.lang.String r2 = "com.google.android.gms.ads.internal.reward.client.IRewardItem"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzagu
            if (r4 == 0) goto L_0x002b
            r1 = r2
            com.google.android.gms.internal.ads.zzagu r1 = (com.google.android.gms.internal.ads.zzagu) r1
            goto L_0x0031
        L_0x002b:
            com.google.android.gms.internal.ads.zzagw r2 = new com.google.android.gms.internal.ads.zzagw
            r2.<init>(r1)
            r1 = r2
        L_0x0031:
            r0.zza(r1)
            goto L_0x0044
        L_0x0035:
            r0.onRewardedVideoAdClosed()
            goto L_0x0044
        L_0x0039:
            r0.onRewardedVideoStarted()
            goto L_0x0044
        L_0x003d:
            r0.onRewardedVideoAdOpened()
            goto L_0x0044
        L_0x0041:
            r0.onRewardedVideoAdLoaded()
        L_0x0044:
            r3.writeNoException()
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahf.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
