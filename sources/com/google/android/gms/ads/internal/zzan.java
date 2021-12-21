package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzahu;
import com.google.android.gms.internal.ads.zzaig;

final class zzan implements zzahu {
    private final /* synthetic */ zzal zzza;

    zzan(zzal zzal) {
        this.zzza = zzal;
    }

    public final void onRewardedVideoAdClosed() {
        this.zzza.zzcb();
    }

    public final void onRewardedVideoAdLeftApplication() {
        this.zzza.zzbo();
    }

    public final void onRewardedVideoAdOpened() {
        this.zzza.zzcc();
    }

    public final void onRewardedVideoCompleted() {
        this.zzza.zzdl();
    }

    public final void onRewardedVideoStarted() {
        this.zzza.zzdk();
    }

    public final void zzc(zzaig zzaig) {
        this.zzza.zzb(zzaig);
    }

    public final void zzdm() {
        this.zzza.onAdClicked();
    }
}
