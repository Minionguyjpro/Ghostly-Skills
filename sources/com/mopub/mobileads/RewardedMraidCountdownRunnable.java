package com.mopub.mobileads;

import android.os.Handler;
import com.mopub.common.Preconditions;
import com.mopub.mraid.RewardedMraidController;

public class RewardedMraidCountdownRunnable extends RepeatingHandlerRunnable {
    private int mCurrentElapsedTimeMillis;
    private final RewardedMraidController mRewardedMraidController;

    public RewardedMraidCountdownRunnable(RewardedMraidController rewardedMraidController, Handler handler) {
        super(handler);
        Preconditions.checkNotNull(handler);
        Preconditions.checkNotNull(rewardedMraidController);
        this.mRewardedMraidController = rewardedMraidController;
    }

    public void doWork() {
        int i = (int) (((long) this.mCurrentElapsedTimeMillis) + this.mUpdateIntervalMillis);
        this.mCurrentElapsedTimeMillis = i;
        this.mRewardedMraidController.updateCountdown(i);
        if (this.mRewardedMraidController.isPlayableCloseable()) {
            this.mRewardedMraidController.showPlayableCloseButton();
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public int getCurrentElapsedTimeMillis() {
        return this.mCurrentElapsedTimeMillis;
    }
}
