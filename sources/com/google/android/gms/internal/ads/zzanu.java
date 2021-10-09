package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final /* synthetic */ class zzanu implements Runnable {
    private final zzaoj zzbnu;
    private final zzanz zzcvk;
    private final Class zzcvp;
    private final zzanj zzcvq;
    private final Executor zzcvr;

    zzanu(zzaoj zzaoj, zzanz zzanz, Class cls, zzanj zzanj, Executor executor) {
        this.zzbnu = zzaoj;
        this.zzcvk = zzanz;
        this.zzcvp = cls;
        this.zzcvq = zzanj;
        this.zzcvr = executor;
    }

    public final void run() {
        zzano.zza(this.zzbnu, this.zzcvk, this.zzcvp, this.zzcvq, this.zzcvr);
    }
}
