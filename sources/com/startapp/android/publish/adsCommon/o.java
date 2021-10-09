package com.startapp.android.publish.adsCommon;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: StartAppSDK */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private final Rect f280a = new Rect();

    public boolean a(View view, int i) {
        if (view == null || !view.hasWindowFocus() || !view.isShown() || a(view) || view.getRootView() == null || view.getRootView().getParent() == null || !view.getGlobalVisibleRect(this.f280a)) {
            return false;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            for (int indexOfChild = viewGroup.indexOfChild(view) + 1; indexOfChild < viewGroup.getChildCount(); indexOfChild++) {
                View childAt = viewGroup.getChildAt(indexOfChild);
                if (childAt.getVisibility() == 0 && a(view, childAt, 0)) {
                    return false;
                }
            }
        }
        long height = ((long) this.f280a.height()) * ((long) this.f280a.width());
        long height2 = ((long) view.getHeight()) * ((long) view.getWidth());
        if (height2 <= 0 || height * 100 < ((long) i) * height2) {
            return false;
        }
        return true;
    }

    private boolean a(View view) {
        return Build.VERSION.SDK_INT >= 11 && view.getAlpha() == 0.0f;
    }

    private static boolean a(View view, View view2, int i) {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr);
        view2.getLocationOnScreen(iArr2);
        Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
        Rect rect2 = new Rect(iArr2[0], iArr2[1], iArr2[0] + view2.getWidth(), iArr2[1] + view2.getHeight());
        long max = ((long) Math.max(0, Math.min(rect.right, rect2.right) - Math.max(rect.left, rect2.left))) * ((long) Math.max(0, Math.min(rect.bottom, rect2.bottom) - Math.max(rect.top, rect2.top)));
        long height = ((long) view.getHeight()) * ((long) view.getWidth());
        if (max <= 0 || max * 100 <= ((long) i) * height) {
            return false;
        }
        return true;
    }
}
