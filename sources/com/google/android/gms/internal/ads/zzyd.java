package com.google.android.gms.internal.ads;

public abstract class zzyd extends zzek implements zzyc {
    public zzyd() {
        super("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005b, code lost:
        r3.writeNoException();
        com.google.android.gms.internal.ads.zzel.zza(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007d, code lost:
        r3.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008f, code lost:
        r3.writeNoException();
        com.google.android.gms.internal.ads.zzel.zza(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00aa, code lost:
        r3.writeNoException();
        r3.writeString(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return true;
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
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) throws android.os.RemoteException {
        /*
            r0 = this;
            switch(r1) {
                case 2: goto L_0x00a6;
                case 3: goto L_0x009b;
                case 4: goto L_0x0096;
                case 5: goto L_0x008b;
                case 6: goto L_0x0086;
                case 7: goto L_0x0081;
                case 8: goto L_0x007a;
                case 9: goto L_0x006e;
                case 10: goto L_0x0062;
                case 11: goto L_0x0057;
                case 12: goto L_0x0052;
                case 13: goto L_0x0047;
                case 14: goto L_0x003b;
                case 15: goto L_0x0036;
                case 16: goto L_0x0031;
                case 17: goto L_0x0003;
                case 18: goto L_0x0003;
                case 19: goto L_0x002c;
                case 20: goto L_0x0027;
                case 21: goto L_0x0021;
                case 22: goto L_0x0005;
                default: goto L_0x0003;
            }
        L_0x0003:
            r1 = 0
            return r1
        L_0x0005:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.IBinder r4 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r4)
            android.os.IBinder r2 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            r0.zzb(r1, r4, r2)
            goto L_0x007d
        L_0x0021:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzke()
            goto L_0x008f
        L_0x0027:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzmw()
            goto L_0x008f
        L_0x002c:
            com.google.android.gms.internal.ads.zzps r1 = r0.zzkf()
            goto L_0x008f
        L_0x0031:
            com.google.android.gms.internal.ads.zzlo r1 = r0.getVideoController()
            goto L_0x008f
        L_0x0036:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzmv()
            goto L_0x008f
        L_0x003b:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            r0.zzl(r1)
            goto L_0x007d
        L_0x0047:
            android.os.Bundle r1 = r0.getExtras()
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r3, r1)
            goto L_0x00b0
        L_0x0052:
            boolean r1 = r0.getOverrideClickHandling()
            goto L_0x005b
        L_0x0057:
            boolean r1 = r0.getOverrideImpressionRecording()
        L_0x005b:
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r3, (boolean) r1)
            goto L_0x00b0
        L_0x0062:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            r0.zzk(r1)
            goto L_0x007d
        L_0x006e:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            r0.zzj(r1)
            goto L_0x007d
        L_0x007a:
            r0.recordImpression()
        L_0x007d:
            r3.writeNoException()
            goto L_0x00b0
        L_0x0081:
            java.lang.String r1 = r0.getAdvertiser()
            goto L_0x00aa
        L_0x0086:
            java.lang.String r1 = r0.getCallToAction()
            goto L_0x00aa
        L_0x008b:
            com.google.android.gms.internal.ads.zzpw r1 = r0.zzkg()
        L_0x008f:
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r3, (android.os.IInterface) r1)
            goto L_0x00b0
        L_0x0096:
            java.lang.String r1 = r0.getBody()
            goto L_0x00aa
        L_0x009b:
            java.util.List r1 = r0.getImages()
            r3.writeNoException()
            r3.writeList(r1)
            goto L_0x00b0
        L_0x00a6:
            java.lang.String r1 = r0.getHeadline()
        L_0x00aa:
            r3.writeNoException()
            r3.writeString(r1)
        L_0x00b0:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzyd.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
