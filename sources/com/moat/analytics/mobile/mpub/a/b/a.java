package com.moat.analytics.mobile.mpub.a.b;

import java.util.NoSuchElementException;

public final class a<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final a<?> f1152a = new a<>();
    private final T b;

    private a() {
        this.b = null;
    }

    private a(T t) {
        if (t != null) {
            this.b = t;
            return;
        }
        throw new NullPointerException("Optional of null value.");
    }

    public static <T> a<T> a() {
        return f1152a;
    }

    public static <T> a<T> a(T t) {
        return new a<>(t);
    }

    public static <T> a<T> b(T t) {
        return t == null ? a() : a(t);
    }

    public T b() {
        T t = this.b;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    public T c(T t) {
        T t2 = this.b;
        return t2 != null ? t2 : t;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        T t = this.b;
        T t2 = ((a) obj).b;
        if (t != t2) {
            return (t == null || t2 == null || !t.equals(t2)) ? false : true;
        }
        return true;
    }

    public int hashCode() {
        T t = this.b;
        if (t == null) {
            return 0;
        }
        return t.hashCode();
    }

    public String toString() {
        T t = this.b;
        if (t == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{t});
    }
}
