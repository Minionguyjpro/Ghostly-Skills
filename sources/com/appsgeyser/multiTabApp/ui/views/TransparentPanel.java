package com.appsgeyser.multiTabApp.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class TransparentPanel extends RelativeLayout {
    private Paint borderPaint;
    private Paint innerPaint;

    public TransparentPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public TransparentPanel(Context context) {
        super(context);
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.innerPaint = paint;
        paint.setARGB(225, 75, 75, 75);
        this.innerPaint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.borderPaint = paint2;
        paint2.setARGB(255, 160, 160, 160);
        this.borderPaint.setAntiAlias(true);
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setStrokeWidth(2.0f);
    }

    public void setInnerPaint(Paint paint) {
        this.innerPaint = paint;
    }

    public void setBorderPaint(Paint paint) {
        this.borderPaint = paint;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        RectF rectF = new RectF();
        rectF.set(0.0f, 0.0f, (float) getMeasuredWidth(), (float) getMeasuredHeight());
        canvas.drawRoundRect(rectF, 10.0f, 10.0f, this.innerPaint);
        canvas.drawRoundRect(rectF, 10.0f, 10.0f, this.borderPaint);
        super.dispatchDraw(canvas);
    }
}
