package com.tappx.a;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class s4 {

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final Object f578a;
        private final String b;
        private Class<?> c;
        private List<Class<?>> d = new ArrayList();
        private List<Object> e = new ArrayList();
        private boolean f;
        private boolean g;

        public a(Object obj, String str) {
            this.f578a = obj;
            this.b = str;
            this.c = obj != null ? obj.getClass() : null;
        }

        public Object a() {
            List<Class<?>> list = this.d;
            Method a2 = s4.a(this.c, this.b, (Class[]) list.toArray(new Class[this.d.size()]));
            if (this.f) {
                a2.setAccessible(true);
            }
            Object[] array = this.e.toArray();
            if (this.g) {
                return a2.invoke((Object) null, array);
            }
            return a2.invoke(this.f578a, array);
        }

        public a b() {
            this.f = true;
            return this;
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        Class<? super Object> cls2;
        while (cls2 != null) {
            try {
                return cls2.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
                Class<? super Object> superclass = cls2.getSuperclass();
                cls2 = cls;
                cls2 = superclass;
            }
        }
        throw new NoSuchMethodException();
    }
}
