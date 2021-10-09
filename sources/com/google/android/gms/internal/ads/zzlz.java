package com.google.android.gms.internal.ads;

final class zzlz extends zzkd {
    private final /* synthetic */ zzly zzatc;

    zzlz(zzly zzly) {
        this.zzatc = zzly;
    }

    public final void onAdFailedToLoad(int i) {
        this.zzatc.zzasv.zza(this.zzatc.zzbc());
        super.onAdFailedToLoad(i);
    }

    public final void onAdLoaded() {
        this.zzatc.zzasv.zza(this.zzatc.zzbc());
        super.onAdLoaded();
    }
}
