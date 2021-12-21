package androidx.media2.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

class AnimatorUtil {
    static ObjectAnimator ofTranslationY(float f, float f2, View view) {
        return ObjectAnimator.ofFloat(view, "translationY", new float[]{f, f2});
    }

    static AnimatorSet ofTranslationYTogether(float f, float f2, View[] viewArr) {
        AnimatorSet animatorSet = new AnimatorSet();
        if (viewArr.length == 0) {
            return animatorSet;
        }
        AnimatorSet.Builder play = animatorSet.play(ofTranslationY(f, f2, viewArr[0]));
        for (int i = 1; i < viewArr.length; i++) {
            play.with(ofTranslationY(f, f2, viewArr[i]));
        }
        return animatorSet;
    }
}
