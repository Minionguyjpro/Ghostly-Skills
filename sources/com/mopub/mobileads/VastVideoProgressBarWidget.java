package com.mopub.mobileads;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mopub.common.util.Dips;
import com.mopub.mobileads.resource.ProgressBarDrawable;

public class VastVideoProgressBarWidget extends ImageView {
    private ProgressBarDrawable mProgressBarDrawable;
    private final int mProgressBarHeight;

    public VastVideoProgressBarWidget(Context context) {
        super(context);
        setId(View.generateViewId());
        ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable(context);
        this.mProgressBarDrawable = progressBarDrawable;
        setImageDrawable(progressBarDrawable);
        this.mProgressBarHeight = Dips.dipsToIntPixels(4.0f, context);
    }

    public void setAnchorId(int i) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.mProgressBarHeight);
        layoutParams.addRule(8, i);
        setLayoutParams(layoutParams);
    }

    public void calibrateAndMakeVisible(int i, int i2) {
        this.mProgressBarDrawable.setDurationAndSkipOffset(i, i2);
        setVisibility(0);
    }

    public void updateProgress(int i) {
        this.mProgressBarDrawable.setProgress(i);
    }

    public void reset() {
        this.mProgressBarDrawable.reset();
        this.mProgressBarDrawable.setProgress(0);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public ProgressBarDrawable getImageViewDrawable() {
        return this.mProgressBarDrawable;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setImageViewDrawable(ProgressBarDrawable progressBarDrawable) {
        this.mProgressBarDrawable = progressBarDrawable;
    }
}
