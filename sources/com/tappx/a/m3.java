package com.tappx.a;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import com.google.android.gms.common.ConnectionResult;
import java.util.Random;

public class m3 {

    /* renamed from: a  reason: collision with root package name */
    private static final k3[] f519a = {k3.FROM_LEFT, k3.FROM_RIGHT, k3.FROM_LEFT_BOUNCE, k3.FROM_RIGHT_BOUNCE};
    private static final Random b = new Random();

    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f520a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.tappx.a.k3[] r0 = com.tappx.a.k3.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f520a = r0
                com.tappx.a.k3 r1 = com.tappx.a.k3.FROM_LEFT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f520a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.k3 r1 = com.tappx.a.k3.FROM_LEFT_BOUNCE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f520a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.k3 r1 = com.tappx.a.k3.FROM_RIGHT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f520a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.k3 r1 = com.tappx.a.k3.FROM_RIGHT_BOUNCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.m3.a.<clinit>():void");
        }
    }

    public static Animation a(k3 k3Var) {
        if (k3Var == k3.RANDOM) {
            k3Var = a();
        }
        int i = a.f520a[k3Var.ordinal()];
        if (i == 1) {
            return a(new AccelerateInterpolator(), 800);
        }
        if (i == 2) {
            return a(new a(), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
        }
        if (i == 3) {
            return b(new AccelerateInterpolator(), 800);
        }
        if (i != 4) {
            return null;
        }
        return b(new a(), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
    }

    private static Animation b(Interpolator interpolator, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 1.0f, 2, 0.0f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setInterpolator(interpolator);
        return translateAnimation;
    }

    static k3 a() {
        k3[] k3VarArr = f519a;
        return k3VarArr[b.nextInt(k3VarArr.length)];
    }

    private static Animation a(Interpolator interpolator, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, -1.0f, 2, 0.0f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setInterpolator(interpolator);
        return translateAnimation;
    }
}
