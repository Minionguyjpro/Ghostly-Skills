package androidx.media2.session;

import android.util.Log;
import android.view.Surface;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pair;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.VideoSize;
import androidx.versionedparcelable.VersionedParcelable;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public class MediaController implements AutoCloseable {
    boolean mClosed;
    private final List<Pair<ControllerCallback, Executor>> mExtraCallbacks;
    MediaControllerImpl mImpl;
    final Object mLock;

    public static abstract class ControllerCallback {
    }

    interface MediaControllerImpl extends AutoCloseable {
        ListenableFuture<SessionResult> deselectTrack(SessionPlayer.TrackInfo trackInfo);

        SessionCommandGroup getAllowedCommands();

        long getBufferedPosition();

        MediaItem getCurrentMediaItem();

        long getCurrentPosition();

        long getDuration();

        int getNextMediaItemIndex();

        float getPlaybackSpeed();

        int getPlayerState();

        int getPreviousMediaItemIndex();

        SessionPlayer.TrackInfo getSelectedTrack(int i);

        List<SessionPlayer.TrackInfo> getTrackInfo();

        VideoSize getVideoSize();

        boolean isConnected();

        ListenableFuture<SessionResult> pause();

        ListenableFuture<SessionResult> play();

        ListenableFuture<SessionResult> seekTo(long j);

        ListenableFuture<SessionResult> selectTrack(SessionPlayer.TrackInfo trackInfo);

        ListenableFuture<SessionResult> setPlaybackSpeed(float f);

        ListenableFuture<SessionResult> setSurface(Surface surface);

        ListenableFuture<SessionResult> skipToNextItem();

        ListenableFuture<SessionResult> skipToPreviousItem();
    }

    /* access modifiers changed from: package-private */
    public MediaControllerImpl getImpl() {
        MediaControllerImpl mediaControllerImpl;
        synchronized (this.mLock) {
            mediaControllerImpl = this.mImpl;
        }
        return mediaControllerImpl;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mLock     // Catch:{ Exception -> 0x0018 }
            monitor-enter(r0)     // Catch:{ Exception -> 0x0018 }
            boolean r1 = r2.mClosed     // Catch:{ all -> 0x0015 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return
        L_0x0009:
            r1 = 1
            r2.mClosed = r1     // Catch:{ all -> 0x0015 }
            androidx.media2.session.MediaController$MediaControllerImpl r1 = r2.mImpl     // Catch:{ all -> 0x0015 }
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            if (r1 == 0) goto L_0x0018
            r1.close()     // Catch:{ Exception -> 0x0018 }
            goto L_0x0018
        L_0x0015:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            throw r1     // Catch:{ Exception -> 0x0018 }
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaController.close():void");
    }

    public boolean isConnected() {
        MediaControllerImpl impl = getImpl();
        return impl != null && impl.isConnected();
    }

    public ListenableFuture<SessionResult> play() {
        if (isConnected()) {
            return getImpl().play();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> pause() {
        if (isConnected()) {
            return getImpl().pause();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> seekTo(long j) {
        if (isConnected()) {
            return getImpl().seekTo(j);
        }
        return createDisconnectedFuture();
    }

    public int getPlayerState() {
        if (isConnected()) {
            return getImpl().getPlayerState();
        }
        return 0;
    }

    public long getDuration() {
        if (isConnected()) {
            return getImpl().getDuration();
        }
        return Long.MIN_VALUE;
    }

    public long getCurrentPosition() {
        if (isConnected()) {
            return getImpl().getCurrentPosition();
        }
        return Long.MIN_VALUE;
    }

    public float getPlaybackSpeed() {
        if (isConnected()) {
            return getImpl().getPlaybackSpeed();
        }
        return 0.0f;
    }

    public ListenableFuture<SessionResult> setPlaybackSpeed(float f) {
        if (f == 0.0f) {
            throw new IllegalArgumentException("speed must not be zero");
        } else if (isConnected()) {
            return getImpl().setPlaybackSpeed(f);
        } else {
            return createDisconnectedFuture();
        }
    }

    public long getBufferedPosition() {
        if (isConnected()) {
            return getImpl().getBufferedPosition();
        }
        return Long.MIN_VALUE;
    }

    public MediaItem getCurrentMediaItem() {
        if (isConnected()) {
            return getImpl().getCurrentMediaItem();
        }
        return null;
    }

    public int getPreviousMediaItemIndex() {
        if (isConnected()) {
            return getImpl().getPreviousMediaItemIndex();
        }
        return -1;
    }

    public int getNextMediaItemIndex() {
        if (isConnected()) {
            return getImpl().getNextMediaItemIndex();
        }
        return -1;
    }

    public ListenableFuture<SessionResult> skipToPreviousPlaylistItem() {
        if (isConnected()) {
            return getImpl().skipToPreviousItem();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> skipToNextPlaylistItem() {
        if (isConnected()) {
            return getImpl().skipToNextItem();
        }
        return createDisconnectedFuture();
    }

    public VideoSize getVideoSize() {
        return isConnected() ? getImpl().getVideoSize() : new VideoSize(0, 0);
    }

    public ListenableFuture<SessionResult> setSurface(Surface surface) {
        if (isConnected()) {
            return getImpl().setSurface(surface);
        }
        return createDisconnectedFuture();
    }

    public List<SessionPlayer.TrackInfo> getTrackInfo() {
        if (isConnected()) {
            return getImpl().getTrackInfo();
        }
        return null;
    }

    public ListenableFuture<SessionResult> selectTrack(SessionPlayer.TrackInfo trackInfo) {
        if (trackInfo != null) {
            return isConnected() ? getImpl().selectTrack(trackInfo) : createDisconnectedFuture();
        }
        throw new NullPointerException("TrackInfo shouldn't be null");
    }

    public ListenableFuture<SessionResult> deselectTrack(SessionPlayer.TrackInfo trackInfo) {
        if (trackInfo != null) {
            return isConnected() ? getImpl().deselectTrack(trackInfo) : createDisconnectedFuture();
        }
        throw new NullPointerException("TrackInfo shouldn't be null");
    }

    public SessionPlayer.TrackInfo getSelectedTrack(int i) {
        if (isConnected()) {
            return getImpl().getSelectedTrack(i);
        }
        return null;
    }

    public void registerExtraCallback(Executor executor, ControllerCallback controllerCallback) {
        if (executor == null) {
            throw new NullPointerException("executor shouldn't be null");
        } else if (controllerCallback != null) {
            boolean z = false;
            synchronized (this.mLock) {
                Iterator<Pair<ControllerCallback, Executor>> it = this.mExtraCallbacks.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().first == controllerCallback) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (!z) {
                    this.mExtraCallbacks.add(new Pair(controllerCallback, executor));
                }
            }
            if (z) {
                Log.w("MediaController", "registerExtraCallback: the callback already exists");
            }
        } else {
            throw new NullPointerException("callback shouldn't be null");
        }
    }

    public void unregisterExtraCallback(ControllerCallback controllerCallback) {
        if (controllerCallback != null) {
            boolean z = false;
            synchronized (this.mLock) {
                int size = this.mExtraCallbacks.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    } else if (this.mExtraCallbacks.get(size).first == controllerCallback) {
                        this.mExtraCallbacks.remove(size);
                        z = true;
                        break;
                    } else {
                        size--;
                    }
                }
            }
            if (!z) {
                Log.w("MediaController", "unregisterExtraCallback: no such callback found");
                return;
            }
            return;
        }
        throw new NullPointerException("callback shouldn't be null");
    }

    public SessionCommandGroup getAllowedCommands() {
        if (!isConnected()) {
            return null;
        }
        return getImpl().getAllowedCommands();
    }

    private static ListenableFuture<SessionResult> createDisconnectedFuture() {
        return SessionResult.createFutureWithResult(-100);
    }

    public static final class PlaybackInfo implements VersionedParcelable {
        AudioAttributesCompat mAudioAttrsCompat;
        int mControlType;
        int mCurrentVolume;
        int mMaxVolume;
        int mPlaybackType;

        PlaybackInfo() {
        }

        public int hashCode() {
            return ObjectsCompat.hash(Integer.valueOf(this.mPlaybackType), Integer.valueOf(this.mControlType), Integer.valueOf(this.mMaxVolume), Integer.valueOf(this.mCurrentVolume), this.mAudioAttrsCompat);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PlaybackInfo)) {
                return false;
            }
            PlaybackInfo playbackInfo = (PlaybackInfo) obj;
            if (this.mPlaybackType == playbackInfo.mPlaybackType && this.mControlType == playbackInfo.mControlType && this.mMaxVolume == playbackInfo.mMaxVolume && this.mCurrentVolume == playbackInfo.mCurrentVolume && ObjectsCompat.equals(this.mAudioAttrsCompat, playbackInfo.mAudioAttrsCompat)) {
                return true;
            }
            return false;
        }
    }
}
