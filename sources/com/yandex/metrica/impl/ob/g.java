package com.yandex.metrica.impl.ob;

import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class g {

    /* renamed from: a  reason: collision with root package name */
    private final Thread f919a;
    /* access modifiers changed from: private */
    public volatile boolean b = true;
    /* access modifiers changed from: private */
    public final BlockingQueue<a> c = new LinkedBlockingQueue();
    private ConcurrentHashMap<Class, CopyOnWriteArrayList<k<? extends i>>> d = new ConcurrentHashMap<>();
    private WeakHashMap<Object, CopyOnWriteArrayList<c>> e = new WeakHashMap<>();
    private ConcurrentHashMap<Class, i> f = new ConcurrentHashMap<>();

    private static final class b {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final g f922a = new g();
    }

    private static class c {

        /* renamed from: a  reason: collision with root package name */
        final CopyOnWriteArrayList<k<? extends i>> f923a;
        final k<? extends i> b;

        /* synthetic */ c(CopyOnWriteArrayList copyOnWriteArrayList, k kVar, byte b2) {
            this(copyOnWriteArrayList, kVar);
        }

        private c(CopyOnWriteArrayList<k<? extends i>> copyOnWriteArrayList, k<? extends i> kVar) {
            this.f923a = copyOnWriteArrayList;
            this.b = kVar;
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.f923a.remove(this.b);
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            super.finalize();
            a();
        }
    }

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private final i f921a;
        private final k<? extends i> b;

        /* synthetic */ a(i iVar, k kVar, byte b2) {
            this(iVar, kVar);
        }

        private a(i iVar, k<? extends i> kVar) {
            this.f921a = iVar;
            this.b = kVar;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            try {
                if (!this.b.b(this.f921a)) {
                    this.b.a(this.f921a);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static final g a() {
        return b.f922a;
    }

    g() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (g.this.b) {
                    try {
                        ((a) g.this.c.take()).a();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }, "Bus Dispatcher");
        this.f919a = thread;
        thread.start();
    }

    public synchronized void a(i iVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.d.get(iVar.getClass());
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                a(iVar, (k) it.next());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(i iVar, k<? extends i> kVar) {
        this.c.add(new a(iVar, kVar, (byte) 0));
    }

    public synchronized void b(i iVar) {
        a(iVar);
        this.f.put(iVar.getClass(), iVar);
    }

    public synchronized void a(Class<? extends i> cls) {
        this.f.remove(cls);
    }

    public synchronized void a(Object obj, Class cls, k<? extends i> kVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.d.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.d.put(cls, copyOnWriteArrayList);
        }
        copyOnWriteArrayList.add(kVar);
        CopyOnWriteArrayList copyOnWriteArrayList2 = this.e.get(obj);
        if (copyOnWriteArrayList2 == null) {
            copyOnWriteArrayList2 = new CopyOnWriteArrayList();
            this.e.put(obj, copyOnWriteArrayList2);
        }
        copyOnWriteArrayList2.add(new c(copyOnWriteArrayList, kVar, (byte) 0));
        i iVar = this.f.get(cls);
        if (iVar != null) {
            a(iVar, kVar);
        }
    }

    public synchronized void a(Object obj) {
        List<c> remove = this.e.remove(obj);
        if (remove != null) {
            for (c a2 : remove) {
                a2.a();
            }
        }
    }
}
