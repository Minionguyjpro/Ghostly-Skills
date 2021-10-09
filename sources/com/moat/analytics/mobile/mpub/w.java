package com.moat.analytics.mobile.mpub;

import android.os.Handler;
import android.os.Looper;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class w {
    private static w h;
    /* access modifiers changed from: private */
    public static final Queue<c> i = new ConcurrentLinkedQueue();

    /* renamed from: a  reason: collision with root package name */
    volatile d f1186a = d.OFF;
    volatile boolean b = false;
    volatile boolean c = false;
    volatile int d = 200;
    volatile int e = 10;
    private long f = 1800000;
    /* access modifiers changed from: private */
    public long g = 60000;
    /* access modifiers changed from: private */
    public Handler j;
    /* access modifiers changed from: private */
    public final AtomicBoolean k = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public volatile long l = 0;
    /* access modifiers changed from: private */
    public final AtomicInteger m = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public final AtomicBoolean n = new AtomicBoolean(false);

    private class a implements Runnable {
        private final Handler b;
        private final String c;
        /* access modifiers changed from: private */
        public final e d;

        private a(String str, Handler handler, e eVar) {
            this.d = eVar;
            this.b = handler;
            this.c = "https://sejs.moatads.com/" + str + "/android/" + "422d7e6" + "/status.json";
        }

        private void a() {
            String b2 = b();
            final m mVar = new m(b2);
            w.this.b = mVar.a();
            w.this.c = mVar.b();
            w.this.d = mVar.c();
            w.this.e = mVar.d();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    try {
                        a.this.d.a(mVar);
                    } catch (Exception e) {
                        n.a(e);
                    }
                }
            });
            long unused = w.this.l = System.currentTimeMillis();
            w.this.n.compareAndSet(true, false);
            if (b2 != null) {
                w.this.m.set(0);
            } else if (w.this.m.incrementAndGet() < 10) {
                w wVar = w.this;
                wVar.a(wVar.g);
            }
        }

        private String b() {
            try {
                return q.a(this.c + "?ts=" + System.currentTimeMillis() + "&v=" + "2.6.6").b();
            } catch (Exception unused) {
                return null;
            }
        }

        public void run() {
            try {
                a();
            } catch (Exception e) {
                n.a(e);
            }
            this.b.removeCallbacks(this);
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
    }

    interface b {
        void b();

        void c();
    }

    private class c {

        /* renamed from: a  reason: collision with root package name */
        final Long f1192a;
        final b b;

        c(Long l, b bVar) {
            this.f1192a = l;
            this.b = bVar;
        }
    }

    enum d {
        OFF,
        ON
    }

    interface e {
        void a(m mVar);
    }

    private w() {
        try {
            this.j = new Handler(Looper.getMainLooper());
        } catch (Exception e2) {
            n.a(e2);
        }
    }

    static synchronized w a() {
        w wVar;
        synchronized (w.class) {
            if (h == null) {
                h = new w();
            }
            wVar = h;
        }
        return wVar;
    }

    /* access modifiers changed from: private */
    public void a(final long j2) {
        if (this.n.compareAndSet(false, true)) {
            p.a(3, "OnOff", (Object) this, "Performing status check.");
            new Thread() {
                public void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    handler.postDelayed(new a("MPUB", handler, new e() {
                        public void a(m mVar) {
                            synchronized (w.i) {
                                boolean z = ((k) MoatAnalytics.getInstance()).f1173a;
                                if (w.this.f1186a != mVar.e() || (w.this.f1186a == d.OFF && z)) {
                                    w.this.f1186a = mVar.e();
                                    if (w.this.f1186a == d.OFF && z) {
                                        w.this.f1186a = d.ON;
                                    }
                                    if (w.this.f1186a == d.ON && !i.a().b()) {
                                        w.this.f1186a = d.OFF;
                                    }
                                    if (w.this.f1186a == d.ON) {
                                        p.a(3, "OnOff", (Object) this, "Moat enabled - Version 2.6.6");
                                    }
                                    for (c cVar : w.i) {
                                        if (w.this.f1186a == d.ON) {
                                            cVar.b.b();
                                        } else {
                                            cVar.b.c();
                                        }
                                    }
                                }
                                while (!w.i.isEmpty()) {
                                    w.i.remove();
                                }
                            }
                        }
                    }), j2);
                    Looper.loop();
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        synchronized (i) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = i.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - ((c) it.next()).f1192a.longValue() >= 60000) {
                    it.remove();
                }
            }
            if (i.size() >= 15) {
                for (int i2 = 0; i2 < 5; i2++) {
                    i.remove();
                }
            }
        }
    }

    private void e() {
        if (this.k.compareAndSet(false, true)) {
            this.j.postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (w.i.size() > 0) {
                            w.this.d();
                            w.this.j.postDelayed(this, 60000);
                            return;
                        }
                        w.this.k.compareAndSet(true, false);
                        w.this.j.removeCallbacks(this);
                    } catch (Exception e) {
                        n.a(e);
                    }
                }
            }, 60000);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        if (this.f1186a == d.ON) {
            bVar.b();
            return;
        }
        d();
        i.add(new c(Long.valueOf(System.currentTimeMillis()), bVar));
        e();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (System.currentTimeMillis() - this.l > this.f) {
            a(0);
        }
    }
}
