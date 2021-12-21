package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zzadh
public final class zzrz extends zzrd {
    private final NativeCustomTemplateAd.OnCustomClickListener zzbld;

    public zzrz(NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
        this.zzbld = onCustomClickListener;
    }

    public final void zzb(zzqs zzqs, String str) {
        this.zzbld.onCustomClick(zzqv.zza(zzqs), str);
    }
}
