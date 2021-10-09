package com.google.android.gms.internal.ads;

public abstract class zzql extends zzek implements zzqk {
    public zzql() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004b, code lost:
        r3.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008d, code lost:
        r3.writeNoException();
        r3.writeString(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0098, code lost:
        r3.writeNoException();
        com.google.android.gms.internal.ads.zzel.zza(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
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
                case 2: goto L_0x0094;
                case 3: goto L_0x0089;
                case 4: goto L_0x007e;
                case 5: goto L_0x0079;
                case 6: goto L_0x0074;
                case 7: goto L_0x006f;
                case 8: goto L_0x0064;
                case 9: goto L_0x005f;
                case 10: goto L_0x005a;
                case 11: goto L_0x004f;
                case 12: goto L_0x0048;
                case 13: goto L_0x0043;
                case 14: goto L_0x0037;
                case 15: goto L_0x0023;
                case 16: goto L_0x0017;
                case 17: goto L_0x0011;
                case 18: goto L_0x000b;
                case 19: goto L_0x0005;
                default: goto L_0x0003;
            }
        L_0x0003:
            r1 = 0
            return r1
        L_0x0005:
            java.lang.String r1 = r0.getMediationAdapterClassName()
            goto L_0x008d
        L_0x000b:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzke()
            goto L_0x0098
        L_0x0011:
            com.google.android.gms.internal.ads.zzps r1 = r0.zzkf()
            goto L_0x0098
        L_0x0017:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r2, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            r0.reportTouchEvent(r1)
            goto L_0x004b
        L_0x0023:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r2, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            boolean r1 = r0.recordImpression(r1)
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r3, (boolean) r1)
            goto L_0x009e
        L_0x0037:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r2, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            r0.performClick(r1)
            goto L_0x004b
        L_0x0043:
            com.google.android.gms.internal.ads.zzlo r1 = r0.getVideoController()
            goto L_0x0098
        L_0x0048:
            r0.destroy()
        L_0x004b:
            r3.writeNoException()
            goto L_0x009e
        L_0x004f:
            android.os.Bundle r1 = r0.getExtras()
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r3, r1)
            goto L_0x009e
        L_0x005a:
            java.lang.String r1 = r0.getPrice()
            goto L_0x008d
        L_0x005f:
            java.lang.String r1 = r0.getStore()
            goto L_0x008d
        L_0x0064:
            double r1 = r0.getStarRating()
            r3.writeNoException()
            r3.writeDouble(r1)
            goto L_0x009e
        L_0x006f:
            java.lang.String r1 = r0.getCallToAction()
            goto L_0x008d
        L_0x0074:
            com.google.android.gms.internal.ads.zzpw r1 = r0.zzjz()
            goto L_0x0098
        L_0x0079:
            java.lang.String r1 = r0.getBody()
            goto L_0x008d
        L_0x007e:
            java.util.List r1 = r0.getImages()
            r3.writeNoException()
            r3.writeList(r1)
            goto L_0x009e
        L_0x0089:
            java.lang.String r1 = r0.getHeadline()
        L_0x008d:
            r3.writeNoException()
            r3.writeString(r1)
            goto L_0x009e
        L_0x0094:
            com.google.android.gms.dynamic.IObjectWrapper r1 = r0.zzka()
        L_0x0098:
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r3, (android.os.IInterface) r1)
        L_0x009e:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzql.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
