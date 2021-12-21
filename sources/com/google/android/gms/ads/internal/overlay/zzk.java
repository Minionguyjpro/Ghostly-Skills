package com.google.android.gms.ads.internal.overlay;

import android.graphics.drawable.Drawable;

final class zzk implements Runnable {
    private final /* synthetic */ Drawable zzbyj;
    private final /* synthetic */ zzj zzbyk;

    zzk(zzj zzj, Drawable drawable) {
        this.zzbyk = zzj;
        this.zzbyj = drawable;
    }

    public final void run() {
        this.zzbyk.zzbyg.mActivity.getWindow().setBackgroundDrawable(this.zzbyj);
    }
}
