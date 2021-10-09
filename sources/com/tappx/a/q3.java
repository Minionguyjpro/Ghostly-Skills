package com.tappx.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.WindowManager;

public class q3 {
    public static boolean a(Context context, Intent intent) {
        try {
            return !context.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public static int b(float f, Context context) {
        return (int) (a(f, context) + 0.5f);
    }

    public static float c(float f, Context context) {
        return f * a(context);
    }

    public static int d(float f, Context context) {
        return (int) (c(f, context) + 0.5f);
    }

    public static float e(float f, Context context) {
        return f / a(context);
    }

    public static int f(float f, Context context) {
        return (int) (e(f, context) + 0.5f);
    }

    private static float a(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static float a(float f, Context context) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static void a(Activity activity, j3 j3Var) {
        int a2 = a(((WindowManager) activity.getSystemService("window")).getDefaultDisplay().getRotation(), activity.getResources().getConfiguration().orientation);
        int i = 8;
        if (j3Var == j3.PORTRAIT) {
            i = 9 == a2 ? 9 : 1;
        } else if (j3Var != j3.LANDSCAPE) {
            return;
        } else {
            if (8 != a2) {
                i = 0;
            }
        }
        try {
            activity.setRequestedOrientation(i);
        } catch (Exception unused) {
        }
    }

    static int a(int i, int i2) {
        if (1 == i2) {
            return (i == 1 || i == 2) ? 9 : 1;
        }
        if (2 == i2) {
            return (i == 2 || i == 3) ? 8 : 0;
        }
        j4.a("Unknown screen orientation. Defaulting to portrait.");
        return 9;
    }
}
