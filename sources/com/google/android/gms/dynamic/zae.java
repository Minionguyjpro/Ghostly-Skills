package com.google.android.gms.dynamic;

import com.google.android.gms.dynamic.DeferredLifecycleHelper;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zae implements DeferredLifecycleHelper.zaa {
    private final /* synthetic */ DeferredLifecycleHelper zaa;

    zae(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zaa = deferredLifecycleHelper;
    }

    public final int zaa() {
        return 4;
    }

    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        this.zaa.zaa.onStart();
    }
}
