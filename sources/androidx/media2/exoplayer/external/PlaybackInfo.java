package androidx.media2.exoplayer.external;

import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.source.TrackGroupArray;
import androidx.media2.exoplayer.external.trackselection.TrackSelectorResult;

final class PlaybackInfo {
    private static final MediaSource.MediaPeriodId DUMMY_MEDIA_PERIOD_ID = new MediaSource.MediaPeriodId(new Object());
    public volatile long bufferedPositionUs;
    public final long contentPositionUs;
    public final boolean isLoading;
    public final MediaSource.MediaPeriodId loadingMediaPeriodId;
    public final Object manifest;
    public final MediaSource.MediaPeriodId periodId;
    public final int playbackState;
    public volatile long positionUs;
    public final long startPositionUs;
    public final Timeline timeline;
    public volatile long totalBufferedDurationUs;
    public final TrackGroupArray trackGroups;
    public final TrackSelectorResult trackSelectorResult;

    public static PlaybackInfo createDummy(long j, TrackSelectorResult trackSelectorResult2) {
        TrackSelectorResult trackSelectorResult3 = trackSelectorResult2;
        return new PlaybackInfo(Timeline.EMPTY, (Object) null, DUMMY_MEDIA_PERIOD_ID, j, -9223372036854775807L, 1, false, TrackGroupArray.EMPTY, trackSelectorResult3, DUMMY_MEDIA_PERIOD_ID, j, 0, j);
    }

    public PlaybackInfo(Timeline timeline2, Object obj, MediaSource.MediaPeriodId mediaPeriodId, long j, long j2, int i, boolean z, TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult2, MediaSource.MediaPeriodId mediaPeriodId2, long j3, long j4, long j5) {
        this.timeline = timeline2;
        this.manifest = obj;
        this.periodId = mediaPeriodId;
        this.startPositionUs = j;
        this.contentPositionUs = j2;
        this.playbackState = i;
        this.isLoading = z;
        this.trackGroups = trackGroupArray;
        this.trackSelectorResult = trackSelectorResult2;
        this.loadingMediaPeriodId = mediaPeriodId2;
        this.bufferedPositionUs = j3;
        this.totalBufferedDurationUs = j4;
        this.positionUs = j5;
    }

    public MediaSource.MediaPeriodId getDummyFirstMediaPeriodId(boolean z, Timeline.Window window) {
        if (this.timeline.isEmpty()) {
            return DUMMY_MEDIA_PERIOD_ID;
        }
        Timeline timeline2 = this.timeline;
        return new MediaSource.MediaPeriodId(this.timeline.getUidOfPeriod(timeline2.getWindow(timeline2.getFirstWindowIndex(z), window).firstPeriodIndex));
    }

    public PlaybackInfo copyWithNewPosition(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2, long j3) {
        return new PlaybackInfo(this.timeline, this.manifest, mediaPeriodId, j, mediaPeriodId.isAd() ? j2 : -9223372036854775807L, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, j3, j);
    }

    public PlaybackInfo copyWithTimeline(Timeline timeline2, Object obj) {
        Timeline timeline3 = timeline2;
        return new PlaybackInfo(timeline2, obj, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithPlaybackState(int i) {
        Timeline timeline2 = this.timeline;
        return new PlaybackInfo(timeline2, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, i, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithIsLoading(boolean z) {
        Timeline timeline2 = this.timeline;
        return new PlaybackInfo(timeline2, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, z, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithTrackInfo(TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult2) {
        Timeline timeline2 = this.timeline;
        return new PlaybackInfo(timeline2, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, trackGroupArray, trackSelectorResult2, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithLoadingMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        Timeline timeline2 = this.timeline;
        return new PlaybackInfo(timeline2, this.manifest, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.isLoading, this.trackGroups, this.trackSelectorResult, mediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }
}
