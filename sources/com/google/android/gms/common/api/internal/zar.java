package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zar implements Runnable {
    private final /* synthetic */ zas zaa;

    zar(zas zas) {
        this.zaa = zas;
    }

    public final void run() {
        this.zaa.zam.lock();
        try {
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }
}
