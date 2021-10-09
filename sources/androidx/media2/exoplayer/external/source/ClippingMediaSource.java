package androidx.media2.exoplayer.external.source;

import androidx.media2.exoplayer.external.C;
import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.upstream.Allocator;
import androidx.media2.exoplayer.external.upstream.TransferListener;
import androidx.media2.exoplayer.external.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;

public final class ClippingMediaSource extends CompositeMediaSource<Void> {
    private final boolean allowDynamicClippingUpdates;
    private IllegalClippingException clippingError;
    private ClippingTimeline clippingTimeline;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private Object manifest;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private final MediaSource mediaSource;
    private long periodEndUs;
    private long periodStartUs;
    private final boolean relativeToDefaultPosition;
    private final long startUs;
    private final Timeline.Window window;

    public static final class IllegalClippingException extends IOException {
        public final int reason;

        private static String getReasonDescription(int i) {
            return i != 0 ? i != 1 ? i != 2 ? "unknown" : "start exceeds end" : "not seekable to start" : "invalid period count";
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public IllegalClippingException(int r4) {
            /*
                r3 = this;
                java.lang.String r0 = getReasonDescription(r4)
                java.lang.String r0 = java.lang.String.valueOf(r0)
                int r1 = r0.length()
                java.lang.String r2 = "Illegal clipping: "
                if (r1 == 0) goto L_0x0015
                java.lang.String r0 = r2.concat(r0)
                goto L_0x001a
            L_0x0015:
                java.lang.String r0 = new java.lang.String
                r0.<init>(r2)
            L_0x001a:
                r3.<init>(r0)
                r3.reason = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.source.ClippingMediaSource.IllegalClippingException.<init>(int):void");
        }
    }

    public ClippingMediaSource(MediaSource mediaSource2, long j, long j2, boolean z, boolean z2, boolean z3) {
        Assertions.checkArgument(j >= 0);
        this.mediaSource = (MediaSource) Assertions.checkNotNull(mediaSource2);
        this.startUs = j;
        this.endUs = j2;
        this.enableInitialDiscontinuity = z;
        this.allowDynamicClippingUpdates = z2;
        this.relativeToDefaultPosition = z3;
        this.mediaPeriods = new ArrayList<>();
        this.window = new Timeline.Window();
    }

    public Object getTag() {
        return this.mediaSource.getTag();
    }

    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareChildSource(null, this.mediaSource);
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }
        throw illegalClippingException;
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator, j), this.enableInitialDiscontinuity, this.periodStartUs, this.periodEndUs);
        this.mediaPeriods.add(clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
        if (this.mediaPeriods.isEmpty() && !this.allowDynamicClippingUpdates) {
            refreshClippedTimeline(((ClippingTimeline) Assertions.checkNotNull(this.clippingTimeline)).timeline);
        }
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.clippingError = null;
        this.clippingTimeline = null;
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Void voidR, MediaSource mediaSource2, Timeline timeline, Object obj) {
        if (this.clippingError == null) {
            this.manifest = obj;
            refreshClippedTimeline(timeline);
        }
    }

    private void refreshClippedTimeline(Timeline timeline) {
        long j;
        timeline.getWindow(0, this.window);
        long positionInFirstPeriodUs = this.window.getPositionInFirstPeriodUs();
        long j2 = Long.MIN_VALUE;
        if (this.clippingTimeline == null || this.mediaPeriods.isEmpty() || this.allowDynamicClippingUpdates) {
            long j3 = this.startUs;
            long j4 = this.endUs;
            if (this.relativeToDefaultPosition) {
                long defaultPositionUs = this.window.getDefaultPositionUs();
                j3 += defaultPositionUs;
                j4 += defaultPositionUs;
            }
            this.periodStartUs = positionInFirstPeriodUs + j3;
            if (this.endUs != Long.MIN_VALUE) {
                j2 = positionInFirstPeriodUs + j4;
            }
            this.periodEndUs = j2;
            int size = this.mediaPeriods.size();
            for (int i = 0; i < size; i++) {
                this.mediaPeriods.get(i).updateClipping(this.periodStartUs, this.periodEndUs);
            }
            j = j3;
            j2 = j4;
        } else {
            long j5 = this.periodStartUs - positionInFirstPeriodUs;
            if (this.endUs != Long.MIN_VALUE) {
                j2 = this.periodEndUs - positionInFirstPeriodUs;
            }
            j = j5;
        }
        try {
            ClippingTimeline clippingTimeline2 = new ClippingTimeline(timeline, j, j2);
            this.clippingTimeline = clippingTimeline2;
            refreshSourceInfo(clippingTimeline2, this.manifest);
        } catch (IllegalClippingException e) {
            this.clippingError = e;
        }
    }

    /* access modifiers changed from: protected */
    public long getMediaTimeForChildMediaTime(Void voidR, long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        long usToMs = C.usToMs(this.startUs);
        long max = Math.max(0, j - usToMs);
        long j2 = this.endUs;
        return j2 != Long.MIN_VALUE ? Math.min(C.usToMs(j2) - usToMs, max) : max;
    }

    private static final class ClippingTimeline extends ForwardingTimeline {
        private final long durationUs;
        private final long endUs;
        private final boolean isDynamic;
        private final long startUs;

        public ClippingTimeline(Timeline timeline, long j, long j2) throws IllegalClippingException {
            super(timeline);
            boolean z = false;
            if (timeline.getPeriodCount() == 1) {
                Timeline.Window window = timeline.getWindow(0, new Timeline.Window());
                long max = Math.max(0, j);
                long max2 = j2 == Long.MIN_VALUE ? window.durationUs : Math.max(0, j2);
                if (window.durationUs != -9223372036854775807L) {
                    max2 = max2 > window.durationUs ? window.durationUs : max2;
                    if (max != 0 && !window.isSeekable) {
                        throw new IllegalClippingException(1);
                    } else if (max > max2) {
                        throw new IllegalClippingException(2);
                    }
                }
                this.startUs = max;
                this.endUs = max2;
                this.durationUs = max2 == -9223372036854775807L ? -9223372036854775807L : max2 - max;
                if (window.isDynamic && (max2 == -9223372036854775807L || (window.durationUs != -9223372036854775807L && max2 == window.durationUs))) {
                    z = true;
                }
                this.isDynamic = z;
                return;
            }
            throw new IllegalClippingException(0);
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
            long j2;
            this.timeline.getWindow(0, window, z, 0);
            window.positionInFirstPeriodUs += this.startUs;
            window.durationUs = this.durationUs;
            window.isDynamic = this.isDynamic;
            if (window.defaultPositionUs != -9223372036854775807L) {
                window.defaultPositionUs = Math.max(window.defaultPositionUs, this.startUs);
                if (this.endUs == -9223372036854775807L) {
                    j2 = window.defaultPositionUs;
                } else {
                    j2 = Math.min(window.defaultPositionUs, this.endUs);
                }
                window.defaultPositionUs = j2;
                window.defaultPositionUs -= this.startUs;
            }
            long usToMs = C.usToMs(this.startUs);
            if (window.presentationStartTimeMs != -9223372036854775807L) {
                window.presentationStartTimeMs += usToMs;
            }
            if (window.windowStartTimeMs != -9223372036854775807L) {
                window.windowStartTimeMs += usToMs;
            }
            return window;
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(0, period, z);
            long positionInWindowUs = period.getPositionInWindowUs() - this.startUs;
            long j = this.durationUs;
            return period.set(period.id, period.uid, 0, j == -9223372036854775807L ? -9223372036854775807L : j - positionInWindowUs, positionInWindowUs);
        }
    }
}
