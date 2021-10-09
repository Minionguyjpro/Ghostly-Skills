package com.mopub.nativeads;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.TextureView;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.util.Predicate;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibilityTracker;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.RepeatingHandlerRunnable;
import com.mopub.mobileads.VastTracker;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.network.TrackingRequest;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativeVideoController extends Player.DefaultEventListener implements AudioManager.OnAudioFocusChangeListener {
    private static final int BUFFER_SEGMENT_COUNT = 32;
    private static final int BUFFER_SEGMENT_SIZE = 65536;
    public static final long RESUME_FINISHED_THRESHOLD = 750;
    public static final int STATE_BUFFERING = 2;
    public static final int STATE_CLEARED = 5;
    public static final int STATE_ENDED = 4;
    public static final int STATE_IDLE = 1;
    public static final int STATE_READY = 3;
    private static final Map<Long, NativeVideoController> sManagerMap = new HashMap(4);
    private boolean mAppAudioEnabled;
    private boolean mAudioEnabled;
    private AudioManager mAudioManager;
    private MediaCodecAudioRenderer mAudioRenderer;
    /* access modifiers changed from: private */
    public final Context mContext;
    private volatile ExoPlayer mExoPlayer;
    private boolean mExoPlayerStateStartedFromIdle;
    private BitmapDrawable mFinalFrame;
    private final Handler mHandler;
    private Listener mListener;
    private final MoPubExoPlayerFactory mMoPubExoPlayerFactory;
    private NativeVideoProgressRunnable mNativeVideoProgressRunnable;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    private WeakReference<Object> mOwnerRef;
    private boolean mPlayWhenReady;
    private int mPreviousExoPlayerState;
    private Surface mSurface;
    private TextureView mTextureView;
    private VastVideoConfig mVastVideoConfig;
    private MediaCodecVideoRenderer mVideoRenderer;

    public interface Listener {
        void onError(Exception exc);

        void onStateChanged(boolean z, int i);
    }

    public void onLoadingChanged(boolean z) {
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    public static NativeVideoController createForId(long j, Context context, List<VisibilityTrackingEvent> list, VastVideoConfig vastVideoConfig) {
        NativeVideoController nativeVideoController = new NativeVideoController(context, list, vastVideoConfig);
        sManagerMap.put(Long.valueOf(j), nativeVideoController);
        return nativeVideoController;
    }

    public static NativeVideoController createForId(long j, Context context, VastVideoConfig vastVideoConfig, NativeVideoProgressRunnable nativeVideoProgressRunnable, MoPubExoPlayerFactory moPubExoPlayerFactory, AudioManager audioManager) {
        NativeVideoController nativeVideoController = new NativeVideoController(context, vastVideoConfig, nativeVideoProgressRunnable, moPubExoPlayerFactory, audioManager);
        sManagerMap.put(Long.valueOf(j), nativeVideoController);
        return nativeVideoController;
    }

    public static void setForId(long j, NativeVideoController nativeVideoController) {
        sManagerMap.put(Long.valueOf(j), nativeVideoController);
    }

    public static NativeVideoController getForId(long j) {
        return sManagerMap.get(Long.valueOf(j));
    }

    public static NativeVideoController remove(long j) {
        return sManagerMap.remove(Long.valueOf(j));
    }

    private NativeVideoController(Context context, List<VisibilityTrackingEvent> list, VastVideoConfig vastVideoConfig) {
        this(context, vastVideoConfig, new NativeVideoProgressRunnable(context, new Handler(Looper.getMainLooper()), list, vastVideoConfig), new MoPubExoPlayerFactory(), (AudioManager) context.getSystemService("audio"));
    }

    private NativeVideoController(Context context, VastVideoConfig vastVideoConfig, NativeVideoProgressRunnable nativeVideoProgressRunnable, MoPubExoPlayerFactory moPubExoPlayerFactory, AudioManager audioManager) {
        this.mPreviousExoPlayerState = 1;
        this.mExoPlayerStateStartedFromIdle = true;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastVideoConfig);
        Preconditions.checkNotNull(moPubExoPlayerFactory);
        Preconditions.checkNotNull(audioManager);
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mVastVideoConfig = vastVideoConfig;
        this.mNativeVideoProgressRunnable = nativeVideoProgressRunnable;
        this.mMoPubExoPlayerFactory = moPubExoPlayerFactory;
        this.mAudioManager = audioManager;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setProgressListener(NativeVideoProgressRunnable.ProgressListener progressListener) {
        this.mNativeVideoProgressRunnable.setProgressListener(progressListener);
    }

    public void setOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.mOnAudioFocusChangeListener = onAudioFocusChangeListener;
    }

    public void setPlayWhenReady(boolean z) {
        if (this.mPlayWhenReady != z) {
            this.mPlayWhenReady = z;
            setExoPlayWhenReady();
        }
    }

    public int getPlaybackState() {
        if (this.mExoPlayer == null) {
            return 5;
        }
        return this.mExoPlayer.getPlaybackState();
    }

    public void setAudioEnabled(boolean z) {
        this.mAudioEnabled = z;
        setExoAudio();
    }

    public void setAppAudioEnabled(boolean z) {
        if (this.mAppAudioEnabled != z) {
            this.mAppAudioEnabled = z;
            if (z) {
                this.mAudioManager.requestAudioFocus(this, 3, 1);
            } else {
                this.mAudioManager.abandonAudioFocus(this);
            }
        }
    }

    public void setAudioVolume(float f) {
        if (this.mAudioEnabled) {
            setExoAudio(f);
        }
    }

    public void onAudioFocusChange(int i) {
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = this.mOnAudioFocusChangeListener;
        if (onAudioFocusChangeListener != null) {
            onAudioFocusChangeListener.onAudioFocusChange(i);
        }
    }

    public void setTextureView(TextureView textureView) {
        Preconditions.checkNotNull(textureView);
        this.mSurface = new Surface(textureView.getSurfaceTexture());
        this.mTextureView = textureView;
        this.mNativeVideoProgressRunnable.setTextureView(textureView);
        setExoSurface(this.mSurface);
    }

    public void prepare(Object obj) {
        Preconditions.checkNotNull(obj);
        this.mOwnerRef = new WeakReference<>(obj);
        clearExistingPlayer();
        preparePlayer();
        setExoSurface(this.mSurface);
    }

    public void clear() {
        setPlayWhenReady(false);
        this.mSurface = null;
        clearExistingPlayer();
    }

    public void release(Object obj) {
        Preconditions.checkNotNull(obj);
        WeakReference<Object> weakReference = this.mOwnerRef;
        if ((weakReference == null ? null : weakReference.get()) == obj) {
            clearExistingPlayer();
        }
    }

    public void onPlayerStateChanged(boolean z, int i) {
        if (i == 4 && this.mFinalFrame == null) {
            if (this.mExoPlayer == null || this.mSurface == null || this.mTextureView == null) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "onPlayerStateChanged called afer view has been recycled.");
                return;
            }
            this.mFinalFrame = new BitmapDrawable(this.mContext.getResources(), this.mTextureView.getBitmap());
            this.mNativeVideoProgressRunnable.requestStop();
        }
        this.mPreviousExoPlayerState = i;
        if (i == 3) {
            this.mExoPlayerStateStartedFromIdle = false;
        } else if (i == 1) {
            this.mExoPlayerStateStartedFromIdle = true;
        }
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onStateChanged(z, i);
        }
    }

    public void seekTo(long j) {
        if (this.mExoPlayer != null) {
            this.mExoPlayer.seekTo(j);
            this.mNativeVideoProgressRunnable.seekTo(j);
        }
    }

    public long getCurrentPosition() {
        return this.mNativeVideoProgressRunnable.getCurrentPosition();
    }

    public long getDuration() {
        return this.mNativeVideoProgressRunnable.getDuration();
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onError(exoPlaybackException);
            this.mNativeVideoProgressRunnable.requestStop();
        }
    }

    public void handleCtaClick(Context context) {
        triggerImpressionTrackers();
        this.mVastVideoConfig.handleClickWithoutResult(context, 0);
    }

    public boolean hasFinalFrame() {
        return this.mFinalFrame != null;
    }

    public Drawable getFinalFrame() {
        return this.mFinalFrame;
    }

    /* access modifiers changed from: package-private */
    public void triggerImpressionTrackers() {
        this.mNativeVideoProgressRunnable.checkImpressionTrackers(true);
    }

    private void clearExistingPlayer() {
        if (this.mExoPlayer != null) {
            setExoSurface((Surface) null);
            this.mExoPlayer.stop();
            this.mExoPlayer.release();
            this.mExoPlayer = null;
            this.mNativeVideoProgressRunnable.stop();
            this.mNativeVideoProgressRunnable.setExoPlayer((ExoPlayer) null);
        }
    }

    private void preparePlayer() {
        if (this.mExoPlayer == null) {
            this.mVideoRenderer = new MediaCodecVideoRenderer(this.mContext, MediaCodecSelector.DEFAULT, 0, this.mHandler, (VideoRendererEventListener) null, 10);
            this.mAudioRenderer = new MediaCodecAudioRenderer(this.mContext, MediaCodecSelector.DEFAULT);
            DefaultAllocator defaultAllocator = new DefaultAllocator(true, BUFFER_SEGMENT_SIZE, 32);
            DefaultLoadControl.Builder builder = new DefaultLoadControl.Builder();
            builder.setAllocator(defaultAllocator);
            this.mExoPlayer = this.mMoPubExoPlayerFactory.newInstance(this.mContext, new Renderer[]{this.mVideoRenderer, this.mAudioRenderer}, new DefaultTrackSelector(), builder.createDefaultLoadControl());
            this.mNativeVideoProgressRunnable.setExoPlayer(this.mExoPlayer);
            this.mExoPlayer.addListener(this);
            AnonymousClass1 r0 = new DataSource.Factory() {
                public DataSource createDataSource() {
                    DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource("exo_demo", (Predicate<String>) null);
                    Cache cacheInstance = MoPubCache.getCacheInstance(NativeVideoController.this.mContext);
                    return cacheInstance != null ? new CacheDataSource(cacheInstance, defaultHttpDataSource) : defaultHttpDataSource;
                }
            };
            AnonymousClass2 r1 = new ExtractorsFactory() {
                public Extractor[] createExtractors() {
                    return new Extractor[]{new Mp4Extractor()};
                }
            };
            ExtractorMediaSource.Factory factory = new ExtractorMediaSource.Factory(r0);
            factory.setExtractorsFactory(r1);
            this.mExoPlayer.prepare(factory.createMediaSource(Uri.parse(this.mVastVideoConfig.getNetworkMediaFileUrl())));
            this.mNativeVideoProgressRunnable.startRepeating(50);
        }
        setExoAudio();
        setExoPlayWhenReady();
    }

    private void setExoPlayWhenReady() {
        if (this.mExoPlayer != null) {
            this.mExoPlayer.setPlayWhenReady(this.mPlayWhenReady);
        }
    }

    private void setExoAudio() {
        setExoAudio(this.mAudioEnabled ? 1.0f : 0.0f);
    }

    private void setExoAudio(float f) {
        ExoPlayer exoPlayer = this.mExoPlayer;
        MediaCodecAudioRenderer mediaCodecAudioRenderer = this.mAudioRenderer;
        if (exoPlayer != null && mediaCodecAudioRenderer != null) {
            PlayerMessage createMessage = exoPlayer.createMessage(mediaCodecAudioRenderer);
            if (createMessage == null) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "ExoPlayer.createMessage returned null.");
                return;
            }
            createMessage.setType(2).setPayload(Float.valueOf(f)).send();
        }
    }

    private void setExoSurface(Surface surface) {
        ExoPlayer exoPlayer = this.mExoPlayer;
        MediaCodecVideoRenderer mediaCodecVideoRenderer = this.mVideoRenderer;
        if (exoPlayer != null && mediaCodecVideoRenderer != null) {
            PlayerMessage createMessage = exoPlayer.createMessage(mediaCodecVideoRenderer);
            if (createMessage == null) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "ExoPlayer.createMessage returned null.");
                return;
            }
            createMessage.setType(1).setPayload(surface).send();
        }
    }

    static class MoPubExoPlayerFactory {
        MoPubExoPlayerFactory() {
        }

        public ExoPlayer newInstance(Context context, Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl) {
            return ExoPlayerFactory.newInstance(context, rendererArr, trackSelector, loadControl);
        }
    }

    static class VisibilityTrackingEvent {
        boolean isTracked;
        int minimumPercentageVisible;
        Integer minimumVisiblePx;
        OnTrackedStrategy strategy;
        int totalQualifiedPlayCounter;
        int totalRequiredPlayTimeMs;

        interface OnTrackedStrategy {
            void execute();
        }

        VisibilityTrackingEvent() {
        }
    }

    static class NativeVideoProgressRunnable extends RepeatingHandlerRunnable {
        private final Context mContext;
        private long mCurrentPosition;
        private long mDuration;
        private ExoPlayer mExoPlayer;
        private ProgressListener mProgressListener;
        private boolean mStopRequested;
        private TextureView mTextureView;
        private final VastVideoConfig mVastVideoConfig;
        private final VisibilityTracker.VisibilityChecker mVisibilityChecker;
        private final List<VisibilityTrackingEvent> mVisibilityTrackingEvents;

        public interface ProgressListener {
            void updateProgress(int i);
        }

        NativeVideoProgressRunnable(Context context, Handler handler, List<VisibilityTrackingEvent> list, VastVideoConfig vastVideoConfig) {
            this(context, handler, list, new VisibilityTracker.VisibilityChecker(), vastVideoConfig);
        }

        NativeVideoProgressRunnable(Context context, Handler handler, List<VisibilityTrackingEvent> list, VisibilityTracker.VisibilityChecker visibilityChecker, VastVideoConfig vastVideoConfig) {
            super(handler);
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(handler);
            Preconditions.checkNotNull(list);
            Preconditions.checkNotNull(vastVideoConfig);
            this.mContext = context.getApplicationContext();
            this.mVisibilityTrackingEvents = list;
            this.mVisibilityChecker = visibilityChecker;
            this.mVastVideoConfig = vastVideoConfig;
            this.mDuration = -1;
            this.mStopRequested = false;
        }

        /* access modifiers changed from: package-private */
        public void setExoPlayer(ExoPlayer exoPlayer) {
            this.mExoPlayer = exoPlayer;
        }

        /* access modifiers changed from: package-private */
        public void setTextureView(TextureView textureView) {
            this.mTextureView = textureView;
        }

        /* access modifiers changed from: package-private */
        public void setProgressListener(ProgressListener progressListener) {
            this.mProgressListener = progressListener;
        }

        /* access modifiers changed from: package-private */
        public void seekTo(long j) {
            this.mCurrentPosition = j;
        }

        /* access modifiers changed from: package-private */
        public long getCurrentPosition() {
            return this.mCurrentPosition;
        }

        /* access modifiers changed from: package-private */
        public long getDuration() {
            return this.mDuration;
        }

        /* access modifiers changed from: package-private */
        public void requestStop() {
            this.mStopRequested = true;
        }

        /* access modifiers changed from: package-private */
        public void checkImpressionTrackers(boolean z) {
            int i = 0;
            for (VisibilityTrackingEvent next : this.mVisibilityTrackingEvents) {
                if (!next.isTracked) {
                    if (!z) {
                        VisibilityTracker.VisibilityChecker visibilityChecker = this.mVisibilityChecker;
                        TextureView textureView = this.mTextureView;
                        if (!visibilityChecker.isVisible(textureView, textureView, next.minimumPercentageVisible, next.minimumVisiblePx)) {
                        }
                    }
                    next.totalQualifiedPlayCounter = (int) (((long) next.totalQualifiedPlayCounter) + this.mUpdateIntervalMillis);
                    if (z || next.totalQualifiedPlayCounter >= next.totalRequiredPlayTimeMs) {
                        next.strategy.execute();
                        next.isTracked = true;
                    }
                }
                i++;
            }
            if (i == this.mVisibilityTrackingEvents.size() && this.mStopRequested) {
                stop();
            }
        }

        public void doWork() {
            ExoPlayer exoPlayer = this.mExoPlayer;
            if (exoPlayer != null && exoPlayer.getPlayWhenReady()) {
                this.mCurrentPosition = this.mExoPlayer.getCurrentPosition();
                this.mDuration = this.mExoPlayer.getDuration();
                checkImpressionTrackers(false);
                ProgressListener progressListener = this.mProgressListener;
                if (progressListener != null) {
                    progressListener.updateProgress((int) ((((float) this.mCurrentPosition) / ((float) this.mDuration)) * 1000.0f));
                }
                List<VastTracker> untriggeredTrackersBefore = this.mVastVideoConfig.getUntriggeredTrackersBefore((int) this.mCurrentPosition, (int) this.mDuration);
                if (!untriggeredTrackersBefore.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    for (VastTracker next : untriggeredTrackersBefore) {
                        if (!next.isTracked()) {
                            arrayList.add(next.getContent());
                            next.setTracked();
                        }
                    }
                    TrackingRequest.makeTrackingHttpRequest((Iterable<String>) arrayList, this.mContext);
                }
            }
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public void setUpdateIntervalMillis(long j) {
            this.mUpdateIntervalMillis = j;
        }
    }
}
