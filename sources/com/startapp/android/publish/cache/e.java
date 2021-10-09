package com.startapp.android.publish.cache;

import android.os.Handler;
import android.os.Looper;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public abstract class e {

    /* renamed from: a  reason: collision with root package name */
    protected g f291a;
    private Handler b = null;
    private Long c = null;
    private boolean d = false;

    /* access modifiers changed from: protected */
    public abstract boolean c();

    /* access modifiers changed from: protected */
    public abstract long d();

    /* access modifiers changed from: protected */
    public String e() {
        return "CacheScheduledTask";
    }

    public e(g gVar) {
        this.f291a = gVar;
    }

    public void f() {
        if (!this.d) {
            if (this.c == null) {
                this.c = Long.valueOf(System.currentTimeMillis());
            }
            if (c()) {
                if (this.b == null) {
                    Looper myLooper = Looper.myLooper();
                    if (myLooper == null) {
                        myLooper = Looper.getMainLooper();
                    }
                    this.b = new Handler(myLooper);
                }
                long d2 = d();
                if (d2 >= 0) {
                    this.d = true;
                    String e = e();
                    g.a(e, 4, "Started for " + this.f291a.c() + " - scheduled to: " + d2);
                    this.b.postDelayed(new Runnable() {
                        public void run() {
                            e.this.b();
                        }
                    }, d2);
                    return;
                }
                g.a(e(), 3, "Can't start, scheduled time < 0");
                return;
            }
            g.a(e(), 3, "Not allowed");
        }
    }

    public void g() {
        j();
        k();
    }

    public void h() {
        j();
        this.d = false;
    }

    public void a() {
        String e = e();
        g.a(e, 4, "Resetting for " + this.f291a.c());
        g();
    }

    /* access modifiers changed from: protected */
    public void b() {
        String e = e();
        g.a(e, 3, "Time reached, reloading " + this.f291a.c());
        k();
        this.f291a.k();
    }

    /* access modifiers changed from: protected */
    public final Long i() {
        return this.c;
    }

    private void j() {
        Handler handler = this.b;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
    }

    private void k() {
        this.c = null;
        this.d = false;
    }
}
