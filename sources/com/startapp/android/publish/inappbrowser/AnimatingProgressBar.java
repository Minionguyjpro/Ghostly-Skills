package com.startapp.android.publish.inappbrowser;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;

/* compiled from: StartAppSDK */
public class AnimatingProgressBar extends ProgressBar {

    /* renamed from: a  reason: collision with root package name */
    private static final Interpolator f319a = new AccelerateDecelerateInterpolator();
    private ValueAnimator b;
    private boolean c = false;

    public AnimatingProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean z = false;
        this.c = Build.VERSION.SDK_INT >= 11 ? true : z;
    }

    public void setProgress(int i) {
        if (!this.c) {
            super.setProgress(i);
            return;
        }
        ValueAnimator valueAnimator = this.b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            if (getProgress() >= i) {
                return;
            }
        } else {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{getProgress(), i});
            this.b = ofInt;
            ofInt.setInterpolator(f319a);
            this.b.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                /* renamed from: a  reason: collision with root package name */
                Integer f320a;

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Integer num = (Integer) valueAnimator.getAnimatedValue();
                    this.f320a = num;
                    AnimatingProgressBar.super.setProgress(num.intValue());
                }
            });
        }
        this.b.setIntValues(new int[]{getProgress(), i});
        this.b.start();
    }

    /* access modifiers changed from: protected */
    public ValueAnimator getAnimator() {
        return this.b;
    }

    public void a() {
        super.setProgress(0);
        ValueAnimator valueAnimator = this.b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }
}
