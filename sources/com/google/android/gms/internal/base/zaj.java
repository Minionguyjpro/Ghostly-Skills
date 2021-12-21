package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zaj extends Drawable.ConstantState {
    int zaa;
    int zab;

    zaj(zaj zaj) {
        if (zaj != null) {
            this.zaa = zaj.zaa;
            this.zab = zaj.zab;
        }
    }

    public final Drawable newDrawable() {
        return new zaf(this);
    }

    public final int getChangingConfigurations() {
        return this.zaa;
    }
}
