package com.tappx.sdk.android;

public interface ITappxBanner {
    void destroy();

    void loadAd();

    void loadAd(AdRequest adRequest);

    void setAppKey(String str);

    void setEnableAutoRefresh(boolean z);

    void setListener(TappxBannerListener tappxBannerListener);

    void setRefreshTimeSeconds(int i);
}
