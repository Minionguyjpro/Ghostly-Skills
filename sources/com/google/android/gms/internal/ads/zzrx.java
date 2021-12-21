package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeAppInstallAd;

@zzadh
public final class zzrx extends zzqx {
    private final NativeAppInstallAd.OnAppInstallAdLoadedListener zzblb;

    public zzrx(NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zzblb = onAppInstallAdLoadedListener;
    }

    public final void zza(zzqk zzqk) {
        this.zzblb.onAppInstallAdLoaded(new zzqn(zzqk));
    }
}
