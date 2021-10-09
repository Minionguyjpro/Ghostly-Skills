package com.tappx.a;

import android.os.Handler;
import java.util.concurrent.Executor;

public class l5 implements v5 {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f511a;

    class a implements Executor {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Handler f512a;

        a(l5 l5Var, Handler handler) {
            this.f512a = handler;
        }

        public void execute(Runnable runnable) {
            this.f512a.post(runnable);
        }
    }

    private static class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final s5 f513a;
        private final u5 b;
        private final Runnable c;

        public b(s5 s5Var, u5 u5Var, Runnable runnable) {
            this.f513a = s5Var;
            this.b = u5Var;
            this.c = runnable;
        }

        public void run() {
            if (this.f513a.t()) {
                this.f513a.b("canceled-at-delivery");
                return;
            }
            if (this.b.a()) {
                this.f513a.a(this.b.f598a);
            } else {
                this.f513a.a(this.b.c);
            }
            if (this.b.d) {
                this.f513a.a("intermediate-response");
            } else {
                this.f513a.b("done");
            }
            Runnable runnable = this.c;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public l5(Handler handler) {
        this.f511a = new a(this, handler);
    }

    public void a(s5<?> s5Var, u5<?> u5Var) {
        a(s5Var, u5Var, (Runnable) null);
    }

    public void a(s5<?> s5Var, u5<?> u5Var, Runnable runnable) {
        s5Var.u();
        s5Var.a("post-response");
        this.f511a.execute(new b(s5Var, u5Var, runnable));
    }

    public void a(s5<?> s5Var, z5 z5Var) {
        s5Var.a("post-error");
        this.f511a.execute(new b(s5Var, u5.a(z5Var), (Runnable) null));
    }
}
