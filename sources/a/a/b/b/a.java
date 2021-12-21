package a.a.b.b;

import a.a.d.c;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public abstract class a implements a.a.d.a, Serializable {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f966a = C0032a.f967a;
    private transient a.a.d.a b;
    protected final Object receiver;

    /* access modifiers changed from: protected */
    public abstract a.a.d.a d();

    /* renamed from: a.a.b.b.a$a  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    private static class C0032a implements Serializable {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final C0032a f967a = new C0032a();

        private C0032a() {
        }
    }

    public a() {
        this(f966a);
    }

    protected a(Object obj) {
        this.receiver = obj;
    }

    public Object e() {
        return this.receiver;
    }

    public a.a.d.a f() {
        a.a.d.a aVar = this.b;
        if (aVar != null) {
            return aVar;
        }
        a.a.d.a d = d();
        this.b = d;
        return d;
    }

    public c a() {
        throw new AbstractMethodError();
    }

    public String b() {
        throw new AbstractMethodError();
    }

    public String c() {
        throw new AbstractMethodError();
    }
}
