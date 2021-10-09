package com.tappx.a;

import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public class o5 extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private final BlockingQueue<s5<?>> f540a;
    private final n5 b;
    private final h5 c;
    private final v5 d;
    private volatile boolean e = false;

    public o5(BlockingQueue<s5<?>> blockingQueue, n5 n5Var, h5 h5Var, v5 v5Var) {
        this.f540a = blockingQueue;
        this.b = n5Var;
        this.c = h5Var;
        this.d = v5Var;
    }

    private void b(s5<?> s5Var) {
        if (Build.VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(s5Var.q());
        }
    }

    public void a() {
        this.e = true;
        interrupt();
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                b();
            } catch (InterruptedException unused) {
                if (this.e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                a6.c("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }

    private void b() {
        a(this.f540a.take());
    }

    /* access modifiers changed from: package-private */
    public void a(s5<?> s5Var) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        s5Var.a(3);
        try {
            s5Var.a("network-queue-take");
            if (s5Var.t()) {
                s5Var.b("network-discard-cancelled");
                s5Var.v();
                s5Var.a(4);
                return;
            }
            b(s5Var);
            q5 a2 = this.b.a(s5Var);
            s5Var.a("network-http-complete");
            if (!a2.e || !s5Var.s()) {
                u5<?> a3 = s5Var.a(a2);
                s5Var.a("network-parse-complete");
                if (s5Var.w() && a3.b != null) {
                    this.c.a(s5Var.e(), a3.b);
                    s5Var.a("network-cache-written");
                }
                s5Var.u();
                this.d.a(s5Var, a3);
                s5Var.a(a3);
                s5Var.a(4);
                return;
            }
            s5Var.b("not-modified");
            s5Var.v();
            s5Var.a(4);
        } catch (z5 e2) {
            e2.a(SystemClock.elapsedRealtime() - elapsedRealtime);
            a(s5Var, e2);
            s5Var.v();
        } catch (Exception e3) {
            a6.a(e3, "Unhandled exception %s", e3.toString());
            z5 z5Var = new z5((Throwable) e3);
            z5Var.a(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.d.a(s5Var, z5Var);
            s5Var.v();
        } catch (Throwable th) {
            s5Var.a(4);
            throw th;
        }
    }

    private void a(s5<?> s5Var, z5 z5Var) {
        this.d.a(s5Var, s5Var.b(z5Var));
    }
}
