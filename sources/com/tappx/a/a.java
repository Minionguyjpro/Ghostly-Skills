package com.tappx.a;

import android.view.animation.Interpolator;

public class a implements Interpolator {
    public float getInterpolation(float f) {
        double d = (double) f;
        if (d < 0.36363636363636365d) {
            return 7.5625f * f * f;
        }
        if (d < 0.7272727272727273d) {
            Double.isNaN(d);
            float f2 = (float) (d - 0.5454545454545454d);
            return (7.5625f * f2 * f2) + 0.75f;
        } else if (d < 0.9090909090909091d) {
            Double.isNaN(d);
            float f3 = (float) (d - 0.8181818181818182d);
            return (7.5625f * f3 * f3) + 0.9375f;
        } else {
            Double.isNaN(d);
            float f4 = (float) (d - 0.9545454545454546d);
            return (7.5625f * f4 * f4) + 0.984375f;
        }
    }
}
