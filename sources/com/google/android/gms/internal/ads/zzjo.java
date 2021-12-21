package com.google.android.gms.internal.ads;

import android.os.Parcelable;

public final class zzjo implements Parcelable.Creator<zzjn> {
    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r15)
            r1 = 0
            r2 = 0
            r4 = r1
            r10 = r4
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x0010:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x005c
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r15)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 2: goto L_0x0057;
                case 3: goto L_0x0052;
                case 4: goto L_0x004d;
                case 5: goto L_0x0048;
                case 6: goto L_0x0043;
                case 7: goto L_0x003e;
                case 8: goto L_0x0034;
                case 9: goto L_0x002f;
                case 10: goto L_0x002a;
                case 11: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r15, r1)
            goto L_0x0010
        L_0x0025:
            boolean r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r15, r1)
            goto L_0x0010
        L_0x002a:
            boolean r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r15, r1)
            goto L_0x0010
        L_0x002f:
            boolean r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r15, r1)
            goto L_0x0010
        L_0x0034:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjn> r2 = com.google.android.gms.internal.ads.zzjn.CREATOR
            java.lang.Object[] r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedArray(r15, r1, r2)
            r10 = r1
            com.google.android.gms.internal.ads.zzjn[] r10 = (com.google.android.gms.internal.ads.zzjn[]) r10
            goto L_0x0010
        L_0x003e:
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r15, r1)
            goto L_0x0010
        L_0x0043:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r15, r1)
            goto L_0x0010
        L_0x0048:
            boolean r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r15, r1)
            goto L_0x0010
        L_0x004d:
            int r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r15, r1)
            goto L_0x0010
        L_0x0052:
            int r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r15, r1)
            goto L_0x0010
        L_0x0057:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x005c:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r15, r0)
            com.google.android.gms.internal.ads.zzjn r15 = new com.google.android.gms.internal.ads.zzjn
            r3 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjo.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzjn[i];
    }
}
