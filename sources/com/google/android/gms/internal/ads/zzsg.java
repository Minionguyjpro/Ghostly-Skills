package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Map;

@zzadh
public final class zzsg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzsg> CREATOR = new zzsh();
    private final String url;
    private final String[] zzbnh;
    private final String[] zzbni;

    zzsg(String str, String[] strArr, String[] strArr2) {
        this.url = str;
        this.zzbnh = strArr;
        this.zzbni = strArr2;
    }

    public static zzsg zzh(zzr zzr) throws zza {
        Map<String, String> headers = zzr.getHeaders();
        int size = headers.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        int i = 0;
        for (Map.Entry next : headers.entrySet()) {
            strArr[i] = (String) next.getKey();
            strArr2[i] = (String) next.getValue();
            i++;
        }
        return new zzsg(zzr.getUrl(), strArr, strArr2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.url, false);
        SafeParcelWriter.writeStringArray(parcel, 2, this.zzbnh, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzbni, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
