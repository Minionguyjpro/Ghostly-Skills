package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zzakj implements ThreadFactory {
    private final AtomicInteger zzcnz = new AtomicInteger(1);
    private final /* synthetic */ String zzcrl;

    zzakj(String str) {
        this.zzcrl = str;
    }

    public final Thread newThread(Runnable runnable) {
        String str = this.zzcrl;
        int andIncrement = this.zzcnz.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 23);
        sb.append("AdWorker(");
        sb.append(str);
        sb.append(") #");
        sb.append(andIncrement);
        return new Thread(runnable, sb.toString());
    }
}
