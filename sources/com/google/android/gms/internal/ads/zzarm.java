package com.google.android.gms.internal.ads;

import java.util.Map;

final /* synthetic */ class zzarm implements Runnable {
    private final Map zzbjl;
    private final zzarl zzdel;

    zzarm(zzarl zzarl, Map map) {
        this.zzdel = zzarl;
        this.zzbjl = map;
    }

    public final void run() {
        this.zzdel.zzo(this.zzbjl);
    }
}
