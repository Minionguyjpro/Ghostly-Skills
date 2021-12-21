package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.BaseMediaChunkIterator;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.DataChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.trackselection.BaseTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class HlsChunkSource {
    private final DataSource encryptionDataSource;
    private Uri expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isTimestampMaster;
    private final FullSegmentEncryptionKeyCache keyCache = new FullSegmentEncryptionKeyCache(4);
    private long liveEdgeInPeriodTimeUs = -9223372036854775807L;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private final Format[] playlistFormats;
    private final HlsPlaylistTracker playlistTracker;
    private final Uri[] playlistUrls;
    private byte[] scratchSpace = Util.EMPTY_BYTE_ARRAY;
    private boolean seenExpectedPlaylistError;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private TrackSelection trackSelection;

    public static final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public Uri playlistUrl;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlistUrl = null;
        }
    }

    public HlsChunkSource(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, Uri[] uriArr, Format[] formatArr, HlsDataSourceFactory hlsDataSourceFactory, TransferListener transferListener, TimestampAdjusterProvider timestampAdjusterProvider2, List<Format> list) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.playlistUrls = uriArr;
        this.playlistFormats = formatArr;
        this.timestampAdjusterProvider = timestampAdjusterProvider2;
        this.muxedCaptionFormats = list;
        DataSource createDataSource = hlsDataSourceFactory.createDataSource(1);
        this.mediaDataSource = createDataSource;
        if (transferListener != null) {
            createDataSource.addTransferListener(transferListener);
        }
        this.encryptionDataSource = hlsDataSourceFactory.createDataSource(3);
        this.trackGroup = new TrackGroup(formatArr);
        int[] iArr = new int[uriArr.length];
        for (int i = 0; i < uriArr.length; i++) {
            iArr[i] = i;
        }
        this.trackSelection = new InitializationTrackSelection(this.trackGroup, iArr);
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            Uri uri = this.expectedPlaylistUrl;
            if (uri != null && this.seenExpectedPlaylistError) {
                this.playlistTracker.maybeThrowPlaylistRefreshError(uri);
                return;
            }
            return;
        }
        throw iOException;
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public void setTrackSelection(TrackSelection trackSelection2) {
        this.trackSelection = trackSelection2;
    }

    public TrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public void reset() {
        this.fatalError = null;
    }

    public void setIsTimestampMaster(boolean z) {
        this.isTimestampMaster = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getNextChunk(long r29, long r31, java.util.List<com.google.android.exoplayer2.source.hls.HlsMediaChunk> r33, boolean r34, com.google.android.exoplayer2.source.hls.HlsChunkSource.HlsChunkHolder r35) {
        /*
            r28 = this;
            r8 = r28
            r6 = r31
            r9 = r35
            boolean r0 = r33.isEmpty()
            r11 = 1
            if (r0 == 0) goto L_0x0011
            r1 = r33
            r4 = 0
            goto L_0x001f
        L_0x0011:
            int r0 = r33.size()
            int r0 = r0 - r11
            r1 = r33
            java.lang.Object r0 = r1.get(r0)
            com.google.android.exoplayer2.source.hls.HlsMediaChunk r0 = (com.google.android.exoplayer2.source.hls.HlsMediaChunk) r0
            r4 = r0
        L_0x001f:
            if (r4 != 0) goto L_0x0024
            r0 = -1
            r5 = -1
            goto L_0x002d
        L_0x0024:
            com.google.android.exoplayer2.source.TrackGroup r0 = r8.trackGroup
            com.google.android.exoplayer2.Format r2 = r4.trackFormat
            int r0 = r0.indexOf(r2)
            r5 = r0
        L_0x002d:
            long r2 = r6 - r29
            long r12 = r28.resolveTimeToLiveEdgeUs(r29)
            if (r4 == 0) goto L_0x0056
            boolean r0 = r8.independentSegments
            if (r0 != 0) goto L_0x0056
            long r14 = r4.getDurationUs()
            long r2 = r2 - r14
            r10 = 0
            long r2 = java.lang.Math.max(r10, r2)
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x0056
            long r12 = r12 - r14
            long r10 = java.lang.Math.max(r10, r12)
            r15 = r2
            r17 = r10
            goto L_0x0059
        L_0x0056:
            r15 = r2
            r17 = r12
        L_0x0059:
            com.google.android.exoplayer2.source.chunk.MediaChunkIterator[] r20 = r8.createMediaChunkIterators(r4, r6)
            com.google.android.exoplayer2.trackselection.TrackSelection r12 = r8.trackSelection
            r13 = r29
            r19 = r33
            r12.updateSelectedTrack(r13, r15, r17, r19, r20)
            com.google.android.exoplayer2.trackselection.TrackSelection r0 = r8.trackSelection
            int r10 = r0.getSelectedIndexInTrackGroup()
            r11 = 0
            if (r5 == r10) goto L_0x0071
            r12 = 1
            goto L_0x0072
        L_0x0071:
            r12 = 0
        L_0x0072:
            android.net.Uri[] r0 = r8.playlistUrls
            r13 = r0[r10]
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r0 = r8.playlistTracker
            boolean r0 = r0.isSnapshotValid(r13)
            if (r0 != 0) goto L_0x008e
            r9.playlistUrl = r13
            boolean r0 = r8.seenExpectedPlaylistError
            android.net.Uri r1 = r8.expectedPlaylistUrl
            boolean r1 = r13.equals(r1)
            r0 = r0 & r1
            r8.seenExpectedPlaylistError = r0
            r8.expectedPlaylistUrl = r13
            return
        L_0x008e:
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r0 = r8.playlistTracker
            r1 = 1
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist r14 = r0.getPlaylistSnapshot(r13, r1)
            com.google.android.exoplayer2.util.Assertions.checkNotNull(r14)
            boolean r0 = r14.hasIndependentSegments
            r8.independentSegments = r0
            r8.updateLiveEdgeTimeUs(r14)
            long r0 = r14.startTimeUs
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r2 = r8.playlistTracker
            long r2 = r2.getInitialStartTimeUs()
            long r15 = r0 - r2
            r0 = r28
            r1 = r4
            r2 = r12
            r3 = r14
            r25 = r4
            r17 = r5
            r4 = r15
            r6 = r31
            long r0 = r0.getChunkMediaSequence(r1, r2, r3, r4, r6)
            long r2 = r14.mediaSequence
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x00e5
            if (r25 == 0) goto L_0x00e5
            if (r12 == 0) goto L_0x00e5
            android.net.Uri[] r0 = r8.playlistUrls
            r0 = r0[r17]
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r1 = r8.playlistTracker
            r2 = 1
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist r1 = r1.getPlaylistSnapshot(r0, r2)
            com.google.android.exoplayer2.util.Assertions.checkNotNull(r1)
            long r2 = r1.startTimeUs
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker r4 = r8.playlistTracker
            long r4 = r4.getInitialStartTimeUs()
            long r2 = r2 - r4
            long r4 = r25.getNextChunkIndex()
            r15 = r2
            r3 = r0
            r2 = r1
            r0 = r4
            r5 = r17
            goto L_0x00e8
        L_0x00e5:
            r5 = r10
            r3 = r13
            r2 = r14
        L_0x00e8:
            long r6 = r2.mediaSequence
            int r4 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x00f6
            com.google.android.exoplayer2.source.BehindLiveWindowException r0 = new com.google.android.exoplayer2.source.BehindLiveWindowException
            r0.<init>()
            r8.fatalError = r0
            return
        L_0x00f6:
            long r6 = r2.mediaSequence
            long r0 = r0 - r6
            int r1 = (int) r0
            java.util.List<com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment> r0 = r2.segments
            int r0 = r0.size()
            if (r1 < r0) goto L_0x0122
            boolean r1 = r2.hasEndTag
            if (r1 == 0) goto L_0x0112
            if (r34 != 0) goto L_0x010e
            if (r0 != 0) goto L_0x010b
            goto L_0x010e
        L_0x010b:
            r1 = 1
            int r0 = r0 - r1
            goto L_0x0123
        L_0x010e:
            r1 = 1
            r9.endOfStream = r1
            return
        L_0x0112:
            r9.playlistUrl = r3
            boolean r0 = r8.seenExpectedPlaylistError
            android.net.Uri r1 = r8.expectedPlaylistUrl
            boolean r1 = r3.equals(r1)
            r0 = r0 & r1
            r8.seenExpectedPlaylistError = r0
            r8.expectedPlaylistUrl = r3
            return
        L_0x0122:
            r0 = r1
        L_0x0123:
            r8.seenExpectedPlaylistError = r11
            r1 = 0
            r8.expectedPlaylistUrl = r1
            java.util.List<com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment> r1 = r2.segments
            java.lang.Object r1 = r1.get(r0)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r1 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment) r1
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r4 = r1.initializationSegment
            android.net.Uri r4 = getFullEncryptionKeyUri(r2, r4)
            com.google.android.exoplayer2.source.chunk.Chunk r6 = r8.maybeCreateEncryptionChunkFor(r4, r5)
            r9.chunk = r6
            com.google.android.exoplayer2.source.chunk.Chunk r6 = r9.chunk
            if (r6 == 0) goto L_0x0141
            return
        L_0x0141:
            android.net.Uri r1 = getFullEncryptionKeyUri(r2, r1)
            com.google.android.exoplayer2.source.chunk.Chunk r6 = r8.maybeCreateEncryptionChunkFor(r1, r5)
            r9.chunk = r6
            com.google.android.exoplayer2.source.chunk.Chunk r6 = r9.chunk
            if (r6 == 0) goto L_0x0150
            return
        L_0x0150:
            com.google.android.exoplayer2.source.hls.HlsExtractorFactory r12 = r8.extractorFactory
            com.google.android.exoplayer2.upstream.DataSource r13 = r8.mediaDataSource
            com.google.android.exoplayer2.Format[] r6 = r8.playlistFormats
            r14 = r6[r5]
            java.util.List<com.google.android.exoplayer2.Format> r5 = r8.muxedCaptionFormats
            com.google.android.exoplayer2.trackselection.TrackSelection r6 = r8.trackSelection
            int r21 = r6.getSelectionReason()
            com.google.android.exoplayer2.trackselection.TrackSelection r6 = r8.trackSelection
            java.lang.Object r22 = r6.getSelectionData()
            boolean r6 = r8.isTimestampMaster
            com.google.android.exoplayer2.source.hls.TimestampAdjusterProvider r7 = r8.timestampAdjusterProvider
            com.google.android.exoplayer2.source.hls.FullSegmentEncryptionKeyCache r10 = r8.keyCache
            byte[] r26 = r10.get(r1)
            com.google.android.exoplayer2.source.hls.FullSegmentEncryptionKeyCache r1 = r8.keyCache
            byte[] r27 = r1.get(r4)
            r17 = r2
            r18 = r0
            r19 = r3
            r20 = r5
            r23 = r6
            r24 = r7
            com.google.android.exoplayer2.source.hls.HlsMediaChunk r0 = com.google.android.exoplayer2.source.hls.HlsMediaChunk.createInstance(r12, r13, r14, r15, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r9.chunk = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsChunkSource.getNextChunk(long, long, java.util.List, boolean, com.google.android.exoplayer2.source.hls.HlsChunkSource$HlsChunkHolder):void");
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            this.keyCache.put(encryptionKeyChunk.dataSpec.uri, (byte[]) Assertions.checkNotNull(encryptionKeyChunk.getResult()));
        }
    }

    public boolean maybeBlacklistTrack(Chunk chunk, long j) {
        TrackSelection trackSelection2 = this.trackSelection;
        return trackSelection2.blacklist(trackSelection2.indexOf(this.trackGroup.indexOf(chunk.trackFormat)), j);
    }

    public boolean onPlaylistError(Uri uri, long j) {
        int indexOf;
        int i = 0;
        while (true) {
            Uri[] uriArr = this.playlistUrls;
            if (i >= uriArr.length) {
                i = -1;
                break;
            } else if (uriArr[i].equals(uri)) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1 || (indexOf = this.trackSelection.indexOf(i)) == -1) {
            return true;
        }
        this.seenExpectedPlaylistError = uri.equals(this.expectedPlaylistUrl) | this.seenExpectedPlaylistError;
        if (j == -9223372036854775807L || this.trackSelection.blacklist(indexOf, j)) {
            return true;
        }
        return false;
    }

    public MediaChunkIterator[] createMediaChunkIterators(HlsMediaChunk hlsMediaChunk, long j) {
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        int indexOf = hlsMediaChunk2 == null ? -1 : this.trackGroup.indexOf(hlsMediaChunk2.trackFormat);
        int length = this.trackSelection.length();
        MediaChunkIterator[] mediaChunkIteratorArr = new MediaChunkIterator[length];
        for (int i = 0; i < length; i++) {
            int indexInTrackGroup = this.trackSelection.getIndexInTrackGroup(i);
            Uri uri = this.playlistUrls[indexInTrackGroup];
            if (!this.playlistTracker.isSnapshotValid(uri)) {
                mediaChunkIteratorArr[i] = MediaChunkIterator.EMPTY;
            } else {
                HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(uri, false);
                Assertions.checkNotNull(playlistSnapshot);
                long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
                long j2 = initialStartTimeUs;
                long chunkMediaSequence = getChunkMediaSequence(hlsMediaChunk, indexInTrackGroup != indexOf, playlistSnapshot, initialStartTimeUs, j);
                if (chunkMediaSequence < playlistSnapshot.mediaSequence) {
                    mediaChunkIteratorArr[i] = MediaChunkIterator.EMPTY;
                } else {
                    mediaChunkIteratorArr[i] = new HlsMediaPlaylistSegmentIterator(playlistSnapshot, j2, (int) (chunkMediaSequence - playlistSnapshot.mediaSequence));
                }
            }
        }
        return mediaChunkIteratorArr;
    }

    private long getChunkMediaSequence(HlsMediaChunk hlsMediaChunk, boolean z, HlsMediaPlaylist hlsMediaPlaylist, long j, long j2) {
        long binarySearchFloor;
        long j3;
        if (hlsMediaChunk != null && !z) {
            return hlsMediaChunk.getNextChunkIndex();
        }
        long j4 = hlsMediaPlaylist.durationUs + j;
        if (hlsMediaChunk != null && !this.independentSegments) {
            j2 = hlsMediaChunk.startTimeUs;
        }
        if (hlsMediaPlaylist.hasEndTag || j2 < j4) {
            binarySearchFloor = (long) Util.binarySearchFloor(hlsMediaPlaylist.segments, Long.valueOf(j2 - j), true, !this.playlistTracker.isLive() || hlsMediaChunk == null);
            j3 = hlsMediaPlaylist.mediaSequence;
        } else {
            binarySearchFloor = hlsMediaPlaylist.mediaSequence;
            j3 = (long) hlsMediaPlaylist.segments.size();
        }
        return binarySearchFloor + j3;
    }

    private long resolveTimeToLiveEdgeUs(long j) {
        if (this.liveEdgeInPeriodTimeUs != -9223372036854775807L) {
            return this.liveEdgeInPeriodTimeUs - j;
        }
        return -9223372036854775807L;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist hlsMediaPlaylist) {
        long j;
        if (hlsMediaPlaylist.hasEndTag) {
            j = -9223372036854775807L;
        } else {
            j = hlsMediaPlaylist.getEndTimeUs() - this.playlistTracker.getInitialStartTimeUs();
        }
        this.liveEdgeInPeriodTimeUs = j;
    }

    private Chunk maybeCreateEncryptionChunkFor(Uri uri, int i) {
        if (uri == null) {
            return null;
        }
        byte[] remove = this.keyCache.remove(uri);
        if (remove != null) {
            this.keyCache.put(uri, remove);
            return null;
        }
        return new EncryptionKeyChunk(this.encryptionDataSource, new DataSpec(uri, 0, -1, (String) null, 1), this.playlistFormats[i], this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.scratchSpace);
    }

    private static Uri getFullEncryptionKeyUri(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist.Segment segment) {
        if (segment == null || segment.fullSegmentEncryptionKeyUri == null) {
            return null;
        }
        return UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.fullSegmentEncryptionKeyUri);
    }

    private static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        public Object getSelectionData() {
            return null;
        }

        public int getSelectionReason() {
            return 0;
        }

        public InitializationTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
            this.selectedIndex = indexOf(trackGroup.getFormat(0));
        }

        public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (isBlacklisted(this.selectedIndex, elapsedRealtime)) {
                for (int i = this.length - 1; i >= 0; i--) {
                    if (!isBlacklisted(i, elapsedRealtime)) {
                        this.selectedIndex = i;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }

        public int getSelectedIndex() {
            return this.selectedIndex;
        }
    }

    private static final class EncryptionKeyChunk extends DataChunk {
        private byte[] result;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, byte[] bArr) {
            super(dataSource, dataSpec, 3, format, i, obj, bArr);
        }

        /* access modifiers changed from: protected */
        public void consume(byte[] bArr, int i) {
            this.result = Arrays.copyOf(bArr, i);
        }

        public byte[] getResult() {
            return this.result;
        }
    }

    private static final class HlsMediaPlaylistSegmentIterator extends BaseMediaChunkIterator {
        private final HlsMediaPlaylist playlist;
        private final long startOfPlaylistInPeriodUs;

        public HlsMediaPlaylistSegmentIterator(HlsMediaPlaylist hlsMediaPlaylist, long j, int i) {
            super((long) i, (long) (hlsMediaPlaylist.segments.size() - 1));
            this.playlist = hlsMediaPlaylist;
            this.startOfPlaylistInPeriodUs = j;
        }
    }
}
