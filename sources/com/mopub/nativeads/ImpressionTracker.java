package com.mopub.nativeads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.mopub.common.VisibilityTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class ImpressionTracker {
    private static final int PERIOD = 250;
    private final Handler mPollHandler;
    private final PollingRunnable mPollingRunnable;
    /* access modifiers changed from: private */
    public final Map<View, TimestampWrapper<ImpressionInterface>> mPollingViews;
    /* access modifiers changed from: private */
    public final Map<View, ImpressionInterface> mTrackedViews;
    /* access modifiers changed from: private */
    public final VisibilityTracker.VisibilityChecker mVisibilityChecker;
    private final VisibilityTracker mVisibilityTracker;
    private VisibilityTracker.VisibilityTrackerListener mVisibilityTrackerListener;

    public ImpressionTracker(Context context) {
        this(new WeakHashMap(), new WeakHashMap(), new VisibilityTracker.VisibilityChecker(), new VisibilityTracker(context), new Handler(Looper.getMainLooper()));
    }

    ImpressionTracker(Map<View, ImpressionInterface> map, Map<View, TimestampWrapper<ImpressionInterface>> map2, VisibilityTracker.VisibilityChecker visibilityChecker, VisibilityTracker visibilityTracker, Handler handler) {
        this.mTrackedViews = map;
        this.mPollingViews = map2;
        this.mVisibilityChecker = visibilityChecker;
        this.mVisibilityTracker = visibilityTracker;
        AnonymousClass1 r1 = new VisibilityTracker.VisibilityTrackerListener() {
            public void onVisibilityChanged(List<View> list, List<View> list2) {
                for (View next : list) {
                    ImpressionInterface impressionInterface = (ImpressionInterface) ImpressionTracker.this.mTrackedViews.get(next);
                    if (impressionInterface == null) {
                        ImpressionTracker.this.removeView(next);
                    } else {
                        TimestampWrapper timestampWrapper = (TimestampWrapper) ImpressionTracker.this.mPollingViews.get(next);
                        if (timestampWrapper == null || !impressionInterface.equals(timestampWrapper.mInstance)) {
                            ImpressionTracker.this.mPollingViews.put(next, new TimestampWrapper(impressionInterface));
                        }
                    }
                }
                for (View remove : list2) {
                    ImpressionTracker.this.mPollingViews.remove(remove);
                }
                ImpressionTracker.this.scheduleNextPoll();
            }
        };
        this.mVisibilityTrackerListener = r1;
        this.mVisibilityTracker.setVisibilityTrackerListener(r1);
        this.mPollHandler = handler;
        this.mPollingRunnable = new PollingRunnable();
    }

    public void addView(View view, ImpressionInterface impressionInterface) {
        if (this.mTrackedViews.get(view) != impressionInterface) {
            removeView(view);
            if (!impressionInterface.isImpressionRecorded()) {
                this.mTrackedViews.put(view, impressionInterface);
                this.mVisibilityTracker.addView(view, impressionInterface.getImpressionMinPercentageViewed(), impressionInterface.getImpressionMinVisiblePx());
            }
        }
    }

    public void removeView(View view) {
        this.mTrackedViews.remove(view);
        removePollingView(view);
        this.mVisibilityTracker.removeView(view);
    }

    public void clear() {
        this.mTrackedViews.clear();
        this.mPollingViews.clear();
        this.mVisibilityTracker.clear();
        this.mPollHandler.removeMessages(0);
    }

    public void destroy() {
        clear();
        this.mVisibilityTracker.destroy();
        this.mVisibilityTrackerListener = null;
    }

    /* access modifiers changed from: package-private */
    public void scheduleNextPoll() {
        if (!this.mPollHandler.hasMessages(0)) {
            this.mPollHandler.postDelayed(this.mPollingRunnable, 250);
        }
    }

    private void removePollingView(View view) {
        this.mPollingViews.remove(view);
    }

    class PollingRunnable implements Runnable {
        private final ArrayList<View> mRemovedViews = new ArrayList<>();

        PollingRunnable() {
        }

        public void run() {
            for (Map.Entry entry : ImpressionTracker.this.mPollingViews.entrySet()) {
                View view = (View) entry.getKey();
                TimestampWrapper timestampWrapper = (TimestampWrapper) entry.getValue();
                if (ImpressionTracker.this.mVisibilityChecker.hasRequiredTimeElapsed(timestampWrapper.mCreatedTimestamp, ((ImpressionInterface) timestampWrapper.mInstance).getImpressionMinTimeViewed())) {
                    ((ImpressionInterface) timestampWrapper.mInstance).recordImpression(view);
                    ((ImpressionInterface) timestampWrapper.mInstance).setImpressionRecorded();
                    this.mRemovedViews.add(view);
                }
            }
            Iterator<View> it = this.mRemovedViews.iterator();
            while (it.hasNext()) {
                ImpressionTracker.this.removeView(it.next());
            }
            this.mRemovedViews.clear();
            if (!ImpressionTracker.this.mPollingViews.isEmpty()) {
                ImpressionTracker.this.scheduleNextPoll();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public VisibilityTracker.VisibilityTrackerListener getVisibilityTrackerListener() {
        return this.mVisibilityTrackerListener;
    }
}
