package com.mopub.nativeads;

import com.mopub.nativeads.MoPubNativeAdPositioning;

interface PositioningSource {

    public interface PositioningListener {
        void onFailed();

        void onLoad(MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning);
    }

    void loadPositions(String str, PositioningListener positioningListener);
}
