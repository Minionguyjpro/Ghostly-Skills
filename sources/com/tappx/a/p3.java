package com.tappx.a;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.tappx.a.s3;

public class p3 extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private final s3 f551a;
    /* access modifiers changed from: private */
    public boolean b;
    private boolean c;
    private StateListDrawable d;
    private e e;
    private d f;
    private f g;
    /* access modifiers changed from: private */
    public TextView h;
    private boolean i;
    private boolean j;
    private final s3.b k;
    private View.OnClickListener l;

    class a implements s3.b {
        a() {
        }

        public void a(int i) {
            String str;
            boolean z = true;
            boolean z2 = i <= 0;
            if (!z2 && p3.this.b) {
                z = false;
            }
            p3.this.setCloseVisible(z);
            if (z2) {
                str = null;
            } else {
                str = String.valueOf(i);
            }
            p3.this.h.setText(str);
            p3.this.h.setEnabled(z2);
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        public void onClick(View view) {
            p3.this.e();
        }
    }

    static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f554a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.tappx.a.p3$e[] r0 = com.tappx.a.p3.e.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f554a = r0
                com.tappx.a.p3$e r1 = com.tappx.a.p3.e.DISABLED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f554a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.p3$e r1 = com.tappx.a.p3.e.TRANSPARENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f554a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.p3$e r1 = com.tappx.a.p3.e.VISIBLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.p3.c.<clinit>():void");
        }
    }

    public enum d {
        TOP_LEFT(51),
        TOP_CENTER(49),
        TOP_RIGHT(53),
        CENTER(17),
        BOTTOM_LEFT(83),
        BOTTOM_CENTER(81),
        BOTTOM_RIGHT(85);
        

        /* renamed from: a  reason: collision with root package name */
        private final int f555a;

        private d(int i2) {
            this.f555a = i2;
        }

        /* access modifiers changed from: package-private */
        public int a() {
            return this.f555a;
        }
    }

    private enum e {
        VISIBLE,
        TRANSPARENT,
        DISABLED
    }

    public interface f {
        void a();
    }

    public p3(Context context) {
        this(context, new s3());
    }

    private void d() {
        this.h = new TextView(getContext());
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.d = stateListDrawable;
        stateListDrawable.addState(FrameLayout.SELECTED_STATE_SET, e4.INTERSTITIAL_CLOSE_BUTTON_PRESSED.a(getContext()));
        this.d.addState(FrameLayout.ENABLED_STATE_SET, e4.INTERSTITIAL_CLOSE_BUTTON_NORMAL.a(getContext()));
        this.d.addState(StateSet.WILD_CARD, e4.INTERSTITIAL_CLOSE_BUTTON_DISABLED.a(getContext()));
        this.h.setBackgroundDrawable(this.d);
        this.h.setOnClickListener(this.l);
        this.h.setTextColor(-1);
        this.h.setTypeface(Typeface.SANS_SERIF);
        this.h.setTextSize(18.0f);
        this.h.setGravity(17);
        c();
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.f551a.b()) {
            playSoundEffect(0);
            f fVar = this.g;
            if (fVar != null) {
                fVar.a();
            }
        }
    }

    private void f() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt != this.h) {
                removeView(childAt);
            }
        }
    }

    private void g() {
        e eVar;
        boolean z = this.i && this.j;
        boolean z2 = this.c;
        if (!z) {
            eVar = e.DISABLED;
        } else if (z2) {
            eVar = e.TRANSPARENT;
        } else {
            eVar = e.VISIBLE;
        }
        if (eVar != this.e) {
            this.e = eVar;
            a(eVar);
        }
    }

    private FrameLayout.LayoutParams getCloseButtonLayoutParams() {
        int b2 = q3.b(10.0f, getContext());
        int b3 = q3.b(30.0f, getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(b3, b3, this.f.a());
        layoutParams.setMargins(b2, b2, b2, b2);
        return layoutParams;
    }

    /* access modifiers changed from: private */
    public void setCloseVisible(boolean z) {
        this.j = z;
        g();
    }

    public void setCloseEnabled(boolean z) {
        this.i = z;
        g();
    }

    public void setCloseListener(f fVar) {
        this.g = fVar;
    }

    public void setClosePosition(d dVar) {
        this.f = dVar;
        this.h.setLayoutParams(getCloseButtonLayoutParams());
    }

    public void setInvisibleClose(boolean z) {
        this.c = z;
        g();
    }

    public p3(Context context, s3 s3Var) {
        super(context);
        this.e = e.VISIBLE;
        this.f = d.TOP_RIGHT;
        this.i = true;
        this.j = true;
        this.k = new a();
        this.l = new b();
        this.f551a = s3Var;
        s3Var.a(this.k);
        d();
    }

    private void c() {
        addView(this.h, getCloseButtonLayoutParams());
    }

    public boolean b() {
        return this.i;
    }

    public void a(int i2, boolean z) {
        if (i2 > 0) {
            this.b = z;
            this.f551a.a((long) i2);
        }
    }

    private void a(e eVar) {
        int i2 = c.f554a[eVar.ordinal()];
        int i3 = 0;
        StateListDrawable stateListDrawable = null;
        if (i2 == 1) {
            i3 = 8;
        } else if (i2 != 2) {
            stateListDrawable = this.d;
        }
        this.h.setBackgroundDrawable(stateListDrawable);
        this.h.setVisibility(i3);
    }

    public void a(View view, FrameLayout.LayoutParams layoutParams) {
        f();
        addView(view, 0, layoutParams);
    }

    public boolean a() {
        return getVisibility() == 0 && this.f551a.b();
    }
}
