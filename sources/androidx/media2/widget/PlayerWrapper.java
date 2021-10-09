package androidx.media2.widget;

import android.view.Surface;
import androidx.media2.common.BaseResult;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.VideoSize;
import androidx.media2.session.MediaController;
import androidx.media2.session.SessionCommandGroup;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

class PlayerWrapper {
    private final SessionCommandGroup mAllCommands;
    SessionCommandGroup mAllowedCommands;
    private boolean mCallbackAttached;
    private final Executor mCallbackExecutor;
    final MediaController mController;
    private final MediaControllerCallback mControllerCallback;
    MediaMetadata mMediaMetadata;
    final SessionPlayer mPlayer;
    private final SessionPlayerCallback mPlayerCallback;
    int mSavedPlayerState = 0;
    final PlayerCallback mWrapperCallback;

    PlayerWrapper(MediaController mediaController, Executor executor, PlayerCallback playerCallback) {
        if (mediaController == null) {
            throw new NullPointerException("controller must not be null");
        } else if (executor == null) {
            throw new NullPointerException("executor must not be null");
        } else if (playerCallback != null) {
            this.mController = mediaController;
            this.mCallbackExecutor = executor;
            this.mWrapperCallback = playerCallback;
            this.mControllerCallback = new MediaControllerCallback();
            this.mPlayer = null;
            this.mPlayerCallback = null;
            this.mAllCommands = null;
        } else {
            throw new NullPointerException("callback must not be null");
        }
    }

    PlayerWrapper(SessionPlayer sessionPlayer, Executor executor, PlayerCallback playerCallback) {
        if (sessionPlayer == null) {
            throw new NullPointerException("player must not be null");
        } else if (executor == null) {
            throw new NullPointerException("executor must not be null");
        } else if (playerCallback != null) {
            this.mPlayer = sessionPlayer;
            this.mCallbackExecutor = executor;
            this.mWrapperCallback = playerCallback;
            this.mPlayerCallback = new SessionPlayerCallback();
            this.mController = null;
            this.mControllerCallback = null;
            this.mAllCommands = new SessionCommandGroup.Builder().addAllPredefinedCommands(1).build();
        } else {
            throw new NullPointerException("callback must not be null");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasDisconnectedController() {
        MediaController mediaController = this.mController;
        return mediaController != null && !mediaController.isConnected();
    }

    /* access modifiers changed from: package-private */
    public void attachCallback() {
        if (!this.mCallbackAttached) {
            MediaController mediaController = this.mController;
            if (mediaController != null) {
                mediaController.registerExtraCallback(this.mCallbackExecutor, this.mControllerCallback);
            } else {
                SessionPlayer sessionPlayer = this.mPlayer;
                if (sessionPlayer != null) {
                    sessionPlayer.registerPlayerCallback(this.mCallbackExecutor, this.mPlayerCallback);
                }
            }
            updateAndNotifyCachedStates();
            this.mCallbackAttached = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void detachCallback() {
        if (this.mCallbackAttached) {
            MediaController mediaController = this.mController;
            if (mediaController != null) {
                mediaController.unregisterExtraCallback(this.mControllerCallback);
            } else {
                SessionPlayer sessionPlayer = this.mPlayer;
                if (sessionPlayer != null) {
                    sessionPlayer.unregisterPlayerCallback(this.mPlayerCallback);
                }
            }
            this.mCallbackAttached = false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isPlaying() {
        return this.mSavedPlayerState == 2;
    }

    /* access modifiers changed from: package-private */
    public long getCurrentPosition() {
        long j;
        if (this.mSavedPlayerState == 0) {
            return 0;
        }
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            j = mediaController.getCurrentPosition();
        } else {
            SessionPlayer sessionPlayer = this.mPlayer;
            j = sessionPlayer != null ? sessionPlayer.getCurrentPosition() : 0;
        }
        if (j < 0) {
            return 0;
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public long getBufferPercentage() {
        long j;
        if (this.mSavedPlayerState == 0) {
            return 0;
        }
        long durationMs = getDurationMs();
        if (durationMs == 0) {
            return 0;
        }
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            j = mediaController.getBufferedPosition();
        } else {
            SessionPlayer sessionPlayer = this.mPlayer;
            j = sessionPlayer != null ? sessionPlayer.getBufferedPosition() : 0;
        }
        if (j < 0) {
            return 0;
        }
        return (j * 100) / durationMs;
    }

    /* access modifiers changed from: package-private */
    public int getPlayerState() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            return mediaController.getPlayerState();
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            return sessionPlayer.getPlayerState();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean canPause() {
        SessionCommandGroup sessionCommandGroup = this.mAllowedCommands;
        return sessionCommandGroup != null && sessionCommandGroup.hasCommand(10001);
    }

    /* access modifiers changed from: package-private */
    public boolean canSeekBackward() {
        SessionCommandGroup sessionCommandGroup = this.mAllowedCommands;
        return sessionCommandGroup != null && sessionCommandGroup.hasCommand(40001);
    }

    /* access modifiers changed from: package-private */
    public boolean canSeekForward() {
        SessionCommandGroup sessionCommandGroup = this.mAllowedCommands;
        return sessionCommandGroup != null && sessionCommandGroup.hasCommand(40000);
    }

    /* access modifiers changed from: package-private */
    public boolean canSkipToNext() {
        SessionCommandGroup sessionCommandGroup = this.mAllowedCommands;
        return sessionCommandGroup != null && sessionCommandGroup.hasCommand(10009);
    }

    /* access modifiers changed from: package-private */
    public boolean canSkipToPrevious() {
        SessionCommandGroup sessionCommandGroup = this.mAllowedCommands;
        return sessionCommandGroup != null && sessionCommandGroup.hasCommand(10008);
    }

    /* access modifiers changed from: package-private */
    public boolean canSeekTo() {
        SessionCommandGroup sessionCommandGroup = this.mAllowedCommands;
        return sessionCommandGroup != null && sessionCommandGroup.hasCommand(10003);
    }

    /* access modifiers changed from: package-private */
    public boolean canSelectDeselectTrack() {
        SessionCommandGroup sessionCommandGroup = this.mAllowedCommands;
        return sessionCommandGroup != null && sessionCommandGroup.hasCommand(11001) && this.mAllowedCommands.hasCommand(11002);
    }

    /* access modifiers changed from: package-private */
    public void pause() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            mediaController.pause();
            return;
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            sessionPlayer.pause();
        }
    }

    /* access modifiers changed from: package-private */
    public void play() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            mediaController.play();
            return;
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            sessionPlayer.play();
        }
    }

    /* access modifiers changed from: package-private */
    public void seekTo(long j) {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            mediaController.seekTo(j);
            return;
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            sessionPlayer.seekTo(j);
        }
    }

    /* access modifiers changed from: package-private */
    public void skipToNextItem() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            mediaController.skipToNextPlaylistItem();
            return;
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            sessionPlayer.skipToNextPlaylistItem();
        }
    }

    /* access modifiers changed from: package-private */
    public void skipToPreviousItem() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            mediaController.skipToPreviousPlaylistItem();
            return;
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            sessionPlayer.skipToPreviousPlaylistItem();
        }
    }

    private float getPlaybackSpeed() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            return mediaController.getPlaybackSpeed();
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            return sessionPlayer.getPlaybackSpeed();
        }
        return 1.0f;
    }

    /* access modifiers changed from: package-private */
    public void setPlaybackSpeed(float f) {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            mediaController.setPlaybackSpeed(f);
            return;
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            sessionPlayer.setPlaybackSpeed(f);
        }
    }

    /* access modifiers changed from: package-private */
    public void selectTrack(SessionPlayer.TrackInfo trackInfo) {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            mediaController.selectTrack(trackInfo);
            return;
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            sessionPlayer.selectTrackInternal(trackInfo);
        }
    }

    /* access modifiers changed from: package-private */
    public void deselectTrack(SessionPlayer.TrackInfo trackInfo) {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            mediaController.deselectTrack(trackInfo);
            return;
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            sessionPlayer.deselectTrackInternal(trackInfo);
        }
    }

    /* access modifiers changed from: package-private */
    public long getDurationMs() {
        long j;
        if (this.mSavedPlayerState == 0) {
            return 0;
        }
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            j = mediaController.getDuration();
        } else {
            SessionPlayer sessionPlayer = this.mPlayer;
            j = sessionPlayer != null ? sessionPlayer.getDuration() : 0;
        }
        if (j < 0) {
            return 0;
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getTitle() {
        MediaMetadata mediaMetadata = this.mMediaMetadata;
        if (mediaMetadata == null || !mediaMetadata.containsKey("android.media.metadata.TITLE")) {
            return null;
        }
        return this.mMediaMetadata.getText("android.media.metadata.TITLE");
    }

    /* access modifiers changed from: package-private */
    public CharSequence getArtistText() {
        MediaMetadata mediaMetadata = this.mMediaMetadata;
        if (mediaMetadata == null || !mediaMetadata.containsKey("android.media.metadata.ARTIST")) {
            return null;
        }
        return this.mMediaMetadata.getText("android.media.metadata.ARTIST");
    }

    /* access modifiers changed from: package-private */
    public MediaItem getCurrentMediaItem() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            return mediaController.getCurrentMediaItem();
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            return sessionPlayer.getCurrentMediaItem();
        }
        return null;
    }

    private SessionCommandGroup getAllowedCommands() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            return mediaController.getAllowedCommands();
        }
        if (this.mPlayer != null) {
            return this.mAllCommands;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void updateAndNotifyCachedStates() {
        boolean z;
        MediaMetadata mediaMetadata;
        int playerState = getPlayerState();
        boolean z2 = true;
        if (this.mSavedPlayerState != playerState) {
            this.mSavedPlayerState = playerState;
            z = true;
        } else {
            z = false;
        }
        SessionCommandGroup allowedCommands = getAllowedCommands();
        if (this.mAllowedCommands != allowedCommands) {
            this.mAllowedCommands = allowedCommands;
        } else {
            z2 = false;
        }
        MediaItem currentMediaItem = getCurrentMediaItem();
        if (currentMediaItem == null) {
            mediaMetadata = null;
        } else {
            mediaMetadata = currentMediaItem.getMetadata();
        }
        this.mMediaMetadata = mediaMetadata;
        if (z) {
            this.mWrapperCallback.onPlayerStateChanged(this, playerState);
        }
        if (allowedCommands != null && z2) {
            this.mWrapperCallback.onAllowedCommandsChanged(this, allowedCommands);
        }
        this.mWrapperCallback.onCurrentMediaItemChanged(this, currentMediaItem);
        notifyNonCachedStates();
    }

    /* access modifiers changed from: package-private */
    public VideoSize getVideoSize() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            return mediaController.getVideoSize();
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            return sessionPlayer.getVideoSizeInternal();
        }
        return new VideoSize(0, 0);
    }

    /* access modifiers changed from: package-private */
    public List<SessionPlayer.TrackInfo> getTrackInfo() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            return mediaController.getTrackInfo();
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            return sessionPlayer.getTrackInfoInternal();
        }
        return Collections.emptyList();
    }

    /* access modifiers changed from: package-private */
    public SessionPlayer.TrackInfo getSelectedTrack(int i) {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            return mediaController.getSelectedTrack(i);
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            return sessionPlayer.getSelectedTrackInternal(i);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<? extends BaseResult> setSurface(Surface surface) {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            return mediaController.setSurface(surface);
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            return sessionPlayer.setSurfaceInternal(surface);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getPreviousMediaItemIndex() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            return mediaController.getPreviousMediaItemIndex();
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            return sessionPlayer.getPreviousMediaItemIndex();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getNextMediaItemIndex() {
        MediaController mediaController = this.mController;
        if (mediaController != null) {
            return mediaController.getNextMediaItemIndex();
        }
        SessionPlayer sessionPlayer = this.mPlayer;
        if (sessionPlayer != null) {
            return sessionPlayer.getNextMediaItemIndex();
        }
        return -1;
    }

    private class MediaControllerCallback extends MediaController.ControllerCallback {
        MediaControllerCallback() {
        }
    }

    private void notifyNonCachedStates() {
        this.mWrapperCallback.onPlaybackSpeedChanged(this, getPlaybackSpeed());
        List<SessionPlayer.TrackInfo> trackInfo = getTrackInfo();
        if (trackInfo != null) {
            this.mWrapperCallback.onTrackInfoChanged(this, trackInfo);
        }
        MediaItem currentMediaItem = getCurrentMediaItem();
        if (currentMediaItem != null) {
            this.mWrapperCallback.onVideoSizeChanged(this, currentMediaItem, getVideoSize());
        }
    }

    private class SessionPlayerCallback extends SessionPlayer.PlayerCallback {
        SessionPlayerCallback() {
        }

        public void onPlayerStateChanged(SessionPlayer sessionPlayer, int i) {
            if (PlayerWrapper.this.mSavedPlayerState != i) {
                PlayerWrapper.this.mSavedPlayerState = i;
                PlayerWrapper.this.mWrapperCallback.onPlayerStateChanged(PlayerWrapper.this, i);
            }
        }

        public void onPlaybackSpeedChanged(SessionPlayer sessionPlayer, float f) {
            PlayerWrapper.this.mWrapperCallback.onPlaybackSpeedChanged(PlayerWrapper.this, f);
        }

        public void onSeekCompleted(SessionPlayer sessionPlayer, long j) {
            PlayerWrapper.this.mWrapperCallback.onSeekCompleted(PlayerWrapper.this, j);
        }

        public void onCurrentMediaItemChanged(SessionPlayer sessionPlayer, MediaItem mediaItem) {
            PlayerWrapper.this.mMediaMetadata = mediaItem == null ? null : mediaItem.getMetadata();
            PlayerWrapper.this.mWrapperCallback.onCurrentMediaItemChanged(PlayerWrapper.this, mediaItem);
        }

        public void onPlaylistChanged(SessionPlayer sessionPlayer, List<MediaItem> list, MediaMetadata mediaMetadata) {
            PlayerWrapper.this.mWrapperCallback.onPlaylistChanged(PlayerWrapper.this, list, mediaMetadata);
        }

        public void onPlaybackCompleted(SessionPlayer sessionPlayer) {
            PlayerWrapper.this.mWrapperCallback.onPlaybackCompleted(PlayerWrapper.this);
        }

        public void onVideoSizeChangedInternal(SessionPlayer sessionPlayer, MediaItem mediaItem, VideoSize videoSize) {
            PlayerWrapper.this.mWrapperCallback.onVideoSizeChanged(PlayerWrapper.this, mediaItem, videoSize);
        }

        public void onSubtitleData(SessionPlayer sessionPlayer, MediaItem mediaItem, SessionPlayer.TrackInfo trackInfo, SubtitleData subtitleData) {
            PlayerWrapper.this.mWrapperCallback.onSubtitleData(PlayerWrapper.this, mediaItem, trackInfo, subtitleData);
        }

        public void onTrackInfoChanged(SessionPlayer sessionPlayer, List<SessionPlayer.TrackInfo> list) {
            PlayerWrapper.this.mWrapperCallback.onTrackInfoChanged(PlayerWrapper.this, list);
        }

        public void onTrackSelected(SessionPlayer sessionPlayer, SessionPlayer.TrackInfo trackInfo) {
            PlayerWrapper.this.mWrapperCallback.onTrackSelected(PlayerWrapper.this, trackInfo);
        }

        public void onTrackDeselected(SessionPlayer sessionPlayer, SessionPlayer.TrackInfo trackInfo) {
            PlayerWrapper.this.mWrapperCallback.onTrackDeselected(PlayerWrapper.this, trackInfo);
        }
    }

    static abstract class PlayerCallback {
        /* access modifiers changed from: package-private */
        public void onAllowedCommandsChanged(PlayerWrapper playerWrapper, SessionCommandGroup sessionCommandGroup) {
        }

        /* access modifiers changed from: package-private */
        public void onCurrentMediaItemChanged(PlayerWrapper playerWrapper, MediaItem mediaItem) {
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackCompleted(PlayerWrapper playerWrapper) {
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackSpeedChanged(PlayerWrapper playerWrapper, float f) {
        }

        /* access modifiers changed from: package-private */
        public void onPlayerStateChanged(PlayerWrapper playerWrapper, int i) {
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistChanged(PlayerWrapper playerWrapper, List<MediaItem> list, MediaMetadata mediaMetadata) {
        }

        /* access modifiers changed from: package-private */
        public void onSeekCompleted(PlayerWrapper playerWrapper, long j) {
        }

        /* access modifiers changed from: package-private */
        public void onSubtitleData(PlayerWrapper playerWrapper, MediaItem mediaItem, SessionPlayer.TrackInfo trackInfo, SubtitleData subtitleData) {
        }

        /* access modifiers changed from: package-private */
        public void onTrackDeselected(PlayerWrapper playerWrapper, SessionPlayer.TrackInfo trackInfo) {
        }

        /* access modifiers changed from: package-private */
        public void onTrackInfoChanged(PlayerWrapper playerWrapper, List<SessionPlayer.TrackInfo> list) {
        }

        /* access modifiers changed from: package-private */
        public void onTrackSelected(PlayerWrapper playerWrapper, SessionPlayer.TrackInfo trackInfo) {
        }

        /* access modifiers changed from: package-private */
        public void onVideoSizeChanged(PlayerWrapper playerWrapper, MediaItem mediaItem, VideoSize videoSize) {
        }

        PlayerCallback() {
        }
    }
}
