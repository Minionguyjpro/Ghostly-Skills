package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;

public final class zzn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzn> CREATOR = new zzo();
    private final String zzaa;
    private final String[] zzab;
    private final String[] zzac;
    private final String[] zzad;
    private final String zzae;
    private final String zzaf;
    private final String zzag;
    private final String zzah;
    private final PlusCommonExtras zzai;
    private final int zzw;

    zzn(int i, String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, String str5, PlusCommonExtras plusCommonExtras) {
        this.zzw = i;
        this.zzaa = str;
        this.zzab = strArr;
        this.zzac = strArr2;
        this.zzad = strArr3;
        this.zzae = str2;
        this.zzaf = str3;
        this.zzag = str4;
        this.zzah = str5;
        this.zzai = plusCommonExtras;
    }

    public zzn(String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, PlusCommonExtras plusCommonExtras) {
        this.zzw = 1;
        this.zzaa = str;
        this.zzab = strArr;
        this.zzac = strArr2;
        this.zzad = strArr3;
        this.zzae = str2;
        this.zzaf = str3;
        this.zzag = null;
        this.zzah = null;
        this.zzai = plusCommonExtras;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzn)) {
            return false;
        }
        zzn zzn = (zzn) obj;
        return this.zzw == zzn.zzw && Objects.equal(this.zzaa, zzn.zzaa) && Arrays.equals(this.zzab, zzn.zzab) && Arrays.equals(this.zzac, zzn.zzac) && Arrays.equals(this.zzad, zzn.zzad) && Objects.equal(this.zzae, zzn.zzae) && Objects.equal(this.zzaf, zzn.zzaf) && Objects.equal(this.zzag, zzn.zzag) && Objects.equal(this.zzah, zzn.zzah) && Objects.equal(this.zzai, zzn.zzai);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzw), this.zzaa, this.zzab, this.zzac, this.zzad, this.zzae, this.zzaf, this.zzag, this.zzah, this.zzai);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zzw)).add("accountName", this.zzaa).add("requestedScopes", this.zzab).add("visibleActivities", this.zzac).add("requiredFeatures", this.zzad).add("packageNameForAuth", this.zzae).add("callingPackageName", this.zzaf).add("applicationName", this.zzag).add("extra", this.zzai.toString()).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzaa, false);
        SafeParcelWriter.writeStringArray(parcel, 2, this.zzab, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzac, false);
        SafeParcelWriter.writeStringArray(parcel, 4, this.zzad, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzae, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzaf, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzag, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzw);
        SafeParcelWriter.writeString(parcel, 8, this.zzah, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzai, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String[] zzc() {
        return this.zzac;
    }

    public final String zzd() {
        return this.zzae;
    }

    public final Bundle zze() {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(PlusCommonExtras.class.getClassLoader());
        bundle.putByteArray("android.gms.plus.internal.PlusCommonExtras.extraPlusCommon", SafeParcelableSerializer.serializeToBytes(this.zzai));
        return bundle;
    }
}
