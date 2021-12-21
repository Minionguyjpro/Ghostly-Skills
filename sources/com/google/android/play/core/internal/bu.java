package com.google.android.play.core.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class bu extends WeakReference<Throwable> {

    /* renamed from: a  reason: collision with root package name */
    private final int f1125a;

    public bu(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        this.f1125a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            bu buVar = (bu) obj;
            return this.f1125a == buVar.f1125a && get() == buVar.get();
        }
    }

    public final int hashCode() {
        return this.f1125a;
    }
}
