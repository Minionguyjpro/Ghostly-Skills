package com.yandex.metrica.impl.ob;

import java.util.ArrayList;
import java.util.List;

class cf {

    /* renamed from: a  reason: collision with root package name */
    private final String f820a;
    private final cj b;
    private int c;
    private final List<ce> d = new ArrayList();
    private final List<ce> e = new ArrayList();
    private final List<ce> f = new ArrayList();

    cf(String str, cj cjVar) {
        this.f820a = str;
        this.b = cjVar;
    }

    public void a(ce ceVar) {
        this.c += ceVar.c().b;
        this.d.add(ceVar);
        int i = AnonymousClass1.f821a[ceVar.a(this.b).ordinal()];
        if (i == 1) {
            this.e.add(ceVar);
        } else if (i == 2) {
            this.f.add(ceVar);
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.cf$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f821a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.yandex.metrica.impl.ob.ce$a[] r0 = com.yandex.metrica.impl.ob.ce.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f821a = r0
                com.yandex.metrica.impl.ob.ce$a r1 = com.yandex.metrica.impl.ob.ce.a.THIS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f821a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.yandex.metrica.impl.ob.ce$a r1 = com.yandex.metrica.impl.ob.ce.a.OTHER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.cf.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean a() {
        return !this.f.isEmpty();
    }

    public int b() {
        return this.d.size();
    }

    public String c() {
        return this.f820a;
    }

    public List<ce> d() {
        return this.d;
    }

    public Long e() {
        long j = Long.MAX_VALUE;
        for (ce c2 : this.d) {
            Long valueOf = Long.valueOf(c2.c().c);
            if (valueOf.compareTo(j) < 0) {
                j = valueOf;
            }
        }
        return j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f820a.equals(((cf) obj).f820a);
    }

    public int hashCode() {
        return this.f820a.hashCode();
    }
}
