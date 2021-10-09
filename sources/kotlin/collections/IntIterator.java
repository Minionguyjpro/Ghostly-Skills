package kotlin.collections;

import java.util.Iterator;

/* compiled from: Iterators.kt */
public abstract class IntIterator implements Iterator<Integer> {
    public abstract int nextInt();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final Integer next() {
        return Integer.valueOf(nextInt());
    }
}
