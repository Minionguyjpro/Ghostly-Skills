package com.tappx.a;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class e5 {

    /* renamed from: a  reason: collision with root package name */
    private static final e5 f429a = new e5();
    private static final Map<Integer, a> b = Collections.synchronizedMap(new HashMap());

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final t3 f430a;
        private final WeakReference<c5> b;

        public a(t3 t3Var, c5 c5Var) {
            this.f430a = t3Var;
            this.b = new WeakReference<>(c5Var);
        }

        public t3 a() {
            return this.f430a;
        }

        public WeakReference<c5> b() {
            return this.b;
        }
    }

    public static e5 a() {
        return f429a;
    }

    private synchronized void b() {
        Iterator<Map.Entry<Integer, a>> it = b.entrySet().iterator();
        while (it.hasNext()) {
            if (((a) it.next().getValue()).b().get() == null) {
                it.remove();
            }
        }
    }

    public void a(int i, c5 c5Var, t3 t3Var) {
        b();
        if (b.size() <= 50) {
            b.put(Integer.valueOf(i), new a(t3Var, (c5) null));
        }
    }

    public a a(int i) {
        return b.remove(Integer.valueOf(i));
    }
}
