package com.google.android.gms.internal.plus;

import android.os.Parcelable;
import com.google.android.gms.internal.plus.zzr;

public final class zzu implements Parcelable.Creator<zzr.zzb> {
    /* JADX WARNING: type inference failed for: r1v7, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v8, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r10) {
        /*
            r9 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r10)
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            r1 = 0
            r3 = 0
            r4 = r1
            r5 = r4
            r6 = 0
        L_0x000e:
            int r1 = r10.dataPosition()
            if (r1 >= r0) goto L_0x0051
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r10)
            int r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            r8 = 1
            if (r7 == r8) goto L_0x0045
            r8 = 2
            if (r7 == r8) goto L_0x003b
            r8 = 3
            if (r7 == r8) goto L_0x0031
            r8 = 4
            if (r7 == r8) goto L_0x002c
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r10, r1)
            goto L_0x000e
        L_0x002c:
            int r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r1)
            goto L_0x0049
        L_0x0031:
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzb$zzb> r5 = com.google.android.gms.internal.plus.zzr.zzb.C0046zzb.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r1, r5)
            r5 = r1
            com.google.android.gms.internal.plus.zzr$zzb$zzb r5 = (com.google.android.gms.internal.plus.zzr.zzb.C0046zzb) r5
            goto L_0x0049
        L_0x003b:
            android.os.Parcelable$Creator<com.google.android.gms.internal.plus.zzr$zzb$zza> r4 = com.google.android.gms.internal.plus.zzr.zzb.zza.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r10, r1, r4)
            r4 = r1
            com.google.android.gms.internal.plus.zzr$zzb$zza r4 = (com.google.android.gms.internal.plus.zzr.zzb.zza) r4
            goto L_0x0049
        L_0x0045:
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r10, r1)
        L_0x0049:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)
            r2.add(r1)
            goto L_0x000e
        L_0x0051:
            int r1 = r10.dataPosition()
            if (r1 != r0) goto L_0x005e
            com.google.android.gms.internal.plus.zzr$zzb r10 = new com.google.android.gms.internal.plus.zzr$zzb
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6)
            return r10
        L_0x005e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException r1 = new com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException
            r2 = 37
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Overread allowed size end="
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r0, r10)
            goto L_0x0078
        L_0x0077:
            throw r1
        L_0x0078:
            goto L_0x0077
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.plus.zzu.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr.zzb[i];
    }
}
