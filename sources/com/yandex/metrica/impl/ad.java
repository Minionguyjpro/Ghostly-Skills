package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.yandex.metrica.IMetricaService;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ad {

    /* renamed from: a  reason: collision with root package name */
    public static final long f703a = TimeUnit.SECONDS.toMillis(10);
    private final Context b;
    private final Handler c;
    private final List<a> d = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public volatile IMetricaService e = null;
    private final Runnable f = new Runnable() {
        public void run() {
            ad.this.f();
        }
    };
    private final ServiceConnection g = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IMetricaService unused = ad.this.e = IMetricaService.Stub.asInterface(iBinder);
            ad.b(ad.this);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            IMetricaService unused = ad.this.e = null;
            ad.this.g();
        }
    };

    interface a {
        void a();
    }

    public ad(Context context, Handler handler) {
        this.b = context.getApplicationContext();
        this.c = handler;
    }

    public synchronized void a() {
        if (this.e == null) {
            try {
                this.b.bindService(be.c(this.b), this.g, 1);
            } catch (Exception unused) {
            }
        }
    }

    public void b() {
        this.c.removeCallbacks(this.f);
        this.c.postDelayed(this.f, f703a);
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.c.removeCallbacks(this.f);
    }

    public boolean d() {
        return this.e != null;
    }

    public IMetricaService e() {
        return this.e;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:1|2|(2:6|7)|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void f() {
        /*
            r3 = this;
            monitor-enter(r3)
            android.content.Context r0 = r3.b     // Catch:{ all -> 0x001c }
            r1 = 0
            if (r0 == 0) goto L_0x0015
            boolean r0 = r3.d()     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0015
            android.content.Context r0 = r3.b     // Catch:{ Exception -> 0x0015 }
            android.content.ServiceConnection r2 = r3.g     // Catch:{ Exception -> 0x0015 }
            r0.unbindService(r2)     // Catch:{ Exception -> 0x0015 }
            r3.e = r1     // Catch:{ Exception -> 0x0015 }
        L_0x0015:
            r3.e = r1     // Catch:{ all -> 0x001c }
            r3.g()     // Catch:{ all -> 0x001c }
            monitor-exit(r3)
            return
        L_0x001c:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ad.f():void");
    }

    /* access modifiers changed from: private */
    public void g() {
        Iterator<a> it = this.d.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    public void a(a aVar) {
        this.d.add(aVar);
    }

    static /* synthetic */ void b(ad adVar) {
        for (a a2 : adVar.d) {
            a2.a();
        }
    }
}
