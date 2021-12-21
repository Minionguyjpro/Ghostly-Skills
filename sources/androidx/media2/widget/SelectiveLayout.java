package androidx.media2.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

class SelectiveLayout extends MediaViewGroup {
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    SelectiveLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return layoutParams;
        }
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Drawable foreground;
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() != 8 && !layoutParams.forceMatchParent) {
                measureChild(childAt, i, i2);
                i3 = Math.max(i3, childAt.getMeasuredWidth());
                i4 = Math.max(i4, childAt.getMeasuredHeight());
                i5 |= childAt.getMeasuredState();
            }
        }
        int positivePaddingLeft = i3 + getPositivePaddingLeft() + getPositivePaddingRight();
        int positivePaddingTop = i4 + getPositivePaddingTop() + getPositivePaddingBottom();
        int max = Math.max(positivePaddingLeft, getSuggestedMinimumWidth());
        int max2 = Math.max(positivePaddingTop, getSuggestedMinimumHeight());
        if (Build.VERSION.SDK_INT >= 23 && (foreground = getForeground()) != null) {
            max = Math.max(max, foreground.getMinimumWidth());
            max2 = Math.max(max2, foreground.getMinimumHeight());
        }
        setMeasuredDimension(resolveSizeAndState(max, i, i5), resolveSizeAndState(max2, i2, i5 << 16));
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - (getPositivePaddingLeft() + getPositivePaddingRight()), 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - (getPositivePaddingTop() + getPositivePaddingBottom()), 1073741824);
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt2 = getChildAt(i7);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            if (childAt2.getVisibility() != 8 && layoutParams2.forceMatchParent) {
                childAt2.measure(makeMeasureSpec, makeMeasureSpec2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int positivePaddingLeft = getPositivePaddingLeft();
        int positivePaddingRight = (i3 - i) - getPositivePaddingRight();
        int positivePaddingTop = getPositivePaddingTop();
        int positivePaddingBottom = (i4 - i2) - getPositivePaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = (((positivePaddingRight - positivePaddingLeft) - measuredWidth) / 2) + positivePaddingLeft;
                int i7 = (((positivePaddingBottom - positivePaddingTop) - measuredHeight) / 2) + positivePaddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    private int getPositivePaddingLeft() {
        return Math.max(getPaddingLeft(), 0);
    }

    private int getPositivePaddingRight() {
        return Math.max(getPaddingRight(), 0);
    }

    private int getPositivePaddingTop() {
        return Math.max(getPaddingTop(), 0);
    }

    private int getPositivePaddingBottom() {
        return Math.max(getPaddingBottom(), 0);
    }

    static class LayoutParams extends ViewGroup.LayoutParams {
        public boolean forceMatchParent;

        LayoutParams() {
            this(-1, -1);
        }

        LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        LayoutParams(int i, int i2) {
            super(i, i2);
        }

        LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }
}
