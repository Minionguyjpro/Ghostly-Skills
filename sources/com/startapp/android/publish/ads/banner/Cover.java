package com.startapp.android.publish.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class Cover extends Banner {
    /* access modifiers changed from: protected */
    public String getBannerName() {
        return "StartApp Cover";
    }

    /* access modifiers changed from: protected */
    public int getBannerType() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public int getHeightInDp() {
        return 157;
    }

    /* access modifiers changed from: protected */
    public int getWidthInDp() {
        return 300;
    }

    public Cover(Activity activity) {
        super(activity);
    }

    public Cover(Activity activity, AdPreferences adPreferences) {
        super(activity, adPreferences);
    }

    public Cover(Activity activity, BannerListener bannerListener) {
        super(activity, bannerListener);
    }

    public Cover(Activity activity, AdPreferences adPreferences, BannerListener bannerListener) {
        super(activity, adPreferences, bannerListener);
    }

    public Cover(Activity activity, AttributeSet attributeSet) {
        super(activity, attributeSet);
    }

    public Cover(Activity activity, AttributeSet attributeSet, int i) {
        super(activity, attributeSet, i);
    }

    @Deprecated
    public Cover(Context context) {
        super(context);
    }

    @Deprecated
    public Cover(Context context, AdPreferences adPreferences) {
        super(context, adPreferences);
    }

    @Deprecated
    public Cover(Context context, BannerListener bannerListener) {
        super(context, bannerListener);
    }

    @Deprecated
    public Cover(Context context, AdPreferences adPreferences, BannerListener bannerListener) {
        super(context, adPreferences, bannerListener);
    }

    @Deprecated
    public Cover(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Deprecated
    public Cover(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
