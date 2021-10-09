package com.google.android.gms.internal.ads;

public abstract class zzxr extends zzek implements zzxq {
    public zzxr() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    /* JADX WARNING: type inference failed for: r11v3, types: [android.os.IInterface] */
    /* JADX WARNING: type inference failed for: r11v4, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0072, code lost:
        r12.writeNoException();
        com.google.android.gms.internal.ads.zzel.zzb(r12, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00cf, code lost:
        r12.writeNoException();
        com.google.android.gms.internal.ads.zzel.zza(r12, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x019e, code lost:
        r12.writeNoException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01d6, code lost:
        r12.writeNoException();
        com.google.android.gms.internal.ads.zzel.zza(r12, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        return true;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r10, android.os.Parcel r11, android.os.Parcel r12, int r13) throws android.os.RemoteException {
        /*
            r9 = this;
            r13 = 0
            java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener"
            switch(r10) {
                case 1: goto L_0x01dd;
                case 2: goto L_0x01d2;
                case 3: goto L_0x01a3;
                case 4: goto L_0x019b;
                case 5: goto L_0x0197;
                case 6: goto L_0x0156;
                case 7: goto L_0x011e;
                case 8: goto L_0x0119;
                case 9: goto L_0x0114;
                case 10: goto L_0x00ed;
                case 11: goto L_0x00dc;
                case 12: goto L_0x00d7;
                case 13: goto L_0x00cb;
                case 14: goto L_0x0086;
                case 15: goto L_0x0080;
                case 16: goto L_0x007a;
                case 17: goto L_0x006e;
                case 18: goto L_0x0069;
                case 19: goto L_0x0064;
                case 20: goto L_0x004f;
                case 21: goto L_0x0042;
                case 22: goto L_0x003c;
                case 23: goto L_0x0023;
                case 24: goto L_0x001d;
                case 25: goto L_0x0014;
                case 26: goto L_0x000e;
                case 27: goto L_0x0008;
                default: goto L_0x0006;
            }
        L_0x0006:
            r10 = 0
            return r10
        L_0x0008:
            com.google.android.gms.internal.ads.zzyf r10 = r9.zzmu()
            goto L_0x01d6
        L_0x000e:
            com.google.android.gms.internal.ads.zzlo r10 = r9.getVideoController()
            goto L_0x01d6
        L_0x0014:
            boolean r10 = com.google.android.gms.internal.ads.zzel.zza(r11)
            r9.setImmersiveMode(r10)
            goto L_0x019e
        L_0x001d:
            com.google.android.gms.internal.ads.zzqs r10 = r9.zzmt()
            goto L_0x01d6
        L_0x0023:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.IBinder r13 = r11.readStrongBinder()
            com.google.android.gms.internal.ads.zzaic r13 = com.google.android.gms.internal.ads.zzaid.zzaa(r13)
            java.util.ArrayList r11 = r11.createStringArrayList()
            r9.zza((com.google.android.gms.dynamic.IObjectWrapper) r10, (com.google.android.gms.internal.ads.zzaic) r13, (java.util.List<java.lang.String>) r11)
            goto L_0x019e
        L_0x003c:
            boolean r10 = r9.zzms()
            goto L_0x00cf
        L_0x0042:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            r9.zzi(r10)
            goto L_0x019e
        L_0x004f:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r10 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r10)
            com.google.android.gms.internal.ads.zzjj r10 = (com.google.android.gms.internal.ads.zzjj) r10
            java.lang.String r13 = r11.readString()
            java.lang.String r11 = r11.readString()
            r9.zza((com.google.android.gms.internal.ads.zzjj) r10, (java.lang.String) r13, (java.lang.String) r11)
            goto L_0x019e
        L_0x0064:
            android.os.Bundle r10 = r9.zzmr()
            goto L_0x0072
        L_0x0069:
            android.os.Bundle r10 = r9.getInterstitialAdapterInfo()
            goto L_0x0072
        L_0x006e:
            android.os.Bundle r10 = r9.zzmq()
        L_0x0072:
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r12, r10)
            goto L_0x021a
        L_0x007a:
            com.google.android.gms.internal.ads.zzyc r10 = r9.zzmp()
            goto L_0x01d6
        L_0x0080:
            com.google.android.gms.internal.ads.zzxz r10 = r9.zzmo()
            goto L_0x01d6
        L_0x0086:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r10 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r10)
            r3 = r10
            com.google.android.gms.internal.ads.zzjj r3 = (com.google.android.gms.internal.ads.zzjj) r3
            java.lang.String r4 = r11.readString()
            java.lang.String r5 = r11.readString()
            android.os.IBinder r10 = r11.readStrongBinder()
            if (r10 != 0) goto L_0x00a7
        L_0x00a5:
            r6 = r13
            goto L_0x00b8
        L_0x00a7:
            android.os.IInterface r13 = r10.queryLocalInterface(r0)
            boolean r0 = r13 instanceof com.google.android.gms.internal.ads.zzxt
            if (r0 == 0) goto L_0x00b2
            com.google.android.gms.internal.ads.zzxt r13 = (com.google.android.gms.internal.ads.zzxt) r13
            goto L_0x00a5
        L_0x00b2:
            com.google.android.gms.internal.ads.zzxv r13 = new com.google.android.gms.internal.ads.zzxv
            r13.<init>(r10)
            goto L_0x00a5
        L_0x00b8:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzpl> r10 = com.google.android.gms.internal.ads.zzpl.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r10)
            r7 = r10
            com.google.android.gms.internal.ads.zzpl r7 = (com.google.android.gms.internal.ads.zzpl) r7
            java.util.ArrayList r8 = r11.createStringArrayList()
            r1 = r9
            r1.zza(r2, r3, r4, r5, r6, r7, r8)
            goto L_0x019e
        L_0x00cb:
            boolean r10 = r9.isInitialized()
        L_0x00cf:
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r12, (boolean) r10)
            goto L_0x021a
        L_0x00d7:
            r9.showVideo()
            goto L_0x019e
        L_0x00dc:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r10 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r10)
            com.google.android.gms.internal.ads.zzjj r10 = (com.google.android.gms.internal.ads.zzjj) r10
            java.lang.String r11 = r11.readString()
            r9.zzc(r10, r11)
            goto L_0x019e
        L_0x00ed:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r10 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r10)
            r2 = r10
            com.google.android.gms.internal.ads.zzjj r2 = (com.google.android.gms.internal.ads.zzjj) r2
            java.lang.String r3 = r11.readString()
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.internal.ads.zzaic r4 = com.google.android.gms.internal.ads.zzaid.zzaa(r10)
            java.lang.String r5 = r11.readString()
            r0 = r9
            r0.zza((com.google.android.gms.dynamic.IObjectWrapper) r1, (com.google.android.gms.internal.ads.zzjj) r2, (java.lang.String) r3, (com.google.android.gms.internal.ads.zzaic) r4, (java.lang.String) r5)
            goto L_0x019e
        L_0x0114:
            r9.resume()
            goto L_0x019e
        L_0x0119:
            r9.pause()
            goto L_0x019e
        L_0x011e:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r10 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r10)
            r3 = r10
            com.google.android.gms.internal.ads.zzjj r3 = (com.google.android.gms.internal.ads.zzjj) r3
            java.lang.String r4 = r11.readString()
            java.lang.String r5 = r11.readString()
            android.os.IBinder r10 = r11.readStrongBinder()
            if (r10 != 0) goto L_0x013f
        L_0x013d:
            r6 = r13
            goto L_0x0151
        L_0x013f:
            android.os.IInterface r11 = r10.queryLocalInterface(r0)
            boolean r13 = r11 instanceof com.google.android.gms.internal.ads.zzxt
            if (r13 == 0) goto L_0x014b
            r13 = r11
            com.google.android.gms.internal.ads.zzxt r13 = (com.google.android.gms.internal.ads.zzxt) r13
            goto L_0x013d
        L_0x014b:
            com.google.android.gms.internal.ads.zzxv r13 = new com.google.android.gms.internal.ads.zzxv
            r13.<init>(r10)
            goto L_0x013d
        L_0x0151:
            r1 = r9
            r1.zza((com.google.android.gms.dynamic.IObjectWrapper) r2, (com.google.android.gms.internal.ads.zzjj) r3, (java.lang.String) r4, (java.lang.String) r5, (com.google.android.gms.internal.ads.zzxt) r6)
            goto L_0x019e
        L_0x0156:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjn> r10 = com.google.android.gms.internal.ads.zzjn.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r10)
            r3 = r10
            com.google.android.gms.internal.ads.zzjn r3 = (com.google.android.gms.internal.ads.zzjn) r3
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r10 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r10)
            r4 = r10
            com.google.android.gms.internal.ads.zzjj r4 = (com.google.android.gms.internal.ads.zzjj) r4
            java.lang.String r5 = r11.readString()
            java.lang.String r6 = r11.readString()
            android.os.IBinder r10 = r11.readStrongBinder()
            if (r10 != 0) goto L_0x0180
        L_0x017e:
            r7 = r13
            goto L_0x0192
        L_0x0180:
            android.os.IInterface r11 = r10.queryLocalInterface(r0)
            boolean r13 = r11 instanceof com.google.android.gms.internal.ads.zzxt
            if (r13 == 0) goto L_0x018c
            r13 = r11
            com.google.android.gms.internal.ads.zzxt r13 = (com.google.android.gms.internal.ads.zzxt) r13
            goto L_0x017e
        L_0x018c:
            com.google.android.gms.internal.ads.zzxv r13 = new com.google.android.gms.internal.ads.zzxv
            r13.<init>(r10)
            goto L_0x017e
        L_0x0192:
            r1 = r9
            r1.zza(r2, r3, r4, r5, r6, r7)
            goto L_0x019e
        L_0x0197:
            r9.destroy()
            goto L_0x019e
        L_0x019b:
            r9.showInterstitial()
        L_0x019e:
            r12.writeNoException()
            goto L_0x021a
        L_0x01a3:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r1 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r1)
            com.google.android.gms.internal.ads.zzjj r1 = (com.google.android.gms.internal.ads.zzjj) r1
            java.lang.String r2 = r11.readString()
            android.os.IBinder r11 = r11.readStrongBinder()
            if (r11 != 0) goto L_0x01be
            goto L_0x01ce
        L_0x01be:
            android.os.IInterface r13 = r11.queryLocalInterface(r0)
            boolean r0 = r13 instanceof com.google.android.gms.internal.ads.zzxt
            if (r0 == 0) goto L_0x01c9
            com.google.android.gms.internal.ads.zzxt r13 = (com.google.android.gms.internal.ads.zzxt) r13
            goto L_0x01ce
        L_0x01c9:
            com.google.android.gms.internal.ads.zzxv r13 = new com.google.android.gms.internal.ads.zzxv
            r13.<init>(r11)
        L_0x01ce:
            r9.zza(r10, r1, r2, r13)
            goto L_0x019e
        L_0x01d2:
            com.google.android.gms.dynamic.IObjectWrapper r10 = r9.getView()
        L_0x01d6:
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r12, (android.os.IInterface) r10)
            goto L_0x021a
        L_0x01dd:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjn> r10 = com.google.android.gms.internal.ads.zzjn.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r10)
            r3 = r10
            com.google.android.gms.internal.ads.zzjn r3 = (com.google.android.gms.internal.ads.zzjn) r3
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r10 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, r10)
            r4 = r10
            com.google.android.gms.internal.ads.zzjj r4 = (com.google.android.gms.internal.ads.zzjj) r4
            java.lang.String r5 = r11.readString()
            android.os.IBinder r10 = r11.readStrongBinder()
            if (r10 != 0) goto L_0x0203
        L_0x0201:
            r6 = r13
            goto L_0x0215
        L_0x0203:
            android.os.IInterface r11 = r10.queryLocalInterface(r0)
            boolean r13 = r11 instanceof com.google.android.gms.internal.ads.zzxt
            if (r13 == 0) goto L_0x020f
            r13 = r11
            com.google.android.gms.internal.ads.zzxt r13 = (com.google.android.gms.internal.ads.zzxt) r13
            goto L_0x0201
        L_0x020f:
            com.google.android.gms.internal.ads.zzxv r13 = new com.google.android.gms.internal.ads.zzxv
            r13.<init>(r10)
            goto L_0x0201
        L_0x0215:
            r1 = r9
            r1.zza((com.google.android.gms.dynamic.IObjectWrapper) r2, (com.google.android.gms.internal.ads.zzjn) r3, (com.google.android.gms.internal.ads.zzjj) r4, (java.lang.String) r5, (com.google.android.gms.internal.ads.zzxt) r6)
            goto L_0x019e
        L_0x021a:
            r10 = 1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxr.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
