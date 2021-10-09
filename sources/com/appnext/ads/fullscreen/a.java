package com.appnext.ads.fullscreen;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public final class a extends Animation {
    private float aA;
    private Circle ay;
    private float az;

    public a(Circle circle, float f) {
        setInterpolator(new LinearInterpolator());
        this.az = circle.getAngle();
        this.aA = f;
        this.ay = circle;
    }

    /* access modifiers changed from: protected */
    public final void applyTransformation(float f, Transformation transformation) {
        float f2 = this.az;
        this.ay.setAngle(f2 - ((f2 - this.aA) * f));
        this.ay.invalidate();
        this.ay.requestLayout();
    }
}
