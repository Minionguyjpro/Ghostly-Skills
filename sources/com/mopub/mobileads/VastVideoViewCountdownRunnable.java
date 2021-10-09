package com.mopub.mobileads;

import android.os.Handler;
import com.mopub.common.Preconditions;

public class VastVideoViewCountdownRunnable extends RepeatingHandlerRunnable {
    private final VastVideoViewController mVideoViewController;

    public VastVideoViewCountdownRunnable(VastVideoViewController vastVideoViewController, Handler handler) {
        super(handler);
        Preconditions.checkNotNull(handler);
        Preconditions.checkNotNull(vastVideoViewController);
        this.mVideoViewController = vastVideoViewController;
    }

    public void doWork() {
        this.mVideoViewController.updateCountdown();
        if (this.mVideoViewController.shouldBeInteractable()) {
            this.mVideoViewController.makeVideoInteractable();
        }
    }
}
