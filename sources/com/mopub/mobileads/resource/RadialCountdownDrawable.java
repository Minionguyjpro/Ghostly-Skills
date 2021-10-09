package com.mopub.mobileads.resource;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Numbers;
import com.mopub.mobileads.resource.DrawableConstants;

public class RadialCountdownDrawable extends BaseWidgetDrawable {
    private final Paint mArcPaint;
    private final Paint mCirclePaint;
    private int mInitialCountdownMilliseconds;
    private int mSecondsRemaining;
    private float mSweepAngle;
    private final Paint mTextPaint;
    private Rect mTextRect = new Rect();

    public RadialCountdownDrawable(Context context) {
        int dipsToIntPixels = Dips.dipsToIntPixels(3.0f, context);
        float dipsToFloatPixels = Dips.dipsToFloatPixels(18.0f, context);
        Paint paint = new Paint();
        this.mCirclePaint = paint;
        paint.setColor(-1);
        this.mCirclePaint.setAlpha(128);
        this.mCirclePaint.setStyle(DrawableConstants.RadialCountdown.BACKGROUND_STYLE);
        float f = (float) dipsToIntPixels;
        this.mCirclePaint.setStrokeWidth(f);
        this.mCirclePaint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.mArcPaint = paint2;
        paint2.setColor(-1);
        this.mArcPaint.setAlpha(255);
        this.mArcPaint.setStyle(DrawableConstants.RadialCountdown.PROGRESS_STYLE);
        this.mArcPaint.setStrokeWidth(f);
        this.mArcPaint.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.mTextPaint = paint3;
        paint3.setColor(-1);
        this.mTextPaint.setTextAlign(DrawableConstants.RadialCountdown.TEXT_ALIGN);
        this.mTextPaint.setTextSize(dipsToFloatPixels);
        this.mTextPaint.setAntiAlias(true);
    }

    public void draw(Canvas canvas) {
        int centerX = getBounds().centerX();
        int centerY = getBounds().centerY();
        canvas.drawCircle((float) centerX, (float) centerY, (float) Math.min(centerX, centerY), this.mCirclePaint);
        drawTextWithinBounds(canvas, this.mTextPaint, this.mTextRect, String.valueOf(this.mSecondsRemaining));
        canvas.drawArc(new RectF(getBounds()), -90.0f, this.mSweepAngle, false, this.mArcPaint);
    }

    public void setInitialCountdown(int i) {
        this.mInitialCountdownMilliseconds = i;
    }

    public void updateCountdownProgress(int i) {
        this.mSecondsRemaining = (int) Numbers.convertMillisecondsToSecondsRoundedUp((long) (this.mInitialCountdownMilliseconds - i));
        this.mSweepAngle = (((float) i) * 360.0f) / ((float) this.mInitialCountdownMilliseconds);
        invalidateSelf();
    }

    @Deprecated
    public int getInitialCountdownMilliseconds() {
        return this.mInitialCountdownMilliseconds;
    }
}
