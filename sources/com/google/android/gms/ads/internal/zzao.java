package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajx;
import com.google.android.gms.internal.ads.zzakk;

@zzadh
final class zzao extends zzajx {
    final /* synthetic */ zzal zzza;
    private final int zzzb;

    public zzao(zzal zzal, int i) {
        this.zzza = zzal;
        this.zzzb = i;
    }

    public final void onStop() {
    }

    public final void zzdn() {
        zzaq zzaq = new zzaq(this.zzza.zzvw.zzze, this.zzza.zzdi(), this.zzza.zzys, this.zzza.zzyt, this.zzza.zzvw.zzze ? this.zzzb : -1, this.zzza.zzyu, this.zzza.zzvw.zzacw.zzzl, this.zzza.zzvw.zzacw.zzzm);
        int requestedOrientation = this.zzza.zzvw.zzacw.zzbyo.getRequestedOrientation();
        if (requestedOrientation == -1) {
            requestedOrientation = this.zzza.zzvw.zzacw.orientation;
        }
        zzal zzal = this.zzza;
        zzakk.zzcrm.post(new zzap(this, new AdOverlayInfoParcel(zzal, zzal, zzal, zzal.zzvw.zzacw.zzbyo, requestedOrientation, this.zzza.zzvw.zzacr, this.zzza.zzvw.zzacw.zzcev, zzaq)));
    }
}
