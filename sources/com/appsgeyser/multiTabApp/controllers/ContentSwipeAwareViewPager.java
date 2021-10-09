package com.appsgeyser.multiTabApp.controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class ContentSwipeAwareViewPager extends ViewPager {
    private boolean enabled = true;

    public ContentSwipeAwareViewPager(Context context) {
        super(context);
    }

    public ContentSwipeAwareViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (getChildCount() == 1 || !this.enabled) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getChildCount() == 1 || !this.enabled) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setPagingEnabled(boolean z) {
        this.enabled = z;
    }
}
