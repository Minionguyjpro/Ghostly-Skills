package com.mopub.mobileads;

import androidx.media2.player.MediaPlayer;
import java.util.concurrent.Executor;

/* compiled from: VastVideoViewControllerTwo.kt */
final class VastVideoViewControllerTwo$createVideoView$$inlined$run$lambda$1 implements Runnable {
    final /* synthetic */ Executor $executor$inlined;
    final /* synthetic */ MediaPlayer $this_run;
    final /* synthetic */ VastVideoViewControllerTwo this$0;

    VastVideoViewControllerTwo$createVideoView$$inlined$run$lambda$1(MediaPlayer mediaPlayer, VastVideoViewControllerTwo vastVideoViewControllerTwo, Executor executor) {
        this.$this_run = mediaPlayer;
        this.this$0 = vastVideoViewControllerTwo;
        this.$executor$inlined = executor;
    }

    public final void run() {
        String diskMediaFileUrl;
        this.this$0.externalViewabilitySessionManager.onVideoPrepared(this.this$0.getLayout(), (int) this.$this_run.getDuration());
        this.this$0.adjustSkipOffset();
        this.this$0.getMediaPlayer().setPlayerVolume(1.0f);
        if (this.this$0.vastCompanionAdConfig == null && (diskMediaFileUrl = this.this$0.getVastVideoConfig().getDiskMediaFileUrl()) != null) {
            VastVideoViewControllerTwo vastVideoViewControllerTwo = this.this$0;
            vastVideoViewControllerTwo.prepareBlurredLastVideoFrame(vastVideoViewControllerTwo.getBlurredLastVideoFrameImageView(), diskMediaFileUrl);
        }
        this.this$0.getProgressBarWidget().calibrateAndMakeVisible((int) this.$this_run.getDuration(), this.this$0.getShowCloseButtonDelay());
        this.this$0.getRadialCountdownWidget().calibrateAndMakeVisible(this.this$0.getShowCloseButtonDelay());
        this.this$0.setCalibrationDone(true);
    }
}
