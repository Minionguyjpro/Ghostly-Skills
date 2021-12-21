package com.mopub.mobileads;

import android.os.Handler;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VastVideoViewCountdownRunnableTwo.kt */
public final class VastVideoViewCountdownRunnableTwo extends RepeatingHandlerRunnable {
    private final VastVideoViewControllerTwo videoViewController;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VastVideoViewCountdownRunnableTwo(VastVideoViewControllerTwo vastVideoViewControllerTwo, Handler handler) {
        super(handler);
        Intrinsics.checkParameterIsNotNull(vastVideoViewControllerTwo, "videoViewController");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.videoViewController = vastVideoViewControllerTwo;
    }

    public void doWork() {
        VastVideoViewControllerTwo.updateCountdown$mopub_sdk_base_release$default(this.videoViewController, false, 1, (Object) null);
    }
}
