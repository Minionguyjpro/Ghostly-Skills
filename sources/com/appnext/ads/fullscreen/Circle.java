package com.appnext.ads.fullscreen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.appnext.core.f;

public class Circle extends View {
    private static final int au = 180;
    private Paint av;
    private RectF aw;
    private float ax;

    public Circle(Context context) {
        super(context);
        init(context);
    }

    public Circle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.aw, 180.0f, this.ax, false, this.av);
    }

    public float getAngle() {
        return this.ax;
    }

    public void setAngle(float f) {
        this.ax = f;
    }

    private static int a(Context context, float f) {
        return f.a(context, f);
    }

    private void init(Context context) {
        float a2 = (float) f.a(context, 5.0f);
        Paint paint = new Paint();
        this.av = paint;
        paint.setAntiAlias(true);
        this.av.setStyle(Paint.Style.STROKE);
        this.av.setStrokeWidth(a2);
        this.av.setColor(-1);
        this.av.setShadowLayer(2.0f, 2.0f, 2.0f, Color.argb(128, 0, 0, 0));
        setLayerType(1, this.av);
        this.aw = new RectF(a2, a2, ((float) f.a(context, 20.0f)) + a2, ((float) f.a(context, 20.0f)) + a2);
        this.ax = 360.0f;
    }
}
