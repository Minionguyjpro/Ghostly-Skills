package com.mopub.mobileads;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class ViewGestureDetector extends GestureDetector {
    private GestureListener mGestureListener;

    public ViewGestureDetector(Context context) {
        this(context, new GestureListener());
    }

    private ViewGestureDetector(Context context, GestureListener gestureListener) {
        super(context, gestureListener);
        this.mGestureListener = gestureListener;
        setIsLongpressEnabled(false);
    }

    /* access modifiers changed from: package-private */
    public void onResetUserClick() {
        this.mGestureListener.onResetUserClick();
    }

    public boolean isClicked() {
        return this.mGestureListener.isClicked();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void setGestureListener(GestureListener gestureListener) {
        this.mGestureListener = gestureListener;
    }

    public void setClicked(boolean z) {
        this.mGestureListener.mIsClicked = z;
    }

    static class GestureListener extends GestureDetector.SimpleOnGestureListener {
        boolean mIsClicked = false;

        GestureListener() {
        }

        /* access modifiers changed from: package-private */
        public void onResetUserClick() {
            this.mIsClicked = false;
        }

        /* access modifiers changed from: package-private */
        public boolean isClicked() {
            return this.mIsClicked;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            this.mIsClicked = true;
            return super.onSingleTapUp(motionEvent);
        }
    }
}
