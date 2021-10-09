package com.google.ads;

import android.content.Context;
import com.mopub.mobileads.MoPubView;

@Deprecated
public final class AdSize {
    public static final AdSize BANNER = new AdSize(320, 50, "mb");
    public static final AdSize IAB_BANNER = new AdSize(468, 60, "as");
    public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90, "as");
    public static final AdSize IAB_MRECT = new AdSize(300, MoPubView.MoPubAdSizeInt.HEIGHT_250_INT, "as");
    public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
    private final com.google.android.gms.ads.AdSize zzcn;

    private AdSize(int i, int i2, String str) {
        this(new com.google.android.gms.ads.AdSize(i, i2));
    }

    public AdSize(com.google.android.gms.ads.AdSize adSize) {
        this.zzcn = adSize;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AdSize)) {
            return false;
        }
        return this.zzcn.equals(((AdSize) obj).zzcn);
    }

    public final int getHeight() {
        return this.zzcn.getHeight();
    }

    public final int getHeightInPixels(Context context) {
        return this.zzcn.getHeightInPixels(context);
    }

    public final int getWidth() {
        return this.zzcn.getWidth();
    }

    public final int getWidthInPixels(Context context) {
        return this.zzcn.getWidthInPixels(context);
    }

    public final int hashCode() {
        return this.zzcn.hashCode();
    }

    public final String toString() {
        return this.zzcn.toString();
    }
}
