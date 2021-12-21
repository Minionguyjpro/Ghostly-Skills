package com.startapp.common;

import android.os.Build;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: StartAppSDK */
public final class g {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f361a = g.class.getSimpleName();
    private static final int b = (Build.VERSION.SDK_INT < 22 ? 10 : 20);
    private static final int c = (Build.VERSION.SDK_INT < 22 ? 4 : 8);
    private static final ThreadFactory d = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f362a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "highPriorityThreadFactory #" + this.f362a.getAndIncrement());
        }
    };
    private static final ThreadFactory e = new ThreadFactory() {

        /* renamed from: a  reason: collision with root package name */
        private final AtomicInteger f363a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "defaultPriorityThreadFactory #" + this.f363a.getAndIncrement());
        }
    };
    private static final RejectedExecutionHandler f = new RejectedExecutionHandler() {
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            String a2 = g.f361a;
            com.startapp.common.a.g.a(a2, 6, "ThreadPoolExecutor rejected execution! " + threadPoolExecutor);
        }
    };
    private static final Executor g;
    private static final Executor h;
    private static final ScheduledExecutorService i = new ScheduledThreadPoolExecutor(1);

    /* compiled from: StartAppSDK */
    public enum a {
        DEFAULT,
        HIGH
    }

    static {
        int i2 = b;
        g = new ThreadPoolExecutor(i2, i2, 20, TimeUnit.SECONDS, new LinkedBlockingQueue(), d, f);
        int i3 = c;
        h = new ThreadPoolExecutor(i3, i3, 20, TimeUnit.SECONDS, new LinkedBlockingQueue(), e, f);
    }

    public static ScheduledFuture<?> a(Runnable runnable, long j) {
        return i.schedule(runnable, j, TimeUnit.MILLISECONDS);
    }

    public static void a(a aVar, Runnable runnable) {
        Executor executor;
        try {
            if (aVar.equals(a.HIGH)) {
                executor = g;
            } else {
                executor = h;
            }
            executor.execute(runnable);
        } catch (Exception unused) {
            String str = f361a;
            com.startapp.common.a.g.a(str, 6, "executeWithPriority failed to execute! " + null);
        }
    }
}
