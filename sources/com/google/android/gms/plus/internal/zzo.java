package com.google.android.gms.plus.internal;

import android.os.Parcelable;

public final class zzo implements Parcelable.Creator<zzn> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r15)
            r1 = 0
            r2 = 0
            r5 = r1
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r4 = 0
        L_0x0010:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x0060
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r15)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            r3 = 1000(0x3e8, float:1.401E-42)
            if (r2 == r3) goto L_0x005b
            switch(r2) {
                case 1: goto L_0x0056;
                case 2: goto L_0x0051;
                case 3: goto L_0x004c;
                case 4: goto L_0x0047;
                case 5: goto L_0x0042;
                case 6: goto L_0x003d;
                case 7: goto L_0x0038;
                case 8: goto L_0x0033;
                case 9: goto L_0x0029;
                default: goto L_0x0025;
            }
        L_0x0025:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r15, r1)
            goto L_0x0010
        L_0x0029:
            android.os.Parcelable$Creator<com.google.android.gms.plus.internal.PlusCommonExtras> r2 = com.google.android.gms.plus.internal.PlusCommonExtras.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r13 = r1
            com.google.android.gms.plus.internal.PlusCommonExtras r13 = (com.google.android.gms.plus.internal.PlusCommonExtras) r13
            goto L_0x0010
        L_0x0033:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x0038:
            java.lang.String r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x003d:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x0042:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x0047:
            java.lang.String[] r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringArray(r15, r1)
            goto L_0x0010
        L_0x004c:
            java.lang.String[] r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringArray(r15, r1)
            goto L_0x0010
        L_0x0051:
            java.lang.String[] r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringArray(r15, r1)
            goto L_0x0010
        L_0x0056:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x005b:
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r15, r1)
            goto L_0x0010
        L_0x0060:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r15, r0)
            com.google.android.gms.plus.internal.zzn r15 = new com.google.android.gms.plus.internal.zzn
            r3 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.plus.internal.zzo.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzn[i];
    }
}
