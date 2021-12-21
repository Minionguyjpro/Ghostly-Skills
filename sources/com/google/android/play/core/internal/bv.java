package com.google.android.play.core.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class bv {

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentHashMap<bu, List<Throwable>> f1126a = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> b = new ReferenceQueue<>();

    bv() {
    }

    public final List<Throwable> a(Throwable th) {
        while (true) {
            Reference<? extends Throwable> poll = this.b.poll();
            if (poll == null) {
                break;
            }
            this.f1126a.remove(poll);
        }
        List<Throwable> list = this.f1126a.get(new bu(th, (ReferenceQueue<Throwable>) null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> putIfAbsent = this.f1126a.putIfAbsent(new bu(th, this.b), vector);
        return putIfAbsent == null ? vector : putIfAbsent;
    }
}
