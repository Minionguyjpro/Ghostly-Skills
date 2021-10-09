package com.google.android.gms.internal.ads;

import java.lang.Thread;

final class zzadd implements Thread.UncaughtExceptionHandler {
    private final /* synthetic */ zzadb zzccb;
    private final /* synthetic */ Thread.UncaughtExceptionHandler zzccc;

    zzadd(zzadb zzadb, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzccb = zzadb;
        this.zzccc = uncaughtExceptionHandler;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            this.zzccb.zza(thread, th);
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.zzccc;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        } catch (Throwable th2) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.zzccc;
            if (uncaughtExceptionHandler2 != null) {
                uncaughtExceptionHandler2.uncaughtException(thread, th);
            }
            throw th2;
        }
    }
}
