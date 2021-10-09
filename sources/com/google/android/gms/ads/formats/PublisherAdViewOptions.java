package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzjp;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlb;

@zzadh
public final class PublisherAdViewOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PublisherAdViewOptions> CREATOR = new zzc();
    private final boolean zzvm;
    private final zzla zzvn;
    private AppEventListener zzvo;

    public static final class Builder {
        /* access modifiers changed from: private */
        public boolean zzvm = false;
        /* access modifiers changed from: private */
        public AppEventListener zzvo;

        public final PublisherAdViewOptions build() {
            return new PublisherAdViewOptions(this);
        }

        public final Builder setAppEventListener(AppEventListener appEventListener) {
            this.zzvo = appEventListener;
            return this;
        }

        public final Builder setManualImpressionsEnabled(boolean z) {
            this.zzvm = z;
            return this;
        }
    }

    private PublisherAdViewOptions(Builder builder) {
        this.zzvm = builder.zzvm;
        AppEventListener zzb = builder.zzvo;
        this.zzvo = zzb;
        this.zzvn = zzb != null ? new zzjp(this.zzvo) : null;
    }

    PublisherAdViewOptions(boolean z, IBinder iBinder) {
        this.zzvm = z;
        this.zzvn = iBinder != null ? zzlb.zzd(iBinder) : null;
    }

    public final AppEventListener getAppEventListener() {
        return this.zzvo;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzvm;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, getManualImpressionsEnabled());
        zzla zzla = this.zzvn;
        SafeParcelWriter.writeIBinder(parcel, 2, zzla == null ? null : zzla.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzla zzbg() {
        return this.zzvn;
    }
}
