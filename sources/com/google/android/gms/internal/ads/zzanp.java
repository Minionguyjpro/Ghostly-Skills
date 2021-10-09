package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzanp implements Runnable {
    private final zzanl zzcvj;
    private final zzanz zzcvk;

    zzanp(zzanl zzanl, zzanz zzanz) {
        this.zzcvj = zzanl;
        this.zzcvk = zzanz;
    }

    public final void run() {
        Throwable e;
        zzanl zzanl = this.zzcvj;
        try {
            zzanl.zzh(this.zzcvk.get());
        } catch (ExecutionException e2) {
            e = e2.getCause();
            zzanl.zzb(e);
        } catch (InterruptedException e3) {
            e = e3;
            Thread.currentThread().interrupt();
            zzanl.zzb(e);
        } catch (Exception e4) {
            e = e4;
            zzanl.zzb(e);
        }
    }
}
