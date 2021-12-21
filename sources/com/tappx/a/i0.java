package com.tappx.a;

import com.tappx.a.a0;
import com.tappx.a.d0;
import com.tappx.a.f0;
import com.tappx.a.s5;
import com.tappx.a.u5;
import java.util.Map;

final class i0<T> extends s5<T> {
    private final d0<T> q;

    class a implements u5.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ d0 f464a;

        a(d0 d0Var) {
            this.f464a = d0Var;
        }

        public void a(z5 z5Var) {
            a0 a0Var;
            f0.a b = this.f464a.b();
            if (b != null) {
                if (z5Var instanceof z) {
                    a0Var = ((z) z5Var).a();
                } else {
                    q5 q5Var = z5Var.f639a;
                    Map<String, String> map = q5Var != null ? q5Var.c : null;
                    q5 q5Var2 = z5Var.f639a;
                    int i = q5Var2 != null ? q5Var2.f563a : -1;
                    if (i0.d(z5Var)) {
                        a0Var = new a0(a0.a.SERVER_ERROR, map, i);
                    } else {
                        a0Var = new a0(a0.a.NETWORK_ERROR, map, i);
                    }
                }
                b.a(a0Var);
            }
        }
    }

    static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f465a;
        static final /* synthetic */ int[] b;

        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        static {
            /*
                com.tappx.a.d0$b[] r0 = com.tappx.a.d0.b.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r1 = 1
                com.tappx.a.d0$b r2 = com.tappx.a.d0.b.LOW     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.d0$b r3 = com.tappx.a.d0.b.HIGH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.d0$b r4 = com.tappx.a.d0.b.IMMEDIATE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = b     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.d0$b r5 = com.tappx.a.d0.b.NORMAL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.tappx.a.d0$a[] r4 = com.tappx.a.d0.a.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f465a = r4
                com.tappx.a.d0$a r5 = com.tappx.a.d0.a.POST     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = f465a     // Catch:{ NoSuchFieldError -> 0x004e }
                com.tappx.a.d0$a r4 = com.tappx.a.d0.a.DELETE     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = f465a     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.tappx.a.d0$a r1 = com.tappx.a.d0.a.HEAD     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = f465a     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.tappx.a.d0$a r1 = com.tappx.a.d0.a.OPTIONS     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = f465a     // Catch:{ NoSuchFieldError -> 0x006d }
                com.tappx.a.d0$a r1 = com.tappx.a.d0.a.PATCH     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = f465a     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.tappx.a.d0$a r1 = com.tappx.a.d0.a.PUT     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = f465a     // Catch:{ NoSuchFieldError -> 0x0083 }
                com.tappx.a.d0$a r1 = com.tappx.a.d0.a.GET     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.i0.b.<clinit>():void");
        }
    }

    public i0(d0<T> d0Var) {
        super(a(d0Var.d()), d0Var.g(), new a(d0Var));
        this.q = d0Var;
        a(d0Var);
        a(d0Var.i());
    }

    private static int a(d0.a aVar) {
        switch (b.f465a[aVar.ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 7;
            case 6:
                return 2;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: private */
    public static boolean d(z5 z5Var) {
        int i;
        q5 q5Var = z5Var.f639a;
        if (q5Var != null && (i = q5Var.f563a) >= 500 && i <= 599) {
            return true;
        }
        return false;
    }

    public byte[] b() {
        return this.q.a();
    }

    public Map<String, String> f() {
        return this.q.c();
    }

    public byte[] j() {
        return b();
    }

    public s5.c m() {
        return a(this.q.e());
    }

    /* access modifiers changed from: protected */
    public u5<T> a(q5 q5Var) {
        f0<T> a2 = this.q.a(new c0(q5Var.f563a, q5Var.c, q5Var.b, q5Var.f));
        if (a2.a()) {
            return u5.a(a2.f435a, f6.a(q5Var));
        }
        return u5.a(new z(a2.b));
    }

    /* access modifiers changed from: protected */
    public void a(T t) {
        this.q.a(t);
    }

    /* access modifiers changed from: package-private */
    public void a(d0<T> d0Var) {
        g0 f = d0Var.f();
        if (f != null) {
            b(f.d());
            a((w5) new k5(f.b(), f.c(), f.a()));
        }
    }

    private s5.c a(d0.b bVar) {
        int i = b.b[bVar.ordinal()];
        if (i == 1) {
            return s5.c.LOW;
        }
        if (i == 2) {
            return s5.c.HIGH;
        }
        if (i != 3) {
            return s5.c.NORMAL;
        }
        return s5.c.IMMEDIATE;
    }
}
