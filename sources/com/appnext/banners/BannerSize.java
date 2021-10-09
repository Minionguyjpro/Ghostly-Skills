package com.appnext.banners;

import com.mopub.mobileads.MoPubView;
import com.startapp.android.publish.common.model.AdPreferences;

public class BannerSize {
    public static final BannerSize BANNER = new BannerSize(320, 50, AdPreferences.TYPE_BANNER);
    public static final BannerSize LARGE_BANNER = new BannerSize(320, 100, "LARGE_BANNER");
    public static final BannerSize MEDIUM_RECTANGLE = new BannerSize(300, MoPubView.MoPubAdSizeInt.HEIGHT_250_INT, "MEDIUM_RECTANGLE");
    private int height;
    private String name;
    private int width;

    private BannerSize(int i, int i2, String str) {
        this.width = i;
        this.height = i2;
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BannerSize)) {
            return false;
        }
        BannerSize bannerSize = (BannerSize) obj;
        return this.width == bannerSize.width && this.height == bannerSize.height && this.name.equals(bannerSize.name);
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public String toString() {
        return this.name;
    }
}
