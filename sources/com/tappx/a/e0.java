package com.tappx.a;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Map;

class e0 {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final Map<d0<?>, Runnable> f424a;
    private final Handler b;
    /* access modifiers changed from: private */
    public b0 c;

    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ d0 f425a;

        a(d0 d0Var) {
            this.f425a = d0Var;
        }

        public void run() {
            if (((Runnable) e0.this.f424a.remove(this.f425a)) != null) {
                e0.this.c.a(this.f425a);
            }
        }
    }

    public e0() {
        this(new Handler(Looper.getMainLooper()));
    }

    e0(Handler handler) {
        this.f424a = new HashMap();
        this.b = handler;
    }

    public void a(b0 b0Var) {
        this.c = b0Var;
    }

    public void a(d0<?> d0Var, long j) {
        a aVar = new a(d0Var);
        this.f424a.put(d0Var, aVar);
        this.b.postDelayed(aVar, j);
    }

    public boolean a(d0<?> d0Var) {
        Runnable remove = this.f424a.remove(d0Var);
        if (remove == null) {
            return false;
        }
        this.b.removeCallbacks(remove);
        return true;
    }
}
