package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;

final class zzpr implements zzox {
    private final /* synthetic */ View zzbkc;
    private final /* synthetic */ zzpp zzbkj;

    zzpr(zzpp zzpp, View view) {
        this.zzbkj = zzpp;
        this.zzbkc = view;
    }

    public final void zzc(MotionEvent motionEvent) {
        this.zzbkj.onTouch((View) null, motionEvent);
    }

    public final void zzki() {
        if (this.zzbkj.zza(zzpp.zzbjs)) {
            this.zzbkj.onClick(this.zzbkc);
        }
    }
}
