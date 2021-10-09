package a.a.a;

import a.a.b.b.d;
import a.a.b.b.h;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: StartAppSDK */
public final class s implements Serializable, List, RandomAccess {

    /* renamed from: a  reason: collision with root package name */
    public static final s f965a = new s();

    public int a() {
        return 0;
    }

    public boolean a(Void voidR) {
        h.b(voidR, "element");
        return false;
    }

    public /* synthetic */ void add(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public int b(Void voidR) {
        h.b(voidR, "element");
        return -1;
    }

    public int c(Void voidR) {
        h.b(voidR, "element");
        return -1;
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public int hashCode() {
        return 1;
    }

    public boolean isEmpty() {
        return true;
    }

    public /* synthetic */ Object remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* synthetic */ Object set(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object[] toArray() {
        return d.a(this);
    }

    public <T> T[] toArray(T[] tArr) {
        return d.a(this, tArr);
    }

    public String toString() {
        return "[]";
    }

    private s() {
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Void) {
            return a((Void) obj);
        }
        return false;
    }

    public final int indexOf(Object obj) {
        if (obj instanceof Void) {
            return b((Void) obj);
        }
        return -1;
    }

    public final int lastIndexOf(Object obj) {
        if (obj instanceof Void) {
            return c((Void) obj);
        }
        return -1;
    }

    public final int size() {
        return a();
    }

    public boolean equals(Object obj) {
        return (obj instanceof List) && ((List) obj).isEmpty();
    }

    public boolean containsAll(Collection collection) {
        h.b(collection, "elements");
        return collection.isEmpty();
    }

    /* renamed from: a */
    public Void get(int i) {
        throw new IndexOutOfBoundsException("Empty list doesn't contain element at index " + i + '.');
    }

    public Iterator iterator() {
        return r.f964a;
    }

    public ListIterator listIterator() {
        return r.f964a;
    }

    public ListIterator listIterator(int i) {
        if (i == 0) {
            return r.f964a;
        }
        throw new IndexOutOfBoundsException("Index: " + i);
    }

    public List subList(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return this;
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2);
    }
}
