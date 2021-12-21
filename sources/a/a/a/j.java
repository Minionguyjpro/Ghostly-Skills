package a.a.a;

import a.a.b.b.h;
import java.util.Collection;

/* compiled from: StartAppSDK */
class j extends i {
    public static final <T> int a(Iterable<? extends T> iterable, int i) {
        h.b(iterable, "$receiver");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }
}
