package androidx.media2.player.exoplayer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import androidx.core.util.Preconditions;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.CallbackMediaItem;
import androidx.media2.common.FileMediaItem;
import androidx.media2.common.MediaItem;
import androidx.media2.common.SubtitleData;
import androidx.media2.exoplayer.external.C;
import androidx.media2.exoplayer.external.DefaultLoadControl;
import androidx.media2.exoplayer.external.ExoPlaybackException;
import androidx.media2.exoplayer.external.ExoPlayerFactory;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.Player;
import androidx.media2.exoplayer.external.SimpleExoPlayer;
import androidx.media2.exoplayer.external.analytics.AnalyticsCollector;
import androidx.media2.exoplayer.external.audio.AudioAttributes;
import androidx.media2.exoplayer.external.audio.AudioCapabilities;
import androidx.media2.exoplayer.external.audio.AudioListener;
import androidx.media2.exoplayer.external.audio.AudioProcessor;
import androidx.media2.exoplayer.external.audio.DefaultAudioSink;
import androidx.media2.exoplayer.external.decoder.DecoderCounters;
import androidx.media2.exoplayer.external.drm.DrmSessionManager;
import androidx.media2.exoplayer.external.drm.FrameworkMediaCrypto;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.metadata.MetadataOutput;
import androidx.media2.exoplayer.external.source.ConcatenatingMediaSource;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.source.TrackGroupArray;
import androidx.media2.exoplayer.external.trackselection.TrackSelectionArray;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.DefaultBandwidthMeter;
import androidx.media2.exoplayer.external.upstream.DefaultDataSourceFactory;
import androidx.media2.exoplayer.external.util.MimeTypes;
import androidx.media2.exoplayer.external.util.Util;
import androidx.media2.exoplayer.external.video.VideoRendererEventListener;
import androidx.media2.player.MediaPlayer2;
import androidx.media2.player.MediaTimestamp;
import androidx.media2.player.PlaybackParams;
import androidx.media2.player.TimedMetaData;
import androidx.media2.player.exoplayer.TextRenderer;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ExoPlayerWrapper {
    private int mAudioSessionId;
    private DefaultAudioSink mAudioSink;
    private int mAuxEffectId;
    private float mAuxEffectSendLevel;
    private final DefaultBandwidthMeter mBandwidthMeter = new DefaultBandwidthMeter();
    private final Context mContext;
    private final Handler mHandler;
    private boolean mHasAudioAttributes;
    private final Listener mListener;
    private final Looper mLooper;
    private MediaItemQueue mMediaItemQueue;
    private boolean mNewlyPrepared;
    private boolean mPendingSeek;
    private PlaybackParams mPlaybackParams;
    private SimpleExoPlayer mPlayer;
    private Handler mPlayerHandler;
    private final Runnable mPollBufferRunnable = new PollBufferRunnable();
    private boolean mPrepared;
    private boolean mRebuffering;
    private TrackSelector mTrackSelector;
    private int mVideoHeight;
    private int mVideoWidth;

    public interface Listener {
        void onBandwidthSample(MediaItem mediaItem, int i);

        void onBufferingEnded(MediaItem mediaItem);

        void onBufferingStarted(MediaItem mediaItem);

        void onBufferingUpdate(MediaItem mediaItem, int i);

        void onError(MediaItem mediaItem, int i);

        void onLoop(MediaItem mediaItem);

        void onMediaItemEnded(MediaItem mediaItem);

        void onMediaItemStartedAsNext(MediaItem mediaItem);

        void onMediaTimeDiscontinuity(MediaItem mediaItem, MediaTimestamp mediaTimestamp);

        void onMetadataChanged(MediaItem mediaItem);

        void onPlaybackEnded(MediaItem mediaItem);

        void onPrepared(MediaItem mediaItem);

        void onSeekCompleted();

        void onSubtitleData(MediaItem mediaItem, int i, SubtitleData subtitleData);

        void onTimedMetadata(MediaItem mediaItem, TimedMetaData timedMetaData);

        void onVideoRenderingStart(MediaItem mediaItem);

        void onVideoSizeChanged(MediaItem mediaItem, int i, int i2);
    }

    ExoPlayerWrapper(Context context, Listener listener, Looper looper) {
        this.mContext = context.getApplicationContext();
        this.mListener = listener;
        this.mLooper = looper;
        this.mHandler = new Handler(looper);
    }

    public Looper getLooper() {
        return this.mLooper;
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.mMediaItemQueue.setMediaItem((MediaItem) Preconditions.checkNotNull(mediaItem));
    }

    public MediaItem getCurrentMediaItem() {
        return this.mMediaItemQueue.getCurrentMediaItem();
    }

    public void prepare() {
        Preconditions.checkState(!this.mPrepared);
        this.mMediaItemQueue.preparePlayer();
    }

    public void play() {
        this.mNewlyPrepared = false;
        if (this.mPlayer.getPlaybackState() == 4) {
            this.mPlayer.seekTo(0);
        }
        this.mPlayer.setPlayWhenReady(true);
    }

    public void pause() {
        this.mNewlyPrepared = false;
        this.mPlayer.setPlayWhenReady(false);
    }

    public void seekTo(long j, int i) {
        this.mPlayer.setSeekParameters(ExoPlayerUtils.getSeekParameters(i));
        MediaItem currentMediaItem = this.mMediaItemQueue.getCurrentMediaItem();
        if (currentMediaItem != null) {
            boolean z = currentMediaItem.getStartPosition() <= j && currentMediaItem.getEndPosition() >= j;
            Preconditions.checkArgument(z, "Requested seek position is out of range : " + j);
            j -= currentMediaItem.getStartPosition();
        }
        this.mPlayer.seekTo(j);
    }

    public long getCurrentPosition() {
        Preconditions.checkState(getState() != 1001);
        long max = Math.max(0, this.mPlayer.getCurrentPosition());
        MediaItem currentMediaItem = this.mMediaItemQueue.getCurrentMediaItem();
        return currentMediaItem != null ? max + currentMediaItem.getStartPosition() : max;
    }

    public long getDuration() {
        long currentMediaItemDuration = this.mMediaItemQueue.getCurrentMediaItemDuration();
        if (currentMediaItemDuration == -9223372036854775807L) {
            return -1;
        }
        return currentMediaItemDuration;
    }

    public long getBufferedPosition() {
        Preconditions.checkState(getState() != 1001);
        long bufferedPosition = this.mPlayer.getBufferedPosition();
        MediaItem currentMediaItem = this.mMediaItemQueue.getCurrentMediaItem();
        return currentMediaItem != null ? bufferedPosition + currentMediaItem.getStartPosition() : bufferedPosition;
    }

    public int getState() {
        if (hasError()) {
            return 1005;
        }
        if (this.mNewlyPrepared) {
            return 1002;
        }
        int playbackState = this.mPlayer.getPlaybackState();
        boolean playWhenReady = this.mPlayer.getPlayWhenReady();
        if (playbackState == 1) {
            return 1001;
        }
        if (playbackState == 2) {
            return 1003;
        }
        if (playbackState == 3) {
            return playWhenReady ? 1004 : 1003;
        }
        if (playbackState == 4) {
            return 1003;
        }
        throw new IllegalStateException();
    }

    public void skipToNext() {
        this.mMediaItemQueue.skipToNext();
    }

    public void setNextMediaItem(MediaItem mediaItem) {
        if (this.mMediaItemQueue.isEmpty()) {
            if (mediaItem instanceof FileMediaItem) {
                FileMediaItem fileMediaItem = (FileMediaItem) mediaItem;
                fileMediaItem.increaseRefCount();
                fileMediaItem.decreaseRefCount();
            }
            throw new IllegalStateException();
        }
        this.mMediaItemQueue.setNextMediaItems(Collections.singletonList(mediaItem));
    }

    public void setAudioAttributes(AudioAttributesCompat audioAttributesCompat) {
        this.mHasAudioAttributes = true;
        this.mPlayer.setAudioAttributes(ExoPlayerUtils.getAudioAttributes(audioAttributesCompat));
        int i = this.mAudioSessionId;
        if (i != 0) {
            updatePlayerAudioSessionId(this.mPlayerHandler, this.mAudioSink, i);
        }
    }

    public AudioAttributesCompat getAudioAttributes() {
        if (this.mHasAudioAttributes) {
            return ExoPlayerUtils.getAudioAttributesCompat(this.mPlayer.getAudioAttributes());
        }
        return null;
    }

    public void setPlaybackParams(PlaybackParams playbackParams) {
        this.mPlaybackParams = playbackParams;
        this.mPlayer.setPlaybackParameters(ExoPlayerUtils.getPlaybackParameters(playbackParams));
        if (getState() == 1004) {
            this.mListener.onMediaTimeDiscontinuity(getCurrentMediaItem(), getTimestamp());
        }
    }

    public PlaybackParams getPlaybackParams() {
        return this.mPlaybackParams;
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public void setSurface(Surface surface) {
        this.mPlayer.setVideoSurface(surface);
    }

    public void setVolume(float f) {
        this.mPlayer.setVolume(f);
    }

    public float getVolume() {
        return this.mPlayer.getVolume();
    }

    public List<MediaPlayer2.TrackInfo> getTrackInfo() {
        return this.mTrackSelector.getTrackInfos();
    }

    public int getSelectedTrack(int i) {
        return this.mTrackSelector.getSelectedTrack(i);
    }

    public void selectTrack(int i) {
        this.mTrackSelector.selectTrack(i);
    }

    public void deselectTrack(int i) {
        this.mTrackSelector.deselectTrack(i);
    }

    public MediaTimestamp getTimestamp() {
        long j;
        if (this.mPlayer.getPlaybackState() == 1) {
            j = 0;
        } else {
            j = C.msToUs(getCurrentPosition());
        }
        return new MediaTimestamp(j, System.nanoTime(), (this.mPlayer.getPlaybackState() != 3 || !this.mPlayer.getPlayWhenReady()) ? 0.0f : this.mPlaybackParams.getSpeed().floatValue());
    }

    public void reset() {
        SimpleExoPlayer simpleExoPlayer = this.mPlayer;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);
            if (getState() != 1001) {
                this.mListener.onMediaTimeDiscontinuity(getCurrentMediaItem(), getTimestamp());
            }
            this.mPlayer.release();
            this.mMediaItemQueue.clear();
        }
        ComponentListener componentListener = new ComponentListener();
        this.mAudioSink = new DefaultAudioSink(AudioCapabilities.getCapabilities(this.mContext), new AudioProcessor[0]);
        TextRenderer textRenderer = new TextRenderer(componentListener);
        this.mTrackSelector = new TrackSelector(textRenderer);
        Context context = this.mContext;
        this.mPlayer = ExoPlayerFactory.newSimpleInstance(context, new RenderersFactory(context, this.mAudioSink, textRenderer), this.mTrackSelector.getPlayerTrackSelector(), new DefaultLoadControl(), (DrmSessionManager<FrameworkMediaCrypto>) null, this.mBandwidthMeter, new AnalyticsCollector.Factory(), this.mLooper);
        this.mPlayerHandler = new Handler(this.mPlayer.getPlaybackLooper());
        this.mMediaItemQueue = new MediaItemQueue(this.mContext, this.mPlayer, this.mListener);
        this.mPlayer.addListener(componentListener);
        this.mPlayer.setVideoDebugListener(componentListener);
        this.mPlayer.addMetadataOutput(componentListener);
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mPrepared = false;
        this.mNewlyPrepared = false;
        this.mRebuffering = false;
        this.mPendingSeek = false;
        this.mHasAudioAttributes = false;
        this.mAudioSessionId = 0;
        this.mAuxEffectId = 0;
        this.mAuxEffectSendLevel = 0.0f;
        this.mPlaybackParams = new PlaybackParams.Builder().setSpeed(1.0f).setPitch(1.0f).setAudioFallbackMode(0).build();
    }

    public void close() {
        if (this.mPlayer != null) {
            this.mHandler.removeCallbacks(this.mPollBufferRunnable);
            this.mPlayer.release();
            this.mPlayer = null;
            this.mMediaItemQueue.clear();
            this.mHasAudioAttributes = false;
        }
    }

    public boolean hasError() {
        return this.mPlayer.getPlaybackError() != null;
    }

    /* access modifiers changed from: package-private */
    public void handleVideoSizeChanged(int i, int i2, float f) {
        if (f != 1.0f) {
            this.mVideoWidth = (int) (f * ((float) i));
        } else {
            this.mVideoWidth = i;
        }
        this.mVideoHeight = i2;
        this.mListener.onVideoSizeChanged(this.mMediaItemQueue.getCurrentMediaItem(), i, i2);
    }

    /* access modifiers changed from: package-private */
    public void handleRenderedFirstFrame() {
        this.mListener.onVideoRenderingStart(this.mMediaItemQueue.getCurrentMediaItem());
    }

    /* access modifiers changed from: package-private */
    public void handlePlayerStateChanged(boolean z, int i) {
        this.mListener.onMediaTimeDiscontinuity(getCurrentMediaItem(), getTimestamp());
        if (i != 3 || !z) {
            maybeUpdateTimerForStopped();
        } else {
            maybeUpdateTimerForPlaying();
        }
        if (i == 3 || i == 2) {
            this.mHandler.post(this.mPollBufferRunnable);
        } else {
            this.mHandler.removeCallbacks(this.mPollBufferRunnable);
        }
        if (i == 1) {
            return;
        }
        if (i == 2) {
            maybeNotifyBufferingEvents();
        } else if (i == 3) {
            maybeNotifyReadyEvents();
        } else if (i == 4) {
            maybeNotifyEndedEvents();
        } else {
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: package-private */
    public void handleTextRendererChannelAvailable(int i, int i2) {
        this.mTrackSelector.handleTextRendererChannelAvailable(i, i2);
        if (this.mTrackSelector.hasPendingMetadataUpdate()) {
            this.mListener.onMetadataChanged(getCurrentMediaItem());
        }
    }

    /* access modifiers changed from: package-private */
    public void handlePlayerTracksChanged() {
        this.mTrackSelector.handlePlayerTracksChanged(this.mPlayer);
        if (this.mTrackSelector.hasPendingMetadataUpdate()) {
            this.mListener.onMetadataChanged(getCurrentMediaItem());
        }
    }

    /* access modifiers changed from: package-private */
    public void handleSeekProcessed() {
        if (getCurrentMediaItem() == null) {
            this.mListener.onSeekCompleted();
            return;
        }
        this.mPendingSeek = true;
        if (this.mPlayer.getPlaybackState() == 3) {
            maybeNotifyReadyEvents();
        }
    }

    /* access modifiers changed from: package-private */
    public void handlePositionDiscontinuity(int i) {
        this.mListener.onMediaTimeDiscontinuity(getCurrentMediaItem(), getTimestamp());
        this.mMediaItemQueue.onPositionDiscontinuity(i == 0);
    }

    /* access modifiers changed from: package-private */
    public void handlePlayerError(ExoPlaybackException exoPlaybackException) {
        this.mListener.onMediaTimeDiscontinuity(getCurrentMediaItem(), getTimestamp());
        this.mListener.onError(getCurrentMediaItem(), ExoPlayerUtils.getError(exoPlaybackException));
    }

    /* access modifiers changed from: package-private */
    public void handleAudioSessionId(int i) {
        this.mAudioSessionId = i;
    }

    /* access modifiers changed from: package-private */
    public void handleSubtitleData(byte[] bArr, long j) {
        int selectedTrack = this.mTrackSelector.getSelectedTrack(4);
        this.mListener.onSubtitleData(getCurrentMediaItem(), selectedTrack, new SubtitleData(j, 0, bArr));
    }

    /* access modifiers changed from: package-private */
    public void handleMetadata(Metadata metadata) {
        int length = metadata.length();
        for (int i = 0; i < length; i++) {
            ByteArrayFrame byteArrayFrame = (ByteArrayFrame) metadata.get(i);
            this.mListener.onTimedMetadata(getCurrentMediaItem(), new TimedMetaData(byteArrayFrame.mTimestamp, byteArrayFrame.mData));
        }
    }

    /* access modifiers changed from: package-private */
    public void updateBufferingAndScheduleNextPollBuffer() {
        if (this.mMediaItemQueue.getCurrentMediaItemIsRemote()) {
            this.mListener.onBufferingUpdate(getCurrentMediaItem(), this.mPlayer.getBufferedPercentage());
        }
        this.mHandler.removeCallbacks(this.mPollBufferRunnable);
        this.mHandler.postDelayed(this.mPollBufferRunnable, 1000);
    }

    private void maybeUpdateTimerForPlaying() {
        this.mMediaItemQueue.onPlaying();
    }

    private void maybeUpdateTimerForStopped() {
        this.mMediaItemQueue.onStopped();
    }

    private void maybeNotifyBufferingEvents() {
        if (this.mPrepared && !this.mRebuffering) {
            this.mRebuffering = true;
            if (this.mMediaItemQueue.getCurrentMediaItemIsRemote()) {
                this.mListener.onBandwidthSample(getCurrentMediaItem(), (int) (this.mBandwidthMeter.getBitrateEstimate() / 1000));
            }
            this.mListener.onBufferingStarted(getCurrentMediaItem());
        }
    }

    private void maybeNotifyReadyEvents() {
        MediaItem currentMediaItem = this.mMediaItemQueue.getCurrentMediaItem();
        boolean z = !this.mPrepared;
        boolean z2 = this.mPendingSeek;
        if (z) {
            this.mPrepared = true;
            this.mNewlyPrepared = true;
            this.mMediaItemQueue.onPositionDiscontinuity(false);
            this.mListener.onPrepared(currentMediaItem);
        } else if (z2) {
            this.mPendingSeek = false;
            this.mListener.onSeekCompleted();
        }
        if (this.mRebuffering) {
            this.mRebuffering = false;
            if (this.mMediaItemQueue.getCurrentMediaItemIsRemote()) {
                this.mListener.onBandwidthSample(getCurrentMediaItem(), (int) (this.mBandwidthMeter.getBitrateEstimate() / 1000));
            }
            this.mListener.onBufferingEnded(getCurrentMediaItem());
        }
    }

    private void maybeNotifyEndedEvents() {
        if (this.mPendingSeek) {
            this.mPendingSeek = false;
            this.mListener.onSeekCompleted();
        }
        if (this.mPlayer.getPlayWhenReady()) {
            this.mMediaItemQueue.onPlayerEnded();
            this.mPlayer.setPlayWhenReady(false);
        }
    }

    final class ComponentListener extends Player.DefaultEventListener implements AudioListener, MetadataOutput, VideoRendererEventListener, TextRenderer.Output {
        public void onAudioAttributesChanged(AudioAttributes audioAttributes) {
        }

        public void onDroppedFrames(int i, long j) {
        }

        public void onVideoDecoderInitialized(String str, long j, long j2) {
        }

        public void onVideoDisabled(DecoderCounters decoderCounters) {
        }

        public void onVideoEnabled(DecoderCounters decoderCounters) {
        }

        public void onVolumeChanged(float f) {
        }

        ComponentListener() {
        }

        public void onPlayerStateChanged(boolean z, int i) {
            ExoPlayerWrapper.this.handlePlayerStateChanged(z, i);
        }

        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            ExoPlayerWrapper.this.handlePlayerTracksChanged();
        }

        public void onSeekProcessed() {
            ExoPlayerWrapper.this.handleSeekProcessed();
        }

        public void onPositionDiscontinuity(int i) {
            ExoPlayerWrapper.this.handlePositionDiscontinuity(i);
        }

        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
            ExoPlayerWrapper.this.handlePlayerError(exoPlaybackException);
        }

        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            ExoPlayerWrapper.this.handleVideoSizeChanged(i, i2, f);
        }

        public void onVideoInputFormatChanged(Format format) {
            if (MimeTypes.isVideo(format.sampleMimeType)) {
                ExoPlayerWrapper.this.handleVideoSizeChanged(format.width, format.height, format.pixelWidthHeightRatio);
            }
        }

        public void onRenderedFirstFrame(Surface surface) {
            ExoPlayerWrapper.this.handleRenderedFirstFrame();
        }

        public void onAudioSessionId(int i) {
            ExoPlayerWrapper.this.handleAudioSessionId(i);
        }

        public void onCcData(byte[] bArr, long j) {
            ExoPlayerWrapper.this.handleSubtitleData(bArr, j);
        }

        public void onChannelAvailable(int i, int i2) {
            ExoPlayerWrapper.this.handleTextRendererChannelAvailable(i, i2);
        }

        public void onMetadata(Metadata metadata) {
            ExoPlayerWrapper.this.handleMetadata(metadata);
        }
    }

    final class PollBufferRunnable implements Runnable {
        PollBufferRunnable() {
        }

        public void run() {
            ExoPlayerWrapper.this.updateBufferingAndScheduleNextPollBuffer();
        }
    }

    private static void updatePlayerAudioSessionId(Handler handler, final DefaultAudioSink defaultAudioSink, final int i) {
        handler.post(new Runnable() {
            public void run() {
                defaultAudioSink.setAudioSessionId(i);
            }
        });
    }

    private static final class MediaItemInfo {
        final DurationProvidingMediaSource mDurationProvidingMediaSource;
        final boolean mIsRemote;
        final MediaItem mMediaItem;

        MediaItemInfo(MediaItem mediaItem, DurationProvidingMediaSource durationProvidingMediaSource, boolean z) {
            this.mMediaItem = mediaItem;
            this.mDurationProvidingMediaSource = durationProvidingMediaSource;
            this.mIsRemote = z;
        }
    }

    private static final class FileDescriptorRegistry {
        private final Map<FileDescriptor, Entry> mEntries = new HashMap();

        private static final class Entry {
            public final Object mLock = new Object();
            public int mMediaItemCount;

            Entry() {
            }
        }

        FileDescriptorRegistry() {
        }

        public Object registerMediaItemAndGetLock(FileDescriptor fileDescriptor) {
            if (!this.mEntries.containsKey(fileDescriptor)) {
                this.mEntries.put(fileDescriptor, new Entry());
            }
            Entry entry = (Entry) Preconditions.checkNotNull(this.mEntries.get(fileDescriptor));
            entry.mMediaItemCount++;
            return entry.mLock;
        }

        public void unregisterMediaItem(FileDescriptor fileDescriptor) {
            Entry entry = (Entry) Preconditions.checkNotNull(this.mEntries.get(fileDescriptor));
            int i = entry.mMediaItemCount - 1;
            entry.mMediaItemCount = i;
            if (i == 0) {
                this.mEntries.remove(fileDescriptor);
            }
        }
    }

    private static final class MediaItemQueue {
        private final ConcatenatingMediaSource mConcatenatingMediaSource = new ConcatenatingMediaSource(new MediaSource[0]);
        private final Context mContext;
        private long mCurrentMediaItemPlayingTimeUs;
        private final DataSource.Factory mDataSourceFactory;
        private final FileDescriptorRegistry mFileDescriptorRegistry = new FileDescriptorRegistry();
        private final Listener mListener;
        private final ArrayDeque<MediaItemInfo> mMediaItemInfos = new ArrayDeque<>();
        private final SimpleExoPlayer mPlayer;
        private long mStartPlayingTimeNs = -1;

        MediaItemQueue(Context context, SimpleExoPlayer simpleExoPlayer, Listener listener) {
            this.mContext = context;
            this.mPlayer = simpleExoPlayer;
            this.mListener = listener;
            this.mDataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "MediaPlayer2"));
        }

        public void clear() {
            while (!this.mMediaItemInfos.isEmpty()) {
                releaseMediaItem(this.mMediaItemInfos.remove());
            }
        }

        public boolean isEmpty() {
            return this.mConcatenatingMediaSource.getSize() == 0;
        }

        public void setMediaItem(MediaItem mediaItem) {
            clear();
            this.mConcatenatingMediaSource.clear();
            setNextMediaItems(Collections.singletonList(mediaItem));
        }

        public void setNextMediaItems(List<MediaItem> list) {
            int size = this.mConcatenatingMediaSource.getSize();
            if (size > 1) {
                this.mConcatenatingMediaSource.removeMediaSourceRange(1, size);
                while (this.mMediaItemInfos.size() > 1) {
                    releaseMediaItem(this.mMediaItemInfos.removeLast());
                }
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (MediaItem next : list) {
                if (next == null) {
                    this.mListener.onError((MediaItem) null, 1);
                    return;
                }
                appendMediaItem(next, this.mMediaItemInfos, arrayList);
            }
            this.mConcatenatingMediaSource.addMediaSources(arrayList);
        }

        public void preparePlayer() {
            this.mPlayer.prepare(this.mConcatenatingMediaSource);
        }

        public MediaItem getCurrentMediaItem() {
            if (this.mMediaItemInfos.isEmpty()) {
                return null;
            }
            return this.mMediaItemInfos.peekFirst().mMediaItem;
        }

        public long getCurrentMediaItemDuration() {
            DurationProvidingMediaSource durationProvidingMediaSource = this.mMediaItemInfos.peekFirst().mDurationProvidingMediaSource;
            if (durationProvidingMediaSource != null) {
                return durationProvidingMediaSource.getDurationMs();
            }
            return this.mPlayer.getDuration();
        }

        public boolean getCurrentMediaItemIsRemote() {
            return !this.mMediaItemInfos.isEmpty() && this.mMediaItemInfos.peekFirst().mIsRemote;
        }

        public void skipToNext() {
            releaseMediaItem(this.mMediaItemInfos.removeFirst());
            this.mConcatenatingMediaSource.removeMediaSource(0);
        }

        public void onPlaying() {
            if (this.mStartPlayingTimeNs == -1) {
                this.mStartPlayingTimeNs = System.nanoTime();
            }
        }

        public void onStopped() {
            if (this.mStartPlayingTimeNs != -1) {
                this.mCurrentMediaItemPlayingTimeUs += ((System.nanoTime() - this.mStartPlayingTimeNs) + 500) / 1000;
                this.mStartPlayingTimeNs = -1;
            }
        }

        public void onPlayerEnded() {
            MediaItem currentMediaItem = getCurrentMediaItem();
            this.mListener.onMediaItemEnded(currentMediaItem);
            this.mListener.onPlaybackEnded(currentMediaItem);
        }

        public void onPositionDiscontinuity(boolean z) {
            MediaItem currentMediaItem = getCurrentMediaItem();
            if (z && this.mPlayer.getRepeatMode() != 0) {
                this.mListener.onLoop(currentMediaItem);
            }
            int currentWindowIndex = this.mPlayer.getCurrentWindowIndex();
            if (currentWindowIndex > 0) {
                if (z) {
                    this.mListener.onMediaItemEnded(getCurrentMediaItem());
                }
                for (int i = 0; i < currentWindowIndex; i++) {
                    releaseMediaItem(this.mMediaItemInfos.removeFirst());
                }
                if (z) {
                    this.mListener.onMediaItemStartedAsNext(getCurrentMediaItem());
                }
                this.mConcatenatingMediaSource.removeMediaSourceRange(0, currentWindowIndex);
                this.mCurrentMediaItemPlayingTimeUs = 0;
                this.mStartPlayingTimeNs = -1;
                if (this.mPlayer.getPlaybackState() == 3) {
                    onPlaying();
                }
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: androidx.media2.exoplayer.external.source.MediaSource} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: androidx.media2.exoplayer.external.source.ClippingMediaSource} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: androidx.media2.exoplayer.external.source.ClippingMediaSource} */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0046, code lost:
            if (r6 != 576460752303423487L) goto L_0x0048;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void appendMediaItem(androidx.media2.common.MediaItem r21, java.util.Collection<androidx.media2.player.exoplayer.ExoPlayerWrapper.MediaItemInfo> r22, java.util.Collection<androidx.media2.exoplayer.external.source.MediaSource> r23) {
            /*
                r20 = this;
                r0 = r20
                r1 = r21
                androidx.media2.exoplayer.external.upstream.DataSource$Factory r2 = r0.mDataSourceFactory
                boolean r3 = r1 instanceof androidx.media2.common.FileMediaItem
                if (r3 == 0) goto L_0x002a
                r2 = r1
                androidx.media2.common.FileMediaItem r2 = (androidx.media2.common.FileMediaItem) r2
                r2.increaseRefCount()
                android.os.ParcelFileDescriptor r3 = r2.getParcelFileDescriptor()
                java.io.FileDescriptor r4 = r3.getFileDescriptor()
                long r5 = r2.getFileDescriptorOffset()
                long r7 = r2.getFileDescriptorLength()
                androidx.media2.player.exoplayer.ExoPlayerWrapper$FileDescriptorRegistry r2 = r0.mFileDescriptorRegistry
                java.lang.Object r9 = r2.registerMediaItemAndGetLock(r4)
                androidx.media2.exoplayer.external.upstream.DataSource$Factory r2 = androidx.media2.player.exoplayer.FileDescriptorDataSource.getFactory(r4, r5, r7, r9)
            L_0x002a:
                android.content.Context r3 = r0.mContext
                androidx.media2.exoplayer.external.source.MediaSource r2 = androidx.media2.player.exoplayer.ExoPlayerUtils.createUnclippedMediaSource(r3, r2, r1)
                r3 = 0
                long r4 = r21.getStartPosition()
                long r6 = r21.getEndPosition()
                r8 = 0
                int r10 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r10 != 0) goto L_0x0048
                r8 = 576460752303423487(0x7ffffffffffffff, double:3.7857669957336787E-270)
                int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r10 == 0) goto L_0x0062
            L_0x0048:
                androidx.media2.player.exoplayer.DurationProvidingMediaSource r3 = new androidx.media2.player.exoplayer.DurationProvidingMediaSource
                r3.<init>(r2)
                androidx.media2.exoplayer.external.source.ClippingMediaSource r2 = new androidx.media2.exoplayer.external.source.ClippingMediaSource
                long r13 = androidx.media2.exoplayer.external.C.msToUs(r4)
                long r15 = androidx.media2.exoplayer.external.C.msToUs(r6)
                r17 = 0
                r18 = 0
                r19 = 1
                r11 = r2
                r12 = r3
                r11.<init>(r12, r13, r15, r17, r18, r19)
            L_0x0062:
                boolean r4 = r1 instanceof androidx.media2.common.UriMediaItem
                if (r4 == 0) goto L_0x0075
                r4 = r1
                androidx.media2.common.UriMediaItem r4 = (androidx.media2.common.UriMediaItem) r4
                android.net.Uri r4 = r4.getUri()
                boolean r4 = androidx.media2.exoplayer.external.util.Util.isLocalFileUri(r4)
                if (r4 != 0) goto L_0x0075
                r4 = 1
                goto L_0x0076
            L_0x0075:
                r4 = 0
            L_0x0076:
                r5 = r23
                r5.add(r2)
                androidx.media2.player.exoplayer.ExoPlayerWrapper$MediaItemInfo r2 = new androidx.media2.player.exoplayer.ExoPlayerWrapper$MediaItemInfo
                r2.<init>(r1, r3, r4)
                r1 = r22
                r1.add(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.exoplayer.ExoPlayerWrapper.MediaItemQueue.appendMediaItem(androidx.media2.common.MediaItem, java.util.Collection, java.util.Collection):void");
        }

        private void releaseMediaItem(MediaItemInfo mediaItemInfo) {
            MediaItem mediaItem = mediaItemInfo.mMediaItem;
            try {
                if (mediaItem instanceof FileMediaItem) {
                    this.mFileDescriptorRegistry.unregisterMediaItem(((FileMediaItem) mediaItem).getParcelFileDescriptor().getFileDescriptor());
                    ((FileMediaItem) mediaItem).decreaseRefCount();
                } else if (mediaItem instanceof CallbackMediaItem) {
                    ((CallbackMediaItem) mediaItemInfo.mMediaItem).getDataSourceCallback().close();
                }
            } catch (IOException e) {
                Log.w("ExoPlayerWrapper", "Error releasing media item " + mediaItem, e);
            }
        }
    }
}
