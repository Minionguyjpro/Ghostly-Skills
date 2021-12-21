package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzxu extends zzek implements zzxt {
    public zzxu() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
    }

    public static zzxt zzs(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
        return queryLocalInterface instanceof zzxt ? (zzxt) queryLocalInterface : new zzxv(iBinder);
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
                case 1: goto L_0x0069;
                case 2: goto L_0x0065;
                case 3: goto L_0x005d;
                case 4: goto L_0x0059;
                case 5: goto L_0x0055;
                case 6: goto L_0x0051;
                case 7: goto L_0x0031;
                case 8: goto L_0x002d;
                case 9: goto L_0x0021;
                case 10: goto L_0x0011;
                case 11: goto L_0x000d;
                case 12: goto L_0x0005;
                default: goto L_0x0003;
            }
        L_0x0003:
            r1 = 0
            return r1
        L_0x0005:
            java.lang.String r1 = r2.readString()
            r0.zzbj(r1)
            goto L_0x006c
        L_0x000d:
            r0.onVideoEnd()
            goto L_0x006c
        L_0x0011:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzqs r1 = com.google.android.gms.internal.ads.zzqt.zzk(r1)
            java.lang.String r2 = r2.readString()
            r0.zzb(r1, r2)
            goto L_0x006c
        L_0x0021:
            java.lang.String r1 = r2.readString()
            java.lang.String r2 = r2.readString()
            r0.onAppEvent(r1, r2)
            goto L_0x006c
        L_0x002d:
            r0.onAdImpression()
            goto L_0x006c
        L_0x0031:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x0039
            r1 = 0
            goto L_0x004d
        L_0x0039:
            java.lang.String r2 = "com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzxw
            if (r4 == 0) goto L_0x0047
            r1 = r2
            com.google.android.gms.internal.ads.zzxw r1 = (com.google.android.gms.internal.ads.zzxw) r1
            goto L_0x004d
        L_0x0047:
            com.google.android.gms.internal.ads.zzxy r2 = new com.google.android.gms.internal.ads.zzxy
            r2.<init>(r1)
            r1 = r2
        L_0x004d:
            r0.zza(r1)
            goto L_0x006c
        L_0x0051:
            r0.onAdLoaded()
            goto L_0x006c
        L_0x0055:
            r0.onAdOpened()
            goto L_0x006c
        L_0x0059:
            r0.onAdLeftApplication()
            goto L_0x006c
        L_0x005d:
            int r1 = r2.readInt()
            r0.onAdFailedToLoad(r1)
            goto L_0x006c
        L_0x0065:
            r0.onAdClosed()
            goto L_0x006c
        L_0x0069:
            r0.onAdClicked()
        L_0x006c:
            r3.writeNoException()
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxu.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
