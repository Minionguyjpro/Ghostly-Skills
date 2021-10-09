package com.google.android.gms.internal.plus;

import android.os.Parcelable;

public final class zzs implements Parcelable.Creator<zzr> {
    /* JADX WARNING: type inference failed for: r2v10, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v15, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v21, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v27, types: [android.os.Parcelable] */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00bd, code lost:
        r3.add(java.lang.Integer.valueOf(r2));
        r15 = r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x014e, code lost:
        r3.add(java.lang.Integer.valueOf(r2));
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r31) {
        /*
            r30 = this;
            r0 = r31
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r31)
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            r2 = 0
            r4 = 0
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r10 = r8
            r11 = r10
            r12 = r11
            r14 = r12
            r15 = r14
            r17 = r15
            r18 = r17
            r19 = r18
            r21 = r19
            r22 = r21
            r25 = r22
            r26 = r25
            r27 = r26
            r4 = 0
            r9 = 0
            r13 = 0
            r16 = 0
            r20 = 0
            r23 = 0
            r24 = 0
            r28 = 0
        L_0x0033:
            int r2 = r31.dataPosition()
            if (r2 >= r1) goto L_0x0157
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r31)
            int r29 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r29) {
                case 1: goto L_0x0147;
                case 2: goto L_0x013f;
                case 3: goto L_0x0132;
                case 4: goto L_0x012a;
                case 5: goto L_0x0122;
                case 6: goto L_0x011a;
                case 7: goto L_0x010d;
                case 8: goto L_0x0104;
                case 9: goto L_0x00fb;
                case 10: goto L_0x0044;
                case 11: goto L_0x0044;
                case 12: goto L_0x00f2;
                case 13: goto L_0x0044;
                case 14: goto L_0x00e9;
                case 15: goto L_0x00dc;
                case 16: goto L_0x00d2;
                case 17: goto L_0x0044;
                case 18: goto L_0x00c8;
                case 19: goto L_0x00af;
                case 20: goto L_0x00a5;
                case 21: goto L_0x009b;
                case 22: goto L_0x0090;
                case 23: goto L_0x0085;
                case 24: goto L_0x007b;
                case 25: goto L_0x0071;
                case 26: goto L_0x0067;
                case 27: goto L_0x005d;
                case 28: goto L_0x0052;
                case 29: goto L_0x004a;
                default: goto L_0x0044;
            }
        L_0x0044:
            r29 = r15
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0033
        L_0x004a:
            boolean r28 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            r2 = 29
            goto L_0x014e
        L_0x0052:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzg> r15 = com.google.android.gms.internal.plus.zzr.zzg.CREATOR
            java.util.ArrayList r27 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r15)
            r2 = 28
            goto L_0x00bd
        L_0x005d:
            r29 = r15
            java.lang.String r26 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 27
            goto L_0x014e
        L_0x0067:
            r29 = r15
            java.lang.String r25 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 26
            goto L_0x014e
        L_0x0071:
            r29 = r15
            int r24 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 25
            goto L_0x014e
        L_0x007b:
            r29 = r15
            int r23 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 24
            goto L_0x014e
        L_0x0085:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzf> r15 = com.google.android.gms.internal.plus.zzr.zzf.CREATOR
            java.util.ArrayList r22 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r15)
            r2 = 23
            goto L_0x00bd
        L_0x0090:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zze> r15 = com.google.android.gms.internal.plus.zzr.zze.CREATOR
            java.util.ArrayList r21 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r15)
            r2 = 22
            goto L_0x00bd
        L_0x009b:
            r29 = r15
            int r20 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 21
            goto L_0x014e
        L_0x00a5:
            r29 = r15
            java.lang.String r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 20
            goto L_0x014e
        L_0x00af:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzd> r15 = com.google.android.gms.internal.plus.zzr.zzd.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r15)
            r18 = r2
            com.google.android.gms.internal.plus.zzr$zzd r18 = (com.google.android.gms.internal.plus.zzr.zzd) r18
            r2 = 19
        L_0x00bd:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3.add(r2)
            r15 = r29
            goto L_0x0033
        L_0x00c8:
            r29 = r15
            java.lang.String r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 18
            goto L_0x014e
        L_0x00d2:
            r29 = r15
            boolean r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            r2 = 16
            goto L_0x014e
        L_0x00dc:
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzc> r15 = com.google.android.gms.internal.plus.zzr.zzc.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r15)
            r15 = r2
            com.google.android.gms.internal.plus.zzr$zzc r15 = (com.google.android.gms.internal.plus.zzr.zzc) r15
            r2 = 15
            goto L_0x014e
        L_0x00e9:
            r29 = r15
            java.lang.String r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 14
            goto L_0x014e
        L_0x00f2:
            r29 = r15
            int r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 12
            goto L_0x014e
        L_0x00fb:
            r29 = r15
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 9
            goto L_0x014e
        L_0x0104:
            r29 = r15
            java.lang.String r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 8
            goto L_0x014e
        L_0x010d:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzb> r10 = com.google.android.gms.internal.plus.zzr.zzb.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r10)
            r10 = r2
            com.google.android.gms.internal.plus.zzr$zzb r10 = (com.google.android.gms.internal.plus.zzr.zzb) r10
            r2 = 7
            goto L_0x014e
        L_0x011a:
            r29 = r15
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 6
            goto L_0x014e
        L_0x0122:
            r29 = r15
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 5
            goto L_0x014e
        L_0x012a:
            r29 = r15
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 4
            goto L_0x014e
        L_0x0132:
            r29 = r15
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zza> r6 = com.google.android.gms.internal.plus.zzr.zza.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r6)
            r6 = r2
            com.google.android.gms.internal.plus.zzr$zza r6 = (com.google.android.gms.internal.plus.zzr.zza) r6
            r2 = 3
            goto L_0x014e
        L_0x013f:
            r29 = r15
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            r2 = 2
            goto L_0x014e
        L_0x0147:
            r29 = r15
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            r2 = 1
        L_0x014e:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3.add(r2)
            goto L_0x0033
        L_0x0157:
            r29 = r15
            int r2 = r31.dataPosition()
            if (r2 != r1) goto L_0x0168
            com.google.android.gms.internal.plus.zzr r0 = new com.google.android.gms.internal.plus.zzr
            r2 = r0
            r15 = r29
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            return r0
        L_0x0168:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException r2 = new com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException
            r3 = 37
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Overread allowed size end="
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r2.<init>(r1, r0)
            goto L_0x0182
        L_0x0181:
            throw r2
        L_0x0182:
            goto L_0x0181
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.plus.zzs.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr[i];
    }
}
