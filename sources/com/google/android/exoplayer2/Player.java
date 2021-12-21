package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

public interface Player {
    void addListener(EventListener eventListener);

    long getCurrentPosition();

    Timeline getCurrentTimeline();

    int getCurrentWindowIndex();

    long getDuration();

    boolean getPlayWhenReady();

    int getPlaybackState();

    int getPlaybackSuppressionReason();

    void release();

    void seekTo(int i, long j);

    void seekTo(long j);

    void setPlayWhenReady(boolean z);

    void stop();

    void stop(boolean z);

    public interface EventListener {
        void onIsPlayingChanged(boolean z);

        void onLoadingChanged(boolean z);

        void onPlaybackParametersChanged(PlaybackParameters playbackParameters);

        void onPlaybackSuppressionReasonChanged(int i);

        void onPlayerError(ExoPlaybackException exoPlaybackException);

        void onPlayerStateChanged(boolean z, int i);

        void onPositionDiscontinuity(int i);

        void onSeekProcessed();

        void onTimelineChanged(Timeline timeline, int i);

        @Deprecated
        void onTimelineChanged(Timeline timeline, Object obj, int i);

        void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray);

        /* renamed from: com.google.android.exoplayer2.Player$EventListener$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onIsPlayingChanged(EventListener eventListener, boolean z) {
            }

            public static void $default$onLoadingChanged(EventListener eventListener, boolean z) {
            }

            public static void $default$onPlaybackParametersChanged(EventListener eventListener, PlaybackParameters playbackParameters) {
            }

            public static void $default$onPlaybackSuppressionReasonChanged(EventListener eventListener, int i) {
            }

            public static void $default$onPlayerError(EventListener eventListener, ExoPlaybackException exoPlaybackException) {
            }

            public static void $default$onPlayerStateChanged(EventListener eventListener, boolean z, int i) {
            }

            public static void $default$onPositionDiscontinuity(EventListener eventListener, int i) {
            }

            public static void $default$onSeekProcessed(EventListener eventListener) {
            }

            @Deprecated
            public static void $default$onTimelineChanged(EventListener eventListener, Timeline timeline, Object obj, int i) {
            }

            public static void $default$onTracksChanged(EventListener eventListener, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            }

            public static void $default$onTimelineChanged(EventListener _this, Timeline timeline, int i) {
                _this.onTimelineChanged(timeline, timeline.getWindowCount() == 1 ? timeline.getWindow(0, new Timeline.Window()).manifest : null, i);
            }
        }
    }

    @Deprecated
    public static abstract class DefaultEventListener implements EventListener {
        public /* synthetic */ void onIsPlayingChanged(boolean z) {
            EventListener.CC.$default$onIsPlayingChanged(this, z);
        }

        public /* synthetic */ void onLoadingChanged(boolean z) {
            EventListener.CC.$default$onLoadingChanged(this, z);
        }

        public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            EventListener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
        }

        public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
            EventListener.CC.$default$onPlaybackSuppressionReasonChanged(this, i);
        }

        public /* synthetic */ void onPlayerError(ExoPlaybackException exoPlaybackException) {
            EventListener.CC.$default$onPlayerError(this, exoPlaybackException);
        }

        public /* synthetic */ void onPlayerStateChanged(boolean z, int i) {
            EventListener.CC.$default$onPlayerStateChanged(this, z, i);
        }

        public /* synthetic */ void onPositionDiscontinuity(int i) {
            EventListener.CC.$default$onPositionDiscontinuity(this, i);
        }

        public /* synthetic */ void onSeekProcessed() {
            EventListener.CC.$default$onSeekProcessed(this);
        }

        @Deprecated
        public void onTimelineChanged(Timeline timeline, Object obj) {
        }

        public /* synthetic */ void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            EventListener.CC.$default$onTracksChanged(this, trackGroupArray, trackSelectionArray);
        }

        public void onTimelineChanged(Timeline timeline, int i) {
            onTimelineChanged(timeline, timeline.getWindowCount() == 1 ? timeline.getWindow(0, new Timeline.Window()).manifest : null, i);
        }

        public void onTimelineChanged(Timeline timeline, Object obj, int i) {
            onTimelineChanged(timeline, obj);
        }
    }
}
