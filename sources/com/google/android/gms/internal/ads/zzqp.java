package com.google.android.gms.internal.ads;

public abstract class zzqp extends zzek implements zzqo {
    public zzqp() {
        super("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004a, code lost:
        r3.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007c, code lost:
        r3.writeNoException();
        r3.writeString(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0087, code lost:
        r3.writeNoException();
        com.google.android.gms.internal.ads.zzel.zza(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) throws android.os.RemoteException {
        /*
            r0 = this;
            switch(r1) {
                case 2: goto L_0x0083;
                case 3: goto L_0x0078;
                case 4: goto L_0x006d;
                case 5: goto L_0x0068;
                case 6: goto L_0x0063;
                case 7: goto L_0x005e;
                case 8: goto L_0x0059;
                case 9: goto L_0x004e;
                case 10: goto L_0x0047;
                case 11: goto L_0x0042;
                case 12: goto L_0x0036;
                case 13: goto L_0x0023;
                case 14: goto L_0x0017;
                case 15: goto L_0x0011;
                case 16: goto L_0x000b;
                case 17: goto L_0x0005;
                default: goto L_0x0003;
            }
        L_0x0003:
            r1 = 0
            return r1
        L_0x0005:
            java.lang.String r1 = r0.getMediationAdapterClassName()
            goto L_0x007c
        L_0x000b:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzke()
            goto L_0x0087
        L_0x0011:
            com.google.android.gms.internal.ads.zzps r1 = r0.zzkf()
            goto L_0x0087
        L_0x0017:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r2, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            r0.reportTouchEvent(r1)
            goto L_0x004a
        L_0x0023:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r2, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            boolean r1 = r0.recordImpression(r1)
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r3, (boolean) r1)
            goto L_0x008d
        L_0x0036:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r2, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            r0.performClick(r1)
            goto L_0x004a
        L_0x0042:
            com.google.android.gms.internal.ads.zzlo r1 = r0.getVideoController()
            goto L_0x0087
        L_0x0047:
            r0.destroy()
        L_0x004a:
            r3.writeNoException()
            goto L_0x008d
        L_0x004e:
            android.os.Bundle r1 = r0.getExtras()
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r3, r1)
            goto L_0x008d
        L_0x0059:
            java.lang.String r1 = r0.getAdvertiser()
            goto L_0x007c
        L_0x005e:
            java.lang.String r1 = r0.getCallToAction()
            goto L_0x007c
        L_0x0063:
            com.google.android.gms.internal.ads.zzpw r1 = r0.zzkg()
            goto L_0x0087
        L_0x0068:
            java.lang.String r1 = r0.getBody()
            goto L_0x007c
        L_0x006d:
            java.util.List r1 = r0.getImages()
            r3.writeNoException()
            r3.writeList(r1)
            goto L_0x008d
        L_0x0078:
            java.lang.String r1 = r0.getHeadline()
        L_0x007c:
            r3.writeNoException()
            r3.writeString(r1)
            goto L_0x008d
        L_0x0083:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzka()
        L_0x0087:
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r3, (android.os.IInterface) r1)
        L_0x008d:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqp.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
