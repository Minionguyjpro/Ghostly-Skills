package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;

final class zzpo implements zzox {
    private final /* synthetic */ View zzbkc;
    private final /* synthetic */ zzpn zzbkd;

    zzpo(zzpn zzpn, View view) {
        this.zzbkd = zzpn;
        this.zzbkc = view;
    }

    public final void zzc(MotionEvent motionEvent) {
        this.zzbkd.onTouch((View) null, motionEvent);
    }

    public final void zzki() {
        this.zzbkd.onClick(this.zzbkc);
    }
}
