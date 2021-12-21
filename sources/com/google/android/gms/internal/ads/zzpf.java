package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.lang.ref.WeakReference;

final class zzpf {
    /* access modifiers changed from: private */
    public final WeakReference<zzaqw> zzbjg;
    /* access modifiers changed from: private */
    public String zzbjh;

    public zzpf(zzaqw zzaqw) {
        this.zzbjg = new WeakReference<>(zzaqw);
    }

    public final void zza(zzacm zzacm) {
        zzacm.zza("/loadHtml", new zzpg(this, zzacm));
        zzacm.zza("/showOverlay", new zzpi(this, zzacm));
        zzacm.zza("/hideOverlay", new zzpj(this, zzacm));
        zzaqw zzaqw = (zzaqw) this.zzbjg.get();
        if (zzaqw != null) {
            zzaqw.zza("/sendMessageToSdk", (zzv<? super zzaqw>) new zzpk(this, zzacm));
        }
    }
}
