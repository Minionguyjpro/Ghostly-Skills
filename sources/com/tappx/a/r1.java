package com.tappx.a;

import com.tappx.a.q1;
import com.tappx.a.v;
import com.tappx.sdk.android.AdRequest;

public class r1 implements q1 {

    /* renamed from: a  reason: collision with root package name */
    private final v f565a;
    private final k0 b;
    private final d2 c;
    private q1.a d;
    private v.b e;

    class a implements m<w1> {
        a() {
        }

        public void a(w1 w1Var) {
            r1.this.b(w1Var);
        }
    }

    class b implements h<v.a> {
        b() {
        }

        public void a(v.a aVar) {
            r1.this.b(aVar);
        }
    }

    static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f568a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.tappx.a.v$a[] r0 = com.tappx.a.v.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f568a = r0
                com.tappx.a.v$a r1 = com.tappx.a.v.a.DEVELOPER_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f568a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.v$a r1 = com.tappx.a.v.a.SERVER_ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f568a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.v$a r1 = com.tappx.a.v.a.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f568a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.v$a r1 = com.tappx.a.v.a.NETWORK_ERROR     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.r1.c.<clinit>():void");
        }
    }

    public r1(v vVar, k0 k0Var, d2 d2Var) {
        this.f565a = vVar;
        this.b = k0Var;
        this.c = d2Var;
    }

    /* access modifiers changed from: private */
    public void b(w1 w1Var) {
        if (w1Var.f()) {
            a(a2.NO_FILL);
        } else {
            a(w1Var);
        }
    }

    public void destroy() {
        a();
    }

    public void a(q1.a aVar) {
        this.d = aVar;
    }

    public void a(String str, String str2, v1 v1Var, AdRequest adRequest) {
        a();
        this.e = this.f565a.a(this.b.a(str, v1Var, str2, adRequest), (m<w1>) new a(), (h<v.a>) new b());
        this.c.a();
    }

    /* access modifiers changed from: private */
    public void b(v.a aVar) {
        a(aVar);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        v.b bVar = this.e;
        if (bVar != null) {
            this.f565a.a(bVar);
            this.e = null;
        }
    }

    private void a(v.a aVar) {
        int i = c.f568a[aVar.ordinal()];
        if (i == 1) {
            a(a2.DEVELOPER_ERROR);
        } else if (i == 2) {
            a(a2.SERVER_ERROR);
        } else if (i == 3) {
            a(a2.NO_FILL);
        } else if (i != 4) {
            a(a2.UNSPECIFIED);
        } else {
            a(a2.NETWORK_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    public void a(a2 a2Var) {
        this.e = null;
        q1.a aVar = this.d;
        if (aVar != null) {
            aVar.a(a2Var);
        }
    }

    /* access modifiers changed from: protected */
    public void a(w1 w1Var) {
        this.e = null;
        q1.a aVar = this.d;
        if (aVar != null) {
            aVar.a(w1Var);
        }
    }
}
