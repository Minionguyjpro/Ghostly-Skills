package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import java.util.List;

public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    static final Property<View, Float> HEIGHT = new Property<View, Float>(Float.class, "height") {
        public void set(View view, Float f) {
            view.getLayoutParams().height = f.intValue();
            view.requestLayout();
        }

        public Float get(View view) {
            return Float.valueOf((float) view.getLayoutParams().height);
        }
    };
    static final Property<View, Float> WIDTH = new Property<View, Float>(Float.class, "width") {
        public void set(View view, Float f) {
            view.getLayoutParams().width = f.intValue();
            view.requestLayout();
        }

        public Float get(View view) {
            return Float.valueOf((float) view.getLayoutParams().width);
        }
    };
    private final CoordinatorLayout.Behavior<ExtendedFloatingActionButton> behavior;
    /* access modifiers changed from: private */
    public final MotionStrategy extendStrategy;
    /* access modifiers changed from: private */
    public final MotionStrategy hideStrategy;
    private boolean isExtended;
    /* access modifiers changed from: private */
    public final Rect shadowPadding;
    /* access modifiers changed from: private */
    public final MotionStrategy showStrategy;
    /* access modifiers changed from: private */
    public final MotionStrategy shrinkStrategy;

    public static abstract class OnChangedCallback {
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isExtended && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.isExtended = false;
            this.shrinkStrategy.performNow();
        }
    }

    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.behavior;
    }

    public void setExtended(boolean z) {
        if (this.isExtended != z) {
            MotionStrategy motionStrategy = z ? this.extendStrategy : this.shrinkStrategy;
            if (!motionStrategy.shouldCancel()) {
                motionStrategy.performNow();
            }
        }
    }

    public MotionSpec getShowMotionSpec() {
        return this.showStrategy.getMotionSpec();
    }

    public void setShowMotionSpec(MotionSpec motionSpec) {
        this.showStrategy.setMotionSpec(motionSpec);
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    public MotionSpec getHideMotionSpec() {
        return this.hideStrategy.getMotionSpec();
    }

    public void setHideMotionSpec(MotionSpec motionSpec) {
        this.hideStrategy.setMotionSpec(motionSpec);
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    public MotionSpec getExtendMotionSpec() {
        return this.extendStrategy.getMotionSpec();
    }

    public void setExtendMotionSpec(MotionSpec motionSpec) {
        this.extendStrategy.setMotionSpec(motionSpec);
    }

    public void setExtendMotionSpecResource(int i) {
        setExtendMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    public MotionSpec getShrinkMotionSpec() {
        return this.shrinkStrategy.getMotionSpec();
    }

    public void setShrinkMotionSpec(MotionSpec motionSpec) {
        this.shrinkStrategy.setMotionSpec(motionSpec);
    }

    public void setShrinkMotionSpecResource(int i) {
        setShrinkMotionSpec(MotionSpec.createFromResource(getContext(), i));
    }

    /* access modifiers changed from: private */
    public void performMotion(final MotionStrategy motionStrategy, final OnChangedCallback onChangedCallback) {
        if (!motionStrategy.shouldCancel()) {
            if (!shouldAnimateVisibilityChange()) {
                motionStrategy.performNow();
                motionStrategy.onChange(onChangedCallback);
                return;
            }
            measure(0, 0);
            AnimatorSet createAnimator = motionStrategy.createAnimator();
            createAnimator.addListener(new AnimatorListenerAdapter() {
                private boolean cancelled;

                public void onAnimationStart(Animator animator) {
                    motionStrategy.onAnimationStart(animator);
                    this.cancelled = false;
                }

                public void onAnimationCancel(Animator animator) {
                    this.cancelled = true;
                    motionStrategy.onAnimationCancel();
                }

                public void onAnimationEnd(Animator animator) {
                    motionStrategy.onAnimationEnd();
                    if (!this.cancelled) {
                        motionStrategy.onChange(onChangedCallback);
                    }
                }
            });
            for (Animator.AnimatorListener addListener : motionStrategy.getListeners()) {
                createAnimator.addListener(addListener);
            }
            createAnimator.start();
        }
    }

    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut(this) && !isInEditMode();
    }

    /* access modifiers changed from: package-private */
    public int getCollapsedSize() {
        return (Math.min(ViewCompat.getPaddingStart(this), ViewCompat.getPaddingEnd(this)) * 2) + getIconSize();
    }

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        private OnChangedCallback internalAutoHideCallback;
        private OnChangedCallback internalAutoShrinkCallback;
        private Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = obtainStyledAttributes.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = obtainStyledAttributes.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            obtainStyledAttributes.recycle();
        }

        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            } else if (!isBottomSheet(view)) {
                return false;
            } else {
                updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton);
                return false;
            }
        }

        private static boolean isBottomSheet(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean shouldUpdateVisibility(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams();
            if ((this.autoHideEnabled || this.autoShrinkEnabled) && layoutParams.getAnchorId() == view.getId()) {
                return true;
            }
            return false;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                shrinkOrHide(extendedFloatingActionButton);
                return true;
            }
            extendOrShow(extendedFloatingActionButton);
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams()).topMargin) {
                shrinkOrHide(extendedFloatingActionButton);
                return true;
            }
            extendOrShow(extendedFloatingActionButton);
            return true;
        }

        /* access modifiers changed from: protected */
        public void shrinkOrHide(ExtendedFloatingActionButton extendedFloatingActionButton) {
            MotionStrategy motionStrategy;
            OnChangedCallback onChangedCallback = this.autoShrinkEnabled ? this.internalAutoShrinkCallback : this.internalAutoHideCallback;
            if (this.autoShrinkEnabled) {
                motionStrategy = extendedFloatingActionButton.shrinkStrategy;
            } else {
                motionStrategy = extendedFloatingActionButton.hideStrategy;
            }
            extendedFloatingActionButton.performMotion(motionStrategy, onChangedCallback);
        }

        /* access modifiers changed from: protected */
        public void extendOrShow(ExtendedFloatingActionButton extendedFloatingActionButton) {
            MotionStrategy motionStrategy;
            OnChangedCallback onChangedCallback = this.autoShrinkEnabled ? this.internalAutoShrinkCallback : this.internalAutoHideCallback;
            if (this.autoShrinkEnabled) {
                motionStrategy = extendedFloatingActionButton.extendStrategy;
            } else {
                motionStrategy = extendedFloatingActionButton.showStrategy;
            }
            extendedFloatingActionButton.performMotion(motionStrategy, onChangedCallback);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
            List<View> dependencies = coordinatorLayout.getDependencies(extendedFloatingActionButton);
            int size = dependencies.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = dependencies.get(i2);
                if (!(view instanceof AppBarLayout)) {
                    if (isBottomSheet(view) && updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild(extendedFloatingActionButton, i);
            offsetIfNeeded(coordinatorLayout, extendedFloatingActionButton);
            return true;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, Rect rect) {
            Rect access$500 = extendedFloatingActionButton.shadowPadding;
            rect.set(extendedFloatingActionButton.getLeft() + access$500.left, extendedFloatingActionButton.getTop() + access$500.top, extendedFloatingActionButton.getRight() - access$500.right, extendedFloatingActionButton.getBottom() - access$500.bottom);
            return true;
        }

        private void offsetIfNeeded(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            int i;
            Rect access$500 = extendedFloatingActionButton.shadowPadding;
            if (access$500 != null && access$500.centerX() > 0 && access$500.centerY() > 0) {
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams();
                int i2 = 0;
                if (extendedFloatingActionButton.getRight() >= coordinatorLayout.getWidth() - layoutParams.rightMargin) {
                    i = access$500.right;
                } else {
                    i = extendedFloatingActionButton.getLeft() <= layoutParams.leftMargin ? -access$500.left : 0;
                }
                if (extendedFloatingActionButton.getBottom() >= coordinatorLayout.getHeight() - layoutParams.bottomMargin) {
                    i2 = access$500.bottom;
                } else if (extendedFloatingActionButton.getTop() <= layoutParams.topMargin) {
                    i2 = -access$500.top;
                }
                if (i2 != 0) {
                    ViewCompat.offsetTopAndBottom(extendedFloatingActionButton, i2);
                }
                if (i != 0) {
                    ViewCompat.offsetLeftAndRight(extendedFloatingActionButton, i);
                }
            }
        }
    }
}
