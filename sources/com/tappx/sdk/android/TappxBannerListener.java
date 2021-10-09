package com.tappx.sdk.android;

public interface TappxBannerListener {
    void onBannerClicked(TappxBanner tappxBanner);

    void onBannerCollapsed(TappxBanner tappxBanner);

    void onBannerExpanded(TappxBanner tappxBanner);

    void onBannerLoadFailed(TappxBanner tappxBanner, TappxAdError tappxAdError);

    void onBannerLoaded(TappxBanner tappxBanner);
}
