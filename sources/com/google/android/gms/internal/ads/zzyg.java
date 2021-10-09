package com.google.android.gms.internal.ads;

public abstract class zzyg extends zzek implements zzyf {
    public zzyg() {
        super("com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0099, code lost:
        r3.writeNoException();
        com.google.android.gms.internal.ads.zzel.zza(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00b4, code lost:
        r3.writeNoException();
        r3.writeString(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x003c, code lost:
        r3.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x004a, code lost:
        r3.writeNoException();
        com.google.android.gms.internal.ads.zzel.zza(r3, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) throws android.os.RemoteException {
        /*
            r0 = this;
            switch(r1) {
                case 2: goto L_0x00b0;
                case 3: goto L_0x00a5;
                case 4: goto L_0x00a0;
                case 5: goto L_0x0095;
                case 6: goto L_0x0090;
                case 7: goto L_0x008b;
                case 8: goto L_0x0080;
                case 9: goto L_0x007b;
                case 10: goto L_0x0076;
                case 11: goto L_0x0071;
                case 12: goto L_0x006c;
                case 13: goto L_0x0067;
                case 14: goto L_0x0062;
                case 15: goto L_0x005d;
                case 16: goto L_0x0052;
                case 17: goto L_0x0046;
                case 18: goto L_0x0041;
                case 19: goto L_0x0039;
                case 20: goto L_0x002d;
                case 21: goto L_0x0011;
                case 22: goto L_0x0005;
                default: goto L_0x0003;
            }
        L_0x0003:
            r1 = 0
            return r1
        L_0x0005:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            r0.zzl(r1)
            goto L_0x003c
        L_0x0011:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.IBinder r4 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r4)
            android.os.IBinder r2 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            r0.zzb(r1, r4, r2)
            goto L_0x003c
        L_0x002d:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            r0.zzj(r1)
            goto L_0x003c
        L_0x0039:
            r0.recordImpression()
        L_0x003c:
            r3.writeNoException()
            goto L_0x00ba
        L_0x0041:
            boolean r1 = r0.getOverrideClickHandling()
            goto L_0x004a
        L_0x0046:
            boolean r1 = r0.getOverrideImpressionRecording()
        L_0x004a:
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r3, (boolean) r1)
            goto L_0x00ba
        L_0x0052:
            android.os.Bundle r1 = r0.getExtras()
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r3, r1)
            goto L_0x00ba
        L_0x005d:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzke()
            goto L_0x0099
        L_0x0062:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzmw()
            goto L_0x0099
        L_0x0067:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzmv()
            goto L_0x0099
        L_0x006c:
            com.google.android.gms.internal.ads.zzps r1 = r0.zzkf()
            goto L_0x0099
        L_0x0071:
            com.google.android.gms.internal.ads.zzlo r1 = r0.getVideoController()
            goto L_0x0099
        L_0x0076:
            java.lang.String r1 = r0.getPrice()
            goto L_0x00b4
        L_0x007b:
            java.lang.String r1 = r0.getStore()
            goto L_0x00b4
        L_0x0080:
            double r1 = r0.getStarRating()
            r3.writeNoException()
            r3.writeDouble(r1)
            goto L_0x00ba
        L_0x008b:
            java.lang.String r1 = r0.getAdvertiser()
            goto L_0x00b4
        L_0x0090:
            java.lang.String r1 = r0.getCallToAction()
            goto L_0x00b4
        L_0x0095:
            com.google.android.gms.internal.ads.zzpw r1 = r0.zzjz()
        L_0x0099:
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r3, (android.os.IInterface) r1)
            goto L_0x00ba
        L_0x00a0:
            java.lang.String r1 = r0.getBody()
            goto L_0x00b4
        L_0x00a5:
            java.util.List r1 = r0.getImages()
            r3.writeNoException()
            r3.writeList(r1)
            goto L_0x00ba
        L_0x00b0:
            java.lang.String r1 = r0.getHeadline()
        L_0x00b4:
            r3.writeNoException()
            r3.writeString(r1)
        L_0x00ba:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzyg.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
