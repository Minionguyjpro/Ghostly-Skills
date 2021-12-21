package androidx.media2.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaFormat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.media2.widget.Cea608CCParser;
import androidx.media2.widget.ClosedCaptionWidget;
import androidx.media2.widget.SubtitleController;
import androidx.media2.widget.SubtitleTrack;
import com.mopub.mobileads.resource.DrawableConstants;

class Cea608CaptionRenderer extends SubtitleController.Renderer {
    private Cea608CCWidget mCCWidget;
    private final Context mContext;

    Cea608CaptionRenderer(Context context) {
        this.mContext = context;
    }

    public boolean supports(MediaFormat mediaFormat) {
        if (mediaFormat.containsKey("mime")) {
            return "text/cea-608".equals(mediaFormat.getString("mime"));
        }
        return false;
    }

    public SubtitleTrack createTrack(MediaFormat mediaFormat) {
        if ("text/cea-608".equals(mediaFormat.getString("mime"))) {
            if (this.mCCWidget == null) {
                this.mCCWidget = new Cea608CCWidget(this, this.mContext);
            }
            return new Cea608CaptionTrack(this.mCCWidget, mediaFormat);
        }
        throw new RuntimeException("No matching format: " + mediaFormat.toString());
    }

    static class Cea608CaptionTrack extends SubtitleTrack {
        private final Cea608CCParser mCCParser = new Cea608CCParser(this.mRenderingWidget);
        private final Cea608CCWidget mRenderingWidget;

        Cea608CaptionTrack(Cea608CCWidget cea608CCWidget, MediaFormat mediaFormat) {
            super(mediaFormat);
            this.mRenderingWidget = cea608CCWidget;
        }

        public void onData(byte[] bArr, boolean z, long j) {
            this.mCCParser.parse(bArr);
        }

        public SubtitleTrack.RenderingWidget getRenderingWidget() {
            return this.mRenderingWidget;
        }
    }

    class Cea608CCWidget extends ClosedCaptionWidget implements Cea608CCParser.DisplayListener {
        final Rect mTextBounds;

        Cea608CCWidget(Cea608CaptionRenderer cea608CaptionRenderer, Context context) {
            this(cea608CaptionRenderer, context, (AttributeSet) null);
        }

        Cea608CCWidget(Cea608CaptionRenderer cea608CaptionRenderer, Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        Cea608CCWidget(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.mTextBounds = new Rect();
        }

        public ClosedCaptionWidget.ClosedCaptionLayout createCaptionLayout(Context context) {
            return new CCLayout(context);
        }

        public void onDisplayChanged(SpannableStringBuilder[] spannableStringBuilderArr) {
            ((CCLayout) this.mClosedCaptionLayout).update(spannableStringBuilderArr);
            if (this.mListener != null) {
                this.mListener.onChanged(this);
            }
        }

        public CaptioningManager.CaptionStyle getCaptionStyle() {
            return this.mCaptionStyle;
        }

        private class CCLineBox extends AppCompatTextView {
            private int mBgColor = DrawableConstants.CtaButton.BACKGROUND_COLOR;
            private int mEdgeColor = 0;
            private int mEdgeType = 0;
            private float mOutlineWidth;
            private float mShadowOffset;
            private float mShadowRadius;
            private int mTextColor = -1;

            CCLineBox(Context context) {
                super(context);
                setGravity(17);
                setBackgroundColor(0);
                setTextColor(-1);
                setTypeface(Typeface.MONOSPACE);
                setVisibility(4);
                Resources resources = getContext().getResources();
                this.mOutlineWidth = (float) resources.getDimensionPixelSize(R.dimen.subtitle_outline_width);
                this.mShadowRadius = (float) resources.getDimensionPixelSize(R.dimen.subtitle_shadow_radius);
                this.mShadowOffset = (float) resources.getDimensionPixelSize(R.dimen.subtitle_shadow_offset);
            }

            /* access modifiers changed from: package-private */
            public void setCaptionStyle(CaptioningManager.CaptionStyle captionStyle) {
                this.mTextColor = captionStyle.foregroundColor;
                this.mBgColor = captionStyle.backgroundColor;
                this.mEdgeType = captionStyle.edgeType;
                this.mEdgeColor = captionStyle.edgeColor;
                setTextColor(this.mTextColor);
                if (this.mEdgeType == 2) {
                    float f = this.mShadowRadius;
                    float f2 = this.mShadowOffset;
                    setShadowLayer(f, f2, f2, this.mEdgeColor);
                } else {
                    setShadowLayer(0.0f, 0.0f, 0.0f, 0);
                }
                invalidate();
            }

            /* access modifiers changed from: protected */
            public void onMeasure(int i, int i2) {
                float size = ((float) View.MeasureSpec.getSize(i2)) * 0.75f;
                setTextSize(0, size);
                this.mOutlineWidth = (0.1f * size) + 1.0f;
                float f = (size * 0.05f) + 1.0f;
                this.mShadowRadius = f;
                this.mShadowOffset = f;
                setScaleX(1.0f);
                getPaint().getTextBounds("1234567890123456789012345678901234", 0, 34, Cea608CCWidget.this.mTextBounds);
                float width = (float) Cea608CCWidget.this.mTextBounds.width();
                float size2 = (float) View.MeasureSpec.getSize(i);
                if (width != 0.0f) {
                    setScaleX(size2 / width);
                } else {
                    Log.w("Cea608CaptionRenderer", "onMeasure(): Paint#getTextBounds() returned zero width. Ignored.");
                }
                super.onMeasure(i, i2);
            }

            /* access modifiers changed from: protected */
            public void onDraw(Canvas canvas) {
                int i = this.mEdgeType;
                if (i == -1 || i == 0 || i == 2) {
                    super.onDraw(canvas);
                } else if (i == 1) {
                    drawEdgeOutline(canvas);
                } else {
                    drawEdgeRaisedOrDepressed(canvas);
                }
            }

            private void drawEdgeOutline(Canvas canvas) {
                TextPaint paint = getPaint();
                Paint.Style style = paint.getStyle();
                Paint.Join strokeJoin = paint.getStrokeJoin();
                float strokeWidth = paint.getStrokeWidth();
                setTextColor(this.mEdgeColor);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeJoin(Paint.Join.ROUND);
                paint.setStrokeWidth(this.mOutlineWidth);
                super.onDraw(canvas);
                setTextColor(this.mTextColor);
                paint.setStyle(style);
                paint.setStrokeJoin(strokeJoin);
                paint.setStrokeWidth(strokeWidth);
                setBackgroundSpans(0);
                super.onDraw(canvas);
                setBackgroundSpans(this.mBgColor);
            }

            private void drawEdgeRaisedOrDepressed(Canvas canvas) {
                int i;
                TextPaint paint = getPaint();
                Paint.Style style = paint.getStyle();
                paint.setStyle(Paint.Style.FILL);
                boolean z = this.mEdgeType == 3;
                int i2 = -1;
                if (z) {
                    i = -1;
                } else {
                    i = this.mEdgeColor;
                }
                if (z) {
                    i2 = this.mEdgeColor;
                }
                float f = this.mShadowRadius;
                float f2 = f / 2.0f;
                float f3 = -f2;
                setShadowLayer(f, f3, f3, i);
                super.onDraw(canvas);
                setBackgroundSpans(0);
                setShadowLayer(this.mShadowRadius, f2, f2, i2);
                super.onDraw(canvas);
                paint.setStyle(style);
                setBackgroundSpans(this.mBgColor);
            }

            private void setBackgroundSpans(int i) {
                CharSequence text = getText();
                if (text instanceof Spannable) {
                    Spannable spannable = (Spannable) text;
                    Cea608CCParser.MutableBackgroundColorSpan[] mutableBackgroundColorSpanArr = (Cea608CCParser.MutableBackgroundColorSpan[]) spannable.getSpans(0, spannable.length(), Cea608CCParser.MutableBackgroundColorSpan.class);
                    for (Cea608CCParser.MutableBackgroundColorSpan backgroundColor : mutableBackgroundColorSpanArr) {
                        backgroundColor.setBackgroundColor(i);
                    }
                }
            }
        }

        private class CCLayout extends LinearLayout implements ClosedCaptionWidget.ClosedCaptionLayout {
            private final CCLineBox[] mLineBoxes = new CCLineBox[15];

            public void setFontScale(float f) {
            }

            CCLayout(Context context) {
                super(context);
                setGravity(8388611);
                setOrientation(1);
                for (int i = 0; i < 15; i++) {
                    this.mLineBoxes[i] = new CCLineBox(getContext());
                    addView(this.mLineBoxes[i], -2, -2);
                }
            }

            public void setCaptionStyle(CaptioningManager.CaptionStyle captionStyle) {
                for (int i = 0; i < 15; i++) {
                    this.mLineBoxes[i].setCaptionStyle(captionStyle);
                }
            }

            /* access modifiers changed from: package-private */
            public void update(SpannableStringBuilder[] spannableStringBuilderArr) {
                for (int i = 0; i < 15; i++) {
                    if (spannableStringBuilderArr[i] != null) {
                        this.mLineBoxes[i].setText(spannableStringBuilderArr[i], TextView.BufferType.SPANNABLE);
                        this.mLineBoxes[i].setVisibility(0);
                    } else {
                        this.mLineBoxes[i].setVisibility(4);
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void onMeasure(int i, int i2) {
                super.onMeasure(i, i2);
                int measuredWidth = getMeasuredWidth();
                int measuredHeight = getMeasuredHeight();
                int i3 = measuredWidth * 3;
                int i4 = measuredHeight * 4;
                if (i3 >= i4) {
                    measuredWidth = i4 / 3;
                } else {
                    measuredHeight = i3 / 4;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(((int) (((float) measuredHeight) * 0.9f)) / 15, 1073741824);
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec((int) (((float) measuredWidth) * 0.9f), 1073741824);
                for (int i5 = 0; i5 < 15; i5++) {
                    this.mLineBoxes[i5].measure(makeMeasureSpec2, makeMeasureSpec);
                }
            }

            /* access modifiers changed from: protected */
            public void onLayout(boolean z, int i, int i2, int i3, int i4) {
                int i5;
                int i6;
                int i7 = i3 - i;
                int i8 = i4 - i2;
                int i9 = i7 * 3;
                int i10 = i8 * 4;
                if (i9 >= i10) {
                    i5 = i10 / 3;
                    i6 = i8;
                } else {
                    i6 = i9 / 4;
                    i5 = i7;
                }
                int i11 = (int) (((float) i5) * 0.9f);
                int i12 = (int) (((float) i6) * 0.9f);
                int i13 = (i7 - i11) / 2;
                int i14 = (i8 - i12) / 2;
                int i15 = 0;
                while (i15 < 15) {
                    i15++;
                    this.mLineBoxes[i15].layout(i13, ((i12 * i15) / 15) + i14, i13 + i11, ((i12 * i15) / 15) + i14);
                }
            }
        }
    }
}
