package com.github.barteksc.pdfviewer.scroll;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.R;
import com.github.barteksc.pdfviewer.util.Util;
import com.mopub.mobileads.resource.DrawableConstants;

public class DefaultScrollHandle extends RelativeLayout implements ScrollHandle {
    protected Context context;
    private float currentPos;
    private Handler handler;
    private Runnable hidePageScrollerRunnable;
    private boolean inverted;
    private PDFView pdfView;
    private float relativeHandlerMiddle;
    protected TextView textView;

    public DefaultScrollHandle(Context context2) {
        this(context2, false);
    }

    public DefaultScrollHandle(Context context2, boolean z) {
        super(context2);
        this.relativeHandlerMiddle = 0.0f;
        this.handler = new Handler();
        this.hidePageScrollerRunnable = new Runnable() {
            public void run() {
                DefaultScrollHandle.this.hide();
            }
        };
        this.context = context2;
        this.inverted = z;
        this.textView = new TextView(context2);
        setVisibility(4);
        setTextColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
        setTextSize(16);
    }

    public void setupLayout(PDFView pDFView) {
        Drawable drawable;
        int i;
        int i2 = 65;
        int i3 = 40;
        if (!pDFView.isSwipeVertical()) {
            if (this.inverted) {
                i = 10;
                drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_top);
            } else {
                i = 12;
                drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_bottom);
            }
            i2 = 40;
            i3 = 65;
        } else if (this.inverted) {
            i = 9;
            drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_left);
        } else {
            i = 11;
            drawable = ContextCompat.getDrawable(this.context, R.drawable.default_scroll_handle_right);
        }
        if (Build.VERSION.SDK_INT < 16) {
            setBackgroundDrawable(drawable);
        } else {
            setBackground(drawable);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(Util.getDP(this.context, i2), Util.getDP(this.context, i3));
        layoutParams.setMargins(0, 0, 0, 0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        addView(this.textView, layoutParams2);
        layoutParams.addRule(i);
        pDFView.addView(this, layoutParams);
        this.pdfView = pDFView;
    }

    public void destroyLayout() {
        this.pdfView.removeView(this);
    }

    public void setScroll(float f) {
        if (!shown()) {
            show();
        } else {
            this.handler.removeCallbacks(this.hidePageScrollerRunnable);
        }
        setPosition(((float) (this.pdfView.isSwipeVertical() ? this.pdfView.getHeight() : this.pdfView.getWidth())) * f);
    }

    private void setPosition(float f) {
        int i;
        if (!Float.isInfinite(f) && !Float.isNaN(f)) {
            if (this.pdfView.isSwipeVertical()) {
                i = this.pdfView.getHeight();
            } else {
                i = this.pdfView.getWidth();
            }
            float f2 = (float) i;
            float f3 = f - this.relativeHandlerMiddle;
            if (f3 < 0.0f) {
                f3 = 0.0f;
            } else if (f3 > f2 - ((float) Util.getDP(this.context, 40))) {
                f3 = f2 - ((float) Util.getDP(this.context, 40));
            }
            if (this.pdfView.isSwipeVertical()) {
                setY(f3);
            } else {
                setX(f3);
            }
            calculateMiddle();
            invalidate();
        }
    }

    private void calculateMiddle() {
        int i;
        float f;
        float f2;
        if (this.pdfView.isSwipeVertical()) {
            f2 = getY();
            f = (float) getHeight();
            i = this.pdfView.getHeight();
        } else {
            f2 = getX();
            f = (float) getWidth();
            i = this.pdfView.getWidth();
        }
        this.relativeHandlerMiddle = ((f2 + this.relativeHandlerMiddle) / ((float) i)) * f;
    }

    public void hideDelayed() {
        this.handler.postDelayed(this.hidePageScrollerRunnable, 1000);
    }

    public void setPageNum(int i) {
        String valueOf = String.valueOf(i);
        if (!this.textView.getText().equals(valueOf)) {
            this.textView.setText(valueOf);
        }
    }

    public boolean shown() {
        return getVisibility() == 0;
    }

    public void show() {
        setVisibility(0);
    }

    public void hide() {
        setVisibility(4);
    }

    public void setTextColor(int i) {
        this.textView.setTextColor(i);
    }

    public void setTextSize(int i) {
        this.textView.setTextSize(1, (float) i);
    }

    private boolean isPDFViewReady() {
        PDFView pDFView = this.pdfView;
        return pDFView != null && pDFView.getPageCount() > 0 && !this.pdfView.documentFitsView();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.isPDFViewReady()
            if (r0 != 0) goto L_0x000b
            boolean r5 = super.onTouchEvent(r5)
            return r5
        L_0x000b:
            int r0 = r5.getAction()
            r1 = 1
            if (r0 == 0) goto L_0x0029
            if (r0 == r1) goto L_0x0025
            r2 = 2
            if (r0 == r2) goto L_0x0054
            r2 = 3
            if (r0 == r2) goto L_0x0025
            r2 = 5
            if (r0 == r2) goto L_0x0029
            r2 = 6
            if (r0 == r2) goto L_0x0025
            boolean r5 = super.onTouchEvent(r5)
            return r5
        L_0x0025:
            r4.hideDelayed()
            return r1
        L_0x0029:
            com.github.barteksc.pdfviewer.PDFView r0 = r4.pdfView
            r0.stopFling()
            android.os.Handler r0 = r4.handler
            java.lang.Runnable r2 = r4.hidePageScrollerRunnable
            r0.removeCallbacks(r2)
            com.github.barteksc.pdfviewer.PDFView r0 = r4.pdfView
            boolean r0 = r0.isSwipeVertical()
            if (r0 == 0) goto L_0x0049
            float r0 = r5.getRawY()
            float r2 = r4.getY()
            float r0 = r0 - r2
            r4.currentPos = r0
            goto L_0x0054
        L_0x0049:
            float r0 = r5.getRawX()
            float r2 = r4.getX()
            float r0 = r0 - r2
            r4.currentPos = r0
        L_0x0054:
            com.github.barteksc.pdfviewer.PDFView r0 = r4.pdfView
            boolean r0 = r0.isSwipeVertical()
            r2 = 0
            if (r0 == 0) goto L_0x0078
            float r5 = r5.getRawY()
            float r0 = r4.currentPos
            float r5 = r5 - r0
            float r0 = r4.relativeHandlerMiddle
            float r5 = r5 + r0
            r4.setPosition(r5)
            com.github.barteksc.pdfviewer.PDFView r5 = r4.pdfView
            float r0 = r4.relativeHandlerMiddle
            int r3 = r4.getHeight()
            float r3 = (float) r3
            float r0 = r0 / r3
            r5.setPositionOffset(r0, r2)
            goto L_0x0092
        L_0x0078:
            float r5 = r5.getRawX()
            float r0 = r4.currentPos
            float r5 = r5 - r0
            float r0 = r4.relativeHandlerMiddle
            float r5 = r5 + r0
            r4.setPosition(r5)
            com.github.barteksc.pdfviewer.PDFView r5 = r4.pdfView
            float r0 = r4.relativeHandlerMiddle
            int r3 = r4.getWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            r5.setPositionOffset(r0, r2)
        L_0x0092:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
