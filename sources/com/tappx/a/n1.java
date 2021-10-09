package com.tappx.a;

import android.os.Handler;
import android.os.Looper;

public class n1 {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f525a;
    /* access modifiers changed from: private */
    public b b;
    private final Runnable c;

    class a implements Runnable {
        a() {
        }

        public void run() {
            if (n1.this.b != null) {
                n1.this.b.a();
            }
        }
    }

    public interface b {
        void a();
    }

    n1(Handler handler) {
        this.c = new a();
        this.f525a = handler;
    }

    private void b() {
        this.f525a.removeCallbacks(this.c);
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public void a(long j) {
        b();
        this.f525a.postDelayed(this.c, j);
    }

    public void a() {
        b();
    }

    public n1() {
        this(new Handler(Looper.getMainLooper()));
    }
}
