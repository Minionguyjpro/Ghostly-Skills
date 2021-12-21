package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

final class zzacl implements ViewTreeObserver.OnScrollChangedListener {
    private final /* synthetic */ zzace zzcbi;
    private final /* synthetic */ WeakReference zzcbj;

    zzacl(zzace zzace, WeakReference weakReference) {
        this.zzcbi = zzace;
        this.zzcbj = weakReference;
    }

    public final void onScrollChanged() {
        this.zzcbi.zza((WeakReference<zzaqw>) this.zzcbj, true);
    }
}
