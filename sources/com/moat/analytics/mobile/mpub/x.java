package com.moat.analytics.mobile.mpub;

import com.moat.analytics.mobile.mpub.w;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

class x<T> implements InvocationHandler {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final Object[] f1194a = new Object[0];
    private final a<T> b;
    private final Class<T> c;
    private final LinkedList<x<T>.b> d = new LinkedList<>();
    private boolean e;
    private T f;

    interface a<T> {
        com.moat.analytics.mobile.mpub.a.b.a<T> a();
    }

    private class b {
        /* access modifiers changed from: private */
        public final WeakReference[] b;
        private final LinkedList<Object> c;
        /* access modifiers changed from: private */
        public final Method d;

        private b(Method method, Object... objArr) {
            this.c = new LinkedList<>();
            objArr = objArr == null ? x.f1194a : objArr;
            WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
            int length = objArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                Object obj = objArr[i];
                if ((obj instanceof Map) || (obj instanceof Integer) || (obj instanceof Double)) {
                    this.c.add(obj);
                }
                weakReferenceArr[i2] = new WeakReference(obj);
                i++;
                i2++;
            }
            this.b = weakReferenceArr;
            this.d = method;
        }
    }

    x(a<T> aVar, Class<T> cls) {
        com.moat.analytics.mobile.mpub.a.a.a.a(aVar);
        com.moat.analytics.mobile.mpub.a.a.a.a(cls);
        this.b = aVar;
        this.c = cls;
        w.a().a((w.b) new w.b() {
            public void b() {
                x.this.c();
            }

            public void c() {
            }
        });
    }

    static <T> T a(a<T> aVar, Class<T> cls) {
        ClassLoader classLoader = cls.getClassLoader();
        x xVar = new x(aVar, cls);
        return Proxy.newProxyInstance(classLoader, new Class[]{cls}, xVar);
    }

    private Object a(Method method) {
        try {
            return Boolean.TYPE.equals(method.getReturnType()) ? true : null;
        } catch (Exception e2) {
            n.a(e2);
            return null;
        }
    }

    private Object a(Method method, Object[] objArr) {
        Class<?> declaringClass = method.getDeclaringClass();
        w a2 = w.a();
        if (Object.class.equals(declaringClass)) {
            String name = method.getName();
            if ("getClass".equals(name)) {
                return this.c;
            }
            boolean equals = "toString".equals(name);
            Object invoke = method.invoke(this, objArr);
            if (!equals) {
                return invoke;
            }
            String name2 = x.class.getName();
            String name3 = this.c.getName();
            return (invoke + "").replace(name2, name3);
        } else if (!this.e || this.f != null) {
            if (a2.f1186a == w.d.ON) {
                c();
                T t = this.f;
                if (t != null) {
                    return method.invoke(t, objArr);
                }
            }
            if (a2.f1186a == w.d.OFF && (!this.e || this.f != null)) {
                b(method, objArr);
            }
            return a(method);
        } else {
            this.d.clear();
            return a(method);
        }
    }

    private void b() {
        if (!this.e) {
            try {
                this.f = this.b.a().c(null);
            } catch (Exception e2) {
                p.a("OnOffTrackerProxy", (Object) this, "Could not create instance", (Throwable) e2);
                n.a(e2);
            }
            this.e = true;
        }
    }

    private void b(Method method, Object[] objArr) {
        if (this.d.size() >= 15) {
            this.d.remove(5);
        }
        this.d.add(new b(method, objArr));
    }

    /* access modifiers changed from: private */
    public void c() {
        b();
        if (this.f != null) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                try {
                    Object[] objArr = new Object[bVar.b.length];
                    WeakReference[] a2 = bVar.b;
                    int length = a2.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        objArr[i2] = a2[i].get();
                        i++;
                        i2++;
                    }
                    bVar.d.invoke(this.f, objArr);
                } catch (Exception e2) {
                    n.a(e2);
                }
            }
            this.d.clear();
        }
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return a(method, objArr);
        } catch (Exception e2) {
            n.a(e2);
            return a(method);
        }
    }
}
