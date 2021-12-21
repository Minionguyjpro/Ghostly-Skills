package com.mopub.mobileads;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.mobileads.resource.DrawableConstants;

public class VastVideoGradientStripWidget extends ImageView {
    private boolean mHasCompanionAd;
    private boolean mIsVideoComplete;
    private int mVisibilityForCompanionAd;

    public VastVideoGradientStripWidget(Context context, GradientDrawable.Orientation orientation, boolean z, int i, int i2, int i3) {
        super(context);
        this.mVisibilityForCompanionAd = i;
        this.mHasCompanionAd = z;
        setImageDrawable(new GradientDrawable(orientation, new int[]{DrawableConstants.GradientStrip.START_COLOR, DrawableConstants.GradientStrip.END_COLOR}));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, Dips.dipsToIntPixels(72.0f, context));
        layoutParams.addRule(i2, i3);
        setLayoutParams(layoutParams);
        updateVisibility();
    }

    /* access modifiers changed from: package-private */
    public void notifyVideoComplete() {
        this.mIsVideoComplete = true;
        updateVisibility();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateVisibility();
    }

    private void updateVisibility() {
        if (!this.mIsVideoComplete) {
            int i = getResources().getConfiguration().orientation;
            if (i == 0) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Screen orientation undefined: do not show gradient strip widget");
                setVisibility(4);
            } else if (i == 1) {
                setVisibility(4);
            } else if (i == 2) {
                setVisibility(0);
            } else if (i != 3) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unrecognized screen orientation: do not show gradient strip widget");
                setVisibility(4);
            } else {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Screen orientation is deprecated ORIENTATION_SQUARE: do not show gradient strip widget");
                setVisibility(4);
            }
        } else if (this.mHasCompanionAd) {
            setVisibility(this.mVisibilityForCompanionAd);
        } else {
            setVisibility(8);
        }
    }
}
