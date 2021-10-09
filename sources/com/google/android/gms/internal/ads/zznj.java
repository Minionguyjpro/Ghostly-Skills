package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zznj implements Callable<T> {
    private final /* synthetic */ zzna zzaty;
    private final /* synthetic */ zzni zzatz;

    zznj(zzni zzni, zzna zzna) {
        this.zzatz = zzni;
        this.zzaty = zzna;
    }

    public final T call() {
        return this.zzaty.zza(this.zzatz.zzatw);
    }
}
