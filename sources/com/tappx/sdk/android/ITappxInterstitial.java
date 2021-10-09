package com.tappx.sdk.android;

import android.content.Context;

public interface ITappxInterstitial {
    void destroy();

    Context getContext();

    boolean isReady();

    void loadAd();

    void loadAd(AdRequest adRequest);

    void setAutoShowWhenReady(boolean z);

    void setListener(TappxInterstitialListener tappxInterstitialListener);

    void show();
}
