package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzd();
    String zza;
    IBinder zzb;
    Scope[] zzc;
    Bundle zzd;
    Account zze;
    Feature[] zzf;
    Feature[] zzg;
    private final int zzh;
    private final int zzi;
    private int zzj;
    private boolean zzk;
    private int zzl;

    public GetServiceRequest(int i) {
        this.zzh = 4;
        this.zzj = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzi = i;
        this.zzk = true;
    }

    GetServiceRequest(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, Feature[] featureArr, Feature[] featureArr2, boolean z, int i4) {
        this.zzh = i;
        this.zzi = i2;
        this.zzj = i3;
        if ("com.google.android.gms".equals(str)) {
            this.zza = "com.google.android.gms";
        } else {
            this.zza = str;
        }
        if (i < 2) {
            this.zze = iBinder != null ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder)) : null;
        } else {
            this.zzb = iBinder;
            this.zze = account;
        }
        this.zzc = scopeArr;
        this.zzd = bundle;
        this.zzf = featureArr;
        this.zzg = featureArr2;
        this.zzk = z;
        this.zzl = i4;
    }

    public Bundle getExtraArgs() {
        return this.zzd;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzh);
        SafeParcelWriter.writeInt(parcel, 2, this.zzi);
        SafeParcelWriter.writeInt(parcel, 3, this.zzj);
        SafeParcelWriter.writeString(parcel, 4, this.zza, false);
        SafeParcelWriter.writeIBinder(parcel, 5, this.zzb, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, this.zzc, i, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzd, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zze, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 10, this.zzf, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 11, this.zzg, i, false);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzk);
        SafeParcelWriter.writeInt(parcel, 13, this.zzl);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
