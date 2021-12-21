package com.startapp.android.publish.ads.list3d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;
import com.startapp.common.a.g;
import com.startapp.common.b;
import java.util.LinkedList;

/* compiled from: StartAppSDK */
public class c extends AdapterView<Adapter> {
    private int A = RecyclerView.UNDEFINED_DURATION;
    private boolean B = false;
    private boolean C = false;
    private boolean D = false;

    /* renamed from: a  reason: collision with root package name */
    protected int f69a = 0;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    protected int i;
    protected Dynamics j;
    protected float k = 0.0f;
    protected boolean l = false;
    protected boolean m = false;
    protected String n;
    protected String o;
    public BroadcastReceiver p = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("getHeight", c.this.getHeight());
            double height = (double) c.this.getHeight();
            double d = (double) intExtra;
            Double.isNaN(height);
            Double.isNaN(d);
            double d2 = height / d;
            g.a(3, c.this.q + "Updating Position with Ratio: [" + d2 + "]");
            c cVar = c.this;
            cVar.f69a = intent.getIntExtra("mTouchState", cVar.f69a);
            c cVar2 = c.this;
            cVar2.b = intent.getIntExtra("mTouchStartX", cVar2.b);
            c cVar3 = c.this;
            cVar3.c = intent.getIntExtra("mTouchStartY", cVar3.c);
            c cVar4 = c.this;
            cVar4.g = intent.getIntExtra("mListRotation", cVar4.g);
            c cVar5 = c.this;
            double intExtra2 = (double) intent.getIntExtra("mFirstItemPosition", cVar5.h);
            Double.isNaN(intExtra2);
            cVar5.h = (int) (intExtra2 * d2);
            c.this.h--;
            c cVar6 = c.this;
            double intExtra3 = (double) intent.getIntExtra("mLastItemPosition", cVar6.i);
            Double.isNaN(intExtra3);
            cVar6.i = (int) (intExtra3 * d2);
            c.this.i--;
            c cVar7 = c.this;
            double intExtra4 = (double) intent.getIntExtra("mListTop", cVar7.e);
            Double.isNaN(intExtra4);
            cVar7.e = (int) (intExtra4 * d2);
            c cVar8 = c.this;
            double intExtra5 = (double) intent.getIntExtra("mListTopStart", cVar8.d);
            Double.isNaN(intExtra5);
            cVar8.d = (int) (intExtra5 * d2);
            c cVar9 = c.this;
            double intExtra6 = (double) intent.getIntExtra("mListTopOffset", cVar9.f);
            Double.isNaN(intExtra6);
            cVar9.f = (int) (intExtra6 * d2);
            c.this.j = (Dynamics) intent.getParcelableExtra("mDynamics");
            c cVar10 = c.this;
            cVar10.k = intent.getFloatExtra("mLastVelocity", cVar10.k);
            c.this.j.a(d2);
            c.this.setAdapter(new b(c.this.getContext(), intent.getParcelableArrayListExtra("list"), "home", c.this.n, c.this.o));
            c.this.l = true;
            c.this.m = true;
            c cVar11 = c.this;
            cVar11.a(cVar11.k, true);
            b.a(context).a((BroadcastReceiver) this);
        }
    };
    /* access modifiers changed from: private */
    public String q = "List3DView";
    private Adapter r;
    private VelocityTracker s;
    private Runnable t;
    private final LinkedList<View> u = new LinkedList<>();
    private Runnable v;
    private Rect w;
    private Camera x;
    private Matrix y;
    private Paint z;

    public View getSelectedView() {
        return null;
    }

    public c(Context context, AttributeSet attributeSet, String str, String str2) {
        super(context, attributeSet);
        this.n = str;
        this.o = str2;
    }

    public void setTag(String str) {
        this.q = str;
    }

    public void a() {
        this.l = true;
    }

    public void setHint(boolean z2) {
        this.C = z2;
    }

    public boolean b() {
        return this.C;
    }

    public boolean c() {
        return this.B;
    }

    public void setFade(boolean z2) {
        this.B = z2;
    }

    public void setAdapter(Adapter adapter) {
        if (d() && c()) {
            com.startapp.common.a.c.a((View) this, 0.0f);
        }
        this.r = adapter;
        removeAllViewsInLayout();
        requestLayout();
    }

    public Adapter getAdapter() {
        return this.r;
    }

    public void setSelection(int i2) {
        throw new UnsupportedOperationException("Not supported");
    }

    private boolean d() {
        return com.startapp.common.a.c.a();
    }

    public void setDynamics(Dynamics dynamics) {
        Dynamics dynamics2 = this.j;
        if (dynamics2 != null) {
            dynamics.a(dynamics2.a(), this.j.b(), AnimationUtils.currentAnimationTimeMillis());
        }
        this.j = dynamics;
    }

    private void e() {
        if (!this.D) {
            this.D = true;
            dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 0, 0.0f, 0.0f, 0));
            postDelayed(new Runnable() {
                public void run() {
                    c.this.dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 2, 0.0f, -20.0f, 0));
                    c.this.dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 1, 0.0f, -20.0f, 0));
                }
            }, 5);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getChildCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            float f2 = 0.0f;
            if (action == 1) {
                int i2 = this.f69a;
                if (i2 == 1) {
                    b((int) motionEvent.getX(), (int) motionEvent.getY());
                } else if (i2 == 2) {
                    this.s.addMovement(motionEvent);
                    this.s.computeCurrentVelocity(1000);
                    f2 = this.s.getYVelocity();
                    this.k = f2;
                }
                b(f2);
            } else if (action != 2) {
                b(0.0f);
            } else {
                if (this.f69a == 1) {
                    b(motionEvent);
                }
                if (this.f69a == 2) {
                    this.s.addMovement(motionEvent);
                    a(((int) motionEvent.getY()) - this.c);
                }
            }
        } else {
            if (d()) {
                com.startapp.common.a.c.a((View) this, 1500);
            }
            a(motionEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (this.l && this.r != null) {
            if (getChildCount() == 0) {
                if (b()) {
                    this.e = getHeight() / 3;
                }
                if (!this.m) {
                    this.i = -1;
                } else {
                    int i6 = this.h;
                    this.i = i6;
                    this.h = i6 + 1;
                }
                c(this.e, 0);
            } else {
                int a2 = (this.e + this.f) - a(getChildAt(0));
                c(a2);
                d(a2);
            }
            h();
            if (b()) {
                e();
            }
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j2) {
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            return super.drawChild(canvas, view, j2);
        }
        int top = view.getTop();
        int left = view.getLeft();
        int width = view.getWidth() / 2;
        int height = view.getHeight() / 2;
        float height2 = (float) (getHeight() / 2);
        float f2 = (((float) (top + height)) - height2) / height2;
        float cos = (float) (1.0d - ((1.0d - Math.cos((double) f2)) * 0.15000000596046448d));
        float f3 = (((float) this.g) - (f2 * 20.0f)) % 90.0f;
        if (f3 < 0.0f) {
            f3 += 90.0f;
        }
        float f4 = f3;
        if (f4 < 45.0f) {
            Canvas canvas2 = canvas;
            Bitmap bitmap = drawingCache;
            int i2 = top;
            int i3 = left;
            int i4 = width;
            int i5 = height;
            float f5 = cos;
            a(canvas2, bitmap, i2, i3, i4, i5, f5, f4 - 90.0f);
            a(canvas2, bitmap, i2, i3, i4, i5, f5, f4);
            return false;
        }
        Canvas canvas3 = canvas;
        Bitmap bitmap2 = drawingCache;
        int i6 = top;
        int i7 = left;
        int i8 = width;
        int i9 = height;
        float f6 = cos;
        a(canvas3, bitmap2, i6, i7, i8, i9, f6, f4);
        a(canvas3, bitmap2, i6, i7, i8, i9, f6, f4 - 90.0f);
        return false;
    }

    private void a(Canvas canvas, Bitmap bitmap, int i2, int i3, int i4, int i5, float f2, float f3) {
        if (this.x == null) {
            this.x = new Camera();
        }
        this.x.save();
        this.x.translate(0.0f, 0.0f, (float) i5);
        this.x.rotateX(f3);
        float f4 = (float) (-i5);
        this.x.translate(0.0f, 0.0f, f4);
        if (this.y == null) {
            this.y = new Matrix();
        }
        this.x.getMatrix(this.y);
        this.x.restore();
        this.y.preTranslate((float) (-i4), f4);
        this.y.postScale(f2, f2);
        this.y.postTranslate((float) (i3 + i4), (float) (i2 + i5));
        if (this.z == null) {
            Paint paint = new Paint();
            this.z = paint;
            paint.setAntiAlias(true);
            this.z.setFilterBitmap(true);
        }
        this.z.setColorFilter(a(f3));
        canvas.drawBitmap(bitmap, this.y, this.z);
    }

    private LightingColorFilter a(float f2) {
        double d2 = (double) f2;
        Double.isNaN(d2);
        double cos = Math.cos((d2 * 3.141592653589793d) / 180.0d);
        int i2 = ((int) (cos * 200.0d)) + 55;
        int pow = (int) (Math.pow(cos, 200.0d) * 70.0d);
        if (i2 > 255) {
            i2 = 255;
        }
        if (pow > 255) {
            pow = 255;
        }
        return new LightingColorFilter(Color.rgb(i2, i2, i2), Color.rgb(pow, pow, pow));
    }

    private void a(MotionEvent motionEvent) {
        removeCallbacks(this.t);
        this.b = (int) motionEvent.getX();
        this.c = (int) motionEvent.getY();
        this.d = a(getChildAt(0)) - this.f;
        g();
        VelocityTracker obtain = VelocityTracker.obtain();
        this.s = obtain;
        obtain.addMovement(motionEvent);
        this.f69a = 1;
    }

    private void b(float f2) {
        a(f2, false);
    }

    /* access modifiers changed from: protected */
    public void a(float f2, boolean z2) {
        if (this.s != null || z2) {
            VelocityTracker velocityTracker = this.s;
            if (velocityTracker != null) {
                velocityTracker.recycle();
            }
            this.s = null;
            removeCallbacks(this.v);
            if (this.t == null) {
                this.t = new Runnable() {
                    public void run() {
                        if (c.this.j != null) {
                            View childAt = c.this.getChildAt(0);
                            if (childAt != null) {
                                c cVar = c.this;
                                cVar.d = cVar.a(childAt) - c.this.f;
                                c.this.j.a(AnimationUtils.currentAnimationTimeMillis());
                                c cVar2 = c.this;
                                cVar2.a(((int) cVar2.j.a()) - c.this.d);
                            }
                            if (!c.this.j.a(0.5f, 0.4f)) {
                                c.this.postDelayed(this, 16);
                            }
                        }
                    }
                };
            }
            Dynamics dynamics = this.j;
            if (dynamics != null) {
                if (!z2) {
                    dynamics.a((float) this.e, f2, AnimationUtils.currentAnimationTimeMillis());
                }
                post(this.t);
            }
            this.f69a = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.t);
    }

    /* access modifiers changed from: protected */
    public void a(int i2) {
        int i3 = this.d + i2;
        this.e = i3;
        this.g = (-(i3 * 270)) / getHeight();
        f();
        requestLayout();
    }

    private void f() {
        int i2;
        int i3 = this.g;
        int i4 = i3 % 90;
        if (i4 < 45) {
            i2 = ((-(i3 - i4)) * getHeight()) / 270;
        } else {
            i2 = ((-((i3 + 90) - i4)) * getHeight()) / 270;
        }
        if (this.A == Integer.MIN_VALUE && this.i == this.r.getCount() - 1 && c(getChildAt(getChildCount() - 1)) < getHeight()) {
            this.A = i2;
        }
        if (i2 > 0) {
            i2 = 0;
        } else {
            int i5 = this.A;
            if (i2 < i5) {
                i2 = i5;
            }
        }
        float f2 = (float) i2;
        this.j.a(f2);
        this.j.b(f2);
    }

    private void g() {
        if (this.v == null) {
            this.v = new Runnable() {
                public void run() {
                    if (c.this.f69a == 1) {
                        c cVar = c.this;
                        int a2 = cVar.a(cVar.b, c.this.c);
                        if (a2 != -1) {
                            c.this.b(a2);
                        }
                    }
                }
            };
        }
        postDelayed(this.v, (long) ViewConfiguration.getLongPressTimeout());
    }

    private boolean b(MotionEvent motionEvent) {
        int x2 = (int) motionEvent.getX();
        int y2 = (int) motionEvent.getY();
        int i2 = this.b;
        if (x2 >= i2 - 10 && x2 <= i2 + 10) {
            int i3 = this.c;
            if (y2 >= i3 - 10 && y2 <= i3 + 10) {
                return false;
            }
        }
        removeCallbacks(this.v);
        this.f69a = 2;
        return true;
    }

    /* access modifiers changed from: protected */
    public int a(int i2, int i3) {
        if (this.w == null) {
            this.w = new Rect();
        }
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            getChildAt(i4).getHitRect(this.w);
            if (this.w.contains(i2, i3)) {
                return i4;
            }
        }
        return -1;
    }

    private void b(int i2, int i3) {
        int a2 = a(i2, i3);
        if (a2 != -1) {
            View childAt = getChildAt(a2);
            int i4 = this.h + a2;
            performItemClick(childAt, i4, this.r.getItemId(i4));
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i2) {
        View childAt = getChildAt(i2);
        int i3 = this.h + i2;
        long itemId = this.r.getItemId(i3);
        AdapterView.OnItemLongClickListener onItemLongClickListener = getOnItemLongClickListener();
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClick(this, childAt, i3, itemId);
        }
    }

    private void c(int i2) {
        int childCount = getChildCount();
        if (this.i != this.r.getCount() - 1 && childCount > 1) {
            View childAt = getChildAt(0);
            while (childAt != null && c(childAt) + i2 < 0) {
                removeViewInLayout(childAt);
                childCount--;
                this.u.addLast(childAt);
                this.h++;
                this.f += d(childAt);
                childAt = childCount > 1 ? getChildAt(0) : null;
            }
        }
        if (this.h != 0 && childCount > 1) {
            View childAt2 = getChildAt(childCount - 1);
            while (childAt2 != null && a(childAt2) + i2 > getHeight()) {
                removeViewInLayout(childAt2);
                childCount--;
                this.u.addLast(childAt2);
                this.i--;
                childAt2 = childCount > 1 ? getChildAt(childCount - 1) : null;
            }
        }
    }

    private void d(int i2) {
        c(c(getChildAt(getChildCount() - 1)), i2);
        d(a(getChildAt(0)), i2);
    }

    private void c(int i2, int i3) {
        while (i2 + i3 < getHeight() && this.i < this.r.getCount() - 1) {
            int i4 = this.i + 1;
            this.i = i4;
            View view = this.r.getView(i4, getCachedView(), this);
            a(view, 0);
            i2 += d(view);
        }
    }

    private void d(int i2, int i3) {
        int i4;
        while (i2 + i3 > 0 && (i4 = this.h) > 0) {
            int i5 = i4 - 1;
            this.h = i5;
            View view = this.r.getView(i5, getCachedView(), this);
            a(view, 1);
            int d2 = d(view);
            i2 -= d2;
            this.f -= d2;
        }
    }

    private void a(View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        int i3 = i2 == 1 ? 0 : -1;
        view.setDrawingCacheEnabled(true);
        addViewInLayout(view, i3, layoutParams, true);
        view.measure(((int) (((float) getWidth()) * 0.85f)) | 1073741824, 0);
    }

    private void h() {
        int i2 = this.e + this.f;
        float width = ((float) getWidth()) * 0.0f;
        float height = 1.0f / (((float) getHeight()) * 0.9f);
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            double d2 = (double) width;
            double d3 = (double) height;
            Double.isNaN(d3);
            double d4 = (double) i2;
            Double.isNaN(d4);
            double sin = Math.sin(d3 * 6.283185307179586d * d4);
            Double.isNaN(d2);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int width2 = ((int) (d2 * sin)) + ((getWidth() - measuredWidth) / 2);
            int b2 = b(childAt);
            int i4 = i2 + b2;
            childAt.layout(width2, i4, measuredWidth + width2, i4 + measuredHeight);
            i2 += measuredHeight + (b2 * 2);
        }
    }

    private View getCachedView() {
        if (this.u.size() != 0) {
            return this.u.removeFirst();
        }
        return null;
    }

    private int b(View view) {
        return (int) ((((float) view.getMeasuredHeight()) * 0.35000002f) / 2.0f);
    }

    /* access modifiers changed from: protected */
    public int a(View view) {
        return view.getTop() - b(view);
    }

    private int c(View view) {
        return view.getBottom() + b(view);
    }

    private int d(View view) {
        return view.getMeasuredHeight() + (b(view) * 2);
    }

    public int getFirstItemPosition() {
        return this.h;
    }

    public int getLastItemPosition() {
        return this.i;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent);
    }
}
