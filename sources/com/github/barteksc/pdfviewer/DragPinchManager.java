package com.github.barteksc.pdfviewer;

import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;

class DragPinchManager implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {
    private AnimationManager animationManager;
    private GestureDetector gestureDetector;
    private boolean isSwipeEnabled;
    private PDFView pdfView;
    private ScaleGestureDetector scaleGestureDetector;
    private boolean scrolling = false;
    private boolean swipeVertical;

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector2) {
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public DragPinchManager(PDFView pDFView, AnimationManager animationManager2) {
        this.pdfView = pDFView;
        this.animationManager = animationManager2;
        this.isSwipeEnabled = false;
        this.swipeVertical = pDFView.isSwipeVertical();
        this.gestureDetector = new GestureDetector(pDFView.getContext(), this);
        this.scaleGestureDetector = new ScaleGestureDetector(pDFView.getContext(), this);
        pDFView.setOnTouchListener(this);
    }

    public void enableDoubletap(boolean z) {
        if (z) {
            this.gestureDetector.setOnDoubleTapListener(this);
        } else {
            this.gestureDetector.setOnDoubleTapListener((GestureDetector.OnDoubleTapListener) null);
        }
    }

    public boolean isZooming() {
        return this.pdfView.isZooming();
    }

    public void setSwipeEnabled(boolean z) {
        this.isSwipeEnabled = z;
    }

    public void setSwipeVertical(boolean z) {
        this.swipeVertical = z;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        ScrollHandle scrollHandle = this.pdfView.getScrollHandle();
        if (scrollHandle != null && !this.pdfView.documentFitsView()) {
            if (!scrollHandle.shown()) {
                scrollHandle.show();
            } else {
                scrollHandle.hide();
            }
        }
        this.pdfView.performClick();
        return true;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.pdfView.getZoom() < this.pdfView.getMidZoom()) {
            this.pdfView.zoomWithAnimation(motionEvent.getX(), motionEvent.getY(), this.pdfView.getMidZoom());
            return true;
        } else if (this.pdfView.getZoom() < this.pdfView.getMaxZoom()) {
            this.pdfView.zoomWithAnimation(motionEvent.getX(), motionEvent.getY(), this.pdfView.getMaxZoom());
            return true;
        } else {
            this.pdfView.resetZoomWithAnimation();
            return true;
        }
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.animationManager.stopFling();
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.scrolling = true;
        if (isZooming() || this.isSwipeEnabled) {
            this.pdfView.moveRelativeTo(-f, -f2);
        }
        this.pdfView.loadPageByOffset();
        return true;
    }

    public void onScrollEnd(MotionEvent motionEvent) {
        this.pdfView.loadPages();
        hideHandle();
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        int i;
        int currentXOffset = (int) this.pdfView.getCurrentXOffset();
        int currentYOffset = (int) this.pdfView.getCurrentYOffset();
        AnimationManager animationManager2 = this.animationManager;
        int i2 = (int) f;
        int i3 = (int) f2;
        int i4 = 2;
        if (this.swipeVertical) {
            i = 2;
        } else {
            i = this.pdfView.getPageCount();
        }
        int i5 = currentXOffset * i;
        if (this.swipeVertical) {
            i4 = this.pdfView.getPageCount();
        }
        animationManager2.startFlingAnimation(currentXOffset, currentYOffset, i2, i3, i5, 0, currentYOffset * i4, 0);
        return true;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector2) {
        float zoom;
        float scaleFactor = scaleGestureDetector2.getScaleFactor();
        float zoom2 = this.pdfView.getZoom() * scaleFactor;
        float f = 1.0f;
        if (zoom2 < 1.0f) {
            zoom = this.pdfView.getZoom();
        } else {
            f = 10.0f;
            if (zoom2 > 10.0f) {
                zoom = this.pdfView.getZoom();
            }
            this.pdfView.zoomCenteredRelativeTo(scaleFactor, new PointF(scaleGestureDetector2.getFocusX(), scaleGestureDetector2.getFocusY()));
            return true;
        }
        scaleFactor = f / zoom;
        this.pdfView.zoomCenteredRelativeTo(scaleFactor, new PointF(scaleGestureDetector2.getFocusX(), scaleGestureDetector2.getFocusY()));
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector2) {
        this.pdfView.loadPages();
        hideHandle();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = this.gestureDetector.onTouchEvent(motionEvent) || this.scaleGestureDetector.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 1 && this.scrolling) {
            this.scrolling = false;
            onScrollEnd(motionEvent);
        }
        return z;
    }

    private void hideHandle() {
        if (this.pdfView.getScrollHandle() != null && this.pdfView.getScrollHandle().shown()) {
            this.pdfView.getScrollHandle().hideDelayed();
        }
    }
}
