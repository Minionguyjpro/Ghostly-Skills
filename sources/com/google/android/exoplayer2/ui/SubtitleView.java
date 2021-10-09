package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import java.util.ArrayList;
import java.util.List;

public final class SubtitleView extends View {
    private boolean applyEmbeddedFontSizes = true;
    private boolean applyEmbeddedStyles = true;
    private float bottomPaddingFraction = 0.08f;
    private List<Cue> cues;
    private final List<SubtitlePainter> painters = new ArrayList();
    private CaptionStyleCompat style = CaptionStyleCompat.DEFAULT;
    private float textSize = 0.0533f;
    private int textSizeType = 0;

    private float resolveTextSize(int i, float f, int i2, int i3) {
        float f2;
        if (i == 0) {
            f2 = (float) i3;
        } else if (i == 1) {
            f2 = (float) i2;
        } else if (i != 2) {
            return -3.4028235E38f;
        } else {
            return f;
        }
        return f * f2;
    }

    public SubtitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setCues(List<Cue> list) {
        int i;
        if (this.cues != list) {
            this.cues = list;
            if (list == null) {
                i = 0;
            } else {
                i = list.size();
            }
            while (this.painters.size() < i) {
                this.painters.add(new SubtitlePainter(getContext()));
            }
            invalidate();
        }
    }

    public void setFractionalTextSize(float f) {
        setFractionalTextSize(f, false);
    }

    public void setFractionalTextSize(float f, boolean z) {
        setTextSize(z ? 1 : 0, f);
    }

    private void setTextSize(int i, float f) {
        if (this.textSizeType != i || this.textSize != f) {
            this.textSizeType = i;
            this.textSize = f;
            invalidate();
        }
    }

    public void setApplyEmbeddedStyles(boolean z) {
        if (this.applyEmbeddedStyles != z || this.applyEmbeddedFontSizes != z) {
            this.applyEmbeddedStyles = z;
            this.applyEmbeddedFontSizes = z;
            invalidate();
        }
    }

    public void setApplyEmbeddedFontSizes(boolean z) {
        if (this.applyEmbeddedFontSizes != z) {
            this.applyEmbeddedFontSizes = z;
            invalidate();
        }
    }

    public void setStyle(CaptionStyleCompat captionStyleCompat) {
        if (this.style != captionStyleCompat) {
            this.style = captionStyleCompat;
            invalidate();
        }
    }

    public void setBottomPaddingFraction(float f) {
        if (this.bottomPaddingFraction != f) {
            this.bottomPaddingFraction = f;
            invalidate();
        }
    }

    public void dispatchDraw(Canvas canvas) {
        List<Cue> list = this.cues;
        if (list != null && !list.isEmpty()) {
            int height = getHeight();
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int paddingBottom = height - getPaddingBottom();
            if (paddingBottom > paddingTop && width > paddingLeft) {
                int i = paddingBottom - paddingTop;
                float resolveTextSize = resolveTextSize(this.textSizeType, this.textSize, height, i);
                if (resolveTextSize > 0.0f) {
                    int size = list.size();
                    int i2 = 0;
                    while (i2 < size) {
                        Cue cue = list.get(i2);
                        float resolveCueTextSize = resolveCueTextSize(cue, height, i);
                        boolean z = this.applyEmbeddedStyles;
                        boolean z2 = this.applyEmbeddedFontSizes;
                        CaptionStyleCompat captionStyleCompat = this.style;
                        int i3 = i2;
                        float f = resolveCueTextSize;
                        int i4 = size;
                        float f2 = this.bottomPaddingFraction;
                        int i5 = paddingBottom;
                        int i6 = width;
                        this.painters.get(i2).draw(cue, z, z2, captionStyleCompat, resolveTextSize, f, f2, canvas, paddingLeft, paddingTop, i6, i5);
                        i2 = i3 + 1;
                        size = i4;
                        i = i;
                        paddingBottom = i5;
                        width = i6;
                        paddingTop = paddingTop;
                        paddingLeft = paddingLeft;
                    }
                }
            }
        }
    }

    private float resolveCueTextSize(Cue cue, int i, int i2) {
        if (cue.textSizeType == Integer.MIN_VALUE || cue.textSize == -3.4028235E38f) {
            return 0.0f;
        }
        return Math.max(resolveTextSize(cue.textSizeType, cue.textSize, i, i2), 0.0f);
    }

    private float getUserCaptionFontScaleV19() {
        return ((CaptioningManager) getContext().getSystemService("captioning")).getFontScale();
    }

    private CaptionStyleCompat getUserCaptionStyleV19() {
        return CaptionStyleCompat.createFromCaptionStyle(((CaptioningManager) getContext().getSystemService("captioning")).getUserStyle());
    }
}
