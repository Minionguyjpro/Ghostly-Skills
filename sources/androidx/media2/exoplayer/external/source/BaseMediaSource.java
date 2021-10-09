package androidx.media2.exoplayer.external.source;

import android.os.Handler;
import android.os.Looper;
import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.source.MediaSourceEventListener;
import androidx.media2.exoplayer.external.upstream.TransferListener;
import androidx.media2.exoplayer.external.util.Assertions;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseMediaSource implements MediaSource {
    private final MediaSourceEventListener.EventDispatcher eventDispatcher = new MediaSourceEventListener.EventDispatcher();
    private Looper looper;
    private Object manifest;
    private final ArrayList<MediaSource.SourceInfoRefreshListener> sourceInfoListeners = new ArrayList<>(1);
    private Timeline timeline;

    public Object getTag() {
        return MediaSource$$CC.getTag$$dflt$$(this);
    }

    /* access modifiers changed from: protected */
    public abstract void prepareSourceInternal(TransferListener transferListener);

    /* access modifiers changed from: protected */
    public abstract void releaseSourceInternal();

    /* access modifiers changed from: protected */
    public final void refreshSourceInfo(Timeline timeline2, Object obj) {
        this.timeline = timeline2;
        this.manifest = obj;
        Iterator<MediaSource.SourceInfoRefreshListener> it = this.sourceInfoListeners.iterator();
        while (it.hasNext()) {
            it.next().onSourceInfoRefreshed(this, timeline2, obj);
        }
    }

    /* access modifiers changed from: protected */
    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(MediaSource.MediaPeriodId mediaPeriodId) {
        return this.eventDispatcher.withParameters(0, mediaPeriodId, 0);
    }

    /* access modifiers changed from: protected */
    public final MediaSourceEventListener.EventDispatcher createEventDispatcher(int i, MediaSource.MediaPeriodId mediaPeriodId, long j) {
        return this.eventDispatcher.withParameters(i, mediaPeriodId, j);
    }

    public final void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this.eventDispatcher.addEventListener(handler, mediaSourceEventListener);
    }

    public final void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
        this.eventDispatcher.removeEventListener(mediaSourceEventListener);
    }

    public final void prepareSource(MediaSource.SourceInfoRefreshListener sourceInfoRefreshListener, TransferListener transferListener) {
        Looper myLooper = Looper.myLooper();
        Looper looper2 = this.looper;
        Assertions.checkArgument(looper2 == null || looper2 == myLooper);
        this.sourceInfoListeners.add(sourceInfoRefreshListener);
        if (this.looper == null) {
            this.looper = myLooper;
            prepareSourceInternal(transferListener);
            return;
        }
        Timeline timeline2 = this.timeline;
        if (timeline2 != null) {
            sourceInfoRefreshListener.onSourceInfoRefreshed(this, timeline2, this.manifest);
        }
    }

    public final void releaseSource(MediaSource.SourceInfoRefreshListener sourceInfoRefreshListener) {
        this.sourceInfoListeners.remove(sourceInfoRefreshListener);
        if (this.sourceInfoListeners.isEmpty()) {
            this.looper = null;
            this.timeline = null;
            this.manifest = null;
            releaseSourceInternal();
        }
    }
}
