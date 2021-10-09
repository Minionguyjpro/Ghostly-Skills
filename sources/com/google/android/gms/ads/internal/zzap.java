package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;

final class zzap implements Runnable {
    private final /* synthetic */ AdOverlayInfoParcel zzzc;
    private final /* synthetic */ zzao zzzd;

    zzap(zzao zzao, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzzd = zzao;
        this.zzzc = adOverlayInfoParcel;
    }

    public final void run() {
        zzbv.zzei();
        zzl.zza(this.zzzd.zzza.zzvw.zzrt, this.zzzc, true);
    }
}
