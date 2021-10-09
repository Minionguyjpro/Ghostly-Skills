package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzjj;
import java.lang.ref.WeakReference;

final class zzbm implements Runnable {
    private final /* synthetic */ WeakReference zzaas;
    private final /* synthetic */ zzbl zzaat;

    zzbm(zzbl zzbl, WeakReference weakReference) {
        this.zzaat = zzbl;
        this.zzaas = weakReference;
    }

    public final void run() {
        boolean unused = this.zzaat.zzaap = false;
        zza zza = (zza) this.zzaas.get();
        if (zza != null) {
            zzjj zzb = this.zzaat.zzaao;
            if (zza.zzc(zzb)) {
                zza.zzb(zzb);
                return;
            }
            zzakb.zzdj("Ad is not visible. Not refreshing ad.");
            zza.zzvv.zzg(zzb);
        }
    }
}
