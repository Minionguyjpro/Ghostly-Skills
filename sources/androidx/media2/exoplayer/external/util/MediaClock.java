package androidx.media2.exoplayer.external.util;

import androidx.media2.exoplayer.external.PlaybackParameters;

public interface MediaClock {
    PlaybackParameters getPlaybackParameters();

    long getPositionUs();

    PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters);
}
