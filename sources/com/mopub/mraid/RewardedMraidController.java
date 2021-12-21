package com.mopub.mraid;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.IntentActions;
import com.mopub.mobileads.BaseBroadcastReceiver;
import com.mopub.mobileads.RewardedMraidCountdownRunnable;
import com.mopub.mobileads.VastVideoRadialCountdownWidget;

public class RewardedMraidController extends MraidController {
    static final int DEFAULT_PLAYABLE_DURATION_FOR_CLOSE_BUTTON_MILLIS = 30000;
    public static final int DEFAULT_PLAYABLE_DURATION_FOR_CLOSE_BUTTON_SECONDS = 30;
    public static final boolean DEFAULT_PLAYABLE_SHOULD_REWARD_ON_CLICK = false;
    public static final int MILLIS_IN_SECOND = 1000;
    static final long PLAYABLE_COUNTDOWN_UPDATE_INTERVAL_MILLIS = 250;
    private final long mBroadcastIdentifier;
    private CloseableLayout mCloseableLayout;
    private RewardedMraidCountdownRunnable mCountdownRunnable;
    private int mCurrentElapsedTimeMillis;
    private boolean mIsCalibrationDone;
    private boolean mIsRewarded;
    private VastVideoRadialCountdownWidget mRadialCountdownWidget;
    private final int mShowCloseButtonDelay;
    private boolean mShowCloseButtonEventFired;

    /* access modifiers changed from: protected */
    public void handleCustomClose(boolean z) {
    }

    public RewardedMraidController(Context context, AdReport adReport, PlacementType placementType, int i, long j) {
        super(context, adReport, placementType);
        int i2 = i * 1000;
        if (i2 < 0 || i2 > 30000) {
            this.mShowCloseButtonDelay = 30000;
        } else {
            this.mShowCloseButtonDelay = i2;
        }
        this.mBroadcastIdentifier = j;
    }

    public void create(Context context, CloseableLayout closeableLayout) {
        this.mCloseableLayout = closeableLayout;
        closeableLayout.setCloseAlwaysInteractable(false);
        this.mCloseableLayout.setCloseVisible(false);
        addRadialCountdownWidget(context, 4);
        this.mRadialCountdownWidget.calibrateAndMakeVisible(this.mShowCloseButtonDelay);
        this.mIsCalibrationDone = true;
        this.mCountdownRunnable = new RewardedMraidCountdownRunnable(this, new Handler(Looper.getMainLooper()));
    }

    public void pause() {
        stopRunnables();
        super.pause(false);
    }

    public void resume() {
        super.resume();
        startRunnables();
    }

    public void destroy() {
        stopRunnables();
        super.destroy();
    }

    /* access modifiers changed from: protected */
    public void handleClose() {
        if (this.mShowCloseButtonEventFired) {
            super.handleClose();
        }
    }

    public boolean backButtonEnabled() {
        return this.mShowCloseButtonEventFired;
    }

    public boolean isPlayableCloseable() {
        return !this.mShowCloseButtonEventFired && this.mCurrentElapsedTimeMillis >= this.mShowCloseButtonDelay;
    }

    public void showPlayableCloseButton() {
        this.mShowCloseButtonEventFired = true;
        this.mRadialCountdownWidget.setVisibility(8);
        this.mCloseableLayout.setCloseVisible(true);
        if (!this.mIsRewarded) {
            BaseBroadcastReceiver.broadcastAction(getContext(), this.mBroadcastIdentifier, IntentActions.ACTION_REWARDED_PLAYABLE_COMPLETE);
            this.mIsRewarded = true;
        }
    }

    public void updateCountdown(int i) {
        this.mCurrentElapsedTimeMillis = i;
        if (this.mIsCalibrationDone) {
            this.mRadialCountdownWidget.updateCountdownProgress(this.mShowCloseButtonDelay, i);
        }
    }

    private void startRunnables() {
        this.mCountdownRunnable.startRepeating(PLAYABLE_COUNTDOWN_UPDATE_INTERVAL_MILLIS);
    }

    private void stopRunnables() {
        this.mCountdownRunnable.stop();
    }

    private void addRadialCountdownWidget(Context context, int i) {
        VastVideoRadialCountdownWidget vastVideoRadialCountdownWidget = new VastVideoRadialCountdownWidget(context);
        this.mRadialCountdownWidget = vastVideoRadialCountdownWidget;
        vastVideoRadialCountdownWidget.setVisibility(i);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mRadialCountdownWidget.getLayoutParams();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(marginLayoutParams.width + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.height + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin);
        layoutParams.gravity = 53;
        this.mCloseableLayout.addView(this.mRadialCountdownWidget, layoutParams);
    }

    @Deprecated
    public int getShowCloseButtonDelay() {
        return this.mShowCloseButtonDelay;
    }

    @Deprecated
    public VastVideoRadialCountdownWidget getRadialCountdownWidget() {
        return this.mRadialCountdownWidget;
    }

    @Deprecated
    public RewardedMraidCountdownRunnable getCountdownRunnable() {
        return this.mCountdownRunnable;
    }

    @Deprecated
    public boolean isCalibrationDone() {
        return this.mIsCalibrationDone;
    }

    @Deprecated
    public boolean isShowCloseButtonEventFired() {
        return this.mShowCloseButtonEventFired;
    }

    @Deprecated
    public boolean isRewarded() {
        return this.mIsRewarded;
    }
}
