package com.google.android.gms.ads.internal.overlay;

import android.os.Parcelable;

public final class zzm implements Parcelable.Creator<AdOverlayInfoParcel> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r22) {
        /*
            r21 = this;
            r0 = r22
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r22)
            r2 = 0
            r3 = 0
            r5 = r3
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r12 = r10
            r13 = r12
            r16 = r13
            r17 = r16
            r18 = r17
            r19 = r18
            r20 = r19
            r11 = 0
            r14 = 0
            r15 = 0
        L_0x001d:
            int r2 = r22.dataPosition()
            if (r2 >= r1) goto L_0x0093
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r22)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x0089;
                case 3: goto L_0x0084;
                case 4: goto L_0x007f;
                case 5: goto L_0x007a;
                case 6: goto L_0x0075;
                case 7: goto L_0x0070;
                case 8: goto L_0x006b;
                case 9: goto L_0x0066;
                case 10: goto L_0x0061;
                case 11: goto L_0x005c;
                case 12: goto L_0x0057;
                case 13: goto L_0x0052;
                case 14: goto L_0x0047;
                case 15: goto L_0x002e;
                case 16: goto L_0x0042;
                case 17: goto L_0x0037;
                case 18: goto L_0x0032;
                default: goto L_0x002e;
            }
        L_0x002e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x001d
        L_0x0032:
            android.os.IBinder r20 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r0, r2)
            goto L_0x001d
        L_0x0037:
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.zzaq> r3 = com.google.android.gms.ads.internal.zzaq.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r19 = r2
            com.google.android.gms.ads.internal.zzaq r19 = (com.google.android.gms.ads.internal.zzaq) r19
            goto L_0x001d
        L_0x0042:
            java.lang.String r18 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x001d
        L_0x0047:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzang> r3 = com.google.android.gms.internal.ads.zzang.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r17 = r2
            com.google.android.gms.internal.ads.zzang r17 = (com.google.android.gms.internal.ads.zzang) r17
            goto L_0x001d
        L_0x0052:
            java.lang.String r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x001d
        L_0x0057:
            int r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x001d
        L_0x005c:
            int r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x001d
        L_0x0061:
            android.os.IBinder r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r0, r2)
            goto L_0x001d
        L_0x0066:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x001d
        L_0x006b:
            boolean r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x001d
        L_0x0070:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x001d
        L_0x0075:
            android.os.IBinder r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r0, r2)
            goto L_0x001d
        L_0x007a:
            android.os.IBinder r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r0, r2)
            goto L_0x001d
        L_0x007f:
            android.os.IBinder r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r0, r2)
            goto L_0x001d
        L_0x0084:
            android.os.IBinder r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIBinder(r0, r2)
            goto L_0x001d
        L_0x0089:
            android.os.Parcelable$Creator<com.google.android.gms.ads.internal.overlay.zzc> r3 = com.google.android.gms.ads.internal.overlay.zzc.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r5 = r2
            com.google.android.gms.ads.internal.overlay.zzc r5 = (com.google.android.gms.ads.internal.overlay.zzc) r5
            goto L_0x001d
        L_0x0093:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel r0 = new com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zzm.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new AdOverlayInfoParcel[i];
    }
}
