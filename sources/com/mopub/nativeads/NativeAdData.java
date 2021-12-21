package com.mopub.nativeads;

class NativeAdData {
    private final MoPubAdRenderer adRenderer;
    private final NativeAd adResponse;
    private final String adUnitId;

    NativeAdData(String str, MoPubAdRenderer moPubAdRenderer, NativeAd nativeAd) {
        this.adUnitId = str;
        this.adRenderer = moPubAdRenderer;
        this.adResponse = nativeAd;
    }

    /* access modifiers changed from: package-private */
    public String getAdUnitId() {
        return this.adUnitId;
    }

    /* access modifiers changed from: package-private */
    public MoPubAdRenderer getAdRenderer() {
        return this.adRenderer;
    }

    /* access modifiers changed from: package-private */
    public NativeAd getAd() {
        return this.adResponse;
    }
}
