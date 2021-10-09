package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzanv implements Runnable {
    private final zzaoj zzbnu;
    private final zzanz zzcvk;

    zzanv(zzaoj zzaoj, zzanz zzanz) {
        this.zzbnu = zzaoj;
        this.zzcvk = zzanz;
    }

    public final void run() {
        Throwable e;
        zzaoj zzaoj = this.zzbnu;
        try {
            zzaoj.set(this.zzcvk.get());
        } catch (ExecutionException e2) {
            e = e2.getCause();
            zzaoj.setException(e);
        } catch (InterruptedException e3) {
            e = e3;
            Thread.currentThread().interrupt();
            zzaoj.setException(e);
        } catch (Exception e4) {
            zzaoj.setException(e4);
        }
    }
}
