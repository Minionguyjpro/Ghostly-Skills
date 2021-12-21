package com.mopub.mobileads;

public abstract class CustomEventRewardedVideo extends CustomEventRewardedAd {

    @Deprecated
    protected interface CustomEventRewardedVideoListener {
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public CustomEventRewardedVideoListener getVideoListenerForSdk() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract boolean hasVideoAvailable();

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract void showVideo();

    /* access modifiers changed from: protected */
    public boolean isReady() {
        return hasVideoAvailable();
    }

    /* access modifiers changed from: protected */
    public void show() {
        showVideo();
    }
}
