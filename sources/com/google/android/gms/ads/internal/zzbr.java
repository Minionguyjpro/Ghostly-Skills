package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;

final class zzbr implements View.OnTouchListener {
    private final /* synthetic */ zzbp zzaba;

    zzbr(zzbp zzbp) {
        this.zzaba = zzbp;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.zzaba.zzaay == null) {
            return false;
        }
        this.zzaba.zzaay.zza(motionEvent);
        return false;
    }
}
