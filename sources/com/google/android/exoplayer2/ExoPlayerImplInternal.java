package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.google.android.exoplayer2.DefaultMediaClock;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

final class ExoPlayerImplInternal implements Handler.Callback, DefaultMediaClock.PlaybackParameterListener, PlayerMessage.Sender, MediaPeriod.Callback, MediaSource.MediaSourceCaller, TrackSelector.InvalidationListener {
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private boolean deliverPendingMessageAtStartPositionRequired;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private Renderer[] enabledRenderers;
    private final Handler eventHandler;
    private boolean foregroundMode;
    private final HandlerWrapper handler;
    private final HandlerThread internalPlaybackThread;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private MediaSource mediaSource;
    private int nextPendingMessageIndex;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private int pendingPrepareCount;
    private final Timeline.Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private final PlaybackInfoUpdate playbackInfoUpdate;
    private final MediaPeriodQueue queue = new MediaPeriodQueue();
    private boolean rebuffering;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private int repeatMode;
    private final boolean retainBackBufferFromKeyframe;
    private SeekParameters seekParameters;
    private boolean shouldContinueLoading;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Timeline.Window window;

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector2, TrackSelectorResult trackSelectorResult, LoadControl loadControl2, BandwidthMeter bandwidthMeter2, boolean z, int i, boolean z2, Handler handler2, Clock clock2) {
        this.renderers = rendererArr;
        this.trackSelector = trackSelector2;
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.loadControl = loadControl2;
        this.bandwidthMeter = bandwidthMeter2;
        this.playWhenReady = z;
        this.repeatMode = i;
        this.shuffleModeEnabled = z2;
        this.eventHandler = handler2;
        this.clock = clock2;
        this.backBufferDurationUs = loadControl2.getBackBufferDurationUs();
        this.retainBackBufferFromKeyframe = loadControl2.retainBackBufferFromKeyframe();
        this.seekParameters = SeekParameters.DEFAULT;
        this.playbackInfo = PlaybackInfo.createDummy(-9223372036854775807L, trackSelectorResult);
        this.playbackInfoUpdate = new PlaybackInfoUpdate();
        this.rendererCapabilities = new RendererCapabilities[rendererArr.length];
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            rendererArr[i2].setIndex(i2);
            this.rendererCapabilities[i2] = rendererArr[i2].getCapabilities();
        }
        this.mediaClock = new DefaultMediaClock(this, clock2);
        this.pendingMessages = new ArrayList<>();
        this.enabledRenderers = new Renderer[0];
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        trackSelector2.init(this, bandwidthMeter2);
        HandlerThread handlerThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.internalPlaybackThread = handlerThread;
        handlerThread.start();
        this.handler = clock2.createHandler(this.internalPlaybackThread.getLooper(), this);
        this.deliverPendingMessageAtStartPositionRequired = true;
    }

    public void prepare(MediaSource mediaSource2, boolean z, boolean z2) {
        this.handler.obtainMessage(0, z ? 1 : 0, z2 ? 1 : 0, mediaSource2).sendToTarget();
    }

    public void setPlayWhenReady(boolean z) {
        this.handler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
    }

    public void seekTo(Timeline timeline, int i, long j) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, i, j)).sendToTarget();
    }

    public void stop(boolean z) {
        this.handler.obtainMessage(6, z ? 1 : 0, 0).sendToTarget();
    }

    public synchronized void sendMessage(PlayerMessage playerMessage) {
        if (!this.released) {
            if (this.internalPlaybackThread.isAlive()) {
                this.handler.obtainMessage(15, playerMessage).sendToTarget();
                return;
            }
        }
        Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
        playerMessage.markAsProcessed(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void release() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.released     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x002a
            android.os.HandlerThread r0 = r2.internalPlaybackThread     // Catch:{ all -> 0x002c }
            boolean r0 = r0.isAlive()     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x000e
            goto L_0x002a
        L_0x000e:
            com.google.android.exoplayer2.util.HandlerWrapper r0 = r2.handler     // Catch:{ all -> 0x002c }
            r1 = 7
            r0.sendEmptyMessage(r1)     // Catch:{ all -> 0x002c }
            r0 = 0
        L_0x0015:
            boolean r1 = r2.released     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x001f
            r2.wait()     // Catch:{ InterruptedException -> 0x001d }
            goto L_0x0015
        L_0x001d:
            r0 = 1
            goto L_0x0015
        L_0x001f:
            if (r0 == 0) goto L_0x0028
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x002c }
            r0.interrupt()     // Catch:{ all -> 0x002c }
        L_0x0028:
            monitor-exit(r2)
            return
        L_0x002a:
            monitor-exit(r2)
            return
        L_0x002c:
            r0 = move-exception
            monitor-exit(r2)
            goto L_0x0030
        L_0x002f:
            throw r0
        L_0x0030:
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.release():void");
    }

    public Looper getPlaybackLooper() {
        return this.internalPlaybackThread.getLooper();
    }

    public void onSourceInfoRefreshed(MediaSource mediaSource2, Timeline timeline) {
        this.handler.obtainMessage(8, new MediaSourceRefreshInfo(mediaSource2, timeline)).sendToTarget();
    }

    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(10, mediaPeriod).sendToTarget();
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        sendPlaybackParametersChangedInternal(playbackParameters, false);
    }

    public boolean handleMessage(Message message) {
        ExoPlaybackException exoPlaybackException;
        try {
            switch (message.what) {
                case 0:
                    prepareInternal((MediaSource) message.obj, message.arg1 != 0, message.arg2 != 0);
                    break;
                case 1:
                    setPlayWhenReadyInternal(message.arg1 != 0);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) message.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) message.obj);
                    break;
                case 6:
                    stopInternal(false, message.arg1 != 0, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handleSourceInfoRefreshed((MediaSourceRefreshInfo) message.obj);
                    break;
                case 9:
                    handlePeriodPrepared((MediaPeriod) message.obj);
                    break;
                case 10:
                    handleContinueLoadingRequested((MediaPeriod) message.obj);
                    break;
                case 11:
                    reselectTracksInternal();
                    break;
                case 12:
                    setRepeatModeInternal(message.arg1);
                    break;
                case 13:
                    setShuffleModeEnabledInternal(message.arg1 != 0);
                    break;
                case 14:
                    setForegroundModeInternal(message.arg1 != 0, (AtomicBoolean) message.obj);
                    break;
                case 15:
                    sendMessageInternal((PlayerMessage) message.obj);
                    break;
                case 16:
                    sendMessageToTargetThread((PlayerMessage) message.obj);
                    break;
                case 17:
                    handlePlaybackParameters((PlaybackParameters) message.obj, message.arg1 != 0);
                    break;
                default:
                    return false;
            }
            maybeNotifyPlaybackInfoChanged();
        } catch (ExoPlaybackException e) {
            Log.e("ExoPlayerImplInternal", getExoPlaybackExceptionMessage(e), e);
            stopInternal(true, false, false);
            this.playbackInfo = this.playbackInfo.copyWithPlaybackError(e);
            maybeNotifyPlaybackInfoChanged();
        } catch (IOException e2) {
            Log.e("ExoPlayerImplInternal", "Source error.", e2);
            stopInternal(false, false, false);
            this.playbackInfo = this.playbackInfo.copyWithPlaybackError(ExoPlaybackException.createForSource(e2));
            maybeNotifyPlaybackInfoChanged();
        } catch (OutOfMemoryError | RuntimeException e3) {
            Log.e("ExoPlayerImplInternal", "Internal runtime error.", e3);
            if (e3 instanceof OutOfMemoryError) {
                exoPlaybackException = ExoPlaybackException.createForOutOfMemoryError((OutOfMemoryError) e3);
            } else {
                exoPlaybackException = ExoPlaybackException.createForUnexpected((RuntimeException) e3);
            }
            stopInternal(true, false, false);
            this.playbackInfo = this.playbackInfo.copyWithPlaybackError(exoPlaybackException);
            maybeNotifyPlaybackInfoChanged();
        }
        return true;
    }

    private String getExoPlaybackExceptionMessage(ExoPlaybackException exoPlaybackException) {
        if (exoPlaybackException.type != 1) {
            return "Playback error.";
        }
        return "Renderer error: index=" + exoPlaybackException.rendererIndex + ", type=" + Util.getTrackTypeString(this.renderers[exoPlaybackException.rendererIndex].getTrackType()) + ", format=" + exoPlaybackException.rendererFormat + ", rendererSupport=" + RendererCapabilities.CC.getFormatSupportString(exoPlaybackException.rendererFormatSupport);
    }

    private void setState(int i) {
        if (this.playbackInfo.playbackState != i) {
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(i);
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        if (this.playbackInfoUpdate.hasPendingUpdate(this.playbackInfo)) {
            this.eventHandler.obtainMessage(0, this.playbackInfoUpdate.operationAcks, this.playbackInfoUpdate.positionDiscontinuity ? this.playbackInfoUpdate.discontinuityReason : -1, this.playbackInfo).sendToTarget();
            this.playbackInfoUpdate.reset(this.playbackInfo);
        }
    }

    private void prepareInternal(MediaSource mediaSource2, boolean z, boolean z2) {
        this.pendingPrepareCount++;
        resetInternal(false, true, z, z2, true);
        this.loadControl.onPrepared();
        this.mediaSource = mediaSource2;
        setState(2);
        mediaSource2.prepareSource(this, this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    private void setPlayWhenReadyInternal(boolean z) throws ExoPlaybackException {
        this.rebuffering = false;
        this.playWhenReady = z;
        if (!z) {
            stopRenderers();
            updatePlaybackPositions();
        } else if (this.playbackInfo.playbackState == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (this.playbackInfo.playbackState == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setRepeatModeInternal(int i) throws ExoPlaybackException {
        this.repeatMode = i;
        if (!this.queue.updateRepeatMode(i)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setShuffleModeEnabledInternal(boolean z) throws ExoPlaybackException {
        this.shuffleModeEnabled = z;
        if (!this.queue.updateShuffleModeEnabled(z)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void seekToCurrentPosition(boolean z) throws ExoPlaybackException {
        MediaSource.MediaPeriodId mediaPeriodId = this.queue.getPlayingPeriod().info.id;
        long seekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs, true);
        if (seekToPeriodPosition != this.playbackInfo.positionUs) {
            this.playbackInfo = copyWithNewPosition(mediaPeriodId, seekToPeriodPosition, this.playbackInfo.contentPositionUs);
            if (z) {
                this.playbackInfoUpdate.setPositionDiscontinuity(4);
            }
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        this.rebuffering = false;
        this.mediaClock.start();
        for (Renderer start : this.enabledRenderers) {
            start.start();
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.mediaClock.stop();
        for (Renderer ensureStopped : this.enabledRenderers) {
            ensureStopped(ensureStopped);
        }
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            long readDiscontinuity = playingPeriod.prepared ? playingPeriod.mediaPeriod.readDiscontinuity() : -9223372036854775807L;
            if (readDiscontinuity != -9223372036854775807L) {
                resetRendererPosition(readDiscontinuity);
                if (readDiscontinuity != this.playbackInfo.positionUs) {
                    this.playbackInfo = copyWithNewPosition(this.playbackInfo.periodId, readDiscontinuity, this.playbackInfo.contentPositionUs);
                    this.playbackInfoUpdate.setPositionDiscontinuity(4);
                }
            } else {
                long syncAndGetPositionUs = this.mediaClock.syncAndGetPositionUs(playingPeriod != this.queue.getReadingPeriod());
                this.rendererPositionUs = syncAndGetPositionUs;
                long periodTime = playingPeriod.toPeriodTime(syncAndGetPositionUs);
                maybeTriggerPendingMessages(this.playbackInfo.positionUs, periodTime);
                this.playbackInfo.positionUs = periodTime;
            }
            this.playbackInfo.bufferedPositionUs = this.queue.getLoadingPeriod().getBufferedPositionUs();
            this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:78:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0145  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doSomeWork() throws com.google.android.exoplayer2.ExoPlaybackException, java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            com.google.android.exoplayer2.util.Clock r1 = r0.clock
            long r1 = r1.uptimeMillis()
            r16.updatePeriods()
            com.google.android.exoplayer2.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            r5 = 1
            if (r3 == r5) goto L_0x0160
            com.google.android.exoplayer2.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            r6 = 4
            if (r3 != r6) goto L_0x001b
            goto L_0x0160
        L_0x001b:
            com.google.android.exoplayer2.MediaPeriodQueue r3 = r0.queue
            com.google.android.exoplayer2.MediaPeriodHolder r3 = r3.getPlayingPeriod()
            r7 = 10
            if (r3 != 0) goto L_0x0029
            r0.scheduleNextWork(r1, r7)
            return
        L_0x0029:
            java.lang.String r9 = "doSomeWork"
            com.google.android.exoplayer2.util.TraceUtil.beginSection(r9)
            r16.updatePlaybackPositions()
            boolean r9 = r3.prepared
            r10 = 1000(0x3e8, double:4.94E-321)
            r12 = 0
            if (r9 == 0) goto L_0x00b2
            long r13 = android.os.SystemClock.elapsedRealtime()
            long r13 = r13 * r10
            com.google.android.exoplayer2.source.MediaPeriod r9 = r3.mediaPeriod
            com.google.android.exoplayer2.PlaybackInfo r15 = r0.playbackInfo
            long r10 = r15.positionUs
            long r7 = r0.backBufferDurationUs
            long r10 = r10 - r7
            boolean r7 = r0.retainBackBufferFromKeyframe
            r9.discardBuffer(r10, r7)
            r7 = 0
            r8 = 1
            r9 = 1
        L_0x004f:
            com.google.android.exoplayer2.Renderer[] r10 = r0.renderers
            int r11 = r10.length
            if (r7 >= r11) goto L_0x00af
            r10 = r10[r7]
            int r11 = r10.getState()
            if (r11 != 0) goto L_0x005d
            goto L_0x00ab
        L_0x005d:
            long r4 = r0.rendererPositionUs
            r10.render(r4, r13)
            if (r8 == 0) goto L_0x006c
            boolean r4 = r10.isEnded()
            if (r4 == 0) goto L_0x006c
            r8 = 1
            goto L_0x006d
        L_0x006c:
            r8 = 0
        L_0x006d:
            com.google.android.exoplayer2.source.SampleStream[] r4 = r3.sampleStreams
            r4 = r4[r7]
            com.google.android.exoplayer2.source.SampleStream r5 = r10.getStream()
            if (r4 == r5) goto L_0x0079
            r4 = 1
            goto L_0x007a
        L_0x0079:
            r4 = 0
        L_0x007a:
            if (r4 != 0) goto L_0x008a
            com.google.android.exoplayer2.MediaPeriodHolder r5 = r3.getNext()
            if (r5 == 0) goto L_0x008a
            boolean r5 = r10.hasReadStreamToEnd()
            if (r5 == 0) goto L_0x008a
            r5 = 1
            goto L_0x008b
        L_0x008a:
            r5 = 0
        L_0x008b:
            if (r4 != 0) goto L_0x009e
            if (r5 != 0) goto L_0x009e
            boolean r4 = r10.isReady()
            if (r4 != 0) goto L_0x009e
            boolean r4 = r10.isEnded()
            if (r4 == 0) goto L_0x009c
            goto L_0x009e
        L_0x009c:
            r4 = 0
            goto L_0x009f
        L_0x009e:
            r4 = 1
        L_0x009f:
            if (r9 == 0) goto L_0x00a5
            if (r4 == 0) goto L_0x00a5
            r9 = 1
            goto L_0x00a6
        L_0x00a5:
            r9 = 0
        L_0x00a6:
            if (r4 != 0) goto L_0x00ab
            r10.maybeThrowStreamError()
        L_0x00ab:
            int r7 = r7 + 1
            r5 = 1
            goto L_0x004f
        L_0x00af:
            r5 = r8
            r15 = r9
            goto L_0x00b9
        L_0x00b2:
            com.google.android.exoplayer2.source.MediaPeriod r4 = r3.mediaPeriod
            r4.maybeThrowPrepareError()
            r5 = 1
            r15 = 1
        L_0x00b9:
            com.google.android.exoplayer2.MediaPeriodInfo r4 = r3.info
            long r7 = r4.durationUs
            r4 = 3
            if (r5 == 0) goto L_0x00e2
            boolean r5 = r3.prepared
            if (r5 == 0) goto L_0x00e2
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 == 0) goto L_0x00d5
            com.google.android.exoplayer2.PlaybackInfo r5 = r0.playbackInfo
            long r9 = r5.positionUs
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 > 0) goto L_0x00e2
        L_0x00d5:
            com.google.android.exoplayer2.MediaPeriodInfo r3 = r3.info
            boolean r3 = r3.isFinal
            if (r3 == 0) goto L_0x00e2
            r0.setState(r6)
            r16.stopRenderers()
            goto L_0x011a
        L_0x00e2:
            com.google.android.exoplayer2.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            r5 = 2
            if (r3 != r5) goto L_0x00fa
            boolean r3 = r0.shouldTransitionToReadyState(r15)
            if (r3 == 0) goto L_0x00fa
            r0.setState(r4)
            boolean r3 = r0.playWhenReady
            if (r3 == 0) goto L_0x011a
            r16.startRenderers()
            goto L_0x011a
        L_0x00fa:
            com.google.android.exoplayer2.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            if (r3 != r4) goto L_0x011a
            com.google.android.exoplayer2.Renderer[] r3 = r0.enabledRenderers
            int r3 = r3.length
            if (r3 != 0) goto L_0x010c
            boolean r3 = r16.isTimelineReady()
            if (r3 == 0) goto L_0x010e
            goto L_0x011a
        L_0x010c:
            if (r15 != 0) goto L_0x011a
        L_0x010e:
            boolean r3 = r0.playWhenReady
            r0.rebuffering = r3
            r3 = 2
            r0.setState(r3)
            r16.stopRenderers()
            goto L_0x011b
        L_0x011a:
            r3 = 2
        L_0x011b:
            com.google.android.exoplayer2.PlaybackInfo r5 = r0.playbackInfo
            int r5 = r5.playbackState
            if (r5 != r3) goto L_0x012e
            com.google.android.exoplayer2.Renderer[] r3 = r0.enabledRenderers
            int r5 = r3.length
        L_0x0124:
            if (r12 >= r5) goto L_0x012e
            r7 = r3[r12]
            r7.maybeThrowStreamError()
            int r12 = r12 + 1
            goto L_0x0124
        L_0x012e:
            boolean r3 = r0.playWhenReady
            if (r3 == 0) goto L_0x0138
            com.google.android.exoplayer2.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            if (r3 == r4) goto L_0x013f
        L_0x0138:
            com.google.android.exoplayer2.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            r4 = 2
            if (r3 != r4) goto L_0x0145
        L_0x013f:
            r3 = 10
            r0.scheduleNextWork(r1, r3)
            goto L_0x015c
        L_0x0145:
            com.google.android.exoplayer2.Renderer[] r3 = r0.enabledRenderers
            int r3 = r3.length
            if (r3 == 0) goto L_0x0156
            com.google.android.exoplayer2.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            if (r3 == r6) goto L_0x0156
            r3 = 1000(0x3e8, double:4.94E-321)
            r0.scheduleNextWork(r1, r3)
            goto L_0x015c
        L_0x0156:
            com.google.android.exoplayer2.util.HandlerWrapper r1 = r0.handler
            r2 = 2
            r1.removeMessages(r2)
        L_0x015c:
            com.google.android.exoplayer2.util.TraceUtil.endSection()
            return
        L_0x0160:
            r2 = 2
            com.google.android.exoplayer2.util.HandlerWrapper r1 = r0.handler
            r1.removeMessages(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.doSomeWork():void");
    }

    private void scheduleNextWork(long j, long j2) {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessageAtTime(2, j + j2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void seekToInternal(com.google.android.exoplayer2.ExoPlayerImplInternal.SeekPosition r17) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r16 = this;
            r7 = r16
            r0 = r17
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r1 = r7.playbackInfoUpdate
            r2 = 1
            r1.incrementPendingOperationAcks(r2)
            android.util.Pair r1 = r7.resolveSeekPosition(r0, r2)
            r4 = 0
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r1 != 0) goto L_0x0028
            com.google.android.exoplayer2.PlaybackInfo r1 = r7.playbackInfo
            boolean r6 = r7.shuffleModeEnabled
            com.google.android.exoplayer2.Timeline$Window r10 = r7.window
            com.google.android.exoplayer2.Timeline$Period r11 = r7.period
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.getDummyFirstMediaPeriodId(r6, r10, r11)
            r11 = r1
            r12 = r8
            r14 = r12
            r10 = 1
            goto L_0x0057
        L_0x0028:
            java.lang.Object r6 = r1.first
            java.lang.Object r10 = r1.second
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            com.google.android.exoplayer2.MediaPeriodQueue r12 = r7.queue
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r6 = r12.resolveMediaPeriodIdForAds(r6, r10)
            boolean r12 = r6.isAd()
            if (r12 == 0) goto L_0x0043
            r12 = r4
            r14 = r10
            r10 = 1
        L_0x0041:
            r11 = r6
            goto L_0x0057
        L_0x0043:
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r12 = r1.longValue()
            long r14 = r0.windowPositionUs
            int r1 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r1 != 0) goto L_0x0053
            r1 = 1
            goto L_0x0054
        L_0x0053:
            r1 = 0
        L_0x0054:
            r14 = r10
            r10 = r1
            goto L_0x0041
        L_0x0057:
            r6 = 2
            com.google.android.exoplayer2.source.MediaSource r1 = r7.mediaSource     // Catch:{ all -> 0x00e9 }
            if (r1 == 0) goto L_0x00d1
            int r1 = r7.pendingPrepareCount     // Catch:{ all -> 0x00e9 }
            if (r1 <= 0) goto L_0x0062
            goto L_0x00d1
        L_0x0062:
            int r0 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r0 != 0) goto L_0x0077
            r0 = 4
            r7.setState(r0)     // Catch:{ all -> 0x00e9 }
            r2 = 0
            r3 = 0
            r4 = 1
            r5 = 0
            r0 = 1
            r1 = r16
            r8 = 2
            r6 = r0
            r1.resetInternal(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00e7 }
            goto L_0x00d4
        L_0x0077:
            r8 = 2
            com.google.android.exoplayer2.PlaybackInfo r0 = r7.playbackInfo     // Catch:{ all -> 0x00e7 }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.periodId     // Catch:{ all -> 0x00e7 }
            boolean r0 = r11.equals(r0)     // Catch:{ all -> 0x00e7 }
            if (r0 == 0) goto L_0x00c2
            com.google.android.exoplayer2.MediaPeriodQueue r0 = r7.queue     // Catch:{ all -> 0x00e7 }
            com.google.android.exoplayer2.MediaPeriodHolder r0 = r0.getPlayingPeriod()     // Catch:{ all -> 0x00e7 }
            if (r0 == 0) goto L_0x009b
            boolean r1 = r0.prepared     // Catch:{ all -> 0x00e7 }
            if (r1 == 0) goto L_0x009b
            int r1 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x009b
            com.google.android.exoplayer2.source.MediaPeriod r0 = r0.mediaPeriod     // Catch:{ all -> 0x00e7 }
            com.google.android.exoplayer2.SeekParameters r1 = r7.seekParameters     // Catch:{ all -> 0x00e7 }
            long r0 = r0.getAdjustedSeekPositionUs(r12, r1)     // Catch:{ all -> 0x00e7 }
            goto L_0x009c
        L_0x009b:
            r0 = r12
        L_0x009c:
            long r4 = com.google.android.exoplayer2.C.usToMs(r0)     // Catch:{ all -> 0x00e7 }
            com.google.android.exoplayer2.PlaybackInfo r6 = r7.playbackInfo     // Catch:{ all -> 0x00e7 }
            long r2 = r6.positionUs     // Catch:{ all -> 0x00e7 }
            long r2 = com.google.android.exoplayer2.C.usToMs(r2)     // Catch:{ all -> 0x00e7 }
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x00c3
            com.google.android.exoplayer2.PlaybackInfo r0 = r7.playbackInfo     // Catch:{ all -> 0x00e7 }
            long r3 = r0.positionUs     // Catch:{ all -> 0x00e7 }
            r1 = r16
            r2 = r11
            r5 = r14
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.copyWithNewPosition(r2, r3, r5)
            r7.playbackInfo = r0
            if (r10 == 0) goto L_0x00c1
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r0 = r7.playbackInfoUpdate
            r0.setPositionDiscontinuity(r8)
        L_0x00c1:
            return
        L_0x00c2:
            r0 = r12
        L_0x00c3:
            long r0 = r7.seekToPeriodPosition(r11, r0)     // Catch:{ all -> 0x00e7 }
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 == 0) goto L_0x00cd
            r2 = 1
            goto L_0x00ce
        L_0x00cd:
            r2 = 0
        L_0x00ce:
            r10 = r10 | r2
            r3 = r0
            goto L_0x00d5
        L_0x00d1:
            r8 = 2
            r7.pendingInitialSeekPosition = r0     // Catch:{ all -> 0x00e7 }
        L_0x00d4:
            r3 = r12
        L_0x00d5:
            r1 = r16
            r2 = r11
            r5 = r14
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.copyWithNewPosition(r2, r3, r5)
            r7.playbackInfo = r0
            if (r10 == 0) goto L_0x00e6
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r0 = r7.playbackInfoUpdate
            r0.setPositionDiscontinuity(r8)
        L_0x00e6:
            return
        L_0x00e7:
            r0 = move-exception
            goto L_0x00eb
        L_0x00e9:
            r0 = move-exception
            r8 = 2
        L_0x00eb:
            r1 = r16
            r2 = r11
            r3 = r12
            r5 = r14
            com.google.android.exoplayer2.PlaybackInfo r1 = r1.copyWithNewPosition(r2, r3, r5)
            r7.playbackInfo = r1
            if (r10 == 0) goto L_0x00fd
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r1 = r7.playbackInfoUpdate
            r1.setPositionDiscontinuity(r8)
        L_0x00fd:
            goto L_0x00ff
        L_0x00fe:
            throw r0
        L_0x00ff:
            goto L_0x00fe
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.seekToInternal(com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition):void");
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j) throws ExoPlaybackException {
        return seekToPeriodPosition(mediaPeriodId, j, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod());
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j, boolean z) throws ExoPlaybackException {
        stopRenderers();
        this.rebuffering = false;
        if (this.playbackInfo.playbackState != 1 && !this.playbackInfo.timeline.isEmpty()) {
            setState(2);
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder mediaPeriodHolder = playingPeriod;
        while (true) {
            if (mediaPeriodHolder != null) {
                if (mediaPeriodId.equals(mediaPeriodHolder.info.id) && mediaPeriodHolder.prepared) {
                    this.queue.removeAfter(mediaPeriodHolder);
                    break;
                }
                mediaPeriodHolder = this.queue.advancePlayingPeriod();
            } else {
                break;
            }
        }
        if (z || playingPeriod != mediaPeriodHolder || (mediaPeriodHolder != null && mediaPeriodHolder.toRendererTime(j) < 0)) {
            for (Renderer disableRenderer : this.enabledRenderers) {
                disableRenderer(disableRenderer);
            }
            this.enabledRenderers = new Renderer[0];
            playingPeriod = null;
            if (mediaPeriodHolder != null) {
                mediaPeriodHolder.setRendererOffset(0);
            }
        }
        if (mediaPeriodHolder != null) {
            updatePlayingPeriodRenderers(playingPeriod);
            if (mediaPeriodHolder.hasEnabledTracks) {
                long seekToUs = mediaPeriodHolder.mediaPeriod.seekToUs(j);
                mediaPeriodHolder.mediaPeriod.discardBuffer(seekToUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
                j = seekToUs;
            }
            resetRendererPosition(j);
            maybeContinueLoading();
        } else {
            this.queue.clear(true);
            this.playbackInfo = this.playbackInfo.copyWithTrackInfo(TrackGroupArray.EMPTY, this.emptyTrackSelectorResult);
            resetRendererPosition(j);
        }
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return j;
    }

    private void resetRendererPosition(long j) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            j = playingPeriod.toRendererTime(j);
        }
        this.rendererPositionUs = j;
        this.mediaClock.resetPosition(j);
        for (Renderer resetPosition : this.enabledRenderers) {
            resetPosition.resetPosition(this.rendererPositionUs);
        }
        notifyTrackSelectionDiscontinuity();
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) {
        this.mediaClock.setPlaybackParameters(playbackParameters);
        sendPlaybackParametersChangedInternal(this.mediaClock.getPlaybackParameters(), true);
    }

    private void setSeekParametersInternal(SeekParameters seekParameters2) {
        this.seekParameters = seekParameters2;
    }

    private void setForegroundModeInternal(boolean z, AtomicBoolean atomicBoolean) {
        if (this.foregroundMode != z) {
            this.foregroundMode = z;
            if (!z) {
                for (Renderer renderer : this.renderers) {
                    if (renderer.getState() == 0) {
                        renderer.reset();
                    }
                }
            }
        }
        if (atomicBoolean != null) {
            synchronized (this) {
                atomicBoolean.set(true);
                notifyAll();
            }
        }
    }

    private void stopInternal(boolean z, boolean z2, boolean z3) {
        resetInternal(z || !this.foregroundMode, true, z2, z2, z2);
        this.playbackInfoUpdate.incrementPendingOperationAcks(this.pendingPrepareCount + (z3 ? 1 : 0));
        this.pendingPrepareCount = 0;
        this.loadControl.onStopped();
        setState(1);
    }

    private void releaseInternal() {
        resetInternal(true, true, true, true, false);
        this.loadControl.onReleased();
        setState(1);
        this.internalPlaybackThread.quit();
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void resetInternal(boolean r24, boolean r25, boolean r26, boolean r27, boolean r28) {
        /*
            r23 = this;
            r1 = r23
            com.google.android.exoplayer2.util.HandlerWrapper r0 = r1.handler
            r2 = 2
            r0.removeMessages(r2)
            r2 = 0
            r1.rebuffering = r2
            com.google.android.exoplayer2.DefaultMediaClock r0 = r1.mediaClock
            r0.stop()
            r3 = 0
            r1.rendererPositionUs = r3
            com.google.android.exoplayer2.Renderer[] r3 = r1.enabledRenderers
            int r4 = r3.length
            r5 = 0
        L_0x0018:
            java.lang.String r6 = "ExoPlayerImplInternal"
            if (r5 >= r4) goto L_0x002d
            r0 = r3[r5]
            r1.disableRenderer(r0)     // Catch:{ ExoPlaybackException -> 0x0024, RuntimeException -> 0x0022 }
            goto L_0x002a
        L_0x0022:
            r0 = move-exception
            goto L_0x0025
        L_0x0024:
            r0 = move-exception
        L_0x0025:
            java.lang.String r7 = "Disable failed."
            com.google.android.exoplayer2.util.Log.e(r6, r7, r0)
        L_0x002a:
            int r5 = r5 + 1
            goto L_0x0018
        L_0x002d:
            if (r24 == 0) goto L_0x0045
            com.google.android.exoplayer2.Renderer[] r3 = r1.renderers
            int r4 = r3.length
            r5 = 0
        L_0x0033:
            if (r5 >= r4) goto L_0x0045
            r0 = r3[r5]
            r0.reset()     // Catch:{ RuntimeException -> 0x003b }
            goto L_0x0042
        L_0x003b:
            r0 = move-exception
            r7 = r0
            java.lang.String r0 = "Reset failed."
            com.google.android.exoplayer2.util.Log.e(r6, r0, r7)
        L_0x0042:
            int r5 = r5 + 1
            goto L_0x0033
        L_0x0045:
            com.google.android.exoplayer2.Renderer[] r0 = new com.google.android.exoplayer2.Renderer[r2]
            r1.enabledRenderers = r0
            r0 = 0
            if (r26 == 0) goto L_0x004f
            r1.pendingInitialSeekPosition = r0
            goto L_0x0088
        L_0x004f:
            if (r27 == 0) goto L_0x0088
            com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition r3 = r1.pendingInitialSeekPosition
            if (r3 != 0) goto L_0x0086
            com.google.android.exoplayer2.PlaybackInfo r3 = r1.playbackInfo
            com.google.android.exoplayer2.Timeline r3 = r3.timeline
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0086
            com.google.android.exoplayer2.PlaybackInfo r3 = r1.playbackInfo
            com.google.android.exoplayer2.Timeline r3 = r3.timeline
            com.google.android.exoplayer2.PlaybackInfo r4 = r1.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r4 = r4.periodId
            java.lang.Object r4 = r4.periodUid
            com.google.android.exoplayer2.Timeline$Period r5 = r1.period
            r3.getPeriodByUid(r4, r5)
            com.google.android.exoplayer2.PlaybackInfo r3 = r1.playbackInfo
            long r3 = r3.positionUs
            com.google.android.exoplayer2.Timeline$Period r5 = r1.period
            long r5 = r5.getPositionInWindowUs()
            long r3 = r3 + r5
            com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition r5 = new com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition
            com.google.android.exoplayer2.Timeline r6 = com.google.android.exoplayer2.Timeline.EMPTY
            com.google.android.exoplayer2.Timeline$Period r7 = r1.period
            int r7 = r7.windowIndex
            r5.<init>(r6, r7, r3)
            r1.pendingInitialSeekPosition = r5
        L_0x0086:
            r3 = 1
            goto L_0x008a
        L_0x0088:
            r3 = r26
        L_0x008a:
            com.google.android.exoplayer2.MediaPeriodQueue r4 = r1.queue
            r5 = r27 ^ 1
            r4.clear(r5)
            r1.shouldContinueLoading = r2
            if (r27 == 0) goto L_0x00bb
            com.google.android.exoplayer2.MediaPeriodQueue r4 = r1.queue
            com.google.android.exoplayer2.Timeline r5 = com.google.android.exoplayer2.Timeline.EMPTY
            r4.setTimeline(r5)
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r4 = r1.pendingMessages
            java.util.Iterator r4 = r4.iterator()
        L_0x00a2:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00b4
            java.lang.Object r5 = r4.next()
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r5 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r5
            com.google.android.exoplayer2.PlayerMessage r5 = r5.message
            r5.markAsProcessed(r2)
            goto L_0x00a2
        L_0x00b4:
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r4 = r1.pendingMessages
            r4.clear()
            r1.nextPendingMessageIndex = r2
        L_0x00bb:
            if (r3 == 0) goto L_0x00ca
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.playbackInfo
            boolean r4 = r1.shuffleModeEnabled
            com.google.android.exoplayer2.Timeline$Window r5 = r1.window
            com.google.android.exoplayer2.Timeline$Period r6 = r1.period
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r2.getDummyFirstMediaPeriodId(r4, r5, r6)
            goto L_0x00ce
        L_0x00ca:
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r2.periodId
        L_0x00ce:
            r16 = r2
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r3 == 0) goto L_0x00da
            r21 = r4
            goto L_0x00e0
        L_0x00da:
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.playbackInfo
            long r6 = r2.positionUs
            r21 = r6
        L_0x00e0:
            if (r3 == 0) goto L_0x00e4
            r9 = r4
            goto L_0x00e9
        L_0x00e4:
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.playbackInfo
            long r2 = r2.contentPositionUs
            r9 = r2
        L_0x00e9:
            com.google.android.exoplayer2.PlaybackInfo r2 = new com.google.android.exoplayer2.PlaybackInfo
            if (r27 == 0) goto L_0x00f0
            com.google.android.exoplayer2.Timeline r3 = com.google.android.exoplayer2.Timeline.EMPTY
            goto L_0x00f4
        L_0x00f0:
            com.google.android.exoplayer2.PlaybackInfo r3 = r1.playbackInfo
            com.google.android.exoplayer2.Timeline r3 = r3.timeline
        L_0x00f4:
            r5 = r3
            com.google.android.exoplayer2.PlaybackInfo r3 = r1.playbackInfo
            int r11 = r3.playbackState
            if (r28 == 0) goto L_0x00fd
            r12 = r0
            goto L_0x0102
        L_0x00fd:
            com.google.android.exoplayer2.PlaybackInfo r3 = r1.playbackInfo
            com.google.android.exoplayer2.ExoPlaybackException r3 = r3.playbackError
            r12 = r3
        L_0x0102:
            r13 = 0
            if (r27 == 0) goto L_0x0108
            com.google.android.exoplayer2.source.TrackGroupArray r3 = com.google.android.exoplayer2.source.TrackGroupArray.EMPTY
            goto L_0x010c
        L_0x0108:
            com.google.android.exoplayer2.PlaybackInfo r3 = r1.playbackInfo
            com.google.android.exoplayer2.source.TrackGroupArray r3 = r3.trackGroups
        L_0x010c:
            r14 = r3
            if (r27 == 0) goto L_0x0112
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r3 = r1.emptyTrackSelectorResult
            goto L_0x0116
        L_0x0112:
            com.google.android.exoplayer2.PlaybackInfo r3 = r1.playbackInfo
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r3 = r3.trackSelectorResult
        L_0x0116:
            r15 = r3
            r19 = 0
            r4 = r2
            r6 = r16
            r7 = r21
            r17 = r21
            r4.<init>(r5, r6, r7, r9, r11, r12, r13, r14, r15, r16, r17, r19, r21)
            r1.playbackInfo = r2
            if (r25 == 0) goto L_0x0130
            com.google.android.exoplayer2.source.MediaSource r2 = r1.mediaSource
            if (r2 == 0) goto L_0x0130
            r2.releaseSource(r1)
            r1.mediaSource = r0
        L_0x0130:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.resetInternal(boolean, boolean, boolean, boolean, boolean):void");
    }

    private void sendMessageInternal(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getPositionMs() == -9223372036854775807L) {
            sendMessageToTarget(playerMessage);
        } else if (this.mediaSource == null || this.pendingPrepareCount > 0) {
            this.pendingMessages.add(new PendingMessageInfo(playerMessage));
        } else {
            PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(playerMessage);
            if (resolvePendingMessagePosition(pendingMessageInfo)) {
                this.pendingMessages.add(pendingMessageInfo);
                Collections.sort(this.pendingMessages);
                return;
            }
            playerMessage.markAsProcessed(false);
        }
    }

    private void sendMessageToTarget(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getHandler().getLooper() == this.handler.getLooper()) {
            deliverMessage(playerMessage);
            if (this.playbackInfo.playbackState == 3 || this.playbackInfo.playbackState == 2) {
                this.handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        this.handler.obtainMessage(16, playerMessage).sendToTarget();
    }

    private void sendMessageToTargetThread(PlayerMessage playerMessage) {
        Handler handler2 = playerMessage.getHandler();
        if (!handler2.getLooper().getThread().isAlive()) {
            Log.w("TAG", "Trying to send message on a dead thread.");
            playerMessage.markAsProcessed(false);
            return;
        }
        handler2.post(new Runnable(playerMessage) {
            public final /* synthetic */ PlayerMessage f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ExoPlayerImplInternal.this.lambda$sendMessageToTargetThread$0$ExoPlayerImplInternal(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$sendMessageToTargetThread$0$ExoPlayerImplInternal(PlayerMessage playerMessage) {
        try {
            deliverMessage(playerMessage);
        } catch (ExoPlaybackException e) {
            Log.e("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    private void deliverMessage(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (!playerMessage.isCanceled()) {
            try {
                playerMessage.getTarget().handleMessage(playerMessage.getType(), playerMessage.getPayload());
            } finally {
                playerMessage.markAsProcessed(true);
            }
        }
    }

    private void resolvePendingMessagePositions() {
        for (int size = this.pendingMessages.size() - 1; size >= 0; size--) {
            if (!resolvePendingMessagePosition(this.pendingMessages.get(size))) {
                this.pendingMessages.get(size).message.markAsProcessed(false);
                this.pendingMessages.remove(size);
            }
        }
        Collections.sort(this.pendingMessages);
    }

    private boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo) {
        if (pendingMessageInfo.resolvedPeriodUid == null) {
            Pair<Object, Long> resolveSeekPosition = resolveSeekPosition(new SeekPosition(pendingMessageInfo.message.getTimeline(), pendingMessageInfo.message.getWindowIndex(), C.msToUs(pendingMessageInfo.message.getPositionMs())), false);
            if (resolveSeekPosition == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(this.playbackInfo.timeline.getIndexOfPeriod(resolveSeekPosition.first), ((Long) resolveSeekPosition.second).longValue(), resolveSeekPosition.first);
            return true;
        }
        int indexOfPeriod = this.playbackInfo.timeline.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid);
        if (indexOfPeriod == -1) {
            return false;
        }
        pendingMessageInfo.resolvedPeriodIndex = indexOfPeriod;
        return true;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00ab A[EDGE_INSN: B:73:0x00ab->B:87:0x00ab ?: BREAK  
    EDGE_INSN: B:74:0x00ab->B:87:0x00ab ?: BREAK  
    EDGE_INSN: B:75:0x00ab->B:87:0x00ab ?: BREAK  
    EDGE_INSN: B:76:0x00ab->B:87:0x00ab ?: BREAK  , SYNTHETIC] */
    private void maybeTriggerPendingMessages(long r7, long r9) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r6 = this;
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r0 = r6.pendingMessages
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x011d
            com.google.android.exoplayer2.PlaybackInfo r0 = r6.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.periodId
            boolean r0 = r0.isAd()
            if (r0 == 0) goto L_0x0014
            goto L_0x011d
        L_0x0014:
            com.google.android.exoplayer2.PlaybackInfo r0 = r6.playbackInfo
            long r0 = r0.startPositionUs
            int r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0023
            boolean r0 = r6.deliverPendingMessageAtStartPositionRequired
            if (r0 == 0) goto L_0x0023
            r0 = 1
            long r7 = r7 - r0
        L_0x0023:
            r0 = 0
            r6.deliverPendingMessageAtStartPositionRequired = r0
            com.google.android.exoplayer2.PlaybackInfo r0 = r6.playbackInfo
            com.google.android.exoplayer2.Timeline r0 = r0.timeline
            com.google.android.exoplayer2.PlaybackInfo r1 = r6.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.periodId
            java.lang.Object r1 = r1.periodUid
            int r0 = r0.getIndexOfPeriod(r1)
            int r1 = r6.nextPendingMessageIndex
            r2 = 0
            if (r1 <= 0) goto L_0x0044
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r1 = r1 + -1
            java.lang.Object r1 = r3.get(r1)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x0045
        L_0x0044:
            r1 = r2
        L_0x0045:
            if (r1 == 0) goto L_0x0068
            int r3 = r1.resolvedPeriodIndex
            if (r3 > r0) goto L_0x0055
            int r3 = r1.resolvedPeriodIndex
            if (r3 != r0) goto L_0x0068
            long r3 = r1.resolvedPeriodTimeUs
            int r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r1 <= 0) goto L_0x0068
        L_0x0055:
            int r1 = r6.nextPendingMessageIndex
            int r1 = r1 + -1
            r6.nextPendingMessageIndex = r1
            if (r1 <= 0) goto L_0x0044
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r1 = r1 + -1
            java.lang.Object r1 = r3.get(r1)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x0045
        L_0x0068:
            int r1 = r6.nextPendingMessageIndex
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x007d
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r1 = r6.pendingMessages
            int r3 = r6.nextPendingMessageIndex
            java.lang.Object r1 = r1.get(r3)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x007e
        L_0x007d:
            r1 = r2
        L_0x007e:
            if (r1 == 0) goto L_0x00ab
            java.lang.Object r3 = r1.resolvedPeriodUid
            if (r3 == 0) goto L_0x00ab
            int r3 = r1.resolvedPeriodIndex
            if (r3 < r0) goto L_0x0092
            int r3 = r1.resolvedPeriodIndex
            if (r3 != r0) goto L_0x00ab
            long r3 = r1.resolvedPeriodTimeUs
            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r5 > 0) goto L_0x00ab
        L_0x0092:
            int r1 = r6.nextPendingMessageIndex
            int r1 = r1 + 1
            r6.nextPendingMessageIndex = r1
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x007d
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r1 = r6.pendingMessages
            int r3 = r6.nextPendingMessageIndex
            java.lang.Object r1 = r1.get(r3)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x007e
        L_0x00ab:
            if (r1 == 0) goto L_0x011d
            java.lang.Object r3 = r1.resolvedPeriodUid
            if (r3 == 0) goto L_0x011d
            int r3 = r1.resolvedPeriodIndex
            if (r3 != r0) goto L_0x011d
            long r3 = r1.resolvedPeriodTimeUs
            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x011d
            long r3 = r1.resolvedPeriodTimeUs
            int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r5 > 0) goto L_0x011d
            com.google.android.exoplayer2.PlayerMessage r3 = r1.message     // Catch:{ all -> 0x00fc }
            r6.sendMessageToTarget(r3)     // Catch:{ all -> 0x00fc }
            com.google.android.exoplayer2.PlayerMessage r3 = r1.message
            boolean r3 = r3.getDeleteAfterDelivery()
            if (r3 != 0) goto L_0x00de
            com.google.android.exoplayer2.PlayerMessage r1 = r1.message
            boolean r1 = r1.isCanceled()
            if (r1 == 0) goto L_0x00d7
            goto L_0x00de
        L_0x00d7:
            int r1 = r6.nextPendingMessageIndex
            int r1 = r1 + 1
            r6.nextPendingMessageIndex = r1
            goto L_0x00e5
        L_0x00de:
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r1 = r6.pendingMessages
            int r3 = r6.nextPendingMessageIndex
            r1.remove(r3)
        L_0x00e5:
            int r1 = r6.nextPendingMessageIndex
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x00fa
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r1 = r6.pendingMessages
            int r3 = r6.nextPendingMessageIndex
            java.lang.Object r1 = r1.get(r3)
            com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo r1 = (com.google.android.exoplayer2.ExoPlayerImplInternal.PendingMessageInfo) r1
            goto L_0x00ab
        L_0x00fa:
            r1 = r2
            goto L_0x00ab
        L_0x00fc:
            r7 = move-exception
            com.google.android.exoplayer2.PlayerMessage r8 = r1.message
            boolean r8 = r8.getDeleteAfterDelivery()
            if (r8 != 0) goto L_0x0115
            com.google.android.exoplayer2.PlayerMessage r8 = r1.message
            boolean r8 = r8.isCanceled()
            if (r8 == 0) goto L_0x010e
            goto L_0x0115
        L_0x010e:
            int r8 = r6.nextPendingMessageIndex
            int r8 = r8 + 1
            r6.nextPendingMessageIndex = r8
            goto L_0x011c
        L_0x0115:
            java.util.ArrayList<com.google.android.exoplayer2.ExoPlayerImplInternal$PendingMessageInfo> r8 = r6.pendingMessages
            int r9 = r6.nextPendingMessageIndex
            r8.remove(r9)
        L_0x011c:
            throw r7
        L_0x011d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.maybeTriggerPendingMessages(long, long):void");
    }

    private void ensureStopped(Renderer renderer) throws ExoPlaybackException {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private void disableRenderer(Renderer renderer) throws ExoPlaybackException {
        this.mediaClock.onRendererDisabled(renderer);
        ensureStopped(renderer);
        renderer.disable();
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        boolean[] zArr;
        MediaPeriodHolder mediaPeriodHolder;
        float f = this.mediaClock.getPlaybackParameters().speed;
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        boolean z = true;
        while (playingPeriod != null && playingPeriod.prepared) {
            TrackSelectorResult selectTracks = playingPeriod.selectTracks(f, this.playbackInfo.timeline);
            if (!selectTracks.isEquivalent(playingPeriod.getTrackSelectorResult())) {
                if (z) {
                    MediaPeriodHolder playingPeriod2 = this.queue.getPlayingPeriod();
                    boolean removeAfter = this.queue.removeAfter(playingPeriod2);
                    boolean[] zArr2 = new boolean[this.renderers.length];
                    long applyTrackSelection = playingPeriod2.applyTrackSelection(selectTracks, this.playbackInfo.positionUs, removeAfter, zArr2);
                    if (this.playbackInfo.playbackState == 4 || applyTrackSelection == this.playbackInfo.positionUs) {
                        mediaPeriodHolder = playingPeriod2;
                        zArr = zArr2;
                    } else {
                        mediaPeriodHolder = playingPeriod2;
                        zArr = zArr2;
                        this.playbackInfo = copyWithNewPosition(this.playbackInfo.periodId, applyTrackSelection, this.playbackInfo.contentPositionUs);
                        this.playbackInfoUpdate.setPositionDiscontinuity(4);
                        resetRendererPosition(applyTrackSelection);
                    }
                    boolean[] zArr3 = new boolean[this.renderers.length];
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        Renderer[] rendererArr = this.renderers;
                        if (i >= rendererArr.length) {
                            break;
                        }
                        Renderer renderer = rendererArr[i];
                        zArr3[i] = renderer.getState() != 0;
                        SampleStream sampleStream = mediaPeriodHolder.sampleStreams[i];
                        if (sampleStream != null) {
                            i2++;
                        }
                        if (zArr3[i]) {
                            if (sampleStream != renderer.getStream()) {
                                disableRenderer(renderer);
                            } else if (zArr[i]) {
                                renderer.resetPosition(this.rendererPositionUs);
                            }
                        }
                        i++;
                    }
                    this.playbackInfo = this.playbackInfo.copyWithTrackInfo(mediaPeriodHolder.getTrackGroups(), mediaPeriodHolder.getTrackSelectorResult());
                    enableRenderers(zArr3, i2);
                } else {
                    this.queue.removeAfter(playingPeriod);
                    if (playingPeriod.prepared) {
                        playingPeriod.applyTrackSelection(selectTracks, Math.max(playingPeriod.info.startPositionUs, playingPeriod.toPeriodTime(this.rendererPositionUs)), false);
                    }
                }
                handleLoadingMediaPeriodChanged(true);
                if (this.playbackInfo.playbackState != 4) {
                    maybeContinueLoading();
                    updatePlaybackPositions();
                    this.handler.sendEmptyMessage(2);
                    return;
                }
                return;
            }
            if (playingPeriod == readingPeriod) {
                z = false;
            }
            playingPeriod = playingPeriod.getNext();
        }
    }

    private void updateTrackSelectionPlaybackSpeed(float f) {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (TrackSelection trackSelection : playingPeriod.getTrackSelectorResult().selections.getAll()) {
                if (trackSelection != null) {
                    trackSelection.onPlaybackSpeed(f);
                }
            }
        }
    }

    private void notifyTrackSelectionDiscontinuity() {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (TrackSelection trackSelection : playingPeriod.getTrackSelectorResult().selections.getAll()) {
                if (trackSelection != null) {
                    trackSelection.onDiscontinuity();
                }
            }
        }
    }

    private boolean shouldTransitionToReadyState(boolean z) {
        if (this.enabledRenderers.length == 0) {
            return isTimelineReady();
        }
        if (!z) {
            return false;
        }
        if (!this.playbackInfo.isLoading) {
            return true;
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if ((loadingPeriod.isFullyBuffered() && loadingPeriod.info.isFinal) || this.loadControl.shouldStartPlayback(getTotalBufferedDurationUs(), this.mediaClock.getPlaybackParameters().speed, this.rebuffering)) {
            return true;
        }
        return false;
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long j = playingPeriod.info.durationUs;
        return playingPeriod.prepared && (j == -9223372036854775807L || this.playbackInfo.positionUs < j);
    }

    private void maybeThrowSourceInfoRefreshError() throws IOException {
        if (this.queue.getLoadingPeriod() != null) {
            Renderer[] rendererArr = this.enabledRenderers;
            int length = rendererArr.length;
            int i = 0;
            while (i < length) {
                if (rendererArr[i].hasReadStreamToEnd()) {
                    i++;
                } else {
                    return;
                }
            }
        }
        this.mediaSource.maybeThrowSourceInfoRefreshError();
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleSourceInfoRefreshed(com.google.android.exoplayer2.ExoPlayerImplInternal.MediaSourceRefreshInfo r12) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r11 = this;
            com.google.android.exoplayer2.source.MediaSource r0 = r12.source
            com.google.android.exoplayer2.source.MediaSource r1 = r11.mediaSource
            if (r0 == r1) goto L_0x0007
            return
        L_0x0007:
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r0 = r11.playbackInfoUpdate
            int r1 = r11.pendingPrepareCount
            r0.incrementPendingOperationAcks(r1)
            r0 = 0
            r11.pendingPrepareCount = r0
            com.google.android.exoplayer2.PlaybackInfo r1 = r11.playbackInfo
            com.google.android.exoplayer2.Timeline r1 = r1.timeline
            com.google.android.exoplayer2.Timeline r12 = r12.timeline
            com.google.android.exoplayer2.MediaPeriodQueue r2 = r11.queue
            r2.setTimeline(r12)
            com.google.android.exoplayer2.PlaybackInfo r2 = r11.playbackInfo
            com.google.android.exoplayer2.PlaybackInfo r2 = r2.copyWithTimeline(r12)
            r11.playbackInfo = r2
            r11.resolvePendingMessagePositions()
            com.google.android.exoplayer2.PlaybackInfo r2 = r11.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r2.periodId
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r3 = r3.periodId
            boolean r3 = r3.isAd()
            if (r3 == 0) goto L_0x003a
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.playbackInfo
            long r3 = r3.contentPositionUs
            goto L_0x003e
        L_0x003a:
            com.google.android.exoplayer2.PlaybackInfo r3 = r11.playbackInfo
            long r3 = r3.positionUs
        L_0x003e:
            com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition r5 = r11.pendingInitialSeekPosition
            if (r5 == 0) goto L_0x0064
            r12 = 1
            android.util.Pair r12 = r11.resolveSeekPosition(r5, r12)
            r1 = 0
            r11.pendingInitialSeekPosition = r1
            if (r12 != 0) goto L_0x0050
            r11.handleSourceInfoRefreshEndedPlayback()
            return
        L_0x0050:
            java.lang.Object r1 = r12.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            com.google.android.exoplayer2.MediaPeriodQueue r5 = r11.queue
            java.lang.Object r12 = r12.first
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r12 = r5.resolveMediaPeriodIdForAds(r12, r1)
        L_0x0060:
            r6 = r12
            r9 = r1
            goto L_0x00f4
        L_0x0064:
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x00a0
            boolean r7 = r12.isEmpty()
            if (r7 != 0) goto L_0x00a0
            boolean r1 = r11.shuffleModeEnabled
            int r1 = r12.getFirstWindowIndex(r1)
            android.util.Pair r12 = r11.getPeriodPosition(r12, r1, r5)
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.queue
            java.lang.Object r2 = r12.first
            java.lang.Object r5 = r12.second
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.resolveMediaPeriodIdForAds(r2, r5)
            boolean r2 = r1.isAd()
            if (r2 != 0) goto L_0x009c
            java.lang.Object r12 = r12.second
            java.lang.Long r12 = (java.lang.Long) r12
            long r5 = r12.longValue()
            goto L_0x009d
        L_0x009c:
            r5 = r3
        L_0x009d:
            r9 = r5
            r6 = r1
            goto L_0x00f4
        L_0x00a0:
            java.lang.Object r7 = r2.periodUid
            int r7 = r12.getIndexOfPeriod(r7)
            r8 = -1
            if (r7 != r8) goto L_0x00d2
            java.lang.Object r2 = r2.periodUid
            java.lang.Object r1 = r11.resolveSubsequentPeriod(r2, r1, r12)
            if (r1 != 0) goto L_0x00b5
            r11.handleSourceInfoRefreshEndedPlayback()
            return
        L_0x00b5:
            com.google.android.exoplayer2.Timeline$Period r2 = r11.period
            com.google.android.exoplayer2.Timeline$Period r1 = r12.getPeriodByUid(r1, r2)
            int r1 = r1.windowIndex
            android.util.Pair r12 = r11.getPeriodPosition(r12, r1, r5)
            java.lang.Object r1 = r12.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            com.google.android.exoplayer2.MediaPeriodQueue r5 = r11.queue
            java.lang.Object r12 = r12.first
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r12 = r5.resolveMediaPeriodIdForAds(r12, r1)
            goto L_0x0060
        L_0x00d2:
            com.google.android.exoplayer2.MediaPeriodQueue r12 = r11.queue
            com.google.android.exoplayer2.PlaybackInfo r1 = r11.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.periodId
            java.lang.Object r1 = r1.periodUid
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r12 = r12.resolveMediaPeriodIdForAds(r1, r3)
            com.google.android.exoplayer2.PlaybackInfo r1 = r11.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.periodId
            boolean r1 = r1.isAd()
            if (r1 != 0) goto L_0x00f2
            boolean r1 = r12.isAd()
            if (r1 != 0) goto L_0x00f2
            com.google.android.exoplayer2.PlaybackInfo r12 = r11.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r12 = r12.periodId
        L_0x00f2:
            r6 = r12
            r9 = r3
        L_0x00f4:
            com.google.android.exoplayer2.PlaybackInfo r12 = r11.playbackInfo
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r12 = r12.periodId
            boolean r12 = r12.equals(r6)
            if (r12 == 0) goto L_0x0114
            int r12 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r12 != 0) goto L_0x0114
            com.google.android.exoplayer2.MediaPeriodQueue r12 = r11.queue
            long r1 = r11.rendererPositionUs
            long r3 = r11.getMaxRendererReadPositionUs()
            boolean r12 = r12.updateQueuedPeriods(r1, r3)
            if (r12 != 0) goto L_0x0150
            r11.seekToCurrentPosition(r0)
            goto L_0x0150
        L_0x0114:
            com.google.android.exoplayer2.MediaPeriodQueue r12 = r11.queue
            com.google.android.exoplayer2.MediaPeriodHolder r12 = r12.getPlayingPeriod()
            if (r12 == 0) goto L_0x013b
        L_0x011c:
            com.google.android.exoplayer2.MediaPeriodHolder r1 = r12.getNext()
            if (r1 == 0) goto L_0x013b
            com.google.android.exoplayer2.MediaPeriodHolder r12 = r12.getNext()
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r12.info
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r1 = r1.id
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x011c
            com.google.android.exoplayer2.MediaPeriodQueue r1 = r11.queue
            com.google.android.exoplayer2.MediaPeriodInfo r2 = r12.info
            com.google.android.exoplayer2.MediaPeriodInfo r1 = r1.getUpdatedMediaPeriodInfo(r2)
            r12.info = r1
            goto L_0x011c
        L_0x013b:
            boolean r12 = r6.isAd()
            if (r12 == 0) goto L_0x0144
            r1 = 0
            goto L_0x0145
        L_0x0144:
            r1 = r9
        L_0x0145:
            long r7 = r11.seekToPeriodPosition(r6, r1)
            r5 = r11
            com.google.android.exoplayer2.PlaybackInfo r12 = r5.copyWithNewPosition(r6, r7, r9)
            r11.playbackInfo = r12
        L_0x0150:
            r11.handleLoadingMediaPeriodChanged(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.handleSourceInfoRefreshed(com.google.android.exoplayer2.ExoPlayerImplInternal$MediaSourceRefreshInfo):void");
    }

    private long getMaxRendererReadPositionUs() {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod == null) {
            return 0;
        }
        long rendererOffset = readingPeriod.getRendererOffset();
        if (!readingPeriod.prepared) {
            return rendererOffset;
        }
        int i = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i >= rendererArr.length) {
                return rendererOffset;
            }
            if (rendererArr[i].getState() != 0 && this.renderers[i].getStream() == readingPeriod.sampleStreams[i]) {
                long readingPositionUs = this.renderers[i].getReadingPositionUs();
                if (readingPositionUs == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                rendererOffset = Math.max(readingPositionUs, rendererOffset);
            }
            i++;
        }
    }

    private void handleSourceInfoRefreshEndedPlayback() {
        if (this.playbackInfo.playbackState != 1) {
            setState(4);
        }
        resetInternal(false, false, true, false, true);
    }

    private Object resolveSubsequentPeriod(Object obj, Timeline timeline, Timeline timeline2) {
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        int periodCount = timeline.getPeriodCount();
        int i = indexOfPeriod;
        int i2 = -1;
        for (int i3 = 0; i3 < periodCount && i2 == -1; i3++) {
            i = timeline.getNextPeriodIndex(i, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if (i == -1) {
                break;
            }
            i2 = timeline2.getIndexOfPeriod(timeline.getUidOfPeriod(i));
        }
        if (i2 == -1) {
            return null;
        }
        return timeline2.getUidOfPeriod(i2);
    }

    private Pair<Object, Long> resolveSeekPosition(SeekPosition seekPosition, boolean z) {
        Object resolveSubsequentPeriod;
        Timeline timeline = this.playbackInfo.timeline;
        Timeline timeline2 = seekPosition.timeline;
        if (timeline.isEmpty()) {
            return null;
        }
        if (timeline2.isEmpty()) {
            timeline2 = timeline;
        }
        try {
            Pair<Object, Long> periodPosition = timeline2.getPeriodPosition(this.window, this.period, seekPosition.windowIndex, seekPosition.windowPositionUs);
            if (timeline == timeline2 || timeline.getIndexOfPeriod(periodPosition.first) != -1) {
                return periodPosition;
            }
            if (z && (resolveSubsequentPeriod = resolveSubsequentPeriod(periodPosition.first, timeline2, timeline)) != null) {
                return getPeriodPosition(timeline, timeline.getPeriodByUid(resolveSubsequentPeriod, this.period).windowIndex, -9223372036854775807L);
            }
            return null;
        } catch (IndexOutOfBoundsException unused) {
        }
    }

    private Pair<Object, Long> getPeriodPosition(Timeline timeline, int i, long j) {
        return timeline.getPeriodPosition(this.window, this.period, i, j);
    }

    private void updatePeriods() throws ExoPlaybackException, IOException {
        MediaSource mediaSource2 = this.mediaSource;
        if (mediaSource2 != null) {
            if (this.pendingPrepareCount > 0) {
                mediaSource2.maybeThrowSourceInfoRefreshError();
                return;
            }
            maybeUpdateLoadingPeriod();
            maybeUpdateReadingPeriod();
            maybeUpdatePlayingPeriod();
        }
    }

    private void maybeUpdateLoadingPeriod() throws ExoPlaybackException, IOException {
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        if (this.queue.shouldLoadNextMediaPeriod()) {
            MediaPeriodInfo nextMediaPeriodInfo = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo);
            if (nextMediaPeriodInfo == null) {
                maybeThrowSourceInfoRefreshError();
            } else {
                MediaPeriodHolder enqueueNextMediaPeriodHolder = this.queue.enqueueNextMediaPeriodHolder(this.rendererCapabilities, this.trackSelector, this.loadControl.getAllocator(), this.mediaSource, nextMediaPeriodInfo, this.emptyTrackSelectorResult);
                enqueueNextMediaPeriodHolder.mediaPeriod.prepare(this, nextMediaPeriodInfo.startPositionUs);
                if (this.queue.getPlayingPeriod() == enqueueNextMediaPeriodHolder) {
                    resetRendererPosition(enqueueNextMediaPeriodHolder.getStartPositionRendererTime());
                }
                handleLoadingMediaPeriodChanged(false);
            }
        }
        if (this.shouldContinueLoading) {
            this.shouldContinueLoading = isLoadingPossible();
            updateIsLoading();
            return;
        }
        maybeContinueLoading();
    }

    private void maybeUpdateReadingPeriod() throws ExoPlaybackException {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod != null) {
            int i = 0;
            if (readingPeriod.getNext() == null) {
                if (readingPeriod.info.isFinal) {
                    while (true) {
                        Renderer[] rendererArr = this.renderers;
                        if (i < rendererArr.length) {
                            Renderer renderer = rendererArr[i];
                            SampleStream sampleStream = readingPeriod.sampleStreams[i];
                            if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                                renderer.setCurrentStreamFinal();
                            }
                            i++;
                        } else {
                            return;
                        }
                    }
                }
            } else if (hasReadingPeriodFinishedReading() && readingPeriod.getNext().prepared) {
                TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
                MediaPeriodHolder advanceReadingPeriod = this.queue.advanceReadingPeriod();
                TrackSelectorResult trackSelectorResult2 = advanceReadingPeriod.getTrackSelectorResult();
                if (advanceReadingPeriod.mediaPeriod.readDiscontinuity() != -9223372036854775807L) {
                    setAllRendererStreamsFinal();
                    return;
                }
                int i2 = 0;
                while (true) {
                    Renderer[] rendererArr2 = this.renderers;
                    if (i2 < rendererArr2.length) {
                        Renderer renderer2 = rendererArr2[i2];
                        if (trackSelectorResult.isRendererEnabled(i2) && !renderer2.isCurrentStreamFinal()) {
                            TrackSelection trackSelection = trackSelectorResult2.selections.get(i2);
                            boolean isRendererEnabled = trackSelectorResult2.isRendererEnabled(i2);
                            boolean z = this.rendererCapabilities[i2].getTrackType() == 6;
                            RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i2];
                            RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i2];
                            if (!isRendererEnabled || !rendererConfiguration2.equals(rendererConfiguration) || z) {
                                renderer2.setCurrentStreamFinal();
                            } else {
                                renderer2.replaceStream(getFormats(trackSelection), advanceReadingPeriod.sampleStreams[i2], advanceReadingPeriod.getRendererOffset());
                            }
                        }
                        i2++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private void maybeUpdatePlayingPeriod() throws ExoPlaybackException {
        boolean z = false;
        while (shouldAdvancePlayingPeriod()) {
            if (z) {
                maybeNotifyPlaybackInfoChanged();
            }
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            if (playingPeriod == this.queue.getReadingPeriod()) {
                setAllRendererStreamsFinal();
            }
            MediaPeriodHolder advancePlayingPeriod = this.queue.advancePlayingPeriod();
            updatePlayingPeriodRenderers(playingPeriod);
            this.playbackInfo = copyWithNewPosition(advancePlayingPeriod.info.id, advancePlayingPeriod.info.startPositionUs, advancePlayingPeriod.info.contentPositionUs);
            this.playbackInfoUpdate.setPositionDiscontinuity(playingPeriod.info.isLastInTimelinePeriod ? 0 : 3);
            updatePlaybackPositions();
            z = true;
        }
    }

    private boolean shouldAdvancePlayingPeriod() {
        MediaPeriodHolder playingPeriod;
        MediaPeriodHolder next;
        if (!this.playWhenReady || (playingPeriod = this.queue.getPlayingPeriod()) == null || (next = playingPeriod.getNext()) == null) {
            return false;
        }
        if ((playingPeriod != this.queue.getReadingPeriod() || hasReadingPeriodFinishedReading()) && this.rendererPositionUs >= next.getStartPositionRendererTime()) {
            return true;
        }
        return false;
    }

    private boolean hasReadingPeriodFinishedReading() {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (!readingPeriod.prepared) {
            return false;
        }
        int i = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i >= rendererArr.length) {
                return true;
            }
            Renderer renderer = rendererArr[i];
            SampleStream sampleStream = readingPeriod.sampleStreams[i];
            if (renderer.getStream() != sampleStream || (sampleStream != null && !renderer.hasReadStreamToEnd())) {
                return false;
            }
            i++;
        }
        return false;
    }

    private void setAllRendererStreamsFinal() {
        for (Renderer renderer : this.renderers) {
            if (renderer.getStream() != null) {
                renderer.setCurrentStreamFinal();
            }
        }
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (this.queue.isLoading(mediaPeriod)) {
            MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
            loadingPeriod.handlePrepared(this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.timeline);
            updateLoadControlTrackSelection(loadingPeriod.getTrackGroups(), loadingPeriod.getTrackSelectorResult());
            if (loadingPeriod == this.queue.getPlayingPeriod()) {
                resetRendererPosition(loadingPeriod.info.startPositionUs);
                updatePlayingPeriodRenderers((MediaPeriodHolder) null);
            }
            maybeContinueLoading();
        }
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            this.queue.reevaluateBuffer(this.rendererPositionUs);
            maybeContinueLoading();
        }
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters, boolean z) throws ExoPlaybackException {
        this.eventHandler.obtainMessage(1, z ? 1 : 0, 0, playbackParameters).sendToTarget();
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
        for (Renderer renderer : this.renderers) {
            if (renderer != null) {
                renderer.setOperatingRate(playbackParameters.speed);
            }
        }
    }

    private void maybeContinueLoading() {
        boolean shouldContinueLoading2 = shouldContinueLoading();
        this.shouldContinueLoading = shouldContinueLoading2;
        if (shouldContinueLoading2) {
            this.queue.getLoadingPeriod().continueLoading(this.rendererPositionUs);
        }
        updateIsLoading();
    }

    private boolean shouldContinueLoading() {
        if (!isLoadingPossible()) {
            return false;
        }
        return this.loadControl.shouldContinueLoading(getTotalBufferedDurationUs(this.queue.getLoadingPeriod().getNextLoadPositionUs()), this.mediaClock.getPlaybackParameters().speed);
    }

    private boolean isLoadingPossible() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (loadingPeriod == null || loadingPeriod.getNextLoadPositionUs() == Long.MIN_VALUE) {
            return false;
        }
        return true;
    }

    private void updateIsLoading() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        boolean z = this.shouldContinueLoading || (loadingPeriod != null && loadingPeriod.mediaPeriod.isLoading());
        if (z != this.playbackInfo.isLoading) {
            this.playbackInfo = this.playbackInfo.copyWithIsLoading(z);
        }
    }

    private PlaybackInfo copyWithNewPosition(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2) {
        this.deliverPendingMessageAtStartPositionRequired = true;
        return this.playbackInfo.copyWithNewPosition(mediaPeriodId, j, j2, getTotalBufferedDurationUs());
    }

    private void updatePlayingPeriodRenderers(MediaPeriodHolder mediaPeriodHolder) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null && mediaPeriodHolder != playingPeriod) {
            boolean[] zArr = new boolean[this.renderers.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                Renderer[] rendererArr = this.renderers;
                if (i < rendererArr.length) {
                    Renderer renderer = rendererArr[i];
                    zArr[i] = renderer.getState() != 0;
                    if (playingPeriod.getTrackSelectorResult().isRendererEnabled(i)) {
                        i2++;
                    }
                    if (zArr[i] && (!playingPeriod.getTrackSelectorResult().isRendererEnabled(i) || (renderer.isCurrentStreamFinal() && renderer.getStream() == mediaPeriodHolder.sampleStreams[i]))) {
                        disableRenderer(renderer);
                    }
                    i++;
                } else {
                    this.playbackInfo = this.playbackInfo.copyWithTrackInfo(playingPeriod.getTrackGroups(), playingPeriod.getTrackSelectorResult());
                    enableRenderers(zArr, i2);
                    return;
                }
            }
        }
    }

    private void enableRenderers(boolean[] zArr, int i) throws ExoPlaybackException {
        this.enabledRenderers = new Renderer[i];
        TrackSelectorResult trackSelectorResult = this.queue.getPlayingPeriod().getTrackSelectorResult();
        for (int i2 = 0; i2 < this.renderers.length; i2++) {
            if (!trackSelectorResult.isRendererEnabled(i2)) {
                this.renderers[i2].reset();
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.renderers.length; i4++) {
            if (trackSelectorResult.isRendererEnabled(i4)) {
                enableRenderer(i4, zArr[i4], i3);
                i3++;
            }
        }
    }

    private void enableRenderer(int i, boolean z, int i2) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        Renderer renderer = this.renderers[i];
        this.enabledRenderers[i2] = renderer;
        if (renderer.getState() == 0) {
            TrackSelectorResult trackSelectorResult = playingPeriod.getTrackSelectorResult();
            RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i];
            Format[] formats = getFormats(trackSelectorResult.selections.get(i));
            boolean z2 = this.playWhenReady && this.playbackInfo.playbackState == 3;
            renderer.enable(rendererConfiguration, formats, playingPeriod.sampleStreams[i], this.rendererPositionUs, !z && z2, playingPeriod.getRendererOffset());
            this.mediaClock.onRendererEnabled(renderer);
            if (z2) {
                renderer.start();
            }
        }
    }

    private void handleLoadingMediaPeriodChanged(boolean z) {
        long j;
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        MediaSource.MediaPeriodId mediaPeriodId = loadingPeriod == null ? this.playbackInfo.periodId : loadingPeriod.info.id;
        boolean z2 = !this.playbackInfo.loadingMediaPeriodId.equals(mediaPeriodId);
        if (z2) {
            this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(mediaPeriodId);
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (loadingPeriod == null) {
            j = playbackInfo2.positionUs;
        } else {
            j = loadingPeriod.getBufferedPositionUs();
        }
        playbackInfo2.bufferedPositionUs = j;
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if ((z2 || z) && loadingPeriod != null && loadingPeriod.prepared) {
            updateLoadControlTrackSelection(loadingPeriod.getTrackGroups(), loadingPeriod.getTrackSelectorResult());
        }
    }

    private long getTotalBufferedDurationUs() {
        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
    }

    private long getTotalBufferedDurationUs(long j) {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (loadingPeriod == null) {
            return 0;
        }
        return Math.max(0, j - loadingPeriod.toPeriodTime(this.rendererPositionUs));
    }

    private void updateLoadControlTrackSelection(TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult) {
        this.loadControl.onTracksSelected(this.renderers, trackGroupArray, trackSelectorResult.selections);
    }

    private void sendPlaybackParametersChangedInternal(PlaybackParameters playbackParameters, boolean z) {
        this.handler.obtainMessage(17, z ? 1 : 0, 0, playbackParameters).sendToTarget();
    }

    private static Format[] getFormats(TrackSelection trackSelection) {
        int length = trackSelection != null ? trackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        for (int i = 0; i < length; i++) {
            formatArr[i] = trackSelection.getFormat(i);
        }
        return formatArr;
    }

    private static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline2, int i, long j) {
            this.timeline = timeline2;
            this.windowIndex = i;
            this.windowPositionUs = j;
        }
    }

    private static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.message = playerMessage;
        }

        public void setResolvedPosition(int i, long j, Object obj) {
            this.resolvedPeriodIndex = i;
            this.resolvedPeriodTimeUs = j;
            this.resolvedPeriodUid = obj;
        }

        public int compareTo(PendingMessageInfo pendingMessageInfo) {
            if ((this.resolvedPeriodUid == null) != (pendingMessageInfo.resolvedPeriodUid == null)) {
                if (this.resolvedPeriodUid != null) {
                    return -1;
                }
                return 1;
            } else if (this.resolvedPeriodUid == null) {
                return 0;
            } else {
                int i = this.resolvedPeriodIndex - pendingMessageInfo.resolvedPeriodIndex;
                if (i != 0) {
                    return i;
                }
                return Util.compareLong(this.resolvedPeriodTimeUs, pendingMessageInfo.resolvedPeriodTimeUs);
            }
        }
    }

    private static final class MediaSourceRefreshInfo {
        public final MediaSource source;
        public final Timeline timeline;

        public MediaSourceRefreshInfo(MediaSource mediaSource, Timeline timeline2) {
            this.source = mediaSource;
            this.timeline = timeline2;
        }
    }

    private static final class PlaybackInfoUpdate {
        /* access modifiers changed from: private */
        public int discontinuityReason;
        private PlaybackInfo lastPlaybackInfo;
        /* access modifiers changed from: private */
        public int operationAcks;
        /* access modifiers changed from: private */
        public boolean positionDiscontinuity;

        private PlaybackInfoUpdate() {
        }

        public boolean hasPendingUpdate(PlaybackInfo playbackInfo) {
            return playbackInfo != this.lastPlaybackInfo || this.operationAcks > 0 || this.positionDiscontinuity;
        }

        public void reset(PlaybackInfo playbackInfo) {
            this.lastPlaybackInfo = playbackInfo;
            this.operationAcks = 0;
            this.positionDiscontinuity = false;
        }

        public void incrementPendingOperationAcks(int i) {
            this.operationAcks += i;
        }

        public void setPositionDiscontinuity(int i) {
            boolean z = true;
            if (!this.positionDiscontinuity || this.discontinuityReason == 4) {
                this.positionDiscontinuity = true;
                this.discontinuityReason = i;
                return;
            }
            if (i != 4) {
                z = false;
            }
            Assertions.checkArgument(z);
        }
    }
}
