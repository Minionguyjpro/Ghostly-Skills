package com.google.android.gms.internal.ads;

final class zzads implements Runnable {
    private final /* synthetic */ zzadk zzccn;

    zzads(zzadk zzadk) {
        this.zzccn = zzadk;
    }

    public final void run() {
        synchronized (this.zzccn.zzbzh) {
            if (this.zzccn.zzccj != null) {
                this.zzccn.onStop();
                this.zzccn.zzc(2, "Timed out waiting for ad response.");
            }
        }
    }
}
