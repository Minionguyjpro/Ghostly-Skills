package com.mopub.mobileads;

import android.view.MotionEvent;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VastVideoViewControllerTwo.kt */
final class VastVideoViewControllerTwo$$special$$inlined$also$lambda$3 implements View.OnTouchListener {
    final /* synthetic */ VastVideoViewControllerTwo this$0;

    VastVideoViewControllerTwo$$special$$inlined$also$lambda$3(VastVideoViewControllerTwo vastVideoViewControllerTwo) {
        this.this$0 = vastVideoViewControllerTwo;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        Intrinsics.checkExpressionValueIsNotNull(motionEvent, "event");
        if (motionEvent.getAction() != 1) {
            return true;
        }
        VastVideoViewControllerTwo vastVideoViewControllerTwo = this.this$0;
        vastVideoViewControllerTwo.setClosing(!vastVideoViewControllerTwo.isInClickExperiment || this.this$0.isComplete());
        this.this$0.handleExitTrackers();
        this.this$0.getBaseVideoViewControllerListener().onFinish();
        return true;
    }
}
