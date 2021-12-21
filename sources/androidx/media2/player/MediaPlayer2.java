package androidx.media2.player;

import android.content.Context;
import android.media.MediaDrmException;
import android.media.MediaFormat;
import android.view.Surface;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.SubtitleData;
import androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl;
import java.util.List;
import java.util.concurrent.Executor;

public abstract class MediaPlayer2 {

    public static abstract class DrmEventCallback {
    }

    public static abstract class EventCallback {
        public void onCallCompleted(MediaPlayer2 mediaPlayer2, MediaItem mediaItem, int i, int i2) {
        }

        public void onError(MediaPlayer2 mediaPlayer2, MediaItem mediaItem, int i, int i2) {
        }

        public void onInfo(MediaPlayer2 mediaPlayer2, MediaItem mediaItem, int i, int i2) {
        }

        public void onMediaTimeDiscontinuity(MediaPlayer2 mediaPlayer2, MediaItem mediaItem, MediaTimestamp mediaTimestamp) {
        }

        public void onSubtitleData(MediaPlayer2 mediaPlayer2, MediaItem mediaItem, int i, SubtitleData subtitleData) {
        }

        public void onTimedMetaDataAvailable(MediaPlayer2 mediaPlayer2, MediaItem mediaItem, TimedMetaData timedMetaData) {
        }

        public void onVideoSizeChanged(MediaPlayer2 mediaPlayer2, MediaItem mediaItem, int i, int i2) {
        }
    }

    public static class NoDrmSchemeException extends MediaDrmException {
    }

    public static abstract class TrackInfo {
        public abstract MediaFormat getFormat();

        public abstract int getTrackType();
    }

    public abstract boolean cancel(Object obj);

    public abstract void close();

    public abstract Object deselectTrack(int i);

    public abstract AudioAttributesCompat getAudioAttributes();

    public abstract long getBufferedPosition();

    public abstract MediaItem getCurrentMediaItem();

    public abstract long getCurrentPosition();

    public abstract long getDuration();

    public abstract PlaybackParams getPlaybackParams();

    public abstract float getPlayerVolume();

    public abstract int getSelectedTrack(int i);

    public abstract List<TrackInfo> getTrackInfo();

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public abstract Object pause();

    public abstract Object play();

    public abstract Object prepare();

    public abstract void reset();

    public abstract Object seekTo(long j, int i);

    public abstract Object selectTrack(int i);

    public abstract Object setAudioAttributes(AudioAttributesCompat audioAttributesCompat);

    public abstract void setDrmEventCallback(Executor executor, DrmEventCallback drmEventCallback);

    public abstract void setEventCallback(Executor executor, EventCallback eventCallback);

    public abstract Object setMediaItem(MediaItem mediaItem);

    public abstract Object setNextMediaItem(MediaItem mediaItem);

    public abstract Object setPlaybackParams(PlaybackParams playbackParams);

    public abstract Object setPlayerVolume(float f);

    public abstract Object setSurface(Surface surface);

    public abstract Object skipToNext();

    public static MediaPlayer2 create(Context context) {
        return new ExoPlayerMediaPlayer2Impl(context);
    }

    protected MediaPlayer2() {
    }

    public Object seekTo(long j) {
        return seekTo(j, 0);
    }
}
