package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzbv;

final class zzzx implements Runnable {
    private final /* synthetic */ zzzv zzbvr;
    private final /* synthetic */ AdOverlayInfoParcel zzzc;

    zzzx(zzzv zzzv, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzbvr = zzzv;
        this.zzzc = adOverlayInfoParcel;
    }

    public final void run() {
        zzbv.zzei();
        zzl.zza(this.zzbvr.zzbvp, this.zzzc, true);
    }
}
