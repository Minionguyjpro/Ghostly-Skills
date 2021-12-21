package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzasg;

final /* synthetic */ class zzz implements zzasg {
    private final zzajh zzxh;
    private final Runnable zzxi;

    zzz(zzajh zzajh, Runnable runnable) {
        this.zzxh = zzajh;
        this.zzxi = runnable;
    }

    public final void zzda() {
        zzajh zzajh = this.zzxh;
        Runnable runnable = this.zzxi;
        if (!zzajh.zzcoc) {
            zzbv.zzek();
            zzakk.zzd(runnable);
        }
    }
}
