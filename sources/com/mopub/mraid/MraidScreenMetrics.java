package com.mopub.mraid;

import android.content.Context;
import android.graphics.Rect;
import com.mopub.common.util.Dips;

class MraidScreenMetrics {
    private final Context mContext;
    private final Rect mCurrentAdRect = new Rect();
    private final Rect mCurrentAdRectDips = new Rect();
    private final Rect mDefaultAdRect = new Rect();
    private final Rect mDefaultAdRectDips = new Rect();
    private final float mDensity;
    private final Rect mRootViewRect = new Rect();
    private final Rect mRootViewRectDips = new Rect();
    private final Rect mScreenRect = new Rect();
    private final Rect mScreenRectDips = new Rect();

    MraidScreenMetrics(Context context, float f) {
        this.mContext = context.getApplicationContext();
        this.mDensity = f;
    }

    private void convertToDips(Rect rect, Rect rect2) {
        rect2.set(Dips.pixelsToIntDips((float) rect.left, this.mContext), Dips.pixelsToIntDips((float) rect.top, this.mContext), Dips.pixelsToIntDips((float) rect.right, this.mContext), Dips.pixelsToIntDips((float) rect.bottom, this.mContext));
    }

    public float getDensity() {
        return this.mDensity;
    }

    /* access modifiers changed from: package-private */
    public void setScreenSize(int i, int i2) {
        this.mScreenRect.set(0, 0, i, i2);
        convertToDips(this.mScreenRect, this.mScreenRectDips);
    }

    /* access modifiers changed from: package-private */
    public Rect getScreenRect() {
        return this.mScreenRect;
    }

    /* access modifiers changed from: package-private */
    public Rect getScreenRectDips() {
        return this.mScreenRectDips;
    }

    /* access modifiers changed from: package-private */
    public void setRootViewPosition(int i, int i2, int i3, int i4) {
        this.mRootViewRect.set(i, i2, i3 + i, i4 + i2);
        convertToDips(this.mRootViewRect, this.mRootViewRectDips);
    }

    /* access modifiers changed from: package-private */
    public Rect getRootViewRect() {
        return this.mRootViewRect;
    }

    /* access modifiers changed from: package-private */
    public Rect getRootViewRectDips() {
        return this.mRootViewRectDips;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentAdPosition(int i, int i2, int i3, int i4) {
        this.mCurrentAdRect.set(i, i2, i3 + i, i4 + i2);
        convertToDips(this.mCurrentAdRect, this.mCurrentAdRectDips);
    }

    /* access modifiers changed from: package-private */
    public Rect getCurrentAdRect() {
        return this.mCurrentAdRect;
    }

    /* access modifiers changed from: package-private */
    public Rect getCurrentAdRectDips() {
        return this.mCurrentAdRectDips;
    }

    /* access modifiers changed from: package-private */
    public void setDefaultAdPosition(int i, int i2, int i3, int i4) {
        this.mDefaultAdRect.set(i, i2, i3 + i, i4 + i2);
        convertToDips(this.mDefaultAdRect, this.mDefaultAdRectDips);
    }

    /* access modifiers changed from: package-private */
    public Rect getDefaultAdRect() {
        return this.mDefaultAdRect;
    }

    /* access modifiers changed from: package-private */
    public Rect getDefaultAdRectDips() {
        return this.mDefaultAdRectDips;
    }
}
