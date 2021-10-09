package com.google.android.gms.internal.ads;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzazt {
    private final ConcurrentHashMap<zzazu, List<Throwable>> zzdoy = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzdoz = new ReferenceQueue<>();

    zzazt() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        while (true) {
            Reference<? extends Throwable> poll = this.zzdoz.poll();
            if (poll != null) {
                this.zzdoy.remove(poll);
            } else {
                return this.zzdoy.get(new zzazu(th, (ReferenceQueue<Throwable>) null));
            }
        }
    }
}
