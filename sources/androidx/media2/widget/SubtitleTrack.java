package androidx.media2.widget;

import android.graphics.Canvas;
import android.media.MediaFormat;
import android.os.Handler;
import android.util.Log;
import android.util.LongSparseArray;
import androidx.media2.common.SubtitleData;
import androidx.media2.widget.MediaTimeProvider;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

abstract class SubtitleTrack implements MediaTimeProvider.OnMediaTimeListener {
    public boolean DEBUG = false;
    final ArrayList<Cue> mActiveCues = new ArrayList<>();
    private CueList mCues;
    private MediaFormat mFormat;
    protected Handler mHandler = new Handler();
    private long mLastTimeMs;
    private long mLastUpdateTimeMs;
    private long mNextScheduledTimeMs = -1;
    private final LongSparseArray<Run> mRunsByEndTime = new LongSparseArray<>();
    private final LongSparseArray<Run> mRunsByID = new LongSparseArray<>();
    protected MediaTimeProvider mTimeProvider;
    protected boolean mVisible;

    public static class Cue {
        public long mEndTimeMs;
        public long[] mInnerTimesMs;
        public Cue mNextInRun;
        public long mStartTimeMs;
    }

    interface RenderingWidget {

        public interface OnChangedListener {
            void onChanged(RenderingWidget renderingWidget);
        }

        void draw(Canvas canvas);

        void onAttachedToWindow();

        void onDetachedFromWindow();

        void setOnChangedListener(OnChangedListener onChangedListener);

        void setSize(int i, int i2);

        void setVisible(boolean z);
    }

    public abstract RenderingWidget getRenderingWidget();

    /* access modifiers changed from: protected */
    public abstract void onData(byte[] bArr, boolean z, long j);

    SubtitleTrack(MediaFormat mediaFormat) {
        this.mFormat = mediaFormat;
        this.mCues = new CueList();
        clearActiveCues();
        this.mLastTimeMs = -1;
    }

    public final MediaFormat getFormat() {
        return this.mFormat;
    }

    public void onData(SubtitleData subtitleData) {
        long startTimeUs = subtitleData.getStartTimeUs() + 1;
        onData(subtitleData.getData(), true, startTimeUs);
        setRunDiscardTimeMs(startTimeUs, (subtitleData.getStartTimeUs() + subtitleData.getDurationUs()) / 1000);
    }

    private void removeRunsByEndTimeIndex(int i) {
        Run valueAt = this.mRunsByEndTime.valueAt(i);
        while (valueAt != null) {
            Cue cue = valueAt.mFirstCue;
            while (cue != null) {
                this.mCues.remove(cue);
                Cue cue2 = cue.mNextInRun;
                cue.mNextInRun = null;
                cue = cue2;
            }
            this.mRunsByID.remove(valueAt.mRunID);
            Run run = valueAt.mNextRunAtEndTimeMs;
            valueAt.mPrevRunAtEndTimeMs = null;
            valueAt.mNextRunAtEndTimeMs = null;
            valueAt = run;
        }
        this.mRunsByEndTime.removeAt(i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        for (int size = this.mRunsByEndTime.size() - 1; size >= 0; size--) {
            removeRunsByEndTimeIndex(size);
        }
        super.finalize();
    }

    /* access modifiers changed from: protected */
    public synchronized void clearActiveCues() {
        if (this.DEBUG) {
            Log.v("SubtitleTrack", "Clearing " + this.mActiveCues.size() + " active cues");
        }
        this.mActiveCues.clear();
        this.mLastUpdateTimeMs = -1;
    }

    public void show() {
        if (!this.mVisible) {
            this.mVisible = true;
            RenderingWidget renderingWidget = getRenderingWidget();
            if (renderingWidget != null) {
                renderingWidget.setVisible(true);
            }
            MediaTimeProvider mediaTimeProvider = this.mTimeProvider;
            if (mediaTimeProvider != null) {
                mediaTimeProvider.scheduleUpdate(this);
            }
        }
    }

    public void hide() {
        if (this.mVisible) {
            MediaTimeProvider mediaTimeProvider = this.mTimeProvider;
            if (mediaTimeProvider != null) {
                mediaTimeProvider.cancelNotifications(this);
            }
            RenderingWidget renderingWidget = getRenderingWidget();
            if (renderingWidget != null) {
                renderingWidget.setVisible(false);
            }
            this.mVisible = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setTimeProvider(androidx.media2.widget.MediaTimeProvider r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            androidx.media2.widget.MediaTimeProvider r0 = r1.mTimeProvider     // Catch:{ all -> 0x0019 }
            if (r0 != r2) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            androidx.media2.widget.MediaTimeProvider r0 = r1.mTimeProvider     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0010
            androidx.media2.widget.MediaTimeProvider r0 = r1.mTimeProvider     // Catch:{ all -> 0x0019 }
            r0.cancelNotifications(r1)     // Catch:{ all -> 0x0019 }
        L_0x0010:
            r1.mTimeProvider = r2     // Catch:{ all -> 0x0019 }
            if (r2 == 0) goto L_0x0017
            r2.scheduleUpdate(r1)     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.widget.SubtitleTrack.setTimeProvider(androidx.media2.widget.MediaTimeProvider):void");
    }

    static class CueList {
        public boolean DEBUG = false;
        SortedMap<Long, ArrayList<Cue>> mCues = new TreeMap();

        /* access modifiers changed from: package-private */
        public void removeEvent(Cue cue, long j) {
            ArrayList arrayList = (ArrayList) this.mCues.get(Long.valueOf(j));
            if (arrayList != null) {
                arrayList.remove(cue);
                if (arrayList.size() == 0) {
                    this.mCues.remove(Long.valueOf(j));
                }
            }
        }

        public void remove(Cue cue) {
            removeEvent(cue, cue.mStartTimeMs);
            if (cue.mInnerTimesMs != null) {
                for (long removeEvent : cue.mInnerTimesMs) {
                    removeEvent(cue, removeEvent);
                }
            }
            removeEvent(cue, cue.mEndTimeMs);
        }

        CueList() {
        }
    }

    public void setRunDiscardTimeMs(long j, long j2) {
        Run run;
        if (j != 0 && j != -1 && (run = this.mRunsByID.get(j)) != null) {
            run.mEndTimeMs = j2;
            run.storeByEndTimeMs(this.mRunsByEndTime);
        }
    }

    public int getTrackType() {
        return getRenderingWidget() == null ? 3 : 4;
    }

    private static class Run {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public long mEndTimeMs = -1;
        public Cue mFirstCue;
        public Run mNextRunAtEndTimeMs;
        public Run mPrevRunAtEndTimeMs;
        public long mRunID = 0;
        private long mStoredEndTimeMs = -1;

        static {
            Class<SubtitleTrack> cls = SubtitleTrack.class;
        }

        Run() {
        }

        public void storeByEndTimeMs(LongSparseArray<Run> longSparseArray) {
            int indexOfKey = longSparseArray.indexOfKey(this.mStoredEndTimeMs);
            if (indexOfKey >= 0) {
                if (this.mPrevRunAtEndTimeMs == null) {
                    Run run = this.mNextRunAtEndTimeMs;
                    if (run == null) {
                        longSparseArray.removeAt(indexOfKey);
                    } else {
                        longSparseArray.setValueAt(indexOfKey, run);
                    }
                }
                removeAtEndTimeMs();
            }
            long j = this.mEndTimeMs;
            if (j >= 0) {
                this.mPrevRunAtEndTimeMs = null;
                Run run2 = longSparseArray.get(j);
                this.mNextRunAtEndTimeMs = run2;
                if (run2 != null) {
                    run2.mPrevRunAtEndTimeMs = this;
                }
                longSparseArray.put(this.mEndTimeMs, this);
                this.mStoredEndTimeMs = this.mEndTimeMs;
            }
        }

        public void removeAtEndTimeMs() {
            Run run = this.mPrevRunAtEndTimeMs;
            if (run != null) {
                run.mNextRunAtEndTimeMs = this.mNextRunAtEndTimeMs;
                this.mPrevRunAtEndTimeMs = null;
            }
            Run run2 = this.mNextRunAtEndTimeMs;
            if (run2 != null) {
                run2.mPrevRunAtEndTimeMs = run;
                this.mNextRunAtEndTimeMs = null;
            }
        }
    }
}
