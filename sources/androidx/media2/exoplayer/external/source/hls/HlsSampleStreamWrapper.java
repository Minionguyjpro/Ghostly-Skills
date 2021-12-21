package androidx.media2.exoplayer.external.source.hls;

import android.net.Uri;
import android.os.Handler;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.FormatHolder;
import androidx.media2.exoplayer.external.decoder.DecoderInputBuffer;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.DummyTrackOutput;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.SeekMap;
import androidx.media2.exoplayer.external.extractor.TrackOutput;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.metadata.id3.PrivFrame;
import androidx.media2.exoplayer.external.source.MediaSourceEventListener;
import androidx.media2.exoplayer.external.source.SampleQueue;
import androidx.media2.exoplayer.external.source.SampleStream;
import androidx.media2.exoplayer.external.source.SequenceableLoader;
import androidx.media2.exoplayer.external.source.TrackGroup;
import androidx.media2.exoplayer.external.source.TrackGroupArray;
import androidx.media2.exoplayer.external.source.chunk.Chunk;
import androidx.media2.exoplayer.external.source.hls.HlsChunkSource;
import androidx.media2.exoplayer.external.upstream.Allocator;
import androidx.media2.exoplayer.external.upstream.LoadErrorHandlingPolicy;
import androidx.media2.exoplayer.external.upstream.Loader;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.MimeTypes;
import androidx.media2.exoplayer.external.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class HlsSampleStreamWrapper implements ExtractorOutput, SampleQueue.UpstreamFormatChangedListener, SequenceableLoader, Loader.Callback<Chunk>, Loader.ReleaseCallback {
    private final Allocator allocator;
    private int audioSampleQueueIndex = -1;
    private boolean audioSampleQueueMappingDone;
    private final Callback callback;
    private final HlsChunkSource chunkSource;
    private int chunkUid;
    private Format downstreamTrackFormat;
    private int enabledTrackGroupCount;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final Handler handler;
    private boolean haveAudioVideoSampleQueues;
    private final ArrayList<HlsSampleStream> hlsSampleStreams;
    private long lastSeekPositionUs;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader = new Loader("Loader:HlsSampleStreamWrapper");
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final ArrayList<HlsMediaChunk> mediaChunks;
    private final Format muxedAudioFormat;
    private final HlsChunkSource.HlsChunkHolder nextChunkHolder = new HlsChunkSource.HlsChunkHolder();
    private final Runnable onTracksEndedRunnable;
    private TrackGroupArray optionalTrackGroups;
    private final Map<String, DrmInitData> overridingDrmInitData;
    private long pendingResetPositionUs;
    private boolean pendingResetUpstreamFormats;
    private boolean prepared;
    private int primarySampleQueueIndex;
    private int primarySampleQueueType;
    private int primaryTrackGroupIndex;
    private final List<HlsMediaChunk> readOnlyMediaChunks;
    private boolean released;
    private long sampleOffsetUs;
    private boolean[] sampleQueueIsAudioVideoFlags = new boolean[0];
    private int[] sampleQueueTrackIds = new int[0];
    private SampleQueue[] sampleQueues = new SampleQueue[0];
    private boolean sampleQueuesBuilt;
    private boolean[] sampleQueuesEnabledStates = new boolean[0];
    private boolean seenFirstTrackSelection;
    private int[] trackGroupToSampleQueueIndex;
    private TrackGroupArray trackGroups;
    private final int trackType;
    private boolean tracksEnded;
    private Format upstreamTrackFormat;
    private int videoSampleQueueIndex = -1;
    private boolean videoSampleQueueMappingDone;

    public interface Callback extends SequenceableLoader.Callback<HlsSampleStreamWrapper> {
        void onPlaylistRefreshRequired(Uri uri);

        void onPrepared();
    }

    private static int getTrackTypeScore(int i) {
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 0 : 1;
        }
        return 3;
    }

    public void reevaluateBuffer(long j) {
    }

    public void seekMap(SeekMap seekMap) {
    }

    public HlsSampleStreamWrapper(int i, Callback callback2, HlsChunkSource hlsChunkSource, Map<String, DrmInitData> map, Allocator allocator2, long j, Format format, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher2) {
        this.trackType = i;
        this.callback = callback2;
        this.chunkSource = hlsChunkSource;
        this.overridingDrmInitData = map;
        this.allocator = allocator2;
        this.muxedAudioFormat = format;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.eventDispatcher = eventDispatcher2;
        ArrayList<HlsMediaChunk> arrayList = new ArrayList<>();
        this.mediaChunks = arrayList;
        this.readOnlyMediaChunks = Collections.unmodifiableList(arrayList);
        this.hlsSampleStreams = new ArrayList<>();
        this.maybeFinishPrepareRunnable = new HlsSampleStreamWrapper$$Lambda$0(this);
        this.onTracksEndedRunnable = new HlsSampleStreamWrapper$$Lambda$1(this);
        this.handler = new Handler();
        this.lastSeekPositionUs = j;
        this.pendingResetPositionUs = j;
    }

    public void continuePreparing() {
        if (!this.prepared) {
            continueLoading(this.lastSeekPositionUs);
        }
    }

    public void prepareWithMasterPlaylistInfo(TrackGroupArray trackGroupArray, int i, TrackGroupArray trackGroupArray2) {
        this.prepared = true;
        this.trackGroups = trackGroupArray;
        this.optionalTrackGroups = trackGroupArray2;
        this.primaryTrackGroupIndex = i;
        Handler handler2 = this.handler;
        Callback callback2 = this.callback;
        callback2.getClass();
        handler2.post(HlsSampleStreamWrapper$$Lambda$2.get$Lambda(callback2));
    }

    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public int bindSampleQueueToSampleStream(int i) {
        int i2 = this.trackGroupToSampleQueueIndex[i];
        if (i2 != -1) {
            boolean[] zArr = this.sampleQueuesEnabledStates;
            if (zArr[i2]) {
                return -2;
            }
            zArr[i2] = true;
            return i2;
        } else if (this.optionalTrackGroups.indexOf(this.trackGroups.get(i)) == -1) {
            return -2;
        } else {
            return -3;
        }
    }

    public void unbindSampleQueue(int i) {
        int i2 = this.trackGroupToSampleQueueIndex[i];
        Assertions.checkState(this.sampleQueuesEnabledStates[i2]);
        this.sampleQueuesEnabledStates[i2] = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x0134  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x013e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean selectTracks(androidx.media2.exoplayer.external.trackselection.TrackSelection[] r20, boolean[] r21, androidx.media2.exoplayer.external.source.SampleStream[] r22, boolean[] r23, long r24, boolean r26) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r22
            r12 = r24
            boolean r3 = r0.prepared
            androidx.media2.exoplayer.external.util.Assertions.checkState(r3)
            int r3 = r0.enabledTrackGroupCount
            r14 = 0
            r4 = 0
        L_0x0011:
            int r5 = r1.length
            r6 = 0
            r15 = 1
            if (r4 >= r5) goto L_0x0033
            r5 = r2[r4]
            if (r5 == 0) goto L_0x0030
            r5 = r1[r4]
            if (r5 == 0) goto L_0x0022
            boolean r5 = r21[r4]
            if (r5 != 0) goto L_0x0030
        L_0x0022:
            int r5 = r0.enabledTrackGroupCount
            int r5 = r5 - r15
            r0.enabledTrackGroupCount = r5
            r5 = r2[r4]
            androidx.media2.exoplayer.external.source.hls.HlsSampleStream r5 = (androidx.media2.exoplayer.external.source.hls.HlsSampleStream) r5
            r5.unbindSampleQueue()
            r2[r4] = r6
        L_0x0030:
            int r4 = r4 + 1
            goto L_0x0011
        L_0x0033:
            if (r26 != 0) goto L_0x0045
            boolean r4 = r0.seenFirstTrackSelection
            if (r4 == 0) goto L_0x003c
            if (r3 != 0) goto L_0x0043
            goto L_0x0045
        L_0x003c:
            long r3 = r0.lastSeekPositionUs
            int r5 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0043
            goto L_0x0045
        L_0x0043:
            r3 = 0
            goto L_0x0046
        L_0x0045:
            r3 = 1
        L_0x0046:
            androidx.media2.exoplayer.external.source.hls.HlsChunkSource r4 = r0.chunkSource
            androidx.media2.exoplayer.external.trackselection.TrackSelection r4 = r4.getTrackSelection()
            r16 = r3
            r11 = r4
            r3 = 0
        L_0x0050:
            int r5 = r1.length
            if (r3 >= r5) goto L_0x00b0
            r5 = r2[r3]
            if (r5 != 0) goto L_0x00ad
            r5 = r1[r3]
            if (r5 == 0) goto L_0x00ad
            int r5 = r0.enabledTrackGroupCount
            int r5 = r5 + r15
            r0.enabledTrackGroupCount = r5
            r5 = r1[r3]
            androidx.media2.exoplayer.external.source.TrackGroupArray r7 = r0.trackGroups
            androidx.media2.exoplayer.external.source.TrackGroup r8 = r5.getTrackGroup()
            int r7 = r7.indexOf(r8)
            int r8 = r0.primaryTrackGroupIndex
            if (r7 != r8) goto L_0x0076
            androidx.media2.exoplayer.external.source.hls.HlsChunkSource r8 = r0.chunkSource
            r8.selectTracks(r5)
            r11 = r5
        L_0x0076:
            androidx.media2.exoplayer.external.source.hls.HlsSampleStream r5 = new androidx.media2.exoplayer.external.source.hls.HlsSampleStream
            r5.<init>(r0, r7)
            r2[r3] = r5
            r23[r3] = r15
            int[] r5 = r0.trackGroupToSampleQueueIndex
            if (r5 == 0) goto L_0x008a
            r5 = r2[r3]
            androidx.media2.exoplayer.external.source.hls.HlsSampleStream r5 = (androidx.media2.exoplayer.external.source.hls.HlsSampleStream) r5
            r5.bindSampleQueue()
        L_0x008a:
            boolean r5 = r0.sampleQueuesBuilt
            if (r5 == 0) goto L_0x00ad
            if (r16 != 0) goto L_0x00ad
            androidx.media2.exoplayer.external.source.SampleQueue[] r5 = r0.sampleQueues
            int[] r8 = r0.trackGroupToSampleQueueIndex
            r7 = r8[r7]
            r5 = r5[r7]
            r5.rewind()
            int r7 = r5.advanceTo(r12, r15, r15)
            r8 = -1
            if (r7 != r8) goto L_0x00aa
            int r5 = r5.getReadIndex()
            if (r5 == 0) goto L_0x00aa
            r5 = 1
            goto L_0x00ab
        L_0x00aa:
            r5 = 0
        L_0x00ab:
            r16 = r5
        L_0x00ad:
            int r3 = r3 + 1
            goto L_0x0050
        L_0x00b0:
            int r1 = r0.enabledTrackGroupCount
            if (r1 != 0) goto L_0x00e7
            androidx.media2.exoplayer.external.source.hls.HlsChunkSource r1 = r0.chunkSource
            r1.reset()
            r0.downstreamTrackFormat = r6
            r0.pendingResetUpstreamFormats = r15
            java.util.ArrayList<androidx.media2.exoplayer.external.source.hls.HlsMediaChunk> r1 = r0.mediaChunks
            r1.clear()
            androidx.media2.exoplayer.external.upstream.Loader r1 = r0.loader
            boolean r1 = r1.isLoading()
            if (r1 == 0) goto L_0x00e2
            boolean r1 = r0.sampleQueuesBuilt
            if (r1 == 0) goto L_0x00db
            androidx.media2.exoplayer.external.source.SampleQueue[] r1 = r0.sampleQueues
            int r3 = r1.length
        L_0x00d1:
            if (r14 >= r3) goto L_0x00db
            r4 = r1[r14]
            r4.discardToEnd()
            int r14 = r14 + 1
            goto L_0x00d1
        L_0x00db:
            androidx.media2.exoplayer.external.upstream.Loader r1 = r0.loader
            r1.cancelLoading()
            goto L_0x014d
        L_0x00e2:
            r19.resetSampleQueues()
            goto L_0x014d
        L_0x00e7:
            java.util.ArrayList<androidx.media2.exoplayer.external.source.hls.HlsMediaChunk> r1 = r0.mediaChunks
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x013a
            boolean r1 = androidx.media2.exoplayer.external.util.Util.areEqual(r11, r4)
            if (r1 != 0) goto L_0x013a
            boolean r1 = r0.seenFirstTrackSelection
            if (r1 != 0) goto L_0x0131
            r3 = 0
            int r1 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0100
            long r3 = -r12
        L_0x0100:
            r6 = r3
            androidx.media2.exoplayer.external.source.hls.HlsMediaChunk r1 = r19.getLastMediaChunk()
            androidx.media2.exoplayer.external.source.hls.HlsChunkSource r3 = r0.chunkSource
            androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator[] r17 = r3.createMediaChunkIterators(r1, r12)
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            java.util.List<androidx.media2.exoplayer.external.source.hls.HlsMediaChunk> r10 = r0.readOnlyMediaChunks
            r3 = r11
            r4 = r24
            r18 = r11
            r11 = r17
            r3.updateSelectedTrack(r4, r6, r8, r10, r11)
            androidx.media2.exoplayer.external.source.hls.HlsChunkSource r3 = r0.chunkSource
            androidx.media2.exoplayer.external.source.TrackGroup r3 = r3.getTrackGroup()
            androidx.media2.exoplayer.external.Format r1 = r1.trackFormat
            int r1 = r3.indexOf(r1)
            int r3 = r18.getSelectedIndexInTrackGroup()
            if (r3 == r1) goto L_0x012f
            goto L_0x0131
        L_0x012f:
            r1 = 0
            goto L_0x0132
        L_0x0131:
            r1 = 1
        L_0x0132:
            if (r1 == 0) goto L_0x013a
            r0.pendingResetUpstreamFormats = r15
            r1 = 1
            r16 = 1
            goto L_0x013c
        L_0x013a:
            r1 = r26
        L_0x013c:
            if (r16 == 0) goto L_0x014d
            r0.seekToUs(r12, r1)
        L_0x0141:
            int r1 = r2.length
            if (r14 >= r1) goto L_0x014d
            r1 = r2[r14]
            if (r1 == 0) goto L_0x014a
            r23[r14] = r15
        L_0x014a:
            int r14 = r14 + 1
            goto L_0x0141
        L_0x014d:
            r0.updateSampleStreams(r2)
            r0.seenFirstTrackSelection = r15
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.source.hls.HlsSampleStreamWrapper.selectTracks(androidx.media2.exoplayer.external.trackselection.TrackSelection[], boolean[], androidx.media2.exoplayer.external.source.SampleStream[], boolean[], long, boolean):boolean");
    }

    public void discardBuffer(long j, boolean z) {
        if (this.sampleQueuesBuilt && !isPendingReset()) {
            int length = this.sampleQueues.length;
            for (int i = 0; i < length; i++) {
                this.sampleQueues[i].discardTo(j, z, this.sampleQueuesEnabledStates[i]);
            }
        }
    }

    public boolean seekToUs(long j, boolean z) {
        this.lastSeekPositionUs = j;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j;
            return true;
        } else if (this.sampleQueuesBuilt && !z && seekInsideBufferUs(j)) {
            return false;
        } else {
            this.pendingResetPositionUs = j;
            this.loadingFinished = false;
            this.mediaChunks.clear();
            if (this.loader.isLoading()) {
                this.loader.cancelLoading();
            } else {
                resetSampleQueues();
            }
            return true;
        }
    }

    public void release() {
        if (this.prepared) {
            for (SampleQueue discardToEnd : this.sampleQueues) {
                discardToEnd.discardToEnd();
            }
        }
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages((Object) null);
        this.released = true;
        this.hlsSampleStreams.clear();
    }

    public void onLoaderReleased() {
        resetSampleQueues();
    }

    public void setIsTimestampMaster(boolean z) {
        this.chunkSource.setIsTimestampMaster(z);
    }

    public boolean onPlaylistError(Uri uri, long j) {
        return this.chunkSource.onPlaylistError(uri, j);
    }

    public boolean isReady(int i) {
        return this.loadingFinished || (!isPendingReset() && this.sampleQueues[i].hasNextSample());
    }

    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        this.chunkSource.maybeThrowError();
    }

    public int readData(int i, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        DrmInitData drmInitData;
        Format format;
        if (isPendingReset()) {
            return -3;
        }
        int i2 = 0;
        if (!this.mediaChunks.isEmpty()) {
            int i3 = 0;
            while (i3 < this.mediaChunks.size() - 1 && finishedReadingChunk(this.mediaChunks.get(i3))) {
                i3++;
            }
            Util.removeRange(this.mediaChunks, 0, i3);
            HlsMediaChunk hlsMediaChunk = this.mediaChunks.get(0);
            Format format2 = hlsMediaChunk.trackFormat;
            if (!format2.equals(this.downstreamTrackFormat)) {
                this.eventDispatcher.downstreamFormatChanged(this.trackType, format2, hlsMediaChunk.trackSelectionReason, hlsMediaChunk.trackSelectionData, hlsMediaChunk.startTimeUs);
            }
            this.downstreamTrackFormat = format2;
        }
        int read = this.sampleQueues[i].read(formatHolder, decoderInputBuffer, z, this.loadingFinished, this.lastSeekPositionUs);
        if (read == -5) {
            Format format3 = formatHolder.format;
            if (i == this.primarySampleQueueIndex) {
                int peekSourceId = this.sampleQueues[i].peekSourceId();
                while (i2 < this.mediaChunks.size() && this.mediaChunks.get(i2).uid != peekSourceId) {
                    i2++;
                }
                if (i2 < this.mediaChunks.size()) {
                    format = this.mediaChunks.get(i2).trackFormat;
                } else {
                    format = this.upstreamTrackFormat;
                }
                format3 = format3.copyWithManifestFormatInfo(format);
            }
            if (!(format3.drmInitData == null || (drmInitData = this.overridingDrmInitData.get(format3.drmInitData.schemeType)) == null)) {
                format3 = format3.copyWithDrmInitData(drmInitData);
            }
            formatHolder.format = format3;
        }
        return read;
    }

    public int skipData(int i, long j) {
        if (isPendingReset()) {
            return 0;
        }
        SampleQueue sampleQueue = this.sampleQueues[i];
        if (this.loadingFinished && j > sampleQueue.getLargestQueuedTimestampUs()) {
            return sampleQueue.advanceToEnd();
        }
        int advanceTo = sampleQueue.advanceTo(j, true, true);
        if (advanceTo == -1) {
            return 0;
        }
        return advanceTo;
    }

    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long j = this.lastSeekPositionUs;
        HlsMediaChunk lastMediaChunk = getLastMediaChunk();
        if (!lastMediaChunk.isLoadCompleted()) {
            if (this.mediaChunks.size() > 1) {
                ArrayList<HlsMediaChunk> arrayList = this.mediaChunks;
                lastMediaChunk = arrayList.get(arrayList.size() - 2);
            } else {
                lastMediaChunk = null;
            }
        }
        if (lastMediaChunk != null) {
            j = Math.max(j, lastMediaChunk.endTimeUs);
        }
        if (this.sampleQueuesBuilt) {
            for (SampleQueue largestQueuedTimestampUs : this.sampleQueues) {
                j = Math.max(j, largestQueuedTimestampUs.getLargestQueuedTimestampUs());
            }
        }
        return j;
    }

    public long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        return getLastMediaChunk().endTimeUs;
    }

    public boolean continueLoading(long j) {
        List<HlsMediaChunk> list;
        long max;
        if (this.loadingFinished || this.loader.isLoading()) {
            return false;
        }
        if (isPendingReset()) {
            list = Collections.emptyList();
            max = this.pendingResetPositionUs;
        } else {
            list = this.readOnlyMediaChunks;
            HlsMediaChunk lastMediaChunk = getLastMediaChunk();
            if (lastMediaChunk.isLoadCompleted()) {
                max = lastMediaChunk.endTimeUs;
            } else {
                max = Math.max(this.lastSeekPositionUs, lastMediaChunk.startTimeUs);
            }
        }
        this.chunkSource.getNextChunk(j, max, list, this.nextChunkHolder);
        boolean z = this.nextChunkHolder.endOfStream;
        Chunk chunk = this.nextChunkHolder.chunk;
        Uri uri = this.nextChunkHolder.playlistUrl;
        this.nextChunkHolder.clear();
        if (z) {
            this.pendingResetPositionUs = -9223372036854775807L;
            this.loadingFinished = true;
            return true;
        } else if (chunk == null) {
            if (uri != null) {
                this.callback.onPlaylistRefreshRequired(uri);
            }
            return false;
        } else {
            if (isMediaChunk(chunk)) {
                this.pendingResetPositionUs = -9223372036854775807L;
                HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) chunk;
                hlsMediaChunk.init(this);
                this.mediaChunks.add(hlsMediaChunk);
                this.upstreamTrackFormat = hlsMediaChunk.trackFormat;
            }
            long startLoading = this.loader.startLoading(chunk, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(chunk.type));
            this.eventDispatcher.loadStarted(chunk.dataSpec, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, startLoading);
            return true;
        }
    }

    public void onLoadCompleted(Chunk chunk, long j, long j2) {
        Chunk chunk2 = chunk;
        this.chunkSource.onChunkLoadCompleted(chunk2);
        this.eventDispatcher.loadCompleted(chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), chunk2.type, this.trackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs, j, j2, chunk.bytesLoaded());
        if (!this.prepared) {
            continueLoading(this.lastSeekPositionUs);
        } else {
            this.callback.onContinueLoadingRequested(this);
        }
    }

    public void onLoadCanceled(Chunk chunk, long j, long j2, boolean z) {
        Chunk chunk2 = chunk;
        this.eventDispatcher.loadCanceled(chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), chunk2.type, this.trackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs, j, j2, chunk.bytesLoaded());
        if (!z) {
            resetSampleQueues();
            if (this.enabledTrackGroupCount > 0) {
                this.callback.onContinueLoadingRequested(this);
            }
        }
    }

    public Loader.LoadErrorAction onLoadError(Chunk chunk, long j, long j2, IOException iOException, int i) {
        Loader.LoadErrorAction loadErrorAction;
        Chunk chunk2 = chunk;
        long bytesLoaded = chunk.bytesLoaded();
        boolean isMediaChunk = isMediaChunk(chunk);
        long blacklistDurationMsFor = this.loadErrorHandlingPolicy.getBlacklistDurationMsFor(chunk2.type, j2, iOException, i);
        boolean z = false;
        boolean maybeBlacklistTrack = blacklistDurationMsFor != -9223372036854775807L ? this.chunkSource.maybeBlacklistTrack(chunk2, blacklistDurationMsFor) : false;
        if (maybeBlacklistTrack) {
            if (isMediaChunk && bytesLoaded == 0) {
                ArrayList<HlsMediaChunk> arrayList = this.mediaChunks;
                if (arrayList.remove(arrayList.size() - 1) == chunk2) {
                    z = true;
                }
                Assertions.checkState(z);
                if (this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                }
            }
            loadErrorAction = Loader.DONT_RETRY;
        } else {
            long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(chunk2.type, j2, iOException, i);
            if (retryDelayMsFor != -9223372036854775807L) {
                loadErrorAction = Loader.createRetryAction(false, retryDelayMsFor);
            } else {
                loadErrorAction = Loader.DONT_RETRY_FATAL;
            }
        }
        Loader.LoadErrorAction loadErrorAction2 = loadErrorAction;
        this.eventDispatcher.loadError(chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), chunk2.type, this.trackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs, j, j2, bytesLoaded, iOException, !loadErrorAction2.isRetry());
        if (maybeBlacklistTrack) {
            if (!this.prepared) {
                continueLoading(this.lastSeekPositionUs);
            } else {
                this.callback.onContinueLoadingRequested(this);
            }
        }
        return loadErrorAction2;
    }

    public void init(int i, boolean z, boolean z2) {
        if (!z2) {
            this.audioSampleQueueMappingDone = false;
            this.videoSampleQueueMappingDone = false;
        }
        this.chunkUid = i;
        for (SampleQueue sourceId : this.sampleQueues) {
            sourceId.sourceId(i);
        }
        if (z) {
            for (SampleQueue splice : this.sampleQueues) {
                splice.splice();
            }
        }
    }

    public TrackOutput track(int i, int i2) {
        SampleQueue[] sampleQueueArr = this.sampleQueues;
        int length = sampleQueueArr.length;
        boolean z = false;
        if (i2 == 1) {
            int i3 = this.audioSampleQueueIndex;
            if (i3 != -1) {
                if (!this.audioSampleQueueMappingDone) {
                    this.audioSampleQueueMappingDone = true;
                    this.sampleQueueTrackIds[i3] = i;
                    return sampleQueueArr[i3];
                } else if (this.sampleQueueTrackIds[i3] == i) {
                    return sampleQueueArr[i3];
                } else {
                    return createDummyTrackOutput(i, i2);
                }
            } else if (this.tracksEnded) {
                return createDummyTrackOutput(i, i2);
            }
        } else if (i2 == 2) {
            int i4 = this.videoSampleQueueIndex;
            if (i4 != -1) {
                if (!this.videoSampleQueueMappingDone) {
                    this.videoSampleQueueMappingDone = true;
                    this.sampleQueueTrackIds[i4] = i;
                    return sampleQueueArr[i4];
                } else if (this.sampleQueueTrackIds[i4] == i) {
                    return sampleQueueArr[i4];
                } else {
                    return createDummyTrackOutput(i, i2);
                }
            } else if (this.tracksEnded) {
                return createDummyTrackOutput(i, i2);
            }
        } else {
            for (int i5 = 0; i5 < length; i5++) {
                if (this.sampleQueueTrackIds[i5] == i) {
                    return this.sampleQueues[i5];
                }
            }
            if (this.tracksEnded) {
                return createDummyTrackOutput(i, i2);
            }
        }
        PrivTimestampStrippingSampleQueue privTimestampStrippingSampleQueue = new PrivTimestampStrippingSampleQueue(this.allocator);
        privTimestampStrippingSampleQueue.setSampleOffsetUs(this.sampleOffsetUs);
        privTimestampStrippingSampleQueue.sourceId(this.chunkUid);
        privTimestampStrippingSampleQueue.setUpstreamFormatChangeListener(this);
        int i6 = length + 1;
        int[] copyOf = Arrays.copyOf(this.sampleQueueTrackIds, i6);
        this.sampleQueueTrackIds = copyOf;
        copyOf[length] = i;
        SampleQueue[] sampleQueueArr2 = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i6);
        this.sampleQueues = sampleQueueArr2;
        sampleQueueArr2[length] = privTimestampStrippingSampleQueue;
        boolean[] copyOf2 = Arrays.copyOf(this.sampleQueueIsAudioVideoFlags, i6);
        this.sampleQueueIsAudioVideoFlags = copyOf2;
        if (i2 == 1 || i2 == 2) {
            z = true;
        }
        copyOf2[length] = z;
        this.haveAudioVideoSampleQueues |= this.sampleQueueIsAudioVideoFlags[length];
        if (i2 == 1) {
            this.audioSampleQueueMappingDone = true;
            this.audioSampleQueueIndex = length;
        } else if (i2 == 2) {
            this.videoSampleQueueMappingDone = true;
            this.videoSampleQueueIndex = length;
        }
        if (getTrackTypeScore(i2) > getTrackTypeScore(this.primarySampleQueueType)) {
            this.primarySampleQueueIndex = length;
            this.primarySampleQueueType = i2;
        }
        this.sampleQueuesEnabledStates = Arrays.copyOf(this.sampleQueuesEnabledStates, i6);
        return privTimestampStrippingSampleQueue;
    }

    public void endTracks() {
        this.tracksEnded = true;
        this.handler.post(this.onTracksEndedRunnable);
    }

    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void setSampleOffsetUs(long j) {
        this.sampleOffsetUs = j;
        for (SampleQueue sampleOffsetUs2 : this.sampleQueues) {
            sampleOffsetUs2.setSampleOffsetUs(j);
        }
    }

    private void updateSampleStreams(SampleStream[] sampleStreamArr) {
        this.hlsSampleStreams.clear();
        for (HlsSampleStream hlsSampleStream : sampleStreamArr) {
            if (hlsSampleStream != null) {
                this.hlsSampleStreams.add(hlsSampleStream);
            }
        }
    }

    private boolean finishedReadingChunk(HlsMediaChunk hlsMediaChunk) {
        int i = hlsMediaChunk.uid;
        int length = this.sampleQueues.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.sampleQueuesEnabledStates[i2] && this.sampleQueues[i2].peekSourceId() == i) {
                return false;
            }
        }
        return true;
    }

    private void resetSampleQueues() {
        for (SampleQueue reset : this.sampleQueues) {
            reset.reset(this.pendingResetUpstreamFormats);
        }
        this.pendingResetUpstreamFormats = false;
    }

    /* access modifiers changed from: private */
    /* renamed from: onTracksEnded */
    public void bridge$lambda$1$HlsSampleStreamWrapper() {
        this.sampleQueuesBuilt = true;
        bridge$lambda$0$HlsSampleStreamWrapper();
    }

    /* access modifiers changed from: private */
    /* renamed from: maybeFinishPrepare */
    public void bridge$lambda$0$HlsSampleStreamWrapper() {
        if (!this.released && this.trackGroupToSampleQueueIndex == null && this.sampleQueuesBuilt) {
            SampleQueue[] sampleQueueArr = this.sampleQueues;
            int length = sampleQueueArr.length;
            int i = 0;
            while (i < length) {
                if (sampleQueueArr[i].getUpstreamFormat() != null) {
                    i++;
                } else {
                    return;
                }
            }
            if (this.trackGroups != null) {
                mapSampleQueuesToMatchTrackGroups();
                return;
            }
            buildTracksFromSampleStreams();
            this.prepared = true;
            this.callback.onPrepared();
        }
    }

    private void mapSampleQueuesToMatchTrackGroups() {
        int i = this.trackGroups.length;
        int[] iArr = new int[i];
        this.trackGroupToSampleQueueIndex = iArr;
        Arrays.fill(iArr, -1);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = 0;
            while (true) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                if (i3 >= sampleQueueArr.length) {
                    break;
                } else if (formatsMatch(sampleQueueArr[i3].getUpstreamFormat(), this.trackGroups.get(i2).getFormat(0))) {
                    this.trackGroupToSampleQueueIndex[i2] = i3;
                    break;
                } else {
                    i3++;
                }
            }
        }
        Iterator<HlsSampleStream> it = this.hlsSampleStreams.iterator();
        while (it.hasNext()) {
            it.next().bindSampleQueue();
        }
    }

    private void buildTracksFromSampleStreams() {
        int length = this.sampleQueues.length;
        boolean z = false;
        int i = 0;
        int i2 = 6;
        int i3 = -1;
        while (true) {
            int i4 = 2;
            if (i >= length) {
                break;
            }
            String str = this.sampleQueues[i].getUpstreamFormat().sampleMimeType;
            if (!MimeTypes.isVideo(str)) {
                if (MimeTypes.isAudio(str)) {
                    i4 = 1;
                } else {
                    i4 = MimeTypes.isText(str) ? 3 : 6;
                }
            }
            if (getTrackTypeScore(i4) > getTrackTypeScore(i2)) {
                i3 = i;
                i2 = i4;
            } else if (i4 == i2 && i3 != -1) {
                i3 = -1;
            }
            i++;
        }
        TrackGroup trackGroup = this.chunkSource.getTrackGroup();
        int i5 = trackGroup.length;
        this.primaryTrackGroupIndex = -1;
        this.trackGroupToSampleQueueIndex = new int[length];
        for (int i6 = 0; i6 < length; i6++) {
            this.trackGroupToSampleQueueIndex[i6] = i6;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        for (int i7 = 0; i7 < length; i7++) {
            Format upstreamFormat = this.sampleQueues[i7].getUpstreamFormat();
            if (i7 == i3) {
                Format[] formatArr = new Format[i5];
                if (i5 == 1) {
                    formatArr[0] = upstreamFormat.copyWithManifestFormatInfo(trackGroup.getFormat(0));
                } else {
                    for (int i8 = 0; i8 < i5; i8++) {
                        formatArr[i8] = deriveFormat(trackGroup.getFormat(i8), upstreamFormat, true);
                    }
                }
                trackGroupArr[i7] = new TrackGroup(formatArr);
                this.primaryTrackGroupIndex = i7;
            } else {
                trackGroupArr[i7] = new TrackGroup(deriveFormat((i2 != 2 || !MimeTypes.isAudio(upstreamFormat.sampleMimeType)) ? null : this.muxedAudioFormat, upstreamFormat, false));
            }
        }
        this.trackGroups = new TrackGroupArray(trackGroupArr);
        if (this.optionalTrackGroups == null) {
            z = true;
        }
        Assertions.checkState(z);
        this.optionalTrackGroups = TrackGroupArray.EMPTY;
    }

    private HlsMediaChunk getLastMediaChunk() {
        ArrayList<HlsMediaChunk> arrayList = this.mediaChunks;
        return arrayList.get(arrayList.size() - 1);
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != -9223372036854775807L;
    }

    private boolean seekInsideBufferUs(long j) {
        int length = this.sampleQueues.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= length) {
                return true;
            }
            SampleQueue sampleQueue = this.sampleQueues[i];
            sampleQueue.rewind();
            if (sampleQueue.advanceTo(j, true, false) == -1) {
                z = false;
            }
            if (z || (!this.sampleQueueIsAudioVideoFlags[i] && this.haveAudioVideoSampleQueues)) {
                i++;
            }
        }
        return false;
    }

    private static Format deriveFormat(Format format, Format format2, boolean z) {
        int i;
        if (format == null) {
            return format2;
        }
        int i2 = z ? format.bitrate : -1;
        if (format.channelCount != -1) {
            i = format.channelCount;
        } else {
            i = format2.channelCount;
        }
        int i3 = i;
        String codecsOfType = Util.getCodecsOfType(format.codecs, MimeTypes.getTrackType(format2.sampleMimeType));
        String mediaMimeType = MimeTypes.getMediaMimeType(codecsOfType);
        if (mediaMimeType == null) {
            mediaMimeType = format2.sampleMimeType;
        }
        return format2.copyWithContainerInfo(format.id, format.label, mediaMimeType, codecsOfType, format.metadata, i2, format.width, format.height, i3, format.selectionFlags, format.language);
    }

    private static boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof HlsMediaChunk;
    }

    private static boolean formatsMatch(Format format, Format format2) {
        String str = format.sampleMimeType;
        String str2 = format2.sampleMimeType;
        int trackType2 = MimeTypes.getTrackType(str);
        if (trackType2 != 3) {
            if (trackType2 == MimeTypes.getTrackType(str2)) {
                return true;
            }
            return false;
        } else if (!Util.areEqual(str, str2)) {
            return false;
        } else {
            if (("application/cea-608".equals(str) || "application/cea-708".equals(str)) && format.accessibilityChannel != format2.accessibilityChannel) {
                return false;
            }
            return true;
        }
    }

    private static DummyTrackOutput createDummyTrackOutput(int i, int i2) {
        StringBuilder sb = new StringBuilder(54);
        sb.append("Unmapped track with id ");
        sb.append(i);
        sb.append(" of type ");
        sb.append(i2);
        Log.w("HlsSampleStreamWrapper", sb.toString());
        return new DummyTrackOutput();
    }

    private static final class PrivTimestampStrippingSampleQueue extends SampleQueue {
        public PrivTimestampStrippingSampleQueue(Allocator allocator) {
            super(allocator);
        }

        public void format(Format format) {
            super.format(format.copyWithMetadata(getAdjustedMetadata(format.metadata)));
        }

        private Metadata getAdjustedMetadata(Metadata metadata) {
            if (metadata == null) {
                return null;
            }
            int length = metadata.length();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                }
                Metadata.Entry entry = metadata.get(i2);
                if ((entry instanceof PrivFrame) && "com.apple.streaming.transportStreamTimestamp".equals(((PrivFrame) entry).owner)) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                return metadata;
            }
            if (length == 1) {
                return null;
            }
            Metadata.Entry[] entryArr = new Metadata.Entry[(length - 1)];
            while (i < length) {
                if (i != i2) {
                    entryArr[i < i2 ? i : i - 1] = metadata.get(i);
                }
                i++;
            }
            return new Metadata(entryArr);
        }
    }
}
