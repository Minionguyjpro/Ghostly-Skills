package com.google.android.gms.internal.ads;

final class zzapl implements Runnable {
    private final /* synthetic */ zzapi zzcyd;

    zzapl(zzapi zzapi) {
        this.zzcyd = zzapi;
    }

    public final void run() {
        this.zzcyd.zza("surfaceDestroyed", new String[0]);
    }
}
