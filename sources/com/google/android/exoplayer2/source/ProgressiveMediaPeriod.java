package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.IcyDataSource;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class ProgressiveMediaPeriod implements ExtractorOutput, MediaPeriod, SampleQueue.UpstreamFormatChangedListener, Loader.Callback<ExtractingLoadable>, Loader.ReleaseCallback {
    /* access modifiers changed from: private */
    public static final Format ICY_FORMAT = Format.createSampleFormat("icy", "application/x-icy", Long.MAX_VALUE);
    /* access modifiers changed from: private */
    public static final Map<String, String> ICY_METADATA_HEADERS = createIcyMetadataHeaders();
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    /* access modifiers changed from: private */
    public final long continueLoadingCheckIntervalBytes;
    /* access modifiers changed from: private */
    public final String customCacheKey;
    private final DataSource dataSource;
    private int dataType;
    private final DrmSessionManager<?> drmSessionManager;
    private long durationUs;
    private int enabledTrackCount;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private int extractedSamplesCountAtStartOfLoad;
    private final ExtractorHolder extractorHolder;
    /* access modifiers changed from: private */
    public final Handler handler;
    private boolean haveAudioVideoTracks;
    /* access modifiers changed from: private */
    public IcyHeaders icyHeaders;
    private boolean isLive;
    private long lastSeekPositionUs;
    private long length;
    private final Listener listener;
    private final ConditionVariable loadCondition;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader = new Loader("Loader:ProgressiveMediaPeriod");
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private boolean notifiedReadingStarted;
    private boolean notifyDiscontinuity;
    /* access modifiers changed from: private */
    public final Runnable onContinueLoadingRequestedRunnable;
    private boolean pendingDeferredRetry;
    private long pendingResetPositionUs;
    private boolean prepared;
    private PreparedState preparedState;
    private boolean released;
    private TrackId[] sampleQueueTrackIds;
    private SampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private final Uri uri;

    interface Listener {
        void onSourceInfoRefreshed(long j, boolean z, boolean z2);
    }

    public void reevaluateBuffer(long j) {
    }

    public ProgressiveMediaPeriod(Uri uri2, DataSource dataSource2, Extractor[] extractorArr, DrmSessionManager<?> drmSessionManager2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher2, Listener listener2, Allocator allocator2, String str, int i) {
        this.uri = uri2;
        this.dataSource = dataSource2;
        this.drmSessionManager = drmSessionManager2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.eventDispatcher = eventDispatcher2;
        this.listener = listener2;
        this.allocator = allocator2;
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = (long) i;
        this.extractorHolder = new ExtractorHolder(extractorArr);
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new Runnable() {
            public final void run() {
                ProgressiveMediaPeriod.this.maybeFinishPrepare();
            }
        };
        this.onContinueLoadingRequestedRunnable = new Runnable() {
            public final void run() {
                ProgressiveMediaPeriod.this.lambda$new$0$ProgressiveMediaPeriod();
            }
        };
        this.handler = new Handler();
        this.sampleQueueTrackIds = new TrackId[0];
        this.sampleQueues = new SampleQueue[0];
        this.pendingResetPositionUs = -9223372036854775807L;
        this.length = -1;
        this.durationUs = -9223372036854775807L;
        this.dataType = 1;
        eventDispatcher2.mediaPeriodCreated();
    }

    public /* synthetic */ void lambda$new$0$ProgressiveMediaPeriod() {
        if (!this.released) {
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
        }
    }

    public void release() {
        if (this.prepared) {
            for (SampleQueue preRelease : this.sampleQueues) {
                preRelease.preRelease();
            }
        }
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages((Object) null);
        this.callback = null;
        this.released = true;
        this.eventDispatcher.mediaPeriodReleased();
    }

    public void onLoaderReleased() {
        for (SampleQueue release : this.sampleQueues) {
            release.release();
        }
        this.extractorHolder.release();
    }

    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        this.loadCondition.open();
        startLoading();
    }

    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
        if (this.loadingFinished && !this.prepared) {
            throw new ParserException("Loading finished before preparation is complete.");
        }
    }

    public TrackGroupArray getTrackGroups() {
        return getPreparedState().tracks;
    }

    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        PreparedState preparedState2 = getPreparedState();
        TrackGroupArray trackGroupArray = preparedState2.tracks;
        boolean[] zArr3 = preparedState2.trackEnabledStates;
        int i = this.enabledTrackCount;
        int i2 = 0;
        for (int i3 = 0; i3 < trackSelectionArr.length; i3++) {
            if (sampleStreamArr[i3] != null && (trackSelectionArr[i3] == null || !zArr[i3])) {
                int access$000 = sampleStreamArr[i3].track;
                Assertions.checkState(zArr3[access$000]);
                this.enabledTrackCount--;
                zArr3[access$000] = false;
                sampleStreamArr[i3] = null;
            }
        }
        boolean z = !this.seenFirstTrackSelection ? j != 0 : i == 0;
        for (int i4 = 0; i4 < trackSelectionArr.length; i4++) {
            if (sampleStreamArr[i4] == null && trackSelectionArr[i4] != null) {
                TrackSelection trackSelection = trackSelectionArr[i4];
                Assertions.checkState(trackSelection.length() == 1);
                Assertions.checkState(trackSelection.getIndexInTrackGroup(0) == 0);
                int indexOf = trackGroupArray.indexOf(trackSelection.getTrackGroup());
                Assertions.checkState(!zArr3[indexOf]);
                this.enabledTrackCount++;
                zArr3[indexOf] = true;
                sampleStreamArr[i4] = new SampleStreamImpl(indexOf);
                zArr2[i4] = true;
                if (!z) {
                    SampleQueue sampleQueue = this.sampleQueues[indexOf];
                    sampleQueue.rewind();
                    z = sampleQueue.advanceTo(j, true, true) == -1 && sampleQueue.getReadIndex() != 0;
                }
            }
        }
        if (this.enabledTrackCount == 0) {
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length2 = sampleQueueArr.length;
                while (i2 < length2) {
                    sampleQueueArr[i2].discardToEnd();
                    i2++;
                }
                this.loader.cancelLoading();
            } else {
                SampleQueue[] sampleQueueArr2 = this.sampleQueues;
                int length3 = sampleQueueArr2.length;
                while (i2 < length3) {
                    sampleQueueArr2[i2].reset();
                    i2++;
                }
            }
        } else if (z) {
            j = seekToUs(j);
            while (i2 < sampleStreamArr.length) {
                if (sampleStreamArr[i2] != null) {
                    zArr2[i2] = true;
                }
                i2++;
            }
        }
        this.seenFirstTrackSelection = true;
        return j;
    }

    public void discardBuffer(long j, boolean z) {
        if (!isPendingReset()) {
            boolean[] zArr = getPreparedState().trackEnabledStates;
            int length2 = this.sampleQueues.length;
            for (int i = 0; i < length2; i++) {
                this.sampleQueues[i].discardTo(j, z, zArr[i]);
            }
        }
    }

    public boolean continueLoading(long j) {
        if (this.loadingFinished || this.loader.hasFatalError() || this.pendingDeferredRetry) {
            return false;
        }
        if (this.prepared && this.enabledTrackCount == 0) {
            return false;
        }
        boolean open = this.loadCondition.open();
        if (this.loader.isLoading()) {
            return open;
        }
        startLoading();
        return true;
    }

    public boolean isLoading() {
        return this.loader.isLoading() && this.loadCondition.isOpen();
    }

    public long getNextLoadPositionUs() {
        if (this.enabledTrackCount == 0) {
            return Long.MIN_VALUE;
        }
        return getBufferedPositionUs();
    }

    public long readDiscontinuity() {
        if (!this.notifiedReadingStarted) {
            this.eventDispatcher.readingStarted();
            this.notifiedReadingStarted = true;
        }
        if (!this.notifyDiscontinuity) {
            return -9223372036854775807L;
        }
        if (!this.loadingFinished && getExtractedSamplesCount() <= this.extractedSamplesCountAtStartOfLoad) {
            return -9223372036854775807L;
        }
        this.notifyDiscontinuity = false;
        return this.lastSeekPositionUs;
    }

    public long getBufferedPositionUs() {
        long j;
        boolean[] zArr = getPreparedState().trackIsAudioVideoFlags;
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.haveAudioVideoTracks) {
            int length2 = this.sampleQueues.length;
            j = Long.MAX_VALUE;
            for (int i = 0; i < length2; i++) {
                if (zArr[i] && !this.sampleQueues[i].isLastSampleQueued()) {
                    j = Math.min(j, this.sampleQueues[i].getLargestQueuedTimestampUs());
                }
            }
        } else {
            j = Long.MAX_VALUE;
        }
        if (j == Long.MAX_VALUE) {
            j = getLargestQueuedTimestampUs();
        }
        return j == Long.MIN_VALUE ? this.lastSeekPositionUs : j;
    }

    public long seekToUs(long j) {
        PreparedState preparedState2 = getPreparedState();
        SeekMap seekMap2 = preparedState2.seekMap;
        boolean[] zArr = preparedState2.trackIsAudioVideoFlags;
        if (!seekMap2.isSeekable()) {
            j = 0;
        }
        this.notifyDiscontinuity = false;
        this.lastSeekPositionUs = j;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j;
            return j;
        } else if (this.dataType != 7 && seekInsideBufferUs(zArr, j)) {
            return j;
        } else {
            this.pendingDeferredRetry = false;
            this.pendingResetPositionUs = j;
            this.loadingFinished = false;
            if (this.loader.isLoading()) {
                this.loader.cancelLoading();
            } else {
                this.loader.clearFatalError();
                for (SampleQueue reset : this.sampleQueues) {
                    reset.reset();
                }
            }
            return j;
        }
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        SeekMap seekMap2 = getPreparedState().seekMap;
        if (!seekMap2.isSeekable()) {
            return 0;
        }
        SeekMap.SeekPoints seekPoints = seekMap2.getSeekPoints(j);
        return Util.resolveSeekPositionUs(j, seekParameters, seekPoints.first.timeUs, seekPoints.second.timeUs);
    }

    /* access modifiers changed from: package-private */
    public boolean isReady(int i) {
        return !suppressRead() && this.sampleQueues[i].isReady(this.loadingFinished);
    }

    /* access modifiers changed from: package-private */
    public void maybeThrowError(int i) throws IOException {
        this.sampleQueues[i].maybeThrowError();
        maybeThrowError();
    }

    /* access modifiers changed from: package-private */
    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError(this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType));
    }

    /* access modifiers changed from: package-private */
    public int readData(int i, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (suppressRead()) {
            return -3;
        }
        maybeNotifyDownstreamFormat(i);
        int read = this.sampleQueues[i].read(formatHolder, decoderInputBuffer, z, this.loadingFinished, this.lastSeekPositionUs);
        if (read == -3) {
            maybeStartDeferredRetry(i);
        }
        return read;
    }

    /* access modifiers changed from: package-private */
    public int skipData(int i, long j) {
        int i2 = 0;
        if (suppressRead()) {
            return 0;
        }
        maybeNotifyDownstreamFormat(i);
        SampleQueue sampleQueue = this.sampleQueues[i];
        if (!this.loadingFinished || j <= sampleQueue.getLargestQueuedTimestampUs()) {
            int advanceTo = sampleQueue.advanceTo(j, true, true);
            if (advanceTo != -1) {
                i2 = advanceTo;
            }
        } else {
            i2 = sampleQueue.advanceToEnd();
        }
        if (i2 == 0) {
            maybeStartDeferredRetry(i);
        }
        return i2;
    }

    private void maybeNotifyDownstreamFormat(int i) {
        PreparedState preparedState2 = getPreparedState();
        boolean[] zArr = preparedState2.trackNotifiedDownstreamFormats;
        if (!zArr[i]) {
            Format format = preparedState2.tracks.get(i).getFormat(0);
            this.eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(format.sampleMimeType), format, 0, (Object) null, this.lastSeekPositionUs);
            zArr[i] = true;
        }
    }

    private void maybeStartDeferredRetry(int i) {
        boolean[] zArr = getPreparedState().trackIsAudioVideoFlags;
        if (this.pendingDeferredRetry && zArr[i]) {
            if (!this.sampleQueues[i].isReady(false)) {
                this.pendingResetPositionUs = 0;
                this.pendingDeferredRetry = false;
                this.notifyDiscontinuity = true;
                this.lastSeekPositionUs = 0;
                this.extractedSamplesCountAtStartOfLoad = 0;
                for (SampleQueue reset : this.sampleQueues) {
                    reset.reset();
                }
                ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
            }
        }
    }

    private boolean suppressRead() {
        return this.notifyDiscontinuity || isPendingReset();
    }

    public void onLoadCompleted(ExtractingLoadable extractingLoadable, long j, long j2) {
        SeekMap seekMap2;
        if (this.durationUs == -9223372036854775807L && (seekMap2 = this.seekMap) != null) {
            boolean isSeekable = seekMap2.isSeekable();
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs();
            long j3 = largestQueuedTimestampUs == Long.MIN_VALUE ? 0 : largestQueuedTimestampUs + 10000;
            this.durationUs = j3;
            this.listener.onSourceInfoRefreshed(j3, isSeekable, this.isLive);
        }
        this.eventDispatcher.loadCompleted(extractingLoadable.dataSpec, extractingLoadable.dataSource.getLastOpenedUri(), extractingLoadable.dataSource.getLastResponseHeaders(), 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs, j, j2, extractingLoadable.dataSource.getBytesRead());
        copyLengthFromLoader(extractingLoadable);
        this.loadingFinished = true;
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }

    public void onLoadCanceled(ExtractingLoadable extractingLoadable, long j, long j2, boolean z) {
        this.eventDispatcher.loadCanceled(extractingLoadable.dataSpec, extractingLoadable.dataSource.getLastOpenedUri(), extractingLoadable.dataSource.getLastResponseHeaders(), 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs, j, j2, extractingLoadable.dataSource.getBytesRead());
        if (!z) {
            copyLengthFromLoader(extractingLoadable);
            for (SampleQueue reset : this.sampleQueues) {
                reset.reset();
            }
            if (this.enabledTrackCount > 0) {
                ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
            }
        }
    }

    public Loader.LoadErrorAction onLoadError(ExtractingLoadable extractingLoadable, long j, long j2, IOException iOException, int i) {
        Loader.LoadErrorAction loadErrorAction;
        ExtractingLoadable extractingLoadable2;
        boolean z;
        copyLengthFromLoader(extractingLoadable);
        long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(this.dataType, j2, iOException, i);
        if (retryDelayMsFor == -9223372036854775807L) {
            loadErrorAction = Loader.DONT_RETRY_FATAL;
            ExtractingLoadable extractingLoadable3 = extractingLoadable;
        } else {
            int extractedSamplesCount = getExtractedSamplesCount();
            if (extractedSamplesCount > this.extractedSamplesCountAtStartOfLoad) {
                extractingLoadable2 = extractingLoadable;
                z = true;
            } else {
                z = false;
                extractingLoadable2 = extractingLoadable;
            }
            loadErrorAction = configureRetry(extractingLoadable2, extractedSamplesCount) ? Loader.createRetryAction(z, retryDelayMsFor) : Loader.DONT_RETRY;
        }
        this.eventDispatcher.loadError(extractingLoadable.dataSpec, extractingLoadable.dataSource.getLastOpenedUri(), extractingLoadable.dataSource.getLastResponseHeaders(), 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs, j, j2, extractingLoadable.dataSource.getBytesRead(), iOException, !loadErrorAction.isRetry());
        return loadErrorAction;
    }

    public TrackOutput track(int i, int i2) {
        return prepareTrackOutput(new TrackId(i, false));
    }

    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void seekMap(SeekMap seekMap2) {
        if (this.icyHeaders != null) {
            seekMap2 = new SeekMap.Unseekable(-9223372036854775807L);
        }
        this.seekMap = seekMap2;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    /* access modifiers changed from: package-private */
    public TrackOutput icyTrack() {
        return prepareTrackOutput(new TrackId(0, true));
    }

    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    private TrackOutput prepareTrackOutput(TrackId trackId) {
        int length2 = this.sampleQueues.length;
        for (int i = 0; i < length2; i++) {
            if (trackId.equals(this.sampleQueueTrackIds[i])) {
                return this.sampleQueues[i];
            }
        }
        SampleQueue sampleQueue = new SampleQueue(this.allocator, this.drmSessionManager);
        sampleQueue.setUpstreamFormatChangeListener(this);
        int i2 = length2 + 1;
        TrackId[] trackIdArr = (TrackId[]) Arrays.copyOf(this.sampleQueueTrackIds, i2);
        trackIdArr[length2] = trackId;
        this.sampleQueueTrackIds = (TrackId[]) Util.castNonNullTypeArray(trackIdArr);
        SampleQueue[] sampleQueueArr = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i2);
        sampleQueueArr[length2] = sampleQueue;
        this.sampleQueues = (SampleQueue[]) Util.castNonNullTypeArray(sampleQueueArr);
        return sampleQueue;
    }

    /* access modifiers changed from: private */
    public void maybeFinishPrepare() {
        Metadata metadata;
        SeekMap seekMap2 = this.seekMap;
        if (!this.released && !this.prepared && this.sampleQueuesBuilt && seekMap2 != null) {
            SampleQueue[] sampleQueueArr = this.sampleQueues;
            int length2 = sampleQueueArr.length;
            boolean z = false;
            int i = 0;
            while (i < length2) {
                if (sampleQueueArr[i].getUpstreamFormat() != null) {
                    i++;
                } else {
                    return;
                }
            }
            this.loadCondition.close();
            int length3 = this.sampleQueues.length;
            TrackGroup[] trackGroupArr = new TrackGroup[length3];
            boolean[] zArr = new boolean[length3];
            this.durationUs = seekMap2.getDurationUs();
            for (int i2 = 0; i2 < length3; i2++) {
                Format upstreamFormat = this.sampleQueues[i2].getUpstreamFormat();
                String str = upstreamFormat.sampleMimeType;
                boolean isAudio = MimeTypes.isAudio(str);
                boolean z2 = isAudio || MimeTypes.isVideo(str);
                zArr[i2] = z2;
                this.haveAudioVideoTracks = z2 | this.haveAudioVideoTracks;
                IcyHeaders icyHeaders2 = this.icyHeaders;
                if (icyHeaders2 != null) {
                    if (isAudio || this.sampleQueueTrackIds[i2].isIcyTrack) {
                        Metadata metadata2 = upstreamFormat.metadata;
                        if (metadata2 == null) {
                            metadata = new Metadata(icyHeaders2);
                        } else {
                            metadata = metadata2.copyWithAppendedEntries(icyHeaders2);
                        }
                        upstreamFormat = upstreamFormat.copyWithMetadata(metadata);
                    }
                    if (isAudio && upstreamFormat.bitrate == -1 && icyHeaders2.bitrate != -1) {
                        upstreamFormat = upstreamFormat.copyWithBitrate(icyHeaders2.bitrate);
                    }
                }
                trackGroupArr[i2] = new TrackGroup(upstreamFormat);
            }
            if (this.length == -1 && seekMap2.getDurationUs() == -9223372036854775807L) {
                z = true;
            }
            this.isLive = z;
            this.dataType = z ? 7 : 1;
            this.preparedState = new PreparedState(seekMap2, new TrackGroupArray(trackGroupArr), zArr);
            this.prepared = true;
            this.listener.onSourceInfoRefreshed(this.durationUs, seekMap2.isSeekable(), this.isLive);
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
        }
    }

    private PreparedState getPreparedState() {
        return (PreparedState) Assertions.checkNotNull(this.preparedState);
    }

    private void copyLengthFromLoader(ExtractingLoadable extractingLoadable) {
        if (this.length == -1) {
            this.length = extractingLoadable.length;
        }
    }

    private void startLoading() {
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.uri, this.dataSource, this.extractorHolder, this, this.loadCondition);
        if (this.prepared) {
            SeekMap seekMap2 = getPreparedState().seekMap;
            Assertions.checkState(isPendingReset());
            long j = this.durationUs;
            if (j == -9223372036854775807L || this.pendingResetPositionUs <= j) {
                extractingLoadable.setLoadPosition(seekMap2.getSeekPoints(this.pendingResetPositionUs).first.position, this.pendingResetPositionUs);
                this.pendingResetPositionUs = -9223372036854775807L;
            } else {
                this.loadingFinished = true;
                this.pendingResetPositionUs = -9223372036854775807L;
                return;
            }
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        this.eventDispatcher.loadStarted(extractingLoadable.dataSpec, 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs, this.loader.startLoading(extractingLoadable, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType)));
    }

    private boolean configureRetry(ExtractingLoadable extractingLoadable, int i) {
        SeekMap seekMap2;
        if (this.length == -1 && ((seekMap2 = this.seekMap) == null || seekMap2.getDurationUs() == -9223372036854775807L)) {
            if (!this.prepared || suppressRead()) {
                this.notifyDiscontinuity = this.prepared;
                this.lastSeekPositionUs = 0;
                this.extractedSamplesCountAtStartOfLoad = 0;
                for (SampleQueue reset : this.sampleQueues) {
                    reset.reset();
                }
                extractingLoadable.setLoadPosition(0, 0);
                return true;
            }
            this.pendingDeferredRetry = true;
            return false;
        }
        this.extractedSamplesCountAtStartOfLoad = i;
        return true;
    }

    private boolean seekInsideBufferUs(boolean[] zArr, long j) {
        int length2 = this.sampleQueues.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= length2) {
                return true;
            }
            SampleQueue sampleQueue = this.sampleQueues[i];
            sampleQueue.rewind();
            if (sampleQueue.advanceTo(j, true, false) == -1) {
                z = false;
            }
            if (z || (!zArr[i] && this.haveAudioVideoTracks)) {
                i++;
            }
        }
        return false;
    }

    private int getExtractedSamplesCount() {
        int i = 0;
        for (SampleQueue writeIndex : this.sampleQueues) {
            i += writeIndex.getWriteIndex();
        }
        return i;
    }

    /* access modifiers changed from: private */
    public long getLargestQueuedTimestampUs() {
        long j = Long.MIN_VALUE;
        for (SampleQueue largestQueuedTimestampUs : this.sampleQueues) {
            j = Math.max(j, largestQueuedTimestampUs.getLargestQueuedTimestampUs());
        }
        return j;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != -9223372036854775807L;
    }

    private final class SampleStreamImpl implements SampleStream {
        /* access modifiers changed from: private */
        public final int track;

        public SampleStreamImpl(int i) {
            this.track = i;
        }

        public boolean isReady() {
            return ProgressiveMediaPeriod.this.isReady(this.track);
        }

        public void maybeThrowError() throws IOException {
            ProgressiveMediaPeriod.this.maybeThrowError(this.track);
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            return ProgressiveMediaPeriod.this.readData(this.track, formatHolder, decoderInputBuffer, z);
        }

        public int skipData(long j) {
            return ProgressiveMediaPeriod.this.skipData(this.track, j);
        }
    }

    final class ExtractingLoadable implements IcyDataSource.Listener, Loader.Loadable {
        /* access modifiers changed from: private */
        public final StatsDataSource dataSource;
        /* access modifiers changed from: private */
        public DataSpec dataSpec = buildDataSpec(0);
        private final ExtractorHolder extractorHolder;
        private final ExtractorOutput extractorOutput;
        private TrackOutput icyTrackOutput;
        /* access modifiers changed from: private */
        public long length = -1;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        private boolean pendingExtractorSeek = true;
        private final PositionHolder positionHolder = new PositionHolder();
        /* access modifiers changed from: private */
        public long seekTimeUs;
        private boolean seenIcyMetadata;
        private final Uri uri;

        public ExtractingLoadable(Uri uri2, DataSource dataSource2, ExtractorHolder extractorHolder2, ExtractorOutput extractorOutput2, ConditionVariable conditionVariable) {
            this.uri = uri2;
            this.dataSource = new StatsDataSource(dataSource2);
            this.extractorHolder = extractorHolder2;
            this.extractorOutput = extractorOutput2;
            this.loadCondition = conditionVariable;
        }

        public void cancelLoad() {
            this.loadCanceled = true;
        }

        public void load() throws IOException, InterruptedException {
            int i = 0;
            while (i == 0 && !this.loadCanceled) {
                DefaultExtractorInput defaultExtractorInput = null;
                try {
                    long j = this.positionHolder.position;
                    DataSpec buildDataSpec = buildDataSpec(j);
                    this.dataSpec = buildDataSpec;
                    long open = this.dataSource.open(buildDataSpec);
                    this.length = open;
                    if (open != -1) {
                        this.length = open + j;
                    }
                    Uri uri2 = (Uri) Assertions.checkNotNull(this.dataSource.getUri());
                    IcyHeaders unused = ProgressiveMediaPeriod.this.icyHeaders = IcyHeaders.parse(this.dataSource.getResponseHeaders());
                    DataSource dataSource2 = this.dataSource;
                    if (!(ProgressiveMediaPeriod.this.icyHeaders == null || ProgressiveMediaPeriod.this.icyHeaders.metadataInterval == -1)) {
                        dataSource2 = new IcyDataSource(this.dataSource, ProgressiveMediaPeriod.this.icyHeaders.metadataInterval, this);
                        TrackOutput icyTrack = ProgressiveMediaPeriod.this.icyTrack();
                        this.icyTrackOutput = icyTrack;
                        icyTrack.format(ProgressiveMediaPeriod.ICY_FORMAT);
                    }
                    DefaultExtractorInput defaultExtractorInput2 = new DefaultExtractorInput(dataSource2, j, this.length);
                    try {
                        Extractor selectExtractor = this.extractorHolder.selectExtractor(defaultExtractorInput2, this.extractorOutput, uri2);
                        if (ProgressiveMediaPeriod.this.icyHeaders != null && (selectExtractor instanceof Mp3Extractor)) {
                            ((Mp3Extractor) selectExtractor).disableSeeking();
                        }
                        if (this.pendingExtractorSeek) {
                            selectExtractor.seek(j, this.seekTimeUs);
                            this.pendingExtractorSeek = false;
                        }
                        while (i == 0 && !this.loadCanceled) {
                            this.loadCondition.block();
                            i = selectExtractor.read(defaultExtractorInput2, this.positionHolder);
                            if (defaultExtractorInput2.getPosition() > ProgressiveMediaPeriod.this.continueLoadingCheckIntervalBytes + j) {
                                j = defaultExtractorInput2.getPosition();
                                this.loadCondition.close();
                                ProgressiveMediaPeriod.this.handler.post(ProgressiveMediaPeriod.this.onContinueLoadingRequestedRunnable);
                            }
                        }
                        if (i == 1) {
                            i = 0;
                        } else {
                            this.positionHolder.position = defaultExtractorInput2.getPosition();
                        }
                        Util.closeQuietly((DataSource) this.dataSource);
                    } catch (Throwable th) {
                        th = th;
                        defaultExtractorInput = defaultExtractorInput2;
                        this.positionHolder.position = defaultExtractorInput.getPosition();
                        Util.closeQuietly((DataSource) this.dataSource);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (!(i == 1 || defaultExtractorInput == null)) {
                        this.positionHolder.position = defaultExtractorInput.getPosition();
                    }
                    Util.closeQuietly((DataSource) this.dataSource);
                    throw th;
                }
            }
        }

        public void onIcyMetadata(ParsableByteArray parsableByteArray) {
            long j;
            if (!this.seenIcyMetadata) {
                j = this.seekTimeUs;
            } else {
                j = Math.max(ProgressiveMediaPeriod.this.getLargestQueuedTimestampUs(), this.seekTimeUs);
            }
            int bytesLeft = parsableByteArray.bytesLeft();
            TrackOutput trackOutput = (TrackOutput) Assertions.checkNotNull(this.icyTrackOutput);
            trackOutput.sampleData(parsableByteArray, bytesLeft);
            trackOutput.sampleMetadata(j, 1, bytesLeft, 0, (TrackOutput.CryptoData) null);
            this.seenIcyMetadata = true;
        }

        private DataSpec buildDataSpec(long j) {
            return new DataSpec(this.uri, j, -1, ProgressiveMediaPeriod.this.customCacheKey, 6, (Map<String, String>) ProgressiveMediaPeriod.ICY_METADATA_HEADERS);
        }

        /* access modifiers changed from: private */
        public void setLoadPosition(long j, long j2) {
            this.positionHolder.position = j;
            this.seekTimeUs = j2;
            this.pendingExtractorSeek = true;
            this.seenIcyMetadata = false;
        }
    }

    private static final class ExtractorHolder {
        private Extractor extractor;
        private final Extractor[] extractors;

        public ExtractorHolder(Extractor[] extractorArr) {
            this.extractors = extractorArr;
        }

        public Extractor selectExtractor(ExtractorInput extractorInput, ExtractorOutput extractorOutput, Uri uri) throws IOException, InterruptedException {
            Extractor extractor2 = this.extractor;
            if (extractor2 != null) {
                return extractor2;
            }
            Extractor[] extractorArr = this.extractors;
            int i = 0;
            if (extractorArr.length == 1) {
                this.extractor = extractorArr[0];
            } else {
                int length = extractorArr.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Extractor extractor3 = extractorArr[i];
                    try {
                        if (extractor3.sniff(extractorInput)) {
                            this.extractor = extractor3;
                            extractorInput.resetPeekPosition();
                            break;
                        }
                        extractorInput.resetPeekPosition();
                        i++;
                    } catch (EOFException unused) {
                    } catch (Throwable th) {
                        extractorInput.resetPeekPosition();
                        throw th;
                    }
                }
                if (this.extractor == null) {
                    throw new UnrecognizedInputFormatException("None of the available extractors (" + Util.getCommaDelimitedSimpleClassNames(this.extractors) + ") could read the stream.", uri);
                }
            }
            this.extractor.init(extractorOutput);
            return this.extractor;
        }

        public void release() {
            Extractor extractor2 = this.extractor;
            if (extractor2 != null) {
                extractor2.release();
                this.extractor = null;
            }
        }
    }

    private static final class PreparedState {
        public final SeekMap seekMap;
        public final boolean[] trackEnabledStates;
        public final boolean[] trackIsAudioVideoFlags;
        public final boolean[] trackNotifiedDownstreamFormats;
        public final TrackGroupArray tracks;

        public PreparedState(SeekMap seekMap2, TrackGroupArray trackGroupArray, boolean[] zArr) {
            this.seekMap = seekMap2;
            this.tracks = trackGroupArray;
            this.trackIsAudioVideoFlags = zArr;
            this.trackEnabledStates = new boolean[trackGroupArray.length];
            this.trackNotifiedDownstreamFormats = new boolean[trackGroupArray.length];
        }
    }

    private static final class TrackId {
        public final int id;
        public final boolean isIcyTrack;

        public TrackId(int i, boolean z) {
            this.id = i;
            this.isIcyTrack = z;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TrackId trackId = (TrackId) obj;
            if (this.id == trackId.id && this.isIcyTrack == trackId.isIcyTrack) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.id * 31) + (this.isIcyTrack ? 1 : 0);
        }
    }

    private static Map<String, String> createIcyMetadataHeaders() {
        HashMap hashMap = new HashMap();
        hashMap.put("Icy-MetaData", "1");
        return Collections.unmodifiableMap(hashMap);
    }
}
