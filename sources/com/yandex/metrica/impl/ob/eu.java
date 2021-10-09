package com.yandex.metrica.impl.ob;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class eu {

    /* renamed from: a  reason: collision with root package name */
    private static final String f889a = eu.class.getSimpleName();
    private ArrayList<Object> b = new ArrayList<>();
    private ek c;
    private final Lock d;
    private final Lock e;
    private final Condition f;
    private ff g;

    eu(fb fbVar) {
        this.c = new eo(fbVar);
        this.d = new ReentrantLock();
        this.e = new ReentrantLock();
        this.f = this.d.newCondition();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:16|17|28|27|14|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003a */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042 A[SYNTHETIC, Splitter:B:16:0x0042] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.security.cert.X509Certificate[] r4) {
        /*
            r3 = this;
            java.util.concurrent.locks.Lock r0 = r3.e
            r0.lock()
            com.yandex.metrica.impl.ob.ek r0 = r3.c     // Catch:{ all -> 0x005e }
            boolean r0 = r0.a(r4)     // Catch:{ all -> 0x005e }
            if (r0 == 0) goto L_0x0013
        L_0x000d:
            java.util.concurrent.locks.Lock r4 = r3.e
            r4.unlock()
            return
        L_0x0013:
            com.yandex.metrica.impl.ob.ff r0 = new com.yandex.metrica.impl.ob.ff     // Catch:{ all -> 0x005e }
            r0.<init>(r4)     // Catch:{ all -> 0x005e }
            r3.g = r0     // Catch:{ all -> 0x005e }
            r4 = 0
            java.util.ArrayList<java.lang.Object> r0 = r3.b     // Catch:{ all -> 0x005e }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x005e }
        L_0x0021:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x002c
            r0.next()     // Catch:{ all -> 0x005e }
            r4 = 1
            goto L_0x0021
        L_0x002c:
            if (r4 == 0) goto L_0x000d
            java.lang.String r4 = f889a     // Catch:{ all -> 0x005e }
            java.lang.String r0 = "waiting for trust issue resolve"
            android.util.Log.i(r4, r0)     // Catch:{ all -> 0x005e }
            java.util.concurrent.locks.Lock r4 = r3.d     // Catch:{ all -> 0x005e }
            r4.lock()     // Catch:{ all -> 0x005e }
        L_0x003a:
            com.yandex.metrica.impl.ob.ff r4 = r3.g     // Catch:{ all -> 0x0057 }
            boolean r4 = r4.b()     // Catch:{ all -> 0x0057 }
            if (r4 != 0) goto L_0x0051
            java.util.concurrent.locks.Condition r4 = r3.f     // Catch:{ InterruptedException -> 0x003a }
            r0 = 30000(0x7530, double:1.4822E-319)
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x003a }
            r4.await(r0, r2)     // Catch:{ InterruptedException -> 0x003a }
            com.yandex.metrica.impl.ob.ff r4 = r3.g     // Catch:{ InterruptedException -> 0x003a }
            r4.c()     // Catch:{ InterruptedException -> 0x003a }
            goto L_0x003a
        L_0x0051:
            java.util.concurrent.locks.Lock r4 = r3.d     // Catch:{ all -> 0x005e }
            r4.unlock()     // Catch:{ all -> 0x005e }
            goto L_0x000d
        L_0x0057:
            r4 = move-exception
            java.util.concurrent.locks.Lock r0 = r3.d     // Catch:{ all -> 0x005e }
            r0.unlock()     // Catch:{ all -> 0x005e }
            throw r4     // Catch:{ all -> 0x005e }
        L_0x005e:
            r4 = move-exception
            java.util.concurrent.locks.Lock r0 = r3.e
            r0.unlock()
            goto L_0x0066
        L_0x0065:
            throw r4
        L_0x0066:
            goto L_0x0065
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.eu.a(java.security.cert.X509Certificate[]):void");
    }
}
