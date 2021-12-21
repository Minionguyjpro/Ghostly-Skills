package a.a.a;

import a.a.b.b.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/* compiled from: StartAppSDK */
class q extends p {
    public static final <T> T c(List<? extends T> list) {
        h.b(list, "$receiver");
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static final <T> T d(List<? extends T> list) {
        h.b(list, "$receiver");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static final <T> T e(List<? extends T> list) {
        h.b(list, "$receiver");
        if (!list.isEmpty()) {
            return list.get(g.a(list));
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static final <T, C extends Collection<? super T>> C a(Iterable<? extends T> iterable, C c) {
        h.b(iterable, "$receiver");
        h.b(c, "destination");
        for (Object add : iterable) {
            c.add(add);
        }
        return c;
    }

    public static final <T> List<T> a(Iterable<? extends T> iterable) {
        h.b(iterable, "$receiver");
        if (!(iterable instanceof Collection)) {
            return g.b(g.b(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return g.a();
        }
        if (size != 1) {
            return g.a(collection);
        }
        return g.a(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static final <T> List<T> b(Iterable<? extends T> iterable) {
        h.b(iterable, "$receiver");
        if (iterable instanceof Collection) {
            return g.a((Collection) iterable);
        }
        return (List) g.a(iterable, new ArrayList());
    }

    public static final <T> List<T> a(Collection<? extends T> collection) {
        h.b(collection, "$receiver");
        return new ArrayList<>(collection);
    }
}
