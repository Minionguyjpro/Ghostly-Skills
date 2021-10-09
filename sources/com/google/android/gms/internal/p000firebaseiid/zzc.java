package com.google.android.gms.internal.p000firebaseiid;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* renamed from: com.google.android.gms.internal.firebase-iid.zzc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
final class zzc implements zzb {
    private zzc() {
    }

    public final ScheduledExecutorService zza(int i, ThreadFactory threadFactory, int i2) {
        return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1, threadFactory));
    }
}
