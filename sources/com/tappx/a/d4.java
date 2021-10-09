package com.tappx.a;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.widget.FrameLayout;
import com.tappx.a.c4;
import com.tappx.a.p3;
import java.lang.ref.WeakReference;
import java.net.URI;

public class d4 {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Activity> f409a;
    /* access modifiers changed from: private */
    public final Context b;
    /* access modifiers changed from: private */
    public final b4 c;
    /* access modifiers changed from: private */
    public final FrameLayout d;
    private final p3 e;
    private ViewGroup f;
    private final j g;
    /* access modifiers changed from: private */
    public final n4 h;
    /* access modifiers changed from: private */
    public v4 i;
    /* access modifiers changed from: private */
    public h j;
    private k k;
    private r4 l;
    private q4 m;
    private q4 n;
    /* access modifiers changed from: private */
    public final c4 o;
    /* access modifiers changed from: private */
    public final c4 p;
    private i q;
    private Integer r;
    private boolean s;
    private l4 t;
    /* access modifiers changed from: private */
    public final k4 u;
    private boolean v;
    /* access modifiers changed from: private */
    public boolean w;
    private final c4.h x;
    private final c4.h y;

    class a implements p3.f {
        a() {
        }

        public void a() {
            d4.this.d();
        }
    }

    class b implements View.OnTouchListener {
        b(d4 d4Var) {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    class c implements c4.h {
        c() {
        }

        public boolean a(String str, JsResult jsResult) {
            return d4.this.a(str, jsResult);
        }

        public void b() {
            if (d4.this.j != null) {
                d4.this.j.d();
            }
        }

        public void c() {
            d4.this.e();
        }

        public boolean a(ConsoleMessage consoleMessage) {
            return d4.this.a(consoleMessage);
        }

        public void a() {
            d4.this.d();
        }

        public void b(boolean z) {
            if (z) {
                d4.this.i();
            }
            if (!d4.this.p.b()) {
                d4.this.o.a(z);
            }
        }

        public void a(int i, int i2, int i3, int i4, p3.d dVar, boolean z) {
            d4.this.a(i, i2, i3, i4, dVar, z);
        }

        public void a(URI uri, boolean z) {
            d4.this.a(uri, z);
        }

        public void a(boolean z) {
            d4.this.a(z);
        }

        public void a(boolean z, l4 l4Var) {
            d4.this.a(z, l4Var);
        }

        public void a(URI uri) {
            if (d4.this.j != null) {
                d4.this.j.b();
            }
        }
    }

    class d implements c4.h {
        d() {
        }

        public void a(URI uri, boolean z) {
        }

        public boolean a(String str, JsResult jsResult) {
            return d4.this.a(str, jsResult);
        }

        public void b() {
        }

        public void b(boolean z) {
            d4.this.o.a(z);
            d4.this.p.a(z);
        }

        public void c() {
            d4.this.f();
        }

        public boolean a(ConsoleMessage consoleMessage) {
            return d4.this.a(consoleMessage);
        }

        public void a(int i, int i2, int i3, int i4, p3.d dVar, boolean z) {
            throw new f4("Invalid state");
        }

        public void a() {
            d4.this.d();
        }

        public void a(boolean z) {
            d4.this.a(z);
        }

        public void a(boolean z, l4 l4Var) {
            d4.this.a(z, l4Var);
        }

        public void a(URI uri) {
            if (d4.this.j != null) {
                d4.this.j.b();
            }
        }
    }

    class e implements Runnable {
        e() {
        }

        public void run() {
            d4.this.o.a(d4.this.u.b(d4.this.b), d4.this.u.d(d4.this.b), d4.this.u.a(d4.this.b), d4.this.u.c(d4.this.b), d4.this.m());
            d4.this.o.a(d4.this.c);
            d4.this.o.a(d4.this.o.d());
            d4.this.o.e();
        }
    }

    class f implements Runnable {
        f() {
        }

        public void run() {
            d4.this.p.a(d4.this.u.b(d4.this.b), d4.this.u.d(d4.this.b), d4.this.u.a(d4.this.b), d4.this.u.c(d4.this.b), d4.this.m());
            d4.this.p.a(d4.this.i);
            d4.this.p.a(d4.this.c);
            d4.this.p.a(d4.this.p.d());
            d4.this.p.e();
        }
    }

    class g implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ View f415a;
        final /* synthetic */ Runnable b;

        g(View view, Runnable runnable) {
            this.f415a = view;
            this.b = runnable;
        }

        public void run() {
            DisplayMetrics displayMetrics = d4.this.b.getResources().getDisplayMetrics();
            d4.this.h.a(displayMetrics.widthPixels, displayMetrics.heightPixels);
            int[] iArr = new int[2];
            ViewGroup f = d4.this.l();
            f.getLocationOnScreen(iArr);
            d4.this.h.c(iArr[0], iArr[1], f.getWidth(), f.getHeight());
            d4.this.d.getLocationOnScreen(iArr);
            d4.this.h.b(iArr[0], iArr[1], d4.this.d.getWidth(), d4.this.d.getHeight());
            this.f415a.getLocationOnScreen(iArr);
            d4.this.h.a(iArr[0], iArr[1], this.f415a.getWidth(), this.f415a.getHeight());
            d4.this.o.a(d4.this.h);
            if (d4.this.p.b()) {
                d4.this.p.a(d4.this.h);
            }
            Runnable runnable = this.b;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public interface h {
        void a();

        void a(View view);

        void b();

        void c();

        void d();
    }

    static class j {

        /* renamed from: a  reason: collision with root package name */
        private final Handler f417a = new Handler(Looper.getMainLooper());
        private a b;

        static class a {
            /* access modifiers changed from: private */

            /* renamed from: a  reason: collision with root package name */
            public final View[] f418a;
            private final Handler b;
            private Runnable c;
            int d;
            private final Runnable e;

            /* renamed from: com.tappx.a.d4$j$a$a  reason: collision with other inner class name */
            class C0015a implements Runnable {

                /* renamed from: com.tappx.a.d4$j$a$a$a  reason: collision with other inner class name */
                class C0016a implements ViewTreeObserver.OnPreDrawListener {

                    /* renamed from: a  reason: collision with root package name */
                    final /* synthetic */ View f420a;

                    C0016a(View view) {
                        this.f420a = view;
                    }

                    public boolean onPreDraw() {
                        this.f420a.getViewTreeObserver().removeOnPreDrawListener(this);
                        a.this.b();
                        return true;
                    }
                }

                C0015a() {
                }

                public void run() {
                    for (View view : a.this.f418a) {
                        if (view.getHeight() > 0 || view.getWidth() > 0) {
                            a.this.b();
                        } else {
                            view.getViewTreeObserver().addOnPreDrawListener(new C0016a(view));
                        }
                    }
                }
            }

            /* synthetic */ a(Handler handler, View[] viewArr, a aVar) {
                this(handler, viewArr);
            }

            private a(Handler handler, View[] viewArr) {
                this.e = new C0015a();
                this.b = handler;
                this.f418a = viewArr;
            }

            /* access modifiers changed from: private */
            public void b() {
                Runnable runnable;
                int i = this.d - 1;
                this.d = i;
                if (i == 0 && (runnable = this.c) != null) {
                    runnable.run();
                    this.c = null;
                }
            }

            /* access modifiers changed from: package-private */
            public void a(Runnable runnable) {
                this.c = runnable;
                this.d = this.f418a.length;
                this.b.post(this.e);
            }

            /* access modifiers changed from: package-private */
            public void a() {
                this.b.removeCallbacks(this.e);
                this.c = null;
            }
        }

        j() {
        }

        /* access modifiers changed from: package-private */
        public a a(View... viewArr) {
            a aVar = new a(this.f417a, viewArr, (a) null);
            this.b = aVar;
            return aVar;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            a aVar = this.b;
            if (aVar != null) {
                aVar.a();
                this.b = null;
            }
        }
    }

    public interface k {
        void a(boolean z);
    }

    public d4(Context context, b4 b4Var) {
        this(context, b4Var, new c4(b4Var), new c4(b4.INTERSTITIAL), new j());
    }

    d4(Context context, b4 b4Var, c4 c4Var, c4 c4Var2, j jVar) {
        this.i = v4.LOADING;
        this.q = new i();
        this.s = true;
        this.t = l4.NONE;
        this.w = false;
        this.x = new c();
        this.y = new d();
        this.b = context.getApplicationContext();
        if (context instanceof Activity) {
            this.f409a = new WeakReference<>((Activity) context);
        } else {
            this.f409a = new WeakReference<>((Object) null);
        }
        this.c = b4Var;
        this.o = c4Var;
        this.p = c4Var2;
        this.g = jVar;
        this.i = v4.LOADING;
        this.h = new n4(this.b, this.b.getResources().getDisplayMetrics().density);
        this.d = new FrameLayout(this.b);
        p3 p3Var = new p3(this.b);
        this.e = p3Var;
        p3Var.setCloseListener(new a());
        View view = new View(this.b);
        view.setOnTouchListener(new b(this));
        this.e.addView(view, new FrameLayout.LayoutParams(-1, -1));
        this.q.a(this.b);
        this.o.a(this.x);
        this.p.a(this.y);
        this.u = new k4();
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.w) {
            this.w = false;
            a((Runnable) null);
        }
    }

    private View j() {
        return this.p.b() ? this.n : this.m;
    }

    /* access modifiers changed from: private */
    public int k() {
        return ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    /* access modifiers changed from: private */
    public ViewGroup l() {
        ViewGroup viewGroup = this.f;
        if (viewGroup != null) {
            return viewGroup;
        }
        View a2 = w4.a((Context) this.f409a.get(), this.d);
        return a2 instanceof ViewGroup ? (ViewGroup) a2 : this.d;
    }

    /* access modifiers changed from: private */
    public boolean m() {
        Activity activity = (Activity) this.f409a.get();
        if (activity == null || j() == null) {
            return false;
        }
        return this.u.a(activity, j());
    }

    public void b(boolean z) {
        this.v = true;
        q4 q4Var = this.m;
        if (q4Var != null) {
            x4.a(q4Var, z);
        }
        q4 q4Var2 = this.n;
        if (q4Var2 != null) {
            x4.a(q4Var2, z);
        }
    }

    public FrameLayout c() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        v4 v4Var;
        q4 q4Var;
        if (this.m != null && (v4Var = this.i) != v4.LOADING && v4Var != v4.HIDDEN) {
            if (v4Var == v4.EXPANDED || this.c == b4.INTERSTITIAL) {
                h();
            }
            v4 v4Var2 = this.i;
            if (v4Var2 == v4.RESIZED || v4Var2 == v4.EXPANDED) {
                if (!this.p.b() || (q4Var = this.n) == null) {
                    this.e.removeView(this.m);
                    this.d.addView(this.m, new FrameLayout.LayoutParams(-1, -1));
                    this.d.setVisibility(0);
                } else {
                    this.e.removeView(q4Var);
                    this.p.a();
                }
                l().removeView(this.e);
                a(v4.DEFAULT);
            } else if (v4Var2 == v4.DEFAULT) {
                this.d.setVisibility(4);
                a(v4.HIDDEN);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void e() {
        a(v4.DEFAULT, (Runnable) new e());
        h hVar = this.j;
        if (hVar != null) {
            hVar.a(this.d);
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        a((Runnable) new f());
    }

    public void g() {
        this.v = false;
        q4 q4Var = this.m;
        if (q4Var != null) {
            x4.a(q4Var);
        }
        q4 q4Var2 = this.n;
        if (q4Var2 != null) {
            x4.a(q4Var2);
        }
    }

    /* access modifiers changed from: package-private */
    public void h() {
        Integer num;
        Activity activity = (Activity) this.f409a.get();
        if (!(activity == null || (num = this.r) == null)) {
            try {
                activity.setRequestedOrientation(num.intValue());
            } catch (Exception unused) {
            }
        }
        this.r = null;
    }

    class i extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        private Context f416a;
        private int b = -1;

        i() {
        }

        public void a(Context context) {
            Context applicationContext = context.getApplicationContext();
            this.f416a = applicationContext;
            if (applicationContext != null) {
                applicationContext.registerReceiver(this, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
            }
        }

        public void onReceive(Context context, Intent intent) {
            int h;
            if (this.f416a != null && "android.intent.action.CONFIGURATION_CHANGED".equals(intent.getAction()) && (h = d4.this.k()) != this.b) {
                boolean unused = d4.this.w = true;
                this.b = h;
                d4.this.a(h);
            }
        }

        public void a() {
            Context context = this.f416a;
            if (context != null) {
                context.unregisterReceiver(this);
                this.f416a = null;
            }
        }
    }

    public void a(h hVar) {
        this.j = hVar;
    }

    public void a(k kVar) {
        this.k = kVar;
    }

    public void a(String str) {
        q4 q4Var = new q4(this.b);
        this.m = q4Var;
        this.o.a(q4Var);
        this.d.addView(this.m, new FrameLayout.LayoutParams(-1, -1));
        this.o.c(str);
    }

    public void b() {
        this.g.a();
        try {
            this.q.a();
        } catch (IllegalArgumentException e2) {
            if (!e2.getMessage().contains("Receiver not registered")) {
                throw e2;
            }
        }
        if (!this.v) {
            b(true);
        }
        w4.b(this.e);
        this.o.a();
        q4 q4Var = this.m;
        if (q4Var != null) {
            q4Var.destroy();
            this.m = null;
        }
        this.p.a();
        q4 q4Var2 = this.n;
        if (q4Var2 != null) {
            q4Var2.destroy();
            this.n = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(ConsoleMessage consoleMessage) {
        r4 r4Var = this.l;
        if (r4Var != null) {
            return r4Var.a(consoleMessage);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str, JsResult jsResult) {
        r4 r4Var = this.l;
        if (r4Var != null) {
            return r4Var.a(str, jsResult);
        }
        jsResult.confirm();
        return true;
    }

    private void a(Runnable runnable) {
        this.g.a();
        View j2 = j();
        if (j2 != null) {
            this.g.a(this.d, j2).a((Runnable) new g(j2, runnable));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        a((Runnable) null);
    }

    private void a(v4 v4Var) {
        a(v4Var, (Runnable) null);
    }

    private void a(v4 v4Var, Runnable runnable) {
        j4.a("MRAID state set to " + v4Var);
        v4 v4Var2 = this.i;
        this.i = v4Var;
        this.o.a(v4Var);
        if (this.p.c()) {
            this.p.a(v4Var);
        }
        h hVar = this.j;
        if (hVar != null) {
            v4 v4Var3 = v4.EXPANDED;
            if (v4Var == v4Var3) {
                hVar.c();
            } else if (v4Var2 == v4Var3 && v4Var == v4.DEFAULT) {
                hVar.a();
            } else if (v4Var == v4.HIDDEN) {
                this.j.a();
            }
        }
        a(runnable);
    }

    /* access modifiers changed from: package-private */
    public void b(int i2) {
        Activity activity = (Activity) this.f409a.get();
        if (activity == null || !a(this.t)) {
            throw new f4("Invalid vale: " + this.t.name());
        }
        if (this.r == null) {
            this.r = Integer.valueOf(activity.getRequestedOrientation());
        }
        try {
            activity.setRequestedOrientation(i2);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public int a(int i2, int i3, int i4) {
        return Math.max(i2, Math.min(i3, i4));
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3, int i4, int i5, p3.d dVar, boolean z) {
        if (this.m != null) {
            v4 v4Var = this.i;
            if (v4Var != v4.LOADING && v4Var != v4.HIDDEN) {
                if (v4Var != v4.EXPANDED) {
                    b4 b4Var = b4.INTERSTITIAL;
                    int d2 = q3.d((float) i2, this.b);
                    int d3 = q3.d((float) i3, this.b);
                    int d4 = q3.d((float) i4, this.b);
                    int d5 = q3.d((float) i5, this.b);
                    int i6 = this.h.c().left + d4;
                    int i7 = this.h.c().top + d5;
                    Rect rect = new Rect(i6, i7, d2 + i6, d3 + i7);
                    if (!z) {
                        Rect e2 = this.h.e();
                        if (rect.width() > e2.width() || rect.height() > e2.height()) {
                            throw new f4("Resize invalid)");
                        }
                        rect.offsetTo(a(e2.left, rect.left, e2.right - rect.width()), a(e2.top, rect.top, e2.bottom - rect.height()));
                    }
                    this.e.setInvisibleClose(true);
                    this.e.setClosePosition(dVar);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(rect.width(), rect.height());
                    layoutParams.leftMargin = rect.left - this.h.e().left;
                    layoutParams.topMargin = rect.top - this.h.e().top;
                    v4 v4Var2 = this.i;
                    if (v4Var2 == v4.DEFAULT) {
                        this.d.removeView(this.m);
                        this.d.setVisibility(4);
                        this.e.a((View) this.m, new FrameLayout.LayoutParams(-1, -1));
                        l().addView(this.e, layoutParams);
                    } else if (v4Var2 == v4.RESIZED) {
                        this.e.setLayoutParams(layoutParams);
                    }
                    this.e.setClosePosition(dVar);
                    a(v4.RESIZED);
                    return;
                }
                throw new f4("Invalid status change");
            }
            return;
        }
        throw new f4("View destroyed, ignoring");
    }

    /* access modifiers changed from: package-private */
    public void a(URI uri, boolean z) {
        if (this.m != null) {
            b4 b4Var = b4.INTERSTITIAL;
            v4 v4Var = this.i;
            if (v4Var == v4.DEFAULT || v4Var == v4.RESIZED) {
                a();
                boolean z2 = uri != null;
                if (z2) {
                    q4 q4Var = new q4(this.b);
                    this.n = q4Var;
                    this.p.a(q4Var);
                    this.p.d(uri.toString());
                }
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                v4 v4Var2 = this.i;
                if (v4Var2 == v4.DEFAULT) {
                    if (z2) {
                        this.e.a((View) this.n, layoutParams);
                    } else {
                        this.d.removeView(this.m);
                        this.d.setVisibility(4);
                        this.e.a((View) this.m, layoutParams);
                    }
                    l().addView(this.e, new FrameLayout.LayoutParams(-1, -1));
                } else if (v4Var2 == v4.RESIZED && z2) {
                    this.e.removeView(this.m);
                    this.d.addView(this.m, layoutParams);
                    this.d.setVisibility(4);
                    this.e.a((View) this.n, layoutParams);
                }
                this.e.setLayoutParams(layoutParams);
                a(z);
                a(v4.EXPANDED);
                return;
            }
            return;
        }
        throw new f4("View destroyed, ignoring");
    }

    /* access modifiers changed from: package-private */
    public void a() {
        l4 l4Var = this.t;
        if (l4Var != l4.NONE) {
            b(l4Var.a());
        } else if (this.s) {
            h();
        } else {
            Activity activity = (Activity) this.f409a.get();
            if (activity != null) {
                b(a(activity));
                return;
            }
            throw new f4("Context is not an Activity");
        }
    }

    public static int a(Activity activity) {
        return q3.a(activity.getWindowManager().getDefaultDisplay().getRotation(), activity.getResources().getConfiguration().orientation);
    }

    /* access modifiers changed from: package-private */
    public boolean a(l4 l4Var) {
        if (l4Var == l4.NONE) {
            return true;
        }
        Activity activity = (Activity) this.f409a.get();
        if (activity == null) {
            return false;
        }
        try {
            ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(new ComponentName(activity, activity.getClass()), 0);
            int i2 = activityInfo.screenOrientation;
            if (i2 == -1) {
                boolean a2 = u4.a(activityInfo.configChanges, 128);
                if (Build.VERSION.SDK_INT < 13) {
                    return a2;
                }
                if (!a2 || !u4.a(activityInfo.configChanges, (int) com.appnext.base.b.d.fb)) {
                    return false;
                }
                return true;
            } else if (i2 == l4Var.a()) {
                return true;
            } else {
                return false;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (z != (!this.e.b())) {
            this.e.setCloseEnabled(!z);
            k kVar = this.k;
            if (kVar != null) {
                kVar.a(z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z, l4 l4Var) {
        if (a(l4Var)) {
            this.s = z;
            this.t = l4Var;
            if (this.i == v4.EXPANDED || this.c == b4.INTERSTITIAL) {
                a();
                return;
            }
            return;
        }
        throw new f4("Unable to force orientation to " + l4Var);
    }
}
