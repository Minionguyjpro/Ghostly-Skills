package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.plus.zzr;
import java.util.HashSet;

public final class zzy implements Parcelable.Creator<zzr.zzd> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    i = 1;
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    i = 2;
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    i = 3;
                    break;
                case 4:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    i = 4;
                    break;
                case 5:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    i = 5;
                    break;
                case 6:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    i = 6;
                    break;
                case 7:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    i = 7;
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    continue;
            }
            hashSet.add(Integer.valueOf(i));
        }
        if (parcel.dataPosition() == validateObjectHeader) {
            return new zzr.zzd(hashSet, i2, str, str2, str3, str4, str5, str6);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(validateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr.zzd[i];
    }
}
