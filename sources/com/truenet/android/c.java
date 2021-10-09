package com.truenet.android;

import a.a.b.b.h;
import a.a.b.b.i;
import a.a.j;
import android.content.Context;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* compiled from: StartAppSDK */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public static final a f676a = new a((e) null);
    private final ExecutorService b;
    private a.a.b.a.a<j> c = C0022c.f678a;
    private int d;
    private final Context e;
    private final List<String> f;
    private final long g;
    private final int h;

    /* renamed from: com.truenet.android.c$c  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    static final class C0022c extends i implements a.a.b.a.a<j> {

        /* renamed from: a  reason: collision with root package name */
        public static final C0022c f678a = new C0022c();

        C0022c() {
            super(0);
        }

        public final void b() {
        }

        public /* synthetic */ Object a() {
            b();
            return j.f974a;
        }
    }

    public c(Context context, List<String> list, ThreadFactory threadFactory, long j, int i, int i2) {
        h.b(context, "context");
        h.b(list, "links");
        h.b(threadFactory, "threadFactory");
        this.e = context;
        this.f = list;
        this.g = j;
        this.h = i;
        this.b = Executors.newFixedThreadPool(i2, threadFactory);
    }

    /* compiled from: StartAppSDK */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(e eVar) {
            this();
        }
    }

    public final void a(a.a.b.a.a<j> aVar) {
        h.b(aVar, "<set-?>");
        this.c = aVar;
    }

    private final int a() {
        int i;
        synchronized (this) {
            i = this.d + 1;
            this.d = i;
        }
        return i;
    }

    /* access modifiers changed from: private */
    public final void b() {
        synchronized (this) {
            int i = this.d - 1;
            this.d = i;
            if (i <= 0) {
                this.c.a();
            }
            j jVar = j.f974a;
        }
    }

    public final void a(a.a.b.a.b<? super b, ? super Integer, j> bVar) {
        h.b(bVar, "block");
        int i = 0;
        for (String bVar2 : this.f) {
            a();
            this.b.execute(new b(new b(this.e, bVar2, this.h, this.g), i, this, bVar));
            i++;
        }
    }

    /* compiled from: StartAppSDK */
    static final class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ b f677a;
        final /* synthetic */ int b;
        final /* synthetic */ c c;
        final /* synthetic */ a.a.b.a.b d;

        b(b bVar, int i, c cVar, a.a.b.a.b bVar2) {
            this.f677a = bVar;
            this.b = i;
            this.c = cVar;
            this.d = bVar2;
        }

        public final void run() {
            this.f677a.g();
            this.d.a(this.f677a, Integer.valueOf(this.b));
            this.c.b();
        }
    }
}
