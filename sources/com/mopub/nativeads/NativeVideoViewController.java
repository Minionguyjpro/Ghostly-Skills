package com.mopub.nativeads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.BaseVideoViewController;
import com.mopub.mobileads.VastErrorCode;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.nativeads.NativeFullScreenVideoView;
import com.mopub.nativeads.NativeVideoController;

public class NativeVideoViewController extends BaseVideoViewController implements AudioManager.OnAudioFocusChangeListener, TextureView.SurfaceTextureListener, NativeVideoController.Listener {
    /* access modifiers changed from: private */
    public Bitmap mCachedVideoFrame;
    /* access modifiers changed from: private */
    public boolean mEnded;
    private boolean mError;
    /* access modifiers changed from: private */
    public final NativeFullScreenVideoView mFullScreenVideoView;
    private int mLatestVideoControllerState;
    /* access modifiers changed from: private */
    public final NativeVideoController mNativeVideoController;
    /* access modifiers changed from: private */
    public VastVideoConfig mVastVideoConfig;
    private VideoState mVideoState;

    enum VideoState {
        NONE,
        LOADING,
        BUFFERING,
        PAUSED,
        PLAYING,
        ENDED,
        FAILED_LOAD
    }

    /* access modifiers changed from: protected */
    public VideoView getVideoView() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
    }

    /* access modifiers changed from: protected */
    public void onPause() {
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public NativeVideoViewController(Context context, Bundle bundle, Bundle bundle2, BaseVideoViewController.BaseVideoViewControllerListener baseVideoViewControllerListener) {
        this(context, bundle, bundle2, baseVideoViewControllerListener, new NativeFullScreenVideoView(context, context.getResources().getConfiguration().orientation, ((VastVideoConfig) bundle.get(Constants.NATIVE_VAST_VIDEO_CONFIG)).getCustomCtaText()));
    }

    NativeVideoViewController(Context context, Bundle bundle, Bundle bundle2, BaseVideoViewController.BaseVideoViewControllerListener baseVideoViewControllerListener, NativeFullScreenVideoView nativeFullScreenVideoView) {
        super(context, (Long) null, baseVideoViewControllerListener);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotNull(baseVideoViewControllerListener);
        Preconditions.checkNotNull(nativeFullScreenVideoView);
        this.mVideoState = VideoState.NONE;
        this.mVastVideoConfig = (VastVideoConfig) bundle.get(Constants.NATIVE_VAST_VIDEO_CONFIG);
        this.mFullScreenVideoView = nativeFullScreenVideoView;
        this.mNativeVideoController = NativeVideoController.getForId(((Long) bundle.get(Constants.NATIVE_VIDEO_ID)).longValue());
        Preconditions.checkNotNull(this.mVastVideoConfig);
        Preconditions.checkNotNull(this.mNativeVideoController);
    }

    /* access modifiers changed from: protected */
    public void onCreate() {
        this.mFullScreenVideoView.setSurfaceTextureListener(this);
        this.mFullScreenVideoView.setMode(NativeFullScreenVideoView.Mode.LOADING);
        this.mFullScreenVideoView.setPlayControlClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (NativeVideoViewController.this.mEnded) {
                    boolean unused = NativeVideoViewController.this.mEnded = false;
                    NativeVideoViewController.this.mFullScreenVideoView.resetProgress();
                    NativeVideoViewController.this.mNativeVideoController.seekTo(0);
                }
                NativeVideoViewController.this.applyState(VideoState.PLAYING);
            }
        });
        this.mFullScreenVideoView.setCloseControlListener(new View.OnClickListener() {
            public void onClick(View view) {
                NativeVideoViewController.this.applyState(VideoState.PAUSED, true);
                NativeVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
            }
        });
        this.mFullScreenVideoView.setCtaClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NativeVideoViewController.this.mNativeVideoController.setPlayWhenReady(false);
                NativeVideoViewController nativeVideoViewController = NativeVideoViewController.this;
                Bitmap unused = nativeVideoViewController.mCachedVideoFrame = nativeVideoViewController.mFullScreenVideoView.getTextureView().getBitmap();
                NativeVideoViewController.this.mNativeVideoController.handleCtaClick((Activity) NativeVideoViewController.this.getContext());
            }
        });
        this.mFullScreenVideoView.setPrivacyInformationClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NativeVideoViewController.this.mNativeVideoController.setPlayWhenReady(false);
                NativeVideoViewController nativeVideoViewController = NativeVideoViewController.this;
                Bitmap unused = nativeVideoViewController.mCachedVideoFrame = nativeVideoViewController.mFullScreenVideoView.getTextureView().getBitmap();
                String privacyInformationIconClickthroughUrl = NativeVideoViewController.this.mVastVideoConfig.getPrivacyInformationIconClickthroughUrl();
                if (TextUtils.isEmpty(privacyInformationIconClickthroughUrl)) {
                    privacyInformationIconClickthroughUrl = "https://www.mopub.com/optout/";
                }
                new UrlHandler.Builder().withSupportedUrlActions(UrlAction.OPEN_IN_APP_BROWSER, new UrlAction[0]).build().handleUrl(NativeVideoViewController.this.getContext(), privacyInformationIconClickthroughUrl);
            }
        });
        this.mFullScreenVideoView.setPrivacyInformationIconImageUrl(this.mVastVideoConfig.getPrivacyInformationIconImageUrl());
        this.mFullScreenVideoView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        getBaseVideoViewControllerListener().onSetContentView(this.mFullScreenVideoView);
        this.mNativeVideoController.setProgressListener(new NativeVideoController.NativeVideoProgressRunnable.ProgressListener() {
            public void updateProgress(int i) {
                NativeVideoViewController.this.mFullScreenVideoView.updateProgress(i);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Bitmap bitmap = this.mCachedVideoFrame;
        if (bitmap != null) {
            this.mFullScreenVideoView.setCachedVideoFrame(bitmap);
        }
        this.mNativeVideoController.prepare(this);
        this.mNativeVideoController.setListener(this);
        this.mNativeVideoController.setOnAudioFocusChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        this.mFullScreenVideoView.setOrientation(configuration.orientation);
    }

    /* access modifiers changed from: protected */
    public void onBackPressed() {
        applyState(VideoState.PAUSED, true);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mNativeVideoController.setTextureView(this.mFullScreenVideoView.getTextureView());
        if (!this.mEnded) {
            NativeVideoController nativeVideoController = this.mNativeVideoController;
            nativeVideoController.seekTo(nativeVideoController.getCurrentPosition());
        }
        this.mNativeVideoController.setPlayWhenReady(!this.mEnded);
        if (this.mNativeVideoController.getDuration() - this.mNativeVideoController.getCurrentPosition() < 750) {
            this.mEnded = true;
            maybeChangeState();
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mNativeVideoController.release(this);
        applyState(VideoState.PAUSED);
        return true;
    }

    public void onStateChanged(boolean z, int i) {
        this.mLatestVideoControllerState = i;
        maybeChangeState();
    }

    public void onError(Exception exc) {
        MoPubLog.log(MoPubLog.SdkLogEvent.ERROR_WITH_THROWABLE, "Error playing back video.", exc);
        this.mError = true;
        maybeChangeState();
    }

    public void onAudioFocusChange(int i) {
        if (i == -1 || i == -2) {
            applyState(VideoState.PAUSED);
        } else if (i == -3) {
            this.mNativeVideoController.setAudioVolume(0.3f);
        } else if (i == 1) {
            this.mNativeVideoController.setAudioVolume(1.0f);
            maybeChangeState();
        }
    }

    private void maybeChangeState() {
        VideoState videoState = this.mVideoState;
        if (this.mError) {
            videoState = VideoState.FAILED_LOAD;
        } else if (this.mEnded) {
            videoState = VideoState.ENDED;
        } else {
            int i = this.mLatestVideoControllerState;
            if (i == 1) {
                videoState = VideoState.LOADING;
            } else if (i == 2) {
                videoState = VideoState.BUFFERING;
            } else if (i == 3) {
                videoState = VideoState.PLAYING;
            } else if (i == 4 || i == 5) {
                videoState = VideoState.ENDED;
            }
        }
        applyState(videoState);
    }

    /* access modifiers changed from: package-private */
    public void applyState(VideoState videoState) {
        applyState(videoState, false);
    }

    /* access modifiers changed from: package-private */
    public void applyState(VideoState videoState, boolean z) {
        Preconditions.checkNotNull(videoState);
        if (this.mVideoState != videoState) {
            switch (AnonymousClass6.$SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState[videoState.ordinal()]) {
                case 1:
                    this.mNativeVideoController.setPlayWhenReady(false);
                    this.mNativeVideoController.setAudioEnabled(false);
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mFullScreenVideoView.setMode(NativeFullScreenVideoView.Mode.LOADING);
                    this.mVastVideoConfig.handleError(getContext(), (VastErrorCode) null, 0);
                    break;
                case 2:
                case 3:
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mFullScreenVideoView.setMode(NativeFullScreenVideoView.Mode.LOADING);
                    break;
                case 4:
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mNativeVideoController.setAudioEnabled(true);
                    this.mNativeVideoController.setAppAudioEnabled(true);
                    this.mFullScreenVideoView.setMode(NativeFullScreenVideoView.Mode.PLAYING);
                    break;
                case 5:
                    if (!z) {
                        this.mNativeVideoController.setAppAudioEnabled(false);
                    }
                    this.mNativeVideoController.setPlayWhenReady(false);
                    this.mFullScreenVideoView.setMode(NativeFullScreenVideoView.Mode.PAUSED);
                    break;
                case 6:
                    this.mEnded = true;
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mFullScreenVideoView.updateProgress(1000);
                    this.mFullScreenVideoView.setMode(NativeFullScreenVideoView.Mode.FINISHED);
                    this.mVastVideoConfig.handleComplete(getContext(), 0);
                    break;
            }
            this.mVideoState = videoState;
        }
    }

    /* renamed from: com.mopub.nativeads.NativeVideoViewController$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.mopub.nativeads.NativeVideoViewController$VideoState[] r0 = com.mopub.nativeads.NativeVideoViewController.VideoState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState = r0
                com.mopub.nativeads.NativeVideoViewController$VideoState r1 = com.mopub.nativeads.NativeVideoViewController.VideoState.FAILED_LOAD     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState     // Catch:{ NoSuchFieldError -> 0x001d }
                com.mopub.nativeads.NativeVideoViewController$VideoState r1 = com.mopub.nativeads.NativeVideoViewController.VideoState.LOADING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.mopub.nativeads.NativeVideoViewController$VideoState r1 = com.mopub.nativeads.NativeVideoViewController.VideoState.BUFFERING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.mopub.nativeads.NativeVideoViewController$VideoState r1 = com.mopub.nativeads.NativeVideoViewController.VideoState.PLAYING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState     // Catch:{ NoSuchFieldError -> 0x003e }
                com.mopub.nativeads.NativeVideoViewController$VideoState r1 = com.mopub.nativeads.NativeVideoViewController.VideoState.PAUSED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.mopub.nativeads.NativeVideoViewController$VideoState r1 = com.mopub.nativeads.NativeVideoViewController.VideoState.ENDED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.nativeads.NativeVideoViewController.AnonymousClass6.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public NativeFullScreenVideoView getNativeFullScreenVideoView() {
        return this.mFullScreenVideoView;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VideoState getVideoState() {
        return this.mVideoState;
    }
}
