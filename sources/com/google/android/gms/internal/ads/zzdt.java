package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

public final class zzdt implements Callable {
    private final zzcz zzps;
    private final zzba zztq;

    public zzdt(zzcz zzcz, zzba zzba) {
        this.zzps = zzcz;
        this.zztq = zzba;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzat */
    public final Void call() throws Exception {
        if (this.zzps.zzak() != null) {
            this.zzps.zzak().get();
        }
        zzba zzaj = this.zzps.zzaj();
        if (zzaj == null) {
            return null;
        }
        try {
            synchronized (this.zztq) {
                zzbfi.zza(this.zztq, zzbfi.zzb(zzaj));
            }
            return null;
        } catch (zzbfh unused) {
            return null;
        }
    }
}
