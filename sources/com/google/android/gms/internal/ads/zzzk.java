package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzzk extends zzek implements zzzj {
    public zzzk() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzzj zzt(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        return queryLocalInterface instanceof zzzj ? (zzzj) queryLocalInterface : new zzzl(iBinder);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: com.google.android.gms.internal.ads.zzzn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: com.google.android.gms.internal.ads.zzzn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: com.google.android.gms.internal.ads.zzzn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v16, resolved type: com.google.android.gms.internal.ads.zzzn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v17, resolved type: com.google.android.gms.internal.ads.zzzn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v19, resolved type: com.google.android.gms.internal.ads.zzzn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v20, resolved type: com.google.android.gms.internal.ads.zzzn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v21, resolved type: com.google.android.gms.internal.ads.zzzn} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v22, resolved type: com.google.android.gms.internal.ads.zzzn} */
    /* JADX WARNING: type inference failed for: r12v9, types: [com.google.android.gms.internal.ads.zzzg] */
    /* JADX WARNING: type inference failed for: r12v10, types: [com.google.android.gms.internal.ads.zzzf] */
    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.android.gms.internal.ads.zzzf] */
    /* JADX WARNING: type inference failed for: r12v14, types: [com.google.android.gms.internal.ads.zzzi] */
    /* JADX WARNING: type inference failed for: r12v15, types: [com.google.android.gms.internal.ads.zzzh] */
    /* JADX WARNING: type inference failed for: r5v1, types: [com.google.android.gms.internal.ads.zzzh] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00af, code lost:
        r11.writeNoException();
        com.google.android.gms.internal.ads.zzel.zzb(r11, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0009, code lost:
        r11.writeNoException();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchTransaction(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
        /*
            r8 = this;
            r12 = 0
            switch(r9) {
                case 1: goto L_0x00b6;
                case 2: goto L_0x00ab;
                case 3: goto L_0x00a6;
                case 4: goto L_0x005b;
                case 5: goto L_0x004f;
                case 6: goto L_0x000e;
                case 7: goto L_0x0006;
                default: goto L_0x0004;
            }
        L_0x0004:
            r9 = 0
            return r9
        L_0x0006:
            r8.showInterstitial()
        L_0x0009:
            r11.writeNoException()
            goto L_0x00e8
        L_0x000e:
            byte[] r1 = r10.createByteArray()
            java.lang.String r2 = r10.readString()
            android.os.Parcelable$Creator r9 = android.os.Bundle.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r10, r9)
            r3 = r9
            android.os.Bundle r3 = (android.os.Bundle) r3
            android.os.IBinder r9 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r9)
            android.os.IBinder r9 = r10.readStrongBinder()
            if (r9 != 0) goto L_0x002f
        L_0x002d:
            r5 = r12
            goto L_0x0042
        L_0x002f:
            java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback"
            android.os.IInterface r12 = r9.queryLocalInterface(r12)
            boolean r0 = r12 instanceof com.google.android.gms.internal.ads.zzzh
            if (r0 == 0) goto L_0x003c
            com.google.android.gms.internal.ads.zzzh r12 = (com.google.android.gms.internal.ads.zzzh) r12
            goto L_0x002d
        L_0x003c:
            com.google.android.gms.internal.ads.zzzi r12 = new com.google.android.gms.internal.ads.zzzi
            r12.<init>(r9)
            goto L_0x002d
        L_0x0042:
            android.os.IBinder r9 = r10.readStrongBinder()
            com.google.android.gms.internal.ads.zzxt r6 = com.google.android.gms.internal.ads.zzxu.zzs(r9)
            r0 = r8
            r0.zza(r1, r2, r3, r4, r5, r6)
            goto L_0x0009
        L_0x004f:
            com.google.android.gms.internal.ads.zzlo r9 = r8.getVideoController()
            r11.writeNoException()
            com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r11, (android.os.IInterface) r9)
            goto L_0x00e8
        L_0x005b:
            byte[] r1 = r10.createByteArray()
            java.lang.String r2 = r10.readString()
            android.os.Parcelable$Creator r9 = android.os.Bundle.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r10, r9)
            r3 = r9
            android.os.Bundle r3 = (android.os.Bundle) r3
            android.os.IBinder r9 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r9)
            android.os.IBinder r9 = r10.readStrongBinder()
            if (r9 != 0) goto L_0x007c
        L_0x007a:
            r5 = r12
            goto L_0x008f
        L_0x007c:
            java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback"
            android.os.IInterface r12 = r9.queryLocalInterface(r12)
            boolean r0 = r12 instanceof com.google.android.gms.internal.ads.zzzf
            if (r0 == 0) goto L_0x0089
            com.google.android.gms.internal.ads.zzzf r12 = (com.google.android.gms.internal.ads.zzzf) r12
            goto L_0x007a
        L_0x0089:
            com.google.android.gms.internal.ads.zzzg r12 = new com.google.android.gms.internal.ads.zzzg
            r12.<init>(r9)
            goto L_0x007a
        L_0x008f:
            android.os.IBinder r9 = r10.readStrongBinder()
            com.google.android.gms.internal.ads.zzxt r6 = com.google.android.gms.internal.ads.zzxu.zzs(r9)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjn> r9 = com.google.android.gms.internal.ads.zzjn.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r10, r9)
            r7 = r9
            com.google.android.gms.internal.ads.zzjn r7 = (com.google.android.gms.internal.ads.zzjn) r7
            r0 = r8
            r0.zza(r1, r2, r3, r4, r5, r6, r7)
            goto L_0x0009
        L_0x00a6:
            com.google.android.gms.internal.ads.zzzt r9 = r8.zznd()
            goto L_0x00af
        L_0x00ab:
            com.google.android.gms.internal.ads.zzzt r9 = r8.zznc()
        L_0x00af:
            r11.writeNoException()
            com.google.android.gms.internal.ads.zzel.zzb(r11, r9)
            goto L_0x00e8
        L_0x00b6:
            android.os.IBinder r9 = r10.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r9 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r9)
            java.lang.String r0 = r10.readString()
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzel.zza((android.os.Parcel) r10, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            android.os.IBinder r10 = r10.readStrongBinder()
            if (r10 != 0) goto L_0x00d1
            goto L_0x00e3
        L_0x00d1:
            java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback"
            android.os.IInterface r12 = r10.queryLocalInterface(r12)
            boolean r2 = r12 instanceof com.google.android.gms.internal.ads.zzzm
            if (r2 == 0) goto L_0x00de
            com.google.android.gms.internal.ads.zzzm r12 = (com.google.android.gms.internal.ads.zzzm) r12
            goto L_0x00e3
        L_0x00de:
            com.google.android.gms.internal.ads.zzzn r12 = new com.google.android.gms.internal.ads.zzzn
            r12.<init>(r10)
        L_0x00e3:
            r8.zza(r9, r0, r1, r12)
            goto L_0x0009
        L_0x00e8:
            r9 = 1
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzzk.dispatchTransaction(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
