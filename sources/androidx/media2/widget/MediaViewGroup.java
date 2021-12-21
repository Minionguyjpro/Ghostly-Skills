package androidx.media2.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

abstract class MediaViewGroup extends ViewGroup {
    private boolean mAggregatedIsVisible = false;

    MediaViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        boolean isShown;
        if (Build.VERSION.SDK_INT < 24 && getWindowVisibility() == 0 && this.mAggregatedIsVisible != (isShown = isShown())) {
            onVisibilityAggregatedCompat(isShown);
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        if (Build.VERSION.SDK_INT < 24 && isShown()) {
            onVisibilityAggregatedCompat(i == 0);
        }
    }

    public void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        onVisibilityAggregatedCompat(z);
    }

    /* access modifiers changed from: package-private */
    public void onVisibilityAggregatedCompat(boolean z) {
        this.mAggregatedIsVisible = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isAggregatedVisible() {
        return this.mAggregatedIsVisible;
    }
}
