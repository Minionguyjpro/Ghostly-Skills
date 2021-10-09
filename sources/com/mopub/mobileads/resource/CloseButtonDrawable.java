package com.mopub.mobileads.resource;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.mopub.mobileads.resource.DrawableConstants;

public class CloseButtonDrawable extends BaseWidgetDrawable {
    private final Paint closeButtonPaint;
    private final float halfStrokeWidth;

    public CloseButtonDrawable() {
        this(8.0f);
    }

    public CloseButtonDrawable(float f) {
        this.halfStrokeWidth = f / 2.0f;
        Paint paint = new Paint();
        this.closeButtonPaint = paint;
        paint.setColor(-1);
        this.closeButtonPaint.setStrokeWidth(f);
        this.closeButtonPaint.setStrokeCap(DrawableConstants.CloseButton.STROKE_CAP);
    }

    public void draw(Canvas canvas) {
        int width = getBounds().width();
        int height = getBounds().height();
        float f = this.halfStrokeWidth;
        float f2 = (float) height;
        float f3 = (float) width;
        Canvas canvas2 = canvas;
        canvas2.drawLine(f + 0.0f, f2 - f, f3 - f, f + 0.0f, this.closeButtonPaint);
        float f4 = this.halfStrokeWidth;
        canvas2.drawLine(f4 + 0.0f, f4 + 0.0f, f3 - f4, f2 - f4, this.closeButtonPaint);
    }
}
