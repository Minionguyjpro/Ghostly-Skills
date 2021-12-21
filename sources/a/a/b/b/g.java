package a.a.b.b;

import a.a.d.a;
import a.a.d.d;

/* compiled from: StartAppSDK */
public class g extends a implements f, d {
    private final int arity;

    public g(int i, Object obj) {
        super(obj);
        this.arity = i;
    }

    /* access modifiers changed from: protected */
    public a d() {
        return n.a(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (a() != null ? a().equals(gVar.a()) : gVar.a() == null) {
                if (!b().equals(gVar.b()) || !c().equals(gVar.c()) || !h.a(e(), gVar.e())) {
                    return false;
                }
                return true;
            }
            return false;
        } else if (obj instanceof d) {
            return obj.equals(f());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (((a() == null ? 0 : a().hashCode() * 31) + b().hashCode()) * 31) + c().hashCode();
    }

    public String toString() {
        a f = f();
        if (f != this) {
            return f.toString();
        }
        if ("<init>".equals(b())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + b() + " (Kotlin reflection is not available)";
    }
}
