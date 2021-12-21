package com.google.android.gms.internal.ads;

import android.os.Parcelable;

public final class zzaez implements Parcelable.Creator<zzaey> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r14)
            r1 = 0
            r2 = 0
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r12 = r10
            r11 = 0
        L_0x000f:
            int r1 = r14.dataPosition()
            if (r1 >= r0) goto L_0x0060
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r14)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x005b;
                case 2: goto L_0x0051;
                case 3: goto L_0x0047;
                case 4: goto L_0x0042;
                case 5: goto L_0x003d;
                case 6: goto L_0x0033;
                case 7: goto L_0x002e;
                case 8: goto L_0x0029;
                case 9: goto L_0x0024;
                default: goto L_0x0020;
            }
        L_0x0020:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r14, r1)
            goto L_0x000f
        L_0x0024:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r14, r1)
            goto L_0x000f
        L_0x0029:
            boolean r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r14, r1)
            goto L_0x000f
        L_0x002e:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r14, r1)
            goto L_0x000f
        L_0x0033:
            android.os.Parcelable$Creator r2 = android.content.pm.PackageInfo.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r1, r2)
            r9 = r1
            android.content.pm.PackageInfo r9 = (android.content.pm.PackageInfo) r9
            goto L_0x000f
        L_0x003d:
            java.util.ArrayList r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r14, r1)
            goto L_0x000f
        L_0x0042:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r14, r1)
            goto L_0x000f
        L_0x0047:
            android.os.Parcelable$Creator r2 = android.content.pm.ApplicationInfo.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r1, r2)
            r6 = r1
            android.content.pm.ApplicationInfo r6 = (android.content.pm.ApplicationInfo) r6
            goto L_0x000f
        L_0x0051:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzang> r2 = com.google.android.gms.internal.ads.zzang.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r1, r2)
            r5 = r1
            com.google.android.gms.internal.ads.zzang r5 = (com.google.android.gms.internal.ads.zzang) r5
            goto L_0x000f
        L_0x005b:
            android.os.Bundle r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r14, r1)
            goto L_0x000f
        L_0x0060:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r14, r0)
            com.google.android.gms.internal.ads.zzaey r14 = new com.google.android.gms.internal.ads.zzaey
            r3 = r14
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaez.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzaey[i];
    }
}
