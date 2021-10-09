package com.b.a.a.a.h.a;

import com.b.a.a.a.h.a.b;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class c implements b.a {

    /* renamed from: a  reason: collision with root package name */
    private final BlockingQueue<Runnable> f1010a = new LinkedBlockingQueue();
    private final ThreadPoolExecutor b = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.f1010a);
    private final ArrayDeque<b> c = new ArrayDeque<>();
    private b d = null;

    private void a() {
        b poll = this.c.poll();
        this.d = poll;
        if (poll != null) {
            poll.a(this.b);
        }
    }

    public void a(b bVar) {
        this.d = null;
        a();
    }

    public void b(b bVar) {
        bVar.a((b.a) this);
        this.c.add(bVar);
        if (this.d == null) {
            a();
        }
    }
}
