package a.a.a;

import a.a.b.b.h;
import java.util.List;

/* compiled from: StartAppSDK */
class d extends c {
    public static final <T> List<T> a(T[] tArr) {
        h.b(tArr, "$receiver");
        List<T> a2 = f.a(tArr);
        h.a((Object) a2, "ArraysUtilJVM.asList(this)");
        return a2;
    }
}
