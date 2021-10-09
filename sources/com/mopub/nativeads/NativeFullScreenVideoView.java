package com.mopub.nativeads;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.mopub.common.Preconditions;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.mobileads.VastVideoProgressBarWidget;
import com.mopub.mobileads.resource.CloseButtonDrawable;
import com.mopub.mobileads.resource.CtaButtonDrawable;
import com.mopub.mobileads.resource.DrawableConstants;

public class NativeFullScreenVideoView extends RelativeLayout {
    private final ImageView mBottomGradient;
    private final ImageView mCachedVideoFrameView;
    private final ImageView mCloseControl;
    final int mCloseControlSizePx;
    final int mClosePaddingPx;
    private final ImageView mCtaButton;
    final int mCtaHeightPx;
    final int mCtaMarginPx;
    final int mCtaWidthPx;
    final int mGradientStripHeightPx;
    private final ProgressBar mLoadingSpinner;
    Mode mMode;
    private int mOrientation;
    private final View mOverlay;
    private final ImageView mPlayButton;
    final int mPlayControlSizePx;
    private final ImageView mPrivacyInformationIcon;
    final int mPrivacyInformationIconSizePx;
    private final ImageView mTopGradient;
    private final VastVideoProgressBarWidget mVideoProgress;
    private final TextureView mVideoTexture;

    public enum Mode {
        LOADING,
        PLAYING,
        PAUSED,
        FINISHED
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NativeFullScreenVideoView(android.content.Context r16, int r17, java.lang.String r18) {
        /*
            r15 = this;
            r1 = r16
            android.widget.ImageView r4 = new android.widget.ImageView
            r4.<init>(r1)
            android.view.TextureView r5 = new android.view.TextureView
            r5.<init>(r1)
            android.widget.ProgressBar r6 = new android.widget.ProgressBar
            r6.<init>(r1)
            android.widget.ImageView r7 = new android.widget.ImageView
            r7.<init>(r1)
            android.widget.ImageView r8 = new android.widget.ImageView
            r8.<init>(r1)
            com.mopub.mobileads.VastVideoProgressBarWidget r9 = new com.mopub.mobileads.VastVideoProgressBarWidget
            r9.<init>(r1)
            android.view.View r10 = new android.view.View
            r10.<init>(r1)
            android.widget.ImageView r11 = new android.widget.ImageView
            r11.<init>(r1)
            android.widget.ImageView r12 = new android.widget.ImageView
            r12.<init>(r1)
            android.widget.ImageView r13 = new android.widget.ImageView
            r13.<init>(r1)
            android.widget.ImageView r14 = new android.widget.ImageView
            r14.<init>(r1)
            r0 = r15
            r2 = r17
            r3 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.NativeFullScreenVideoView.<init>(android.content.Context, int, java.lang.String):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NativeFullScreenVideoView(Context context, int i, String str, ImageView imageView, TextureView textureView, ProgressBar progressBar, ImageView imageView2, ImageView imageView3, VastVideoProgressBarWidget vastVideoProgressBarWidget, View view, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7) {
        super(context);
        Context context2 = context;
        ImageView imageView8 = imageView;
        TextureView textureView2 = textureView;
        ProgressBar progressBar2 = progressBar;
        ImageView imageView9 = imageView2;
        ImageView imageView10 = imageView3;
        VastVideoProgressBarWidget vastVideoProgressBarWidget2 = vastVideoProgressBarWidget;
        View view2 = view;
        ImageView imageView11 = imageView4;
        ImageView imageView12 = imageView5;
        ImageView imageView13 = imageView6;
        ImageView imageView14 = imageView7;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(imageView);
        Preconditions.checkNotNull(textureView);
        Preconditions.checkNotNull(progressBar);
        Preconditions.checkNotNull(imageView2);
        Preconditions.checkNotNull(imageView3);
        Preconditions.checkNotNull(vastVideoProgressBarWidget);
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(imageView4);
        Preconditions.checkNotNull(imageView5);
        Preconditions.checkNotNull(imageView6);
        Preconditions.checkNotNull(imageView7);
        this.mOrientation = i;
        this.mMode = Mode.LOADING;
        this.mCtaWidthPx = Dips.asIntPixels(200.0f, context2);
        this.mCtaHeightPx = Dips.asIntPixels(42.0f, context2);
        this.mCtaMarginPx = Dips.asIntPixels(10.0f, context2);
        this.mCloseControlSizePx = Dips.asIntPixels(50.0f, context2);
        this.mClosePaddingPx = Dips.asIntPixels(8.0f, context2);
        this.mPrivacyInformationIconSizePx = Dips.asIntPixels(44.0f, context2);
        this.mPlayControlSizePx = Dips.asIntPixels(50.0f, context2);
        this.mGradientStripHeightPx = Dips.asIntPixels(45.0f, context2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.mVideoTexture = textureView2;
        textureView2.setId(View.generateViewId());
        this.mVideoTexture.setLayoutParams(layoutParams);
        addView(this.mVideoTexture);
        this.mCachedVideoFrameView = imageView8;
        imageView8.setId(View.generateViewId());
        this.mCachedVideoFrameView.setLayoutParams(layoutParams);
        this.mCachedVideoFrameView.setBackgroundColor(0);
        addView(this.mCachedVideoFrameView);
        int i2 = this.mPlayControlSizePx;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i2, i2);
        layoutParams2.addRule(13);
        this.mLoadingSpinner = progressBar2;
        progressBar2.setId(View.generateViewId());
        this.mLoadingSpinner.setBackground(new LoadingBackground(context2));
        this.mLoadingSpinner.setLayoutParams(layoutParams2);
        this.mLoadingSpinner.setIndeterminate(true);
        addView(this.mLoadingSpinner);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, this.mGradientStripHeightPx);
        layoutParams3.addRule(8, this.mVideoTexture.getId());
        this.mBottomGradient = imageView9;
        imageView9.setId(View.generateViewId());
        this.mBottomGradient.setLayoutParams(layoutParams3);
        this.mBottomGradient.setImageDrawable(new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{DrawableConstants.GradientStrip.START_COLOR, DrawableConstants.GradientStrip.END_COLOR}));
        addView(this.mBottomGradient);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, this.mGradientStripHeightPx);
        layoutParams4.addRule(10);
        this.mTopGradient = imageView10;
        imageView10.setId(View.generateViewId());
        this.mTopGradient.setLayoutParams(layoutParams4);
        this.mTopGradient.setImageDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{DrawableConstants.GradientStrip.START_COLOR, DrawableConstants.GradientStrip.END_COLOR}));
        addView(this.mTopGradient);
        this.mVideoProgress = vastVideoProgressBarWidget2;
        vastVideoProgressBarWidget2.setId(View.generateViewId());
        this.mVideoProgress.setAnchorId(this.mVideoTexture.getId());
        this.mVideoProgress.calibrateAndMakeVisible(1000, 0);
        addView(this.mVideoProgress);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams5.addRule(13);
        this.mOverlay = view2;
        view2.setId(View.generateViewId());
        this.mOverlay.setLayoutParams(layoutParams5);
        this.mOverlay.setBackgroundColor(DrawableConstants.TRANSPARENT_GRAY);
        addView(this.mOverlay);
        int i3 = this.mPlayControlSizePx;
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(i3, i3);
        layoutParams6.addRule(13);
        this.mPlayButton = imageView11;
        imageView11.setId(View.generateViewId());
        this.mPlayButton.setLayoutParams(layoutParams6);
        this.mPlayButton.setImageDrawable(Drawables.NATIVE_PLAY.createDrawable(context2));
        addView(this.mPlayButton);
        this.mPrivacyInformationIcon = imageView12;
        imageView12.setId(View.generateViewId());
        ImageView imageView15 = this.mPrivacyInformationIcon;
        int i4 = this.mClosePaddingPx;
        imageView15.setPadding(i4, i4, i4 * 2, i4 * 2);
        addView(this.mPrivacyInformationIcon);
        CtaButtonDrawable ctaButtonDrawable = new CtaButtonDrawable(context2);
        if (!TextUtils.isEmpty(str)) {
            ctaButtonDrawable.setCtaText(str);
        }
        this.mCtaButton = imageView13;
        imageView13.setId(View.generateViewId());
        this.mCtaButton.setImageDrawable(ctaButtonDrawable);
        addView(this.mCtaButton);
        this.mCloseControl = imageView14;
        imageView14.setId(View.generateViewId());
        this.mCloseControl.setImageDrawable(new CloseButtonDrawable());
        ImageView imageView16 = this.mCloseControl;
        int i5 = this.mClosePaddingPx;
        imageView16.setPadding(i5 * 3, i5, i5, i5 * 3);
        addView(this.mCloseControl);
        updateViewState();
    }

    public void resetProgress() {
        this.mVideoProgress.reset();
    }

    public void setMode(Mode mode) {
        Preconditions.checkNotNull(mode);
        if (this.mMode != mode) {
            this.mMode = mode;
            updateViewState();
        }
    }

    public TextureView getTextureView() {
        return this.mVideoTexture;
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            updateViewState();
        }
    }

    public void setSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        this.mVideoTexture.setSurfaceTextureListener(surfaceTextureListener);
        SurfaceTexture surfaceTexture = this.mVideoTexture.getSurfaceTexture();
        if (surfaceTexture != null && surfaceTextureListener != null) {
            surfaceTextureListener.onSurfaceTextureAvailable(surfaceTexture, this.mVideoTexture.getWidth(), this.mVideoTexture.getHeight());
        }
    }

    public void setCloseControlListener(View.OnClickListener onClickListener) {
        this.mCloseControl.setOnClickListener(onClickListener);
    }

    public void setPrivacyInformationClickListener(View.OnClickListener onClickListener) {
        this.mPrivacyInformationIcon.setOnClickListener(onClickListener);
    }

    public void setPrivacyInformationIconImageUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mPrivacyInformationIcon.setImageDrawable(Drawables.NATIVE_PRIVACY_INFORMATION_ICON.createDrawable(this.mPrivacyInformationIcon.getContext()));
        } else {
            NativeImageHelper.loadImageView(str, this.mPrivacyInformationIcon);
        }
    }

    public void setCtaClickListener(View.OnClickListener onClickListener) {
        this.mCtaButton.setOnClickListener(onClickListener);
    }

    public void setPlayControlClickListener(View.OnClickListener onClickListener) {
        this.mPlayButton.setOnClickListener(onClickListener);
        this.mOverlay.setOnClickListener(onClickListener);
    }

    public void updateProgress(int i) {
        this.mVideoProgress.updateProgress(i);
    }

    public void setCachedVideoFrame(Bitmap bitmap) {
        this.mCachedVideoFrameView.setImageBitmap(bitmap);
    }

    /* renamed from: com.mopub.nativeads.NativeFullScreenVideoView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$NativeFullScreenVideoView$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.mopub.nativeads.NativeFullScreenVideoView$Mode[] r0 = com.mopub.nativeads.NativeFullScreenVideoView.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$nativeads$NativeFullScreenVideoView$Mode = r0
                com.mopub.nativeads.NativeFullScreenVideoView$Mode r1 = com.mopub.nativeads.NativeFullScreenVideoView.Mode.LOADING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeFullScreenVideoView$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.nativeads.NativeFullScreenVideoView$Mode r1 = com.mopub.nativeads.NativeFullScreenVideoView.Mode.PLAYING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeFullScreenVideoView$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.nativeads.NativeFullScreenVideoView$Mode r1 = com.mopub.nativeads.NativeFullScreenVideoView.Mode.PAUSED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeFullScreenVideoView$Mode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.nativeads.NativeFullScreenVideoView$Mode r1 = com.mopub.nativeads.NativeFullScreenVideoView.Mode.FINISHED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.NativeFullScreenVideoView.AnonymousClass1.<clinit>():void");
        }
    }

    private void updateViewState() {
        int i = AnonymousClass1.$SwitchMap$com$mopub$nativeads$NativeFullScreenVideoView$Mode[this.mMode.ordinal()];
        if (i == 1) {
            setCachedImageVisibility(0);
            setLoadingSpinnerVisibility(0);
            setVideoProgressVisibility(4);
            setPlayButtonVisibility(4);
        } else if (i == 2) {
            setCachedImageVisibility(4);
            setLoadingSpinnerVisibility(4);
            setVideoProgressVisibility(0);
            setPlayButtonVisibility(4);
        } else if (i == 3) {
            setCachedImageVisibility(4);
            setLoadingSpinnerVisibility(4);
            setVideoProgressVisibility(0);
            setPlayButtonVisibility(0);
        } else if (i == 4) {
            setCachedImageVisibility(0);
            setLoadingSpinnerVisibility(4);
            setVideoProgressVisibility(4);
            setPlayButtonVisibility(0);
        }
        updateVideoTextureLayout();
        updateControlLayouts();
    }

    private void setCachedImageVisibility(int i) {
        this.mCachedVideoFrameView.setVisibility(i);
    }

    private void setLoadingSpinnerVisibility(int i) {
        this.mLoadingSpinner.setVisibility(i);
    }

    private void setVideoProgressVisibility(int i) {
        this.mVideoProgress.setVisibility(i);
    }

    private void setPlayButtonVisibility(int i) {
        this.mPlayButton.setVisibility(i);
        this.mOverlay.setVisibility(i);
    }

    private void updateVideoTextureLayout() {
        Configuration configuration = getContext().getResources().getConfiguration();
        ViewGroup.LayoutParams layoutParams = this.mVideoTexture.getLayoutParams();
        int dipsToIntPixels = Dips.dipsToIntPixels((float) configuration.screenWidthDp, getContext());
        if (dipsToIntPixels != layoutParams.width) {
            layoutParams.width = dipsToIntPixels;
        }
        int dipsToIntPixels2 = Dips.dipsToIntPixels((((float) configuration.screenWidthDp) * 9.0f) / 16.0f, getContext());
        if (dipsToIntPixels2 != layoutParams.height) {
            layoutParams.height = dipsToIntPixels2;
        }
    }

    private void updateControlLayouts() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.mCtaWidthPx, this.mCtaHeightPx);
        int i = this.mCtaMarginPx;
        layoutParams.setMargins(i, i, i, i);
        int i2 = this.mPrivacyInformationIconSizePx;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i2, i2);
        int i3 = this.mCloseControlSizePx;
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i3, i3);
        int i4 = this.mOrientation;
        if (i4 == 1) {
            layoutParams.addRule(3, this.mVideoTexture.getId());
            layoutParams.addRule(14);
            layoutParams2.addRule(10);
            layoutParams2.addRule(9);
            layoutParams3.addRule(10);
            layoutParams3.addRule(11);
        } else if (i4 == 2) {
            layoutParams.addRule(2, this.mVideoProgress.getId());
            layoutParams.addRule(11);
            layoutParams2.addRule(6, this.mVideoTexture.getId());
            layoutParams2.addRule(5, this.mVideoTexture.getId());
            layoutParams3.addRule(6, this.mVideoTexture.getId());
            layoutParams3.addRule(7, this.mVideoTexture.getId());
        }
        this.mCtaButton.setLayoutParams(layoutParams);
        this.mPrivacyInformationIcon.setLayoutParams(layoutParams2);
        this.mCloseControl.setLayoutParams(layoutParams3);
    }

    static class LoadingBackground extends Drawable {
        private final RectF mButtonRect;
        final int mCornerRadiusPx;
        private final Paint mPaint;

        public int getOpacity() {
            return 0;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }

        LoadingBackground(Context context) {
            this(context, new RectF(), new Paint());
        }

        LoadingBackground(Context context, RectF rectF, Paint paint) {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(rectF);
            Preconditions.checkNotNull(paint);
            this.mButtonRect = rectF;
            this.mPaint = paint;
            paint.setColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
            this.mPaint.setAlpha(128);
            this.mPaint.setAntiAlias(true);
            this.mCornerRadiusPx = Dips.asIntPixels(5.0f, context);
        }

        public void draw(Canvas canvas) {
            this.mButtonRect.set(getBounds());
            RectF rectF = this.mButtonRect;
            int i = this.mCornerRadiusPx;
            canvas.drawRoundRect(rectF, (float) i, (float) i, this.mPaint);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public ImageView getCtaButton() {
        return this.mCtaButton;
    }
}
