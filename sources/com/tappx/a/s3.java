package com.tappx.a;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.tappx.a.n;

public class s3 {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final Handler f576a = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public int b = -1;
    /* access modifiers changed from: private */
    public long c;
    /* access modifiers changed from: private */
    public b d;
    /* access modifiers changed from: private */
    public final Runnable e = new a();

    class a implements Runnable {
        a() {
        }

        public void run() {
            int floor = (int) Math.floor((double) (((float) (s3.this.c - s3.this.a())) / 1000.0f));
            if (floor > 0) {
                s3.this.f576a.removeCallbacks(s3.this.e);
                s3.this.f576a.postDelayed(s3.this.e, 200);
            }
            if (floor != s3.this.b) {
                int unused = s3.this.b = floor;
                if (s3.this.d != null) {
                    s3.this.d.a(floor);
                }
            }
        }
    }

    public interface b {
        void a(int i);
    }

    public boolean b() {
        return this.c == 0 || a() > this.c;
    }

    public void a(long j) {
        long j2 = n.a.k;
        if (j > j2) {
            j = j2;
        }
        this.c = a() + j;
        this.e.run();
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    /* access modifiers changed from: package-private */
    public long a() {
        return SystemClock.elapsedRealtime();
    }
}
