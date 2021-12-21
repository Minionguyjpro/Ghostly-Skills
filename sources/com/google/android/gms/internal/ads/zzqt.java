package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzqt extends zzek implements zzqs {
    public zzqt() {
        super("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    public static zzqs zzk(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        return queryLocalInterface instanceof zzqs ? (zzqs) queryLocalInterface : new zzqu(iBinder);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0052, code lost:
        r3.writeNoException();
        com.google.android.gms.internal.ads.zzel.zza(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0061, code lost:
        r3.writeNoException();
        r3.writeString(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0036, code lost:
        r3.writeNoException();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) throws android.os.RemoteException {
        /*
            r0 = this;
            switch(r1) {
                case 1: goto L_0x0059;
                case 2: goto L_0x004a;
                case 3: goto L_0x003f;
                case 4: goto L_0x003a;
                case 5: goto L_0x002f;
                case 6: goto L_0x002b;
                case 7: goto L_0x0026;
                case 8: goto L_0x0022;
                case 9: goto L_0x001d;
                case 10: goto L_0x000a;
                case 11: goto L_0x0005;
                default: goto L_0x0003;
            }
        L_0x0003:
            r1 = 0
            return r1
        L_0x0005:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzka()
            goto L_0x0052
        L_0x000a:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            boolean r1 = r0.zzh(r1)
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r3, (boolean) r1)
            goto L_0x0067
        L_0x001d:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzkh()
            goto L_0x0052
        L_0x0022:
            r0.destroy()
            goto L_0x0036
        L_0x0026:
            com.google.android.gms.internal.ads.zzlo r1 = r0.getVideoController()
            goto L_0x0052
        L_0x002b:
            r0.recordImpression()
            goto L_0x0036
        L_0x002f:
            java.lang.String r1 = r2.readString()
            r0.performClick(r1)
        L_0x0036:
            r3.writeNoException()
            goto L_0x0067
        L_0x003a:
            java.lang.String r1 = r0.getCustomTemplateId()
            goto L_0x0061
        L_0x003f:
            java.util.List r1 = r0.getAvailableAssetNames()
            r3.writeNoException()
            r3.writeStringList(r1)
            goto L_0x0067
        L_0x004a:
            java.lang.String r1 = r2.readString()
            com.google.android.gms.internal.ads.zzpw r1 = r0.zzap(r1)
        L_0x0052:
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r3, (android.os.IInterface) r1)
            goto L_0x0067
        L_0x0059:
            java.lang.String r1 = r2.readString()
            java.lang.String r1 = r0.zzao(r1)
        L_0x0061:
            r3.writeNoException()
            r3.writeString(r1)
        L_0x0067:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqt.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
