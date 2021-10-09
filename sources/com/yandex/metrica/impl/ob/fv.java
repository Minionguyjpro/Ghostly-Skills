package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ob.fu;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class fv<T> implements fu.a, fu.b<T>, Future<T> {

    /* renamed from: a  reason: collision with root package name */
    private boolean f917a = false;
    private T b;
    private fr c;

    public boolean isCancelled() {
        return false;
    }

    public static <E> fv<E> a() {
        return new fv<>();
    }

    private fv() {
    }

    public synchronized boolean cancel(boolean z) {
        return false;
    }

    public T get() throws InterruptedException, ExecutionException {
        try {
            return a((Long) null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return a(Long.valueOf(TimeUnit.MILLISECONDS.convert(j, timeUnit)));
    }

    private synchronized T a(Long l) throws InterruptedException, ExecutionException, TimeoutException {
        if (this.c != null) {
            throw new ExecutionException(this.c);
        } else if (this.f917a) {
            return this.b;
        } else {
            if (l == null) {
                wait(0);
            } else if (l.longValue() > 0) {
                wait(l.longValue());
            }
            if (this.c != null) {
                throw new ExecutionException(this.c);
            } else if (this.f917a) {
                return this.b;
            } else {
                throw new TimeoutException();
            }
        }
    }

    public synchronized boolean isDone() {
        return this.f917a || this.c != null || isCancelled();
    }

    public synchronized void a(T t) {
        this.f917a = true;
        this.b = t;
        notifyAll();
    }

    public synchronized void a(fr frVar) {
        this.c = frVar;
        notifyAll();
    }
}
