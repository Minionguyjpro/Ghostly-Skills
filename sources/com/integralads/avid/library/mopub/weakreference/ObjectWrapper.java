package com.integralads.avid.library.mopub.weakreference;

import java.lang.ref.WeakReference;

public class ObjectWrapper<T> {
    private WeakReference<T> weakReference;

    public ObjectWrapper(T t) {
        this.weakReference = new WeakReference<>(t);
    }

    public T get() {
        return this.weakReference.get();
    }

    public void set(T t) {
        this.weakReference = new WeakReference<>(t);
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean contains(Object obj) {
        Object obj2 = get();
        return (obj2 == null || obj == null || !obj2.equals(obj)) ? false : true;
    }
}
