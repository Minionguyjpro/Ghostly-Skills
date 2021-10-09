package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final /* synthetic */ class zzut implements Runnable {
    private final zzus zzbpo;
    private final zzv zzbpp;
    private final Map zzbpq;

    zzut(zzus zzus, zzv zzv, Map map) {
        this.zzbpo = zzus;
        this.zzbpp = zzv;
        this.zzbpq = map;
    }

    public final void run() {
        zzus zzus = this.zzbpo;
        this.zzbpp.zza(zzus.getReference(), this.zzbpq);
    }
}
