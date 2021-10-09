package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Parcelable.Creator<Status> CREATOR = new zzb();
    public static final Status RESULT_CANCELED = new Status(16);
    public static final Status RESULT_DEAD_CLIENT = new Status(18);
    public static final Status RESULT_INTERNAL_ERROR = new Status(8);
    public static final Status RESULT_INTERRUPTED = new Status(14);
    public static final Status RESULT_SUCCESS = new Status(0);
    public static final Status RESULT_TIMEOUT = new Status(15);
    private static final Status zza = new Status(17);
    private final int zzb;
    private final int zzc;
    private final String zzd;
    private final PendingIntent zze;

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.zzb = i;
        this.zzc = i2;
        this.zzd = str;
        this.zze = pendingIntent;
    }

    public final Status getStatus() {
        return this;
    }

    public Status(int i) {
        this(i, (String) null);
    }

    public Status(int i, String str) {
        this(1, i, str, (PendingIntent) null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    public final void startResolutionForResult(Activity activity, int i) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(((PendingIntent) Preconditions.checkNotNull(this.zze)).getIntentSender(), i, (Intent) null, 0, 0, 0);
        }
    }

    public final String getStatusMessage() {
        return this.zzd;
    }

    public final boolean hasResolution() {
        return this.zze != null;
    }

    public final boolean isSuccess() {
        return this.zzc <= 0;
    }

    public final boolean isCanceled() {
        return this.zzc == 16;
    }

    public final boolean isInterrupted() {
        return this.zzc == 14;
    }

    public final int getStatusCode() {
        return this.zzc;
    }

    public final PendingIntent getResolution() {
        return this.zze;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zzd, this.zze);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        if (this.zzb != status.zzb || this.zzc != status.zzc || !Objects.equal(this.zzd, status.zzd) || !Objects.equal(this.zze, status.zze)) {
            return false;
        }
        return true;
    }

    public final String zza() {
        String str = this.zzd;
        if (str != null) {
            return str;
        }
        return CommonStatusCodes.getStatusCodeString(this.zzc);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("statusCode", zza()).add("resolution", this.zze).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getStatusCode());
        SafeParcelWriter.writeString(parcel, 2, getStatusMessage(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zze, i, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
