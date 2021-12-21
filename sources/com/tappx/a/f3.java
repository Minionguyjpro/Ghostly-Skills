package com.tappx.a;

import android.os.Handler;
import android.os.Looper;

public class f3 {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f440a;
    private final long b;
    private final long c;
    private final long d;
    private long e;
    /* access modifiers changed from: private */
    public b f;
    /* access modifiers changed from: private */
    public long g;
    private long h;
    private boolean i;
    private final Runnable j;

    class a implements Runnable {
        a() {
        }

        public void run() {
            long unused = f3.this.g = 0;
            if (f3.this.f != null) {
                f3.this.f.a();
            }
        }
    }

    public interface b {
        void a();
    }

    f3(Handler handler, long j2, long j3, long j4) {
        this.i = true;
        this.j = new a();
        this.f440a = handler;
        this.b = j2;
        this.c = j3;
        this.d = j4;
        a(j4);
    }

    private long f() {
        return System.currentTimeMillis();
    }

    private void g() {
        this.f440a.removeCallbacks(this.j);
    }

    public void b() {
        a(this.d);
    }

    public void c() {
        if (this.i) {
            long j2 = this.e;
            long j3 = this.g;
            if (j3 > 0 && j3 < j2) {
                j2 -= j3;
            }
            g();
            this.f440a.postDelayed(this.j, j2);
            this.h = f();
        }
    }

    public void d() {
        if (this.i) {
            this.g = 0;
            c();
        }
    }

    public void e() {
        a();
        this.g = 0;
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public void a() {
        g();
        this.g += f() - this.h;
    }

    public void a(long j2) {
        long j3 = this.b;
        if (j2 < j3) {
            j2 = j3;
        }
        long j4 = this.c;
        if (j2 > j4) {
            j2 = j4;
        }
        this.e = j2;
    }

    public void a(boolean z) {
        this.i = z;
        if (!z) {
            e();
        }
    }

    public f3(long j2, long j3, long j4) {
        this(new Handler(Looper.getMainLooper()), j2, j3, j4);
    }
}
