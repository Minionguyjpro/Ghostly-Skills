package com.google.android.gms.internal.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public interface zam {
    ExecutorService zaa(int i, int i2);

    ExecutorService zaa(int i, ThreadFactory threadFactory, int i2);

    ExecutorService zaa(ThreadFactory threadFactory, int i);
}
