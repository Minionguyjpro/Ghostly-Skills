package com.google.android.gms.internal.ads;

import android.view.View;

final class zzasl implements Runnable {
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ int zzdch;
    private final /* synthetic */ zzasj zzdes;

    zzasl(zzasj zzasj, View view, zzait zzait, int i) {
        this.zzdes = zzasj;
        this.val$view = view;
        this.zzdcg = zzait;
        this.zzdch = i;
    }

    public final void run() {
        this.zzdes.zza(this.val$view, this.zzdcg, this.zzdch - 1);
    }
}
