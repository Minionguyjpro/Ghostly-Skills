package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

@zzadh
public final class zzjj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzjj> CREATOR = new zzjl();
    public final Bundle extras;
    public final int versionCode;
    public final long zzapw;
    public final int zzapx;
    public final List<String> zzapy;
    public final boolean zzapz;
    public final int zzaqa;
    public final boolean zzaqb;
    public final String zzaqc;
    public final zzmq zzaqd;
    public final Location zzaqe;
    public final String zzaqf;
    public final Bundle zzaqg;
    public final Bundle zzaqh;
    public final List<String> zzaqi;
    public final String zzaqj;
    public final String zzaqk;
    public final boolean zzaql;

    public zzjj(int i, long j, Bundle bundle, int i2, List<String> list, boolean z, int i3, boolean z2, String str, zzmq zzmq, Location location, String str2, Bundle bundle2, Bundle bundle3, List<String> list2, String str3, String str4, boolean z3) {
        this.versionCode = i;
        this.zzapw = j;
        this.extras = bundle == null ? new Bundle() : bundle;
        this.zzapx = i2;
        this.zzapy = list;
        this.zzapz = z;
        this.zzaqa = i3;
        this.zzaqb = z2;
        this.zzaqc = str;
        this.zzaqd = zzmq;
        this.zzaqe = location;
        this.zzaqf = str2;
        this.zzaqg = bundle2 == null ? new Bundle() : bundle2;
        this.zzaqh = bundle3;
        this.zzaqi = list2;
        this.zzaqj = str3;
        this.zzaqk = str4;
        this.zzaql = z3;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzjj)) {
            return false;
        }
        zzjj zzjj = (zzjj) obj;
        return this.versionCode == zzjj.versionCode && this.zzapw == zzjj.zzapw && Objects.equal(this.extras, zzjj.extras) && this.zzapx == zzjj.zzapx && Objects.equal(this.zzapy, zzjj.zzapy) && this.zzapz == zzjj.zzapz && this.zzaqa == zzjj.zzaqa && this.zzaqb == zzjj.zzaqb && Objects.equal(this.zzaqc, zzjj.zzaqc) && Objects.equal(this.zzaqd, zzjj.zzaqd) && Objects.equal(this.zzaqe, zzjj.zzaqe) && Objects.equal(this.zzaqf, zzjj.zzaqf) && Objects.equal(this.zzaqg, zzjj.zzaqg) && Objects.equal(this.zzaqh, zzjj.zzaqh) && Objects.equal(this.zzaqi, zzjj.zzaqi) && Objects.equal(this.zzaqj, zzjj.zzaqj) && Objects.equal(this.zzaqk, zzjj.zzaqk) && this.zzaql == zzjj.zzaql;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.versionCode), Long.valueOf(this.zzapw), this.extras, Integer.valueOf(this.zzapx), this.zzapy, Boolean.valueOf(this.zzapz), Integer.valueOf(this.zzaqa), Boolean.valueOf(this.zzaqb), this.zzaqc, this.zzaqd, this.zzaqe, this.zzaqf, this.zzaqg, this.zzaqh, this.zzaqi, this.zzaqj, this.zzaqk, Boolean.valueOf(this.zzaql));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel, 2, this.zzapw);
        SafeParcelWriter.writeBundle(parcel, 3, this.extras, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzapx);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzapy, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzapz);
        SafeParcelWriter.writeInt(parcel, 7, this.zzaqa);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzaqb);
        SafeParcelWriter.writeString(parcel, 9, this.zzaqc, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzaqd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzaqe, i, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzaqf, false);
        SafeParcelWriter.writeBundle(parcel, 13, this.zzaqg, false);
        SafeParcelWriter.writeBundle(parcel, 14, this.zzaqh, false);
        SafeParcelWriter.writeStringList(parcel, 15, this.zzaqi, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzaqj, false);
        SafeParcelWriter.writeString(parcel, 17, this.zzaqk, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzaql);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzjj zzhv() {
        Bundle bundle = this.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle == null) {
            bundle = this.extras;
            this.zzaqg.putBundle("com.google.ads.mediation.admob.AdMobAdapter", bundle);
        }
        return new zzjj(this.versionCode, this.zzapw, bundle, this.zzapx, this.zzapy, this.zzapz, this.zzaqa, this.zzaqb, this.zzaqc, this.zzaqd, this.zzaqe, this.zzaqf, this.zzaqg, this.zzaqh, this.zzaqi, this.zzaqj, this.zzaqk, this.zzaql);
    }
}
