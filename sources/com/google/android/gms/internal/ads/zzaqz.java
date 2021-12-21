package com.google.android.gms.internal.ads;

import android.view.View;

final class zzaqz implements Runnable {
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ int zzdch;
    private final /* synthetic */ zzaqx zzdci;

    zzaqz(zzaqx zzaqx, View view, zzait zzait, int i) {
        this.zzdci = zzaqx;
        this.val$view = view;
        this.zzdcg = zzait;
        this.zzdch = i;
    }

    public final void run() {
        this.zzdci.zza(this.val$view, this.zzdcg, this.zzdch - 1);
    }
}
