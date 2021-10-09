package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
abstract class zaap implements Runnable {
    private final /* synthetic */ zaaf zaa;

    private zaap(zaaf zaaf) {
        this.zaa = zaaf;
    }

    /* access modifiers changed from: protected */
    public abstract void zaa();

    public void run() {
        this.zaa.zab.lock();
        try {
            if (!Thread.interrupted()) {
                zaa();
                this.zaa.zab.unlock();
            }
        } catch (RuntimeException e) {
            this.zaa.zaa.zaa(e);
        } finally {
            this.zaa.zab.unlock();
        }
    }

    /* synthetic */ zaap(zaaf zaaf, zaae zaae) {
        this(zaaf);
    }
}
