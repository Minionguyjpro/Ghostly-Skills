package com.mopub.mobileads;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Views;
import java.lang.ref.WeakReference;

class BannerVisibilityTracker {
    private static final int VISIBILITY_THROTTLE_MILLIS = 100;
    /* access modifiers changed from: private */
    public BannerVisibilityTrackerListener mBannerVisibilityTrackerListener;
    /* access modifiers changed from: private */
    public boolean mIsImpTrackerFired;
    /* access modifiers changed from: private */
    public boolean mIsVisibilityScheduled;
    final ViewTreeObserver.OnPreDrawListener mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() {
        public boolean onPreDraw() {
            BannerVisibilityTracker.this.scheduleVisibilityCheck();
            return true;
        }
    };
    /* access modifiers changed from: private */
    public final View mRootView;
    /* access modifiers changed from: private */
    public final View mTrackedView;
    /* access modifiers changed from: private */
    public final BannerVisibilityChecker mVisibilityChecker;
    private final Handler mVisibilityHandler = new Handler();
    private final BannerVisibilityRunnable mVisibilityRunnable = new BannerVisibilityRunnable();
    WeakReference<ViewTreeObserver> mWeakViewTreeObserver = new WeakReference<>((Object) null);

    interface BannerVisibilityTrackerListener {
        void onVisibilityChanged();
    }

    public BannerVisibilityTracker(Context context, View view, View view2, int i, int i2) {
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(view2);
        this.mRootView = view;
        this.mTrackedView = view2;
        this.mVisibilityChecker = new BannerVisibilityChecker(i, i2);
        setViewTreeObserver(context, this.mTrackedView);
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

    /* access modifiers changed from: package-private */
    @Deprecated
    public BannerVisibilityTrackerListener getBannerVisibilityTrackerListener() {
        return this.mBannerVisibilityTrackerListener;
    }

    /* access modifiers changed from: package-private */
    public void setBannerVisibilityTrackerListener(BannerVisibilityTrackerListener bannerVisibilityTrackerListener) {
        this.mBannerVisibilityTrackerListener = bannerVisibilityTrackerListener;
    }

    /* access modifiers changed from: package-private */
    public void destroy() {
        this.mVisibilityHandler.removeMessages(0);
        this.mIsVisibilityScheduled = false;
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.mWeakViewTreeObserver.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mWeakViewTreeObserver.clear();
        this.mBannerVisibilityTrackerListener = null;
    }

    /* access modifiers changed from: package-private */
    public void scheduleVisibilityCheck() {
        if (!this.mIsVisibilityScheduled) {
            this.mIsVisibilityScheduled = true;
            this.mVisibilityHandler.postDelayed(this.mVisibilityRunnable, 100);
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public BannerVisibilityChecker getBannerVisibilityChecker() {
        return this.mVisibilityChecker;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public Handler getVisibilityHandler() {
        return this.mVisibilityHandler;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean isVisibilityScheduled() {
        return this.mIsVisibilityScheduled;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public boolean isImpTrackerFired() {
        return this.mIsImpTrackerFired;
    }

    class BannerVisibilityRunnable implements Runnable {
        BannerVisibilityRunnable() {
        }

        public void run() {
            if (!BannerVisibilityTracker.this.mIsImpTrackerFired) {
                boolean unused = BannerVisibilityTracker.this.mIsVisibilityScheduled = false;
                if (BannerVisibilityTracker.this.mVisibilityChecker.isVisible(BannerVisibilityTracker.this.mRootView, BannerVisibilityTracker.this.mTrackedView)) {
                    if (!BannerVisibilityTracker.this.mVisibilityChecker.hasBeenVisibleYet()) {
                        BannerVisibilityTracker.this.mVisibilityChecker.setStartTimeMillis();
                    }
                    if (BannerVisibilityTracker.this.mVisibilityChecker.hasRequiredTimeElapsed() && BannerVisibilityTracker.this.mBannerVisibilityTrackerListener != null) {
                        BannerVisibilityTracker.this.mBannerVisibilityTrackerListener.onVisibilityChanged();
                        boolean unused2 = BannerVisibilityTracker.this.mIsImpTrackerFired = true;
                    }
                }
                if (!BannerVisibilityTracker.this.mIsImpTrackerFired) {
                    BannerVisibilityTracker.this.scheduleVisibilityCheck();
                }
            }
        }
    }

    static class BannerVisibilityChecker {
        private final Rect mClipRect = new Rect();
        private int mMinVisibleDips;
        private int mMinVisibleMillis;
        private long mStartTimeMillis = Long.MIN_VALUE;

        BannerVisibilityChecker(int i, int i2) {
            this.mMinVisibleDips = i;
            this.mMinVisibleMillis = i2;
        }

        /* access modifiers changed from: package-private */
        public boolean hasBeenVisibleYet() {
            return this.mStartTimeMillis != Long.MIN_VALUE;
        }

        /* access modifiers changed from: package-private */
        public void setStartTimeMillis() {
            this.mStartTimeMillis = SystemClock.uptimeMillis();
        }

        /* access modifiers changed from: package-private */
        public boolean hasRequiredTimeElapsed() {
            if (hasBeenVisibleYet() && SystemClock.uptimeMillis() - this.mStartTimeMillis >= ((long) this.mMinVisibleMillis)) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean isVisible(View view, View view2) {
            if (view2 == null || view2.getVisibility() != 0 || view.getParent() == null || view2.getWidth() <= 0 || view2.getHeight() <= 0 || !view2.getGlobalVisibleRect(this.mClipRect) || ((long) (Dips.pixelsToIntDips((float) this.mClipRect.width(), view2.getContext()) * Dips.pixelsToIntDips((float) this.mClipRect.height(), view2.getContext()))) < ((long) this.mMinVisibleDips)) {
                return false;
            }
            return true;
        }
    }
}
