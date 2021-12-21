package com.google.android.gms.internal.ads;

import android.view.View;

final class zzasm implements View.OnAttachStateChangeListener {
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ zzasj zzdes;

    zzasm(zzasj zzasj, zzait zzait) {
        this.zzdes = zzasj;
        this.zzdcg = zzait;
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzdes.zza(view, this.zzdcg, 10);
    }

    public final void onViewDetachedFromWindow(View view) {
    }
}
