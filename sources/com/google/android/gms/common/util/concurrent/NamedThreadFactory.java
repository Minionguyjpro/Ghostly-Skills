package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public class NamedThreadFactory implements ThreadFactory {
    private final String zza;
    private final int zzb;
    private final ThreadFactory zzc;

    public NamedThreadFactory(String str) {
        this(str, 0);
    }

    private NamedThreadFactory(String str, int i) {
        this.zzc = Executors.defaultThreadFactory();
        this.zza = (String) Preconditions.checkNotNull(str, "Name must not be null");
        this.zzb = 0;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.zzc.newThread(new zza(runnable, 0));
        newThread.setName(this.zza);
        return newThread;
    }
}
