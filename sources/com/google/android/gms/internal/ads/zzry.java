package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeContentAd;

@zzadh
public final class zzry extends zzra {
    private final NativeContentAd.OnContentAdLoadedListener zzblc;

    public zzry(NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzblc = onContentAdLoadedListener;
    }

    public final void zza(zzqo zzqo) {
        this.zzblc.onContentAdLoaded(new zzqr(zzqo));
    }
}
