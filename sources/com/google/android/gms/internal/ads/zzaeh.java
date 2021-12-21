package com.google.android.gms.internal.ads;

import android.os.Parcelable;

public final class zzaeh implements Parcelable.Creator<zzaef> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v7, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v8, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v9, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r66) {
        /*
            r65 = this;
            r0 = r66
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r66)
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r28 = r2
            r35 = r28
            r9 = r6
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r16 = r15
            r17 = r16
            r18 = r17
            r19 = r18
            r21 = r19
            r22 = r21
            r27 = r22
            r30 = r27
            r31 = r30
            r32 = r31
            r33 = r32
            r34 = r33
            r37 = r34
            r44 = r37
            r45 = r44
            r48 = r45
            r49 = r48
            r50 = r49
            r52 = r50
            r53 = r52
            r54 = r53
            r55 = r54
            r57 = r55
            r58 = r57
            r59 = r58
            r64 = r59
            r8 = 0
            r20 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r42 = 0
            r43 = 0
            r46 = 0
            r47 = 0
            r51 = 0
            r56 = 0
            r60 = 0
            r61 = 0
            r62 = 0
            r63 = 0
        L_0x0071:
            int r2 = r66.dataPosition()
            if (r2 >= r1) goto L_0x01e2
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r66)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 1: goto L_0x01dc;
                case 2: goto L_0x01d6;
                case 3: goto L_0x01cb;
                case 4: goto L_0x01c0;
                case 5: goto L_0x01ba;
                case 6: goto L_0x01af;
                case 7: goto L_0x01a4;
                case 8: goto L_0x019e;
                case 9: goto L_0x0198;
                case 10: goto L_0x0192;
                case 11: goto L_0x0186;
                case 12: goto L_0x0180;
                case 13: goto L_0x017a;
                case 14: goto L_0x0174;
                case 15: goto L_0x016e;
                case 16: goto L_0x0168;
                case 17: goto L_0x0082;
                case 18: goto L_0x0162;
                case 19: goto L_0x015c;
                case 20: goto L_0x0156;
                case 21: goto L_0x0150;
                case 22: goto L_0x0082;
                case 23: goto L_0x0082;
                case 24: goto L_0x0082;
                case 25: goto L_0x014a;
                case 26: goto L_0x0144;
                case 27: goto L_0x013e;
                case 28: goto L_0x0138;
                case 29: goto L_0x012c;
                case 30: goto L_0x0126;
                case 31: goto L_0x0120;
                case 32: goto L_0x0082;
                case 33: goto L_0x011a;
                case 34: goto L_0x0114;
                case 35: goto L_0x010e;
                case 36: goto L_0x0108;
                case 37: goto L_0x0102;
                case 38: goto L_0x00fc;
                case 39: goto L_0x00f6;
                case 40: goto L_0x00f0;
                case 41: goto L_0x00eb;
                case 42: goto L_0x00e6;
                case 43: goto L_0x00e1;
                case 44: goto L_0x00dc;
                case 45: goto L_0x00d7;
                case 46: goto L_0x00cc;
                case 47: goto L_0x00c7;
                case 48: goto L_0x00c2;
                case 49: goto L_0x00bd;
                case 50: goto L_0x00b8;
                case 51: goto L_0x00b3;
                case 52: goto L_0x00ae;
                case 53: goto L_0x00a9;
                case 54: goto L_0x00a4;
                case 55: goto L_0x009f;
                case 56: goto L_0x009a;
                case 57: goto L_0x0095;
                case 58: goto L_0x0090;
                case 59: goto L_0x008b;
                case 60: goto L_0x0086;
                default: goto L_0x0082;
            }
        L_0x0082:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0071
        L_0x0086:
            java.util.ArrayList r64 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0071
        L_0x008b:
            boolean r63 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0071
        L_0x0090:
            boolean r62 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0071
        L_0x0095:
            boolean r61 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0071
        L_0x009a:
            int r60 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0071
        L_0x009f:
            java.util.ArrayList r59 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0071
        L_0x00a4:
            java.lang.String r58 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x00a9:
            java.util.ArrayList r57 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createIntegerList(r0, r2)
            goto L_0x0071
        L_0x00ae:
            boolean r56 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0071
        L_0x00b3:
            java.lang.String r55 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x00b8:
            java.lang.String r54 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x00bd:
            java.lang.String r53 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x00c2:
            android.os.Bundle r52 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0071
        L_0x00c7:
            boolean r51 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0071
        L_0x00cc:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzlu> r3 = com.google.android.gms.internal.ads.zzlu.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r50 = r2
            com.google.android.gms.internal.ads.zzlu r50 = (com.google.android.gms.internal.ads.zzlu) r50
            goto L_0x0071
        L_0x00d7:
            java.lang.String r49 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x00dc:
            android.os.Bundle r48 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0071
        L_0x00e1:
            int r47 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0071
        L_0x00e6:
            boolean r46 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0071
        L_0x00eb:
            java.lang.String r45 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x00f0:
            boolean r39 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0071
        L_0x00f6:
            java.lang.String r44 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x00fc:
            boolean r43 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0071
        L_0x0102:
            boolean r42 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0071
        L_0x0108:
            int r41 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0071
        L_0x010e:
            int r40 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0071
        L_0x0114:
            float r38 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0071
        L_0x011a:
            java.lang.String r37 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x0120:
            long r35 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0071
        L_0x0126:
            java.util.ArrayList r34 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0071
        L_0x012c:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzpl> r3 = com.google.android.gms.internal.ads.zzpl.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r33 = r2
            com.google.android.gms.internal.ads.zzpl r33 = (com.google.android.gms.internal.ads.zzpl) r33
            goto L_0x0071
        L_0x0138:
            java.lang.String r32 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x013e:
            java.util.ArrayList r31 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0071
        L_0x0144:
            java.lang.String r30 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x014a:
            long r28 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0071
        L_0x0150:
            java.lang.String r27 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x0156:
            float r26 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0071
        L_0x015c:
            int r25 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0071
        L_0x0162:
            int r24 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0071
        L_0x0168:
            boolean r23 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0071
        L_0x016e:
            android.os.Bundle r22 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0071
        L_0x0174:
            java.util.ArrayList r21 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0071
        L_0x017a:
            int r20 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0071
        L_0x0180:
            android.os.Bundle r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0071
        L_0x0186:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzang> r3 = com.google.android.gms.internal.ads.zzang.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r18 = r2
            com.google.android.gms.internal.ads.zzang r18 = (com.google.android.gms.internal.ads.zzang) r18
            goto L_0x0071
        L_0x0192:
            java.lang.String r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x0198:
            java.lang.String r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x019e:
            java.lang.String r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x01a4:
            android.os.Parcelable$Creator r3 = android.content.pm.PackageInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r14 = r2
            android.content.pm.PackageInfo r14 = (android.content.pm.PackageInfo) r14
            goto L_0x0071
        L_0x01af:
            android.os.Parcelable$Creator r3 = android.content.pm.ApplicationInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r13 = r2
            android.content.pm.ApplicationInfo r13 = (android.content.pm.ApplicationInfo) r13
            goto L_0x0071
        L_0x01ba:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0071
        L_0x01c0:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjn> r3 = com.google.android.gms.internal.ads.zzjn.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r11 = r2
            com.google.android.gms.internal.ads.zzjn r11 = (com.google.android.gms.internal.ads.zzjn) r11
            goto L_0x0071
        L_0x01cb:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzjj> r3 = com.google.android.gms.internal.ads.zzjj.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r10 = r2
            com.google.android.gms.internal.ads.zzjj r10 = (com.google.android.gms.internal.ads.zzjj) r10
            goto L_0x0071
        L_0x01d6:
            android.os.Bundle r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0071
        L_0x01dc:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0071
        L_0x01e2:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.internal.ads.zzaef r0 = new com.google.android.gms.internal.ads.zzaef
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r30, r31, r32, r33, r34, r35, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaeh.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaef[i];
    }
}
