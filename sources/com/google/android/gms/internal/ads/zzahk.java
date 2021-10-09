package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@zzadh
public final class zzahk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzahk> CREATOR = new zzahl();
    public final String zzacp;
    public final zzjj zzccv;

    public zzahk(zzjj zzjj, String str) {
        this.zzccv = zzjj;
        this.zzacp = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzccv, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzacp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
