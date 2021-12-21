package com.google.android.play.core.internal;

public final class cc<T> implements ca, ce {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f1130a = new Object();
    private volatile ce<T> b;
    private volatile Object c = f1130a;

    private cc(ce<T> ceVar) {
        this.b = ceVar;
    }

    public static <P extends ce<T>, T> ce<T> b(P p) {
        bh.j(p);
        return p instanceof cc ? p : new cc(p);
    }

    public static <P extends ce<T>, T> ca<T> c(P p) {
        if (p instanceof ca) {
            return (ca) p;
        }
        bh.j(p);
        return new cc(p);
    }

    public final T a() {
        T t = this.c;
        if (t == f1130a) {
            synchronized (this) {
                t = this.c;
                if (t == f1130a) {
                    t = this.b.a();
                    T t2 = this.c;
                    if (t2 != f1130a && !(t2 instanceof cd)) {
                        if (t2 != t) {
                            String valueOf = String.valueOf(t2);
                            String valueOf2 = String.valueOf(t);
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 118 + String.valueOf(valueOf2).length());
                            sb.append("Scoped provider was invoked recursively returning different results: ");
                            sb.append(valueOf);
                            sb.append(" & ");
                            sb.append(valueOf2);
                            sb.append(". This is likely due to a circular dependency.");
                            throw new IllegalStateException(sb.toString());
                        }
                    }
                    this.c = t;
                    this.b = null;
                }
            }
        }
        return t;
    }
}
