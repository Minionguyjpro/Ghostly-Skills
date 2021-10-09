package androidx.media2.player.exoplayer;

import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.source.CompositeMediaSource;
import androidx.media2.exoplayer.external.source.MediaPeriod;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.upstream.Allocator;
import androidx.media2.exoplayer.external.upstream.TransferListener;

class DurationProvidingMediaSource extends CompositeMediaSource<Void> {
    private Timeline mCurrentTimeline;
    private final MediaSource mMediaSource;

    DurationProvidingMediaSource(MediaSource mediaSource) {
        this.mMediaSource = mediaSource;
    }

    public long getDurationMs() {
        Timeline timeline = this.mCurrentTimeline;
        if (timeline == null) {
            return -9223372036854775807L;
        }
        return timeline.getWindow(0, new Timeline.Window()).getDurationMs();
    }

    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        prepareChildSource(null, this.mMediaSource);
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return this.mMediaSource.createPeriod(mediaPeriodId, allocator, j);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.mMediaSource.releasePeriod(mediaPeriod);
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Void voidR, MediaSource mediaSource, Timeline timeline, Object obj) {
        this.mCurrentTimeline = timeline;
        refreshSourceInfo(timeline, obj);
    }
}
