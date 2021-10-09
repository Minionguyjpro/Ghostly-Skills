package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.UriMediaItem;
import androidx.media2.player.MediaPlayer;
import androidx.media2.player.PlaybackParams;
import androidx.media2.widget.VideoView;
import com.mopub.common.ExternalViewabilitySession;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.IntentActions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.Dips;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

@Mockable
/* compiled from: VastVideoViewControllerTwo.kt */
public class VastVideoViewControllerTwo extends BaseVideoViewController {
    public static final String CURRENT_POSITION = "current_position";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON = 5000;
    public static final String RESUMED_VAST_CONFIG = "resumed_vast_config";
    private static final int SEEKER_POSITION_NOT_INITIALIZED = -1;
    public static final String VAST_VIDEO_CONFIG = "vast_video_config";
    private static final long VIDEO_COUNTDOWN_UPDATE_INTERVAL = 250;
    private static final long VIDEO_PROGRESS_TIMER_CHECKER_DELAY = 50;
    private final Activity activity;
    private VastVideoBlurLastVideoFrameTask blurLastVideoFrameTask;
    private final ImageView blurredLastVideoFrameImageView;
    public VastVideoGradientStripWidget bottomGradientStripWidget;
    private final View.OnTouchListener clickThroughListener;
    public VastVideoCloseButtonWidget closeButtonWidget;
    private final VastVideoViewCountdownRunnableTwo countdownRunnable;
    private final VastVideoCtaButtonWidget ctaButtonWidget;
    /* access modifiers changed from: private */
    public final ExternalViewabilitySessionManager externalViewabilitySessionManager = new ExternalViewabilitySessionManager(getActivity());
    private final Bundle extras;
    private final View iconView;
    private boolean isCalibrationDone;
    private boolean isClosing;
    private boolean isComplete;
    /* access modifiers changed from: private */
    public boolean isInClickExperiment;
    private final View landscapeCompanionAdView;
    private MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
    private final MediaPlayer mediaPlayer = new MediaPlayer(getContext());
    private final View portraitCompanionAdView;
    public VastVideoProgressBarWidget progressBarWidget;
    private final VastVideoViewProgressRunnableTwo progressCheckerRunnable;
    public VastVideoRadialCountdownWidget radialCountdownWidget;
    private final Bundle savedInstanceState;
    private int seekerPositionOnPause = -1;
    private boolean shouldAllowClose;
    private boolean shouldAllowSkip;
    private int showCloseButtonDelay = DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON;
    public VastVideoGradientStripWidget topGradientStripWidget;
    /* access modifiers changed from: private */
    public VastCompanionAdConfigTwo vastCompanionAdConfig;
    private final VastIconConfigTwo vastIconConfig;
    private final VastVideoConfigTwo vastVideoConfig;
    private boolean videoError;
    /* access modifiers changed from: private */
    public final VideoView videoView;

    @VisibleForTesting
    public static /* synthetic */ void blurLastVideoFrameTask$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void blurredLastVideoFrameImageView$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void bottomGradientStripWidget$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void closeButtonWidget$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void ctaButtonWidget$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void iconView$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void isCalibrationDone$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void isClosing$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void isComplete$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void landscapeCompanionAdView$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void mediaMetadataRetriever$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void portraitCompanionAdView$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void progressBarWidget$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void radialCountdownWidget$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void shouldAllowClose$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void showCloseButtonDelay$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void topGradientStripWidget$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void vastIconConfig$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void vastVideoConfig$annotations() {
    }

    public Activity getActivity() {
        return this.activity;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public Bundle getSavedInstanceState() {
        return this.savedInstanceState;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0273, code lost:
        if (r0 != null) goto L_0x027f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0346  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VastVideoViewControllerTwo(android.app.Activity r8, android.os.Bundle r9, android.os.Bundle r10, long r11, com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener r13) {
        /*
            r7 = this;
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.lang.String r0 = "extras"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            java.lang.String r0 = "baseListener"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r0)
            r0 = r8
            android.content.Context r0 = (android.content.Context) r0
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            r7.<init>(r0, r11, r13)
            r7.activity = r8
            r7.extras = r9
            r7.savedInstanceState = r10
            androidx.media2.player.MediaPlayer r8 = new androidx.media2.player.MediaPlayer
            android.content.Context r9 = r7.getContext()
            r8.<init>(r9)
            r7.mediaPlayer = r8
            r8 = -1
            r7.seekerPositionOnPause = r8
            com.mopub.common.ExternalViewabilitySessionManager r9 = new com.mopub.common.ExternalViewabilitySessionManager
            android.app.Activity r10 = r7.getActivity()
            android.content.Context r10 = (android.content.Context) r10
            r9.<init>(r10)
            r7.externalViewabilitySessionManager = r9
            android.media.MediaMetadataRetriever r9 = new android.media.MediaMetadataRetriever
            r9.<init>()
            r7.mediaMetadataRetriever = r9
            r9 = 5000(0x1388, float:7.006E-42)
            r7.showCloseButtonDelay = r9
            android.os.Bundle r9 = r7.getSavedInstanceState()
            r10 = 0
            if (r9 == 0) goto L_0x0053
            java.lang.String r11 = "resumed_vast_config"
            java.io.Serializable r9 = r9.getSerializable(r11)
            goto L_0x0054
        L_0x0053:
            r9 = r10
        L_0x0054:
            boolean r11 = r9 instanceof com.mopub.mobileads.VastVideoConfigTwo
            if (r11 != 0) goto L_0x0059
            r9 = r10
        L_0x0059:
            com.mopub.mobileads.VastVideoConfigTwo r9 = (com.mopub.mobileads.VastVideoConfigTwo) r9
            if (r9 == 0) goto L_0x005f
            r11 = r9
            goto L_0x0072
        L_0x005f:
            android.os.Bundle r11 = r7.getExtras()
            java.lang.String r12 = "vast_video_config"
            java.io.Serializable r11 = r11.getSerializable(r12)
            boolean r12 = r11 instanceof com.mopub.mobileads.VastVideoConfigTwo
            if (r12 != 0) goto L_0x006e
            r11 = r10
        L_0x006e:
            com.mopub.mobileads.VastVideoConfigTwo r11 = (com.mopub.mobileads.VastVideoConfigTwo) r11
            if (r11 == 0) goto L_0x0354
        L_0x0072:
            r7.vastVideoConfig = r11
            if (r9 == 0) goto L_0x008f
            android.os.Bundle r9 = r7.getSavedInstanceState()
            if (r9 == 0) goto L_0x0087
            java.lang.String r11 = "current_position"
            int r9 = r9.getInt(r11, r8)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            goto L_0x0088
        L_0x0087:
            r9 = r10
        L_0x0088:
            if (r9 == 0) goto L_0x008f
            int r9 = r9.intValue()
            goto L_0x0090
        L_0x008f:
            r9 = -1
        L_0x0090:
            r7.seekerPositionOnPause = r9
            com.mopub.mobileads.VastVideoConfigTwo r9 = r7.getVastVideoConfig()
            java.lang.String r9 = r9.getDiskMediaFileUrl()
            if (r9 == 0) goto L_0x0346
            com.mopub.mobileads.VastVideoConfigTwo r9 = r7.getVastVideoConfig()
            android.app.Activity r11 = r7.getActivity()
            android.content.res.Resources r11 = r11.getResources()
            java.lang.String r12 = "activity.resources"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r12)
            android.content.res.Configuration r11 = r11.getConfiguration()
            int r11 = r11.orientation
            com.mopub.mobileads.VastCompanionAdConfigTwo r9 = r9.getVastCompanionAd(r11)
            r7.vastCompanionAdConfig = r9
            com.mopub.mobileads.VastVideoConfigTwo r9 = r7.getVastVideoConfig()
            com.mopub.mobileads.VastIconConfigTwo r9 = r9.getVastIconConfig()
            r7.vastIconConfig = r9
            com.mopub.mobileads.VastVideoConfigTwo r9 = r7.getVastVideoConfig()
            boolean r9 = r9.getEnableClickExperiment()
            r7.isInClickExperiment = r9
            com.mopub.mobileads.VastVideoViewControllerTwo$4 r9 = new com.mopub.mobileads.VastVideoViewControllerTwo$4
            r9.<init>(r7)
            android.view.View$OnTouchListener r9 = (android.view.View.OnTouchListener) r9
            r7.clickThroughListener = r9
            android.view.ViewGroup r9 = r7.getLayout()
            r11 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r9.setBackgroundColor(r11)
            android.widget.ImageView r9 = new android.widget.ImageView
            android.content.Context r11 = r7.getContext()
            r9.<init>(r11)
            r11 = 4
            r9.setVisibility(r11)
            android.widget.RelativeLayout$LayoutParams r12 = new android.widget.RelativeLayout$LayoutParams
            r12.<init>(r8, r8)
            android.view.ViewGroup r8 = r7.getLayout()
            r13 = r9
            android.view.View r13 = (android.view.View) r13
            android.view.ViewGroup$LayoutParams r12 = (android.view.ViewGroup.LayoutParams) r12
            r8.addView(r13, r12)
            android.view.View$OnTouchListener r8 = r7.clickThroughListener
            r9.setOnTouchListener(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            r7.blurredLastVideoFrameImageView = r9
            android.app.Activity r8 = r7.getActivity()
            android.content.Context r8 = (android.content.Context) r8
            r9 = 0
            androidx.media2.widget.VideoView r8 = r7.createVideoView(r8, r9)
            r7.videoView = r8
            r8.requestFocus()
            com.mopub.common.ExternalViewabilitySessionManager r8 = r7.externalViewabilitySessionManager
            android.app.Activity r12 = r7.getActivity()
            androidx.media2.widget.VideoView r13 = r7.videoView
            android.view.View r13 = (android.view.View) r13
            com.mopub.mobileads.VastVideoConfigTwo r0 = r7.getVastVideoConfig()
            r8.createVideoSession((android.app.Activity) r12, (android.view.View) r13, (com.mopub.mobileads.VastVideoConfigTwo) r0)
            com.mopub.common.ExternalViewabilitySessionManager r8 = r7.externalViewabilitySessionManager
            android.widget.ImageView r12 = r7.getBlurredLastVideoFrameImageView()
            android.view.View r12 = (android.view.View) r12
            r8.registerVideoObstruction(r12)
            com.mopub.mobileads.VastVideoConfigTwo r8 = r7.getVastVideoConfig()
            r12 = 2
            android.view.View r8 = r7.createCompanionAdView(r8, r12, r11)
            r7.landscapeCompanionAdView = r8
            com.mopub.mobileads.VastVideoConfigTwo r8 = r7.getVastVideoConfig()
            r12 = 1
            android.view.View r8 = r7.createCompanionAdView(r8, r12, r11)
            r7.portraitCompanionAdView = r8
            com.mopub.mobileads.VastCompanionAdConfigTwo r8 = r7.vastCompanionAdConfig
            if (r8 == 0) goto L_0x014e
            r8 = 1
            goto L_0x014f
        L_0x014e:
            r8 = 0
        L_0x014f:
            com.mopub.mobileads.VastVideoGradientStripWidget r13 = new com.mopub.mobileads.VastVideoGradientStripWidget
            android.content.Context r1 = r7.getContext()
            android.graphics.drawable.GradientDrawable$Orientation r2 = android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM
            r4 = 0
            r5 = 6
            android.view.ViewGroup r0 = r7.getLayout()
            java.lang.String r3 = "layout"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3)
            int r6 = r0.getId()
            r0 = r13
            r3 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            android.view.ViewGroup r0 = r7.getLayout()
            r1 = r13
            android.view.View r1 = (android.view.View) r1
            r0.addView(r1)
            com.mopub.common.ExternalViewabilitySessionManager r0 = r7.externalViewabilitySessionManager
            r0.registerVideoObstruction(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            r7.setTopGradientStripWidget(r13)
            com.mopub.mobileads.VastVideoProgressBarWidget r13 = new com.mopub.mobileads.VastVideoProgressBarWidget
            android.content.Context r0 = r7.getContext()
            r13.<init>(r0)
            androidx.media2.widget.VideoView r0 = r7.videoView
            int r0 = r0.getId()
            r13.setAnchorId(r0)
            r13.setVisibility(r11)
            android.view.ViewGroup r0 = r7.getLayout()
            r1 = r13
            android.view.View r1 = (android.view.View) r1
            r0.addView(r1)
            com.mopub.common.ExternalViewabilitySessionManager r0 = r7.externalViewabilitySessionManager
            r0.registerVideoObstruction(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            r7.setProgressBarWidget(r13)
            com.mopub.mobileads.VastVideoGradientStripWidget r13 = new com.mopub.mobileads.VastVideoGradientStripWidget
            android.content.Context r1 = r7.getContext()
            android.graphics.drawable.GradientDrawable$Orientation r2 = android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP
            r4 = 8
            r5 = 2
            com.mopub.mobileads.VastVideoProgressBarWidget r0 = r7.getProgressBarWidget()
            int r6 = r0.getId()
            r0 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6)
            android.view.ViewGroup r0 = r7.getLayout()
            r1 = r13
            android.view.View r1 = (android.view.View) r1
            r0.addView(r1)
            com.mopub.common.ExternalViewabilitySessionManager r0 = r7.externalViewabilitySessionManager
            r0.registerVideoObstruction(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            r7.setBottomGradientStripWidget(r13)
            com.mopub.mobileads.VastVideoRadialCountdownWidget r13 = new com.mopub.mobileads.VastVideoRadialCountdownWidget
            android.content.Context r0 = r7.getContext()
            r13.<init>(r0)
            r13.setVisibility(r11)
            android.view.ViewGroup r0 = r7.getLayout()
            r1 = r13
            android.view.View r1 = (android.view.View) r1
            r0.addView(r1)
            com.mopub.common.ExternalViewabilitySessionManager r0 = r7.externalViewabilitySessionManager
            r0.registerVideoObstruction(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            r7.setRadialCountdownWidget(r13)
            com.mopub.mobileads.VastIconConfigTwo r13 = r7.getVastIconConfig()
            if (r13 == 0) goto L_0x0276
            android.content.Context r0 = r7.getContext()
            com.mopub.mobileads.VastResourceTwo r1 = r13.getVastResource()
            com.mopub.mobileads.VastWebView r0 = com.mopub.mobileads.VastWebView.createView((android.content.Context) r0, (com.mopub.mobileads.VastResourceTwo) r1)
            java.lang.String r1 = "it"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            com.mopub.mobileads.VastVideoViewControllerTwo$$special$$inlined$let$lambda$1 r1 = new com.mopub.mobileads.VastVideoViewControllerTwo$$special$$inlined$let$lambda$1
            r1.<init>(r13, r7)
            com.mopub.mobileads.VastWebView$VastWebViewClickListener r1 = (com.mopub.mobileads.VastWebView.VastWebViewClickListener) r1
            r0.setVastWebViewClickListener(r1)
            com.mopub.mobileads.VastVideoViewControllerTwo$$special$$inlined$let$lambda$2 r1 = new com.mopub.mobileads.VastVideoViewControllerTwo$$special$$inlined$let$lambda$2
            r1.<init>(r13, r7)
            android.webkit.WebViewClient r1 = (android.webkit.WebViewClient) r1
            r0.setWebViewClient(r1)
            r0.setVisibility(r11)
            com.mopub.mobileads.VastIconConfigTwo r11 = r7.getVastIconConfig()
            if (r11 == 0) goto L_0x0246
            android.widget.RelativeLayout$LayoutParams r10 = new android.widget.RelativeLayout$LayoutParams
            int r11 = r13.getWidth()
            float r11 = (float) r11
            android.content.Context r1 = r7.getContext()
            int r11 = com.mopub.common.util.Dips.asIntPixels(r11, r1)
            int r13 = r13.getHeight()
            float r13 = (float) r13
            android.content.Context r1 = r7.getContext()
            int r13 = com.mopub.common.util.Dips.asIntPixels(r13, r1)
            r10.<init>(r11, r13)
        L_0x0246:
            r11 = 12
            float r11 = (float) r11
            android.content.Context r13 = r7.getContext()
            int r13 = com.mopub.common.util.Dips.dipsToIntPixels(r11, r13)
            android.content.Context r1 = r7.getContext()
            int r11 = com.mopub.common.util.Dips.dipsToIntPixels(r11, r1)
            if (r10 == 0) goto L_0x0260
            r10.setMargins(r13, r11, r9, r9)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
        L_0x0260:
            android.view.ViewGroup r11 = r7.getLayout()
            r13 = r0
            android.view.View r13 = (android.view.View) r13
            android.view.ViewGroup$LayoutParams r10 = (android.view.ViewGroup.LayoutParams) r10
            r11.addView(r13, r10)
            com.mopub.common.ExternalViewabilitySessionManager r10 = r7.externalViewabilitySessionManager
            r10.registerVideoObstruction(r13)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            if (r0 == 0) goto L_0x0276
            goto L_0x027f
        L_0x0276:
            android.view.View r13 = new android.view.View
            android.content.Context r10 = r7.getContext()
            r13.<init>(r10)
        L_0x027f:
            r7.iconView = r13
            android.content.Context r10 = r7.getContext()
            androidx.media2.widget.VideoView r11 = r7.videoView
            int r11 = r11.getId()
            com.mopub.mobileads.VastVideoConfigTwo r13 = r7.getVastVideoConfig()
            java.lang.String r13 = r13.getClickThroughUrl()
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            if (r13 == 0) goto L_0x029d
            int r13 = r13.length()
            if (r13 != 0) goto L_0x029e
        L_0x029d:
            r9 = 1
        L_0x029e:
            r9 = r9 ^ r12
            com.mopub.mobileads.VastVideoCtaButtonWidget r12 = new com.mopub.mobileads.VastVideoCtaButtonWidget
            r12.<init>(r10, r11, r8, r9)
            android.view.ViewGroup r8 = r7.getLayout()
            r9 = r12
            android.view.View r9 = (android.view.View) r9
            r8.addView(r9)
            com.mopub.common.ExternalViewabilitySessionManager r8 = r7.externalViewabilitySessionManager
            r8.registerVideoObstruction(r9)
            com.mopub.mobileads.VastVideoConfigTwo r8 = r7.getVastVideoConfig()
            java.lang.String r8 = r8.getCustomCtaText()
            if (r8 == 0) goto L_0x02c2
            r12.updateCtaText(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
        L_0x02c2:
            android.view.View$OnTouchListener r8 = r7.clickThroughListener
            r12.setOnTouchListener(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            r7.ctaButtonWidget = r12
            com.mopub.mobileads.VastVideoCloseButtonWidget r8 = new com.mopub.mobileads.VastVideoCloseButtonWidget
            android.content.Context r9 = r7.getContext()
            r8.<init>(r9)
            r9 = 8
            r8.setVisibility(r9)
            android.view.ViewGroup r9 = r7.getLayout()
            r10 = r8
            android.view.View r10 = (android.view.View) r10
            r9.addView(r10)
            com.mopub.common.ExternalViewabilitySessionManager r9 = r7.externalViewabilitySessionManager
            r9.registerVideoObstruction(r10)
            com.mopub.mobileads.VastVideoViewControllerTwo$$special$$inlined$also$lambda$3 r9 = new com.mopub.mobileads.VastVideoViewControllerTwo$$special$$inlined$also$lambda$3
            r9.<init>(r7)
            android.view.View$OnTouchListener r9 = (android.view.View.OnTouchListener) r9
            r8.setOnTouchListenerToContent(r9)
            com.mopub.mobileads.VastVideoConfigTwo r9 = r7.getVastVideoConfig()
            java.lang.String r9 = r9.getCustomSkipText()
            if (r9 == 0) goto L_0x0301
            r8.updateCloseButtonText(r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
        L_0x0301:
            com.mopub.mobileads.VastVideoConfigTwo r9 = r7.getVastVideoConfig()
            java.lang.String r9 = r9.getCustomCloseIconUrl()
            if (r9 == 0) goto L_0x0310
            r8.updateCloseButtonIcon(r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
        L_0x0310:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            r7.setCloseButtonWidget(r8)
            boolean r8 = r7.isInClickExperiment
            if (r8 == 0) goto L_0x032a
            com.mopub.mobileads.VastVideoConfigTwo r8 = r7.getVastVideoConfig()
            boolean r8 = r8.isRewarded()
            if (r8 != 0) goto L_0x032a
            com.mopub.mobileads.VastVideoCtaButtonWidget r8 = r7.getCtaButtonWidget()
            r8.notifyVideoSkippable()
        L_0x032a:
            android.os.Handler r8 = new android.os.Handler
            android.os.Looper r9 = android.os.Looper.getMainLooper()
            r8.<init>(r9)
            com.mopub.mobileads.VastVideoViewProgressRunnableTwo r9 = new com.mopub.mobileads.VastVideoViewProgressRunnableTwo
            com.mopub.mobileads.VastVideoConfigTwo r10 = r7.getVastVideoConfig()
            r9.<init>(r7, r10, r8)
            r7.progressCheckerRunnable = r9
            com.mopub.mobileads.VastVideoViewCountdownRunnableTwo r9 = new com.mopub.mobileads.VastVideoViewCountdownRunnableTwo
            r9.<init>(r7, r8)
            r7.countdownRunnable = r9
            return
        L_0x0346:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "VastVideoConfigTwo does not have a video disk path"
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        L_0x0354:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "VastVideoConfigTwo is invalid"
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VastVideoViewControllerTwo.<init>(android.app.Activity, android.os.Bundle, android.os.Bundle, long, com.mopub.mobileads.BaseVideoViewController$BaseVideoViewControllerListener):void");
    }

    /* compiled from: VastVideoViewControllerTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public VastVideoConfigTwo getVastVideoConfig() {
        return this.vastVideoConfig;
    }

    public VastIconConfigTwo getVastIconConfig() {
        return this.vastIconConfig;
    }

    public ImageView getBlurredLastVideoFrameImageView() {
        return this.blurredLastVideoFrameImageView;
    }

    public View getLandscapeCompanionAdView() {
        return this.landscapeCompanionAdView;
    }

    public View getPortraitCompanionAdView() {
        return this.portraitCompanionAdView;
    }

    public View getIconView() {
        return this.iconView;
    }

    public VastVideoGradientStripWidget getTopGradientStripWidget() {
        VastVideoGradientStripWidget vastVideoGradientStripWidget = this.topGradientStripWidget;
        if (vastVideoGradientStripWidget == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topGradientStripWidget");
        }
        return vastVideoGradientStripWidget;
    }

    public void setTopGradientStripWidget(VastVideoGradientStripWidget vastVideoGradientStripWidget) {
        Intrinsics.checkParameterIsNotNull(vastVideoGradientStripWidget, "<set-?>");
        this.topGradientStripWidget = vastVideoGradientStripWidget;
    }

    public VastVideoGradientStripWidget getBottomGradientStripWidget() {
        VastVideoGradientStripWidget vastVideoGradientStripWidget = this.bottomGradientStripWidget;
        if (vastVideoGradientStripWidget == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomGradientStripWidget");
        }
        return vastVideoGradientStripWidget;
    }

    public void setBottomGradientStripWidget(VastVideoGradientStripWidget vastVideoGradientStripWidget) {
        Intrinsics.checkParameterIsNotNull(vastVideoGradientStripWidget, "<set-?>");
        this.bottomGradientStripWidget = vastVideoGradientStripWidget;
    }

    public VastVideoProgressBarWidget getProgressBarWidget() {
        VastVideoProgressBarWidget vastVideoProgressBarWidget = this.progressBarWidget;
        if (vastVideoProgressBarWidget == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBarWidget");
        }
        return vastVideoProgressBarWidget;
    }

    public void setProgressBarWidget(VastVideoProgressBarWidget vastVideoProgressBarWidget) {
        Intrinsics.checkParameterIsNotNull(vastVideoProgressBarWidget, "<set-?>");
        this.progressBarWidget = vastVideoProgressBarWidget;
    }

    public VastVideoRadialCountdownWidget getRadialCountdownWidget() {
        VastVideoRadialCountdownWidget vastVideoRadialCountdownWidget = this.radialCountdownWidget;
        if (vastVideoRadialCountdownWidget == null) {
            Intrinsics.throwUninitializedPropertyAccessException("radialCountdownWidget");
        }
        return vastVideoRadialCountdownWidget;
    }

    public void setRadialCountdownWidget(VastVideoRadialCountdownWidget vastVideoRadialCountdownWidget) {
        Intrinsics.checkParameterIsNotNull(vastVideoRadialCountdownWidget, "<set-?>");
        this.radialCountdownWidget = vastVideoRadialCountdownWidget;
    }

    public VastVideoCtaButtonWidget getCtaButtonWidget() {
        return this.ctaButtonWidget;
    }

    public VastVideoCloseButtonWidget getCloseButtonWidget() {
        VastVideoCloseButtonWidget vastVideoCloseButtonWidget = this.closeButtonWidget;
        if (vastVideoCloseButtonWidget == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButtonWidget");
        }
        return vastVideoCloseButtonWidget;
    }

    public void setCloseButtonWidget(VastVideoCloseButtonWidget vastVideoCloseButtonWidget) {
        Intrinsics.checkParameterIsNotNull(vastVideoCloseButtonWidget, "<set-?>");
        this.closeButtonWidget = vastVideoCloseButtonWidget;
    }

    public VastVideoBlurLastVideoFrameTask getBlurLastVideoFrameTask() {
        return this.blurLastVideoFrameTask;
    }

    public void setBlurLastVideoFrameTask(VastVideoBlurLastVideoFrameTask vastVideoBlurLastVideoFrameTask) {
        this.blurLastVideoFrameTask = vastVideoBlurLastVideoFrameTask;
    }

    public MediaMetadataRetriever getMediaMetadataRetriever() {
        return this.mediaMetadataRetriever;
    }

    public void setMediaMetadataRetriever(MediaMetadataRetriever mediaMetadataRetriever2) {
        this.mediaMetadataRetriever = mediaMetadataRetriever2;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public void setComplete(boolean z) {
        this.isComplete = z;
    }

    public boolean getShouldAllowClose() {
        return this.shouldAllowClose;
    }

    public void setShouldAllowClose(boolean z) {
        this.shouldAllowClose = z;
    }

    public int getShowCloseButtonDelay() {
        return this.showCloseButtonDelay;
    }

    public void setShowCloseButtonDelay(int i) {
        this.showCloseButtonDelay = i;
    }

    public boolean isCalibrationDone() {
        return this.isCalibrationDone;
    }

    public void setCalibrationDone(boolean z) {
        this.isCalibrationDone = z;
    }

    public boolean isClosing() {
        return this.isClosing;
    }

    public void setClosing(boolean z) {
        this.isClosing = z;
    }

    public boolean getVideoError() {
        return this.videoError;
    }

    public void setVideoError(boolean z) {
        this.videoError = z;
    }

    public String getNetworkMediaFileUrl() {
        return getVastVideoConfig().getNetworkMediaFileUrl();
    }

    /* access modifiers changed from: private */
    public void adjustSkipOffset() {
        int duration = getDuration();
        if (getVastVideoConfig().isRewarded()) {
            setShowCloseButtonDelay(duration);
            return;
        }
        if (duration < 16000) {
            setShowCloseButtonDelay(duration);
        }
        try {
            Integer skipOffsetMillis = getVastVideoConfig().getSkipOffsetMillis(duration);
            if (skipOffsetMillis != null) {
                setShowCloseButtonDelay(skipOffsetMillis.intValue());
            }
        } catch (NumberFormatException unused) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Failed to parse skipoffset " + getVastVideoConfig().getSkipOffset());
        }
    }

    private VideoView createVideoView(Context context, int i) {
        VideoView videoView2 = new VideoView(context);
        Executor mainExecutor = ContextCompat.getMainExecutor(context);
        PlaybackParams build = new PlaybackParams.Builder().setAudioFallbackMode(0).setSpeed(1.0f).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "PlaybackParams.Builder()….0f)\n            .build()");
        getMediaPlayer().setPlaybackParams(build);
        getMediaPlayer().setAudioAttributes(new AudioAttributesCompat.Builder().setUsage(1).setContentType(3).build());
        getMediaPlayer().registerPlayerCallback(mainExecutor, new PlayerCallback());
        videoView2.removeView(videoView2.getMediaControlView());
        videoView2.setId(View.generateViewId());
        videoView2.setPlayer(getMediaPlayer());
        videoView2.setOnTouchListener(this.clickThroughListener);
        MediaPlayer mediaPlayer2 = getMediaPlayer();
        mediaPlayer2.setMediaItem(new UriMediaItem.Builder(Uri.parse(getVastVideoConfig().getDiskMediaFileUrl())).build());
        mediaPlayer2.prepare().addListener(new VastVideoViewControllerTwo$createVideoView$$inlined$run$lambda$1(mediaPlayer2, this, mainExecutor), mainExecutor);
        return videoView2;
    }

    public View createCompanionAdView(VastVideoConfigTwo vastVideoConfigTwo, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(vastVideoConfigTwo, "$this$createCompanionAdView");
        VastCompanionAdConfigTwo vastCompanionAd = vastVideoConfigTwo.getVastCompanionAd(i);
        if (vastCompanionAd != null) {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            relativeLayout.setGravity(17);
            View view = relativeLayout;
            getLayout().addView(view, layoutParams);
            this.externalViewabilitySessionManager.registerVideoObstruction(view);
            VastWebView createWebView = createWebView(vastCompanionAd);
            createWebView.setVisibility(i2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(Dips.dipsToIntPixels((float) (vastCompanionAd.getWidth() + 16), getContext()), Dips.dipsToIntPixels((float) (vastCompanionAd.getHeight() + 16), getContext()));
            layoutParams2.addRule(13, -1);
            View view2 = createWebView;
            relativeLayout.addView(view2, layoutParams2);
            this.externalViewabilitySessionManager.registerVideoObstruction(view2);
            if (createWebView != null) {
                return view2;
            }
        }
        View view3 = new View(getContext());
        view3.setVisibility(4);
        return view3;
    }

    private VastWebView createWebView(VastCompanionAdConfigTwo vastCompanionAdConfigTwo) {
        VastWebView createView = VastWebView.createView(getContext(), vastCompanionAdConfigTwo.getVastResource());
        Intrinsics.checkExpressionValueIsNotNull(createView, "it");
        createView.setVastWebViewClickListener(new VastVideoViewControllerTwo$createWebView$$inlined$also$lambda$1(this, vastCompanionAdConfigTwo));
        createView.setWebViewClient(new VastVideoViewControllerTwo$createWebView$$inlined$also$lambda$2(this, vastCompanionAdConfigTwo));
        Intrinsics.checkExpressionValueIsNotNull(createView, "VastWebView.createView(c…}\n            }\n        }");
        return createView;
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        super.onCreate();
        VastVideoConfigTwo vastVideoConfig2 = getVastVideoConfig();
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        vastVideoConfig2.handleImpression(context, getDuration());
        broadcastAction(IntentActions.ACTION_INTERSTITIAL_SHOW);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        startRunnables();
        if (this.seekerPositionOnPause > 0) {
            this.externalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_PLAYING, this.seekerPositionOnPause);
            Intrinsics.checkExpressionValueIsNotNull(getMediaPlayer().seekTo((long) this.seekerPositionOnPause, 3), "mediaPlayer.seekTo(seeke…MediaPlayer.SEEK_CLOSEST)");
        } else {
            this.externalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_LOADED, getDuration());
            if (!isComplete()) {
                getMediaPlayer().play();
            }
        }
        if (this.seekerPositionOnPause != -1) {
            VastVideoConfigTwo vastVideoConfig2 = getVastVideoConfig();
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            vastVideoConfig2.handleResume(context, this.seekerPositionOnPause);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        stopRunnables();
        this.seekerPositionOnPause = getCurrentPosition();
        getMediaPlayer().pause();
        if (!isClosing()) {
            this.externalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_PAUSED, getCurrentPosition());
            VastVideoConfigTwo vastVideoConfig2 = getVastVideoConfig();
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            vastVideoConfig2.handlePause(context, this.seekerPositionOnPause);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        stopRunnables();
        this.externalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_STOPPED, getCurrentPosition());
        this.externalViewabilitySessionManager.endVideoSession();
        broadcastAction(IntentActions.ACTION_INTERSTITIAL_DISMISS);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "outState");
        bundle.putInt(CURRENT_POSITION, this.seekerPositionOnPause);
        bundle.putSerializable(RESUMED_VAST_CONFIG, getVastVideoConfig());
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "newConfig");
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        int i = resources.getConfiguration().orientation;
        if (getLandscapeCompanionAdView().getVisibility() == 0 || getPortraitCompanionAdView().getVisibility() == 0) {
            if (i == 1) {
                getLandscapeCompanionAdView().setVisibility(4);
                getPortraitCompanionAdView().setVisibility(0);
            } else {
                getLandscapeCompanionAdView().setVisibility(0);
                getPortraitCompanionAdView().setVisibility(4);
            }
        }
        VastCompanionAdConfigTwo vastCompanionAd = getVastVideoConfig().getVastCompanionAd(i);
        if (vastCompanionAd != null) {
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            vastCompanionAd.handleImpression(context2, getDuration());
        } else {
            vastCompanionAd = null;
        }
        this.vastCompanionAdConfig = vastCompanionAd;
    }

    /* access modifiers changed from: protected */
    public View getVideoView() {
        return this.videoView;
    }

    /* access modifiers changed from: protected */
    public void onBackPressed() {
        handleExitTrackers();
    }

    public boolean backButtonEnabled() {
        return this.shouldAllowSkip || getShouldAllowClose();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (isClosing() && i == 1 && i2 == -1) {
            getBaseVideoViewControllerListener().onFinish();
        }
    }

    public void handleViewabilityQuartileEvent$mopub_sdk_base_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "enumValue");
        ExternalViewabilitySession.VideoEvent valueOf = ExternalViewabilitySession.VideoEvent.valueOf(str);
        if (valueOf != null) {
            this.externalViewabilitySessionManager.recordVideoEvent(valueOf, getCurrentPosition());
        }
    }

    public int getDuration() {
        return (int) getMediaPlayer().getDuration();
    }

    public int getCurrentPosition() {
        return (int) getMediaPlayer().getCurrentPosition();
    }

    public static /* synthetic */ void updateCountdown$mopub_sdk_base_release$default(VastVideoViewControllerTwo vastVideoViewControllerTwo, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = false;
            }
            vastVideoViewControllerTwo.updateCountdown$mopub_sdk_base_release(z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateCountdown");
    }

    public void updateCountdown$mopub_sdk_base_release(boolean z) {
        if (isCalibrationDone()) {
            getRadialCountdownWidget().updateCountdownProgress(getShowCloseButtonDelay(), getCurrentPosition());
        }
        if (z || getCurrentPosition() >= getShowCloseButtonDelay()) {
            getRadialCountdownWidget().setVisibility(8);
            getCloseButtonWidget().setVisibility(0);
            setShouldAllowClose(true);
            if (this.isInClickExperiment || !getVastVideoConfig().isRewarded()) {
                getCtaButtonWidget().notifyVideoSkippable();
            }
        }
    }

    public void updateProgressBar() {
        getProgressBarWidget().updateProgress(getCurrentPosition());
    }

    public void handleIconDisplay(int i) {
        Integer durationMS;
        VastIconConfigTwo vastIconConfig2;
        VastIconConfigTwo vastIconConfig3 = getVastIconConfig();
        if (vastIconConfig3 != null) {
            int offsetMS = vastIconConfig3.getOffsetMS();
            getIconView().setVisibility(0);
            String networkMediaFileUrl = getNetworkMediaFileUrl();
            if (!(networkMediaFileUrl == null || (vastIconConfig2 = getVastIconConfig()) == null)) {
                Context context = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                vastIconConfig2.handleImpression(context, i, networkMediaFileUrl);
            }
            VastIconConfigTwo vastIconConfig4 = getVastIconConfig();
            if (vastIconConfig4 != null && (durationMS = vastIconConfig4.getDurationMS()) != null && i >= offsetMS + durationMS.intValue()) {
                getIconView().setVisibility(8);
            }
        }
    }

    public void prepareBlurredLastVideoFrame(ImageView imageView, String str) {
        Intrinsics.checkParameterIsNotNull(imageView, "blurredLastVideoFrameImageView");
        Intrinsics.checkParameterIsNotNull(str, "diskMediaFileUrl");
        MediaMetadataRetriever mediaMetadataRetriever2 = getMediaMetadataRetriever();
        if (mediaMetadataRetriever2 != null) {
            VastVideoBlurLastVideoFrameTask vastVideoBlurLastVideoFrameTask = new VastVideoBlurLastVideoFrameTask(mediaMetadataRetriever2, imageView, getDuration());
            AsyncTasks.safeExecuteOnExecutor(vastVideoBlurLastVideoFrameTask, str);
            setBlurLastVideoFrameTask(vastVideoBlurLastVideoFrameTask);
        }
    }

    /* access modifiers changed from: private */
    public void handleExitTrackers() {
        int currentPosition = getCurrentPosition();
        if (isComplete()) {
            this.externalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_COMPLETE, getDuration());
            VastVideoConfigTwo vastVideoConfig2 = getVastVideoConfig();
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            vastVideoConfig2.handleComplete(context, getDuration());
        } else if (this.shouldAllowSkip) {
            this.externalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_COMPLETE, currentPosition);
            VastVideoConfigTwo vastVideoConfig3 = getVastVideoConfig();
            Context context2 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
            vastVideoConfig3.handleSkip(context2, currentPosition);
        }
        VastVideoConfigTwo vastVideoConfig4 = getVastVideoConfig();
        Context context3 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context3, "context");
        vastVideoConfig4.handleClose(context3, getDuration());
    }

    private void startRunnables() {
        this.progressCheckerRunnable.startRepeating(VIDEO_PROGRESS_TIMER_CHECKER_DELAY);
        this.countdownRunnable.startRepeating(VIDEO_COUNTDOWN_UPDATE_INTERVAL);
    }

    /* access modifiers changed from: private */
    public void stopRunnables() {
        this.progressCheckerRunnable.stop();
        this.countdownRunnable.stop();
    }

    /* compiled from: VastVideoViewControllerTwo.kt */
    public final class PlayerCallback extends MediaPlayer.PlayerCallback {
        private boolean complete;

        private final String playerStateToString(int i) {
            return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : "PLAYER_STATE_ERROR" : "PLAYER_STATE_PLAYING" : "PLAYER_STATE_PAUSED" : "PLAYER_STATE_IDLE";
        }

        public PlayerCallback() {
        }

        public final boolean getComplete() {
            return this.complete;
        }

        public final void setComplete(boolean z) {
            this.complete = z;
        }

        public void onPlayerStateChanged(SessionPlayer sessionPlayer, int i) {
            Intrinsics.checkParameterIsNotNull(sessionPlayer, "player");
            super.onPlayerStateChanged(sessionPlayer, i);
            if (i != 3) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Player state changed to " + playerStateToString(i));
                return;
            }
            VastVideoViewControllerTwo.this.externalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.RECORD_AD_ERROR, VastVideoViewControllerTwo.this.getCurrentPosition());
            VastVideoViewControllerTwo.this.stopRunnables();
            VastVideoViewControllerTwo.this.updateCountdown$mopub_sdk_base_release(true);
            VastVideoViewControllerTwo.this.videoError(false);
            VastVideoViewControllerTwo.this.setVideoError(true);
            VastVideoConfigTwo vastVideoConfig = VastVideoViewControllerTwo.this.getVastVideoConfig();
            Context context = VastVideoViewControllerTwo.this.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            vastVideoConfig.handleError(context, VastErrorCode.GENERAL_LINEAR_AD_ERROR, VastVideoViewControllerTwo.this.getCurrentPosition());
        }

        public void onPlaybackCompleted(SessionPlayer sessionPlayer) {
            Intrinsics.checkParameterIsNotNull(sessionPlayer, "player");
            VastVideoViewControllerTwo.this.stopRunnables();
            VastVideoViewControllerTwo.updateCountdown$mopub_sdk_base_release$default(VastVideoViewControllerTwo.this, false, 1, (Object) null);
            VastVideoViewControllerTwo.this.setComplete(true);
            VastVideoViewControllerTwo.this.videoCompleted(false);
            if (VastVideoViewControllerTwo.this.getVastVideoConfig().isRewarded()) {
                VastVideoViewControllerTwo.this.broadcastAction(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
            }
            if (VastVideoViewControllerTwo.this.getVideoError() && VastVideoViewControllerTwo.this.getVastVideoConfig().getRemainingProgressTrackerCount() == 0) {
                VastVideoViewControllerTwo.this.externalViewabilitySessionManager.recordVideoEvent(ExternalViewabilitySession.VideoEvent.AD_COMPLETE, VastVideoViewControllerTwo.this.getCurrentPosition());
                VastVideoConfigTwo vastVideoConfig = VastVideoViewControllerTwo.this.getVastVideoConfig();
                Context context = VastVideoViewControllerTwo.this.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                vastVideoConfig.handleComplete(context, VastVideoViewControllerTwo.this.getCurrentPosition());
            }
            VastVideoViewControllerTwo.this.videoView.setVisibility(4);
            VastVideoViewControllerTwo.this.getProgressBarWidget().setVisibility(8);
            VastVideoViewControllerTwo.this.getIconView().setVisibility(8);
            VastVideoViewControllerTwo.this.getTopGradientStripWidget().notifyVideoComplete();
            VastVideoViewControllerTwo.this.getBottomGradientStripWidget().notifyVideoComplete();
            VastVideoViewControllerTwo.this.getCtaButtonWidget().notifyVideoComplete();
            VastCompanionAdConfigTwo access$getVastCompanionAdConfig$p = VastVideoViewControllerTwo.this.vastCompanionAdConfig;
            if (access$getVastCompanionAdConfig$p != null) {
                Context context2 = VastVideoViewControllerTwo.this.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                Resources resources = context2.getResources();
                Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
                if (resources.getConfiguration().orientation == 1) {
                    VastVideoViewControllerTwo.this.getPortraitCompanionAdView().setVisibility(0);
                } else {
                    VastVideoViewControllerTwo.this.getLandscapeCompanionAdView().setVisibility(0);
                }
                Context context3 = VastVideoViewControllerTwo.this.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context3, "context");
                access$getVastCompanionAdConfig$p.handleImpression(context3, VastVideoViewControllerTwo.this.getDuration());
            } else if (VastVideoViewControllerTwo.this.getBlurredLastVideoFrameImageView().getDrawable() != null) {
                VastVideoViewControllerTwo.this.getBlurredLastVideoFrameImageView().setVisibility(0);
            }
        }

        public void onSeekCompleted(SessionPlayer sessionPlayer, long j) {
            Intrinsics.checkParameterIsNotNull(sessionPlayer, "player");
            VastVideoViewControllerTwo.this.getMediaPlayer().play();
        }
    }
}
