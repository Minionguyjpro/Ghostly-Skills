package com.google.android.gms.internal.ads;

final class zzaff implements Runnable {
    private final /* synthetic */ zzafa zzcgj;

    zzaff(zzafa zzafa) {
        this.zzcgj = zzafa;
    }

    public final void run() {
        if (this.zzcgj.zzcgi != null) {
            this.zzcgj.zzcgi.release();
            zzvs unused = this.zzcgj.zzcgi = null;
        }
    }
}
