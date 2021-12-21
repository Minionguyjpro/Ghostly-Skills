package androidx.media2.exoplayer.external.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.source.TrackGroup;
import androidx.media2.exoplayer.external.source.chunk.BaseMediaChunkIterator;
import androidx.media2.exoplayer.external.source.chunk.Chunk;
import androidx.media2.exoplayer.external.source.chunk.DataChunk;
import androidx.media2.exoplayer.external.source.chunk.MediaChunk;
import androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator;
import androidx.media2.exoplayer.external.source.hls.playlist.HlsMediaPlaylist;
import androidx.media2.exoplayer.external.source.hls.playlist.HlsPlaylistTracker;
import androidx.media2.exoplayer.external.trackselection.BaseTrackSelection;
import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.DataSpec;
import androidx.media2.exoplayer.external.upstream.TransferListener;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.UriUtil;
import androidx.media2.exoplayer.external.util.Util;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class HlsChunkSource {
    private final DataSource encryptionDataSource;
    private Uri expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isTimestampMaster;
    private final FullSegmentEncryptionKeyCache keyCache = new FullSegmentEncryptionKeyCache();
    private long liveEdgeInPeriodTimeUs = -9223372036854775807L;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private final Format[] playlistFormats;
    private final HlsPlaylistTracker playlistTracker;
    private final Uri[] playlistUrls;
    private byte[] scratchSpace;
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

    public void selectTracks(TrackSelection trackSelection2) {
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
    public void getNextChunk(long r29, long r31, java.util.List<androidx.media2.exoplayer.external.source.hls.HlsMediaChunk> r33, androidx.media2.exoplayer.external.source.hls.HlsChunkSource.HlsChunkHolder r34) {
        /*
            r28 = this;
            r8 = r28
            r6 = r31
            r9 = r34
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
            androidx.media2.exoplayer.external.source.hls.HlsMediaChunk r0 = (androidx.media2.exoplayer.external.source.hls.HlsMediaChunk) r0
            r4 = r0
        L_0x001f:
            if (r4 != 0) goto L_0x0024
            r0 = -1
            r5 = -1
            goto L_0x002d
        L_0x0024:
            androidx.media2.exoplayer.external.source.TrackGroup r0 = r8.trackGroup
            androidx.media2.exoplayer.external.Format r2 = r4.trackFormat
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
            androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator[] r20 = r8.createMediaChunkIterators(r4, r6)
            androidx.media2.exoplayer.external.trackselection.TrackSelection r12 = r8.trackSelection
            r13 = r29
            r19 = r33
            r12.updateSelectedTrack(r13, r15, r17, r19, r20)
            androidx.media2.exoplayer.external.trackselection.TrackSelection r0 = r8.trackSelection
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
            androidx.media2.exoplayer.external.source.hls.playlist.HlsPlaylistTracker r0 = r8.playlistTracker
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
            androidx.media2.exoplayer.external.source.hls.playlist.HlsPlaylistTracker r0 = r8.playlistTracker
            r1 = 1
            androidx.media2.exoplayer.external.source.hls.playlist.HlsMediaPlaylist r14 = r0.getPlaylistSnapshot(r13, r1)
            boolean r0 = r14.hasIndependentSegments
            r8.independentSegments = r0
            r8.updateLiveEdgeTimeUs(r14)
            long r0 = r14.startTimeUs
            androidx.media2.exoplayer.external.source.hls.playlist.HlsPlaylistTracker r2 = r8.playlistTracker
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
            if (r4 >= 0) goto L_0x00df
            if (r25 == 0) goto L_0x00df
            if (r12 == 0) goto L_0x00df
            android.net.Uri[] r0 = r8.playlistUrls
            r0 = r0[r17]
            androidx.media2.exoplayer.external.source.hls.playlist.HlsPlaylistTracker r1 = r8.playlistTracker
            r2 = 1
            androidx.media2.exoplayer.external.source.hls.playlist.HlsMediaPlaylist r1 = r1.getPlaylistSnapshot(r0, r2)
            long r2 = r1.startTimeUs
            androidx.media2.exoplayer.external.source.hls.playlist.HlsPlaylistTracker r4 = r8.playlistTracker
            long r4 = r4.getInitialStartTimeUs()
            long r2 = r2 - r4
            long r4 = r25.getNextChunkIndex()
            r15 = r2
            r3 = r0
            r2 = r1
            r0 = r4
            r5 = r17
            goto L_0x00e2
        L_0x00df:
            r5 = r10
            r3 = r13
            r2 = r14
        L_0x00e2:
            long r6 = r2.mediaSequence
            int r4 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x00f0
            androidx.media2.exoplayer.external.source.BehindLiveWindowException r0 = new androidx.media2.exoplayer.external.source.BehindLiveWindowException
            r0.<init>()
            r8.fatalError = r0
            return
        L_0x00f0:
            long r6 = r2.mediaSequence
            long r0 = r0 - r6
            int r1 = (int) r0
            java.util.List<androidx.media2.exoplayer.external.source.hls.playlist.HlsMediaPlaylist$Segment> r0 = r2.segments
            int r0 = r0.size()
            if (r1 < r0) goto L_0x0114
            boolean r0 = r2.hasEndTag
            if (r0 == 0) goto L_0x0104
            r0 = 1
            r9.endOfStream = r0
            goto L_0x0113
        L_0x0104:
            r9.playlistUrl = r3
            boolean r0 = r8.seenExpectedPlaylistError
            android.net.Uri r1 = r8.expectedPlaylistUrl
            boolean r1 = r3.equals(r1)
            r0 = r0 & r1
            r8.seenExpectedPlaylistError = r0
            r8.expectedPlaylistUrl = r3
        L_0x0113:
            return
        L_0x0114:
            r8.seenExpectedPlaylistError = r11
            r0 = 0
            r8.expectedPlaylistUrl = r0
            java.util.List<androidx.media2.exoplayer.external.source.hls.playlist.HlsMediaPlaylist$Segment> r0 = r2.segments
            java.lang.Object r0 = r0.get(r1)
            androidx.media2.exoplayer.external.source.hls.playlist.HlsMediaPlaylist$Segment r0 = (androidx.media2.exoplayer.external.source.hls.playlist.HlsMediaPlaylist.Segment) r0
            androidx.media2.exoplayer.external.source.hls.playlist.HlsMediaPlaylist$Segment r4 = r0.initializationSegment
            android.net.Uri r4 = getFullEncryptionKeyUri(r2, r4)
            androidx.media2.exoplayer.external.source.chunk.Chunk r6 = r8.maybeCreateEncryptionChunkFor(r4, r5)
            r9.chunk = r6
            androidx.media2.exoplayer.external.source.chunk.Chunk r6 = r9.chunk
            if (r6 == 0) goto L_0x0132
            return
        L_0x0132:
            android.net.Uri r0 = getFullEncryptionKeyUri(r2, r0)
            androidx.media2.exoplayer.external.source.chunk.Chunk r6 = r8.maybeCreateEncryptionChunkFor(r0, r5)
            r9.chunk = r6
            androidx.media2.exoplayer.external.source.chunk.Chunk r6 = r9.chunk
            if (r6 == 0) goto L_0x0141
            return
        L_0x0141:
            androidx.media2.exoplayer.external.source.hls.HlsExtractorFactory r12 = r8.extractorFactory
            androidx.media2.exoplayer.external.upstream.DataSource r13 = r8.mediaDataSource
            androidx.media2.exoplayer.external.Format[] r6 = r8.playlistFormats
            r14 = r6[r5]
            java.util.List<androidx.media2.exoplayer.external.Format> r5 = r8.muxedCaptionFormats
            androidx.media2.exoplayer.external.trackselection.TrackSelection r6 = r8.trackSelection
            int r21 = r6.getSelectionReason()
            androidx.media2.exoplayer.external.trackselection.TrackSelection r6 = r8.trackSelection
            java.lang.Object r22 = r6.getSelectionData()
            boolean r6 = r8.isTimestampMaster
            androidx.media2.exoplayer.external.source.hls.TimestampAdjusterProvider r7 = r8.timestampAdjusterProvider
            androidx.media2.exoplayer.external.source.hls.HlsChunkSource$FullSegmentEncryptionKeyCache r10 = r8.keyCache
            byte[] r26 = r10.get((java.lang.Object) r0)
            androidx.media2.exoplayer.external.source.hls.HlsChunkSource$FullSegmentEncryptionKeyCache r0 = r8.keyCache
            byte[] r27 = r0.get((java.lang.Object) r4)
            r17 = r2
            r18 = r1
            r19 = r3
            r20 = r5
            r23 = r6
            r24 = r7
            androidx.media2.exoplayer.external.source.hls.HlsMediaChunk r0 = androidx.media2.exoplayer.external.source.hls.HlsMediaChunk.createInstance(r12, r13, r14, r15, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r9.chunk = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.source.hls.HlsChunkSource.getNextChunk(long, long, java.util.List, androidx.media2.exoplayer.external.source.hls.HlsChunkSource$HlsChunkHolder):void");
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            this.keyCache.put(encryptionKeyChunk.dataSpec.uri, encryptionKeyChunk.getResult());
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
        if (this.keyCache.containsKey(uri)) {
            FullSegmentEncryptionKeyCache fullSegmentEncryptionKeyCache = this.keyCache;
            fullSegmentEncryptionKeyCache.put(uri, (byte[]) fullSegmentEncryptionKeyCache.remove(uri));
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

    private static final class FullSegmentEncryptionKeyCache extends LinkedHashMap<Uri, byte[]> {
        public FullSegmentEncryptionKeyCache() {
            super(8, 1.0f, false);
        }

        public byte[] get(Object obj) {
            if (obj == null) {
                return null;
            }
            return (byte[]) super.get(obj);
        }

        public byte[] put(Uri uri, byte[] bArr) {
            return (byte[]) super.put(uri, (byte[]) Assertions.checkNotNull(bArr));
        }

        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Map.Entry<Uri, byte[]> entry) {
            return size() > 4;
        }
    }
}
