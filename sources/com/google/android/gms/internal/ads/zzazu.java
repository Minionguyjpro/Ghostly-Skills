package com.google.android.gms.internal.ads;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class zzazu extends WeakReference<Throwable> {
    private final int zzdpa;

    public zzazu(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, (ReferenceQueue) null);
        if (th != null) {
            this.zzdpa = System.identityHashCode(th);
            return;
        }
        throw new NullPointerException("The referent cannot be null");
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            zzazu zzazu = (zzazu) obj;
            return this.zzdpa == zzazu.zzdpa && get() == zzazu.get();
        }
    }

    public final int hashCode() {
        return this.zzdpa;
    }
}
