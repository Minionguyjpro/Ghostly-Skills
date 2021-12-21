package com.google.android.gms.internal.ads;

import android.view.View;

public class zzakx extends zzakw {
    public boolean isAttachedToWindow(View view) {
        return super.isAttachedToWindow(view) || view.getWindowId() != null;
    }

    public final int zzrn() {
        return 14;
    }
}
