package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;

final class zzgn implements ValueCallback<String> {
    private final /* synthetic */ zzgm zzaip;

    zzgn(zzgm zzgm) {
        this.zzaip = zzgm;
    }

    public final /* synthetic */ void onReceiveValue(Object obj) {
        this.zzaip.zzaik.zza(this.zzaip.zzaim, this.zzaip.zzain, (String) obj, this.zzaip.zzaio);
    }
}
