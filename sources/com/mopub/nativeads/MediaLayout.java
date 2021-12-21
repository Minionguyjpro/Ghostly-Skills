package com.mopub.nativeads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.mobileads.VastVideoProgressBarWidget;
import com.mopub.mobileads.resource.DrawableConstants;

public class MediaLayout extends RelativeLayout {
    private static final float ASPECT_MULTIPLIER_HEIGHT_TO_WIDTH = 1.7777778f;
    private static final float ASPECT_MULTIPLIER_WIDTH_TO_HEIGHT = 0.5625f;
    private static final int CONTROL_SIZE_DIPS = 40;
    private static final int GRADIENT_STRIP_HEIGHT_DIPS = 35;
    private static final int MUTE_SIZE_DIPS = 36;
    private static final int PINNER_PADDING_DIPS = 10;
    private ImageView mBottomGradient;
    private final int mControlSizePx;
    private final int mGradientStripHeightPx;
    private boolean mIsInitialized;
    private ProgressBar mLoadingSpinner;
    private ImageView mMainImageView;
    private volatile Mode mMode;
    private ImageView mMuteControl;
    private final int mMuteSizePx;
    private MuteState mMuteState;
    private Drawable mMutedDrawable;
    private View mOverlay;
    private final int mPaddingPx;
    private ImageView mPlayButton;
    private ImageView mTopGradient;
    private Drawable mUnmutedDrawable;
    private VastVideoProgressBarWidget mVideoProgress;
    private TextureView mVideoTextureView;

    public enum Mode {
        IMAGE,
        PLAYING,
        LOADING,
        BUFFERING,
        PAUSED,
        FINISHED
    }

    public enum MuteState {
        MUTED,
        UNMUTED
    }

    public MediaLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public MediaLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMode = Mode.IMAGE;
        Preconditions.checkNotNull(context);
        this.mMuteState = MuteState.MUTED;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        ImageView imageView = new ImageView(context);
        this.mMainImageView = imageView;
        imageView.setLayoutParams(layoutParams);
        this.mMainImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(this.mMainImageView);
        this.mControlSizePx = Dips.asIntPixels(40.0f, context);
        this.mGradientStripHeightPx = Dips.asIntPixels(35.0f, context);
        this.mMuteSizePx = Dips.asIntPixels(36.0f, context);
        this.mPaddingPx = Dips.asIntPixels(10.0f, context);
    }

    public void setSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        TextureView textureView = this.mVideoTextureView;
        if (textureView != null) {
            textureView.setSurfaceTextureListener(surfaceTextureListener);
            SurfaceTexture surfaceTexture = this.mVideoTextureView.getSurfaceTexture();
            if (surfaceTexture != null && surfaceTextureListener != null) {
                surfaceTextureListener.onSurfaceTextureAvailable(surfaceTexture, this.mVideoTextureView.getWidth(), this.mVideoTextureView.getHeight());
            }
        }
    }

    public void initForVideo() {
        if (!this.mIsInitialized) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            TextureView textureView = new TextureView(getContext());
            this.mVideoTextureView = textureView;
            textureView.setLayoutParams(layoutParams);
            this.mVideoTextureView.setId(View.generateViewId());
            addView(this.mVideoTextureView);
            this.mMainImageView.bringToFront();
            int i = this.mControlSizePx;
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i, i);
            layoutParams2.addRule(10);
            layoutParams2.addRule(11);
            ProgressBar progressBar = new ProgressBar(getContext());
            this.mLoadingSpinner = progressBar;
            progressBar.setLayoutParams(layoutParams2);
            ProgressBar progressBar2 = this.mLoadingSpinner;
            int i2 = this.mPaddingPx;
            progressBar2.setPadding(0, i2, i2, 0);
            this.mLoadingSpinner.setIndeterminate(true);
            addView(this.mLoadingSpinner);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, this.mGradientStripHeightPx);
            layoutParams3.addRule(8, this.mVideoTextureView.getId());
            ImageView imageView = new ImageView(getContext());
            this.mBottomGradient = imageView;
            imageView.setLayoutParams(layoutParams3);
            this.mBottomGradient.setImageDrawable(new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{DrawableConstants.GradientStrip.START_COLOR, DrawableConstants.GradientStrip.END_COLOR}));
            addView(this.mBottomGradient);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, this.mGradientStripHeightPx);
            layoutParams4.addRule(6, this.mVideoTextureView.getId());
            ImageView imageView2 = new ImageView(getContext());
            this.mTopGradient = imageView2;
            imageView2.setLayoutParams(layoutParams4);
            this.mTopGradient.setImageDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{DrawableConstants.GradientStrip.START_COLOR, DrawableConstants.GradientStrip.END_COLOR}));
            addView(this.mTopGradient);
            VastVideoProgressBarWidget vastVideoProgressBarWidget = new VastVideoProgressBarWidget(getContext());
            this.mVideoProgress = vastVideoProgressBarWidget;
            vastVideoProgressBarWidget.setAnchorId(this.mVideoTextureView.getId());
            this.mVideoProgress.calibrateAndMakeVisible(1000, 0);
            addView(this.mVideoProgress);
            this.mMutedDrawable = Drawables.NATIVE_MUTED.createDrawable(getContext());
            this.mUnmutedDrawable = Drawables.NATIVE_UNMUTED.createDrawable(getContext());
            int i3 = this.mMuteSizePx;
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(i3, i3);
            layoutParams5.addRule(9);
            layoutParams5.addRule(2, this.mVideoProgress.getId());
            ImageView imageView3 = new ImageView(getContext());
            this.mMuteControl = imageView3;
            imageView3.setLayoutParams(layoutParams5);
            this.mMuteControl.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            ImageView imageView4 = this.mMuteControl;
            int i4 = this.mPaddingPx;
            imageView4.setPadding(i4, i4, i4, i4);
            this.mMuteControl.setImageDrawable(this.mMutedDrawable);
            addView(this.mMuteControl);
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams6.addRule(13);
            View view = new View(getContext());
            this.mOverlay = view;
            view.setLayoutParams(layoutParams6);
            this.mOverlay.setBackgroundColor(0);
            addView(this.mOverlay);
            int i5 = this.mControlSizePx;
            RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(i5, i5);
            layoutParams7.addRule(13);
            ImageView imageView5 = new ImageView(getContext());
            this.mPlayButton = imageView5;
            imageView5.setLayoutParams(layoutParams7);
            this.mPlayButton.setImageDrawable(Drawables.NATIVE_PLAY.createDrawable(getContext()));
            addView(this.mPlayButton);
            this.mIsInitialized = true;
            updateViewState();
        }
    }

    public void reset() {
        setMode(Mode.IMAGE);
        setPlayButtonClickListener((View.OnClickListener) null);
        setMuteControlClickListener((View.OnClickListener) null);
        setVideoClickListener((View.OnClickListener) null);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (mode != 1073741824) {
            if (mode == Integer.MIN_VALUE) {
                size = Math.min(size, measuredWidth);
            } else {
                size = measuredWidth;
            }
        }
        int i3 = (int) (((float) size) * ASPECT_MULTIPLIER_WIDTH_TO_HEIGHT);
        if (mode2 != 1073741824 || size2 >= i3) {
            size2 = i3;
        } else {
            size = (int) (((float) size2) * ASPECT_MULTIPLIER_HEIGHT_TO_WIDTH);
        }
        if (Math.abs(size2 - measuredHeight) >= 2 || Math.abs(size - measuredWidth) >= 2) {
            MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, String.format("Resetting mediaLayout size to w: %d h: %d", new Object[]{Integer.valueOf(size), Integer.valueOf(size2)}));
            getLayoutParams().width = size;
            getLayoutParams().height = size2;
        }
        super.onMeasure(i, i2);
    }

    public void setMainImageDrawable(Drawable drawable) {
        Preconditions.checkNotNull(drawable);
        this.mMainImageView.setImageDrawable(drawable);
    }

    public void resetProgress() {
        VastVideoProgressBarWidget vastVideoProgressBarWidget = this.mVideoProgress;
        if (vastVideoProgressBarWidget != null) {
            vastVideoProgressBarWidget.reset();
        }
    }

    public void updateProgress(int i) {
        VastVideoProgressBarWidget vastVideoProgressBarWidget = this.mVideoProgress;
        if (vastVideoProgressBarWidget != null) {
            vastVideoProgressBarWidget.updateProgress(i);
        }
    }

    public TextureView getTextureView() {
        return this.mVideoTextureView;
    }

    public void setMode(Mode mode) {
        Preconditions.checkNotNull(mode);
        this.mMode = mode;
        post(new Runnable() {
            public void run() {
                MediaLayout.this.updateViewState();
            }
        });
    }

    public ImageView getMainImageView() {
        return this.mMainImageView;
    }

    public void setMuteControlClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.mMuteControl;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public void setPlayButtonClickListener(View.OnClickListener onClickListener) {
        View view;
        if (this.mPlayButton != null && (view = this.mOverlay) != null) {
            view.setOnClickListener(onClickListener);
            this.mPlayButton.setOnClickListener(onClickListener);
        }
    }

    public void setVideoClickListener(View.OnClickListener onClickListener) {
        TextureView textureView = this.mVideoTextureView;
        if (textureView != null) {
            textureView.setOnClickListener(onClickListener);
        }
    }

    public void setMuteState(MuteState muteState) {
        Preconditions.checkNotNull(muteState);
        if (muteState != this.mMuteState) {
            this.mMuteState = muteState;
            if (this.mMuteControl == null) {
                return;
            }
            if (AnonymousClass2.$SwitchMap$com$mopub$nativeads$MediaLayout$MuteState[this.mMuteState.ordinal()] != 1) {
                this.mMuteControl.setImageDrawable(this.mUnmutedDrawable);
            } else {
                this.mMuteControl.setImageDrawable(this.mMutedDrawable);
            }
        }
    }

    /* renamed from: com.mopub.nativeads.MediaLayout$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$MediaLayout$Mode;
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$MediaLayout$MuteState;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|5|6|7|8|9|10|11|12|13|14|15|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0028 */
        static {
            /*
                com.mopub.nativeads.MediaLayout$Mode[] r0 = com.mopub.nativeads.MediaLayout.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$nativeads$MediaLayout$Mode = r0
                r1 = 1
                com.mopub.nativeads.MediaLayout$Mode r2 = com.mopub.nativeads.MediaLayout.Mode.IMAGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.nativeads.MediaLayout$Mode r3 = com.mopub.nativeads.MediaLayout.Mode.LOADING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.nativeads.MediaLayout$Mode r3 = com.mopub.nativeads.MediaLayout.Mode.BUFFERING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.nativeads.MediaLayout$Mode r3 = com.mopub.nativeads.MediaLayout.Mode.PLAYING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4 = 4
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.mopub.nativeads.MediaLayout$Mode r3 = com.mopub.nativeads.MediaLayout.Mode.PAUSED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r4 = 5
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r2 = $SwitchMap$com$mopub$nativeads$MediaLayout$Mode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.mopub.nativeads.MediaLayout$Mode r3 = com.mopub.nativeads.MediaLayout.Mode.FINISHED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r4 = 6
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.mopub.nativeads.MediaLayout$MuteState[] r2 = com.mopub.nativeads.MediaLayout.MuteState.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$mopub$nativeads$MediaLayout$MuteState = r2
                com.mopub.nativeads.MediaLayout$MuteState r3 = com.mopub.nativeads.MediaLayout.MuteState.MUTED     // Catch:{ NoSuchFieldError -> 0x005a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r1 = $SwitchMap$com$mopub$nativeads$MediaLayout$MuteState     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.mopub.nativeads.MediaLayout$MuteState r2 = com.mopub.nativeads.MediaLayout.MuteState.UNMUTED     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.MediaLayout.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public void updateViewState() {
        switch (AnonymousClass2.$SwitchMap$com$mopub$nativeads$MediaLayout$Mode[this.mMode.ordinal()]) {
            case 1:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(4);
                return;
            case 2:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(0);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(4);
                return;
            case 3:
                setMainImageVisibility(4);
                setLoadingSpinnerVisibility(0);
                setVideoControlVisibility(0);
                setPlayButtonVisibility(4);
                break;
            case 4:
                break;
            case 5:
                setMainImageVisibility(4);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(0);
                setPlayButtonVisibility(0);
                return;
            case 6:
                setMainImageVisibility(0);
                setLoadingSpinnerVisibility(4);
                setVideoControlVisibility(4);
                setPlayButtonVisibility(0);
                return;
            default:
                return;
        }
        setMainImageVisibility(4);
        setLoadingSpinnerVisibility(4);
        setVideoControlVisibility(0);
        setPlayButtonVisibility(4);
    }

    private void setMainImageVisibility(int i) {
        this.mMainImageView.setVisibility(i);
    }

    private void setLoadingSpinnerVisibility(int i) {
        ProgressBar progressBar = this.mLoadingSpinner;
        if (progressBar != null) {
            progressBar.setVisibility(i);
        }
        ImageView imageView = this.mTopGradient;
        if (imageView != null) {
            imageView.setVisibility(i);
        }
    }

    private void setVideoControlVisibility(int i) {
        ImageView imageView = this.mBottomGradient;
        if (imageView != null) {
            imageView.setVisibility(i);
        }
        VastVideoProgressBarWidget vastVideoProgressBarWidget = this.mVideoProgress;
        if (vastVideoProgressBarWidget != null) {
            vastVideoProgressBarWidget.setVisibility(i);
        }
        ImageView imageView2 = this.mMuteControl;
        if (imageView2 != null) {
            imageView2.setVisibility(i);
        }
    }

    private void setPlayButtonVisibility(int i) {
        ImageView imageView = this.mPlayButton;
        if (imageView != null && this.mOverlay != null) {
            imageView.setVisibility(i);
            this.mOverlay.setVisibility(i);
        }
    }
}
