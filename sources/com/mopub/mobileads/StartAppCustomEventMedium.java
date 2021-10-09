package com.mopub.mobileads;

import android.content.Context;
import android.view.View;
import com.mopub.mobileads.MoPubView;
import com.startapp.android.publish.ads.banner.BannerListener;
import com.startapp.android.publish.ads.banner.Mrec;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.Map;

public class StartAppCustomEventMedium extends StartAppCustomEventBanner {
    /* access modifiers changed from: protected */
    public int getDefaultHeight() {
        return MoPubView.MoPubAdSizeInt.HEIGHT_250_INT;
    }

    /* access modifiers changed from: protected */
    public int getDefaultWidth() {
        return 300;
    }

    /* access modifiers changed from: protected */
    public View getBanner(Map<String, Object> map, Map<String, String> map2, Context context, AdPreferences adPreferences, BannerListener bannerListener) {
        return new Mrec(context, adPreferences, bannerListener);
    }
}
