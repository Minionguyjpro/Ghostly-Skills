package com.tappx.a;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class t5 {

    /* renamed from: a  reason: collision with root package name */
    private final AtomicInteger f587a;
    private final Set<s5<?>> b;
    private final PriorityBlockingQueue<s5<?>> c;
    private final PriorityBlockingQueue<s5<?>> d;
    private final h5 e;
    private final n5 f;
    private final v5 g;
    private final o5[] h;
    private i5 i;
    private final List<d> j;
    private final List<b> k;

    class a implements c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Object f588a;

        a(t5 t5Var, Object obj) {
            this.f588a = obj;
        }

        public boolean a(s5<?> s5Var) {
            return s5Var.o() == this.f588a;
        }
    }

    public interface b {
        void a(s5<?> s5Var, int i);
    }

    public interface c {
        boolean a(s5<?> s5Var);
    }

    @Deprecated
    public interface d<T> {
        void a(s5<T> s5Var);
    }

    public t5(h5 h5Var, n5 n5Var, int i2, v5 v5Var) {
        this.f587a = new AtomicInteger();
        this.b = new HashSet();
        this.c = new PriorityBlockingQueue<>();
        this.d = new PriorityBlockingQueue<>();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.e = h5Var;
        this.f = n5Var;
        this.h = new o5[i2];
        this.g = v5Var;
    }

    public int a() {
        return this.f587a.incrementAndGet();
    }

    public void b() {
        c();
        i5 i5Var = new i5(this.c, this.d, this.e, this.g);
        this.i = i5Var;
        i5Var.start();
        for (int i2 = 0; i2 < this.h.length; i2++) {
            o5 o5Var = new o5(this.d, this.f, this.e, this.g);
            this.h[i2] = o5Var;
            o5Var.start();
        }
    }

    public void c() {
        i5 i5Var = this.i;
        if (i5Var != null) {
            i5Var.a();
        }
        for (o5 o5Var : this.h) {
            if (o5Var != null) {
                o5Var.a();
            }
        }
    }

    public void a(c cVar) {
        synchronized (this.b) {
            for (s5 next : this.b) {
                if (cVar.a(next)) {
                    next.a();
                }
            }
        }
    }

    public void a(Object obj) {
        if (obj != null) {
            a((c) new a(this, obj));
            return;
        }
        throw new IllegalArgumentException("Cannot cancelAll with a null tag");
    }

    public <T> s5<T> a(s5<T> s5Var) {
        s5Var.a(this);
        synchronized (this.b) {
            this.b.add(s5Var);
        }
        s5Var.b(a());
        s5Var.a("add-to-queue");
        a(s5Var, 0);
        if (!s5Var.w()) {
            this.d.add(s5Var);
            return s5Var;
        }
        this.c.add(s5Var);
        return s5Var;
    }

    /* access modifiers changed from: package-private */
    public <T> void b(s5<T> s5Var) {
        synchronized (this.b) {
            this.b.remove(s5Var);
        }
        synchronized (this.j) {
            for (d a2 : this.j) {
                a2.a(s5Var);
            }
        }
        a(s5Var, 5);
    }

    /* access modifiers changed from: package-private */
    public void a(s5<?> s5Var, int i2) {
        synchronized (this.k) {
            for (b a2 : this.k) {
                a2.a(s5Var, i2);
            }
        }
    }

    public t5(h5 h5Var, n5 n5Var, int i2) {
        this(h5Var, n5Var, i2, new l5(new Handler(Looper.getMainLooper())));
    }

    public t5(h5 h5Var, n5 n5Var) {
        this(h5Var, n5Var, 4);
    }
}
