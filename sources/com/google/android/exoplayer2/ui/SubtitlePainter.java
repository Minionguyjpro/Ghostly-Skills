package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.mopub.mobileads.resource.DrawableConstants;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

final class SubtitlePainter {
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private int backgroundColor;
    private Rect bitmapRect;
    private float bottomPaddingFraction;
    private Bitmap cueBitmap;
    private float cueBitmapHeight;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    private float cueSize;
    private CharSequence cueText;
    private Layout.Alignment cueTextAlignment;
    private float cueTextSizePx;
    private float defaultTextSizePx;
    private int edgeColor;
    private int edgeType;
    private int foregroundColor;
    private final float outlineWidth;
    private final Paint paint;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint;
    private int textTop;
    private int windowColor;

    public SubtitlePainter(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, new int[]{16843287, 16843288}, 0, 0);
        this.spacingAdd = (float) obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
        float round = (float) Math.round((((float) context.getResources().getDisplayMetrics().densityDpi) * 2.0f) / 160.0f);
        this.outlineWidth = round;
        this.shadowRadius = round;
        this.shadowOffset = round;
        TextPaint textPaint2 = new TextPaint();
        this.textPaint = textPaint2;
        textPaint2.setAntiAlias(true);
        this.textPaint.setSubpixelText(true);
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL);
    }

    public void draw(Cue cue, boolean z, boolean z2, CaptionStyleCompat captionStyleCompat, float f, float f2, float f3, Canvas canvas, int i, int i2, int i3, int i4) {
        Cue cue2 = cue;
        boolean z3 = z;
        boolean z4 = z2;
        CaptionStyleCompat captionStyleCompat2 = captionStyleCompat;
        float f4 = f;
        float f5 = f2;
        float f6 = f3;
        Canvas canvas2 = canvas;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        boolean z5 = cue2.bitmap == null;
        int i9 = DrawableConstants.CtaButton.BACKGROUND_COLOR;
        if (z5) {
            if (!TextUtils.isEmpty(cue2.text)) {
                i9 = (!cue2.windowColorSet || !z3) ? captionStyleCompat2.windowColor : cue2.windowColor;
            } else {
                return;
            }
        }
        if (areCharSequencesEqual(this.cueText, cue2.text) && Util.areEqual(this.cueTextAlignment, cue2.textAlignment) && this.cueBitmap == cue2.bitmap && this.cueLine == cue2.line && this.cueLineType == cue2.lineType && Util.areEqual(Integer.valueOf(this.cueLineAnchor), Integer.valueOf(cue2.lineAnchor)) && this.cuePosition == cue2.position && Util.areEqual(Integer.valueOf(this.cuePositionAnchor), Integer.valueOf(cue2.positionAnchor)) && this.cueSize == cue2.size && this.cueBitmapHeight == cue2.bitmapHeight && this.applyEmbeddedStyles == z3 && this.applyEmbeddedFontSizes == z4 && this.foregroundColor == captionStyleCompat2.foregroundColor && this.backgroundColor == captionStyleCompat2.backgroundColor && this.windowColor == i9 && this.edgeType == captionStyleCompat2.edgeType && this.edgeColor == captionStyleCompat2.edgeColor && Util.areEqual(this.textPaint.getTypeface(), captionStyleCompat2.typeface) && this.defaultTextSizePx == f4 && this.cueTextSizePx == f5 && this.bottomPaddingFraction == f6 && this.parentLeft == i5 && this.parentTop == i6 && this.parentRight == i7 && this.parentBottom == i8) {
            drawLayout(canvas, z5);
            return;
        }
        Canvas canvas3 = canvas;
        this.cueText = cue2.text;
        this.cueTextAlignment = cue2.textAlignment;
        this.cueBitmap = cue2.bitmap;
        this.cueLine = cue2.line;
        this.cueLineType = cue2.lineType;
        this.cueLineAnchor = cue2.lineAnchor;
        this.cuePosition = cue2.position;
        this.cuePositionAnchor = cue2.positionAnchor;
        this.cueSize = cue2.size;
        this.cueBitmapHeight = cue2.bitmapHeight;
        this.applyEmbeddedStyles = z3;
        this.applyEmbeddedFontSizes = z4;
        this.foregroundColor = captionStyleCompat2.foregroundColor;
        this.backgroundColor = captionStyleCompat2.backgroundColor;
        this.windowColor = i9;
        this.edgeType = captionStyleCompat2.edgeType;
        this.edgeColor = captionStyleCompat2.edgeColor;
        this.textPaint.setTypeface(captionStyleCompat2.typeface);
        this.defaultTextSizePx = f4;
        this.cueTextSizePx = f5;
        this.bottomPaddingFraction = f6;
        this.parentLeft = i5;
        this.parentTop = i6;
        this.parentRight = i7;
        this.parentBottom = i8;
        if (z5) {
            Assertions.checkNotNull(this.cueText);
            setupTextLayout();
        } else {
            Assertions.checkNotNull(this.cueBitmap);
            setupBitmapLayout();
        }
        drawLayout(canvas3, z5);
    }

    @RequiresNonNull({"cueText"})
    private void setupTextLayout() {
        SpannableStringBuilder spannableStringBuilder;
        int i;
        int i2;
        int i3;
        int round;
        int i4;
        SpannableStringBuilder spannableStringBuilder2;
        CharSequence charSequence = this.cueText;
        int i5 = this.parentRight - this.parentLeft;
        int i6 = this.parentBottom - this.parentTop;
        this.textPaint.setTextSize(this.defaultTextSizePx);
        int i7 = (int) ((this.defaultTextSizePx * 0.125f) + 0.5f);
        int i8 = i7 * 2;
        int i9 = i5 - i8;
        float f = this.cueSize;
        if (f != -3.4028235E38f) {
            i9 = (int) (((float) i9) * f);
        }
        if (i9 <= 0) {
            Log.w("SubtitlePainter", "Skipped drawing subtitle cue (insufficient space)");
            return;
        }
        if (!this.applyEmbeddedStyles) {
            charSequence = charSequence.toString();
        } else {
            if (!this.applyEmbeddedFontSizes) {
                spannableStringBuilder2 = new SpannableStringBuilder(charSequence);
                int length = spannableStringBuilder2.length();
                AbsoluteSizeSpan[] absoluteSizeSpanArr = (AbsoluteSizeSpan[]) spannableStringBuilder2.getSpans(0, length, AbsoluteSizeSpan.class);
                RelativeSizeSpan[] relativeSizeSpanArr = (RelativeSizeSpan[]) spannableStringBuilder2.getSpans(0, length, RelativeSizeSpan.class);
                for (AbsoluteSizeSpan removeSpan : absoluteSizeSpanArr) {
                    spannableStringBuilder2.removeSpan(removeSpan);
                }
                for (RelativeSizeSpan removeSpan2 : relativeSizeSpanArr) {
                    spannableStringBuilder2.removeSpan(removeSpan2);
                }
            } else if (this.cueTextSizePx > 0.0f) {
                spannableStringBuilder2 = new SpannableStringBuilder(charSequence);
                spannableStringBuilder2.setSpan(new AbsoluteSizeSpan((int) this.cueTextSizePx), 0, spannableStringBuilder2.length(), 16711680);
            }
            charSequence = spannableStringBuilder2;
        }
        if (Color.alpha(this.backgroundColor) > 0) {
            SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(charSequence);
            spannableStringBuilder3.setSpan(new BackgroundColorSpan(this.backgroundColor), 0, spannableStringBuilder3.length(), 16711680);
            spannableStringBuilder = spannableStringBuilder3;
        } else {
            spannableStringBuilder = charSequence;
        }
        Layout.Alignment alignment = this.cueTextAlignment;
        if (alignment == null) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        }
        Layout.Alignment alignment2 = alignment;
        StaticLayout staticLayout = new StaticLayout(spannableStringBuilder, this.textPaint, i9, alignment2, this.spacingMult, this.spacingAdd, true);
        this.textLayout = staticLayout;
        int height = staticLayout.getHeight();
        int lineCount = this.textLayout.getLineCount();
        int i10 = 0;
        for (int i11 = 0; i11 < lineCount; i11++) {
            i10 = Math.max((int) Math.ceil((double) this.textLayout.getLineWidth(i11)), i10);
        }
        if (this.cueSize == -3.4028235E38f || i10 >= i9) {
            i9 = i10;
        }
        int i12 = i9 + i8;
        float f2 = this.cuePosition;
        if (f2 != -3.4028235E38f) {
            int round2 = Math.round(((float) i5) * f2) + this.parentLeft;
            int i13 = this.cuePositionAnchor;
            if (i13 == 1) {
                round2 = ((round2 * 2) - i12) / 2;
            } else if (i13 == 2) {
                round2 -= i12;
            }
            i2 = Math.max(round2, this.parentLeft);
            i = Math.min(i12 + i2, this.parentRight);
        } else {
            i2 = ((i5 - i12) / 2) + this.parentLeft;
            i = i2 + i12;
        }
        int i14 = i - i2;
        if (i14 <= 0) {
            Log.w("SubtitlePainter", "Skipped drawing subtitle cue (invalid horizontal positioning)");
            return;
        }
        float f3 = this.cueLine;
        if (f3 != -3.4028235E38f) {
            if (this.cueLineType == 0) {
                round = Math.round(((float) i6) * f3);
                i4 = this.parentTop;
            } else {
                int lineBottom = this.textLayout.getLineBottom(0) - this.textLayout.getLineTop(0);
                float f4 = this.cueLine;
                if (f4 >= 0.0f) {
                    round = Math.round(f4 * ((float) lineBottom));
                    i4 = this.parentTop;
                } else {
                    round = Math.round((f4 + 1.0f) * ((float) lineBottom));
                    i4 = this.parentBottom;
                }
            }
            i3 = round + i4;
            int i15 = this.cueLineAnchor;
            if (i15 == 2) {
                i3 -= height;
            } else if (i15 == 1) {
                i3 = ((i3 * 2) - height) / 2;
            }
            int i16 = i3 + height;
            int i17 = this.parentBottom;
            if (i16 > i17) {
                i3 = i17 - height;
            } else {
                int i18 = this.parentTop;
                if (i3 < i18) {
                    i3 = i18;
                }
            }
        } else {
            i3 = (this.parentBottom - height) - ((int) (((float) i6) * this.bottomPaddingFraction));
        }
        this.textLayout = new StaticLayout(spannableStringBuilder, this.textPaint, i14, alignment2, this.spacingMult, this.spacingAdd, true);
        this.textLeft = i2;
        this.textTop = i3;
        this.textPaddingX = i7;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"cueBitmap"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupBitmapLayout() {
        /*
            r7 = this;
            android.graphics.Bitmap r0 = r7.cueBitmap
            int r1 = r7.parentRight
            int r2 = r7.parentLeft
            int r1 = r1 - r2
            int r3 = r7.parentBottom
            int r4 = r7.parentTop
            int r3 = r3 - r4
            float r2 = (float) r2
            float r1 = (float) r1
            float r5 = r7.cuePosition
            float r5 = r5 * r1
            float r2 = r2 + r5
            float r4 = (float) r4
            float r3 = (float) r3
            float r5 = r7.cueLine
            float r5 = r5 * r3
            float r4 = r4 + r5
            float r5 = r7.cueSize
            float r1 = r1 * r5
            int r1 = java.lang.Math.round(r1)
            float r5 = r7.cueBitmapHeight
            r6 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            int r6 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x0032
            float r3 = r3 * r5
            int r0 = java.lang.Math.round(r3)
            goto L_0x0044
        L_0x0032:
            float r3 = (float) r1
            int r5 = r0.getHeight()
            float r5 = (float) r5
            int r0 = r0.getWidth()
            float r0 = (float) r0
            float r5 = r5 / r0
            float r3 = r3 * r5
            int r0 = java.lang.Math.round(r3)
        L_0x0044:
            int r3 = r7.cuePositionAnchor
            r5 = 1
            r6 = 2
            if (r3 != r6) goto L_0x004d
            float r3 = (float) r1
        L_0x004b:
            float r2 = r2 - r3
            goto L_0x0053
        L_0x004d:
            if (r3 != r5) goto L_0x0053
            int r3 = r1 / 2
            float r3 = (float) r3
            goto L_0x004b
        L_0x0053:
            int r2 = java.lang.Math.round(r2)
            int r3 = r7.cueLineAnchor
            if (r3 != r6) goto L_0x005e
            float r3 = (float) r0
        L_0x005c:
            float r4 = r4 - r3
            goto L_0x0064
        L_0x005e:
            if (r3 != r5) goto L_0x0064
            int r3 = r0 / 2
            float r3 = (float) r3
            goto L_0x005c
        L_0x0064:
            int r3 = java.lang.Math.round(r4)
            android.graphics.Rect r4 = new android.graphics.Rect
            int r1 = r1 + r2
            int r0 = r0 + r3
            r4.<init>(r2, r3, r1, r0)
            r7.bitmapRect = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SubtitlePainter.setupBitmapLayout():void");
    }

    private void drawLayout(Canvas canvas, boolean z) {
        if (z) {
            drawTextLayout(canvas);
            return;
        }
        Assertions.checkNotNull(this.bitmapRect);
        Assertions.checkNotNull(this.cueBitmap);
        drawBitmapLayout(canvas);
    }

    private void drawTextLayout(Canvas canvas) {
        int i;
        StaticLayout staticLayout = this.textLayout;
        if (staticLayout != null) {
            int save = canvas.save();
            canvas.translate((float) this.textLeft, (float) this.textTop);
            if (Color.alpha(this.windowColor) > 0) {
                this.paint.setColor(this.windowColor);
                canvas.drawRect((float) (-this.textPaddingX), 0.0f, (float) (staticLayout.getWidth() + this.textPaddingX), (float) staticLayout.getHeight(), this.paint);
            }
            int i2 = this.edgeType;
            boolean z = true;
            if (i2 == 1) {
                this.textPaint.setStrokeJoin(Paint.Join.ROUND);
                this.textPaint.setStrokeWidth(this.outlineWidth);
                this.textPaint.setColor(this.edgeColor);
                this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                staticLayout.draw(canvas);
            } else if (i2 == 2) {
                TextPaint textPaint2 = this.textPaint;
                float f = this.shadowRadius;
                float f2 = this.shadowOffset;
                textPaint2.setShadowLayer(f, f2, f2, this.edgeColor);
            } else if (i2 == 3 || i2 == 4) {
                if (this.edgeType != 3) {
                    z = false;
                }
                int i3 = -1;
                if (z) {
                    i = -1;
                } else {
                    i = this.edgeColor;
                }
                if (z) {
                    i3 = this.edgeColor;
                }
                float f3 = this.shadowRadius / 2.0f;
                this.textPaint.setColor(this.foregroundColor);
                this.textPaint.setStyle(Paint.Style.FILL);
                float f4 = -f3;
                this.textPaint.setShadowLayer(this.shadowRadius, f4, f4, i);
                staticLayout.draw(canvas);
                this.textPaint.setShadowLayer(this.shadowRadius, f3, f3, i3);
            }
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Paint.Style.FILL);
            staticLayout.draw(canvas);
            this.textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            canvas.restoreToCount(save);
        }
    }

    @RequiresNonNull({"cueBitmap", "bitmapRect"})
    private void drawBitmapLayout(Canvas canvas) {
        canvas.drawBitmap(this.cueBitmap, (Rect) null, this.bitmapRect, (Paint) null);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == charSequence2 || (charSequence != null && charSequence.equals(charSequence2));
    }
}
