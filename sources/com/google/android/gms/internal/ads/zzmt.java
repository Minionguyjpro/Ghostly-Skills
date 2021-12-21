package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;

public final class zzmt extends zzls {
    private final VideoController.VideoLifecycleCallbacks zzuy;

    public zzmt(VideoController.VideoLifecycleCallbacks videoLifecycleCallbacks) {
        this.zzuy = videoLifecycleCallbacks;
    }

    public final void onVideoEnd() {
        this.zzuy.onVideoEnd();
    }

    public final void onVideoMute(boolean z) {
        this.zzuy.onVideoMute(z);
    }

    public final void onVideoPause() {
        this.zzuy.onVideoPause();
    }

    public final void onVideoPlay() {
        this.zzuy.onVideoPlay();
    }

    public final void onVideoStart() {
        this.zzuy.onVideoStart();
    }
}
