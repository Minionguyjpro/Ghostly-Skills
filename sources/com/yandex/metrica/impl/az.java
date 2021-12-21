package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.impl.ad;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.i;
import com.yandex.metrica.impl.ob.j;
import com.yandex.metrica.impl.ob.k;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class az implements ad.a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final s f738a;
    /* access modifiers changed from: private */
    public final ad b;
    /* access modifiers changed from: private */
    public final Object c = new Object();
    /* access modifiers changed from: private */
    public final ExecutorService d = Executors.newSingleThreadExecutor();

    public interface c {
        h a(h hVar);
    }

    public az(s sVar) {
        this.f738a = sVar;
        ad a2 = sVar.a();
        this.b = a2;
        a2.a((ad.a) this);
    }

    public Future<Void> a(d dVar) {
        return this.d.submit(dVar.c() ? new a(this, dVar, (byte) 0) : new b(this, dVar, (byte) 0));
    }

    public void a() {
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    private class b implements Callable<Void> {
        final d b;
        boolean c;

        /* synthetic */ b(az azVar, d dVar, byte b2) {
            this(dVar);
        }

        private b(d dVar) {
            this.b = dVar;
            g.a().a(this, ao.class, k.a(new j<ao>() {
                public /* bridge */ /* synthetic */ void a(i iVar) {
                    a();
                }

                public void a() {
                    b.this.c = true;
                }
            }).a());
        }

        /* renamed from: a */
        public Void call() {
            int i = 0;
            do {
                try {
                    IMetricaService e = az.this.b.e();
                    if (e != null && a(e, this.b)) {
                        break;
                    }
                    i++;
                    if (!b()) {
                        break;
                    } else if (this.c) {
                        break;
                    }
                } catch (Throwable unused) {
                    g.a().a((Object) this);
                    return null;
                }
            } while (i >= 3);
            g.a().a((Object) this);
            return null;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Can't wrap try/catch for region: R(4:5|6|7|8) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0029 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean b() {
            /*
                r5 = this;
                com.yandex.metrica.impl.az r0 = com.yandex.metrica.impl.az.this
                com.yandex.metrica.impl.ad r0 = r0.b
                r0.a()
                com.yandex.metrica.impl.az r0 = com.yandex.metrica.impl.az.this
                java.lang.Object r0 = r0.c
                monitor-enter(r0)
                com.yandex.metrica.impl.az r1 = com.yandex.metrica.impl.az.this     // Catch:{ all -> 0x003e }
                com.yandex.metrica.impl.ad r1 = r1.b     // Catch:{ all -> 0x003e }
                boolean r1 = r1.d()     // Catch:{ all -> 0x003e }
                if (r1 != 0) goto L_0x003b
                com.yandex.metrica.impl.az r1 = com.yandex.metrica.impl.az.this     // Catch:{ InterruptedException -> 0x0029 }
                java.lang.Object r1 = r1.c     // Catch:{ InterruptedException -> 0x0029 }
                r2 = 500(0x1f4, double:2.47E-321)
                r4 = 0
                r1.wait(r2, r4)     // Catch:{ InterruptedException -> 0x0029 }
                goto L_0x003b
            L_0x0029:
                com.yandex.metrica.impl.az r1 = com.yandex.metrica.impl.az.this     // Catch:{ all -> 0x003e }
                java.lang.Object r1 = r1.c     // Catch:{ all -> 0x003e }
                r1.notifyAll()     // Catch:{ all -> 0x003e }
                com.yandex.metrica.impl.az r1 = com.yandex.metrica.impl.az.this     // Catch:{ all -> 0x003e }
                java.util.concurrent.ExecutorService r1 = r1.d     // Catch:{ all -> 0x003e }
                r1.shutdownNow()     // Catch:{ all -> 0x003e }
            L_0x003b:
                monitor-exit(r0)     // Catch:{ all -> 0x003e }
                r0 = 1
                return r0
            L_0x003e:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x003e }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.az.b.b():boolean");
        }

        private boolean a(IMetricaService iMetricaService, d dVar) {
            try {
                az.this.f738a.a(iMetricaService, dVar.b(), dVar.b);
                return true;
            } catch (RemoteException unused) {
                return false;
            }
        }
    }

    private class a extends b {
        /* synthetic */ a(az azVar, d dVar, byte b) {
            this(dVar);
        }

        private a(d dVar) {
            super(az.this, dVar, (byte) 0);
        }

        /* renamed from: a */
        public Void call() {
            az.this.b.b();
            return super.call();
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            d dVar = this.b;
            Context b = az.this.f738a.b();
            Intent c = be.c(b);
            c.putExtras(dVar.f741a.a(dVar.b.c()));
            b.startService(c);
            return false;
        }
    }

    public static class d {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public h f741a;
        /* access modifiers changed from: private */
        public aw b;
        private boolean c = false;
        private c d;

        d(h hVar, aw awVar) {
            this.f741a = hVar;
            this.b = awVar;
        }

        /* access modifiers changed from: package-private */
        public d a(c cVar) {
            this.d = cVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        public d a(boolean z) {
            this.c = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public aw a() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public h b() {
            c cVar = this.d;
            return cVar != null ? cVar.a(this.f741a) : this.f741a;
        }

        /* access modifiers changed from: package-private */
        public boolean c() {
            return this.c;
        }

        public String toString() {
            return "ReportToSend{mReport=" + this.f741a + ", mEnvironment=" + this.b + ", mCrash=" + this.c + ", mAction=" + this.d + '}';
        }
    }
}
