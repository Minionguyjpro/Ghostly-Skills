package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;

final class PlaybackInfo {
    private static final MediaSource.MediaPeriodId DUMMY_MEDIA_PERIOD_ID = new MediaSource.MediaPeriodId(new Object());
    public volatile long bufferedPositionUs;
    public final long contentPositionUs;
    public final boolean isLoading;
    public final MediaSource.MediaPeriodId loadingMediaPeriodId;
    public final MediaSource.MediaPeriodId periodId;
    public final ExoPlaybackException playbackError;
    public final int playbackState;
    public volatile long positionUs;
    public final long startPositionUs;
    public final Timeline timeline;
    public volatile long totalBufferedDurationUs;
    public final TrackGroupArray trackGroups;
    public final TrackSelectorResult trackSelectorResult;

    public static PlaybackInfo createDummy(long j, TrackSelectorResult trackSelectorResult2) {
        TrackSelectorResult trackSelectorResult3 = trackSelectorResult2;
        return new PlaybackInfo(Timeline.EMPTY, DUMMY_MEDIA_PERIOD_ID, j, -9223372036854775807L, 1, (ExoPlaybackException) null, false, TrackGroupArray.EMPTY, trackSelectorResult3, DUMMY_MEDIA_PERIOD_ID, j, 0, j);
    }

    public PlaybackInfo(Timeline timeline2, MediaSource.MediaPeriodId mediaPeriodId, long j, long j2, int i, ExoPlaybackException exoPlaybackException, boolean z, TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult2, MediaSource.MediaPeriodId mediaPeriodId2, long j3, long j4, long j5) {
        this.timeline = timeline2;
        this.periodId = mediaPeriodId;
        this.startPositionUs = j;
        this.contentPositionUs = j2;
        this.playbackState = i;
        this.playbackError = exoPlaybackException;
        this.isLoading = z;
        this.trackGroups = trackGroupArray;
        this.trackSelectorResult = trackSelectorResult2;
        this.loadingMediaPeriodId = mediaPeriodId2;
        this.bufferedPositionUs = j3;
        this.totalBufferedDurationUs = j4;
        this.positionUs = j5;
    }

    public MediaSource.MediaPeriodId getDummyFirstMediaPeriodId(boolean z, Timeline.Window window, Timeline.Period period) {
        if (this.timeline.isEmpty()) {
            return DUMMY_MEDIA_PERIOD_ID;
        }
        int firstWindowIndex = this.timeline.getFirstWindowIndex(z);
        int i = this.timeline.getWindow(firstWindowIndex, window).firstPeriodIndex;
        int indexOfPeriod = this.timeline.getIndexOfPeriod(this.periodId.periodUid);
        long j = -1;
        if (indexOfPeriod != -1 && firstWindowIndex == this.timeline.getPeriod(indexOfPeriod, period).windowIndex) {
            j = this.periodId.windowSequenceNumber;
        }
        return new MediaSource.MediaPeriodId(this.timeline.getUidOfPeriod(i), j);
    }

    public PlaybackInfo copyWithNewPosition(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2, long j3) {
        return new PlaybackInfo(this.timeline, mediaPeriodId, j, mediaPeriodId.isAd() ? j2 : -9223372036854775807L, this.playbackState, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, j3, j);
    }

    public PlaybackInfo copyWithTimeline(Timeline timeline2) {
        Timeline timeline3 = timeline2;
        return new PlaybackInfo(timeline2, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithPlaybackState(int i) {
        Timeline timeline2 = this.timeline;
        return new PlaybackInfo(timeline2, this.periodId, this.startPositionUs, this.contentPositionUs, i, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithPlaybackError(ExoPlaybackException exoPlaybackException) {
        Timeline timeline2 = this.timeline;
        return new PlaybackInfo(timeline2, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, exoPlaybackException, this.isLoading, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithIsLoading(boolean z) {
        Timeline timeline2 = this.timeline;
        return new PlaybackInfo(timeline2, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.playbackError, z, this.trackGroups, this.trackSelectorResult, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithTrackInfo(TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult2) {
        Timeline timeline2 = this.timeline;
        return new PlaybackInfo(timeline2, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.playbackError, this.isLoading, trackGroupArray, trackSelectorResult2, this.loadingMediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }

    public PlaybackInfo copyWithLoadingMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        Timeline timeline2 = this.timeline;
        return new PlaybackInfo(timeline2, this.periodId, this.startPositionUs, this.contentPositionUs, this.playbackState, this.playbackError, this.isLoading, this.trackGroups, this.trackSelectorResult, mediaPeriodId, this.bufferedPositionUs, this.totalBufferedDurationUs, this.positionUs);
    }
}
