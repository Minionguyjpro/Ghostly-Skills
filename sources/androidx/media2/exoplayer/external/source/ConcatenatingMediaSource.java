package androidx.media2.exoplayer.external.source;

import android.os.Handler;
import android.os.Message;
import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.source.ShuffleOrder;
import androidx.media2.exoplayer.external.upstream.Allocator;
import androidx.media2.exoplayer.external.upstream.TransferListener;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConcatenatingMediaSource extends CompositeMediaSource<MediaSourceHolder> {
    private final boolean isAtomic;
    private final Map<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final Map<Object, MediaSourceHolder> mediaSourceByUid;
    private final List<MediaSourceHolder> mediaSourceHolders;
    private final List<MediaSourceHolder> mediaSourcesPublic;
    private Set<HandlerAndRunnable> nextTimelineUpdateOnCompletionActions;
    private final Set<HandlerAndRunnable> pendingOnCompletionActions;
    private final Timeline.Period period;
    private int periodCount;
    private Handler playbackThreadHandler;
    private ShuffleOrder shuffleOrder;
    private boolean timelineUpdateScheduled;
    private final boolean useLazyPreparation;
    private final Timeline.Window window;
    private int windowCount;

    public Object getTag() {
        return null;
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
    }

    public ConcatenatingMediaSource(MediaSource... mediaSourceArr) {
        this(false, mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, MediaSource... mediaSourceArr) {
        this(z, new ShuffleOrder.DefaultShuffleOrder(0), mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, ShuffleOrder shuffleOrder2, MediaSource... mediaSourceArr) {
        this(z, false, shuffleOrder2, mediaSourceArr);
    }

    public ConcatenatingMediaSource(boolean z, boolean z2, ShuffleOrder shuffleOrder2, MediaSource... mediaSourceArr) {
        for (MediaSource checkNotNull : mediaSourceArr) {
            Assertions.checkNotNull(checkNotNull);
        }
        this.shuffleOrder = shuffleOrder2.getLength() > 0 ? shuffleOrder2.cloneAndClear() : shuffleOrder2;
        this.mediaSourceByMediaPeriod = new IdentityHashMap();
        this.mediaSourceByUid = new HashMap();
        this.mediaSourcesPublic = new ArrayList();
        this.mediaSourceHolders = new ArrayList();
        this.nextTimelineUpdateOnCompletionActions = new HashSet();
        this.pendingOnCompletionActions = new HashSet();
        this.isAtomic = z;
        this.useLazyPreparation = z2;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        addMediaSources(Arrays.asList(mediaSourceArr));
    }

    public final synchronized void addMediaSources(Collection<MediaSource> collection) {
        addPublicMediaSources(this.mediaSourcesPublic.size(), collection, (Handler) null, (Runnable) null);
    }

    public final synchronized void removeMediaSource(int i) {
        removePublicMediaSources(i, i + 1, (Handler) null, (Runnable) null);
    }

    public final synchronized void removeMediaSourceRange(int i, int i2) {
        removePublicMediaSources(i, i2, (Handler) null, (Runnable) null);
    }

    public final synchronized void clear() {
        removeMediaSourceRange(0, getSize());
    }

    public final synchronized int getSize() {
        return this.mediaSourcesPublic.size();
    }

    public final synchronized void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        this.playbackThreadHandler = new Handler(new ConcatenatingMediaSource$$Lambda$0(this));
        if (this.mediaSourcesPublic.isEmpty()) {
            updateTimelineAndScheduleOnCompletionActions();
        } else {
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(0, this.mediaSourcesPublic.size());
            addMediaSourcesInternal(0, this.mediaSourcesPublic);
            scheduleTimelineUpdate();
        }
    }

    public final MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaSourceHolder mediaSourceHolder = this.mediaSourceByUid.get(getMediaSourceHolderUid(mediaPeriodId.periodUid));
        if (mediaSourceHolder == null) {
            mediaSourceHolder = new MediaSourceHolder(new DummyMediaSource());
            mediaSourceHolder.hasStartedPreparing = true;
        }
        DeferredMediaPeriod deferredMediaPeriod = new DeferredMediaPeriod(mediaSourceHolder.mediaSource, mediaPeriodId, allocator, j);
        this.mediaSourceByMediaPeriod.put(deferredMediaPeriod, mediaSourceHolder);
        mediaSourceHolder.activeMediaPeriods.add(deferredMediaPeriod);
        if (!mediaSourceHolder.hasStartedPreparing) {
            mediaSourceHolder.hasStartedPreparing = true;
            prepareChildSource(mediaSourceHolder, mediaSourceHolder.mediaSource);
        } else if (mediaSourceHolder.isPrepared) {
            deferredMediaPeriod.createPeriod(mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaSourceHolder, mediaPeriodId.periodUid)));
        }
        return deferredMediaPeriod;
    }

    public final void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(mediaPeriod));
        ((DeferredMediaPeriod) mediaPeriod).releasePeriod();
        mediaSourceHolder.activeMediaPeriods.remove(mediaPeriod);
        maybeReleaseChildSource(mediaSourceHolder);
    }

    public final synchronized void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.mediaSourceHolders.clear();
        this.mediaSourceByUid.clear();
        this.shuffleOrder = this.shuffleOrder.cloneAndClear();
        this.windowCount = 0;
        this.periodCount = 0;
        if (this.playbackThreadHandler != null) {
            this.playbackThreadHandler.removeCallbacksAndMessages((Object) null);
            this.playbackThreadHandler = null;
        }
        this.timelineUpdateScheduled = false;
        this.nextTimelineUpdateOnCompletionActions.clear();
        dispatchOnCompletionActions(this.pendingOnCompletionActions);
    }

    /* access modifiers changed from: protected */
    public final void onChildSourceInfoRefreshed(MediaSourceHolder mediaSourceHolder, MediaSource mediaSource, Timeline timeline, Object obj) {
        updateMediaSourceInternal(mediaSourceHolder, timeline);
    }

    /* access modifiers changed from: protected */
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSourceHolder mediaSourceHolder, MediaSource.MediaPeriodId mediaPeriodId) {
        for (int i = 0; i < mediaSourceHolder.activeMediaPeriods.size(); i++) {
            if (mediaSourceHolder.activeMediaPeriods.get(i).id.windowSequenceNumber == mediaPeriodId.windowSequenceNumber) {
                return mediaPeriodId.copyWithPeriodUid(getPeriodUid(mediaSourceHolder, mediaPeriodId.periodUid));
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getWindowIndexForChildWindowIndex(MediaSourceHolder mediaSourceHolder, int i) {
        return i + mediaSourceHolder.firstWindowIndexInChild;
    }

    private void addPublicMediaSources(int i, Collection<MediaSource> collection, Handler handler, Runnable runnable) {
        boolean z = true;
        if ((handler == null) != (runnable == null)) {
            z = false;
        }
        Assertions.checkArgument(z);
        Handler handler2 = this.playbackThreadHandler;
        for (MediaSource checkNotNull : collection) {
            Assertions.checkNotNull(checkNotNull);
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (MediaSource mediaSourceHolder : collection) {
            arrayList.add(new MediaSourceHolder(mediaSourceHolder));
        }
        this.mediaSourcesPublic.addAll(i, arrayList);
        if (handler2 != null && !collection.isEmpty()) {
            handler2.obtainMessage(0, new MessageData(i, arrayList, createOnCompletionAction(handler, runnable))).sendToTarget();
        } else if (runnable != null && handler != null) {
            handler.post(runnable);
        }
    }

    private void removePublicMediaSources(int i, int i2, Handler handler, Runnable runnable) {
        boolean z = false;
        if ((handler == null) == (runnable == null)) {
            z = true;
        }
        Assertions.checkArgument(z);
        Handler handler2 = this.playbackThreadHandler;
        Util.removeRange(this.mediaSourcesPublic, i, i2);
        if (handler2 != null) {
            handler2.obtainMessage(1, new MessageData(i, Integer.valueOf(i2), createOnCompletionAction(handler, runnable))).sendToTarget();
        } else if (runnable != null && handler != null) {
            handler.post(runnable);
        }
    }

    private HandlerAndRunnable createOnCompletionAction(Handler handler, Runnable runnable) {
        if (handler == null || runnable == null) {
            return null;
        }
        HandlerAndRunnable handlerAndRunnable = new HandlerAndRunnable(handler, runnable);
        this.pendingOnCompletionActions.add(handlerAndRunnable);
        return handlerAndRunnable;
    }

    /* access modifiers changed from: private */
    /* renamed from: handleMessage */
    public boolean bridge$lambda$0$ConcatenatingMediaSource(Message message) {
        int i = message.what;
        if (i == 0) {
            MessageData messageData = (MessageData) Util.castNonNull(message.obj);
            this.shuffleOrder = this.shuffleOrder.cloneAndInsert(messageData.index, ((Collection) messageData.customData).size());
            addMediaSourcesInternal(messageData.index, (Collection) messageData.customData);
            scheduleTimelineUpdate(messageData.onCompletionAction);
        } else if (i == 1) {
            MessageData messageData2 = (MessageData) Util.castNonNull(message.obj);
            int i2 = messageData2.index;
            int intValue = ((Integer) messageData2.customData).intValue();
            if (i2 == 0 && intValue == this.shuffleOrder.getLength()) {
                this.shuffleOrder = this.shuffleOrder.cloneAndClear();
            } else {
                this.shuffleOrder = this.shuffleOrder.cloneAndRemove(i2, intValue);
            }
            for (int i3 = intValue - 1; i3 >= i2; i3--) {
                removeMediaSourceInternal(i3);
            }
            scheduleTimelineUpdate(messageData2.onCompletionAction);
        } else if (i == 2) {
            MessageData messageData3 = (MessageData) Util.castNonNull(message.obj);
            ShuffleOrder cloneAndRemove = this.shuffleOrder.cloneAndRemove(messageData3.index, messageData3.index + 1);
            this.shuffleOrder = cloneAndRemove;
            this.shuffleOrder = cloneAndRemove.cloneAndInsert(((Integer) messageData3.customData).intValue(), 1);
            moveMediaSourceInternal(messageData3.index, ((Integer) messageData3.customData).intValue());
            scheduleTimelineUpdate(messageData3.onCompletionAction);
        } else if (i == 3) {
            MessageData messageData4 = (MessageData) Util.castNonNull(message.obj);
            this.shuffleOrder = (ShuffleOrder) messageData4.customData;
            scheduleTimelineUpdate(messageData4.onCompletionAction);
        } else if (i == 4) {
            updateTimelineAndScheduleOnCompletionActions();
        } else if (i == 5) {
            dispatchOnCompletionActions((Set) Util.castNonNull(message.obj));
        } else {
            throw new IllegalStateException();
        }
        return true;
    }

    private void scheduleTimelineUpdate() {
        scheduleTimelineUpdate((HandlerAndRunnable) null);
    }

    private void scheduleTimelineUpdate(HandlerAndRunnable handlerAndRunnable) {
        if (!this.timelineUpdateScheduled) {
            getPlaybackThreadHandlerOnPlaybackThread().obtainMessage(4).sendToTarget();
            this.timelineUpdateScheduled = true;
        }
        if (handlerAndRunnable != null) {
            this.nextTimelineUpdateOnCompletionActions.add(handlerAndRunnable);
        }
    }

    private void updateTimelineAndScheduleOnCompletionActions() {
        this.timelineUpdateScheduled = false;
        Set<HandlerAndRunnable> set = this.nextTimelineUpdateOnCompletionActions;
        this.nextTimelineUpdateOnCompletionActions = new HashSet();
        refreshSourceInfo(new ConcatenatedTimeline(this.mediaSourceHolders, this.windowCount, this.periodCount, this.shuffleOrder, this.isAtomic), (Object) null);
        getPlaybackThreadHandlerOnPlaybackThread().obtainMessage(5, set).sendToTarget();
    }

    private Handler getPlaybackThreadHandlerOnPlaybackThread() {
        return (Handler) Assertions.checkNotNull(this.playbackThreadHandler);
    }

    private synchronized void dispatchOnCompletionActions(Set<HandlerAndRunnable> set) {
        for (HandlerAndRunnable dispatch : set) {
            dispatch.dispatch();
        }
        this.pendingOnCompletionActions.removeAll(set);
    }

    private void addMediaSourcesInternal(int i, Collection<MediaSourceHolder> collection) {
        for (MediaSourceHolder addMediaSourceInternal : collection) {
            addMediaSourceInternal(i, addMediaSourceInternal);
            i++;
        }
    }

    private void addMediaSourceInternal(int i, MediaSourceHolder mediaSourceHolder) {
        if (i > 0) {
            MediaSourceHolder mediaSourceHolder2 = this.mediaSourceHolders.get(i - 1);
            mediaSourceHolder.reset(i, mediaSourceHolder2.firstWindowIndexInChild + mediaSourceHolder2.timeline.getWindowCount(), mediaSourceHolder2.firstPeriodIndexInChild + mediaSourceHolder2.timeline.getPeriodCount());
        } else {
            mediaSourceHolder.reset(i, 0, 0);
        }
        correctOffsets(i, 1, mediaSourceHolder.timeline.getWindowCount(), mediaSourceHolder.timeline.getPeriodCount());
        this.mediaSourceHolders.add(i, mediaSourceHolder);
        this.mediaSourceByUid.put(mediaSourceHolder.uid, mediaSourceHolder);
        if (!this.useLazyPreparation) {
            mediaSourceHolder.hasStartedPreparing = true;
            prepareChildSource(mediaSourceHolder, mediaSourceHolder.mediaSource);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x009c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateMediaSourceInternal(androidx.media2.exoplayer.external.source.ConcatenatingMediaSource.MediaSourceHolder r14, androidx.media2.exoplayer.external.Timeline r15) {
        /*
            r13 = this;
            if (r14 == 0) goto L_0x00b6
            androidx.media2.exoplayer.external.source.ConcatenatingMediaSource$DeferredTimeline r0 = r14.timeline
            androidx.media2.exoplayer.external.Timeline r1 = r0.getTimeline()
            if (r1 != r15) goto L_0x000b
            return
        L_0x000b:
            int r1 = r15.getWindowCount()
            int r2 = r0.getWindowCount()
            int r1 = r1 - r2
            int r2 = r15.getPeriodCount()
            int r3 = r0.getPeriodCount()
            int r2 = r2 - r3
            r3 = 0
            r4 = 1
            if (r1 != 0) goto L_0x0023
            if (r2 == 0) goto L_0x0029
        L_0x0023:
            int r5 = r14.childIndex
            int r5 = r5 + r4
            r13.correctOffsets(r5, r3, r1, r2)
        L_0x0029:
            boolean r1 = r14.isPrepared
            if (r1 == 0) goto L_0x0035
            androidx.media2.exoplayer.external.source.ConcatenatingMediaSource$DeferredTimeline r15 = r0.cloneWithUpdatedTimeline(r15)
            r14.timeline = r15
            goto L_0x00b0
        L_0x0035:
            boolean r0 = r15.isEmpty()
            if (r0 == 0) goto L_0x0046
            java.lang.Object r0 = androidx.media2.exoplayer.external.source.ConcatenatingMediaSource.DeferredTimeline.DUMMY_ID
            androidx.media2.exoplayer.external.source.ConcatenatingMediaSource$DeferredTimeline r15 = androidx.media2.exoplayer.external.source.ConcatenatingMediaSource.DeferredTimeline.createWithRealTimeline(r15, r0)
            r14.timeline = r15
            goto L_0x00b0
        L_0x0046:
            java.util.List<androidx.media2.exoplayer.external.source.DeferredMediaPeriod> r0 = r14.activeMediaPeriods
            int r0 = r0.size()
            if (r0 > r4) goto L_0x0050
            r0 = 1
            goto L_0x0051
        L_0x0050:
            r0 = 0
        L_0x0051:
            androidx.media2.exoplayer.external.util.Assertions.checkState(r0)
            java.util.List<androidx.media2.exoplayer.external.source.DeferredMediaPeriod> r0 = r14.activeMediaPeriods
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x005e
            r0 = 0
            goto L_0x0066
        L_0x005e:
            java.util.List<androidx.media2.exoplayer.external.source.DeferredMediaPeriod> r0 = r14.activeMediaPeriods
            java.lang.Object r0 = r0.get(r3)
            androidx.media2.exoplayer.external.source.DeferredMediaPeriod r0 = (androidx.media2.exoplayer.external.source.DeferredMediaPeriod) r0
        L_0x0066:
            androidx.media2.exoplayer.external.Timeline$Window r1 = r13.window
            r15.getWindow(r3, r1)
            androidx.media2.exoplayer.external.Timeline$Window r1 = r13.window
            long r1 = r1.getDefaultPositionUs()
            if (r0 == 0) goto L_0x007f
            long r5 = r0.getPreparePositionUs()
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x007f
            r11 = r5
            goto L_0x0080
        L_0x007f:
            r11 = r1
        L_0x0080:
            androidx.media2.exoplayer.external.Timeline$Window r8 = r13.window
            androidx.media2.exoplayer.external.Timeline$Period r9 = r13.period
            r10 = 0
            r7 = r15
            android.util.Pair r1 = r7.getPeriodPosition(r8, r9, r10, r11)
            java.lang.Object r2 = r1.first
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r5 = r1.longValue()
            androidx.media2.exoplayer.external.source.ConcatenatingMediaSource$DeferredTimeline r15 = androidx.media2.exoplayer.external.source.ConcatenatingMediaSource.DeferredTimeline.createWithRealTimeline(r15, r2)
            r14.timeline = r15
            if (r0 == 0) goto L_0x00b0
            r0.overridePreparePositionUs(r5)
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r15 = r0.id
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r1 = r0.id
            java.lang.Object r1 = r1.periodUid
            java.lang.Object r1 = getChildPeriodUid(r14, r1)
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r15 = r15.copyWithPeriodUid(r1)
            r0.createPeriod(r15)
        L_0x00b0:
            r14.isPrepared = r4
            r13.scheduleTimelineUpdate()
            return
        L_0x00b6:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            r14.<init>()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.source.ConcatenatingMediaSource.updateMediaSourceInternal(androidx.media2.exoplayer.external.source.ConcatenatingMediaSource$MediaSourceHolder, androidx.media2.exoplayer.external.Timeline):void");
    }

    private void removeMediaSourceInternal(int i) {
        MediaSourceHolder remove = this.mediaSourceHolders.remove(i);
        this.mediaSourceByUid.remove(remove.uid);
        DeferredTimeline deferredTimeline = remove.timeline;
        correctOffsets(i, -1, -deferredTimeline.getWindowCount(), -deferredTimeline.getPeriodCount());
        remove.isRemoved = true;
        maybeReleaseChildSource(remove);
    }

    private void moveMediaSourceInternal(int i, int i2) {
        int min = Math.min(i, i2);
        int max = Math.max(i, i2);
        int i3 = this.mediaSourceHolders.get(min).firstWindowIndexInChild;
        int i4 = this.mediaSourceHolders.get(min).firstPeriodIndexInChild;
        List<MediaSourceHolder> list = this.mediaSourceHolders;
        list.add(i2, list.remove(i));
        while (min <= max) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(min);
            mediaSourceHolder.firstWindowIndexInChild = i3;
            mediaSourceHolder.firstPeriodIndexInChild = i4;
            i3 += mediaSourceHolder.timeline.getWindowCount();
            i4 += mediaSourceHolder.timeline.getPeriodCount();
            min++;
        }
    }

    private void correctOffsets(int i, int i2, int i3, int i4) {
        this.windowCount += i3;
        this.periodCount += i4;
        while (i < this.mediaSourceHolders.size()) {
            this.mediaSourceHolders.get(i).childIndex += i2;
            this.mediaSourceHolders.get(i).firstWindowIndexInChild += i3;
            this.mediaSourceHolders.get(i).firstPeriodIndexInChild += i4;
            i++;
        }
    }

    private void maybeReleaseChildSource(MediaSourceHolder mediaSourceHolder) {
        if (mediaSourceHolder.isRemoved && mediaSourceHolder.hasStartedPreparing && mediaSourceHolder.activeMediaPeriods.isEmpty()) {
            releaseChildSource(mediaSourceHolder);
        }
    }

    private static Object getMediaSourceHolderUid(Object obj) {
        return ConcatenatedTimeline.getChildTimelineUidFromConcatenatedUid(obj);
    }

    private static Object getChildPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        Object childPeriodUidFromConcatenatedUid = ConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(obj);
        return childPeriodUidFromConcatenatedUid.equals(DeferredTimeline.DUMMY_ID) ? mediaSourceHolder.timeline.replacedId : childPeriodUidFromConcatenatedUid;
    }

    private static Object getPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        if (mediaSourceHolder.timeline.replacedId.equals(obj)) {
            obj = DeferredTimeline.DUMMY_ID;
        }
        return ConcatenatedTimeline.getConcatenatedUid(mediaSourceHolder.uid, obj);
    }

    static final class MediaSourceHolder implements Comparable<MediaSourceHolder> {
        public final List<DeferredMediaPeriod> activeMediaPeriods = new ArrayList();
        public int childIndex;
        public int firstPeriodIndexInChild;
        public int firstWindowIndexInChild;
        public boolean hasStartedPreparing;
        public boolean isPrepared;
        public boolean isRemoved;
        public final MediaSource mediaSource;
        public DeferredTimeline timeline;
        public final Object uid = new Object();

        public MediaSourceHolder(MediaSource mediaSource2) {
            this.mediaSource = mediaSource2;
            this.timeline = DeferredTimeline.createWithDummyTimeline(mediaSource2.getTag());
        }

        public void reset(int i, int i2, int i3) {
            this.childIndex = i;
            this.firstWindowIndexInChild = i2;
            this.firstPeriodIndexInChild = i3;
            this.hasStartedPreparing = false;
            this.isPrepared = false;
            this.isRemoved = false;
            this.activeMediaPeriods.clear();
        }

        public int compareTo(MediaSourceHolder mediaSourceHolder) {
            return this.firstPeriodIndexInChild - mediaSourceHolder.firstPeriodIndexInChild;
        }
    }

    private static final class MessageData<T> {
        public final T customData;
        public final int index;
        public final HandlerAndRunnable onCompletionAction;

        public MessageData(int i, T t, HandlerAndRunnable handlerAndRunnable) {
            this.index = i;
            this.customData = t;
            this.onCompletionAction = handlerAndRunnable;
        }
    }

    private static final class ConcatenatedTimeline extends AbstractConcatenatedTimeline {
        private final HashMap<Object, Integer> childIndexByUid = new HashMap<>();
        private final int[] firstPeriodInChildIndices;
        private final int[] firstWindowInChildIndices;
        private final int periodCount;
        private final Timeline[] timelines;
        private final Object[] uids;
        private final int windowCount;

        public ConcatenatedTimeline(Collection<MediaSourceHolder> collection, int i, int i2, ShuffleOrder shuffleOrder, boolean z) {
            super(z, shuffleOrder);
            this.windowCount = i;
            this.periodCount = i2;
            int size = collection.size();
            this.firstPeriodInChildIndices = new int[size];
            this.firstWindowInChildIndices = new int[size];
            this.timelines = new Timeline[size];
            this.uids = new Object[size];
            int i3 = 0;
            for (MediaSourceHolder next : collection) {
                this.timelines[i3] = next.timeline;
                this.firstPeriodInChildIndices[i3] = next.firstPeriodIndexInChild;
                this.firstWindowInChildIndices[i3] = next.firstWindowIndexInChild;
                this.uids[i3] = next.uid;
                this.childIndexByUid.put(this.uids[i3], Integer.valueOf(i3));
                i3++;
            }
        }

        /* access modifiers changed from: protected */
        public int getChildIndexByPeriodIndex(int i) {
            return Util.binarySearchFloor(this.firstPeriodInChildIndices, i + 1, false, false);
        }

        /* access modifiers changed from: protected */
        public int getChildIndexByWindowIndex(int i) {
            return Util.binarySearchFloor(this.firstWindowInChildIndices, i + 1, false, false);
        }

        /* access modifiers changed from: protected */
        public int getChildIndexByChildUid(Object obj) {
            Integer num = this.childIndexByUid.get(obj);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }

        /* access modifiers changed from: protected */
        public Timeline getTimelineByChildIndex(int i) {
            return this.timelines[i];
        }

        /* access modifiers changed from: protected */
        public int getFirstPeriodIndexByChildIndex(int i) {
            return this.firstPeriodInChildIndices[i];
        }

        /* access modifiers changed from: protected */
        public int getFirstWindowIndexByChildIndex(int i) {
            return this.firstWindowInChildIndices[i];
        }

        /* access modifiers changed from: protected */
        public Object getChildUidByChildIndex(int i) {
            return this.uids[i];
        }

        public int getWindowCount() {
            return this.windowCount;
        }

        public int getPeriodCount() {
            return this.periodCount;
        }
    }

    private static final class DeferredTimeline extends ForwardingTimeline {
        /* access modifiers changed from: private */
        public static final Object DUMMY_ID = new Object();
        /* access modifiers changed from: private */
        public final Object replacedId;

        public static DeferredTimeline createWithDummyTimeline(Object obj) {
            return new DeferredTimeline(new DummyTimeline(obj), DUMMY_ID);
        }

        public static DeferredTimeline createWithRealTimeline(Timeline timeline, Object obj) {
            return new DeferredTimeline(timeline, obj);
        }

        private DeferredTimeline(Timeline timeline, Object obj) {
            super(timeline);
            this.replacedId = obj;
        }

        public DeferredTimeline cloneWithUpdatedTimeline(Timeline timeline) {
            return new DeferredTimeline(timeline, this.replacedId);
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(i, period, z);
            if (Util.areEqual(period.uid, this.replacedId)) {
                period.uid = DUMMY_ID;
            }
            return period;
        }

        public int getIndexOfPeriod(Object obj) {
            Timeline timeline = this.timeline;
            if (DUMMY_ID.equals(obj)) {
                obj = this.replacedId;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        public Object getUidOfPeriod(int i) {
            Object uidOfPeriod = this.timeline.getUidOfPeriod(i);
            return Util.areEqual(uidOfPeriod, this.replacedId) ? DUMMY_ID : uidOfPeriod;
        }
    }

    private static final class DummyTimeline extends Timeline {
        private final Object tag;

        public int getPeriodCount() {
            return 1;
        }

        public int getWindowCount() {
            return 1;
        }

        public DummyTimeline(Object obj) {
            this.tag = obj;
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
            return window.set(this.tag, -9223372036854775807L, -9223372036854775807L, false, true, 0, -9223372036854775807L, 0, 0, 0);
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            return period.set(0, DeferredTimeline.DUMMY_ID, 0, -9223372036854775807L, 0);
        }

        public int getIndexOfPeriod(Object obj) {
            return obj == DeferredTimeline.DUMMY_ID ? 0 : -1;
        }

        public Object getUidOfPeriod(int i) {
            return DeferredTimeline.DUMMY_ID;
        }
    }

    private static final class DummyMediaSource extends BaseMediaSource {
        public Object getTag() {
            return null;
        }

        public void maybeThrowSourceInfoRefreshError() throws IOException {
        }

        /* access modifiers changed from: protected */
        public void prepareSourceInternal(TransferListener transferListener) {
        }

        public void releasePeriod(MediaPeriod mediaPeriod) {
        }

        /* access modifiers changed from: protected */
        public void releaseSourceInternal() {
        }

        private DummyMediaSource() {
        }

        public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
            throw new UnsupportedOperationException();
        }
    }

    private static final class HandlerAndRunnable {
        private final Handler handler;
        private final Runnable runnable;

        public HandlerAndRunnable(Handler handler2, Runnable runnable2) {
            this.handler = handler2;
            this.runnable = runnable2;
        }

        public void dispatch() {
            this.handler.post(this.runnable);
        }
    }
}
