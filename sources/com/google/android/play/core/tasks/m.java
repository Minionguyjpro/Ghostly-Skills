package com.google.android.play.core.tasks;

import com.google.android.play.core.internal.ax;
import java.util.concurrent.Executor;

final class m<ResultT> extends Task<ResultT> {

    /* renamed from: a  reason: collision with root package name */
    private final Object f1143a = new Object();
    private final h<ResultT> b = new h<>();
    private boolean c;
    private ResultT d;
    private Exception e;

    m() {
    }

    private final void e() {
        ax.c(this.c, "Task is not yet complete");
    }

    private final void f() {
        ax.c(!this.c, "Task is already complete");
    }

    private final void g() {
        synchronized (this.f1143a) {
            if (this.c) {
                this.b.b(this);
            }
        }
    }

    public final void a(ResultT resultt) {
        synchronized (this.f1143a) {
            f();
            this.c = true;
            this.d = resultt;
        }
        this.b.b(this);
    }

    public final Task<ResultT> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        this.b.a(new d(executor, onFailureListener));
        g();
        return this;
    }

    public final Task<ResultT> addOnSuccessListener(Executor executor, OnSuccessListener<? super ResultT> onSuccessListener) {
        this.b.a(new f(executor, onSuccessListener));
        g();
        return this;
    }

    public final boolean b(ResultT resultt) {
        synchronized (this.f1143a) {
            if (this.c) {
                return false;
            }
            this.c = true;
            this.d = resultt;
            this.b.b(this);
            return true;
        }
    }

    public final void c(Exception exc) {
        synchronized (this.f1143a) {
            f();
            this.c = true;
            this.e = exc;
        }
        this.b.b(this);
    }

    public final boolean d(Exception exc) {
        synchronized (this.f1143a) {
            if (this.c) {
                return false;
            }
            this.c = true;
            this.e = exc;
            this.b.b(this);
            return true;
        }
    }

    public final Exception getException() {
        Exception exc;
        synchronized (this.f1143a) {
            exc = this.e;
        }
        return exc;
    }

    public final ResultT getResult() {
        ResultT resultt;
        synchronized (this.f1143a) {
            e();
            Exception exc = this.e;
            if (exc == null) {
                resultt = this.d;
            } else {
                throw new RuntimeExecutionException(exc);
            }
        }
        return resultt;
    }

    public final boolean isComplete() {
        boolean z;
        synchronized (this.f1143a) {
            z = this.c;
        }
        return z;
    }

    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.f1143a) {
            z = false;
            if (this.c && this.e == null) {
                z = true;
            }
        }
        return z;
    }
}
