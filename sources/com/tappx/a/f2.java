package com.tappx.a;

import android.content.Context;
import com.tappx.a.q1;
import com.tappx.sdk.android.AdRequest;
import com.tappx.sdk.android.TappxAdError;

public abstract class f2 implements q1.a {

    /* renamed from: a  reason: collision with root package name */
    private final q1 f437a;
    private final Context b;
    private final t2 c;
    private final v1 d;
    private final h1 e;
    private final s1 f;
    protected final n2 g;
    protected AdRequest h;
    private String i;
    private String j;
    boolean k;

    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AdRequest f438a;

        a(AdRequest adRequest) {
            this.f438a = adRequest;
        }

        public void run() {
            f2.this.c(this.f438a);
        }
    }

    static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f439a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.tappx.a.a2[] r0 = com.tappx.a.a2.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f439a = r0
                com.tappx.a.a2 r1 = com.tappx.a.a2.DEVELOPER_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f439a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.a2 r1 = com.tappx.a.a2.INTERNAL_ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f439a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.a2 r1 = com.tappx.a.a2.NETWORK_ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f439a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.a2 r1 = com.tappx.a.a2.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f439a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.tappx.a.a2 r1 = com.tappx.a.a2.SERVER_ERROR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f439a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.tappx.a.a2 r1 = com.tappx.a.a2.UNSPECIFIED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.f2.b.<clinit>():void");
        }
    }

    private static final class c extends Exception {
        private c() {
        }

        /* synthetic */ c(a aVar) {
            this();
        }
    }

    f2(Context context, v1 v1Var) {
        this(context, v1Var, o2.a(context).c(), new s1(context));
    }

    private c e() {
        return c.a(this.b);
    }

    private void f() {
        boolean z = false;
        if (this.i == null) {
            j0.b(f.b("ql/gpRHeskeYCNYrbDS7nxNb5jI2ynHn201S0j/Gqul8JUVryuBrMPjdaYQ+79ST"), new Object[0]);
            z = true;
        }
        if (z) {
            a(TappxAdError.DEVELOPER_ERROR);
            throw new c((a) null);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(TappxAdError tappxAdError);

    public Context b() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public abstract void b(w1 w1Var);

    public void c() {
        b(this.h);
    }

    /* access modifiers changed from: protected */
    public void d() {
    }

    f2(Context context, v1 v1Var, n2 n2Var, s1 s1Var) {
        this.b = context;
        this.d = v1Var;
        c e2 = e();
        this.g = n2Var;
        this.f437a = e2.o();
        this.e = e2.g();
        this.f437a.a(this);
        this.c = e2.e();
        this.f = s1Var;
    }

    /* access modifiers changed from: private */
    public void c(AdRequest adRequest) {
        j0.a(f.b("mo5jy7IL/t1GLb3J/P8gjQ"), new Object[0]);
        j0.d("CpJSwAt+xAYUOl939gSabw", new Object[0]);
        this.h = adRequest;
        if (adRequest == null) {
            adRequest = new AdRequest();
        }
        try {
            f();
            if (!this.e.a()) {
                a(TappxAdError.NETWORK_ERROR);
                return;
            }
            d();
            this.f437a.a(this.i, this.j, this.d, adRequest);
        } catch (c unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.j = str;
    }

    public void b(String str) {
        this.i = str;
    }

    public void a() {
        this.k = true;
        this.f437a.destroy();
    }

    /* access modifiers changed from: protected */
    public void b(AdRequest adRequest) {
        try {
            this.f.a();
            this.g.a((Runnable) new a(adRequest));
        } catch (Exception unused) {
            a(TappxAdError.NO_FILL);
        }
    }

    public void a(AdRequest adRequest) {
        b(adRequest);
    }

    public final void a(w1 w1Var) {
        if (!this.k) {
            this.g.a(w1Var.a(), w1Var.e(), w1Var.d());
            j0.a(f.b("ftLVnAFo4UVdmS7TEXHP3z1+tuYsCsVdhGwkH7sMMCI"), new Object[0]);
            j0.d("7+KAkb3Ej2KFLftBLdWrHXNw5SyHuZNhHCgeqkrxnXg", new Object[0]);
            b(w1Var);
        }
    }

    /* access modifiers changed from: package-private */
    public TappxAdError b(a2 a2Var) {
        int i2 = b.f439a[a2Var.ordinal()];
        if (i2 == 1) {
            return TappxAdError.DEVELOPER_ERROR;
        }
        if (i2 == 2) {
            return TappxAdError.INTERNAL_ERROR;
        }
        if (i2 == 3) {
            return TappxAdError.NETWORK_ERROR;
        }
        if (i2 == 4) {
            return TappxAdError.NO_FILL;
        }
        if (i2 != 5) {
            return TappxAdError.UNSPECIFIED;
        }
        return TappxAdError.SERVER_ERROR;
    }

    public final void a(a2 a2Var) {
        if (!this.k) {
            TappxAdError b2 = b(a2Var);
            String b3 = f.b("8V2SkLfQtXT7yOPHxqrPlAg6jp+lx+rvQTk+I2vfHWM");
            j0.a(b3 + b2, new Object[0]);
            j0.d("lgaGjSo8VdlXgzQ7qLaLqzOElG/CkYie3dvHgxY0q1o", String.valueOf(b2));
            a(b2);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(String str) {
        if (str != null) {
            this.c.a(str);
        }
    }
}
