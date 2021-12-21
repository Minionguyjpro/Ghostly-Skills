package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.google.android.exoplayer2.BasePlayer;
import com.google.android.exoplayer2.ExoPlayerImpl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

final class ExoPlayerImpl extends BasePlayer implements ExoPlayer {
    final TrackSelectorResult emptyTrackSelectorResult;
    private final Handler eventHandler;
    private boolean hasPendingPrepare;
    private boolean hasPendingSeek;
    private final ExoPlayerImplInternal internalPlayer;
    private final Handler internalPlayerHandler;
    private final CopyOnWriteArrayList<BasePlayer.ListenerHolder> listeners;
    private int maskingPeriodIndex;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    private MediaSource mediaSource;
    private final ArrayDeque<Runnable> pendingListenerNotifications;
    private int pendingOperationAcks;
    private int pendingSetPlaybackParametersAcks;
    private final Timeline.Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private PlaybackParameters playbackParameters;
    private int playbackSuppressionReason;
    private final Renderer[] renderers;
    private int repeatMode;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;

    public ExoPlayerImpl(Renderer[] rendererArr, TrackSelector trackSelector2, LoadControl loadControl, BandwidthMeter bandwidthMeter, Clock clock, Looper looper) {
        Renderer[] rendererArr2 = rendererArr;
        Log.i("ExoPlayerImpl", "Init " + Integer.toHexString(System.identityHashCode(this)) + " [" + "ExoPlayerLib/2.11.0" + "] [" + Util.DEVICE_DEBUG_INFO + "]");
        Assertions.checkState(rendererArr2.length > 0);
        this.renderers = (Renderer[]) Assertions.checkNotNull(rendererArr);
        this.trackSelector = (TrackSelector) Assertions.checkNotNull(trackSelector2);
        this.playWhenReady = false;
        this.repeatMode = 0;
        this.shuffleModeEnabled = false;
        this.listeners = new CopyOnWriteArrayList<>();
        this.emptyTrackSelectorResult = new TrackSelectorResult(new RendererConfiguration[rendererArr2.length], new TrackSelection[rendererArr2.length], (Object) null);
        this.period = new Timeline.Period();
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.seekParameters = SeekParameters.DEFAULT;
        this.playbackSuppressionReason = 0;
        this.eventHandler = new Handler(looper) {
            public void handleMessage(Message message) {
                ExoPlayerImpl.this.handleEvent(message);
            }
        };
        this.playbackInfo = PlaybackInfo.createDummy(0, this.emptyTrackSelectorResult);
        this.pendingListenerNotifications = new ArrayDeque<>();
        this.internalPlayer = new ExoPlayerImplInternal(rendererArr, trackSelector2, this.emptyTrackSelectorResult, loadControl, bandwidthMeter, this.playWhenReady, this.repeatMode, this.shuffleModeEnabled, this.eventHandler, clock);
        this.internalPlayerHandler = new Handler(this.internalPlayer.getPlaybackLooper());
    }

    public void addListener(Player.EventListener eventListener) {
        this.listeners.addIfAbsent(new BasePlayer.ListenerHolder(eventListener));
    }

    public int getPlaybackState() {
        return this.playbackInfo.playbackState;
    }

    public int getPlaybackSuppressionReason() {
        return this.playbackSuppressionReason;
    }

    public void prepare(MediaSource mediaSource2) {
        prepare(mediaSource2, true, true);
    }

    public void prepare(MediaSource mediaSource2, boolean z, boolean z2) {
        this.mediaSource = mediaSource2;
        PlaybackInfo resetPlaybackInfo = getResetPlaybackInfo(z, z2, true, 2);
        this.hasPendingPrepare = true;
        this.pendingOperationAcks++;
        this.internalPlayer.prepare(mediaSource2, z, z2);
        updatePlaybackInfo(resetPlaybackInfo, false, 4, 1, false);
    }

    public void setPlayWhenReady(boolean z) {
        setPlayWhenReady(z, 0);
    }

    public void setPlayWhenReady(boolean z, int i) {
        boolean isPlaying = isPlaying();
        boolean z2 = this.playWhenReady && this.playbackSuppressionReason == 0;
        boolean z3 = z && i == 0;
        if (z2 != z3) {
            this.internalPlayer.setPlayWhenReady(z3);
        }
        boolean z4 = this.playWhenReady != z;
        boolean z5 = this.playbackSuppressionReason != i;
        this.playWhenReady = z;
        this.playbackSuppressionReason = i;
        boolean isPlaying2 = isPlaying();
        boolean z6 = isPlaying != isPlaying2;
        if (z4 || z5 || z6) {
            notifyListeners((BasePlayer.ListenerInvocation) new BasePlayer.ListenerInvocation(z4, z, this.playbackInfo.playbackState, z5, i, z6, isPlaying2) {
                public final /* synthetic */ boolean f$0;
                public final /* synthetic */ boolean f$1;
                public final /* synthetic */ int f$2;
                public final /* synthetic */ boolean f$3;
                public final /* synthetic */ int f$4;
                public final /* synthetic */ boolean f$5;
                public final /* synthetic */ boolean f$6;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                    this.f$5 = r6;
                    this.f$6 = r7;
                }

                public final void invokeListener(Player.EventListener eventListener) {
                    ExoPlayerImpl.lambda$setPlayWhenReady$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, eventListener);
                }
            });
        }
    }

    static /* synthetic */ void lambda$setPlayWhenReady$0(boolean z, boolean z2, int i, boolean z3, int i2, boolean z4, boolean z5, Player.EventListener eventListener) {
        if (z) {
            eventListener.onPlayerStateChanged(z2, i);
        }
        if (z3) {
            eventListener.onPlaybackSuppressionReasonChanged(i2);
        }
        if (z4) {
            eventListener.onIsPlayingChanged(z5);
        }
    }

    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    public void seekTo(int i, long j) {
        Timeline timeline = this.playbackInfo.timeline;
        if (i < 0 || (!timeline.isEmpty() && i >= timeline.getWindowCount())) {
            throw new IllegalSeekPositionException(timeline, i, j);
        }
        this.hasPendingSeek = true;
        this.pendingOperationAcks++;
        if (isPlayingAd()) {
            Log.w("ExoPlayerImpl", "seekTo ignored because an ad is playing");
            this.eventHandler.obtainMessage(0, 1, -1, this.playbackInfo).sendToTarget();
            return;
        }
        this.maskingWindowIndex = i;
        if (timeline.isEmpty()) {
            this.maskingWindowPositionMs = j == -9223372036854775807L ? 0 : j;
            this.maskingPeriodIndex = 0;
        } else {
            long defaultPositionUs = j == -9223372036854775807L ? timeline.getWindow(i, this.window).getDefaultPositionUs() : C.msToUs(j);
            Pair<Object, Long> periodPosition = timeline.getPeriodPosition(this.window, this.period, i, defaultPositionUs);
            this.maskingWindowPositionMs = C.usToMs(defaultPositionUs);
            this.maskingPeriodIndex = timeline.getIndexOfPeriod(periodPosition.first);
        }
        this.internalPlayer.seekTo(timeline, i, C.msToUs(j));
        notifyListeners((BasePlayer.ListenerInvocation) $$Lambda$ExoPlayerImpl$Or0VmpLdRqfIa3jPOGIz08ZWLAg.INSTANCE);
    }

    public void stop(boolean z) {
        if (z) {
            this.mediaSource = null;
        }
        PlaybackInfo resetPlaybackInfo = getResetPlaybackInfo(z, z, z, 1);
        this.pendingOperationAcks++;
        this.internalPlayer.stop(z);
        updatePlaybackInfo(resetPlaybackInfo, false, 4, 1, false);
    }

    public void release() {
        Log.i("ExoPlayerImpl", "Release " + Integer.toHexString(System.identityHashCode(this)) + " [" + "ExoPlayerLib/2.11.0" + "] [" + Util.DEVICE_DEBUG_INFO + "] [" + ExoPlayerLibraryInfo.registeredModules() + "]");
        this.mediaSource = null;
        this.internalPlayer.release();
        this.eventHandler.removeCallbacksAndMessages((Object) null);
        this.playbackInfo = getResetPlaybackInfo(false, false, false, 1);
    }

    public PlayerMessage createMessage(PlayerMessage.Target target) {
        return new PlayerMessage(this.internalPlayer, target, this.playbackInfo.timeline, getCurrentWindowIndex(), this.internalPlayerHandler);
    }

    public int getCurrentPeriodIndex() {
        if (shouldMaskPosition()) {
            return this.maskingPeriodIndex;
        }
        return this.playbackInfo.timeline.getIndexOfPeriod(this.playbackInfo.periodId.periodUid);
    }

    public int getCurrentWindowIndex() {
        if (shouldMaskPosition()) {
            return this.maskingWindowIndex;
        }
        return this.playbackInfo.timeline.getPeriodByUid(this.playbackInfo.periodId.periodUid, this.period).windowIndex;
    }

    public long getDuration() {
        if (!isPlayingAd()) {
            return getContentDuration();
        }
        MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
        this.playbackInfo.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return C.usToMs(this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup));
    }

    public long getCurrentPosition() {
        if (shouldMaskPosition()) {
            return this.maskingWindowPositionMs;
        }
        if (this.playbackInfo.periodId.isAd()) {
            return C.usToMs(this.playbackInfo.positionUs);
        }
        return periodPositionUsToWindowPositionMs(this.playbackInfo.periodId, this.playbackInfo.positionUs);
    }

    public boolean isPlayingAd() {
        return !shouldMaskPosition() && this.playbackInfo.periodId.isAd();
    }

    public Timeline getCurrentTimeline() {
        return this.playbackInfo.timeline;
    }

    /* access modifiers changed from: package-private */
    public void handleEvent(Message message) {
        int i = message.what;
        boolean z = false;
        if (i == 0) {
            PlaybackInfo playbackInfo2 = (PlaybackInfo) message.obj;
            int i2 = message.arg1;
            if (message.arg2 != -1) {
                z = true;
            }
            handlePlaybackInfo(playbackInfo2, i2, z, message.arg2);
        } else if (i == 1) {
            PlaybackParameters playbackParameters2 = (PlaybackParameters) message.obj;
            if (message.arg1 != 0) {
                z = true;
            }
            handlePlaybackParameters(playbackParameters2, z);
        } else {
            throw new IllegalStateException();
        }
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters2, boolean z) {
        if (z) {
            this.pendingSetPlaybackParametersAcks--;
        }
        if (this.pendingSetPlaybackParametersAcks == 0 && !this.playbackParameters.equals(playbackParameters2)) {
            this.playbackParameters = playbackParameters2;
            notifyListeners((BasePlayer.ListenerInvocation) new BasePlayer.ListenerInvocation() {
                public final void invokeListener(Player.EventListener eventListener) {
                    eventListener.onPlaybackParametersChanged(PlaybackParameters.this);
                }
            });
        }
    }

    private void handlePlaybackInfo(PlaybackInfo playbackInfo2, int i, boolean z, int i2) {
        int i3 = this.pendingOperationAcks - i;
        this.pendingOperationAcks = i3;
        if (i3 == 0) {
            if (playbackInfo2.startPositionUs == -9223372036854775807L) {
                playbackInfo2 = playbackInfo2.copyWithNewPosition(playbackInfo2.periodId, 0, playbackInfo2.contentPositionUs, playbackInfo2.totalBufferedDurationUs);
            }
            PlaybackInfo playbackInfo3 = playbackInfo2;
            if (!this.playbackInfo.timeline.isEmpty() && playbackInfo3.timeline.isEmpty()) {
                this.maskingPeriodIndex = 0;
                this.maskingWindowIndex = 0;
                this.maskingWindowPositionMs = 0;
            }
            int i4 = this.hasPendingPrepare ? 0 : 2;
            boolean z2 = this.hasPendingSeek;
            this.hasPendingPrepare = false;
            this.hasPendingSeek = false;
            updatePlaybackInfo(playbackInfo3, z, i2, i4, z2);
        }
    }

    private PlaybackInfo getResetPlaybackInfo(boolean z, boolean z2, boolean z3, int i) {
        long j;
        long j2 = 0;
        boolean z4 = false;
        if (z) {
            this.maskingWindowIndex = 0;
            this.maskingPeriodIndex = 0;
            this.maskingWindowPositionMs = 0;
        } else {
            this.maskingWindowIndex = getCurrentWindowIndex();
            this.maskingPeriodIndex = getCurrentPeriodIndex();
            this.maskingWindowPositionMs = getCurrentPosition();
        }
        if (z || z2) {
            z4 = true;
        }
        MediaSource.MediaPeriodId dummyFirstMediaPeriodId = z4 ? this.playbackInfo.getDummyFirstMediaPeriodId(this.shuffleModeEnabled, this.window, this.period) : this.playbackInfo.periodId;
        if (!z4) {
            j2 = this.playbackInfo.positionUs;
        }
        long j3 = j2;
        if (z4) {
            j = -9223372036854775807L;
        } else {
            j = this.playbackInfo.contentPositionUs;
        }
        return new PlaybackInfo(z2 ? Timeline.EMPTY : this.playbackInfo.timeline, dummyFirstMediaPeriodId, j3, j, i, z3 ? null : this.playbackInfo.playbackError, false, z2 ? TrackGroupArray.EMPTY : this.playbackInfo.trackGroups, z2 ? this.emptyTrackSelectorResult : this.playbackInfo.trackSelectorResult, dummyFirstMediaPeriodId, j3, 0, j3);
    }

    private void updatePlaybackInfo(PlaybackInfo playbackInfo2, boolean z, int i, int i2, boolean z2) {
        boolean isPlaying = isPlaying();
        PlaybackInfo playbackInfo3 = this.playbackInfo;
        this.playbackInfo = playbackInfo2;
        notifyListeners((Runnable) new PlaybackInfoUpdate(playbackInfo2, playbackInfo3, this.listeners, this.trackSelector, z, i, i2, z2, this.playWhenReady, isPlaying != isPlaying()));
    }

    private void notifyListeners(BasePlayer.ListenerInvocation listenerInvocation) {
        notifyListeners((Runnable) new Runnable(new CopyOnWriteArrayList(this.listeners), listenerInvocation) {
            public final /* synthetic */ CopyOnWriteArrayList f$0;
            public final /* synthetic */ BasePlayer.ListenerInvocation f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void run() {
                ExoPlayerImpl.invokeAll(this.f$0, this.f$1);
            }
        });
    }

    private void notifyListeners(Runnable runnable) {
        boolean z = !this.pendingListenerNotifications.isEmpty();
        this.pendingListenerNotifications.addLast(runnable);
        if (!z) {
            while (!this.pendingListenerNotifications.isEmpty()) {
                this.pendingListenerNotifications.peekFirst().run();
                this.pendingListenerNotifications.removeFirst();
            }
        }
    }

    private long periodPositionUsToWindowPositionMs(MediaSource.MediaPeriodId mediaPeriodId, long j) {
        long usToMs = C.usToMs(j);
        this.playbackInfo.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return usToMs + this.period.getPositionInWindowMs();
    }

    private boolean shouldMaskPosition() {
        return this.playbackInfo.timeline.isEmpty() || this.pendingOperationAcks > 0;
    }

    private static final class PlaybackInfoUpdate implements Runnable {
        private final boolean isLoadingChanged;
        private final boolean isPlayingChanged;
        private final CopyOnWriteArrayList<BasePlayer.ListenerHolder> listenerSnapshot;
        private final boolean playWhenReady;
        private final boolean playbackErrorChanged;
        private final PlaybackInfo playbackInfo;
        private final boolean playbackStateChanged;
        private final boolean positionDiscontinuity;
        private final int positionDiscontinuityReason;
        private final boolean seekProcessed;
        private final int timelineChangeReason;
        private final boolean timelineChanged;
        private final TrackSelector trackSelector;
        private final boolean trackSelectorResultChanged;

        public PlaybackInfoUpdate(PlaybackInfo playbackInfo2, PlaybackInfo playbackInfo3, CopyOnWriteArrayList<BasePlayer.ListenerHolder> copyOnWriteArrayList, TrackSelector trackSelector2, boolean z, int i, int i2, boolean z2, boolean z3, boolean z4) {
            this.playbackInfo = playbackInfo2;
            this.listenerSnapshot = new CopyOnWriteArrayList<>(copyOnWriteArrayList);
            this.trackSelector = trackSelector2;
            this.positionDiscontinuity = z;
            this.positionDiscontinuityReason = i;
            this.timelineChangeReason = i2;
            this.seekProcessed = z2;
            this.playWhenReady = z3;
            this.isPlayingChanged = z4;
            boolean z5 = true;
            this.playbackStateChanged = playbackInfo3.playbackState != playbackInfo2.playbackState;
            this.playbackErrorChanged = (playbackInfo3.playbackError == playbackInfo2.playbackError || playbackInfo2.playbackError == null) ? false : true;
            this.timelineChanged = playbackInfo3.timeline != playbackInfo2.timeline;
            this.isLoadingChanged = playbackInfo3.isLoading != playbackInfo2.isLoading;
            this.trackSelectorResultChanged = playbackInfo3.trackSelectorResult == playbackInfo2.trackSelectorResult ? false : z5;
        }

        public void run() {
            if (this.timelineChanged || this.timelineChangeReason == 0) {
                ExoPlayerImpl.invokeAll(this.listenerSnapshot, new BasePlayer.ListenerInvocation() {
                    public final void invokeListener(Player.EventListener eventListener) {
                        ExoPlayerImpl.PlaybackInfoUpdate.this.lambda$run$0$ExoPlayerImpl$PlaybackInfoUpdate(eventListener);
                    }
                });
            }
            if (this.positionDiscontinuity) {
                ExoPlayerImpl.invokeAll(this.listenerSnapshot, new BasePlayer.ListenerInvocation() {
                    public final void invokeListener(Player.EventListener eventListener) {
                        ExoPlayerImpl.PlaybackInfoUpdate.this.lambda$run$1$ExoPlayerImpl$PlaybackInfoUpdate(eventListener);
                    }
                });
            }
            if (this.playbackErrorChanged) {
                ExoPlayerImpl.invokeAll(this.listenerSnapshot, new BasePlayer.ListenerInvocation() {
                    public final void invokeListener(Player.EventListener eventListener) {
                        ExoPlayerImpl.PlaybackInfoUpdate.this.lambda$run$2$ExoPlayerImpl$PlaybackInfoUpdate(eventListener);
                    }
                });
            }
            if (this.trackSelectorResultChanged) {
                this.trackSelector.onSelectionActivated(this.playbackInfo.trackSelectorResult.info);
                ExoPlayerImpl.invokeAll(this.listenerSnapshot, new BasePlayer.ListenerInvocation() {
                    public final void invokeListener(Player.EventListener eventListener) {
                        ExoPlayerImpl.PlaybackInfoUpdate.this.lambda$run$3$ExoPlayerImpl$PlaybackInfoUpdate(eventListener);
                    }
                });
            }
            if (this.isLoadingChanged) {
                ExoPlayerImpl.invokeAll(this.listenerSnapshot, new BasePlayer.ListenerInvocation() {
                    public final void invokeListener(Player.EventListener eventListener) {
                        ExoPlayerImpl.PlaybackInfoUpdate.this.lambda$run$4$ExoPlayerImpl$PlaybackInfoUpdate(eventListener);
                    }
                });
            }
            if (this.playbackStateChanged) {
                ExoPlayerImpl.invokeAll(this.listenerSnapshot, new BasePlayer.ListenerInvocation() {
                    public final void invokeListener(Player.EventListener eventListener) {
                        ExoPlayerImpl.PlaybackInfoUpdate.this.lambda$run$5$ExoPlayerImpl$PlaybackInfoUpdate(eventListener);
                    }
                });
            }
            if (this.isPlayingChanged) {
                ExoPlayerImpl.invokeAll(this.listenerSnapshot, new BasePlayer.ListenerInvocation() {
                    public final void invokeListener(Player.EventListener eventListener) {
                        ExoPlayerImpl.PlaybackInfoUpdate.this.lambda$run$6$ExoPlayerImpl$PlaybackInfoUpdate(eventListener);
                    }
                });
            }
            if (this.seekProcessed) {
                ExoPlayerImpl.invokeAll(this.listenerSnapshot, $$Lambda$5UFexKQkRNqmel8DaRJEnD1bDjg.INSTANCE);
            }
        }

        public /* synthetic */ void lambda$run$0$ExoPlayerImpl$PlaybackInfoUpdate(Player.EventListener eventListener) {
            eventListener.onTimelineChanged(this.playbackInfo.timeline, this.timelineChangeReason);
        }

        public /* synthetic */ void lambda$run$1$ExoPlayerImpl$PlaybackInfoUpdate(Player.EventListener eventListener) {
            eventListener.onPositionDiscontinuity(this.positionDiscontinuityReason);
        }

        public /* synthetic */ void lambda$run$2$ExoPlayerImpl$PlaybackInfoUpdate(Player.EventListener eventListener) {
            eventListener.onPlayerError(this.playbackInfo.playbackError);
        }

        public /* synthetic */ void lambda$run$3$ExoPlayerImpl$PlaybackInfoUpdate(Player.EventListener eventListener) {
            eventListener.onTracksChanged(this.playbackInfo.trackGroups, this.playbackInfo.trackSelectorResult.selections);
        }

        public /* synthetic */ void lambda$run$4$ExoPlayerImpl$PlaybackInfoUpdate(Player.EventListener eventListener) {
            eventListener.onLoadingChanged(this.playbackInfo.isLoading);
        }

        public /* synthetic */ void lambda$run$5$ExoPlayerImpl$PlaybackInfoUpdate(Player.EventListener eventListener) {
            eventListener.onPlayerStateChanged(this.playWhenReady, this.playbackInfo.playbackState);
        }

        public /* synthetic */ void lambda$run$6$ExoPlayerImpl$PlaybackInfoUpdate(Player.EventListener eventListener) {
            eventListener.onIsPlayingChanged(this.playbackInfo.playbackState == 3);
        }
    }

    /* access modifiers changed from: private */
    public static void invokeAll(CopyOnWriteArrayList<BasePlayer.ListenerHolder> copyOnWriteArrayList, BasePlayer.ListenerInvocation listenerInvocation) {
        Iterator<BasePlayer.ListenerHolder> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            it.next().invoke(listenerInvocation);
        }
    }
}
