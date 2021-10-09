package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zaax extends zabk {
    private WeakReference<zaar> zaa;

    zaax(zaar zaar) {
        this.zaa = new WeakReference<>(zaar);
    }

    public final void zaa() {
        zaar zaar = (zaar) this.zaa.get();
        if (zaar != null) {
            zaar.zae();
        }
    }
}
