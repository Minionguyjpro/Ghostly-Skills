package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzald;

@zzadh
final class zzh extends RelativeLayout {
    private zzald zzaed;
    boolean zzbyh;

    public zzh(Context context, String str, String str2) {
        super(context);
        zzald zzald = new zzald(context, str);
        this.zzaed = zzald;
        zzald.zzda(str2);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.zzbyh) {
            return false;
        }
        this.zzaed.zze(motionEvent);
        return false;
    }
}
