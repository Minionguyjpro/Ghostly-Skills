package com.mopub.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;

public class CloseableLayout extends FrameLayout {
    static final float CLOSE_BUTTON_PADDING_DP = 8.0f;
    static final float CLOSE_BUTTON_SIZE_DP = 30.0f;
    static final float CLOSE_REGION_SIZE_DP = 50.0f;
    private final Rect mClosableLayoutRect;
    private boolean mCloseAlwaysInteractable;
    private boolean mCloseBoundChanged;
    private final Rect mCloseButtonBounds;
    private final int mCloseButtonPadding;
    private final int mCloseButtonSize;
    private final StateListDrawable mCloseDrawable;
    private ClosePosition mClosePosition;
    private final Rect mCloseRegionBounds;
    private final int mCloseRegionSize;
    private final Rect mInsetCloseRegionBounds;
    private OnCloseListener mOnCloseListener;
    private final int mTouchSlop;
    private UnsetPressedState mUnsetPressedState;

    public interface OnCloseListener {
        void onClose();
    }

    public enum ClosePosition {
        TOP_LEFT(51),
        TOP_CENTER(49),
        TOP_RIGHT(53),
        CENTER(17),
        BOTTOM_LEFT(83),
        BOTTOM_CENTER(81),
        BOTTOM_RIGHT(85);
        
        private final int mGravity;

        private ClosePosition(int i) {
            this.mGravity = i;
        }

        /* access modifiers changed from: package-private */
        public int getGravity() {
            return this.mGravity;
        }
    }

    public CloseableLayout(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public CloseableLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CloseableLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mClosableLayoutRect = new Rect();
        this.mCloseRegionBounds = new Rect();
        this.mCloseButtonBounds = new Rect();
        this.mInsetCloseRegionBounds = new Rect();
        this.mCloseDrawable = new StateListDrawable();
        this.mClosePosition = ClosePosition.TOP_RIGHT;
        this.mCloseDrawable.addState(SELECTED_STATE_SET, Drawables.INTERSTITIAL_CLOSE_BUTTON_PRESSED.createDrawable(context));
        this.mCloseDrawable.addState(EMPTY_STATE_SET, Drawables.INTERSTITIAL_CLOSE_BUTTON_NORMAL.createDrawable(context));
        this.mCloseDrawable.setState(EMPTY_STATE_SET);
        this.mCloseDrawable.setCallback(this);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mCloseRegionSize = Dips.asIntPixels(CLOSE_REGION_SIZE_DP, context);
        this.mCloseButtonSize = Dips.asIntPixels(CLOSE_BUTTON_SIZE_DP, context);
        this.mCloseButtonPadding = Dips.asIntPixels(8.0f, context);
        setWillNotDraw(false);
        this.mCloseAlwaysInteractable = true;
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.mOnCloseListener = onCloseListener;
    }

    public void setClosePosition(ClosePosition closePosition) {
        Preconditions.checkNotNull(closePosition);
        this.mClosePosition = closePosition;
        this.mCloseBoundChanged = true;
        invalidate();
    }

    public void setCloseVisible(boolean z) {
        if (this.mCloseDrawable.setVisible(z, false)) {
            invalidate(this.mCloseRegionBounds);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mCloseBoundChanged = true;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mCloseBoundChanged) {
            this.mCloseBoundChanged = false;
            this.mClosableLayoutRect.set(0, 0, getWidth(), getHeight());
            applyCloseRegionBounds(this.mClosePosition, this.mClosableLayoutRect, this.mCloseRegionBounds);
            this.mInsetCloseRegionBounds.set(this.mCloseRegionBounds);
            Rect rect = this.mInsetCloseRegionBounds;
            int i = this.mCloseButtonPadding;
            rect.inset(i, i);
            applyCloseButtonBounds(this.mClosePosition, this.mInsetCloseRegionBounds, this.mCloseButtonBounds);
            this.mCloseDrawable.setBounds(this.mCloseButtonBounds);
        }
        if (this.mCloseDrawable.isVisible()) {
            this.mCloseDrawable.draw(canvas);
        }
    }

    public void applyCloseRegionBounds(ClosePosition closePosition, Rect rect, Rect rect2) {
        applyCloseBoundsWithSize(closePosition, this.mCloseRegionSize, rect, rect2);
    }

    private void applyCloseButtonBounds(ClosePosition closePosition, Rect rect, Rect rect2) {
        applyCloseBoundsWithSize(closePosition, this.mCloseButtonSize, rect, rect2);
    }

    private void applyCloseBoundsWithSize(ClosePosition closePosition, int i, Rect rect, Rect rect2) {
        Gravity.apply(closePosition.getGravity(), i, i, rect, rect2);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        return pointInCloseBounds((int) motionEvent.getX(), (int) motionEvent.getY(), 0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!pointInCloseBounds((int) motionEvent.getX(), (int) motionEvent.getY(), this.mTouchSlop) || !shouldAllowPress()) {
            setClosePressed(false);
            super.onTouchEvent(motionEvent);
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            setClosePressed(true);
        } else if (action != 1) {
            if (action == 3) {
                setClosePressed(false);
            }
        } else if (isClosePressed()) {
            if (this.mUnsetPressedState == null) {
                this.mUnsetPressedState = new UnsetPressedState();
            }
            postDelayed(this.mUnsetPressedState, (long) ViewConfiguration.getPressedStateDuration());
            performClose();
        }
        return true;
    }

    public void setCloseAlwaysInteractable(boolean z) {
        this.mCloseAlwaysInteractable = z;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAllowPress() {
        return this.mCloseAlwaysInteractable || this.mCloseDrawable.isVisible();
    }

    /* access modifiers changed from: private */
    public void setClosePressed(boolean z) {
        if (z != isClosePressed()) {
            this.mCloseDrawable.setState(z ? SELECTED_STATE_SET : EMPTY_STATE_SET);
            invalidate(this.mCloseRegionBounds);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isClosePressed() {
        return this.mCloseDrawable.getState() == SELECTED_STATE_SET;
    }

    /* access modifiers changed from: package-private */
    public boolean pointInCloseBounds(int i, int i2, int i3) {
        return i >= this.mCloseRegionBounds.left - i3 && i2 >= this.mCloseRegionBounds.top - i3 && i < this.mCloseRegionBounds.right + i3 && i2 < this.mCloseRegionBounds.bottom + i3;
    }

    private void performClose() {
        playSoundEffect(0);
        OnCloseListener onCloseListener = this.mOnCloseListener;
        if (onCloseListener != null) {
            onCloseListener.onClose();
        }
    }

    private final class UnsetPressedState implements Runnable {
        private UnsetPressedState() {
        }

        public void run() {
            CloseableLayout.this.setClosePressed(false);
        }
    }

    /* access modifiers changed from: package-private */
    public void setCloseBounds(Rect rect) {
        this.mCloseRegionBounds.set(rect);
    }

    /* access modifiers changed from: package-private */
    public Rect getCloseBounds() {
        return this.mCloseRegionBounds;
    }

    /* access modifiers changed from: package-private */
    public void setCloseBoundChanged(boolean z) {
        this.mCloseBoundChanged = z;
    }

    public boolean isCloseVisible() {
        return this.mCloseDrawable.isVisible();
    }
}
