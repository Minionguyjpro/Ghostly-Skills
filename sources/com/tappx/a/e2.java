package com.tappx.a;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

public class e2 {

    /* renamed from: a  reason: collision with root package name */
    private final Rect f426a = new Rect();

    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final int f427a;
        private final boolean b;

        a(int i, boolean z) {
            if (i < 0) {
                i = 0;
            } else if (i > 100) {
                i = 100;
            }
            this.f427a = i;
            this.b = z;
        }

        public int a() {
            return this.f427a;
        }

        public boolean b() {
            return this.b;
        }
    }

    private boolean b(ViewGroup viewGroup) {
        return viewGroup.getChildCount() == 0 && viewGroup.getWidth() == 0 && viewGroup.getHeight() == 0;
    }

    public a a(ViewGroup viewGroup) {
        int i;
        boolean localVisibleRect = viewGroup.getLocalVisibleRect(this.f426a);
        if (!localVisibleRect) {
            if (b(viewGroup)) {
                localVisibleRect = true;
            }
            i = 0;
        } else {
            int a2 = a((View) viewGroup, this.f426a);
            i = viewGroup.getChildCount() > 0 ? Math.min(a2, a(viewGroup, this.f426a)) : a2;
        }
        return new a(i, localVisibleRect);
    }

    private int a(View view, Rect rect) {
        float height = (float) (rect.height() * rect.width());
        float height2 = (float) (view.getHeight() * view.getWidth());
        if (height2 <= 0.0f) {
            return 0;
        }
        return (int) ((height * 100.0f) / height2);
    }

    private int a(ViewGroup viewGroup, Rect rect) {
        int childCount = viewGroup.getChildCount();
        int i = 100;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                i = Math.min(a(childAt, rect), i);
            }
        }
        return i;
    }
}
