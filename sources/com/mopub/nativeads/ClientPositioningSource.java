package com.mopub.nativeads;

import android.os.Handler;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import com.mopub.nativeads.PositioningSource;

class ClientPositioningSource implements PositioningSource {
    private final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public final MoPubNativeAdPositioning.MoPubClientPositioning mPositioning;

    ClientPositioningSource(MoPubNativeAdPositioning.MoPubClientPositioning moPubClientPositioning) {
        this.mPositioning = MoPubNativeAdPositioning.clone(moPubClientPositioning);
    }

    public void loadPositions(String str, final PositioningSource.PositioningListener positioningListener) {
        this.mHandler.post(new Runnable() {
            public void run() {
                positioningListener.onLoad(ClientPositioningSource.this.mPositioning);
            }
        });
    }
}
