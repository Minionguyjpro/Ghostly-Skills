package com.mopub.nativeads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.mopub.common.Preconditions;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Views;
import com.mopub.mobileads.resource.DrawableConstants;

class SpinningProgressView extends ViewGroup {
    private final ProgressBar mProgressBar;
    private int mProgressIndicatorRadius = Dips.asIntPixels(25.0f, getContext());

    SpinningProgressView(Context context) {
        super(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        setVisibility(8);
        setBackgroundColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
        getBackground().setAlpha(180);
        this.mProgressBar = new ProgressBar(context);
        this.mProgressBar.setIndeterminate(true);
        addView(this.mProgressBar);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            int i5 = (i + i3) / 2;
            int i6 = (i2 + i4) / 2;
            ProgressBar progressBar = this.mProgressBar;
            int i7 = this.mProgressIndicatorRadius;
            progressBar.layout(i5 - i7, i6 - i7, i5 + i7, i6 + i7);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean addToRoot(View view) {
        Preconditions.checkNotNull(view);
        View rootView = view.getRootView();
        if (rootView == null || !(rootView instanceof ViewGroup)) {
            return false;
        }
        setVisibility(0);
        setMeasuredDimension(rootView.getWidth(), rootView.getHeight());
        ((ViewGroup) rootView).addView(this);
        forceLayout();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean removeFromRoot() {
        Views.removeFromParent(this);
        setVisibility(8);
        return true;
    }
}
