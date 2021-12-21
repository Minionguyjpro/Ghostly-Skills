package com.tappx.sdk.android;

public interface TappxInterstitialListener {
    void onInterstitialClicked(TappxInterstitial tappxInterstitial);

    void onInterstitialDismissed(TappxInterstitial tappxInterstitial);

    void onInterstitialLoadFailed(TappxInterstitial tappxInterstitial, TappxAdError tappxAdError);

    void onInterstitialLoaded(TappxInterstitial tappxInterstitial);

    void onInterstitialShown(TappxInterstitial tappxInterstitial);
}
