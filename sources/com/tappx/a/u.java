package com.tappx.a;

import android.net.Uri;
import com.tappx.a.a0;
import com.tappx.a.d0;
import com.tappx.a.f0;
import com.tappx.a.j1;
import com.tappx.a.l0;
import com.tappx.a.m1;
import java.util.Map;

public class u extends d0<Void> {
    private static final String l = f.b("Y3lXBmQ23xTYiukQ1UnbWw");
    private static final String m = f.b("KG6txY+dAsHV+aE9vCpHOQ");
    private static final String n = f.b("FzLBfq4NHhh6H3aZu09wNg");
    private static final String o = f.b("5RPecgzrVUOe/I8D8SnSVA");
    private static final String p = f.b("p2JtzU2YCqXoi6X+GUHC9A");
    private static final String q = f.b("ChYe7NtYsJ5it5MJ0kItoQ");
    private final m1.a f;
    private final j1.a g;
    private final l0.a h;
    private final long i;
    private final p2 j;
    private final String k;

    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f589a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.tappx.a.p2[] r0 = com.tappx.a.p2.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f589a = r0
                com.tappx.a.p2 r1 = com.tappx.a.p2.GRANTED_DEVELOPER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f589a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.p2 r1 = com.tappx.a.p2.DENIED_DEVELOPER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.u.a.<clinit>():void");
        }
    }

    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private final m1.a f590a;
        private final t b;
        private final j1.a c;
        private final l0.a d;

        public b(m1.a aVar, t tVar, j1.a aVar2, l0.a aVar3) {
            this.f590a = aVar;
            this.b = tVar;
            this.c = aVar2;
            this.d = aVar3;
        }

        /* access modifiers changed from: package-private */
        public u a(f0.b<Void> bVar, f0.a aVar, long j, p2 p2Var) {
            return new u(this.b.a(), bVar, aVar, this.f590a, this.c, this.d, j, p2Var);
        }
    }

    u(String str, f0.b<Void> bVar, f0.a aVar, m1.a aVar2, j1.a aVar3, l0.a aVar4, long j2, p2 p2Var) {
        super(bVar, aVar);
        this.k = str;
        this.f = aVar2;
        this.g = aVar3;
        this.h = aVar4;
        a(false);
        a(new g0(10000, 1, 1.0f));
        this.i = j2;
        this.j = p2Var;
    }

    private String a(p2 p2Var) {
        int i2 = a.f589a[p2Var.ordinal()];
        return (i2 == 1 || i2 == 2) ? "d" : "u";
    }

    public byte[] a() {
        return null;
    }

    public Map<String, String> c() {
        return h();
    }

    public d0.a d() {
        return d0.a.GET;
    }

    public String g() {
        Uri.Builder buildUpon = Uri.parse(this.k).buildUpon();
        m1 b2 = this.f.b();
        l0 a2 = this.h.a();
        j1 a3 = this.g.a();
        buildUpon.appendQueryParameter(l, b2.e);
        buildUpon.appendQueryParameter(m, b2.d);
        buildUpon.appendQueryParameter(n, this.j.a() ? "0" : "1");
        buildUpon.appendQueryParameter("o", a(this.j));
        buildUpon.appendQueryParameter(o, String.valueOf(this.i));
        buildUpon.appendQueryParameter(p, a3.f478a);
        buildUpon.appendQueryParameter(q, a2.f500a);
        return buildUpon.build().toString();
    }

    /* access modifiers changed from: protected */
    public f0<Void> a(c0 c0Var) {
        if ("1".equals(c0Var.a())) {
            return f0.a(null);
        }
        return f0.a(new a0(a0.a.PARSE_ERROR));
    }
}
