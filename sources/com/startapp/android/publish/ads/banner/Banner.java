package com.startapp.android.publish.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import com.startapp.android.publish.ads.banner.bannerstandard.BannerStandard;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class Banner extends BannerStandard {
    public Banner(Activity activity) {
        super(activity);
    }

    public Banner(Activity activity, AdPreferences adPreferences) {
        super(activity, adPreferences);
    }

    public Banner(Activity activity, BannerListener bannerListener) {
        super(activity, bannerListener);
    }

    public Banner(Activity activity, AdPreferences adPreferences, BannerListener bannerListener) {
        super(activity, adPreferences, bannerListener);
    }

    public Banner(Activity activity, AttributeSet attributeSet) {
        super(activity, attributeSet);
    }

    public Banner(Activity activity, AttributeSet attributeSet, int i) {
        super(activity, attributeSet, i);
    }

    @Deprecated
    public Banner(Context context) {
        super(context);
    }

    @Deprecated
    public Banner(Context context, AdPreferences adPreferences) {
        super(context, adPreferences);
    }

    @Deprecated
    public Banner(Context context, BannerListener bannerListener) {
        super(context, bannerListener);
    }

    @Deprecated
    public Banner(Context context, AdPreferences adPreferences, BannerListener bannerListener) {
        super(context, adPreferences, bannerListener);
    }

    @Deprecated
    public Banner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Deprecated
    public Banner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
