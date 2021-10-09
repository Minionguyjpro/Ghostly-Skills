package com.mopub.mobileads;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.mobileads.resource.CtaButtonDrawable;

public class VastVideoCtaButtonWidget extends ImageView {
    private CtaButtonDrawable mCtaButtonDrawable;
    private boolean mHasClickthroughUrl;
    private boolean mHasCompanionAd;
    private boolean mIsVideoComplete;
    private boolean mIsVideoSkippable;
    private final RelativeLayout.LayoutParams mLandscapeLayoutParams;
    private final RelativeLayout.LayoutParams mPortraitLayoutParams;

    public VastVideoCtaButtonWidget(Context context, int i, boolean z, boolean z2) {
        super(context);
        this.mHasCompanionAd = z;
        this.mHasClickthroughUrl = z2;
        setId(View.generateViewId());
        int dipsToIntPixels = Dips.dipsToIntPixels(150.0f, context);
        int dipsToIntPixels2 = Dips.dipsToIntPixels(38.0f, context);
        int dipsToIntPixels3 = Dips.dipsToIntPixels(16.0f, context);
        CtaButtonDrawable ctaButtonDrawable = new CtaButtonDrawable(context);
        this.mCtaButtonDrawable = ctaButtonDrawable;
        setImageDrawable(ctaButtonDrawable);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dipsToIntPixels, dipsToIntPixels2);
        this.mLandscapeLayoutParams = layoutParams;
        layoutParams.setMargins(dipsToIntPixels3, dipsToIntPixels3, dipsToIntPixels3, dipsToIntPixels3);
        this.mLandscapeLayoutParams.addRule(8, i);
        this.mLandscapeLayoutParams.addRule(7, i);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dipsToIntPixels, dipsToIntPixels2);
        this.mPortraitLayoutParams = layoutParams2;
        layoutParams2.setMargins(dipsToIntPixels3, dipsToIntPixels3, dipsToIntPixels3, dipsToIntPixels3);
        this.mPortraitLayoutParams.addRule(12);
        this.mPortraitLayoutParams.addRule(11);
        updateLayoutAndVisibility();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateLayoutAndVisibility();
    }

    /* access modifiers changed from: package-private */
    public void updateCtaText(String str) {
        this.mCtaButtonDrawable.setCtaText(str);
    }

    /* access modifiers changed from: package-private */
    public void notifyVideoSkippable() {
        this.mIsVideoSkippable = true;
        updateLayoutAndVisibility();
    }

    /* access modifiers changed from: package-private */
    public void notifyVideoComplete() {
        this.mIsVideoSkippable = true;
        this.mIsVideoComplete = true;
        updateLayoutAndVisibility();
    }

    /* access modifiers changed from: package-private */
    public void notifyVideoClickable() {
        this.mIsVideoComplete = true;
        updateLayoutAndVisibility();
    }

    private void updateLayoutAndVisibility() {
        if (!this.mHasClickthroughUrl) {
            setVisibility(8);
        } else if (!this.mIsVideoSkippable) {
            setVisibility(4);
        } else if (!this.mIsVideoComplete || !this.mHasCompanionAd) {
            int i = getResources().getConfiguration().orientation;
            if (i == 0) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Screen orientation undefined: CTA button widget defaulting to portrait layout");
                setLayoutParams(this.mPortraitLayoutParams);
            } else if (i == 1) {
                setLayoutParams(this.mPortraitLayoutParams);
            } else if (i == 2) {
                setLayoutParams(this.mLandscapeLayoutParams);
            } else if (i != 3) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unrecognized screen orientation: CTA button widget defaulting to portrait layout");
                setLayoutParams(this.mPortraitLayoutParams);
            } else {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Screen orientation is deprecated ORIENTATION_SQUARE: CTA button widget defaulting to portrait layout");
                setLayoutParams(this.mPortraitLayoutParams);
            }
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public String getCtaText() {
        return this.mCtaButtonDrawable.getCtaText();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean hasPortraitLayoutParams() {
        return getLayoutParams().equals(this.mPortraitLayoutParams);
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean hasLandscapeLayoutParams() {
        return getLayoutParams().equals(this.mLandscapeLayoutParams);
    }
}
