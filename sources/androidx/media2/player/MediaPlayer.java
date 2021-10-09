package androidx.media2.player;

import android.content.Context;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import androidx.collection.ArrayMap;
import androidx.core.util.Pair;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.FileMediaItem;
import androidx.media2.common.MediaItem;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.VideoSize;
import androidx.media2.player.MediaPlayer2;
import androidx.media2.player.PlaybackParams;
import androidx.media2.player.futures.AbstractResolvableFuture;
import androidx.media2.player.futures.ResolvableFuture;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class MediaPlayer extends SessionPlayer {
    static final PlaybackParams DEFAULT_PLAYBACK_PARAMS = new PlaybackParams.Builder().setSpeed(1.0f).setPitch(1.0f).setAudioFallbackMode(0).build();
    static ArrayMap<Integer, Integer> sErrorCodeMap;
    static ArrayMap<Integer, Integer> sInfoCodeMap;
    static ArrayMap<Integer, Integer> sPrepareDrmStatusMap;
    static ArrayMap<Integer, Integer> sResultCodeMap;
    static ArrayMap<Integer, Integer> sSeekModeMap;
    final AudioFocusHandler mAudioFocusHandler;
    private boolean mClosed;
    MediaItem mCurPlaylistItem;
    int mCurrentShuffleIdx;
    ExecutorService mExecutor;
    private Map<MediaItem, Integer> mMediaItemToBuffState = new HashMap();
    MediaItem mNextPlaylistItem;
    final ArrayDeque<PendingCommand> mPendingCommands = new ArrayDeque<>();
    final ArrayDeque<PendingFuture<? super SessionPlayer.PlayerResult>> mPendingFutures = new ArrayDeque<>();
    MediaPlayer2 mPlayer;
    MediaItemList mPlaylist = new MediaItemList();
    final Object mPlaylistLock = new Object();
    int mRepeatMode;
    private boolean mSetMediaItemCalled;
    ArrayList<MediaItem> mShuffledList = new ArrayList<>();
    private int mState;
    private final Object mStateLock = new Object();

    public static final class DrmInfo {
    }

    private interface MediaPlayerCallbackNotifier {
        void callCallback(PlayerCallback playerCallback);
    }

    private interface SessionPlayerCallbackNotifier {
        void callCallback(SessionPlayer.PlayerCallback playerCallback);
    }

    static {
        ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
        sResultCodeMap = arrayMap;
        arrayMap.put(0, 0);
        sResultCodeMap.put(Integer.valueOf(RecyclerView.UNDEFINED_DURATION), -1);
        sResultCodeMap.put(1, -2);
        sResultCodeMap.put(2, -3);
        sResultCodeMap.put(3, -4);
        sResultCodeMap.put(4, -5);
        sResultCodeMap.put(5, 1);
        ArrayMap<Integer, Integer> arrayMap2 = new ArrayMap<>();
        sErrorCodeMap = arrayMap2;
        arrayMap2.put(1, 1);
        sErrorCodeMap.put(-1004, -1004);
        sErrorCodeMap.put(-1007, -1007);
        sErrorCodeMap.put(-1010, -1010);
        sErrorCodeMap.put(-110, -110);
        ArrayMap<Integer, Integer> arrayMap3 = new ArrayMap<>();
        sInfoCodeMap = arrayMap3;
        arrayMap3.put(3, 3);
        sInfoCodeMap.put(700, 700);
        sInfoCodeMap.put(704, 704);
        sInfoCodeMap.put(800, 800);
        sInfoCodeMap.put(801, 801);
        sInfoCodeMap.put(802, 802);
        sInfoCodeMap.put(804, 804);
        sInfoCodeMap.put(805, 805);
        ArrayMap<Integer, Integer> arrayMap4 = new ArrayMap<>();
        sSeekModeMap = arrayMap4;
        arrayMap4.put(0, 0);
        sSeekModeMap.put(1, 1);
        sSeekModeMap.put(2, 2);
        sSeekModeMap.put(3, 3);
        ArrayMap<Integer, Integer> arrayMap5 = new ArrayMap<>();
        sPrepareDrmStatusMap = arrayMap5;
        arrayMap5.put(0, 0);
        sPrepareDrmStatusMap.put(1, -1001);
        sPrepareDrmStatusMap.put(2, -1003);
        sPrepareDrmStatusMap.put(3, -1003);
        sPrepareDrmStatusMap.put(4, -1004);
        sPrepareDrmStatusMap.put(5, -1005);
    }

    static final class PendingCommand {
        final int mCallType;
        final ResolvableFuture mFuture;
        final TrackInfo mTrackInfo;

        PendingCommand(int i, ResolvableFuture resolvableFuture) {
            this(i, resolvableFuture, (TrackInfo) null);
        }

        PendingCommand(int i, ResolvableFuture resolvableFuture, TrackInfo trackInfo) {
            this.mCallType = i;
            this.mFuture = resolvableFuture;
            this.mTrackInfo = trackInfo;
        }
    }

    static abstract class PendingFuture<V extends SessionPlayer.PlayerResult> extends AbstractResolvableFuture<V> {
        boolean mExecuteCalled;
        List<ResolvableFuture<V>> mFutures;
        final boolean mIsSeekTo;

        /* access modifiers changed from: package-private */
        public abstract List<ResolvableFuture<V>> onExecute();

        PendingFuture(Executor executor) {
            this(executor, false);
        }

        PendingFuture(Executor executor, boolean z) {
            this.mExecuteCalled = false;
            this.mIsSeekTo = z;
            addListener(new Runnable() {
                public void run() {
                    if (PendingFuture.this.isCancelled() && PendingFuture.this.mExecuteCalled) {
                        PendingFuture.this.cancelFutures();
                    }
                }
            }, executor);
        }

        public boolean set(V v) {
            return super.set(v);
        }

        public boolean setException(Throwable th) {
            return super.setException(th);
        }

        public boolean execute() {
            if (!this.mExecuteCalled && !isCancelled()) {
                this.mExecuteCalled = true;
                this.mFutures = onExecute();
            }
            if (!isCancelled() && !isDone()) {
                setResultIfFinished();
            }
            if (isCancelled() || isDone()) {
                return true;
            }
            return false;
        }

        private void setResultIfFinished() {
            SessionPlayer.PlayerResult playerResult = null;
            int i = 0;
            while (i < this.mFutures.size()) {
                ResolvableFuture resolvableFuture = this.mFutures.get(i);
                if (resolvableFuture.isDone() || resolvableFuture.isCancelled()) {
                    try {
                        playerResult = (SessionPlayer.PlayerResult) resolvableFuture.get();
                        int resultCode = playerResult.getResultCode();
                        if (resultCode == 0 || resultCode == 1) {
                            i++;
                        } else {
                            cancelFutures();
                            set(playerResult);
                            return;
                        }
                    } catch (Exception e) {
                        cancelFutures();
                        setException(e);
                        return;
                    }
                } else {
                    return;
                }
            }
            try {
                set(playerResult);
            } catch (Exception e2) {
                setException(e2);
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelFutures() {
            for (ResolvableFuture next : this.mFutures) {
                if (!next.isCancelled() && !next.isDone()) {
                    next.cancel(true);
                }
            }
        }
    }

    public MediaPlayer(Context context) {
        if (context != null) {
            this.mState = 0;
            this.mPlayer = MediaPlayer2.create(context);
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
            this.mExecutor = newFixedThreadPool;
            this.mPlayer.setEventCallback(newFixedThreadPool, new Mp2Callback());
            this.mPlayer.setDrmEventCallback(this.mExecutor, new Mp2DrmCallback());
            this.mCurrentShuffleIdx = -2;
            this.mAudioFocusHandler = new AudioFocusHandler(context, this);
            return;
        }
        throw new NullPointerException("context shouldn't be null");
    }

    /* access modifiers changed from: package-private */
    public void addPendingCommandLocked(int i, ResolvableFuture resolvableFuture, Object obj) {
        PendingCommand pendingCommand = new PendingCommand(i, resolvableFuture);
        this.mPendingCommands.add(pendingCommand);
        addFutureListener(pendingCommand, resolvableFuture, obj);
    }

    /* access modifiers changed from: package-private */
    public void addPendingCommandWithTrackInfoLocked(int i, ResolvableFuture resolvableFuture, TrackInfo trackInfo, Object obj) {
        PendingCommand pendingCommand = new PendingCommand(i, resolvableFuture, trackInfo);
        this.mPendingCommands.add(pendingCommand);
        addFutureListener(pendingCommand, resolvableFuture, obj);
    }

    /* access modifiers changed from: package-private */
    public void addFutureListener(final PendingCommand pendingCommand, final ResolvableFuture resolvableFuture, final Object obj) {
        resolvableFuture.addListener(new Runnable() {
            public void run() {
                if (resolvableFuture.isCancelled()) {
                    synchronized (MediaPlayer.this.mPendingCommands) {
                        if (MediaPlayer.this.mPlayer.cancel(obj)) {
                            MediaPlayer.this.mPendingCommands.remove(pendingCommand);
                        }
                    }
                }
            }
        }, this.mExecutor);
    }

    /* access modifiers changed from: package-private */
    public void addPendingFuture(PendingFuture pendingFuture) {
        synchronized (this.mPendingFutures) {
            this.mPendingFutures.add(pendingFuture);
            executePendingFutures();
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> play() {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                return createFutureForClosed;
            }
            AnonymousClass2 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                /* access modifiers changed from: package-private */
                public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                    ResolvableFuture<SessionPlayer.PlayerResult> resolvableFuture;
                    ArrayList arrayList = new ArrayList();
                    if (MediaPlayer.this.mAudioFocusHandler.onPlay()) {
                        if (MediaPlayer.this.mPlayer.getAudioAttributes() == null) {
                            arrayList.add(MediaPlayer.this.setPlayerVolumeInternal(0.0f));
                        }
                        resolvableFuture = ResolvableFuture.create();
                        synchronized (MediaPlayer.this.mPendingCommands) {
                            MediaPlayer.this.addPendingCommandLocked(5, resolvableFuture, MediaPlayer.this.mPlayer.play());
                        }
                    } else {
                        resolvableFuture = MediaPlayer.this.createFutureForResultCode(-1);
                    }
                    arrayList.add(resolvableFuture);
                    return arrayList;
                }
            };
            addPendingFuture(r0);
            return r0;
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> pause() {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                return createFutureForClosed;
            }
            AnonymousClass3 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                /* access modifiers changed from: package-private */
                public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                    ArrayList arrayList = new ArrayList();
                    ResolvableFuture create = ResolvableFuture.create();
                    MediaPlayer.this.mAudioFocusHandler.onPause();
                    synchronized (MediaPlayer.this.mPendingCommands) {
                        MediaPlayer.this.addPendingCommandLocked(4, create, MediaPlayer.this.mPlayer.pause());
                    }
                    arrayList.add(create);
                    return arrayList;
                }
            };
            addPendingFuture(r0);
            return r0;
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> prepare() {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                return createFutureForClosed;
            }
            AnonymousClass4 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                /* access modifiers changed from: package-private */
                public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                    ArrayList arrayList = new ArrayList();
                    ResolvableFuture create = ResolvableFuture.create();
                    synchronized (MediaPlayer.this.mPendingCommands) {
                        MediaPlayer.this.addPendingCommandLocked(6, create, MediaPlayer.this.mPlayer.prepare());
                    }
                    MediaPlayer mediaPlayer = MediaPlayer.this;
                    mediaPlayer.setBufferingState(mediaPlayer.mPlayer.getCurrentMediaItem(), 2);
                    arrayList.add(create);
                    return arrayList;
                }
            };
            addPendingFuture(r0);
            return r0;
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> seekTo(long j) {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                return createFutureForClosed;
            }
            final long j2 = j;
            AnonymousClass5 r1 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor, true) {
                /* access modifiers changed from: package-private */
                public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                    ArrayList arrayList = new ArrayList();
                    ResolvableFuture create = ResolvableFuture.create();
                    synchronized (MediaPlayer.this.mPendingCommands) {
                        MediaPlayer.this.addPendingCommandLocked(14, create, MediaPlayer.this.mPlayer.seekTo(j2));
                    }
                    arrayList.add(create);
                    return arrayList;
                }
            };
            addPendingFuture(r1);
            return r1;
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setPlaybackSpeed(final float f) {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                return createFutureForClosed;
            }
            AnonymousClass6 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                /* access modifiers changed from: package-private */
                public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                    if (f <= 0.0f) {
                        return MediaPlayer.this.createFuturesForResultCode(-3);
                    }
                    ArrayList arrayList = new ArrayList();
                    ResolvableFuture create = ResolvableFuture.create();
                    synchronized (MediaPlayer.this.mPendingCommands) {
                        MediaPlayer.this.addPendingCommandLocked(24, create, MediaPlayer.this.mPlayer.setPlaybackParams(new PlaybackParams.Builder(MediaPlayer.this.mPlayer.getPlaybackParams()).setSpeed(f).build()));
                    }
                    arrayList.add(create);
                    return arrayList;
                }
            };
            addPendingFuture(r0);
            return r0;
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setAudioAttributes(final AudioAttributesCompat audioAttributesCompat) {
        if (audioAttributesCompat != null) {
            synchronized (this.mStateLock) {
                if (this.mClosed) {
                    ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                    return createFutureForClosed;
                }
                AnonymousClass7 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                    /* access modifiers changed from: package-private */
                    public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                        ArrayList arrayList = new ArrayList();
                        ResolvableFuture create = ResolvableFuture.create();
                        synchronized (MediaPlayer.this.mPendingCommands) {
                            MediaPlayer.this.addPendingCommandLocked(16, create, MediaPlayer.this.mPlayer.setAudioAttributes(audioAttributesCompat));
                        }
                        arrayList.add(create);
                        return arrayList;
                    }
                };
                addPendingFuture(r0);
                return r0;
            }
        }
        throw new NullPointerException("attr shouldn't be null");
    }

    public int getPlayerState() {
        int i;
        synchronized (this.mStateLock) {
            i = this.mState;
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        if (r0 < 0) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r0 = r7.mPlayer.getCurrentPosition();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getCurrentPosition() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mStateLock
            monitor-enter(r0)
            boolean r1 = r7.mClosed     // Catch:{ all -> 0x001a }
            r2 = -9223372036854775808
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x000b:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            androidx.media2.player.MediaPlayer2 r0 = r7.mPlayer     // Catch:{ IllegalStateException -> 0x0019 }
            long r0 = r0.getCurrentPosition()     // Catch:{ IllegalStateException -> 0x0019 }
            r4 = 0
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x0019
            return r0
        L_0x0019:
            return r2
        L_0x001a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.MediaPlayer.getCurrentPosition():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        if (r0 < 0) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r0 = r7.mPlayer.getDuration();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getDuration() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mStateLock
            monitor-enter(r0)
            boolean r1 = r7.mClosed     // Catch:{ all -> 0x001a }
            r2 = -9223372036854775808
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x000b:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            androidx.media2.player.MediaPlayer2 r0 = r7.mPlayer     // Catch:{ IllegalStateException -> 0x0019 }
            long r0 = r0.getDuration()     // Catch:{ IllegalStateException -> 0x0019 }
            r4 = 0
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x0019
            return r0
        L_0x0019:
            return r2
        L_0x001a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.MediaPlayer.getDuration():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        if (r0 < 0) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r0 = r7.mPlayer.getBufferedPosition();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getBufferedPosition() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mStateLock
            monitor-enter(r0)
            boolean r1 = r7.mClosed     // Catch:{ all -> 0x001a }
            r2 = -9223372036854775808
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return r2
        L_0x000b:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            androidx.media2.player.MediaPlayer2 r0 = r7.mPlayer     // Catch:{ IllegalStateException -> 0x0019 }
            long r0 = r0.getBufferedPosition()     // Catch:{ IllegalStateException -> 0x0019 }
            r4 = 0
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x0019
            return r0
        L_0x0019:
            return r2
        L_0x001a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.MediaPlayer.getBufferedPosition():long");
    }

    public float getPlaybackSpeed() {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                return 1.0f;
            }
            try {
                return this.mPlayer.getPlaybackParams().getSpeed().floatValue();
            } catch (IllegalStateException unused) {
                return 1.0f;
            }
        }
    }

    public AudioAttributesCompat getAudioAttributes() {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                return null;
            }
            try {
                return this.mPlayer.getAudioAttributes();
            } catch (IllegalStateException unused) {
                return null;
            }
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setMediaItem(final MediaItem mediaItem) {
        if (mediaItem == null) {
            throw new NullPointerException("item shouldn't be null");
        } else if (!(mediaItem instanceof FileMediaItem) || !((FileMediaItem) mediaItem).isClosed()) {
            synchronized (this.mStateLock) {
                if (this.mClosed) {
                    ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                    return createFutureForClosed;
                }
                AnonymousClass8 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                    /* access modifiers changed from: package-private */
                    public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                        ArrayList arrayList = new ArrayList();
                        synchronized (MediaPlayer.this.mPlaylistLock) {
                            MediaPlayer.this.mPlaylist.clear();
                            MediaPlayer.this.mShuffledList.clear();
                            MediaPlayer.this.mCurPlaylistItem = mediaItem;
                            MediaPlayer.this.mNextPlaylistItem = null;
                            MediaPlayer.this.mCurrentShuffleIdx = -1;
                        }
                        arrayList.addAll(MediaPlayer.this.setMediaItemsInternal(mediaItem, (MediaItem) null));
                        return arrayList;
                    }
                };
                addPendingFuture(r0);
                return r0;
            }
        } else {
            throw new IllegalArgumentException("File descriptor is closed. " + mediaItem);
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> skipToPreviousPlaylistItem() {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                return createFutureForClosed;
            }
            AnonymousClass13 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                /* access modifiers changed from: package-private */
                public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                    synchronized (MediaPlayer.this.mPlaylistLock) {
                        if (MediaPlayer.this.mCurrentShuffleIdx < 0) {
                            List<ResolvableFuture<SessionPlayer.PlayerResult>> createFuturesForResultCode = MediaPlayer.this.createFuturesForResultCode(-2);
                            return createFuturesForResultCode;
                        }
                        int i = MediaPlayer.this.mCurrentShuffleIdx - 1;
                        if (i < 0) {
                            if (MediaPlayer.this.mRepeatMode != 2) {
                                if (MediaPlayer.this.mRepeatMode != 3) {
                                    List<ResolvableFuture<SessionPlayer.PlayerResult>> createFuturesForResultCode2 = MediaPlayer.this.createFuturesForResultCode(-2);
                                    return createFuturesForResultCode2;
                                }
                            }
                            i = MediaPlayer.this.mShuffledList.size() - 1;
                        }
                        MediaPlayer.this.mCurrentShuffleIdx = i;
                        MediaPlayer.this.updateAndGetCurrentNextItemIfNeededLocked();
                        MediaItem mediaItem = MediaPlayer.this.mCurPlaylistItem;
                        MediaItem mediaItem2 = MediaPlayer.this.mNextPlaylistItem;
                        return MediaPlayer.this.setMediaItemsInternal(mediaItem, mediaItem2);
                    }
                }
            };
            addPendingFuture(r0);
            return r0;
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> skipToNextPlaylistItem() {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                return createFutureForClosed;
            }
            AnonymousClass14 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                /* access modifiers changed from: package-private */
                /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
                    if (r1 == null) goto L_0x0057;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
                    return r4.this$0.setMediaItemsInternal(r1, r2);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:24:0x0057, code lost:
                    r0 = new java.util.ArrayList();
                    r0.add(r4.this$0.skipToNextInternal());
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:25:0x0065, code lost:
                    return r0;
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.util.List<androidx.media2.player.futures.ResolvableFuture<androidx.media2.common.SessionPlayer.PlayerResult>> onExecute() {
                    /*
                        r4 = this;
                        androidx.media2.player.MediaPlayer r0 = androidx.media2.player.MediaPlayer.this
                        java.lang.Object r0 = r0.mPlaylistLock
                        monitor-enter(r0)
                        androidx.media2.player.MediaPlayer r1 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        int r1 = r1.mCurrentShuffleIdx     // Catch:{ all -> 0x0066 }
                        r2 = -2
                        if (r1 >= 0) goto L_0x0014
                        androidx.media2.player.MediaPlayer r1 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        java.util.List r1 = r1.createFuturesForResultCode(r2)     // Catch:{ all -> 0x0066 }
                        monitor-exit(r0)     // Catch:{ all -> 0x0066 }
                        return r1
                    L_0x0014:
                        androidx.media2.player.MediaPlayer r1 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        int r1 = r1.mCurrentShuffleIdx     // Catch:{ all -> 0x0066 }
                        int r1 = r1 + 1
                        androidx.media2.player.MediaPlayer r3 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        java.util.ArrayList<androidx.media2.common.MediaItem> r3 = r3.mShuffledList     // Catch:{ all -> 0x0066 }
                        int r3 = r3.size()     // Catch:{ all -> 0x0066 }
                        if (r1 < r3) goto L_0x003c
                        androidx.media2.player.MediaPlayer r1 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        int r1 = r1.mRepeatMode     // Catch:{ all -> 0x0066 }
                        r3 = 2
                        if (r1 == r3) goto L_0x003b
                        androidx.media2.player.MediaPlayer r1 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        int r1 = r1.mRepeatMode     // Catch:{ all -> 0x0066 }
                        r3 = 3
                        if (r1 != r3) goto L_0x0033
                        goto L_0x003b
                    L_0x0033:
                        androidx.media2.player.MediaPlayer r1 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        java.util.List r1 = r1.createFuturesForResultCode(r2)     // Catch:{ all -> 0x0066 }
                        monitor-exit(r0)     // Catch:{ all -> 0x0066 }
                        return r1
                    L_0x003b:
                        r1 = 0
                    L_0x003c:
                        androidx.media2.player.MediaPlayer r2 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        r2.mCurrentShuffleIdx = r1     // Catch:{ all -> 0x0066 }
                        androidx.media2.player.MediaPlayer r1 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        r1.updateAndGetCurrentNextItemIfNeededLocked()     // Catch:{ all -> 0x0066 }
                        androidx.media2.player.MediaPlayer r1 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        androidx.media2.common.MediaItem r1 = r1.mCurPlaylistItem     // Catch:{ all -> 0x0066 }
                        androidx.media2.player.MediaPlayer r2 = androidx.media2.player.MediaPlayer.this     // Catch:{ all -> 0x0066 }
                        androidx.media2.common.MediaItem r2 = r2.mNextPlaylistItem     // Catch:{ all -> 0x0066 }
                        monitor-exit(r0)     // Catch:{ all -> 0x0066 }
                        if (r1 == 0) goto L_0x0057
                        androidx.media2.player.MediaPlayer r0 = androidx.media2.player.MediaPlayer.this
                        java.util.List r0 = r0.setMediaItemsInternal(r1, r2)
                        return r0
                    L_0x0057:
                        java.util.ArrayList r0 = new java.util.ArrayList
                        r0.<init>()
                        androidx.media2.player.MediaPlayer r1 = androidx.media2.player.MediaPlayer.this
                        androidx.media2.player.futures.ResolvableFuture r1 = r1.skipToNextInternal()
                        r0.add(r1)
                        return r0
                    L_0x0066:
                        r1 = move-exception
                        monitor-exit(r0)     // Catch:{ all -> 0x0066 }
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.MediaPlayer.AnonymousClass14.onExecute():java.util.List");
                }
            };
            addPendingFuture(r0);
            return r0;
        }
    }

    public MediaItem getCurrentMediaItem() {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                return null;
            }
            return this.mPlayer.getCurrentMediaItem();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0010, code lost:
        if (r4.mCurrentShuffleIdx >= 0) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0012, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0013, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0014, code lost:
        r0 = r4.mCurrentShuffleIdx - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0018, code lost:
        if (r0 >= 0) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001d, code lost:
        if (r4.mRepeatMode == 2) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0022, code lost:
        if (r4.mRepeatMode != 3) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0025, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0026, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0027, code lost:
        r0 = r4.mPlaylist.indexOf(r4.mShuffledList.get(r4.mShuffledList.size() - 1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003b, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003c, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003d, code lost:
        r0 = r4.mPlaylist.indexOf(r4.mShuffledList.get(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0049, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004a, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000b, code lost:
        r1 = r4.mPlaylistLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        monitor-enter(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getPreviousMediaItemIndex() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mStateLock
            monitor-enter(r0)
            boolean r1 = r4.mClosed     // Catch:{ all -> 0x004e }
            r2 = -1
            if (r1 == 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return r2
        L_0x000a:
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            java.lang.Object r1 = r4.mPlaylistLock
            monitor-enter(r1)
            int r0 = r4.mCurrentShuffleIdx     // Catch:{ all -> 0x004b }
            if (r0 >= 0) goto L_0x0014
            monitor-exit(r1)     // Catch:{ all -> 0x004b }
            return r2
        L_0x0014:
            int r0 = r4.mCurrentShuffleIdx     // Catch:{ all -> 0x004b }
            int r0 = r0 + -1
            if (r0 >= 0) goto L_0x003d
            int r0 = r4.mRepeatMode     // Catch:{ all -> 0x004b }
            r3 = 2
            if (r0 == r3) goto L_0x0027
            int r0 = r4.mRepeatMode     // Catch:{ all -> 0x004b }
            r3 = 3
            if (r0 != r3) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            monitor-exit(r1)     // Catch:{ all -> 0x004b }
            return r2
        L_0x0027:
            androidx.media2.player.MediaPlayer$MediaItemList r0 = r4.mPlaylist     // Catch:{ all -> 0x004b }
            java.util.ArrayList<androidx.media2.common.MediaItem> r2 = r4.mShuffledList     // Catch:{ all -> 0x004b }
            java.util.ArrayList<androidx.media2.common.MediaItem> r3 = r4.mShuffledList     // Catch:{ all -> 0x004b }
            int r3 = r3.size()     // Catch:{ all -> 0x004b }
            int r3 = r3 + -1
            java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x004b }
            int r0 = r0.indexOf(r2)     // Catch:{ all -> 0x004b }
            monitor-exit(r1)     // Catch:{ all -> 0x004b }
            return r0
        L_0x003d:
            androidx.media2.player.MediaPlayer$MediaItemList r2 = r4.mPlaylist     // Catch:{ all -> 0x004b }
            java.util.ArrayList<androidx.media2.common.MediaItem> r3 = r4.mShuffledList     // Catch:{ all -> 0x004b }
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x004b }
            int r0 = r2.indexOf(r0)     // Catch:{ all -> 0x004b }
            monitor-exit(r1)     // Catch:{ all -> 0x004b }
            return r0
        L_0x004b:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x004b }
            throw r0
        L_0x004e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.MediaPlayer.getPreviousMediaItemIndex():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0010, code lost:
        if (r4.mCurrentShuffleIdx >= 0) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0012, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0013, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0014, code lost:
        r0 = r4.mCurrentShuffleIdx + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001e, code lost:
        if (r0 < r4.mShuffledList.size()) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0023, code lost:
        if (r4.mRepeatMode == 2) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0028, code lost:
        if (r4.mRepeatMode != 3) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002b, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002c, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002d, code lost:
        r0 = r4.mPlaylist.indexOf(r4.mShuffledList.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003a, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003b, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003c, code lost:
        r0 = r4.mPlaylist.indexOf(r4.mShuffledList.get(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0048, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0049, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000b, code lost:
        r1 = r4.mPlaylistLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        monitor-enter(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getNextMediaItemIndex() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mStateLock
            monitor-enter(r0)
            boolean r1 = r4.mClosed     // Catch:{ all -> 0x004d }
            r2 = -1
            if (r1 == 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            return r2
        L_0x000a:
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            java.lang.Object r1 = r4.mPlaylistLock
            monitor-enter(r1)
            int r0 = r4.mCurrentShuffleIdx     // Catch:{ all -> 0x004a }
            if (r0 >= 0) goto L_0x0014
            monitor-exit(r1)     // Catch:{ all -> 0x004a }
            return r2
        L_0x0014:
            int r0 = r4.mCurrentShuffleIdx     // Catch:{ all -> 0x004a }
            int r0 = r0 + 1
            java.util.ArrayList<androidx.media2.common.MediaItem> r3 = r4.mShuffledList     // Catch:{ all -> 0x004a }
            int r3 = r3.size()     // Catch:{ all -> 0x004a }
            if (r0 < r3) goto L_0x003c
            int r0 = r4.mRepeatMode     // Catch:{ all -> 0x004a }
            r3 = 2
            if (r0 == r3) goto L_0x002d
            int r0 = r4.mRepeatMode     // Catch:{ all -> 0x004a }
            r3 = 3
            if (r0 != r3) goto L_0x002b
            goto L_0x002d
        L_0x002b:
            monitor-exit(r1)     // Catch:{ all -> 0x004a }
            return r2
        L_0x002d:
            androidx.media2.player.MediaPlayer$MediaItemList r0 = r4.mPlaylist     // Catch:{ all -> 0x004a }
            java.util.ArrayList<androidx.media2.common.MediaItem> r2 = r4.mShuffledList     // Catch:{ all -> 0x004a }
            r3 = 0
            java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x004a }
            int r0 = r0.indexOf(r2)     // Catch:{ all -> 0x004a }
            monitor-exit(r1)     // Catch:{ all -> 0x004a }
            return r0
        L_0x003c:
            androidx.media2.player.MediaPlayer$MediaItemList r2 = r4.mPlaylist     // Catch:{ all -> 0x004a }
            java.util.ArrayList<androidx.media2.common.MediaItem> r3 = r4.mShuffledList     // Catch:{ all -> 0x004a }
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x004a }
            int r0 = r2.indexOf(r0)     // Catch:{ all -> 0x004a }
            monitor-exit(r1)     // Catch:{ all -> 0x004a }
            return r0
        L_0x004a:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x004a }
            throw r0
        L_0x004d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.MediaPlayer.getNextMediaItemIndex():int");
    }

    public void close() throws Exception {
        synchronized (this.mStateLock) {
            if (!this.mClosed) {
                this.mClosed = true;
                reset();
                this.mAudioFocusHandler.close();
                this.mPlayer.close();
                this.mExecutor.shutdown();
            }
        }
    }

    public void reset() {
        synchronized (this.mPendingCommands) {
            Iterator<PendingCommand> it = this.mPendingCommands.iterator();
            while (it.hasNext()) {
                it.next().mFuture.cancel(true);
            }
            this.mPendingCommands.clear();
        }
        synchronized (this.mPendingFutures) {
            Iterator<PendingFuture<? super SessionPlayer.PlayerResult>> it2 = this.mPendingFutures.iterator();
            while (it2.hasNext()) {
                PendingFuture next = it2.next();
                if (next.mExecuteCalled && !next.isDone() && !next.isCancelled()) {
                    next.cancel(true);
                }
            }
            this.mPendingFutures.clear();
        }
        synchronized (this.mStateLock) {
            this.mState = 0;
            this.mMediaItemToBuffState.clear();
        }
        synchronized (this.mPlaylistLock) {
            this.mPlaylist.clear();
            this.mShuffledList.clear();
            this.mCurPlaylistItem = null;
            this.mNextPlaylistItem = null;
            this.mCurrentShuffleIdx = -1;
            this.mSetMediaItemCalled = false;
        }
        this.mAudioFocusHandler.onReset();
        this.mPlayer.reset();
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setSurface(final Surface surface) {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                return createFutureForClosed;
            }
            AnonymousClass19 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                /* access modifiers changed from: package-private */
                public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                    ArrayList arrayList = new ArrayList();
                    ResolvableFuture create = ResolvableFuture.create();
                    synchronized (MediaPlayer.this.mPendingCommands) {
                        MediaPlayer.this.addPendingCommandLocked(27, create, MediaPlayer.this.mPlayer.setSurface(surface));
                    }
                    arrayList.add(create);
                    return arrayList;
                }
            };
            addPendingFuture(r0);
            return r0;
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setSurfaceInternal(Surface surface) {
        return setSurface(surface);
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setPlayerVolume(final float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("volume should be between 0.0 and 1.0");
        }
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                return createFutureForClosed;
            }
            AnonymousClass20 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                /* access modifiers changed from: package-private */
                public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(MediaPlayer.this.setPlayerVolumeInternal(f));
                    return arrayList;
                }
            };
            addPendingFuture(r0);
            return r0;
        }
    }

    public float getPlayerVolume() {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                return 1.0f;
            }
            return this.mPlayer.getPlayerVolume();
        }
    }

    public VideoSize getVideoSizeInternal() {
        synchronized (this.mStateLock) {
            if (!this.mClosed) {
                return new VideoSize(this.mPlayer.getVideoWidth(), this.mPlayer.getVideoHeight());
            }
            VideoSize videoSize = new VideoSize(0, 0);
            return videoSize;
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setPlaybackParams(final PlaybackParams playbackParams) {
        if (playbackParams != null) {
            synchronized (this.mStateLock) {
                if (this.mClosed) {
                    ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                    return createFutureForClosed;
                }
                AnonymousClass21 r0 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                    /* access modifiers changed from: package-private */
                    public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                        ArrayList arrayList = new ArrayList();
                        ResolvableFuture create = ResolvableFuture.create();
                        synchronized (MediaPlayer.this.mPendingCommands) {
                            MediaPlayer.this.addPendingCommandLocked(24, create, MediaPlayer.this.mPlayer.setPlaybackParams(playbackParams));
                        }
                        arrayList.add(create);
                        return arrayList;
                    }
                };
                addPendingFuture(r0);
                return r0;
            }
        }
        throw new NullPointerException("params shouldn't be null");
    }

    public ListenableFuture<SessionPlayer.PlayerResult> seekTo(long j, int i) {
        synchronized (this.mStateLock) {
            if (this.mClosed) {
                ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                return createFutureForClosed;
            }
            final int i2 = i;
            final long j2 = j;
            AnonymousClass22 r1 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor, true) {
                /* access modifiers changed from: package-private */
                public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                    ArrayList arrayList = new ArrayList();
                    ResolvableFuture create = ResolvableFuture.create();
                    int intValue = MediaPlayer.sSeekModeMap.containsKey(Integer.valueOf(i2)) ? MediaPlayer.sSeekModeMap.get(Integer.valueOf(i2)).intValue() : 1;
                    synchronized (MediaPlayer.this.mPendingCommands) {
                        MediaPlayer.this.addPendingCommandLocked(14, create, MediaPlayer.this.mPlayer.seekTo(j2, intValue));
                    }
                    arrayList.add(create);
                    return arrayList;
                }
            };
            addPendingFuture(r1);
            return r1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        if (r3 >= r0.size()) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        r4 = r0.get(r3);
        r2.add(new androidx.media2.player.MediaPlayer.TrackInfo(r3, r1, r4.getTrackType(), r4.getFormat()));
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        r0 = r7.mPlayer.getTrackInfo();
        r1 = r7.mPlayer.getCurrentMediaItem();
        r2 = new java.util.ArrayList();
        r3 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<androidx.media2.player.MediaPlayer.TrackInfo> getTrackInfo() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mStateLock
            monitor-enter(r0)
            boolean r1 = r7.mClosed     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x000d
            java.util.List r1 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0040 }
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return r1
        L_0x000d:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            androidx.media2.player.MediaPlayer2 r0 = r7.mPlayer
            java.util.List r0 = r0.getTrackInfo()
            androidx.media2.player.MediaPlayer2 r1 = r7.mPlayer
            androidx.media2.common.MediaItem r1 = r1.getCurrentMediaItem()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 0
        L_0x0020:
            int r4 = r0.size()
            if (r3 >= r4) goto L_0x003f
            java.lang.Object r4 = r0.get(r3)
            androidx.media2.player.MediaPlayer2$TrackInfo r4 = (androidx.media2.player.MediaPlayer2.TrackInfo) r4
            androidx.media2.player.MediaPlayer$TrackInfo r5 = new androidx.media2.player.MediaPlayer$TrackInfo
            int r6 = r4.getTrackType()
            android.media.MediaFormat r4 = r4.getFormat()
            r5.<init>(r3, r1, r6, r4)
            r2.add(r5)
            int r3 = r3 + 1
            goto L_0x0020
        L_0x003f:
            return r2
        L_0x0040:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            goto L_0x0044
        L_0x0043:
            throw r1
        L_0x0044:
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.MediaPlayer.getTrackInfo():java.util.List");
    }

    /* access modifiers changed from: package-private */
    public TrackInfo getTrackInfo(int i) {
        MediaPlayer2.TrackInfo trackInfo = this.mPlayer.getTrackInfo().get(i);
        return new TrackInfo(i, this.mPlayer.getCurrentMediaItem(), trackInfo.getTrackType(), trackInfo.getFormat());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return getTrackInfo(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000b, code lost:
        r4 = r3.mPlayer.getSelectedTrack(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        if (r4 >= 0) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media2.player.MediaPlayer.TrackInfo getSelectedTrack(int r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mStateLock
            monitor-enter(r0)
            boolean r1 = r3.mClosed     // Catch:{ all -> 0x0019 }
            r2 = 0
            if (r1 == 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return r2
        L_0x000a:
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            androidx.media2.player.MediaPlayer2 r0 = r3.mPlayer
            int r4 = r0.getSelectedTrack(r4)
            if (r4 >= 0) goto L_0x0014
            goto L_0x0018
        L_0x0014:
            androidx.media2.player.MediaPlayer$TrackInfo r2 = r3.getTrackInfo(r4)
        L_0x0018:
            return r2
        L_0x0019:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.MediaPlayer.getSelectedTrack(int):androidx.media2.player.MediaPlayer$TrackInfo");
    }

    public ListenableFuture<SessionPlayer.PlayerResult> selectTrack(final TrackInfo trackInfo) {
        if (trackInfo != null) {
            synchronized (this.mStateLock) {
                if (this.mClosed) {
                    ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                    return createFutureForClosed;
                }
                final int id = trackInfo.getId();
                AnonymousClass26 r1 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                    /* access modifiers changed from: package-private */
                    public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                        ArrayList arrayList = new ArrayList();
                        ResolvableFuture create = ResolvableFuture.create();
                        synchronized (MediaPlayer.this.mPendingCommands) {
                            MediaPlayer.this.addPendingCommandWithTrackInfoLocked(15, create, trackInfo, MediaPlayer.this.mPlayer.selectTrack(id));
                        }
                        arrayList.add(create);
                        return arrayList;
                    }
                };
                addPendingFuture(r1);
                return r1;
            }
        }
        throw new NullPointerException("trackInfo shouldn't be null");
    }

    public ListenableFuture<SessionPlayer.PlayerResult> deselectTrack(final TrackInfo trackInfo) {
        if (trackInfo != null) {
            synchronized (this.mStateLock) {
                if (this.mClosed) {
                    ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed = createFutureForClosed();
                    return createFutureForClosed;
                }
                final int id = trackInfo.getId();
                AnonymousClass27 r1 = new PendingFuture<SessionPlayer.PlayerResult>(this.mExecutor) {
                    /* access modifiers changed from: package-private */
                    public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                        ArrayList arrayList = new ArrayList();
                        ResolvableFuture create = ResolvableFuture.create();
                        synchronized (MediaPlayer.this.mPendingCommands) {
                            MediaPlayer.this.addPendingCommandWithTrackInfoLocked(2, create, trackInfo, MediaPlayer.this.mPlayer.deselectTrack(id));
                        }
                        arrayList.add(create);
                        return arrayList;
                    }
                };
                addPendingFuture(r1);
                return r1;
            }
        }
        throw new NullPointerException("trackInfo shouldn't be null");
    }

    public List<SessionPlayer.TrackInfo> getTrackInfoInternal() {
        List<TrackInfo> trackInfo = getTrackInfo();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < trackInfo.size(); i++) {
            arrayList.add(createTrackInfoInternal(trackInfo.get(i)));
        }
        return arrayList;
    }

    public ListenableFuture<SessionPlayer.PlayerResult> selectTrackInternal(SessionPlayer.TrackInfo trackInfo) {
        return selectTrack(createTrackInfo(trackInfo));
    }

    public ListenableFuture<SessionPlayer.PlayerResult> deselectTrackInternal(SessionPlayer.TrackInfo trackInfo) {
        return deselectTrack(createTrackInfo(trackInfo));
    }

    public SessionPlayer.TrackInfo getSelectedTrackInternal(int i) {
        return createTrackInfoInternal(getSelectedTrack(i));
    }

    public void registerPlayerCallback(Executor executor, PlayerCallback playerCallback) {
        super.registerPlayerCallback(executor, playerCallback);
    }

    /* access modifiers changed from: package-private */
    public void setState(final int i) {
        boolean z;
        synchronized (this.mStateLock) {
            if (this.mState != i) {
                this.mState = i;
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                    playerCallback.onPlayerStateChanged(MediaPlayer.this, i);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void setBufferingState(final MediaItem mediaItem, final int i) {
        Integer put;
        synchronized (this.mStateLock) {
            put = this.mMediaItemToBuffState.put(mediaItem, Integer.valueOf(i));
        }
        if (put == null || put.intValue() != i) {
            notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                    playerCallback.onBufferingStateChanged(MediaPlayer.this, mediaItem, i);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        if (r0.hasNext() == false) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        r1 = r0.next();
        r2 = (androidx.media2.common.SessionPlayer.PlayerCallback) r1.first;
        ((java.util.concurrent.Executor) r1.second).execute(new androidx.media2.player.MediaPlayer.AnonymousClass32(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        r0 = getCallbacks().iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifySessionPlayerCallback(final androidx.media2.player.MediaPlayer.SessionPlayerCallbackNotifier r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mStateLock
            monitor-enter(r0)
            boolean r1 = r4.mClosed     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x0009:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            java.util.List r0 = r4.getCallbacks()
            java.util.Iterator r0 = r0.iterator()
        L_0x0012:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x002f
            java.lang.Object r1 = r0.next()
            androidx.core.util.Pair r1 = (androidx.core.util.Pair) r1
            F r2 = r1.first
            androidx.media2.common.SessionPlayer$PlayerCallback r2 = (androidx.media2.common.SessionPlayer.PlayerCallback) r2
            S r1 = r1.second
            java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
            androidx.media2.player.MediaPlayer$32 r3 = new androidx.media2.player.MediaPlayer$32
            r3.<init>(r5, r2)
            r1.execute(r3)
            goto L_0x0012
        L_0x002f:
            return
        L_0x0030:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            goto L_0x0034
        L_0x0033:
            throw r5
        L_0x0034:
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.MediaPlayer.notifySessionPlayerCallback(androidx.media2.player.MediaPlayer$SessionPlayerCallbackNotifier):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        if (r0.hasNext() == false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        r1 = r0.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        if ((r1.first instanceof androidx.media2.player.MediaPlayer.PlayerCallback) == false) goto L_0x0012;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        r2 = (androidx.media2.player.MediaPlayer.PlayerCallback) r1.first;
        ((java.util.concurrent.Executor) r1.second).execute(new androidx.media2.player.MediaPlayer.AnonymousClass33(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        r0 = getCallbacks().iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyMediaPlayerCallback(final androidx.media2.player.MediaPlayer.MediaPlayerCallbackNotifier r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mStateLock
            monitor-enter(r0)
            boolean r1 = r4.mClosed     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return
        L_0x0009:
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            java.util.List r0 = r4.getCallbacks()
            java.util.Iterator r0 = r0.iterator()
        L_0x0012:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0035
            java.lang.Object r1 = r0.next()
            androidx.core.util.Pair r1 = (androidx.core.util.Pair) r1
            F r2 = r1.first
            boolean r2 = r2 instanceof androidx.media2.player.MediaPlayer.PlayerCallback
            if (r2 == 0) goto L_0x0012
            F r2 = r1.first
            androidx.media2.player.MediaPlayer$PlayerCallback r2 = (androidx.media2.player.MediaPlayer.PlayerCallback) r2
            S r1 = r1.second
            java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
            androidx.media2.player.MediaPlayer$33 r3 = new androidx.media2.player.MediaPlayer$33
            r3.<init>(r5, r2)
            r1.execute(r3)
            goto L_0x0012
        L_0x0035:
            return
        L_0x0036:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0039:
            throw r5
        L_0x003a:
            goto L_0x0039
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.MediaPlayer.notifyMediaPlayerCallback(androidx.media2.player.MediaPlayer$MediaPlayerCallbackNotifier):void");
    }

    /* access modifiers changed from: package-private */
    public List<ResolvableFuture<SessionPlayer.PlayerResult>> setMediaItemsInternal(MediaItem mediaItem, MediaItem mediaItem2) {
        boolean z;
        if (mediaItem != null) {
            synchronized (this.mPlaylistLock) {
                z = this.mSetMediaItemCalled;
            }
            ArrayList arrayList = new ArrayList();
            if (z) {
                arrayList.add(setNextMediaItemInternal(mediaItem));
                arrayList.add(skipToNextInternal());
            } else {
                arrayList.add(setMediaItemInternal(mediaItem));
            }
            if (mediaItem2 != null) {
                arrayList.add(setNextMediaItemInternal(mediaItem2));
            }
            return arrayList;
        }
        throw new NullPointerException("curItem shouldn't be null");
    }

    private ResolvableFuture<SessionPlayer.PlayerResult> setMediaItemInternal(MediaItem mediaItem) {
        ResolvableFuture<SessionPlayer.PlayerResult> create = ResolvableFuture.create();
        synchronized (this.mPendingCommands) {
            addPendingCommandLocked(19, create, this.mPlayer.setMediaItem(mediaItem));
        }
        synchronized (this.mPlaylistLock) {
            this.mSetMediaItemCalled = true;
        }
        return create;
    }

    /* access modifiers changed from: package-private */
    public ResolvableFuture<SessionPlayer.PlayerResult> setNextMediaItemInternal(MediaItem mediaItem) {
        ResolvableFuture<SessionPlayer.PlayerResult> create = ResolvableFuture.create();
        synchronized (this.mPendingCommands) {
            addPendingCommandLocked(22, create, this.mPlayer.setNextMediaItem(mediaItem));
        }
        return create;
    }

    /* access modifiers changed from: package-private */
    public ResolvableFuture<SessionPlayer.PlayerResult> skipToNextInternal() {
        ResolvableFuture<SessionPlayer.PlayerResult> create = ResolvableFuture.create();
        synchronized (this.mPendingCommands) {
            addPendingCommandLocked(29, create, this.mPlayer.skipToNext());
        }
        return create;
    }

    /* access modifiers changed from: package-private */
    public ResolvableFuture<SessionPlayer.PlayerResult> setPlayerVolumeInternal(float f) {
        ResolvableFuture<SessionPlayer.PlayerResult> create = ResolvableFuture.create();
        synchronized (this.mPendingCommands) {
            addPendingCommandLocked(26, create, this.mPlayer.setPlayerVolume(f));
        }
        return create;
    }

    /* access modifiers changed from: package-private */
    public ResolvableFuture<SessionPlayer.PlayerResult> createFutureForClosed() {
        ResolvableFuture<SessionPlayer.PlayerResult> create = ResolvableFuture.create();
        create.set(new SessionPlayer.PlayerResult(-2, (MediaItem) null));
        return create;
    }

    /* access modifiers changed from: package-private */
    public ResolvableFuture<SessionPlayer.PlayerResult> createFutureForResultCode(int i) {
        return createFutureForResultCode(i, (MediaItem) null);
    }

    /* access modifiers changed from: package-private */
    public ResolvableFuture<SessionPlayer.PlayerResult> createFutureForResultCode(int i, MediaItem mediaItem) {
        ResolvableFuture<SessionPlayer.PlayerResult> create = ResolvableFuture.create();
        if (mediaItem == null) {
            mediaItem = this.mPlayer.getCurrentMediaItem();
        }
        create.set(new SessionPlayer.PlayerResult(i, mediaItem));
        return create;
    }

    /* access modifiers changed from: package-private */
    public List<ResolvableFuture<SessionPlayer.PlayerResult>> createFuturesForResultCode(int i) {
        return createFuturesForResultCode(i, (MediaItem) null);
    }

    /* access modifiers changed from: package-private */
    public List<ResolvableFuture<SessionPlayer.PlayerResult>> createFuturesForResultCode(int i, MediaItem mediaItem) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(createFutureForResultCode(i, mediaItem));
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public Pair<MediaItem, MediaItem> updateAndGetCurrentNextItemIfNeededLocked() {
        MediaItem mediaItem;
        MediaItem mediaItem2;
        int i = this.mCurrentShuffleIdx;
        if (i >= 0) {
            if (!Objects.equals(this.mCurPlaylistItem, this.mShuffledList.get(i))) {
                mediaItem = this.mShuffledList.get(this.mCurrentShuffleIdx);
                this.mCurPlaylistItem = mediaItem;
            } else {
                mediaItem = null;
            }
            int i2 = this.mCurrentShuffleIdx + 1;
            if (i2 >= this.mShuffledList.size()) {
                int i3 = this.mRepeatMode;
                i2 = (i3 == 2 || i3 == 3) ? 0 : -1;
            }
            if (i2 == -1) {
                this.mNextPlaylistItem = null;
            } else if (!Objects.equals(this.mNextPlaylistItem, this.mShuffledList.get(i2))) {
                mediaItem2 = this.mShuffledList.get(i2);
                this.mNextPlaylistItem = mediaItem2;
                if (mediaItem == null || mediaItem2 != null) {
                    return new Pair<>(mediaItem, mediaItem2);
                }
                return null;
            }
            mediaItem2 = null;
            if (mediaItem == null) {
            }
            return new Pair<>(mediaItem, mediaItem2);
        } else if (this.mCurPlaylistItem == null && this.mNextPlaylistItem == null) {
            return null;
        } else {
            this.mCurPlaylistItem = null;
            this.mNextPlaylistItem = null;
            return new Pair<>(null, null);
        }
    }

    /* access modifiers changed from: package-private */
    public void handleCallComplete(MediaPlayer2 mediaPlayer2, final MediaItem mediaItem, int i, int i2) {
        PendingCommand pollFirst;
        synchronized (this.mPendingCommands) {
            pollFirst = this.mPendingCommands.pollFirst();
        }
        if (pollFirst == null) {
            Log.i("MediaPlayer", "No matching call type for " + i + ". Possibly because of reset().");
            return;
        }
        final TrackInfo trackInfo = pollFirst.mTrackInfo;
        if (i != pollFirst.mCallType) {
            Log.w("MediaPlayer", "Call type does not match. expeced:" + pollFirst.mCallType + " actual:" + i);
            i2 = RecyclerView.UNDEFINED_DURATION;
        }
        if (i2 == 0) {
            if (i == 2) {
                notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                    public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                        MediaPlayer mediaPlayer = MediaPlayer.this;
                        playerCallback.onTrackDeselected(mediaPlayer, mediaPlayer.createTrackInfoInternal(trackInfo));
                    }
                });
            } else if (i == 19) {
                notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                    public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                        playerCallback.onCurrentMediaItemChanged(MediaPlayer.this, mediaItem);
                    }
                });
            } else if (i != 24) {
                if (i != 4) {
                    if (i != 5) {
                        if (i != 6) {
                            switch (i) {
                                case 14:
                                    final long currentPosition = getCurrentPosition();
                                    notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                                        public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                                            playerCallback.onSeekCompleted(MediaPlayer.this, currentPosition);
                                        }
                                    });
                                    break;
                                case 15:
                                    notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                                        public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                                            MediaPlayer mediaPlayer = MediaPlayer.this;
                                            playerCallback.onTrackSelected(mediaPlayer, mediaPlayer.createTrackInfoInternal(trackInfo));
                                        }
                                    });
                                    break;
                                case 16:
                                    final AudioAttributesCompat audioAttributes = this.mPlayer.getAudioAttributes();
                                    notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                                        public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                                            playerCallback.onAudioAttributesChanged(MediaPlayer.this, audioAttributes);
                                        }
                                    });
                                    break;
                            }
                        }
                    } else {
                        setState(2);
                    }
                }
                setState(1);
            } else {
                final float floatValue = this.mPlayer.getPlaybackParams().getSpeed().floatValue();
                notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                    public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                        playerCallback.onPlaybackSpeedChanged(MediaPlayer.this, floatValue);
                    }
                });
            }
        }
        if (i != 1001) {
            pollFirst.mFuture.set(new SessionPlayer.PlayerResult(Integer.valueOf(sResultCodeMap.containsKey(Integer.valueOf(i2)) ? sResultCodeMap.get(Integer.valueOf(i2)).intValue() : -1).intValue(), mediaItem));
        } else {
            pollFirst.mFuture.set(new DrmResult(Integer.valueOf(sPrepareDrmStatusMap.containsKey(Integer.valueOf(i2)) ? sPrepareDrmStatusMap.get(Integer.valueOf(i2)).intValue() : -1003).intValue(), mediaItem));
        }
        executePendingFutures();
    }

    private void executePendingFutures() {
        synchronized (this.mPendingFutures) {
            Iterator<PendingFuture<? super SessionPlayer.PlayerResult>> it = this.mPendingFutures.iterator();
            while (it.hasNext()) {
                PendingFuture next = it.next();
                if (!next.isCancelled() && !next.execute()) {
                    break;
                }
                this.mPendingFutures.removeFirst();
            }
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PendingFuture next2 = it.next();
                if (!next2.mIsSeekTo) {
                    break;
                }
                next2.execute();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public SessionPlayer.TrackInfo createTrackInfoInternal(TrackInfo trackInfo) {
        if (trackInfo == null) {
            return null;
        }
        return new SessionPlayer.TrackInfo(trackInfo.getId(), trackInfo.getMediaItem(), trackInfo.getTrackType(), trackInfo.getFormat());
    }

    private TrackInfo createTrackInfo(SessionPlayer.TrackInfo trackInfo) {
        if (trackInfo == null) {
            return null;
        }
        return new TrackInfo(trackInfo.getId(), trackInfo.getMediaItem(), trackInfo.getTrackType(), trackInfo.getFormat());
    }

    class Mp2DrmCallback extends MediaPlayer2.DrmEventCallback {
        Mp2DrmCallback() {
        }
    }

    class Mp2Callback extends MediaPlayer2.EventCallback {
        Mp2Callback() {
        }

        public void onVideoSizeChanged(MediaPlayer2 mediaPlayer2, final MediaItem mediaItem, int i, int i2) {
            final VideoSize videoSize = new VideoSize(i, i2);
            MediaPlayer.this.notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                    playerCallback.onVideoSizeChangedInternal(MediaPlayer.this, mediaItem, videoSize);
                }
            });
        }

        public void onTimedMetaDataAvailable(MediaPlayer2 mediaPlayer2, final MediaItem mediaItem, final TimedMetaData timedMetaData) {
            MediaPlayer.this.notifyMediaPlayerCallback(new MediaPlayerCallbackNotifier() {
                public void callCallback(PlayerCallback playerCallback) {
                    playerCallback.onTimedMetaDataAvailable(MediaPlayer.this, mediaItem, timedMetaData);
                }
            });
        }

        public void onError(MediaPlayer2 mediaPlayer2, final MediaItem mediaItem, final int i, final int i2) {
            MediaPlayer.this.setState(3);
            MediaPlayer.this.setBufferingState(mediaItem, 0);
            MediaPlayer.this.notifyMediaPlayerCallback(new MediaPlayerCallbackNotifier() {
                public void callCallback(PlayerCallback playerCallback) {
                    playerCallback.onError(MediaPlayer.this, mediaItem, i, i2);
                }
            });
        }

        public void onInfo(MediaPlayer2 mediaPlayer2, final MediaItem mediaItem, int i, final int i2) {
            final MediaItem mediaItem2;
            MediaItem mediaItem3;
            boolean z = true;
            if (i == 2) {
                synchronized (MediaPlayer.this.mPlaylistLock) {
                    if (MediaPlayer.this.mCurPlaylistItem == mediaItem) {
                        z = false;
                        mediaItem2 = null;
                    } else {
                        MediaPlayer.this.mCurrentShuffleIdx = MediaPlayer.this.mShuffledList.indexOf(mediaItem);
                        MediaPlayer.this.updateAndGetCurrentNextItemIfNeededLocked();
                        mediaItem2 = MediaPlayer.this.mNextPlaylistItem;
                    }
                }
                if (z) {
                    MediaPlayer.this.notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                        public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                            playerCallback.onCurrentMediaItemChanged(MediaPlayer.this, mediaItem);
                        }
                    });
                    if (mediaItem2 != null) {
                        MediaPlayer.this.addPendingFuture(new PendingFuture<SessionPlayer.PlayerResult>(MediaPlayer.this.mExecutor) {
                            /* access modifiers changed from: package-private */
                            public List<ResolvableFuture<SessionPlayer.PlayerResult>> onExecute() {
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(MediaPlayer.this.setNextMediaItemInternal(mediaItem2));
                                return arrayList;
                            }
                        });
                    }
                }
            } else if (i == 6) {
                synchronized (MediaPlayer.this.mPlaylistLock) {
                    MediaPlayer.this.mCurrentShuffleIdx = MediaPlayer.this.mShuffledList.indexOf(mediaItem);
                    mediaItem3 = MediaPlayer.this.mNextPlaylistItem;
                }
                if (mediaItem3 == null) {
                    MediaPlayer.this.setState(1);
                    MediaPlayer.this.notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                        public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                            playerCallback.onPlaybackCompleted(MediaPlayer.this);
                        }
                    });
                } else if (MediaPlayer.this.skipToNextPlaylistItem() == null) {
                    Log.e("MediaPlayer", "Cannot play next media item", new IllegalStateException());
                    MediaPlayer.this.setState(3);
                }
            } else if (i == 100) {
                MediaPlayer.this.notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                    public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                        playerCallback.onTrackInfoChanged(MediaPlayer.this, MediaPlayer.this.getTrackInfoInternal());
                    }
                });
                MediaPlayer.this.setBufferingState(mediaItem, 1);
            } else if (i != 704) {
                if (i == 802) {
                    MediaPlayer.this.notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                        public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                            playerCallback.onTrackInfoChanged(MediaPlayer.this, MediaPlayer.this.getTrackInfoInternal());
                        }
                    });
                } else if (i == 701) {
                    MediaPlayer.this.setBufferingState(mediaItem, 2);
                } else if (i == 702) {
                    MediaPlayer.this.setBufferingState(mediaItem, 1);
                }
            } else if (i2 >= 100) {
                MediaPlayer.this.setBufferingState(mediaItem, 3);
            }
            if (MediaPlayer.sInfoCodeMap.containsKey(Integer.valueOf(i))) {
                final int intValue = MediaPlayer.sInfoCodeMap.get(Integer.valueOf(i)).intValue();
                MediaPlayer.this.notifyMediaPlayerCallback(new MediaPlayerCallbackNotifier() {
                    public void callCallback(PlayerCallback playerCallback) {
                        playerCallback.onInfo(MediaPlayer.this, mediaItem, intValue, i2);
                    }
                });
            }
        }

        public void onCallCompleted(MediaPlayer2 mediaPlayer2, MediaItem mediaItem, int i, int i2) {
            MediaPlayer.this.handleCallComplete(mediaPlayer2, mediaItem, i, i2);
        }

        public void onMediaTimeDiscontinuity(MediaPlayer2 mediaPlayer2, final MediaItem mediaItem, final MediaTimestamp mediaTimestamp) {
            MediaPlayer.this.notifyMediaPlayerCallback(new MediaPlayerCallbackNotifier() {
                public void callCallback(PlayerCallback playerCallback) {
                    playerCallback.onMediaTimeDiscontinuity(MediaPlayer.this, mediaItem, mediaTimestamp);
                }
            });
        }

        public void onSubtitleData(MediaPlayer2 mediaPlayer2, final MediaItem mediaItem, final int i, final SubtitleData subtitleData) {
            MediaPlayer.this.notifySessionPlayerCallback(new SessionPlayerCallbackNotifier() {
                public void callCallback(SessionPlayer.PlayerCallback playerCallback) {
                    playerCallback.onSubtitleData(MediaPlayer.this, mediaItem, MediaPlayer.this.createTrackInfoInternal(MediaPlayer.this.getTrackInfo(i)), subtitleData);
                }
            });
        }
    }

    public static abstract class PlayerCallback extends SessionPlayer.PlayerCallback {
        public void onDrmInfo(MediaPlayer mediaPlayer, MediaItem mediaItem, DrmInfo drmInfo) {
        }

        public void onError(MediaPlayer mediaPlayer, MediaItem mediaItem, int i, int i2) {
        }

        public void onInfo(MediaPlayer mediaPlayer, MediaItem mediaItem, int i, int i2) {
        }

        public void onMediaTimeDiscontinuity(MediaPlayer mediaPlayer, MediaItem mediaItem, MediaTimestamp mediaTimestamp) {
        }

        public void onTimedMetaDataAvailable(MediaPlayer mediaPlayer, MediaItem mediaItem, TimedMetaData timedMetaData) {
        }

        public void onVideoSizeChanged(MediaPlayer mediaPlayer, MediaItem mediaItem, VideoSize videoSize) {
        }

        public void onVideoSizeChangedInternal(SessionPlayer sessionPlayer, MediaItem mediaItem, VideoSize videoSize) {
            if (sessionPlayer instanceof MediaPlayer) {
                onVideoSizeChanged((MediaPlayer) sessionPlayer, mediaItem, new VideoSize(videoSize));
                return;
            }
            throw new IllegalArgumentException("player must be MediaPlayer");
        }
    }

    public static final class TrackInfo {
        private final MediaFormat mFormat;
        private final int mId;
        private final MediaItem mItem;
        private final int mTrackType;

        public int getTrackType() {
            return this.mTrackType;
        }

        public MediaFormat getFormat() {
            if (this.mTrackType == 4) {
                return this.mFormat;
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public int getId() {
            return this.mId;
        }

        /* access modifiers changed from: package-private */
        public MediaItem getMediaItem() {
            return this.mItem;
        }

        public TrackInfo(int i, MediaItem mediaItem, int i2, MediaFormat mediaFormat) {
            this.mId = i;
            this.mItem = mediaItem;
            this.mTrackType = i2;
            this.mFormat = mediaFormat;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append(getClass().getName());
            sb.append('#');
            sb.append(this.mId);
            sb.append('{');
            int i = this.mTrackType;
            if (i == 1) {
                sb.append("VIDEO");
            } else if (i == 2) {
                sb.append("AUDIO");
            } else if (i != 4) {
                sb.append("UNKNOWN");
            } else {
                sb.append("SUBTITLE");
            }
            sb.append(", ");
            sb.append(this.mFormat);
            sb.append("}");
            return sb.toString();
        }

        public int hashCode() {
            int i;
            int i2 = this.mId + 31;
            MediaItem mediaItem = this.mItem;
            if (mediaItem != null) {
                i = mediaItem.getMediaId() != null ? this.mItem.getMediaId().hashCode() : this.mItem.hashCode();
            } else {
                i = 0;
            }
            return (i2 * 31) + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TrackInfo trackInfo = (TrackInfo) obj;
            if (this.mId != trackInfo.mId) {
                return false;
            }
            if (this.mItem == null && trackInfo.mItem == null) {
                return true;
            }
            MediaItem mediaItem = this.mItem;
            if (mediaItem == null || trackInfo.mItem == null) {
                return false;
            }
            String mediaId = mediaItem.getMediaId();
            if (mediaId != null) {
                return mediaId.equals(trackInfo.mItem.getMediaId());
            }
            return this.mItem.equals(trackInfo.mItem);
        }
    }

    public static class DrmResult extends SessionPlayer.PlayerResult {
        public DrmResult(int i, MediaItem mediaItem) {
            super(i, mediaItem);
        }

        public int getResultCode() {
            return super.getResultCode();
        }
    }

    static class MediaItemList {
        private ArrayList<MediaItem> mList = new ArrayList<>();

        MediaItemList() {
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            Iterator<MediaItem> it = this.mList.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                if (next instanceof FileMediaItem) {
                    ((FileMediaItem) next).decreaseRefCount();
                }
            }
            this.mList.clear();
        }

        /* access modifiers changed from: package-private */
        public int indexOf(Object obj) {
            return this.mList.indexOf(obj);
        }
    }
}
