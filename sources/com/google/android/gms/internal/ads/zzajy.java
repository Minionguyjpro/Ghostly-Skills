package com.google.android.gms.internal.ads;

final class zzajy implements Runnable {
    private final /* synthetic */ zzajx zzcqt;

    zzajy(zzajx zzajx) {
        this.zzcqt = zzajx;
    }

    public final void run() {
        Thread unused = this.zzcqt.zzcqr = Thread.currentThread();
        this.zzcqt.zzdn();
    }
}
