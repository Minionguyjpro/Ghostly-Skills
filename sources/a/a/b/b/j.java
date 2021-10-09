package a.a.b.b;

import a.a.d.a;
import a.a.d.e;

/* compiled from: StartAppSDK */
public abstract class j extends a implements e {
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            if (!a().equals(jVar.a()) || !b().equals(jVar.b()) || !c().equals(jVar.c()) || !h.a(e(), jVar.e())) {
                return false;
            }
            return true;
        } else if (obj instanceof e) {
            return obj.equals(f());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (((a().hashCode() * 31) + b().hashCode()) * 31) + c().hashCode();
    }

    public String toString() {
        a f = f();
        if (f != this) {
            return f.toString();
        }
        return "property " + b() + " (Kotlin reflection is not available)";
    }
}
