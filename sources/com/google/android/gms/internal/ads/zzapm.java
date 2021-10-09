package com.google.android.gms.internal.ads;

final class zzapm implements Runnable {
    private final /* synthetic */ zzapi zzcyd;
    private final /* synthetic */ boolean zzcye;

    zzapm(zzapi zzapi, boolean z) {
        this.zzcyd = zzapi;
        this.zzcye = z;
    }

    public final void run() {
        this.zzcyd.zza("windowVisibilityChanged", "isVisible", String.valueOf(this.zzcye));
    }
}
