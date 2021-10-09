package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-basement@@17.3.0 */
public class NumberedThreadFactory implements ThreadFactory {
    private final String zza;
    private final int zzb;
    private final AtomicInteger zzc;
    private final ThreadFactory zzd;

    public NumberedThreadFactory(String str) {
        this(str, 0);
    }

    private NumberedThreadFactory(String str, int i) {
        this.zzc = new AtomicInteger();
        this.zzd = Executors.defaultThreadFactory();
        this.zza = (String) Preconditions.checkNotNull(str, "Name must not be null");
        this.zzb = 0;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.zzd.newThread(new zza(runnable, 0));
        String str = this.zza;
        int andIncrement = this.zzc.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13);
        sb.append(str);
        sb.append("[");
        sb.append(andIncrement);
        sb.append("]");
        newThread.setName(sb.toString());
        return newThread;
    }
}
