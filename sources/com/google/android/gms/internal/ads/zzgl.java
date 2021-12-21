package com.google.android.gms.internal.ads;

import android.view.View;

final class zzgl implements Runnable {
    private final /* synthetic */ View zzaij;
    private final /* synthetic */ zzgk zzaik;

    zzgl(zzgk zzgk, View view) {
        this.zzaik = zzgk;
        this.zzaij = view;
    }

    public final void run() {
        this.zzaik.zzk(this.zzaij);
    }
}
