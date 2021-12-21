package com.google.android.gms.internal.ads;

public abstract class zzaeo extends zzek implements zzaen {
    public zzaeo() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.gms.internal.ads.zzaeq] */
    /* JADX WARNING: type inference failed for: r1v6, types: [com.google.android.gms.internal.ads.zzaet] */
    /* JADX WARNING: type inference failed for: r1v11, types: [com.google.android.gms.internal.ads.zzaet] */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: type inference failed for: r1v20 */
    /* JADX WARNING: type inference failed for: r1v21 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
        /*
            r3 = this;
            r7 = 1
            if (r4 == r7) goto L_0x0082
            r0 = 2
            r1 = 0
            if (r4 == r0) goto L_0x0059
            r0 = 4
            java.lang.String r2 = "com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener"
            if (r4 == r0) goto L_0x0035
            r0 = 5
            if (r4 == r0) goto L_0x0011
            r4 = 0
            return r4
        L_0x0011:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaey> r4 = com.google.android.gms.internal.ads.zzaey.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.ads.zzaey r4 = (com.google.android.gms.internal.ads.zzaey) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0020
            goto L_0x0031
        L_0x0020:
            android.os.IInterface r0 = r5.queryLocalInterface(r2)
            boolean r1 = r0 instanceof com.google.android.gms.internal.ads.zzaet
            if (r1 == 0) goto L_0x002c
            r1 = r0
            com.google.android.gms.internal.ads.zzaet r1 = (com.google.android.gms.internal.ads.zzaet) r1
            goto L_0x0031
        L_0x002c:
            com.google.android.gms.internal.ads.zzaeu r1 = new com.google.android.gms.internal.ads.zzaeu
            r1.<init>(r5)
        L_0x0031:
            r3.zzb(r4, r1)
            goto L_0x007e
        L_0x0035:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaey> r4 = com.google.android.gms.internal.ads.zzaey.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.ads.zzaey r4 = (com.google.android.gms.internal.ads.zzaey) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0044
            goto L_0x0055
        L_0x0044:
            android.os.IInterface r0 = r5.queryLocalInterface(r2)
            boolean r1 = r0 instanceof com.google.android.gms.internal.ads.zzaet
            if (r1 == 0) goto L_0x0050
            r1 = r0
            com.google.android.gms.internal.ads.zzaet r1 = (com.google.android.gms.internal.ads.zzaet) r1
            goto L_0x0055
        L_0x0050:
            com.google.android.gms.internal.ads.zzaeu r1 = new com.google.android.gms.internal.ads.zzaeu
            r1.<init>(r5)
        L_0x0055:
            r3.zza((com.google.android.gms.internal.ads.zzaey) r4, (com.google.android.gms.internal.ads.zzaet) r1)
            goto L_0x007e
        L_0x0059:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaef> r4 = com.google.android.gms.internal.ads.zzaef.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.ads.zzaef r4 = (com.google.android.gms.internal.ads.zzaef) r4
            android.os.IBinder r5 = r5.readStrongBinder()
            if (r5 != 0) goto L_0x0068
            goto L_0x007b
        L_0x0068:
            java.lang.String r0 = "com.google.android.gms.ads.internal.request.IAdResponseListener"
            android.os.IInterface r0 = r5.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.ads.zzaeq
            if (r1 == 0) goto L_0x0076
            r1 = r0
            com.google.android.gms.internal.ads.zzaeq r1 = (com.google.android.gms.internal.ads.zzaeq) r1
            goto L_0x007b
        L_0x0076:
            com.google.android.gms.internal.ads.zzaes r1 = new com.google.android.gms.internal.ads.zzaes
            r1.<init>(r5)
        L_0x007b:
            r3.zza((com.google.android.gms.internal.ads.zzaef) r4, (com.google.android.gms.internal.ads.zzaeq) r1)
        L_0x007e:
            r6.writeNoException()
            goto L_0x0094
        L_0x0082:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaef> r4 = com.google.android.gms.internal.ads.zzaef.CREATOR
            android.os.Parcelable r4 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r5, r4)
            com.google.android.gms.internal.ads.zzaef r4 = (com.google.android.gms.internal.ads.zzaef) r4
            com.google.android.gms.internal.ads.zzaej r4 = r3.zzb(r4)
            r6.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r6, r4)
        L_0x0094:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaeo.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
