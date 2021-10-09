package androidx.media2.player.exoplayer;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.SubtitleData;
import androidx.media2.player.MediaPlayer2;
import androidx.media2.player.MediaTimestamp;
import androidx.media2.player.PlaybackParams;
import androidx.media2.player.TimedMetaData;
import androidx.media2.player.exoplayer.ExoPlayerWrapper;
import androidx.media2.player.futures.ResolvableFuture;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public final class ExoPlayerMediaPlayer2Impl extends MediaPlayer2 implements ExoPlayerWrapper.Listener {
    Task mCurrentTask;
    private Pair<Executor, MediaPlayer2.DrmEventCallback> mExecutorAndDrmEventCallback;
    private Pair<Executor, MediaPlayer2.EventCallback> mExecutorAndEventCallback;
    private HandlerThread mHandlerThread;
    final Object mLock = new Object();
    final ArrayDeque<Task> mPendingTasks = new ArrayDeque<>();
    final ExoPlayerWrapper mPlayer;
    private final Handler mTaskHandler = new Handler(this.mPlayer.getLooper());
    final Object mTaskLock = new Object();

    private interface Mp2EventNotifier {
        void notify(MediaPlayer2.EventCallback eventCallback);
    }

    public ExoPlayerMediaPlayer2Impl(Context context) {
        HandlerThread handlerThread = new HandlerThread("ExoMediaPlayer2Thread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mPlayer = new ExoPlayerWrapper(context.getApplicationContext(), this, this.mHandlerThread.getLooper());
        resetPlayer();
    }

    public void clearPendingCommands() {
        synchronized (this.mTaskLock) {
            this.mPendingTasks.clear();
        }
    }

    public boolean cancel(Object obj) {
        boolean remove;
        synchronized (this.mTaskLock) {
            remove = this.mPendingTasks.remove(obj);
        }
        return remove;
    }

    private Object addTask(Task task) {
        synchronized (this.mTaskLock) {
            this.mPendingTasks.add(task);
            processPendingTask();
        }
        return task;
    }

    /* access modifiers changed from: package-private */
    public void processPendingTask() {
        if (this.mCurrentTask == null && !this.mPendingTasks.isEmpty()) {
            Task removeFirst = this.mPendingTasks.removeFirst();
            this.mCurrentTask = removeFirst;
            this.mTaskHandler.post(removeFirst);
        }
    }

    public void setEventCallback(Executor executor, MediaPlayer2.EventCallback eventCallback) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(eventCallback);
        synchronized (this.mLock) {
            this.mExecutorAndEventCallback = Pair.create(executor, eventCallback);
        }
    }

    public void clearEventCallback() {
        synchronized (this.mLock) {
            this.mExecutorAndEventCallback = null;
        }
    }

    public void setDrmEventCallback(Executor executor, MediaPlayer2.DrmEventCallback drmEventCallback) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(drmEventCallback);
        synchronized (this.mLock) {
            this.mExecutorAndDrmEventCallback = Pair.create(executor, drmEventCallback);
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyMediaPlayer2Event(final Mp2EventNotifier mp2EventNotifier) {
        Pair<Executor, MediaPlayer2.EventCallback> pair;
        synchronized (this.mLock) {
            pair = this.mExecutorAndEventCallback;
        }
        if (pair != null) {
            Executor executor = (Executor) pair.first;
            final MediaPlayer2.EventCallback eventCallback = (MediaPlayer2.EventCallback) pair.second;
            try {
                executor.execute(new Runnable() {
                    public void run() {
                        mp2EventNotifier.notify(eventCallback);
                    }
                });
            } catch (RejectedExecutionException unused) {
                Log.w("ExoPlayerMediaPlayer2", "The given executor is shutting down. Ignoring the player event.");
            }
        }
    }

    public Object setMediaItem(final MediaItem mediaItem) {
        return addTask(new Task(19, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setMediaItem(mediaItem);
            }
        });
    }

    public MediaItem getCurrentMediaItem() {
        return (MediaItem) runPlayerCallableBlocking(new Callable<MediaItem>() {
            public MediaItem call() throws Exception {
                return ExoPlayerMediaPlayer2Impl.this.mPlayer.getCurrentMediaItem();
            }
        });
    }

    public Object prepare() {
        return addTask(new Task(6, true) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.prepare();
            }
        });
    }

    public Object play() {
        return addTask(new Task(5, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.play();
            }
        });
    }

    public Object pause() {
        return addTask(new Task(4, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.pause();
            }
        });
    }

    public Object seekTo(long j, int i) {
        final long j2 = j;
        final int i2 = i;
        return addTask(new Task(14, true) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.seekTo(j2, i2);
            }
        });
    }

    public long getCurrentPosition() {
        return ((Long) runPlayerCallableBlocking(new Callable<Long>() {
            public Long call() throws Exception {
                return Long.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getCurrentPosition());
            }
        })).longValue();
    }

    public long getDuration() {
        return ((Long) runPlayerCallableBlocking(new Callable<Long>() {
            public Long call() throws Exception {
                return Long.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getDuration());
            }
        })).longValue();
    }

    public long getBufferedPosition() {
        return ((Long) runPlayerCallableBlocking(new Callable<Long>() {
            public Long call() throws Exception {
                return Long.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getBufferedPosition());
            }
        })).longValue();
    }

    public Object skipToNext() {
        return addTask(new Task(29, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.skipToNext();
            }
        });
    }

    public Object setNextMediaItem(final MediaItem mediaItem) {
        return addTask(new Task(22, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setNextMediaItem(mediaItem);
            }
        });
    }

    public Object setAudioAttributes(final AudioAttributesCompat audioAttributesCompat) {
        return addTask(new Task(16, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setAudioAttributes(audioAttributesCompat);
            }
        });
    }

    public AudioAttributesCompat getAudioAttributes() {
        return (AudioAttributesCompat) runPlayerCallableBlocking(new Callable<AudioAttributesCompat>() {
            public AudioAttributesCompat call() throws Exception {
                return ExoPlayerMediaPlayer2Impl.this.mPlayer.getAudioAttributes();
            }
        });
    }

    public Object setPlaybackParams(final PlaybackParams playbackParams) {
        return addTask(new Task(24, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setPlaybackParams(playbackParams);
            }
        });
    }

    public PlaybackParams getPlaybackParams() {
        return (PlaybackParams) runPlayerCallableBlocking(new Callable<PlaybackParams>() {
            public PlaybackParams call() throws Exception {
                return ExoPlayerMediaPlayer2Impl.this.mPlayer.getPlaybackParams();
            }
        });
    }

    public int getVideoWidth() {
        return ((Integer) runPlayerCallableBlocking(new Callable<Integer>() {
            public Integer call() throws Exception {
                return Integer.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getVideoWidth());
            }
        })).intValue();
    }

    public int getVideoHeight() {
        return ((Integer) runPlayerCallableBlocking(new Callable<Integer>() {
            public Integer call() throws Exception {
                return Integer.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getVideoHeight());
            }
        })).intValue();
    }

    public Object setSurface(final Surface surface) {
        return addTask(new Task(27, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setSurface(surface);
            }
        });
    }

    public Object setPlayerVolume(final float f) {
        return addTask(new Task(26, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.setVolume(f);
            }
        });
    }

    public float getPlayerVolume() {
        return ((Float) runPlayerCallableBlocking(new Callable<Float>() {
            public Float call() throws Exception {
                return Float.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getVolume());
            }
        })).floatValue();
    }

    public List<MediaPlayer2.TrackInfo> getTrackInfo() {
        return (List) runPlayerCallableBlocking(new Callable<List<MediaPlayer2.TrackInfo>>() {
            public List<MediaPlayer2.TrackInfo> call() throws Exception {
                return ExoPlayerMediaPlayer2Impl.this.mPlayer.getTrackInfo();
            }
        });
    }

    public int getSelectedTrack(final int i) {
        return ((Integer) runPlayerCallableBlocking(new Callable<Integer>() {
            public Integer call() {
                return Integer.valueOf(ExoPlayerMediaPlayer2Impl.this.mPlayer.getSelectedTrack(i));
            }
        })).intValue();
    }

    public Object selectTrack(final int i) {
        return addTask(new Task(15, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.selectTrack(i);
            }
        });
    }

    public Object deselectTrack(final int i) {
        return addTask(new Task(2, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.deselectTrack(i);
            }
        });
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0016 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reset() {
        /*
            r2 = this;
            r2.clearPendingCommands()
            java.lang.Object r0 = r2.mTaskLock
            monitor-enter(r0)
            androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl$Task r1 = r2.mCurrentTask     // Catch:{ all -> 0x0029 }
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            if (r1 == 0) goto L_0x001a
            monitor-enter(r1)
        L_0x000c:
            boolean r0 = r1.mDone     // Catch:{ InterruptedException -> 0x0016 }
            if (r0 != 0) goto L_0x0016
            r1.wait()     // Catch:{ InterruptedException -> 0x0016 }
            goto L_0x000c
        L_0x0014:
            r0 = move-exception
            goto L_0x0018
        L_0x0016:
            monitor-exit(r1)     // Catch:{ all -> 0x0014 }
            goto L_0x001a
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x0014 }
            throw r0
        L_0x001a:
            android.os.Handler r0 = r2.mTaskHandler
            r1 = 0
            r0.removeCallbacksAndMessages(r1)
            androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl$36 r0 = new androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl$36
            r0.<init>()
            r2.runPlayerCallableBlocking(r0)
            return
        L_0x0029:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            goto L_0x002d
        L_0x002c:
            throw r1
        L_0x002d:
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.player.exoplayer.ExoPlayerMediaPlayer2Impl.reset():void");
    }

    public void close() {
        clearEventCallback();
        synchronized (this.mLock) {
            HandlerThread handlerThread = this.mHandlerThread;
            if (handlerThread != null) {
                this.mHandlerThread = null;
                runPlayerCallableBlocking(new Callable<Void>() {
                    public Void call() {
                        ExoPlayerMediaPlayer2Impl.this.mPlayer.close();
                        return null;
                    }
                });
                handlerThread.quit();
            }
        }
    }

    public void onPrepared(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 100);
        synchronized (this.mTaskLock) {
            if (this.mCurrentTask != null && this.mCurrentTask.mMediaCallType == 6 && ObjectsCompat.equals(this.mCurrentTask.mDSD, mediaItem) && this.mCurrentTask.mNeedToWaitForEventToComplete) {
                this.mCurrentTask.sendCompleteNotification(0);
                this.mCurrentTask = null;
                processPendingTask();
            }
        }
    }

    public void onMetadataChanged(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 802);
    }

    public void onSeekCompleted() {
        synchronized (this.mTaskLock) {
            if (this.mCurrentTask != null && this.mCurrentTask.mMediaCallType == 14 && this.mCurrentTask.mNeedToWaitForEventToComplete) {
                this.mCurrentTask.sendCompleteNotification(0);
                this.mCurrentTask = null;
                processPendingTask();
            }
        }
    }

    public void onBufferingStarted(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 701);
    }

    public void onBufferingEnded(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 702);
    }

    public void onBufferingUpdate(MediaItem mediaItem, int i) {
        notifyOnInfo(mediaItem, 704, i);
    }

    public void onBandwidthSample(MediaItem mediaItem, int i) {
        notifyOnInfo(mediaItem, 703, i);
    }

    public void onVideoRenderingStart(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 3);
    }

    public void onVideoSizeChanged(final MediaItem mediaItem, final int i, final int i2) {
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onVideoSizeChanged(ExoPlayerMediaPlayer2Impl.this, mediaItem, i, i2);
            }
        });
    }

    public void onSubtitleData(final MediaItem mediaItem, final int i, final SubtitleData subtitleData) {
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onSubtitleData(ExoPlayerMediaPlayer2Impl.this, mediaItem, i, subtitleData);
            }
        });
    }

    public void onTimedMetadata(final MediaItem mediaItem, final TimedMetaData timedMetaData) {
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onTimedMetaDataAvailable(ExoPlayerMediaPlayer2Impl.this, mediaItem, timedMetaData);
            }
        });
    }

    public void onMediaItemStartedAsNext(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 2);
    }

    public void onMediaItemEnded(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 5);
    }

    public void onLoop(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 7);
    }

    public void onMediaTimeDiscontinuity(final MediaItem mediaItem, final MediaTimestamp mediaTimestamp) {
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onMediaTimeDiscontinuity(ExoPlayerMediaPlayer2Impl.this, mediaItem, mediaTimestamp);
            }
        });
    }

    public void onPlaybackEnded(MediaItem mediaItem) {
        notifyOnInfo(mediaItem, 6);
    }

    public void onError(final MediaItem mediaItem, final int i) {
        synchronized (this.mTaskLock) {
            if (this.mCurrentTask != null && this.mCurrentTask.mNeedToWaitForEventToComplete) {
                this.mCurrentTask.sendCompleteNotification(RecyclerView.UNDEFINED_DURATION);
                this.mCurrentTask = null;
                processPendingTask();
            }
        }
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onError(ExoPlayerMediaPlayer2Impl.this, mediaItem, i, 0);
            }
        });
    }

    private void notifyOnInfo(MediaItem mediaItem, int i) {
        notifyOnInfo(mediaItem, i, 0);
    }

    private void notifyOnInfo(final MediaItem mediaItem, final int i, final int i2) {
        notifyMediaPlayer2Event(new Mp2EventNotifier() {
            public void notify(MediaPlayer2.EventCallback eventCallback) {
                eventCallback.onInfo(ExoPlayerMediaPlayer2Impl.this, mediaItem, i, i2);
            }
        });
    }

    private void resetPlayer() {
        runPlayerCallableBlocking(new Callable<Void>() {
            public Void call() throws Exception {
                ExoPlayerMediaPlayer2Impl.this.mPlayer.reset();
                return null;
            }
        });
    }

    private <T> T runPlayerCallableBlocking(final Callable<T> callable) {
        T t;
        final ResolvableFuture create = ResolvableFuture.create();
        Preconditions.checkState(this.mTaskHandler.post(new Runnable() {
            public void run() {
                try {
                    create.set(callable.call());
                } catch (Throwable th) {
                    create.setException(th);
                }
            }
        }));
        boolean z = false;
        while (true) {
            try {
                t = create.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            try {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                Log.e("ExoPlayerMediaPlayer2", "Internal player error", new RuntimeException(cause));
                throw new IllegalStateException(cause);
            }
        }
        return t;
    }

    private abstract class Task implements Runnable {
        MediaItem mDSD;
        boolean mDone;
        final int mMediaCallType;
        final boolean mNeedToWaitForEventToComplete;

        /* access modifiers changed from: package-private */
        public abstract void process() throws IOException, MediaPlayer2.NoDrmSchemeException;

        Task(int i, boolean z) {
            this.mMediaCallType = i;
            this.mNeedToWaitForEventToComplete = z;
        }

        public void run() {
            boolean z;
            int i = 0;
            if (this.mMediaCallType == 14) {
                synchronized (ExoPlayerMediaPlayer2Impl.this.mTaskLock) {
                    Task peekFirst = ExoPlayerMediaPlayer2Impl.this.mPendingTasks.peekFirst();
                    z = peekFirst != null && peekFirst.mMediaCallType == 14;
                }
            } else {
                z = false;
            }
            if (!z) {
                try {
                    if (this.mMediaCallType == 1000 || !ExoPlayerMediaPlayer2Impl.this.mPlayer.hasError()) {
                        process();
                    } else {
                        i = 1;
                    }
                } catch (IllegalStateException unused) {
                } catch (IllegalArgumentException unused2) {
                    i = 2;
                } catch (SecurityException unused3) {
                    i = 3;
                } catch (IOException unused4) {
                    i = 4;
                } catch (Exception unused5) {
                    i = RecyclerView.UNDEFINED_DURATION;
                }
            } else {
                i = 5;
            }
            this.mDSD = ExoPlayerMediaPlayer2Impl.this.mPlayer.getCurrentMediaItem();
            if (!this.mNeedToWaitForEventToComplete || i != 0 || z) {
                sendCompleteNotification(i);
                synchronized (ExoPlayerMediaPlayer2Impl.this.mTaskLock) {
                    ExoPlayerMediaPlayer2Impl.this.mCurrentTask = null;
                    ExoPlayerMediaPlayer2Impl.this.processPendingTask();
                }
            }
            synchronized (this) {
                this.mDone = true;
                notifyAll();
            }
        }

        /* access modifiers changed from: package-private */
        public void sendCompleteNotification(final int i) {
            if (this.mMediaCallType < 1000) {
                ExoPlayerMediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onCallCompleted(ExoPlayerMediaPlayer2Impl.this, Task.this.mDSD, Task.this.mMediaCallType, i);
                    }
                });
            }
        }
    }
}
