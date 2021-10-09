package com.google.android.gms.internal.ads;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@zzadh
public final class zzms extends zzjn {
    public zzms(zzjn zzjn) {
        super(zzjn.zzarb, zzjn.height, zzjn.heightPixels, zzjn.zzarc, zzjn.width, zzjn.widthPixels, zzjn.zzard, zzjn.zzare, zzjn.zzarf, zzjn.zzarg);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzarb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
