package androidx.media2.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import androidx.media2.widget.SubtitleController;
import androidx.media2.widget.SubtitleTrack;

class SubtitleAnchorView extends View implements SubtitleController.Anchor {
    private SubtitleTrack.RenderingWidget mSubtitleWidget;
    private SubtitleTrack.RenderingWidget.OnChangedListener mSubtitlesChangedListener;

    public CharSequence getAccessibilityClassName() {
        return "androidx.media2.widget.SubtitleAnchorView";
    }

    SubtitleAnchorView(Context context) {
        this(context, (AttributeSet) null);
    }

    SubtitleAnchorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    SubtitleAnchorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setSubtitleWidget(SubtitleTrack.RenderingWidget renderingWidget) {
        if (this.mSubtitleWidget != renderingWidget) {
            boolean isAttachedToWindow = isAttachedToWindow();
            SubtitleTrack.RenderingWidget renderingWidget2 = this.mSubtitleWidget;
            if (renderingWidget2 != null) {
                if (isAttachedToWindow) {
                    renderingWidget2.onDetachedFromWindow();
                }
                this.mSubtitleWidget.setOnChangedListener((SubtitleTrack.RenderingWidget.OnChangedListener) null);
            }
            this.mSubtitleWidget = renderingWidget;
            if (renderingWidget != null) {
                if (this.mSubtitlesChangedListener == null) {
                    this.mSubtitlesChangedListener = new SubtitleTrack.RenderingWidget.OnChangedListener() {
                        public void onChanged(SubtitleTrack.RenderingWidget renderingWidget) {
                            SubtitleAnchorView.this.invalidate();
                        }
                    };
                }
                setWillNotDraw(false);
                renderingWidget.setOnChangedListener(this.mSubtitlesChangedListener);
                if (isAttachedToWindow) {
                    renderingWidget.onAttachedToWindow();
                    requestLayout();
                }
            } else {
                setWillNotDraw(true);
            }
            invalidate();
        }
    }

    public Looper getSubtitleLooper() {
        return Looper.getMainLooper();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        SubtitleTrack.RenderingWidget renderingWidget = this.mSubtitleWidget;
        if (renderingWidget != null) {
            renderingWidget.onAttachedToWindow();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SubtitleTrack.RenderingWidget renderingWidget = this.mSubtitleWidget;
        if (renderingWidget != null) {
            renderingWidget.onDetachedFromWindow();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mSubtitleWidget != null) {
            this.mSubtitleWidget.setSize((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mSubtitleWidget != null) {
            int save = canvas.save();
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            this.mSubtitleWidget.draw(canvas);
            canvas.restoreToCount(save);
        }
    }
}
