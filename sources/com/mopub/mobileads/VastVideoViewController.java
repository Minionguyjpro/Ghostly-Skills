package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.mopub.common.ExternalViewabilitySession;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.mobileads.BaseVideoViewController;
import com.mopub.mobileads.VastWebView;
import com.mopub.mobileads.resource.DrawableConstants;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;

public class VastVideoViewController extends BaseVideoViewController {
    static final String CURRENT_POSITION = "current_position";
    static final int DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON = 5000;
    static final int MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON = 16000;
    static final String RESUMED_VAST_CONFIG = "resumed_vast_config";
    private static final int SEEKER_POSITION_NOT_INITIALIZED = -1;
    static final String VAST_VIDEO_CONFIG = "vast_video_config";
    private static final long VIDEO_COUNTDOWN_UPDATE_INTERVAL = 250;
    private static final long VIDEO_PROGRESS_TIMER_CHECKER_DELAY = 50;
    public static final int WEBVIEW_PADDING = 16;
    /* access modifiers changed from: private */
    public ImageView mBlurredLastVideoFrameImageView;
    /* access modifiers changed from: private */
    public VastVideoGradientStripWidget mBottomGradientStripWidget;
    private final View.OnTouchListener mClickThroughListener;
    private VastVideoCloseButtonWidget mCloseButtonWidget;
    private final VastVideoViewCountdownRunnable mCountdownRunnable;
    /* access modifiers changed from: private */
    public VastVideoCtaButtonWidget mCtaButtonWidget;
    /* access modifiers changed from: private */
    public int mDuration;
    /* access modifiers changed from: private */
    public ExternalViewabilitySessionManager mExternalViewabilitySessionManager;
    private boolean mHasSkipOffset = false;
    /* access modifiers changed from: private */
    public final View mIconView;
    /* access modifiers changed from: private */
    public boolean mIsCalibrationDone = false;
    /* access modifiers changed from: private */
    public boolean mIsClosing = false;
    /* access modifiers changed from: private */
    public boolean mIsVideoFinishedPlaying;
    /* access modifiers changed from: private */
    public final View mLandscapeCompanionAdView;
    /* access modifiers changed from: private */
    public final View mPortraitCompanionAdView;
    /* access modifiers changed from: private */
    public VastVideoProgressBarWidget mProgressBarWidget;
    private final VastVideoViewProgressRunnable mProgressCheckerRunnable;
    /* access modifiers changed from: private */
    public VastVideoRadialCountdownWidget mRadialCountdownWidget;
    private int mSeekerPositionOnPause = -1;
    /* access modifiers changed from: private */
    public int mShowCloseButtonDelay = DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON;
    private boolean mShowCloseButtonEventFired;
    /* access modifiers changed from: private */
    public VastVideoGradientStripWidget mTopGradientStripWidget;
    /* access modifiers changed from: private */
    public VastCompanionAdConfig mVastCompanionAdConfig;
    private final VastIconConfig mVastIconConfig;
    /* access modifiers changed from: private */
    public final VastVideoConfig mVastVideoConfig;
    /* access modifiers changed from: private */
    public boolean mVideoError;
    /* access modifiers changed from: private */
    public final VastVideoView mVideoView;

    VastVideoViewController(final Activity activity, Bundle bundle, Bundle bundle2, long j, BaseVideoViewController.BaseVideoViewControllerListener baseVideoViewControllerListener) throws IllegalStateException {
        super(activity, Long.valueOf(j), baseVideoViewControllerListener);
        Serializable serializable = bundle2 != null ? bundle2.getSerializable("resumed_vast_config") : null;
        Serializable serializable2 = bundle.getSerializable("vast_video_config");
        if (serializable != null && (serializable instanceof VastVideoConfig)) {
            this.mVastVideoConfig = (VastVideoConfig) serializable;
            this.mSeekerPositionOnPause = bundle2.getInt("current_position", -1);
        } else if (serializable2 == null || !(serializable2 instanceof VastVideoConfig)) {
            throw new IllegalStateException("VastVideoConfig is invalid");
        } else {
            this.mVastVideoConfig = (VastVideoConfig) serializable2;
        }
        if (this.mVastVideoConfig.getDiskMediaFileUrl() != null) {
            this.mVastCompanionAdConfig = this.mVastVideoConfig.getVastCompanionAd(activity.getResources().getConfiguration().orientation);
            this.mVastIconConfig = this.mVastVideoConfig.getVastIconConfig();
            this.mClickThroughListener = new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1 && VastVideoViewController.this.shouldAllowClickThrough()) {
                        VastVideoViewController.this.mExternalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_CLICK_THRU, VastVideoViewController.this.getCurrentPosition());
                        boolean unused = VastVideoViewController.this.mIsClosing = true;
                        VastVideoViewController.this.broadcastAction(IntentActions.ACTION_INTERSTITIAL_CLICK);
                        VastVideoViewController.this.mVastVideoConfig.handleClickForResult(activity, VastVideoViewController.this.mIsVideoFinishedPlaying ? VastVideoViewController.this.mDuration : VastVideoViewController.this.getCurrentPosition(), 1);
                    }
                    return true;
                }
            };
            getLayout().setBackgroundColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
            addBlurredLastVideoFrameImageView(activity, 4);
            VastVideoView createVideoView = createVideoView(activity, 0);
            this.mVideoView = createVideoView;
            createVideoView.requestFocus();
            ExternalViewabilitySessionManager externalViewabilitySessionManager = new ExternalViewabilitySessionManager(activity);
            this.mExternalViewabilitySessionManager = externalViewabilitySessionManager;
            externalViewabilitySessionManager.createVideoSession(activity, (View) this.mVideoView, this.mVastVideoConfig);
            this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mBlurredLastVideoFrameImageView);
            this.mLandscapeCompanionAdView = createCompanionAdView(activity, this.mVastVideoConfig.getVastCompanionAd(2), 4);
            this.mPortraitCompanionAdView = createCompanionAdView(activity, this.mVastVideoConfig.getVastCompanionAd(1), 4);
            addTopGradientStripWidget(activity);
            addProgressBarWidget(activity, 4);
            addBottomGradientStripWidget(activity);
            addRadialCountdownWidget(activity, 4);
            this.mIconView = createIconView(activity, this.mVastIconConfig, 4);
            addCtaButtonWidget(activity);
            addCloseButtonWidget(activity, 8);
            Handler handler = new Handler(Looper.getMainLooper());
            this.mProgressCheckerRunnable = new VastVideoViewProgressRunnable(this, this.mVastVideoConfig, handler);
            this.mCountdownRunnable = new VastVideoViewCountdownRunnable(this, handler);
            return;
        }
        throw new IllegalStateException("VastVideoConfig does not have a video disk path");
    }

    /* access modifiers changed from: protected */
    public VideoView getVideoView() {
        return this.mVideoView;
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        super.onCreate();
        this.mVastVideoConfig.handleImpression(getContext(), getCurrentPosition());
        broadcastAction(IntentActions.ACTION_INTERSTITIAL_SHOW);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        startRunnables();
        if (this.mSeekerPositionOnPause > 0) {
            this.mExternalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_PLAYING, this.mSeekerPositionOnPause);
            this.mVideoView.seekTo(this.mSeekerPositionOnPause);
        } else {
            this.mExternalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_LOADED, getCurrentPosition());
        }
        if (!this.mIsVideoFinishedPlaying) {
            this.mVideoView.start();
        }
        if (this.mSeekerPositionOnPause != -1) {
            this.mVastVideoConfig.handleResume(getContext(), this.mSeekerPositionOnPause);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        stopRunnables();
        this.mSeekerPositionOnPause = getCurrentPosition();
        this.mVideoView.pause();
        if (!this.mIsVideoFinishedPlaying && !this.mIsClosing) {
            this.mExternalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_PAUSED, getCurrentPosition());
            this.mVastVideoConfig.handlePause(getContext(), this.mSeekerPositionOnPause);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        stopRunnables();
        this.mExternalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_STOPPED, getCurrentPosition());
        this.mExternalViewabilitySessionManager.endVideoSession();
        broadcastAction(IntentActions.ACTION_INTERSTITIAL_DISMISS);
        this.mVideoView.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("current_position", this.mSeekerPositionOnPause);
        bundle.putSerializable("resumed_vast_config", this.mVastVideoConfig);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        int i = getContext().getResources().getConfiguration().orientation;
        this.mVastCompanionAdConfig = this.mVastVideoConfig.getVastCompanionAd(i);
        if (this.mLandscapeCompanionAdView.getVisibility() == 0 || this.mPortraitCompanionAdView.getVisibility() == 0) {
            if (i == 1) {
                this.mLandscapeCompanionAdView.setVisibility(4);
                this.mPortraitCompanionAdView.setVisibility(0);
            } else {
                this.mPortraitCompanionAdView.setVisibility(4);
                this.mLandscapeCompanionAdView.setVisibility(0);
            }
            VastCompanionAdConfig vastCompanionAdConfig = this.mVastCompanionAdConfig;
            if (vastCompanionAdConfig != null) {
                vastCompanionAdConfig.handleImpression(getContext(), this.mDuration);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onBackPressed() {
        handleExitTrackers();
    }

    public boolean backButtonEnabled() {
        return this.mShowCloseButtonEventFired;
    }

    /* access modifiers changed from: package-private */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            getBaseVideoViewControllerListener().onFinish();
        }
    }

    /* access modifiers changed from: private */
    public void handleExitTrackers() {
        int currentPosition = getCurrentPosition();
        if (!this.mIsVideoFinishedPlaying) {
            if (currentPosition < this.mDuration) {
                this.mExternalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_SKIPPED, currentPosition);
                this.mVastVideoConfig.handleSkip(getContext(), currentPosition);
            } else {
                this.mExternalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_COMPLETE, currentPosition);
                this.mVastVideoConfig.handleComplete(getContext(), this.mDuration);
            }
        }
        this.mVastVideoConfig.handleClose(getContext(), this.mDuration);
    }

    /* access modifiers changed from: private */
    public void adjustSkipOffset() {
        int duration = getDuration();
        if (this.mVastVideoConfig.isRewardedVideo()) {
            this.mShowCloseButtonDelay = duration;
            return;
        }
        if (duration < MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON) {
            this.mShowCloseButtonDelay = duration;
        }
        try {
            Integer skipOffsetMillis = this.mVastVideoConfig.getSkipOffsetMillis(duration);
            if (skipOffsetMillis != null) {
                this.mShowCloseButtonDelay = skipOffsetMillis.intValue();
                this.mHasSkipOffset = true;
            }
        } catch (NumberFormatException unused) {
            MoPubLog.SdkLogEvent sdkLogEvent = MoPubLog.SdkLogEvent.CUSTOM;
            MoPubLog.log(sdkLogEvent, "Failed to parse skipoffset " + this.mVastVideoConfig.getSkipOffsetString());
        }
    }

    private VastVideoView createVideoView(final Context context, int i) {
        if (this.mVastVideoConfig.getDiskMediaFileUrl() != null) {
            final VastVideoView vastVideoView = new VastVideoView(context);
            vastVideoView.setId(View.generateViewId());
            vastVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    VastVideoViewController vastVideoViewController = VastVideoViewController.this;
                    int unused = vastVideoViewController.mDuration = vastVideoViewController.mVideoView.getDuration();
                    VastVideoViewController.this.mExternalViewabilitySessionManager.onVideoPrepared(VastVideoViewController.this.getLayout(), VastVideoViewController.this.mDuration);
                    VastVideoViewController.this.adjustSkipOffset();
                    if (VastVideoViewController.this.mVastCompanionAdConfig == null) {
                        vastVideoView.prepareBlurredLastVideoFrame(VastVideoViewController.this.mBlurredLastVideoFrameImageView, VastVideoViewController.this.mVastVideoConfig.getDiskMediaFileUrl());
                    }
                    VastVideoViewController.this.mProgressBarWidget.calibrateAndMakeVisible(VastVideoViewController.this.getDuration(), VastVideoViewController.this.mShowCloseButtonDelay);
                    VastVideoViewController.this.mRadialCountdownWidget.calibrateAndMakeVisible(VastVideoViewController.this.mShowCloseButtonDelay);
                    boolean unused2 = VastVideoViewController.this.mIsCalibrationDone = true;
                }
            });
            vastVideoView.setOnTouchListener(this.mClickThroughListener);
            vastVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    VastVideoViewController.this.stopRunnables();
                    VastVideoViewController.this.makeVideoInteractable();
                    VastVideoViewController.this.videoCompleted(false);
                    boolean unused = VastVideoViewController.this.mIsVideoFinishedPlaying = true;
                    if (VastVideoViewController.this.mVastVideoConfig.isRewardedVideo()) {
                        VastVideoViewController.this.broadcastAction(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
                    }
                    if (!VastVideoViewController.this.mVideoError && VastVideoViewController.this.mVastVideoConfig.getRemainingProgressTrackerCount() == 0) {
                        VastVideoViewController.this.mExternalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_COMPLETE, VastVideoViewController.this.getCurrentPosition());
                        VastVideoViewController.this.mVastVideoConfig.handleComplete(VastVideoViewController.this.getContext(), VastVideoViewController.this.getCurrentPosition());
                    }
                    vastVideoView.setVisibility(4);
                    VastVideoViewController.this.mProgressBarWidget.setVisibility(8);
                    VastVideoViewController.this.mIconView.setVisibility(8);
                    VastVideoViewController.this.mTopGradientStripWidget.notifyVideoComplete();
                    VastVideoViewController.this.mBottomGradientStripWidget.notifyVideoComplete();
                    VastVideoViewController.this.mCtaButtonWidget.notifyVideoComplete();
                    if (VastVideoViewController.this.mVastCompanionAdConfig != null) {
                        if (context.getResources().getConfiguration().orientation == 1) {
                            VastVideoViewController.this.mPortraitCompanionAdView.setVisibility(0);
                        } else {
                            VastVideoViewController.this.mLandscapeCompanionAdView.setVisibility(0);
                        }
                        VastVideoViewController.this.mVastCompanionAdConfig.handleImpression(context, VastVideoViewController.this.mDuration);
                    } else if (VastVideoViewController.this.mBlurredLastVideoFrameImageView.getDrawable() != null) {
                        VastVideoViewController.this.mBlurredLastVideoFrameImageView.setVisibility(0);
                    }
                }
            });
            vastVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    VastVideoViewController.this.mExternalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.RECORD_AD_ERROR, VastVideoViewController.this.getCurrentPosition());
                    VastVideoViewController.this.stopRunnables();
                    VastVideoViewController.this.makeVideoInteractable();
                    VastVideoViewController.this.videoError(false);
                    boolean unused = VastVideoViewController.this.mVideoError = true;
                    VastVideoViewController.this.mVastVideoConfig.handleError(VastVideoViewController.this.getContext(), VastErrorCode.GENERAL_LINEAR_AD_ERROR, VastVideoViewController.this.getCurrentPosition());
                    return false;
                }
            });
            vastVideoView.setVideoPath(this.mVastVideoConfig.getDiskMediaFileUrl());
            vastVideoView.setVisibility(i);
            return vastVideoView;
        }
        throw new IllegalStateException("VastVideoConfig does not have a video disk path");
    }

    private void addTopGradientStripWidget(Context context) {
        this.mTopGradientStripWidget = new VastVideoGradientStripWidget(context, GradientDrawable.Orientation.TOP_BOTTOM, this.mVastCompanionAdConfig != null, 0, 6, getLayout().getId());
        getLayout().addView(this.mTopGradientStripWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mTopGradientStripWidget);
    }

    private void addBottomGradientStripWidget(Context context) {
        this.mBottomGradientStripWidget = new VastVideoGradientStripWidget(context, GradientDrawable.Orientation.BOTTOM_TOP, this.mVastCompanionAdConfig != null, 8, 2, this.mProgressBarWidget.getId());
        getLayout().addView(this.mBottomGradientStripWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mBottomGradientStripWidget);
    }

    private void addProgressBarWidget(Context context, int i) {
        VastVideoProgressBarWidget vastVideoProgressBarWidget = new VastVideoProgressBarWidget(context);
        this.mProgressBarWidget = vastVideoProgressBarWidget;
        vastVideoProgressBarWidget.setAnchorId(this.mVideoView.getId());
        this.mProgressBarWidget.setVisibility(i);
        getLayout().addView(this.mProgressBarWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mProgressBarWidget);
    }

    private void addRadialCountdownWidget(Context context, int i) {
        VastVideoRadialCountdownWidget vastVideoRadialCountdownWidget = new VastVideoRadialCountdownWidget(context);
        this.mRadialCountdownWidget = vastVideoRadialCountdownWidget;
        vastVideoRadialCountdownWidget.setVisibility(i);
        getLayout().addView(this.mRadialCountdownWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mRadialCountdownWidget);
    }

    private void addCtaButtonWidget(Context context) {
        this.mCtaButtonWidget = new VastVideoCtaButtonWidget(context, this.mVideoView.getId(), this.mVastCompanionAdConfig != null, true ^ TextUtils.isEmpty(this.mVastVideoConfig.getClickThroughUrl()));
        getLayout().addView(this.mCtaButtonWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mCtaButtonWidget);
        this.mCtaButtonWidget.setOnTouchListener(this.mClickThroughListener);
        String customCtaText = this.mVastVideoConfig.getCustomCtaText();
        if (customCtaText != null) {
            this.mCtaButtonWidget.updateCtaText(customCtaText);
        }
    }

    private void addCloseButtonWidget(Context context, int i) {
        VastVideoCloseButtonWidget vastVideoCloseButtonWidget = new VastVideoCloseButtonWidget(context);
        this.mCloseButtonWidget = vastVideoCloseButtonWidget;
        vastVideoCloseButtonWidget.setVisibility(i);
        getLayout().addView(this.mCloseButtonWidget);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(this.mCloseButtonWidget);
        this.mCloseButtonWidget.setOnTouchListenerToContent(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 1) {
                    return true;
                }
                boolean unused = VastVideoViewController.this.mIsClosing = true;
                VastVideoViewController.this.handleExitTrackers();
                VastVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
                return true;
            }
        });
        String customSkipText = this.mVastVideoConfig.getCustomSkipText();
        if (customSkipText != null) {
            this.mCloseButtonWidget.updateCloseButtonText(customSkipText);
        }
        String customCloseIconUrl = this.mVastVideoConfig.getCustomCloseIconUrl();
        if (customCloseIconUrl != null) {
            this.mCloseButtonWidget.updateCloseButtonIcon(customCloseIconUrl);
        }
    }

    private void addBlurredLastVideoFrameImageView(Context context, int i) {
        ImageView imageView = new ImageView(context);
        this.mBlurredLastVideoFrameImageView = imageView;
        imageView.setVisibility(i);
        getLayout().addView(this.mBlurredLastVideoFrameImageView, new RelativeLayout.LayoutParams(-1, -1));
    }

    /* access modifiers changed from: package-private */
    public View createCompanionAdView(Context context, VastCompanionAdConfig vastCompanionAdConfig, int i) {
        Preconditions.checkNotNull(context);
        if (vastCompanionAdConfig == null) {
            View view = new View(context);
            view.setVisibility(4);
            return view;
        }
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(17);
        getLayout().addView(relativeLayout, new RelativeLayout.LayoutParams(-1, -1));
        this.mExternalViewabilitySessionManager.registerVideoObstruction(relativeLayout);
        VastWebView createCompanionVastWebView = createCompanionVastWebView(context, vastCompanionAdConfig);
        createCompanionVastWebView.setVisibility(i);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Dips.dipsToIntPixels((float) (vastCompanionAdConfig.getWidth() + 16), context), Dips.dipsToIntPixels((float) (vastCompanionAdConfig.getHeight() + 16), context));
        layoutParams.addRule(13, -1);
        relativeLayout.addView(createCompanionVastWebView, layoutParams);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(createCompanionVastWebView);
        return createCompanionVastWebView;
    }

    /* access modifiers changed from: package-private */
    public View createIconView(final Context context, final VastIconConfig vastIconConfig, int i) {
        Preconditions.checkNotNull(context);
        if (vastIconConfig == null) {
            return new View(context);
        }
        VastWebView createView = VastWebView.createView(context, vastIconConfig.getVastResource());
        createView.setVastWebViewClickListener(new VastWebView.VastWebViewClickListener() {
            public void onVastWebViewClick() {
                TrackingRequest.makeVastTrackingHttpRequest(vastIconConfig.getClickTrackingUris(), (VastErrorCode) null, Integer.valueOf(VastVideoViewController.this.getCurrentPosition()), VastVideoViewController.this.getNetworkMediaFileUrl(), context);
                vastIconConfig.handleClick(VastVideoViewController.this.getContext(), (String) null, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
            }
        });
        createView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                vastIconConfig.handleClick(VastVideoViewController.this.getContext(), str, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
                return true;
            }
        });
        createView.setVisibility(i);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Dips.asIntPixels((float) vastIconConfig.getWidth(), context), Dips.asIntPixels((float) vastIconConfig.getHeight(), context));
        layoutParams.setMargins(Dips.dipsToIntPixels(12.0f, context), Dips.dipsToIntPixels(12.0f, context), 0, 0);
        getLayout().addView(createView, layoutParams);
        this.mExternalViewabilitySessionManager.registerVideoObstruction(createView);
        return createView;
    }

    /* access modifiers changed from: package-private */
    public int getDuration() {
        return this.mVideoView.getDuration();
    }

    /* access modifiers changed from: package-private */
    public int getCurrentPosition() {
        return this.mVideoView.getCurrentPosition();
    }

    /* access modifiers changed from: package-private */
    public void makeVideoInteractable() {
        this.mShowCloseButtonEventFired = true;
        this.mRadialCountdownWidget.setVisibility(8);
        this.mCloseButtonWidget.setVisibility(0);
        this.mCtaButtonWidget.notifyVideoSkippable();
    }

    /* access modifiers changed from: package-private */
    public boolean shouldBeInteractable() {
        return !this.mShowCloseButtonEventFired && getCurrentPosition() >= this.mShowCloseButtonDelay;
    }

    /* access modifiers changed from: package-private */
    public void updateCountdown() {
        if (this.mIsCalibrationDone) {
            this.mRadialCountdownWidget.updateCountdownProgress(this.mShowCloseButtonDelay, getCurrentPosition());
        }
    }

    /* access modifiers changed from: package-private */
    public void updateProgressBar() {
        this.mProgressBarWidget.updateProgress(getCurrentPosition());
    }

    /* access modifiers changed from: package-private */
    public String getNetworkMediaFileUrl() {
        VastVideoConfig vastVideoConfig = this.mVastVideoConfig;
        if (vastVideoConfig == null) {
            return null;
        }
        return vastVideoConfig.getNetworkMediaFileUrl();
    }

    /* access modifiers changed from: package-private */
    public void handleIconDisplay(int i) {
        VastIconConfig vastIconConfig = this.mVastIconConfig;
        if (vastIconConfig != null && i >= vastIconConfig.getOffsetMS()) {
            this.mIconView.setVisibility(0);
            this.mVastIconConfig.handleImpression(getContext(), i, getNetworkMediaFileUrl());
            if (this.mVastIconConfig.getDurationMS() != null && i >= this.mVastIconConfig.getOffsetMS() + this.mVastIconConfig.getDurationMS().intValue()) {
                this.mIconView.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handleViewabilityQuartileEvent(String str) {
        this.mExternalViewabilitySessionManager.recordVideoEvent((ExternalViewabilitySession.VideoEvent) Enum.valueOf(ExternalViewabilitySession.VideoEvent.class, str), getCurrentPosition());
    }

    /* access modifiers changed from: private */
    public boolean shouldAllowClickThrough() {
        return this.mShowCloseButtonEventFired;
    }

    private void startRunnables() {
        this.mProgressCheckerRunnable.startRepeating(VIDEO_PROGRESS_TIMER_CHECKER_DELAY);
        this.mCountdownRunnable.startRepeating(VIDEO_COUNTDOWN_UPDATE_INTERVAL);
    }

    /* access modifiers changed from: private */
    public void stopRunnables() {
        this.mProgressCheckerRunnable.stop();
        this.mCountdownRunnable.stop();
    }

    private VastWebView createCompanionVastWebView(final Context context, final VastCompanionAdConfig vastCompanionAdConfig) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastCompanionAdConfig);
        VastWebView createView = VastWebView.createView(context, vastCompanionAdConfig.getVastResource());
        createView.setVastWebViewClickListener(new VastWebView.VastWebViewClickListener() {
            public void onVastWebViewClick() {
                VastVideoViewController.this.broadcastAction(IntentActions.ACTION_INTERSTITIAL_CLICK);
                TrackingRequest.makeVastTrackingHttpRequest(vastCompanionAdConfig.getClickTrackers(), (VastErrorCode) null, Integer.valueOf(VastVideoViewController.this.mDuration), (String) null, context);
                vastCompanionAdConfig.handleClick(context, 1, (String) null, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
            }
        });
        createView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                vastCompanionAdConfig.handleClick(context, 1, str, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
                return true;
            }
        });
        return createView;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VastVideoViewProgressRunnable getProgressCheckerRunnable() {
        return this.mProgressCheckerRunnable;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VastVideoViewCountdownRunnable getCountdownRunnable() {
        return this.mCountdownRunnable;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean getHasSkipOffset() {
        return this.mHasSkipOffset;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public int getShowCloseButtonDelay() {
        return this.mShowCloseButtonDelay;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean isShowCloseButtonEventFired() {
        return this.mShowCloseButtonEventFired;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setCloseButtonVisible(boolean z) {
        this.mShowCloseButtonEventFired = z;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean isVideoFinishedPlaying() {
        return this.mIsVideoFinishedPlaying;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean isCalibrationDone() {
        return this.mIsCalibrationDone;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public View getLandscapeCompanionAdView() {
        return this.mLandscapeCompanionAdView;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public View getPortraitCompanionAdView() {
        return this.mPortraitCompanionAdView;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean getVideoError() {
        return this.mVideoError;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setVideoError() {
        this.mVideoError = true;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public View getIconView() {
        return this.mIconView;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VastVideoGradientStripWidget getTopGradientStripWidget() {
        return this.mTopGradientStripWidget;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VastVideoGradientStripWidget getBottomGradientStripWidget() {
        return this.mBottomGradientStripWidget;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VastVideoProgressBarWidget getProgressBarWidget() {
        return this.mProgressBarWidget;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setProgressBarWidget(VastVideoProgressBarWidget vastVideoProgressBarWidget) {
        this.mProgressBarWidget = vastVideoProgressBarWidget;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VastVideoRadialCountdownWidget getRadialCountdownWidget() {
        return this.mRadialCountdownWidget;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setRadialCountdownWidget(VastVideoRadialCountdownWidget vastVideoRadialCountdownWidget) {
        this.mRadialCountdownWidget = vastVideoRadialCountdownWidget;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VastVideoCtaButtonWidget getCtaButtonWidget() {
        return this.mCtaButtonWidget;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VastVideoCloseButtonWidget getCloseButtonWidget() {
        return this.mCloseButtonWidget;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public ImageView getBlurredLastVideoFrameImageView() {
        return this.mBlurredLastVideoFrameImageView;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VastVideoView getVastVideoView() {
        return this.mVideoView;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setIsClosing(boolean z) {
        this.mIsClosing = z;
    }
}
