package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new zza();
    private IBinder zza;

    public BinderWrapper() {
        this.zza = null;
    }

    public final int describeContents() {
        return 0;
    }

    public BinderWrapper(IBinder iBinder) {
        this.zza = null;
        this.zza = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.zza = null;
        this.zza = parcel.readStrongBinder();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.zza);
    }

    /* synthetic */ BinderWrapper(Parcel parcel, zza zza2) {
        this(parcel);
    }
}
