package com.tappx.a;

import com.tappx.a.h5;

public class u5<T> {

    /* renamed from: a  reason: collision with root package name */
    public final T f598a;
    public final h5.a b;
    public final z5 c;
    public boolean d;

    public interface a {
        void a(z5 z5Var);
    }

    private u5(T t, h5.a aVar) {
        this.d = false;
        this.f598a = t;
        this.b = aVar;
        this.c = null;
    }

    public static <T> u5<T> a(T t, h5.a aVar) {
        return new u5<>(t, aVar);
    }

    public static <T> u5<T> a(z5 z5Var) {
        return new u5<>(z5Var);
    }

    public boolean a() {
        return this.c == null;
    }

    private u5(z5 z5Var) {
        this.d = false;
        this.f598a = null;
        this.b = null;
        this.c = z5Var;
    }
}
