package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;

final class zzn implements View.OnTouchListener {
    private final /* synthetic */ zzl zzwp;
    private final /* synthetic */ zzx zzwq;

    zzn(zzl zzl, zzx zzx) {
        this.zzwp = zzl;
        this.zzwq = zzx;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        this.zzwq.recordClick();
        if (this.zzwp.zzwn == null) {
            return false;
        }
        this.zzwp.zzwn.zzpi();
        return false;
    }
}
