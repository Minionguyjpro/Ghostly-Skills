package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd;

@zzadh
public final class zzsd extends zzrm {
    private final UnifiedNativeAd.OnUnifiedNativeAdLoadedListener zzblj;

    public zzsd(UnifiedNativeAd.OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
        this.zzblj = onUnifiedNativeAdLoadedListener;
    }

    public final void zza(zzrr zzrr) {
        this.zzblj.onUnifiedNativeAdLoaded(new zzru(zzrr));
    }
}
