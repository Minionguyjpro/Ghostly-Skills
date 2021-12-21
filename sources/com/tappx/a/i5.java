package com.tappx.a;

import android.os.Process;
import com.tappx.a.h5;
import com.tappx.a.s5;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class i5 extends Thread {
    private static final boolean g = a6.b;

    /* renamed from: a  reason: collision with root package name */
    private final BlockingQueue<s5<?>> f472a;
    /* access modifiers changed from: private */
    public final BlockingQueue<s5<?>> b;
    private final h5 c;
    /* access modifiers changed from: private */
    public final v5 d;
    private volatile boolean e = false;
    private final b f;

    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ s5 f473a;

        a(s5 s5Var) {
            this.f473a = s5Var;
        }

        public void run() {
            try {
                i5.this.b.put(this.f473a);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class b implements s5.b {

        /* renamed from: a  reason: collision with root package name */
        private final Map<String, List<s5<?>>> f474a = new HashMap();
        private final i5 b;

        b(i5 i5Var) {
            this.b = i5Var;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean b(com.tappx.a.s5<?> r6) {
            /*
                r5 = this;
                monitor-enter(r5)
                java.lang.String r0 = r6.e()     // Catch:{ all -> 0x0052 }
                java.util.Map<java.lang.String, java.util.List<com.tappx.a.s5<?>>> r1 = r5.f474a     // Catch:{ all -> 0x0052 }
                boolean r1 = r1.containsKey(r0)     // Catch:{ all -> 0x0052 }
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L_0x003a
                java.util.Map<java.lang.String, java.util.List<com.tappx.a.s5<?>>> r1 = r5.f474a     // Catch:{ all -> 0x0052 }
                java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0052 }
                java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0052 }
                if (r1 != 0) goto L_0x001e
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0052 }
                r1.<init>()     // Catch:{ all -> 0x0052 }
            L_0x001e:
                java.lang.String r4 = "waiting-for-response"
                r6.a((java.lang.String) r4)     // Catch:{ all -> 0x0052 }
                r1.add(r6)     // Catch:{ all -> 0x0052 }
                java.util.Map<java.lang.String, java.util.List<com.tappx.a.s5<?>>> r6 = r5.f474a     // Catch:{ all -> 0x0052 }
                r6.put(r0, r1)     // Catch:{ all -> 0x0052 }
                boolean r6 = com.tappx.a.a6.b     // Catch:{ all -> 0x0052 }
                if (r6 == 0) goto L_0x0038
                java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
                r6[r3] = r0     // Catch:{ all -> 0x0052 }
                java.lang.String r0 = "Request for cacheKey=%s is in flight, putting on hold."
                com.tappx.a.a6.b(r0, r6)     // Catch:{ all -> 0x0052 }
            L_0x0038:
                monitor-exit(r5)
                return r2
            L_0x003a:
                java.util.Map<java.lang.String, java.util.List<com.tappx.a.s5<?>>> r1 = r5.f474a     // Catch:{ all -> 0x0052 }
                r4 = 0
                r1.put(r0, r4)     // Catch:{ all -> 0x0052 }
                r6.a((com.tappx.a.s5.b) r5)     // Catch:{ all -> 0x0052 }
                boolean r6 = com.tappx.a.a6.b     // Catch:{ all -> 0x0052 }
                if (r6 == 0) goto L_0x0050
                java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
                r6[r3] = r0     // Catch:{ all -> 0x0052 }
                java.lang.String r0 = "new request, sending to network %s"
                com.tappx.a.a6.b(r0, r6)     // Catch:{ all -> 0x0052 }
            L_0x0050:
                monitor-exit(r5)
                return r3
            L_0x0052:
                r6 = move-exception
                monitor-exit(r5)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.i5.b.b(com.tappx.a.s5):boolean");
        }

        public void a(s5<?> s5Var, u5<?> u5Var) {
            List<s5> remove;
            h5.a aVar = u5Var.b;
            if (aVar == null || aVar.a()) {
                a(s5Var);
                return;
            }
            String e = s5Var.e();
            synchronized (this) {
                remove = this.f474a.remove(e);
            }
            if (remove != null) {
                if (a6.b) {
                    a6.d("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(remove.size()), e);
                }
                for (s5 a2 : remove) {
                    this.b.d.a((s5<?>) a2, u5Var);
                }
            }
        }

        public synchronized void a(s5<?> s5Var) {
            String e = s5Var.e();
            List remove = this.f474a.remove(e);
            if (remove != null && !remove.isEmpty()) {
                if (a6.b) {
                    a6.d("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(remove.size()), e);
                }
                s5 s5Var2 = (s5) remove.remove(0);
                this.f474a.put(e, remove);
                s5Var2.a((s5.b) this);
                try {
                    this.b.b.put(s5Var2);
                } catch (InterruptedException e2) {
                    a6.c("Couldn't add request to queue. %s", e2.toString());
                    Thread.currentThread().interrupt();
                    this.b.a();
                }
            }
            return;
        }
    }

    public i5(BlockingQueue<s5<?>> blockingQueue, BlockingQueue<s5<?>> blockingQueue2, h5 h5Var, v5 v5Var) {
        this.f472a = blockingQueue;
        this.b = blockingQueue2;
        this.c = h5Var;
        this.d = v5Var;
        this.f = new b(this);
    }

    public void run() {
        if (g) {
            a6.d("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.c.a();
        while (true) {
            try {
                b();
            } catch (InterruptedException unused) {
                if (this.e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                a6.c("Ignoring spurious interrupt of CacheDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }

    private void b() {
        a(this.f472a.take());
    }

    public void a() {
        this.e = true;
        interrupt();
    }

    /* access modifiers changed from: package-private */
    public void a(s5<?> s5Var) {
        s5Var.a("cache-queue-take");
        s5Var.a(1);
        try {
            if (s5Var.t()) {
                s5Var.b("cache-discard-canceled");
                return;
            }
            h5.a a2 = this.c.a(s5Var.e());
            if (a2 == null) {
                s5Var.a("cache-miss");
                if (!this.f.b(s5Var)) {
                    this.b.put(s5Var);
                }
                s5Var.a(2);
            } else if (a2.a()) {
                s5Var.a("cache-hit-expired");
                s5Var.a(a2);
                if (!this.f.b(s5Var)) {
                    this.b.put(s5Var);
                }
                s5Var.a(2);
            } else {
                s5Var.a("cache-hit");
                u5<?> a3 = s5Var.a(new q5(a2.f460a, a2.g));
                s5Var.a("cache-hit-parsed");
                if (!a2.b()) {
                    this.d.a(s5Var, a3);
                } else {
                    s5Var.a("cache-hit-refresh-needed");
                    s5Var.a(a2);
                    a3.d = true;
                    if (!this.f.b(s5Var)) {
                        this.d.a(s5Var, a3, new a(s5Var));
                    } else {
                        this.d.a(s5Var, a3);
                    }
                }
                s5Var.a(2);
            }
        } finally {
            s5Var.a(2);
        }
    }
}
