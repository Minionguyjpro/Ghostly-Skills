package com.google.android.exoplayer2.source;

import android.os.Looper;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

final class SampleMetadataQueue {
    private int absoluteFirstIndex;
    private int capacity = 1000;
    private TrackOutput.CryptoData[] cryptoDatas = new TrackOutput.CryptoData[1000];
    private DrmSession<?> currentDrmSession;
    private Format downstreamFormat;
    private final DrmSessionManager<?> drmSessionManager;
    private int[] flags = new int[1000];
    private Format[] formats = new Format[1000];
    private boolean isLastSampleQueued;
    private long largestDiscardedTimestampUs = Long.MIN_VALUE;
    private long largestQueuedTimestampUs = Long.MIN_VALUE;
    private int length;
    private long[] offsets = new long[1000];
    private int readPosition;
    private int relativeFirstIndex;
    private int[] sizes = new int[1000];
    private int[] sourceIds = new int[1000];
    private long[] timesUs = new long[1000];
    private Format upstreamCommittedFormat;
    private Format upstreamFormat;
    private boolean upstreamFormatRequired = true;
    private boolean upstreamKeyframeRequired = true;
    private int upstreamSourceId;

    public static final class SampleExtrasHolder {
        public TrackOutput.CryptoData cryptoData;
        public long offset;
        public int size;
    }

    public SampleMetadataQueue(DrmSessionManager<?> drmSessionManager2) {
        this.drmSessionManager = drmSessionManager2;
    }

    public void reset(boolean z) {
        this.length = 0;
        this.absoluteFirstIndex = 0;
        this.relativeFirstIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.isLastSampleQueued = false;
        this.upstreamCommittedFormat = null;
        if (z) {
            this.upstreamFormat = null;
            this.upstreamFormatRequired = true;
        }
    }

    public int getWriteIndex() {
        return this.absoluteFirstIndex + this.length;
    }

    public long discardUpstreamSamples(int i) {
        int writeIndex = getWriteIndex() - i;
        boolean z = false;
        Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.length - this.readPosition);
        int i2 = this.length - writeIndex;
        this.length = i2;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i2));
        if (writeIndex == 0 && this.isLastSampleQueued) {
            z = true;
        }
        this.isLastSampleQueued = z;
        int i3 = this.length;
        if (i3 == 0) {
            return 0;
        }
        int relativeIndex = getRelativeIndex(i3 - 1);
        return this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]);
    }

    public void sourceId(int i) {
        this.upstreamSourceId = i;
    }

    public void maybeThrowError() throws IOException {
        DrmSession<?> drmSession = this.currentDrmSession;
        if (drmSession != null && drmSession.getState() == 1) {
            throw ((DrmSession.DrmSessionException) Assertions.checkNotNull(this.currentDrmSession.getError()));
        }
    }

    public void releaseDrmSessionReferences() {
        DrmSession<?> drmSession = this.currentDrmSession;
        if (drmSession != null) {
            drmSession.release();
            this.currentDrmSession = null;
            this.downstreamFormat = null;
        }
    }

    public int getFirstIndex() {
        return this.absoluteFirstIndex;
    }

    public int getReadIndex() {
        return this.absoluteFirstIndex + this.readPosition;
    }

    public synchronized int peekSourceId() {
        return hasNextSample() ? this.sourceIds[getRelativeIndex(this.readPosition)] : this.upstreamSourceId;
    }

    public synchronized Format getUpstreamFormat() {
        return this.upstreamFormatRequired ? null : this.upstreamFormat;
    }

    public synchronized long getLargestQueuedTimestampUs() {
        return this.largestQueuedTimestampUs;
    }

    public synchronized boolean isLastSampleQueued() {
        return this.isLastSampleQueued;
    }

    public synchronized long getFirstTimestampUs() {
        return this.length == 0 ? Long.MIN_VALUE : this.timesUs[this.relativeFirstIndex];
    }

    public synchronized void rewind() {
        this.readPosition = 0;
    }

    public boolean isReady(boolean z) {
        if (hasNextSample()) {
            int relativeIndex = getRelativeIndex(this.readPosition);
            if (this.formats[relativeIndex] != this.downstreamFormat) {
                return true;
            }
            return mayReadSample(relativeIndex);
        } else if (z || this.isLastSampleQueued) {
            return true;
        } else {
            Format format = this.upstreamFormat;
            if (format == null || format == this.downstreamFormat) {
                return false;
            }
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(com.google.android.exoplayer2.FormatHolder r5, com.google.android.exoplayer2.decoder.DecoderInputBuffer r6, boolean r7, boolean r8, com.google.android.exoplayer2.source.SampleMetadataQueue.SampleExtrasHolder r9) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.hasNextSample()     // Catch:{ all -> 0x0083 }
            r1 = -5
            r2 = -3
            r3 = -4
            if (r0 != 0) goto L_0x0032
            if (r8 != 0) goto L_0x002c
            boolean r8 = r4.isLastSampleQueued     // Catch:{ all -> 0x0083 }
            if (r8 == 0) goto L_0x0011
            goto L_0x002c
        L_0x0011:
            com.google.android.exoplayer2.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x0083 }
            if (r6 == 0) goto L_0x002a
            if (r7 != 0) goto L_0x001d
            com.google.android.exoplayer2.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x0083 }
            com.google.android.exoplayer2.Format r7 = r4.downstreamFormat     // Catch:{ all -> 0x0083 }
            if (r6 == r7) goto L_0x002a
        L_0x001d:
            com.google.android.exoplayer2.Format r6 = r4.upstreamFormat     // Catch:{ all -> 0x0083 }
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r6)     // Catch:{ all -> 0x0083 }
            com.google.android.exoplayer2.Format r6 = (com.google.android.exoplayer2.Format) r6     // Catch:{ all -> 0x0083 }
            r4.onFormatResult(r6, r5)     // Catch:{ all -> 0x0083 }
            monitor-exit(r4)
            return r1
        L_0x002a:
            monitor-exit(r4)
            return r2
        L_0x002c:
            r5 = 4
            r6.setFlags(r5)     // Catch:{ all -> 0x0083 }
            monitor-exit(r4)
            return r3
        L_0x0032:
            int r8 = r4.readPosition     // Catch:{ all -> 0x0083 }
            int r8 = r4.getRelativeIndex(r8)     // Catch:{ all -> 0x0083 }
            if (r7 != 0) goto L_0x007a
            com.google.android.exoplayer2.Format[] r7 = r4.formats     // Catch:{ all -> 0x0083 }
            r7 = r7[r8]     // Catch:{ all -> 0x0083 }
            com.google.android.exoplayer2.Format r0 = r4.downstreamFormat     // Catch:{ all -> 0x0083 }
            if (r7 == r0) goto L_0x0043
            goto L_0x007a
        L_0x0043:
            boolean r5 = r4.mayReadSample(r8)     // Catch:{ all -> 0x0083 }
            if (r5 != 0) goto L_0x004b
            monitor-exit(r4)
            return r2
        L_0x004b:
            int[] r5 = r4.flags     // Catch:{ all -> 0x0083 }
            r5 = r5[r8]     // Catch:{ all -> 0x0083 }
            r6.setFlags(r5)     // Catch:{ all -> 0x0083 }
            long[] r5 = r4.timesUs     // Catch:{ all -> 0x0083 }
            r0 = r5[r8]     // Catch:{ all -> 0x0083 }
            r6.timeUs = r0     // Catch:{ all -> 0x0083 }
            boolean r5 = r6.isFlagsOnly()     // Catch:{ all -> 0x0083 }
            if (r5 == 0) goto L_0x0060
            monitor-exit(r4)
            return r3
        L_0x0060:
            int[] r5 = r4.sizes     // Catch:{ all -> 0x0083 }
            r5 = r5[r8]     // Catch:{ all -> 0x0083 }
            r9.size = r5     // Catch:{ all -> 0x0083 }
            long[] r5 = r4.offsets     // Catch:{ all -> 0x0083 }
            r6 = r5[r8]     // Catch:{ all -> 0x0083 }
            r9.offset = r6     // Catch:{ all -> 0x0083 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r5 = r4.cryptoDatas     // Catch:{ all -> 0x0083 }
            r5 = r5[r8]     // Catch:{ all -> 0x0083 }
            r9.cryptoData = r5     // Catch:{ all -> 0x0083 }
            int r5 = r4.readPosition     // Catch:{ all -> 0x0083 }
            int r5 = r5 + 1
            r4.readPosition = r5     // Catch:{ all -> 0x0083 }
            monitor-exit(r4)
            return r3
        L_0x007a:
            com.google.android.exoplayer2.Format[] r6 = r4.formats     // Catch:{ all -> 0x0083 }
            r6 = r6[r8]     // Catch:{ all -> 0x0083 }
            r4.onFormatResult(r6, r5)     // Catch:{ all -> 0x0083 }
            monitor-exit(r4)
            return r1
        L_0x0083:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.read(com.google.android.exoplayer2.FormatHolder, com.google.android.exoplayer2.decoder.DecoderInputBuffer, boolean, boolean, com.google.android.exoplayer2.source.SampleMetadataQueue$SampleExtrasHolder):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int advanceTo(long r9, boolean r11, boolean r12) {
        /*
            r8 = this;
            monitor-enter(r8)
            int r0 = r8.readPosition     // Catch:{ all -> 0x0039 }
            int r2 = r8.getRelativeIndex(r0)     // Catch:{ all -> 0x0039 }
            boolean r0 = r8.hasNextSample()     // Catch:{ all -> 0x0039 }
            r7 = -1
            if (r0 == 0) goto L_0x0037
            long[] r0 = r8.timesUs     // Catch:{ all -> 0x0039 }
            r3 = r0[r2]     // Catch:{ all -> 0x0039 }
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x0037
            long r0 = r8.largestQueuedTimestampUs     // Catch:{ all -> 0x0039 }
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x001f
            if (r12 != 0) goto L_0x001f
            goto L_0x0037
        L_0x001f:
            int r12 = r8.length     // Catch:{ all -> 0x0039 }
            int r0 = r8.readPosition     // Catch:{ all -> 0x0039 }
            int r3 = r12 - r0
            r1 = r8
            r4 = r9
            r6 = r11
            int r9 = r1.findSampleBefore(r2, r3, r4, r6)     // Catch:{ all -> 0x0039 }
            if (r9 != r7) goto L_0x0030
            monitor-exit(r8)
            return r7
        L_0x0030:
            int r10 = r8.readPosition     // Catch:{ all -> 0x0039 }
            int r10 = r10 + r9
            r8.readPosition = r10     // Catch:{ all -> 0x0039 }
            monitor-exit(r8)
            return r9
        L_0x0037:
            monitor-exit(r8)
            return r7
        L_0x0039:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.advanceTo(long, boolean, boolean):int");
    }

    public synchronized int advanceToEnd() {
        int i;
        i = this.length - this.readPosition;
        this.readPosition = this.length;
        return i;
    }

    public synchronized boolean setReadPosition(int i) {
        if (this.absoluteFirstIndex > i || i > this.absoluteFirstIndex + this.length) {
            return false;
        }
        this.readPosition = i - this.absoluteFirstIndex;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0037, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long discardTo(long r10, boolean r12, boolean r13) {
        /*
            r9 = this;
            monitor-enter(r9)
            int r0 = r9.length     // Catch:{ all -> 0x0038 }
            r1 = -1
            if (r0 == 0) goto L_0x0036
            long[] r0 = r9.timesUs     // Catch:{ all -> 0x0038 }
            int r3 = r9.relativeFirstIndex     // Catch:{ all -> 0x0038 }
            r3 = r0[r3]     // Catch:{ all -> 0x0038 }
            int r0 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0012
            goto L_0x0036
        L_0x0012:
            if (r13 == 0) goto L_0x001f
            int r13 = r9.readPosition     // Catch:{ all -> 0x0038 }
            int r0 = r9.length     // Catch:{ all -> 0x0038 }
            if (r13 == r0) goto L_0x001f
            int r13 = r9.readPosition     // Catch:{ all -> 0x0038 }
            int r13 = r13 + 1
            goto L_0x0021
        L_0x001f:
            int r13 = r9.length     // Catch:{ all -> 0x0038 }
        L_0x0021:
            r5 = r13
            int r4 = r9.relativeFirstIndex     // Catch:{ all -> 0x0038 }
            r3 = r9
            r6 = r10
            r8 = r12
            int r10 = r3.findSampleBefore(r4, r5, r6, r8)     // Catch:{ all -> 0x0038 }
            r11 = -1
            if (r10 != r11) goto L_0x0030
            monitor-exit(r9)
            return r1
        L_0x0030:
            long r10 = r9.discardSamples(r10)     // Catch:{ all -> 0x0038 }
            monitor-exit(r9)
            return r10
        L_0x0036:
            monitor-exit(r9)
            return r1
        L_0x0038:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.discardTo(long, boolean, boolean):long");
    }

    public synchronized long discardToRead() {
        if (this.readPosition == 0) {
            return -1;
        }
        return discardSamples(this.readPosition);
    }

    public synchronized long discardToEnd() {
        if (this.length == 0) {
            return -1;
        }
        return discardSamples(this.length);
    }

    public synchronized boolean format(Format format) {
        if (format == null) {
            this.upstreamFormatRequired = true;
            return false;
        }
        this.upstreamFormatRequired = false;
        if (Util.areEqual(format, this.upstreamFormat)) {
            return false;
        }
        if (Util.areEqual(format, this.upstreamCommittedFormat)) {
            this.upstreamFormat = this.upstreamCommittedFormat;
            return true;
        }
        this.upstreamFormat = format;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00e2, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void commitSample(long r6, int r8, long r9, int r11, com.google.android.exoplayer2.extractor.TrackOutput.CryptoData r12) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.upstreamKeyframeRequired     // Catch:{ all -> 0x00e3 }
            r1 = 0
            if (r0 == 0) goto L_0x000e
            r0 = r8 & 1
            if (r0 != 0) goto L_0x000c
            monitor-exit(r5)
            return
        L_0x000c:
            r5.upstreamKeyframeRequired = r1     // Catch:{ all -> 0x00e3 }
        L_0x000e:
            boolean r0 = r5.upstreamFormatRequired     // Catch:{ all -> 0x00e3 }
            r2 = 1
            if (r0 != 0) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            com.google.android.exoplayer2.util.Assertions.checkState(r0)     // Catch:{ all -> 0x00e3 }
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r8
            if (r0 == 0) goto L_0x0020
            r0 = 1
            goto L_0x0021
        L_0x0020:
            r0 = 0
        L_0x0021:
            r5.isLastSampleQueued = r0     // Catch:{ all -> 0x00e3 }
            long r3 = r5.largestQueuedTimestampUs     // Catch:{ all -> 0x00e3 }
            long r3 = java.lang.Math.max(r3, r6)     // Catch:{ all -> 0x00e3 }
            r5.largestQueuedTimestampUs = r3     // Catch:{ all -> 0x00e3 }
            int r0 = r5.length     // Catch:{ all -> 0x00e3 }
            int r0 = r5.getRelativeIndex(r0)     // Catch:{ all -> 0x00e3 }
            long[] r3 = r5.timesUs     // Catch:{ all -> 0x00e3 }
            r3[r0] = r6     // Catch:{ all -> 0x00e3 }
            long[] r6 = r5.offsets     // Catch:{ all -> 0x00e3 }
            r6[r0] = r9     // Catch:{ all -> 0x00e3 }
            int[] r6 = r5.sizes     // Catch:{ all -> 0x00e3 }
            r6[r0] = r11     // Catch:{ all -> 0x00e3 }
            int[] r6 = r5.flags     // Catch:{ all -> 0x00e3 }
            r6[r0] = r8     // Catch:{ all -> 0x00e3 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r6 = r5.cryptoDatas     // Catch:{ all -> 0x00e3 }
            r6[r0] = r12     // Catch:{ all -> 0x00e3 }
            com.google.android.exoplayer2.Format[] r6 = r5.formats     // Catch:{ all -> 0x00e3 }
            com.google.android.exoplayer2.Format r7 = r5.upstreamFormat     // Catch:{ all -> 0x00e3 }
            r6[r0] = r7     // Catch:{ all -> 0x00e3 }
            int[] r6 = r5.sourceIds     // Catch:{ all -> 0x00e3 }
            int r7 = r5.upstreamSourceId     // Catch:{ all -> 0x00e3 }
            r6[r0] = r7     // Catch:{ all -> 0x00e3 }
            com.google.android.exoplayer2.Format r6 = r5.upstreamFormat     // Catch:{ all -> 0x00e3 }
            r5.upstreamCommittedFormat = r6     // Catch:{ all -> 0x00e3 }
            int r6 = r5.length     // Catch:{ all -> 0x00e3 }
            int r6 = r6 + r2
            r5.length = r6     // Catch:{ all -> 0x00e3 }
            int r7 = r5.capacity     // Catch:{ all -> 0x00e3 }
            if (r6 != r7) goto L_0x00e1
            int r6 = r5.capacity     // Catch:{ all -> 0x00e3 }
            int r6 = r6 + 1000
            int[] r7 = new int[r6]     // Catch:{ all -> 0x00e3 }
            long[] r8 = new long[r6]     // Catch:{ all -> 0x00e3 }
            long[] r9 = new long[r6]     // Catch:{ all -> 0x00e3 }
            int[] r10 = new int[r6]     // Catch:{ all -> 0x00e3 }
            int[] r11 = new int[r6]     // Catch:{ all -> 0x00e3 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r12 = new com.google.android.exoplayer2.extractor.TrackOutput.CryptoData[r6]     // Catch:{ all -> 0x00e3 }
            com.google.android.exoplayer2.Format[] r0 = new com.google.android.exoplayer2.Format[r6]     // Catch:{ all -> 0x00e3 }
            int r2 = r5.capacity     // Catch:{ all -> 0x00e3 }
            int r3 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e3 }
            int r2 = r2 - r3
            long[] r3 = r5.offsets     // Catch:{ all -> 0x00e3 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r3, r4, r8, r1, r2)     // Catch:{ all -> 0x00e3 }
            long[] r3 = r5.timesUs     // Catch:{ all -> 0x00e3 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r3, r4, r9, r1, r2)     // Catch:{ all -> 0x00e3 }
            int[] r3 = r5.flags     // Catch:{ all -> 0x00e3 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r3, r4, r10, r1, r2)     // Catch:{ all -> 0x00e3 }
            int[] r3 = r5.sizes     // Catch:{ all -> 0x00e3 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r3, r4, r11, r1, r2)     // Catch:{ all -> 0x00e3 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r3 = r5.cryptoDatas     // Catch:{ all -> 0x00e3 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r3, r4, r12, r1, r2)     // Catch:{ all -> 0x00e3 }
            com.google.android.exoplayer2.Format[] r3 = r5.formats     // Catch:{ all -> 0x00e3 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r3, r4, r0, r1, r2)     // Catch:{ all -> 0x00e3 }
            int[] r3 = r5.sourceIds     // Catch:{ all -> 0x00e3 }
            int r4 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r3, r4, r7, r1, r2)     // Catch:{ all -> 0x00e3 }
            int r3 = r5.relativeFirstIndex     // Catch:{ all -> 0x00e3 }
            long[] r4 = r5.offsets     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r4, r1, r8, r2, r3)     // Catch:{ all -> 0x00e3 }
            long[] r4 = r5.timesUs     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r4, r1, r9, r2, r3)     // Catch:{ all -> 0x00e3 }
            int[] r4 = r5.flags     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r4, r1, r10, r2, r3)     // Catch:{ all -> 0x00e3 }
            int[] r4 = r5.sizes     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r4, r1, r11, r2, r3)     // Catch:{ all -> 0x00e3 }
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData[] r4 = r5.cryptoDatas     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r4, r1, r12, r2, r3)     // Catch:{ all -> 0x00e3 }
            com.google.android.exoplayer2.Format[] r4 = r5.formats     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r4, r1, r0, r2, r3)     // Catch:{ all -> 0x00e3 }
            int[] r4 = r5.sourceIds     // Catch:{ all -> 0x00e3 }
            java.lang.System.arraycopy(r4, r1, r7, r2, r3)     // Catch:{ all -> 0x00e3 }
            r5.offsets = r8     // Catch:{ all -> 0x00e3 }
            r5.timesUs = r9     // Catch:{ all -> 0x00e3 }
            r5.flags = r10     // Catch:{ all -> 0x00e3 }
            r5.sizes = r11     // Catch:{ all -> 0x00e3 }
            r5.cryptoDatas = r12     // Catch:{ all -> 0x00e3 }
            r5.formats = r0     // Catch:{ all -> 0x00e3 }
            r5.sourceIds = r7     // Catch:{ all -> 0x00e3 }
            r5.relativeFirstIndex = r1     // Catch:{ all -> 0x00e3 }
            int r7 = r5.capacity     // Catch:{ all -> 0x00e3 }
            r5.length = r7     // Catch:{ all -> 0x00e3 }
            r5.capacity = r6     // Catch:{ all -> 0x00e3 }
        L_0x00e1:
            monitor-exit(r5)
            return
        L_0x00e3:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.commitSample(long, int, long, int, com.google.android.exoplayer2.extractor.TrackOutput$CryptoData):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean attemptSplice(long r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            int r0 = r7.length     // Catch:{ all -> 0x004a }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0010
            long r3 = r7.largestDiscardedTimestampUs     // Catch:{ all -> 0x004a }
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x000e
            r1 = 1
        L_0x000e:
            monitor-exit(r7)
            return r1
        L_0x0010:
            long r3 = r7.largestDiscardedTimestampUs     // Catch:{ all -> 0x004a }
            int r0 = r7.readPosition     // Catch:{ all -> 0x004a }
            long r5 = r7.getLargestTimestamp(r0)     // Catch:{ all -> 0x004a }
            long r3 = java.lang.Math.max(r3, r5)     // Catch:{ all -> 0x004a }
            int r0 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r0 < 0) goto L_0x0022
            monitor-exit(r7)
            return r1
        L_0x0022:
            int r0 = r7.length     // Catch:{ all -> 0x004a }
            int r1 = r7.length     // Catch:{ all -> 0x004a }
            int r1 = r1 - r2
            int r1 = r7.getRelativeIndex(r1)     // Catch:{ all -> 0x004a }
        L_0x002b:
            int r3 = r7.readPosition     // Catch:{ all -> 0x004a }
            if (r0 <= r3) goto L_0x0042
            long[] r3 = r7.timesUs     // Catch:{ all -> 0x004a }
            r4 = r3[r1]     // Catch:{ all -> 0x004a }
            int r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r3 < 0) goto L_0x0042
            int r0 = r0 + -1
            int r1 = r1 + -1
            r3 = -1
            if (r1 != r3) goto L_0x002b
            int r1 = r7.capacity     // Catch:{ all -> 0x004a }
            int r1 = r1 - r2
            goto L_0x002b
        L_0x0042:
            int r8 = r7.absoluteFirstIndex     // Catch:{ all -> 0x004a }
            int r8 = r8 + r0
            r7.discardUpstreamSamples(r8)     // Catch:{ all -> 0x004a }
            monitor-exit(r7)
            return r2
        L_0x004a:
            r8 = move-exception
            monitor-exit(r7)
            goto L_0x004e
        L_0x004d:
            throw r8
        L_0x004e:
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleMetadataQueue.attemptSplice(long):boolean");
    }

    private boolean hasNextSample() {
        return this.readPosition != this.length;
    }

    private void onFormatResult(Format format, FormatHolder formatHolder) {
        DrmInitData drmInitData;
        DrmSession<?> drmSession;
        formatHolder.format = format;
        boolean z = this.downstreamFormat == null;
        if (z) {
            drmInitData = null;
        } else {
            drmInitData = this.downstreamFormat.drmInitData;
        }
        this.downstreamFormat = format;
        if (this.drmSessionManager != DrmSessionManager.DUMMY) {
            DrmInitData drmInitData2 = format.drmInitData;
            formatHolder.includesDrmSession = true;
            formatHolder.drmSession = this.currentDrmSession;
            if (z || !Util.areEqual(drmInitData, drmInitData2)) {
                DrmSession<?> drmSession2 = this.currentDrmSession;
                Looper looper = (Looper) Assertions.checkNotNull(Looper.myLooper());
                if (drmInitData2 != null) {
                    drmSession = this.drmSessionManager.acquireSession(looper, drmInitData2);
                } else {
                    drmSession = this.drmSessionManager.acquirePlaceholderSession(looper, MimeTypes.getTrackType(format.sampleMimeType));
                }
                this.currentDrmSession = drmSession;
                formatHolder.drmSession = drmSession;
                if (drmSession2 != null) {
                    drmSession2.release();
                }
            }
        }
    }

    private boolean mayReadSample(int i) {
        DrmSession<?> drmSession;
        if (this.drmSessionManager == DrmSessionManager.DUMMY || (drmSession = this.currentDrmSession) == null || drmSession.getState() == 4) {
            return true;
        }
        if ((this.flags[i] & 1073741824) != 0 || !this.currentDrmSession.playClearSamplesWithoutKeys()) {
            return false;
        }
        return true;
    }

    private int findSampleBefore(int i, int i2, long j, boolean z) {
        int i3 = -1;
        for (int i4 = 0; i4 < i2 && this.timesUs[i] <= j; i4++) {
            if (!z || (this.flags[i] & 1) != 0) {
                i3 = i4;
            }
            i++;
            if (i == this.capacity) {
                i = 0;
            }
        }
        return i3;
    }

    private long discardSamples(int i) {
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i));
        this.length -= i;
        this.absoluteFirstIndex += i;
        int i2 = this.relativeFirstIndex + i;
        this.relativeFirstIndex = i2;
        int i3 = this.capacity;
        if (i2 >= i3) {
            this.relativeFirstIndex = i2 - i3;
        }
        int i4 = this.readPosition - i;
        this.readPosition = i4;
        if (i4 < 0) {
            this.readPosition = 0;
        }
        if (this.length != 0) {
            return this.offsets[this.relativeFirstIndex];
        }
        int i5 = this.relativeFirstIndex;
        if (i5 == 0) {
            i5 = this.capacity;
        }
        int i6 = i5 - 1;
        return this.offsets[i6] + ((long) this.sizes[i6]);
    }

    private long getLargestTimestamp(int i) {
        long j = Long.MIN_VALUE;
        if (i == 0) {
            return Long.MIN_VALUE;
        }
        int relativeIndex = getRelativeIndex(i - 1);
        for (int i2 = 0; i2 < i; i2++) {
            j = Math.max(j, this.timesUs[relativeIndex]);
            if ((this.flags[relativeIndex] & 1) != 0) {
                break;
            }
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return j;
    }

    private int getRelativeIndex(int i) {
        int i2 = this.relativeFirstIndex + i;
        int i3 = this.capacity;
        return i2 < i3 ? i2 : i2 - i3;
    }
}
