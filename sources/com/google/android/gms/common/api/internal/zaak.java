package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.zad;
import com.google.android.gms.signin.internal.zam;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zaak extends zad {
    private final WeakReference<zaaf> zaa;

    zaak(zaaf zaaf) {
        this.zaa = new WeakReference<>(zaaf);
    }

    public final void zaa(zam zam) {
        zaaf zaaf = (zaaf) this.zaa.get();
        if (zaaf != null) {
            zaaf.zaa.zaa((zaay) new zaan(this, zaaf, zaaf, zam));
        }
    }
}
