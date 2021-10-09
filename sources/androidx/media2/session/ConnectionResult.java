package androidx.media2.session;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import androidx.media2.common.MediaItem;
import androidx.media2.common.ParcelImplListSlice;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.VideoSize;
import androidx.media2.session.IMediaSession;
import androidx.media2.session.MediaController;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.util.List;

class ConnectionResult extends CustomVersionedParcelable {
    SessionCommandGroup mAllowedCommands;
    long mBufferedPositionMs;
    MediaItem mCurrentMediaItem;
    int mCurrentMediaItemIndex;
    int mNextMediaItemIndex;
    MediaItem mParcelableCurrentMediaItem;
    MediaController.PlaybackInfo mPlaybackInfo;
    float mPlaybackSpeed;
    int mPlayerState;
    ParcelImplListSlice mPlaylistSlice;
    long mPositionEventTimeMs;
    long mPositionMs;
    int mPreviousMediaItemIndex;
    int mRepeatMode;
    SessionPlayer.TrackInfo mSelectedAudioTrack;
    SessionPlayer.TrackInfo mSelectedMetadataTrack;
    SessionPlayer.TrackInfo mSelectedSubtitleTrack;
    SessionPlayer.TrackInfo mSelectedVideoTrack;
    PendingIntent mSessionActivity;
    IBinder mSessionBinder;
    IMediaSession mSessionStub;
    int mShuffleMode;
    Bundle mTokenExtras;
    List<SessionPlayer.TrackInfo> mTrackInfos;
    int mVersion;
    VideoSize mVideoSize;

    ConnectionResult() {
    }

    public void onPreParceling(boolean z) {
        this.mSessionBinder = (IBinder) this.mSessionStub;
        this.mParcelableCurrentMediaItem = MediaUtils.upcastForPreparceling(this.mCurrentMediaItem);
    }

    public void onPostParceling() {
        this.mSessionStub = IMediaSession.Stub.asInterface(this.mSessionBinder);
        this.mSessionBinder = null;
        this.mCurrentMediaItem = this.mParcelableCurrentMediaItem;
        this.mParcelableCurrentMediaItem = null;
    }
}
