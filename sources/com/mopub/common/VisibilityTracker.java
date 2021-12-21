package com.mopub.common;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Views;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class VisibilityTracker {
    static final int NUM_ACCESSES_BEFORE_TRIMMING = 50;
    private static final int VISIBILITY_THROTTLE_MILLIS = 100;
    private long mAccessCounter;
    /* access modifiers changed from: private */
    public boolean mIsVisibilityScheduled;
    final ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    /* access modifiers changed from: private */
    public final Map<View, TrackingInfo> mTrackedViews;
    private final ArrayList<View> mTrimmedViews;
    /* access modifiers changed from: private */
    public final VisibilityChecker mVisibilityChecker;
    private final Handler mVisibilityHandler;
    private final VisibilityRunnable mVisibilityRunnable;
    /* access modifiers changed from: private */
    public VisibilityTrackerListener mVisibilityTrackerListener;
    WeakReference<ViewTreeObserver> mWeakViewTreeObserver;

    public interface VisibilityTrackerListener {
        void onVisibilityChanged(List<View> list, List<View> list2);
    }

    static class TrackingInfo {
        long mAccessOrder;
        int mMaxInvisiblePercent;
        int mMinViewablePercent;
        Integer mMinVisiblePx;
        View mRootView;

        TrackingInfo() {
        }
    }

    public VisibilityTracker(Context context) {
        this(context, new WeakHashMap(10), new VisibilityChecker(), new Handler());
    }

    VisibilityTracker(Context context, Map<View, TrackingInfo> map, VisibilityChecker visibilityChecker, Handler handler) {
        this.mAccessCounter = 0;
        this.mTrackedViews = map;
        this.mVisibilityChecker = visibilityChecker;
        this.mVisibilityHandler = handler;
        this.mVisibilityRunnable = new VisibilityRunnable();
        this.mTrimmedViews = new ArrayList<>(50);
        this.mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                VisibilityTracker.this.scheduleVisibilityCheck();
                return true;
            }
        };
        this.mWeakViewTreeObserver = new WeakReference<>((Object) null);
        setViewTreeObserver(context, (View) null);
    }

    private void setViewTreeObserver(Context context, View view) {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.mWeakViewTreeObserver.get();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            View topmostView = Views.getTopmostView(context, view);
            if (topmostView == null) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Unable to set Visibility Tracker due to no available root view.");
                return;
            }
            ViewTreeObserver viewTreeObserver2 = topmostView.getViewTreeObserver();
            if (!viewTreeObserver2.isAlive()) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, "Visibility Tracker was unable to track views because the root view tree observer was not alive");
                return;
            }
            this.mWeakViewTreeObserver = new WeakReference<>(viewTreeObserver2);
            viewTreeObserver2.addOnPreDrawListener(this.mOnPreDrawListener);
        }
    }

    public void setVisibilityTrackerListener(VisibilityTrackerListener visibilityTrackerListener) {
        this.mVisibilityTrackerListener = visibilityTrackerListener;
    }

    public void addView(View view, int i, Integer num) {
        addView(view, view, i, num);
    }

    public void addView(View view, View view2, int i, Integer num) {
        addView(view, view2, i, i, num);
    }

    public void addView(View view, View view2, int i, int i2, Integer num) {
        setViewTreeObserver(view2.getContext(), view2);
        TrackingInfo trackingInfo = this.mTrackedViews.get(view2);
        if (trackingInfo == null) {
            trackingInfo = new TrackingInfo();
            this.mTrackedViews.put(view2, trackingInfo);
            scheduleVisibilityCheck();
        }
        int min = Math.min(i2, i);
        trackingInfo.mRootView = view;
        trackingInfo.mMinViewablePercent = i;
        trackingInfo.mMaxInvisiblePercent = min;
        trackingInfo.mAccessOrder = this.mAccessCounter;
        trackingInfo.mMinVisiblePx = num;
        long j = this.mAccessCounter + 1;
        this.mAccessCounter = j;
        if (j % 50 == 0) {
            trimTrackedViews(j - 50);
        }
    }

    private void trimTrackedViews(long j) {
        for (Map.Entry next : this.mTrackedViews.entrySet()) {
            if (((TrackingInfo) next.getValue()).mAccessOrder < j) {
                this.mTrimmedViews.add(next.getKey());
            }
        }
        Iterator<View> it = this.mTrimmedViews.iterator();
        while (it.hasNext()) {
            removeView(it.next());
        }
        this.mTrimmedViews.clear();
    }

    public void removeView(View view) {
        this.mTrackedViews.remove(view);
    }

    public void clear() {
        this.mTrackedViews.clear();
        this.mVisibilityHandler.removeMessages(0);
        this.mIsVisibilityScheduled = false;
    }

    public void destroy() {
        clear();
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.mWeakViewTreeObserver.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mWeakViewTreeObserver.clear();
        this.mVisibilityTrackerListener = null;
    }

    public void scheduleVisibilityCheck() {
        if (!this.mIsVisibilityScheduled) {
            this.mIsVisibilityScheduled = true;
            this.mVisibilityHandler.postDelayed(this.mVisibilityRunnable, 100);
        }
    }

    class VisibilityRunnable implements Runnable {
        private final ArrayList<View> mInvisibleViews = new ArrayList<>();
        private final ArrayList<View> mVisibleViews = new ArrayList<>();

        VisibilityRunnable() {
        }

        public void run() {
            boolean unused = VisibilityTracker.this.mIsVisibilityScheduled = false;
            for (Map.Entry entry : VisibilityTracker.this.mTrackedViews.entrySet()) {
                View view = (View) entry.getKey();
                int i = ((TrackingInfo) entry.getValue()).mMinViewablePercent;
                int i2 = ((TrackingInfo) entry.getValue()).mMaxInvisiblePercent;
                Integer num = ((TrackingInfo) entry.getValue()).mMinVisiblePx;
                View view2 = ((TrackingInfo) entry.getValue()).mRootView;
                if (VisibilityTracker.this.mVisibilityChecker.isVisible(view2, view, i, num)) {
                    this.mVisibleViews.add(view);
                } else if (!VisibilityTracker.this.mVisibilityChecker.isVisible(view2, view, i2, (Integer) null)) {
                    this.mInvisibleViews.add(view);
                }
            }
            if (VisibilityTracker.this.mVisibilityTrackerListener != null) {
                VisibilityTracker.this.mVisibilityTrackerListener.onVisibilityChanged(this.mVisibleViews, this.mInvisibleViews);
            }
            this.mVisibleViews.clear();
            this.mInvisibleViews.clear();
        }
    }

    public static class VisibilityChecker {
        private final Rect mClipRect = new Rect();

        public boolean hasRequiredTimeElapsed(long j, int i) {
            return SystemClock.uptimeMillis() - j >= ((long) i);
        }

        public boolean isVisible(View view, View view2, int i, Integer num) {
            if (view2 == null || view2.getVisibility() != 0 || view.getParent() == null || !view2.getGlobalVisibleRect(this.mClipRect)) {
                return false;
            }
            long height = ((long) this.mClipRect.height()) * ((long) this.mClipRect.width());
            long height2 = ((long) view2.getHeight()) * ((long) view2.getWidth());
            if (height2 <= 0) {
                return false;
            }
            if (num == null || num.intValue() <= 0) {
                if (height * 100 >= ((long) i) * height2) {
                    return true;
                }
                return false;
            } else if (height >= ((long) num.intValue())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
